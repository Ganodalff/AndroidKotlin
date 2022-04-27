package com.example.kotlinteste

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.rpc.context.AttributeContext
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

class CadastrarCEPActitivity : AppCompatActivity() {
    private var usuarioID = ""
    private val db = FirebaseFirestore.getInstance()
    private var txtCEPCEP: EditText? = null
    private var txtCEPEstado: EditText? = null
    private var txtCEPCidade: EditText? = null
    private var txtCEPBairro: EditText? = null
    private var txtCEPRua: EditText? = null
    private var txtCEPComplemento: EditText? = null
    private var txtCEPNumero: EditText? = null
    lateinit var btCEPCadastrar: Button
    lateinit var btCEPCompletar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_cepactitivity)

        txtCEPCEP = findViewById(R.id.txtCEPCEP)
        txtCEPEstado = findViewById(R.id.txtCEPEstado)
        txtCEPCidade = findViewById(R.id.txtCEPCidade)
        txtCEPBairro = findViewById(R.id.txtCEPBairro)
        txtCEPRua = findViewById(R.id.txtCEPRua)
        txtCEPNumero = findViewById(R.id.txtCEPNumero)
        txtCEPComplemento = findViewById(R.id.txtCEPComplemento)
        btCEPCadastrar = findViewById(R.id.btCEPCadastrar)
        btCEPCompletar = findViewById(R.id.btCEPCompletar)

        btCEPCompletar.setOnClickListener(View.OnClickListener {
            val cep = txtCEPCEP?.text.toString()
            val url = "https://viacep.com.br/ws/$cep/json/"
            doAsync {
                val url = URL(url)
                val urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = 7000
                val content = urlConnection.inputStream.bufferedReader().use(BufferedReader::readText)
                var json = JSONObject(content);
                uiThread {
                    var estado = txtCEPEstado?.setText(json.getString("uf"))
                    var cidade = txtCEPCidade?.setText(json.getString("localidade"))
                    var bairro = txtCEPBairro?.setText(json.getString("bairro"))
                    var rua = txtCEPRua?.setText(json.getString("logradouro"))
                }
            }

        })

        btCEPCadastrar.setOnClickListener(View.OnClickListener { cadastrarcep() })
    }
    fun cadastrarcep(){
        var cep = txtCEPCEP?.text.toString()
        var estado = txtCEPEstado?.text.toString()
        var cidade = txtCEPCidade?.text.toString()
        var bairro = txtCEPBairro?.text.toString()
        var rua = txtCEPRua?.text.toString()
        var numero = txtCEPNumero?.text.toString()
        var complemento = txtCEPComplemento?.text.toString()
        val enderecoMap = hashMapOf(
            "cep" to cep,
            "estado" to estado,
            "cidade" to cidade,
            "bairro" to bairro,
            "rua" to rua,
            "numero" to numero,
            "complemento" to complemento
        )
        usuarioID = FirebaseAuth.getInstance().currentUser!!.uid
        db.collection("Endereco").document(usuarioID).set(enderecoMap).addOnCompleteListener{
            task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "Sucesso ao cadastrar Endereço", Toast.LENGTH_LONG).show()
                    val intent = Intent(this,SecondActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Erro",Toast.LENGTH_LONG).show()
                }
        }

    }
}