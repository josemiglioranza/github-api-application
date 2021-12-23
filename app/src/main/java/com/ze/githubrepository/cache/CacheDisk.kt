package com.ze.githubrepository.cache

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

object CacheDisk {

    private lateinit var database: SharedPreferences

    fun initDatabase(context: Context){
        database = context.getSharedPreferences("githubBase", Context.MODE_PRIVATE)
    }

    fun exist(tag: String) = database.contains(tag)

    fun <T> find(tag: String, type: Class<T>): T {
        val result = database.getString(tag, "[]")
        return Gson().fromJson(result, type)
    }

    fun <T>save(tag: String, data: T) {
        database
            .edit()
            .putString(tag, Gson().toJson(data))
            .apply() //Esse apply vai fazer as coisas na thread de background e não na principal
    }

    fun delete(tag: String) {
        database
            .edit()
            .remove(tag)
            .apply()
    }

}