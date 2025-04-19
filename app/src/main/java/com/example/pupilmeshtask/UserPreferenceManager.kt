package com.example.pupilmeshtask

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore by preferencesDataStore(name = "user_pref")

@Singleton
class UserPreferenceManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private val USER_EMAIL_KEY = stringPreferencesKey("user_email")
    }

    val userEmail: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[USER_EMAIL_KEY]
    }

    suspend fun saveUserEmail(email: String) {
        context.dataStore.edit { prefs ->
            prefs[USER_EMAIL_KEY] = email
        }
    }

    suspend fun clearUserEmail() {
        context.dataStore.edit { prefs ->
            prefs.remove(USER_EMAIL_KEY)
        }
    }
}
