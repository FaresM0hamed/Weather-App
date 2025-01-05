# Weather Now & Later 🌤️

**Weather Now & Later** is an Android application designed to fetch and display current weather conditions and a 7-day forecast for a given city. Built with Kotlin, the app follows clean architecture principles, ensuring maintainability and scalability.

---

## **Screenshots** 📸
<img src="https://github.com/FaresM0hamed/Weather-App/blob/master/1.jpg" width=20% height=20%>   <img src="https://github.com/FaresM0hamed/Weather-App/blob/master/2.jpg" width=20% height=20%>   <img src="https://github.com/FaresM0hamed/Weather-App/blob/master/3.jpg" width=20% height=20%>

---

## **API 🌐**

The app integrates the Weather API to fetch real-time weather data.  
**Documentation**: [Weather API Docs](https://www.weatherapi.com/docs/)  
> This API provides detailed current weather and forecast information for cities worldwide.  

---

## **Features ✨**

1. **City Search**  
   - Users can input the name of a city to fetch weather data.

2. **Current Weather**  
   - Displays the current temperature, weather condition (e.g., sunny, cloudy, rainy), and an appropriate icon.

3. **7-Day Weather Forecast**  
   - Lists daily weather forecasts, including temperature, condition, and an icon.

4. **Last Searched City**  
   - Automatically displays weather data for the last searched city when the app is reopened.

5. **Dark Mode**  
   - Supports light and dark themes to enhance user experience.

---

## **Technology Stack 🛠️**

- **Kotlin**: Language of choice for modern Android development.
- **Jetpack Compose**: For building the UI.
- **DataStore**: For local storage of the last searched city.
- **Retrofit**: For network calls to the OpenWeatherMap API.
- **Dagger-Hilt**: For dependency injection.
- **Coroutines/Flow**: For asynchronous and reactive programming.
- **JUnit/Mockito**: For unit testing.

---

## **Custom Library 📚**

The app includes a custom library for:
- Formatting weather data (e.g., temperature display).
Library dependencies:  

```groovy
implementation("com.devfares.weatherutil:weather-utils:1.0.0")
```
In gradle.settings add maven local :
```groovy
dependencyResolutionManagement {
...
mavenLocal()
}
```
---

## **CI/CD ⚙️**

The project leverages **GitHub Actions** to automate the following steps:  

1. **Lint Your Code**  
   - Ensures code quality and consistency using linting tools.  

2. **Run Unit Tests**  
   - Executes unit tests to verify the functionality of your application.  

3. **Build and Generate an APK**  
   - Builds the project and generates a signed APK.  

4. **Upload APK to Firebase App Distribution**  
   - Automatically uploads the generated APK to Firebase App Distribution for easy testing.  

---


## Project Structure 🛠
<img src="https://github.com/FaresM0hamed/Weather-App/blob/master/project_structure.jpg" width=100% height=100%>

     📦interviewtaskadvansyswithvodafone
     ┣ 📂data
     ┃ ┣ 📂di
     ┃ ┃ ┣ 📜ApiServicesModule.kt
     ┃ ┃ ┣ 📜NetworkModule.kt
     ┃ ┃ ┗ 📜RepositoryModule.kt
     ┃ ┣ 📂respositories
     ┃ ┃ ┗ 📜WeatherRepositoryImpl.kt
     ┃ ┣ 📂sources
     ┃ ┃ ┣ 📂local
     ┃ ┃ ┃ ┗ 📜dataStore.kt
     ┃ ┃ ┗ 📂remote
     ┃ ┃ ┃ ┣ 📂endpoint
     ┃ ┃ ┃ ┃ ┗ 📜WeatherApi.kt
     ┃ ┃ ┃ ┣ 📂mappers
     ┃ ┃ ┃ ┃ ┗ 📜BaseApiMapper.kt
     ┃ ┃ ┃ ┗ 📂model
     ┃ ┃ ┃ ┃ ┗ 📜BaseApiResponse.kt
     ┃ ┗ 📂utilities
     ┃ ┃ ┣ 📜Constants.kt
     ┃ ┃ ┗ 📜globalNetworkCall.kt
     ┣ 📂domain
     ┃ ┣ 📂entities
     ┃ ┃ ┣ 📜Condition.kt
     ┃ ┃ ┣ 📜Current.kt
     ┃ ┃ ┣ 📜Day.kt
     ┃ ┃ ┣ 📜DomainResult.kt
     ┃ ┃ ┣ 📜Error.kt
     ┃ ┃ ┣ 📜Forecast.kt
     ┃ ┃ ┣ 📜ForecastDay.kt
     ┃ ┃ ┣ 📜Location.kt
     ┃ ┃ ┣ 📜MainResponse.kt
     ┃ ┃ ┗ 📜ValidationResult.kt
     ┃ ┣ 📂repositories
     ┃ ┃ ┗ 📜WeatherRepository.kt
     ┃ ┣ 📂usecases
     ┃ ┃ ┣ 📜FetchWeatherUseCase.kt
     ┃ ┃ ┣ 📜LoadLastSearchedCityWeatherUseCase.kt
     ┃ ┃ ┣ 📜SaveCityNameUseCase.kt
     ┃ ┃ ┗ 📜ValidateCityNameUseCase.kt
     ┃ ┗ 📂utilities
     ┣ 📂presentation
     ┃ ┗ 📂screens
     ┃ ┃ ┗ 📂home_screen
     ┃ ┃ ┃ ┣ 📜HomeScreenUiEffect.kt
     ┃ ┃ ┃ ┣ 📜HomeScreenUiState.kt
     ┃ ┃ ┃ ┣ 📜HomeScreenViewModel.kt
     ┃ ┃ ┃ ┗ 📜home_screen.kt
     ┣ 📂ui
     ┃ ┗ 📂theme
     ┃ ┃ ┣ 📜Color.kt
     ┃ ┃ ┣ 📜Font.kt
     ┃ ┃ ┣ 📜Theme.kt
     ┃ ┃ ┗ 📜Type.kt
     ┣ 📜InterviewTaskApp.kt
     ┗ 📜MainActivity.kt
