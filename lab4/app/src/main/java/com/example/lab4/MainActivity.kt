package com.example.lab4

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val REQUEST_CODE = 10000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    private fun showActivity() {
        val i = Intent(this, SecondActivity::class.java)
        i.putExtra("Parametr", "Twoje dane")
        startActivityForResult(i, REQUEST_CODE)
    }

    fun myButtonClick (v: View) {
        statusText.text = "Uruchamiam"
        showActivity()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if ((requestCode == REQUEST_CODE) && (resultCode == Activity.RESULT_OK)) {
            if (data != null) {
                if (data.hasExtra("returnString1")) {
                    statusText.text = data.extras.getString("returnString1")
                }
            }
        }
    }

}
