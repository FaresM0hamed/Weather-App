package com.devfares.interviewtaskadvansyswithvodafone.presentation.screens
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfares.interviewtaskadvansyswithvodafone.data.sources.local.DataStoreManager
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.DomainResult
import com.devfares.interviewtaskadvansyswithvodafone.domain.repositories.WeatherRepository
import com.devfares.interviewtaskadvansyswithvodafone.domain.usecases.FetchWeatherUseCase
import com.devfares.interviewtaskadvansyswithvodafone.domain.usecases.LoadLastSearchedCityWeatherUseCase
import com.devfares.interviewtaskadvansyswithvodafone.domain.usecases.SaveCityNameUseCase
import com.devfares.interviewtaskadvansyswithvodafone.domain.usecases.ValidateCityNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
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

    init {
        loadLastSearchedCityWeather()
    }

    fun getWeather(city: String) {
        val validationResult = validateCityNameUseCase(city)
        if (!validationResult.isValid) {
            _uiState.value = _uiState.value.copy(errorMessage = validationResult.errorMessage ?: "")
            return
        }

        _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = "")

        viewModelScope.launch {
            when (val result = fetchWeatherUseCase.invoke(city = city)) {
                is DomainResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        weatherData = result.data,
                        errorMessage = ""
                    )
                    saveCityName(city)
                }
                is DomainResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.message ?: "Something went wrong!"
                    )
                }
                is DomainResult.UnAuthorized -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Unauthorized access. Please login again."
                    )
                }
                is DomainResult.UnKnownError -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "An unknown error occurred. Please try again."
                    )
                }
                is DomainResult.Nothing -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "No data available."
                    )
                }
            }
        }
    }

    private fun saveCityName(city: String) {
        viewModelScope.launch {
            saveCityNameUseCase(city)
        }
    }

    private fun loadLastSearchedCityWeather() {
        viewModelScope.launch {
            loadLastSearchedCityWeatherUseCase { result ->
                result?.let { city ->
                    getWeather(city)
                } ?: run {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "No previously searched city found."
                    )
                }
            }
        }
    }
}

