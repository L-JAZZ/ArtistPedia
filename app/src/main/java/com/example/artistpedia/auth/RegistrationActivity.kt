package com.example.artistpedia.auth

import android.content.Intent
import android.content.SearchRecentSuggestionsProvider
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import com.example.artistpedia.R
import com.example.artistpedia.activities.SearchActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_registration.*
import com.example.artistpedia.models.User


class RegistrationActivity : AppCompatActivity() {

    private  val database by lazy{ FirebaseFirestore.getInstance()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        goToLogin()
        callRegister()
    }


    private fun goToLogin(){
        text_haveaccount.setOnClickListener {
            startActivity(Intent(this,
                LoginActivity::class.java))
            finish()
        }
    }

    private fun callRegister(){
        btn_register.setOnClickListener {
            register()
        }
    }

    private fun register(){
        val email = txt_email.text.toString()
        val password = txt_password.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill out each text field.", Toast.LENGTH_SHORT).show()
            return
        }
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){

                    val uid = FirebaseAuth.getInstance().uid ?: ""


                    val user = User(uid,txt_username.text.toString(),txt_email.text.toString(), emptyList())


                    Toast.makeText(this, "CHTO TO PROISXODIT", Toast.LENGTH_LONG).show()
                    saveUserToFirebase(uid,user)
                }
                    return@addOnCompleteListener
            }
                .addOnFailureListener{
                Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }


    private fun saveUserToFirebase(id: String, user: User) {
        database
            .collection("users")
            .document(id)
            .set(user)
            .addOnSuccessListener {
                startActivity(Intent(this,SearchActivity::class.java))
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_LONG)
                    .show()
            }
    }
}
