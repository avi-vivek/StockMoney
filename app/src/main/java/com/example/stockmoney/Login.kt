package com.example.stockmoney

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signin_button_login.setOnClickListener {

            llProgressBar?.visibility = View.VISIBLE
            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )

            val email = email_edittext_login.text.toString()
            val password = password_edittext_login.text.toString()
            Log.d("Login", "Attempt login with email/pw: $email/****")

            if (email.isEmpty() || password.isEmpty()) {

                llProgressBar?.visibility = View.GONE

                window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                Toast.makeText(this, "Please enter your email and password", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener
                    // else
                    Log.d("Main", "Successfully Login")
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, homescreen::class.java)
                    startActivity(intent)
                    finish()
                    llProgressBar?.visibility = View.GONE
                    window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                }
                .addOnFailureListener {
                    Log.d("Main", "Failed to Login ${it.message}")
                    val toast =
                        Toast.makeText(this, "Failed to Login ${it.message}", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER, 0, 0)

                    llProgressBar?.visibility = View.GONE
                    window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                    toast.show()
                }
        }

        forgot_password_login.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.dialog_forgot_password, null)
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            builder.setTitle("Enter Your Email")
            builder.setCancelable(false)
            val email = view.findViewById<EditText>(R.id.forgot_password_email)
            builder.setPositiveButton("Submit", DialogInterface.OnClickListener { _, _ ->
                forgotPassword(email)
            })
            builder.setNegativeButton("Close", DialogInterface.OnClickListener { _, _ -> })
            builder.show()
        }
    }

    private fun forgotPassword(email: EditText) {
        if (email.text.toString().isEmpty()) {
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            Toast.makeText(this, "Please Enter valid email", Toast.LENGTH_SHORT).show()
            return
        }

        FirebaseAuth.getInstance().sendPasswordResetEmail(email.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Reset Link Sent.", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Please Enter valid email", Toast.LENGTH_SHORT).show()

            }
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}