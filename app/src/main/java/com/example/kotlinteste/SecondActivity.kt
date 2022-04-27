package com.example.kotlinteste

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class SecondActivity : AppCompatActivity() {

    lateinit var btSecondCadastrar: Button
    lateinit var btSecondListar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

            btSecondCadastrar = findViewById(R.id.btSecondCadastrar)
        btSecondCadastrar.setOnClickListener(
            View.OnClickListener {
                val intent = Intent(this,CadastrarCEPActitivity::class.java)
                startActivity(intent)
            }
        )
        btSecondListar = findViewById(R.id.btSecondListar)
        btSecondListar.setOnClickListener(
            View.OnClickListener {
                val intent = Intent(this,Listar::class.java)
                startActivity(intent)
            }
        )
    }
}