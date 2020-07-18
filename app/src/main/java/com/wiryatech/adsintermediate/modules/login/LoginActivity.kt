package com.wiryatech.adsintermediate.modules.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wiryatech.adsintermediate.R
import com.wiryatech.adsintermediate.utils.UserPreference
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userPreference = UserPreference(this)

        btn_login.setOnClickListener {
            if (et_username.text.isNullOrEmpty()) {
                et_username.error = "Please Input Username"
            } else {
                userPreference.setUserName(et_username.text.toString())
                userPreference.setStatusUser(true)

                var resultIntent = Intent()
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}