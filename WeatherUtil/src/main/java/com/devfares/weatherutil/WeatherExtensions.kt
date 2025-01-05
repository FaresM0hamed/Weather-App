package com.devfares.weatherutil


fun Double.formatTemperature(): String = "$this°C"
fun Double.formatWindSpeed(): String = "$this kph"
fun Int.formatHumidity(): String = "$this%"