package com.example.kotlinteste

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var btMainLogar: Button
    lateinit var btMainCadastrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btMainLogar = findViewById(R.id.btMainLogar)
        btMainLogar.setOnClickListener(
            View.OnClickListener {
                val intent = Intent(this,Login::class.java)
                startActivity(intent)
            }
        )
        btMainCadastrar = findViewById(R.id.btMainCadastrar)
        btMainCadastrar.setOnClickListener(
            View.OnClickListener {
                val intent = Intent(this,Cadastrar::class.java)
                startActivity(intent)
            }
        )

    }

}