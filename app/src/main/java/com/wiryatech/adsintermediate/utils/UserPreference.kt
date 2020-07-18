package com.wiryatech.adsintermediate.utils

import android.content.Context
import com.wiryatech.adsintermediate.utils.Const.PREF_NAME
import com.wiryatech.adsintermediate.utils.Const.STATUS_USER
import com.wiryatech.adsintermediate.utils.Const.USER_NAME

class UserPreference(context: Context) {

    private val preference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun setStatusUser(status: Boolean) {
        val editor = preference.edit()
        editor.putBoolean(STATUS_USER, status)
        editor.apply()
    }

    fun getStatusUser() : Boolean = preference.getBoolean(STATUS_USER, false)

    fun setUserName(name: String) {
        val editor = preference.edit()
        editor.putString(USER_NAME, name)
        editor.apply()
    }

    fun getUserName() : String? = preference.getString(USER_NAME, "")
}