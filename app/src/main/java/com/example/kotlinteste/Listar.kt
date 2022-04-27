package com.example.kotlinteste

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class Listar : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private var usuarioID = ""
    lateinit var btListarVoltar: Button

    private var txtListarCep: EditText? = null
    private var txtListarEstado: EditText? = null
    private var txtListarCidade: EditText? = null
    private var txtListarBairro: EditText? = null
    private var txtListarRua: EditText? = null
    private var txtListarNumero: EditText? = null
    private var txtListarComplemento: EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar)
        txtListarCep = findViewById(R.id.ListarCEP)
        txtListarEstado = findViewById(R.id.ListarEstado)
        txtListarCidade = findViewById(R.id.ListarCidade)
        txtListarBairro = findViewById(R.id.ListarBairro)
        txtListarRua = findViewById(R.id.ListarRua)
        txtListarNumero = findViewById(R.id.ListarNumero)
        txtListarComplemento = findViewById(R.id.ListarComplemento)


        Listar()

        btListarVoltar = findViewById(R.id.btListarVoltar)
        btListarVoltar.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)
        })

    }
    fun Listar(){
        usuarioID = FirebaseAuth.getInstance().currentUser!!.uid
        val ref = db.collection("Endereco").document(usuarioID)
        ref.get().addOnSuccessListener { document ->
            if (document.exists()){

                var cep = document.data?.get("cep").toString()
                var estado = document.data?.get("estado").toString()
                var cidade = document.data?.get("cidade").toString()
                var bairro = document.data?.get("bairro").toString()
                var rua = document.data?.get("rua").toString()
                var numero = document.data?.get("numero").toString()
                var complemento = document.data?.get("complemento").toString()

                txtListarCep?.setText(cep)
                txtListarEstado?.setText(estado)
                txtListarCidade?.setText(cidade)
                txtListarBairro?.setText(bairro)
                txtListarRua?.setText(rua)
                txtListarNumero?.setText(numero)
                txtListarComplemento?.setText(complemento)

            }else{
                Toast.makeText(this,"Sem conteudo",Toast.LENGTH_SHORT).show()
            }
        }
    }
}