package com.example.learnnavbotsheet

sealed class Screens(val screen:String) {

    data object Home:Screens("home")
    data object Notification:Screens("notification")
    data object Post:Screens("post")
    data object Profile:Screens("profile")
    data object Search:Screens("search")
    data object Setting:Screens("setting")
}