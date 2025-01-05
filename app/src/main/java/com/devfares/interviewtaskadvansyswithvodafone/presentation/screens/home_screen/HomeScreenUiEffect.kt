package com.devfares.interviewtaskadvansyswithvodafone.presentation.screens.home_screen

sealed class HomeScreenUiEffect {
    data class ShowSnackBar(val message: String) : HomeScreenUiEffect()
}
