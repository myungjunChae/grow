package com.softdough.grow.datasource.local

import android.content.Context
import com.google.gson.Gson
import com.softdough.grow.domain.model.Category
import java.lang.reflect.Type

class SharedPreference(context: Context, name: String) {

    val pref = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    val gson = Gson()

    val DEFAULT_STRING: String? = null
    val DEFAULT_INT = -1
    val DEFAULT_LONG = 0L
    val DEFAULT_FLOAT = 0F
    val DEFAULT_BOOLEAN = false

    val STRING_TYPE = ""
    val INT_TYPE = 0
    val LONG_TYPE = 0L
    val FLOAT_TYPE = 0F
    val BOOLEAN_TYPE = false

    //basic type
    fun <T> getValue(key: String, type: T): T {
        return when(type){
            STRING_TYPE -> this.pref.getString(key, DEFAULT_STRING) as T
            INT_TYPE -> this.pref.getInt(key, DEFAULT_INT) as T
            LONG_TYPE -> this.pref.getLong(key, DEFAULT_LONG) as T
            FLOAT_TYPE -> this.pref.getFloat(key, DEFAULT_FLOAT) as T
            BOOLEAN_TYPE -> this.pref.getBoolean(key, DEFAULT_BOOLEAN) as T
            else -> {
                throw IllegalArgumentException()
            }
        }
    }

    //class or list
    fun getValue(key: Any, type: Type) : Type {
        val json = this.pref.getString(key.toString(), DEFAULT_STRING)
        return gson.fromJson(json, type)
    }

    fun <T> setValue(key: Any, value: T): T {
        when (value) {
            is String -> this.pref.edit().putString(key.toString(), value as String).apply()
            is Int -> this.pref.edit().putInt(key.toString(), value as Int).apply()
            is Long -> this.pref.edit().putLong(key.toString(), value as Long).apply()
            is Float -> this.pref.edit().putFloat(key.toString(), value as Float).apply()
            is Boolean -> this.pref.edit().putBoolean(key.toString(), value as Boolean).apply()
            else -> {
                //TODO : 오류있음 확인
                var json = gson.toJson(value)
                setValue(key, json)
            }
        }

        return value
    }
}