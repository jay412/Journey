package chau.jordan.journey

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.ActionCodeSettings
import com.firebase.ui.auth.util.ExtraConstants



class LoginActivity : AppCompatActivity() {

    private companion object {
        const val RC_SIGN_IN = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        createSignInIntent()
    }

    private fun createSignInIntent() {
        //action code settings for email link sign in
        val actionCodeSettings = ActionCodeSettings.newBuilder()
            // /* yourPackageName= */ /* installIfNotAvailable= */ /* minimumVersion= */
            .setAndroidPackageName("chau.jordan.journey", true, null)
            .setHandleCodeInApp(true) // This must be set to true
            .setUrl("https://google.com") // This URL needs to be whitelisted
            .build()

        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().enableEmailLinkSignIn().setActionCodeSettings(actionCodeSettings).build(),
            AuthUI.IdpConfig.GoogleBuilder().build())
        /*AuthUI.IdpConfig.PhoneBuilder().build(),
        ,
        AuthUI.IdpConfig.FacebookBuilder().build(),
        AuthUI.IdpConfig.TwitterBuilder().build()) */

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN
        )

        /* TODO fix this
        if (AuthUI.canHandleIntent(intent)) {
            if (intent.extras == null) {
                // Create and launch sign-in intent if email link was not used

            }
            val link = intent.extras!!.getString(ExtraConstants.EMAIL_LINK_SIGN_IN)
            if (link != null) {
                startActivityForResult(
                    AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setEmailLink(link)
                        .setAvailableProviders(providers)
                        .build(),
                    RC_SIGN_IN
                )
            }
        } */
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                //val user = FirebaseAuth.getInstance().currentUser

                val intent = Intent(this, NavigationActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }
}
