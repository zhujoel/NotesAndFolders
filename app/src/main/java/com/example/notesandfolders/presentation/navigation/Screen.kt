package com.example.notesandfolders.presentation.navigation

/**
 * Represent all Screens in the app.
 */
sealed class Screen (
    val route: String
){
    object Landing : Screen("landing")
    object Note : Screen("Note")
}