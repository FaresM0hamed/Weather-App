package com.devfares.interviewtaskadvansyswithvodafone.data.sources.local

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "weather_prefs")

class DataStoreManager(private val context: Context) {
    companion object {
        private val LAST_SEARCHED_CITY_KEY = stringPreferencesKey("last_searched_city")
    }

    suspend fun saveLastSearchedCity(city: String) {
        context.dataStore.edit { preferences ->
            preferences[LAST_SEARCHED_CITY_KEY] = city
        }
    }

    val lastSearchedCity: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[LAST_SEARCHED_CITY_KEY]
        }
}
