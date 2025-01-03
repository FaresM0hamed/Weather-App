package com.devfares.interviewtaskadvansyswithvodafone.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.ForecastDay
import com.devfares.interviewtaskadvansyswithvodafone.domain.entities.MainResponse

//@Preview(showBackground = true)
@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> {
            CircularProgressIndicator()
        }

        uiState.errorMessage.isNotEmpty() -> {
            Text(text = uiState.errorMessage, color = Color.Red)
        }

        else -> {
            WeatherContent(weatherData = uiState.weatherData, viewModel = viewModel)
        }
    }
}

@Composable
fun WeatherContent(weatherData: MainResponse, viewModel: HomeScreenViewModel) {
    var city by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = city,
                onValueChange = {
                    city = it
                },
                label = { Text(text = "Search City") }
            )

            IconButton(onClick = { viewModel.getWeather(city) }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            }
        }
        WeatherCard(weatherData = weatherData)
        Spacer(modifier = Modifier.padding(8.dp))
        LazyColumn {
            items(weatherData.forecast.forecastDay.size) { index ->
                ForecastItem(forecastDay = weatherData.forecast.forecastDay[index])
            }
        }

    }
}

//@Preview(showBackground = true)
@Composable
fun WeatherCard(modifier: Modifier = Modifier, weatherData: MainResponse) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(96.dp)
    ) {
        Row {
            Column {
                Text(text = weatherData.current.tempC.toString() + "°C")
                Spacer(modifier = Modifier.padding(16.dp))
                Row {
                    Text(text = weatherData.location.name)
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = weatherData.location.region)
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = weatherData.location.country)
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                AsyncImage(
                    modifier = Modifier.size(160.dp),
                    model = "https:${weatherData.current.condition?.icon}".replace("64x64", "128x128"),
                    contentDescription = "Weather Icon",
                    contentScale = ContentScale.Crop,
//                    placeholder = painterResource(R.drawable.baseline_broken_image_24), // replace with your actual placeholder
//                    error = painterResource(R.drawable.baseline_error_24)
                )
            }
        }
    }

}


@Composable
fun ForecastItem(modifier: Modifier = Modifier,forecastDay: ForecastDay) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(96.dp)
    ) {
        Row {
            Column {
                Text(text = forecastDay.day?.maxTempC.toString() + "°C")
                Text(text = forecastDay.day?.minTempC.toString() + "°C")
                Spacer(modifier = Modifier.padding(16.dp))
                Row {
                    Text(text = forecastDay.date)
                    Spacer(modifier = Modifier.padding(4.dp))

                    Text(text = forecastDay.day?.condition?.text.toString())
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                AsyncImage(
                    modifier = Modifier.size(160.dp),
                    model = "https:${forecastDay.day?.condition?.icon}".replace("64x64", "128x128"),
                    contentDescription = "Weather Icon",
                    contentScale = ContentScale.Crop,
//                    placeholder = painterResource(R.drawable.baseline_broken_image_24), // replace with your actual placeholder
//                    error = painterResource(R.drawable.baseline_error_24)
                )
            }
        }
    }
}
