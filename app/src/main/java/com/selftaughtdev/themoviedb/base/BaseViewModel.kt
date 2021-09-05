package com.selftaughtdev.themoviedb.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.selftaughtdev.domain.base.model.Dialog
import com.selftaughtdev.domain.base.model.Redirect
import com.selftaughtdev.domain.base.model.Tag
import com.selftaughtdev.domain.exception.*
import com.selftaughtdev.themoviedb.util.Event

abstract class BaseViewModel : ViewModel() {

    val snackBarMessage = MutableLiveData<Event<String>>()
    val toastMessage = MutableLiveData<Event<String>>()
    val inlineException = MutableLiveData<Event<List<Tag>>>()
    val alertException = MutableLiveData<Event<Pair<String?, String>>>()
    val dialogException = MutableLiveData<Event<Dialog>>()
    val redirectException = MutableLiveData<Event<Redirect>>()

    fun setThrowable(throwable: Throwable) {
        when (throwable) {
            is SnackBarException -> snackBarMessage.value = Event(throwable.message)
            is ToastException -> toastMessage.value = Event(throwable.message)
            is InlineException -> inlineException.value = Event(throwable.tags.toList())
            is AlertException -> alertException.value =
                Event(Pair(throwable.title, throwable.message))
            is DialogException -> dialogException.value = Event(throwable.dialog)
            is RedirectException -> redirectException.value = Event(throwable.redirect)
        }
    }
}