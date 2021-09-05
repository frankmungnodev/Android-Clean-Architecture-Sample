dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "TheMovieDb"
include(":app")
include(":domain")
include(":data")
include(":data:network")
include(":data:common")
include(":data:cache")
include(":data:android")
