package com.aukde.clinica.UI.Credentials

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.viewmodel.savedstate.R
import com.aukde.clinica.Domain.Auth.AuthProvider
import com.aukde.clinica.UI.Booking.Loading
import com.aukde.clinica.UI.Menu.MenuActivity
import com.aukde.clinica.databinding.ActivityCreateBookingBinding.inflate
import com.aukde.clinica.databinding.ActivityListBookingBinding
import com.aukde.clinica.databinding.ActivityLoginBinding
import com.google.common.cache.LoadingCache
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root);

        binding.btnButtonIngresar.setOnClickListener {

            val dni = binding.editTextUsuario.text.toString()
            val password = binding.editTextContraseA.text.toString()

      if(dni.isNotEmpty() && password.isNotEmpty()){
          AuthProvider().login(this,dni,password,binding.progress)

            }else{
                Toast.makeText(this,"Complete los campos",Toast.LENGTH_SHORT).show()
            }
        }

        binding.txtRegister.setOnClickListener {

            startActivity(Intent(this,RegisterActivity::class.java))

        }

    }
    override fun onStart() {

        super.onStart()

        var user = AuthProvider().getCurrentUserID()

        if( user != "")
        {
            startActivity(Intent(this,MenuActivity::class.java))

               }

    }
}
