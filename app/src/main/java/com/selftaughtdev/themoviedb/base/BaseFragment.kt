package com.selftaughtdev.themoviedb.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.selftaughtdev.domain.annotation.Action
import com.selftaughtdev.domain.annotation.Redirect
import com.selftaughtdev.themoviedb.util.autoCleared
import com.selftaughtdev.themoviedb.util.observeEvent
import com.selftaughtdev.themoviedb.util.show
import com.selftaughtdev.themoviedb.util.showDialog

abstract class BaseFragment<BINDING : ViewDataBinding, VIEW_MODEL : BaseViewModel> : Fragment() {

    abstract val bindingVariable: Int

    @get:LayoutRes
    abstract val layoutId: Int

    abstract val title: String

    abstract val viewModel: VIEW_MODEL

    var binding by autoCleared<BINDING>()

    abstract fun observeViewModel()

    private var toast: Toast? = null
    private var snackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = title
        binding.apply {
            setVariable(bindingVariable, viewModel)
            executePendingBindings()
            lifecycleOwner = this@BaseFragment
        }
        observeViewModel()
        observeException()
    }

    private fun observeException() {

        viewModel.run {
            observeEvent(snackBarMessage) { message ->
                view?.let { snackBar = Snackbar.make(it, message, Snackbar.LENGTH_SHORT) }
                snackBar?.show()
            }
            observeEvent(toastMessage) { message ->
                context?.let { toast = Toast.makeText(it, message, Toast.LENGTH_SHORT) }
                toast?.show()
            }
            observeEvent(inlineException) { tags ->
                tags.forEach { tag ->
                    val currentView = view?.findViewWithTag<TextView>(tag.name)
                    currentView?.run {
                        tag.message?.let { text = it }
                        show()
                    }
                }
            }
            observeEvent(alertException) { pair ->
                context?.showDialog(
                    title = pair.first,
                    message = pair.second,
                    positiveMessage = getString(android.R.string.ok)
                )
            }
            observeEvent(dialogException) { dialog ->
                context?.showDialog(
                    title = dialog.title,
                    message = dialog.message,
                    positiveMessage = dialog.positiveMessage,
                    negativeMessage = dialog.negativeMessage,
                    positiveAction = {
                        positiveAction(
                            dialog.positiveAction,
                            dialog.positiveObject
                        )
                    },
                    negativeAction = {
                        negativeAction(
                            dialog.negativeAction,
                            dialog.negativeObject
                        )
                    }
                )
            }
            observeEvent(redirectException) { redirect ->
                redirectAction(redirect.redirect, redirect.redirectObject)
            }
        }
    }

    open fun positiveAction(@Action action: Int?, data: Any? = null) {}
    open fun negativeAction(@Action action: Int?, data: Any? = null) {}
    open fun redirectAction(@Redirect redirect: Int?, data: Any? = null) {}

    override fun onStop() {
        super.onStop()
        toast?.cancel()
        snackBar?.dismiss()
    }
}