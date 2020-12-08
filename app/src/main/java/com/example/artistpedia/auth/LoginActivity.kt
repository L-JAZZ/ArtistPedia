package com.example.artistpedia.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.artistpedia.R
import com.example.artistpedia.activities.SearchActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        goToRegister()
        callLogin()
    }

    private fun goToRegister(){
        textView4.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    RegistrationActivity::class.java
                )
            )
            finish()
        }
    }
    private fun callLogin(){
        btn_login.setOnClickListener {
            performLogin()
        }
    }

    private fun performLogin() {
        val email = txt_email.text.toString()
        val password = txt_password.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill out email/pw.", Toast.LENGTH_SHORT).show()
            return
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener

                Toast.makeText(this,"Welcome",Toast.LENGTH_SHORT).show()

                val intent = Intent(this, SearchActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to log in", Toast.LENGTH_LONG).show()
            }
    }
}
