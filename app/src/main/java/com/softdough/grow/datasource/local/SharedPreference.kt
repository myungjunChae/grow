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

    //basic type
    inline fun <reified T> getValue(key: String, type: T): T {
        return when (type) {
            String::class.java -> this.pref.getString(key, DEFAULT_STRING) as T
            Int::class.java -> this.pref.getInt(key, DEFAULT_INT) as T
            Long::class.java -> this.pref.getLong(key, DEFAULT_LONG) as T
            Float::class.java -> this.pref.getFloat(key, DEFAULT_FLOAT) as T
            Boolean::class.java -> this.pref.getBoolean(key, DEFAULT_BOOLEAN) as T
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
            value is String -> this.pref.edit().putString(key.toString(), value as String).apply()
            value is Int -> this.pref.edit().putInt(key.toString(), value as Int).apply()
            value is Long -> this.pref.edit().putLong(key.toString(), value as Long).apply()
            value is Float -> this.pref.edit().putFloat(key.toString(), value as Float).apply()
            value is Boolean -> this.pref.edit().putBoolean(key.toString(), value as Boolean).apply()
            else -> {
                var json = gson.toJson(value)
                setValue(key, json)
            }
        }

        return value
    }
}