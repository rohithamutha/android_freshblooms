package com.example.freshblooms

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(context: Context) {

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    // Keys
    companion object {
        private const val KEY_ID = "user_id"
        private const val KEY_NAME = "user_name"
        private const val KEY_EMAIL = "user_email"
        private const val KEY_PHONE = "user_phone"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }

    // Save individual values
    fun saveString(key: String, value: String) {
        sharedPref.edit().putString(key, value).apply()
    }

    fun saveBoolean(key: String, value: Boolean) {
        sharedPref.edit().putBoolean(key, value).apply()
    }

    // Get individual values
    fun getString(key: String, defaultValue: String = ""): String {
        return sharedPref.getString(key, defaultValue) ?: defaultValue
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPref.getBoolean(key, defaultValue)
    }

    // Save full user data
    fun saveUserData(id: String, name: String, email: String, phone: String) {
        sharedPref.edit().apply {
            putString(KEY_ID, id)
            putString(KEY_NAME, name)
            putString(KEY_EMAIL, email)
            putString(KEY_PHONE, phone)
            putBoolean(KEY_IS_LOGGED_IN, true)
            apply()
        }
    }

    // Get user data
    fun getUserId(): String = getString(KEY_ID)
    fun getUserName(): String = getString(KEY_NAME)
    fun getUserEmail(): String = getString(KEY_EMAIL)
    fun getUserPhone(): String = getString(KEY_PHONE)
    fun isLoggedIn(): Boolean = getBoolean(KEY_IS_LOGGED_IN)

    // Logout (clear user data)
    fun logout() {
        sharedPref.edit().clear().apply()
    }
}
