package com.aukde.clinica.UI.Credentials

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import com.aukde.clinica.Domain.Auth.AuthProvider
import com.aukde.clinica.Models.Paciente
import com.aukde.clinica.R
import com.aukde.clinica.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.util.HashMap
import java.util.jar.Attributes


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var db: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var name = binding.editTextNombres
        var email = binding.editTextCorreo
        var dni= binding.editTextDni
        var password= binding.editTextContraseAReg
        var last_name= binding.editTextApellidos
        var phone = binding.editTextCelular


        var user = Paciente("",dni.text.toString(),name.text.toString(), last_name.text.toString(),email.text.toString(), phone.text.toString(),"Paciente")


         binding.buttonRegistro.setOnClickListener{

             if(email.text.toString() != ""){
                 register(email.text.toString(),password.text.toString(),user)
             }
             else{

                 Toast.makeText(this,"complete los campos",Toast.LENGTH_SHORT).show()
             }
         }

    }
 private   fun register(email: String, password: String, user: Paciente) {

        //VALIDATE INPUTS AUTENTICATION
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val id = mAuth.currentUser!!.uid
                user.id=id
                updateUI(id, user)
                Toast.makeText(this, "REGISTRADO", Toast.LENGTH_SHORT).show()
                var logout: FirebaseAuth = FirebaseAuth.getInstance()
                logout.signOut()
            } else {
                Toast.makeText(this, "NO REGISTRADO", Toast.LENGTH_SHORT).show()
            }


        }
    }

    fun updateUI(id: String, user: Paciente) {

        //CREATE DATA CLASS USER
        db.collection("users").document(id).set(user,
            SetOptions.merge()).addOnCompleteListener { task ->

            if (task.isSuccessful) {
                Toast.makeText(this, "registrado", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"No se pudo regsitrar",Toast.LENGTH_SHORT).show()
            }
        }
    }

}
