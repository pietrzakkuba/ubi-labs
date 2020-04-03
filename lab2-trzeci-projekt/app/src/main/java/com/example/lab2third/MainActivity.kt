package com.example.lab2third

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pressMeButton.setOnClickListener {
            statusText.text = "Naciśnięto"
        }

        pressMeButton.setOnLongClickListener {
            statusText.text = "Długie naciśnięcie"
            true
        }
    }
}
