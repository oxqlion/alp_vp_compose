package com.example.alp_vp_dev1.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.alp_vp_dev1.model.User
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATA_STORE_NAME = "ALPVP"

class DataStoreManager(context: Context) {

    private val Context.userDataStore by preferencesDataStore(name = DATA_STORE_NAME)
    private val userDataStore = context.userDataStore

    companion object {
        val USER_KEY = stringPreferencesKey("user_key")
    }

    //Serialization
    suspend fun saveUser(user: User) {
        val jsonUser = Gson().toJson(user)
        userDataStore.edit { preferences ->
            preferences[USER_KEY] = jsonUser
        }
    }

    //Deserialization
    val getUser: Flow<User?> = userDataStore.data.map { preferences ->
        val jsonUser = preferences[USER_KEY] ?: return@map null
        Gson().fromJson(jsonUser, User::class.java)
    }
}