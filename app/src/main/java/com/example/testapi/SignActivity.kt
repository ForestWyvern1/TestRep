package com.example.testapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.testapi.Case.userToken
import com.example.testapi.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_sign.*

class SignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)



        if (App.dm.isLogin()) {
            startActivity(Intent(this, MainActivity::class.java))
        }

        check_rememberMe.setOnClickListener {
            Toast.makeText(this, check_rememberMe.isChecked.toString(), Toast.LENGTH_SHORT).show()
        }

        signIn.setOnClickListener {
            if (check_rememberMe.isChecked) {
                App.dm.login()
                signIn()
            } else {
                signIn()
            }
        }

        loginIn.setOnClickListener {
            loginIn()
        }



    }

    override fun onBackPressed() {
        finishAffinity()
    }

    var checkchange = 0

    public fun change() {
        if (checkchange == 0) {
            sign.visibility = View.GONE
            login.visibility = View.VISIBLE
            checkchange = 1
        } else if (checkchange == 1) {
            sign.visibility = View.VISIBLE
            login.visibility = View.GONE
            checkchange = 0
        }
    }

    private fun loginIn() {
        if (loginNickname.text.isNullOrEmpty() || mailLogin.text.isNullOrEmpty() || passwordLogin.text.isNullOrEmpty() || confirmPassword.text.isNullOrEmpty()) {
            Toast.makeText(this, "Fill in all the fields", Toast.LENGTH_SHORT).show()
        } else if (passwordLogin.text.toString() != confirmPassword.text.toString()) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
        } else {
            val user = Users(mailLogin.text.toString(), loginNickname.text.toString(), passwordLogin.text.toString(), "")
            val disp = App.dm.apisign
                .registration(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ m ->

                }, {
                    if (it.message!!.contains("465")) {
                        Toast.makeText(
                            this,
                            "Пользователь с таким логином уже существует",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(this, "Успешно", Toast.LENGTH_SHORT).show()
                        change()
                    }
                })
        }
    }


    private fun signIn() {

        if (loginSignin.text.toString()
                .isEmpty() || !loginSignin.text.contains("@") || loginSignin.text.toString().length <= 3
        ) {
            Toast.makeText(this, "Incorrect Email", Toast.LENGTH_SHORT).show()
        } else if (passwordSignin.text.toString().isEmpty()) {
            Toast.makeText(this, "Please, enter a password", Toast.LENGTH_SHORT).show()
        } else {

            var user = Users(loginSignin.text.toString(), "", passwordSignin.text.toString(), "")

            val disp = App.dm.apisign
                .autorisation(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ m ->
                    userToken = m.token.toString()
                }, {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }, {
                    if (userToken.isEmpty()) {
                        Toast.makeText(
                            this,
                            "Такого пользователя не существует",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                })

        }
    }


}