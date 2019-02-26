package chau.jordan.journey

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val user = FirebaseAuth.getInstance().currentUser
        if(user == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            //do more stuff
        }
    }

    fun signOut(view: View) {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                Toast.makeText(this, "You have signed out.", Toast.LENGTH_SHORT).show()
            }
    }
}
