package br.unisanta.firebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    // Fonte: https://firebase.google.com/docs/auth/android/firebaseui?hl=pt-br#kotlin
    // See: https://developer.android.com/training/basics/intents/result
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    // Fonte: https://firebase.google.com/docs/auth/android/firebaseui?hl=pt-br#kotlin
    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            val user = FirebaseAuth.getInstance().currentUser
            android.util.Log.d("FirebaseAuth", "Sign-in successful: ${user?.uid}")

            val intent = Intent(this,  UsuarioActivity::class.java)
            startActivity(intent)
        } else {
            val error = result.idpResponse?.error
            android.util.Log.e(
                "FirebaseAuth",
                "Sign-in failed; error code: ${error?.errorCode}",
                error
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val btnEntrar = findViewById<Button>(R.id.btn_entrar)

        btnEntrar.setOnClickListener {
            // Fonte: https://firebase.google.com/docs/auth/android/firebaseui?hl=pt-br#kotlin
            // Adaptado com o uso de inteligência artificial para a versão 8.0.2

            val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build())

// Create and launch sign-in intent
            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setIsSmartLockEnabled(false)
                .build()

            signInLauncher.launch(signInIntent)
        }

//        auth = FirebaseAuth.getInstance()
//
//        auth.signInWithEmailAndPassword("gs@unisanta.br", "123456")
//            .addOnCompleteListener {
//                task -> if (task.isSuccessful) {
//                    Log.d("FIREBASE", "LOGIN COM SUCESSO")
//                } else {
//                    Log.w("FIREBASE", "LOGIN INCORRETO: ${task.exception}")
//                }
//            }
    }
}