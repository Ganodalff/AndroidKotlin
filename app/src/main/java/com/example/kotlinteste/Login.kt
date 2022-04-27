package com.example.kotlinteste

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Login : AppCompatActivity() {

    private var txtLoginLogin: EditText? = null
    private var txtLoginSenha: EditText? = null
    lateinit var btLoginLogar: Button

    private var mDataBaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    private var email: String? = null
    private var password: String? = null

    private val TAG = "Cadastrar"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        txtLoginLogin = findViewById(R.id.txtLoginLogin)
        txtLoginSenha = findViewById(R.id.txtLoginSenha)
        btLoginLogar = findViewById(R.id.btLoginLogar)
        mDatabase = FirebaseDatabase.getInstance()
        mDataBaseReference = mDatabase?.reference?.child("Users")
        mAuth = FirebaseAuth.getInstance()
        btLoginLogar.setOnClickListener(View.OnClickListener { loginAccount() })
    }

    private fun loginAccount() {
        email = txtLoginLogin?.text.toString()
        password = txtLoginSenha?.text.toString()
        mAuth!!.signInWithEmailAndPassword(email!!, password!!)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "LoginUserWithEmail:Sucess")
                    Toast.makeText(baseContext, "Login efetuado com sucesso.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this,SecondActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Log.d(TAG, "LoginUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Nao foi possivel logar.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}