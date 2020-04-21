package com.e.databaseexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun newProdcut(view: View) {
        val dbHandler = MyDBHandler(this, null, null, 1)

        val quantity = productQuantity.text.toInt()

        val product = Product(prod)
    }

}
