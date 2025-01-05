package com.devfares.interviewtaskadvansyswithvodafone.presentation.screens.home_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.devfares.interviewtaskadvansyswithvodafone.R
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.ForecastDay
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.MainResponse
import com.devfares.interviewtaskadvansyswithvodafone.ui.theme.sarabun
import com.devfares.weatherutil.formatTemperature

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            if (effect is HomeScreenUiEffect.ShowSnackBar) {
                snackBarHostState.showSnackbar(effect.message)
            }
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(snackBarHostState) }) { padding ->
        WeatherContent(
            uiState = uiState,
            modifier = Modifier.padding(padding),
            onSearchClicked = { city -> viewModel.getWeather(city) }
        )
    }
}

@Composable
fun WeatherContent(
    uiState: HomeScreenUiState, modifier: Modifier = Modifier, onSearchClicked: (String) -> Unit
) {
    val city = remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SearchBar(city, onSearchClicked)
        Spacer(modifier = Modifier.padding(8.dp))

        if (uiState.isCityEmpty) {
            EmptyState()
        } else {
            HeaderText(text = stringResource(R.string.current_weather))
            if (uiState.isLoading) {
                LoadingIndicator()
            } else {
                CurrentWeatherInfo(weatherData = uiState.weatherData)
                HeaderText(text = stringResource(R.string.next_7_days))
                ForecastList(forecastDays = uiState.weatherData.forecast.forecastDay)
            }
        }
    }
}


@Composable
fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search Icon",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(48.dp)
        )
        Text(
            text = stringResource(R.string.please_enter_a_city_to_get_the_weather_information),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,


        )
    }
}


@Composable
fun SearchBar(city: MutableState<String>, onSearchClicked: (String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = city.value,
            onValueChange = { city.value = it },
            label = { Text(text = "Search City") },
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = {
                    onSearchClicked(city.value)
                    keyboardController?.hide()
                }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(city.value)
                    keyboardController?.hide()
                }
            )
        )
    }
}


@Composable
fun HeaderText(text: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = text, fontSize = 16.sp, fontFamily = sarabun, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun LoadingIndicator() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ForecastList(forecastDays: List<ForecastDay>) {
    LazyRow {
        items(forecastDays.size) { index ->
            ForecastItem(forecastDay = forecastDays[index])
        }
    }
}

@Composable
fun CurrentWeatherInfo(weatherData: MainResponse) {
    Spacer(modifier = Modifier.padding(8.dp))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = weatherData.current.tempC.formatTemperature(),
            fontSize = 48.sp,
            fontFamily = sarabun,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.weight(1f)
        )
        VerticalDivider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = Modifier
                .height(60.dp)
                .padding(horizontal = 8.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            Text(text = weatherData.current.condition?.text.toString(), fontSize = 20.sp, fontFamily = sarabun, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = "${weatherData.location.name}, ${weatherData.location.country}", fontSize = 16.sp, fontFamily = sarabun, fontWeight = FontWeight.Medium)
        }
    }

    AsyncImage(
        modifier = Modifier
            .size(280.dp)
            .padding(top = 16.dp),
        model = "https:${weatherData.current.condition?.icon}".replace("64x64", "128x128"),
        contentDescription = "Weather Icon",
        contentScale = ContentScale.Crop
    )

    HorizontalDivider(
        thickness = 1.dp,
        color = Color.Gray,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    )
}

@Composable
fun ForecastItem(forecastDay: ForecastDay) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(180.dp)
            .width(150.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = forecastDay.date,
                modifier = Modifier.padding(top = 8.dp),
                fontFamily = sarabun,
                fontWeight = FontWeight.Medium
            )
            AsyncImage(
                modifier = Modifier.size(64.dp),
                model = "https:${forecastDay.day?.condition?.icon}",
                contentDescription = "Weather Icon",
                contentScale = ContentScale.Crop
            )
            Text(
                text = forecastDay.day?.condition?.text.toString(),
                fontFamily = sarabun,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${forecastDay.day?.minTempC?.formatTemperature()} - ${forecastDay.day?.maxTempC?.formatTemperature()}",
                fontFamily = sarabun,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
