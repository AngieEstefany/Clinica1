package com.aukde.clinica.UI.Menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.aukde.clinica.Domain.Auth.AuthProvider
import com.aukde.clinica.R
import com.aukde.clinica.UI.Credentials.LoginActivity
import com.aukde.clinica.databinding.ActivityLoginBinding
import com.aukde.clinica.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCerrarSesion.setOnClickListener()
        {

            AuthProvider().logout(this)



        }




    }


}