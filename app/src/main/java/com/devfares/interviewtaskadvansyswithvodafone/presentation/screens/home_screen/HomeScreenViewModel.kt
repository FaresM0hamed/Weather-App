package com.devfares.interviewtaskadvansyswithvodafone.presentation.screens.home_screen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.DomainResult
import com.devfares.interviewtaskadvansyswithvodafone.domain.usecases.FetchWeatherUseCase
import com.devfares.interviewtaskadvansyswithvodafone.domain.usecases.LoadLastSearchedCityWeatherUseCase
import com.devfares.interviewtaskadvansyswithvodafone.domain.usecases.SaveCityNameUseCase
import com.devfares.interviewtaskadvansyswithvodafone.domain.usecases.ValidateCityNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val fetchWeatherUseCase: FetchWeatherUseCase,
    private val loadLastSearchedCityWeatherUseCase: LoadLastSearchedCityWeatherUseCase,
    private val saveCityNameUseCase: SaveCityNameUseCase,
    private val validateCityNameUseCase: ValidateCityNameUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<HomeScreenUiEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    init {
        loadLastSearchedCityWeather()
    }

    fun getWeather(city: String) {
        val validationResult = validateCityNameUseCase(city)
        if (!validationResult.isValid) {
            sendEffect(
                HomeScreenUiEffect.ShowSnackBar(
                    validationResult.errorMessage ?: "Invalid city name."
                )
            )
            return
        }

        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            when (val result = fetchWeatherUseCase.invoke(city = city)) {
                is DomainResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        weatherData = result.data
                    )
                    saveCityName(city)
                }
                is DomainResult.Error -> {
                    handleError(result.message)
                }
                is DomainResult.UnAuthorized -> {
                    handleError("Unauthorized access. Please login again.")
                }
                is DomainResult.UnKnownError -> {
                    handleError("An unknown error occurred. Please try again.")
                }
                is DomainResult.Nothing -> {
                    handleError("No data available.")
                }
            }
        }
    }

    private fun handleError(message: String?) {
        _uiState.value = _uiState.value.copy(isLoading = false)
        sendEffect(HomeScreenUiEffect.ShowSnackBar(message ?: "Something went wrong!"))
    }

    private fun saveCityName(city: String) {
        viewModelScope.launch { saveCityNameUseCase(city) }
    }

    private fun loadLastSearchedCityWeather() {
        viewModelScope.launch {
            loadLastSearchedCityWeatherUseCase { result ->
                if (result != null) {
                    _uiState.value = _uiState.value.copy(isCityEmpty = false)
                    getWeather(result)
                } else {
                    _uiState.value = _uiState.value.copy(isCityEmpty = true)
                    sendEffect(HomeScreenUiEffect.ShowSnackBar("No previously searched city found."))
                }
            }
        }
    }


    private fun sendEffect(effect: HomeScreenUiEffect) {
        viewModelScope.launch { _uiEffect.emit(effect) }
    }
}


