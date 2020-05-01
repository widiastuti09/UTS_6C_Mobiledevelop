package com.diyas.ecommerceuts.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.diyas.ecommerceuts.R
import com.diyas.ecommerceuts.network.ApiService
import com.diyas.ecommerceuts.data.ResponseLogin
import com.diyas.ecommerceuts.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar!!.title = "Masuk"
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        btnLogin.setOnClickListener {

            val username = edtUsername.text.toString().trim()
            val password = edtPassword.text.toString().trim()

            if(username.isEmpty()){
                edtUsername.error = "Username required"
                edtUsername.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                edtPassword.error = "Password required"
                edtPassword.requestFocus()
                return@setOnClickListener
            }

            ApiService.instance.Login(username, password).enqueue(object: Callback<ResponseLogin> {
                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
//                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                    if(!response.body()?.status!!){

                        SharedPrefManager.getInstance(applicationContext).saveUser(response.body()?.data!!)

                        val intent = Intent(applicationContext, ProfileActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }else{
                        Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
                    }

                }
            })

        }

    }
    override fun onStart() {
        super.onStart()
        if(SharedPrefManager.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, ProfileActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
