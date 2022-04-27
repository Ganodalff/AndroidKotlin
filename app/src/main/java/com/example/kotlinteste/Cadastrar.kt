package com.example.kotlinteste

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.EmailAuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.rpc.context.AttributeContext

class Cadastrar : AppCompatActivity() {

    private var txtCadastrarLogin: EditText? = null
    private var txtCadastrarSenha: EditText? = null
    lateinit var btCadastrarCadastrar: Button

    private var mDataBaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    private val TAG = "Cadastrar"

    private var email: String? = null
    private var password: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

        txtCadastrarLogin = findViewById(R.id.txtCadastrarLogin)
        txtCadastrarSenha = findViewById(R.id.txtCadastrarsenha)
        btCadastrarCadastrar = findViewById(R.id.btCadastrarCadastar)
        mDatabase = FirebaseDatabase.getInstance()
        mDataBaseReference = mDatabase?.reference?.child("Users")
        mAuth = FirebaseAuth.getInstance()
        btCadastrarCadastrar.setOnClickListener(View.OnClickListener { createAccount() })
    }

    private fun createAccount(){
        email = txtCadastrarLogin?.text.toString()
        password = txtCadastrarSenha?.text.toString()
        if(email!!.isEmpty() || password!!.isEmpty()){
            Toast.makeText(baseContext, "Campos vazios",Toast.LENGTH_SHORT).show()
        }else{
            mAuth!!.createUserWithEmailAndPassword(email!!, password!!)?.addOnCompleteListener(this){
                task ->
                    if (task.isSuccessful){
                        Log.d(TAG,"CreateUserWithEmail:Sucess")
                        Toast.makeText(baseContext, "Cadastro efetuado com sucesso.",Toast.LENGTH_SHORT).show()
                    }else{
                        Log.d(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Nao foi possivel cadastrar.",Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

}


