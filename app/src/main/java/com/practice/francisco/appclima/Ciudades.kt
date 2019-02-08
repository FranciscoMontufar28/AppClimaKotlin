package com.practice.francisco.appclima

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Ciudades : AppCompatActivity() {

    val TAG = "com.practice.francisco.appclima.ciudades.CIUDAD"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudades)

        val bPopayan = findViewById<Button>(R.id.btn_popayan)
        val bBerlin = findViewById<Button>(R.id.btn_berlin)

        bPopayan.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(TAG, "3672778")
            startActivity(intent)
        }

        bBerlin.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(TAG, "2950159")
            startActivity(intent)
        }
    }
}
