package com.michau.firebase_chat

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask

class SignInActivity : AppCompatActivity() {
    private val RC_SIGN_IN: Int=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        account_sign_in.setOnClickListener {
            val intent=AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(signInProviders)
                .setLogo(R.drawable.ic_baseline_fireplace_24)
                .build()

            startActivityForResult(intent, RC_SIGN_IN)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==RC_SIGN_IN){
            val response=IdpResponse.fromResultIntent(data)
        if(resultCode== Activity.RESULT_OK){
            val progressDialog=indeterminateProgressDialog("Setting account")
            //todo set up user with firebase
            startActivity(intentFor<MainActivity>().newTask().clearTask())  //clear task blokuje przed powrotem na strone logowania jak już jesteś zalogowany
        progressDialog.dismiss()
        }
        else if (resultCode== Activity.RESULT_CANCELED){
            if (response==null) return

            when(response.error?.errorCode){
                ErrorCodes.NO_NETWORK-> longSnackbar(constraint_layout, "no network")
                ErrorCodes.UNKNOWN_ERROR-> longSnackbar(constraint_layout, "Unknown error")
            }
        }
        }
    }

    private var signInProviders = listOf(
        AuthUI.IdpConfig.EmailBuilder()
            .setAllowNewAccounts(true)
            .setRequireName(true)
            .build()
    )
}