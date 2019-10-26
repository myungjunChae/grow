package com.softdough.grow.datasource.local

import android.content.Context

class SharedPreference(context : Context, name: String){

    val pref = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    val DEFAULT_STRING : String? = null
    val DEFAULT_INT = -1
    val DEFAULT_LONG = 0L
    val DEFAULT_FLOAT = 0F
    val DEFAULT_BOOLEAN = false

    inline fun <reified T> getValue(key : String, type : T){
        when(type) {
            String::class.java -> this.pref.getString(key, DEFAULT_STRING)
            Int::class.java -> this.pref.getInt(key, DEFAULT_INT)
            Long::class.java -> this.pref.getLong(key, DEFAULT_LONG)
            Float::class.java -> this.pref.getFloat(key, DEFAULT_FLOAT)
            Boolean::class.java -> this.pref.getBoolean(key, DEFAULT_BOOLEAN)
            else -> throw IllegalArgumentException()
        }
    }

    fun <T> setValue(key : String, value : T){
        when(value) {
            value is String -> this.pref.edit().putString(key, value as String).apply()
            value is Int -> this.pref.edit().putInt(key, value as Int).apply()
            value is Long -> this.pref.edit().putLong(key, value as Long).apply()
            value is Float -> this.pref.edit().putFloat(key, value as Float).apply()
            value is Boolean -> this.pref.edit().putBoolean(key, value as Boolean).apply()
            else -> throw IllegalArgumentException()
        }
    }
}