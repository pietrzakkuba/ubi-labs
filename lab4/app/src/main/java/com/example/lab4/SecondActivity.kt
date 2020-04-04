package com.example.lab4

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val extras = intent.extras ?: return
        val message = extras.getString("Parametr")
        statusText.text = message
    }

    override fun finish() {
        val data = Intent()

        data.putExtra("returnString1", "Wiadomość dla ciebie")
        setResult(Activity.RESULT_OK, data)

        super.finish()
    }
}
