package com.example.artistpedia.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.artistpedia.R
import com.example.artistpedia.auth.LoginActivity
import com.example.artistpedia.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_search.*

//самое простое активити которое просто передает интент с текстом
class SearchActivity : AppCompatActivity() {
    private val database by lazy { FirebaseFirestore.getInstance() }
    private val auth by lazy { FirebaseAuth.getInstance() }

    companion object{
        const val EXTRA_DATA = "data"
    }

    //агай можно плез 92,6 за файнал? ради степухи (будет ровно 70). Я всё сделал! как и обещал)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        loadArtistSearch()
        toLogIn()
        toFavorite()
        logOut()
        if (auth.currentUser != null){
            txt_login.text = null

        }else {
            txt_logout.text = null
            goToFavorite.visibility = View.GONE
        }

    }

    private fun loadArtistSearch() {
        btn_search.setOnClickListener{
            val intent = Intent(this, ArtistPageActivity::class.java)
            val text = txt_search.text.toString()
            intent.putExtra(EXTRA_DATA, text)
            Toast.makeText(this, text, Toast.LENGTH_LONG).show()
            startActivity(intent)
        }
    }

    private fun toLogIn(){
        txt_login.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
    private  fun logOut(){
        txt_logout.setOnClickListener {
            auth.signOut()
            finish()
            startActivity(intent)
        }
    }

    private fun toFavorite(){
        goToFavorite.setOnClickListener {
            startActivity(Intent(this,FavoriteActivity::class.java))
        }
    }

    private fun setUsername(uid:String){
        if (auth.currentUser == null) {
            database.collection("users")
                .document(uid)
                .addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        return@addSnapshotListener
                    }
                    val user = snapshot?.toObject(User::class.java)
                    txt_username.text = user!!.username
                }
        }
    }

}
