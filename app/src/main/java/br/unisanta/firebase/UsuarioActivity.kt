package br.unisanta.firebase

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class UsuarioActivity : AppCompatActivity(R.layout.activity_usuario) {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        auth = FirebaseAuth.getInstance()

        val btnSair = findViewById<Button>(R.id.btnSair)
        val txvSaudacao = findViewById<TextView>(R.id.txv_saudacao)

        txvSaudacao.text = "Olá, ${auth.currentUser?.displayName ?: "usuário"}!"

        btnSair.setOnClickListener {
            finish()
        }
    }
}