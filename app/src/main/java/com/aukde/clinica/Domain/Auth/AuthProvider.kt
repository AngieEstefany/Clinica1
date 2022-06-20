package com.aukde.clinica.Domain.Auth

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.aukde.clinica.Domain.Network
import com.aukde.clinica.UI.Credentials.LoginActivity
import com.aukde.clinica.UI.Credentials.RegisterActivity
import com.aukde.clinica.Utils.Constants
import com.aukde.clinica.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.QueryDocumentSnapshot

class  AuthProvider {


    fun login(activity : LoginActivity , dni : String ,password : String,bindingProgressBar: ProgressBar){
        lateinit var binding : ActivityRegisterBinding
        lateinit var firebaseAuth: FirebaseAuth

           firebaseAuth = FirebaseAuth.getInstance()

        var count = 0

        bindingProgressBar.visibility = View.VISIBLE

        Network().firestore().collection(Constants.USERS).whereEqualTo("dni",dni).get().addOnSuccessListener { document ->

            if(document != null){
                for (Query : QueryDocumentSnapshot in document){
                    if (Query.exists()){
                        count++
                        val typeUser = Query.data["type_user"].toString()
                        val email = Query.data["email"].toString()
                        if(typeUser == Constants.PATIENT){
                            Toast.makeText(activity, "Usted es paciente!",Toast.LENGTH_SHORT).show()

                            //UNA VEZ VALIDADO SI ES USUARIO ES PACIENTE , REALIZAR LA AUTENTICACATION
                            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                                if(it.isSuccessful){
                                    Toast.makeText(activity,"Logueado",Toast.LENGTH_SHORT).show()
                                    activity.startActivity(Intent(activity,LoginActivity::class.java))
                                    bindingProgressBar.visibility = View.GONE
                                    val intent = Intent(activity, RegisterActivity::class.java)
                                }
                                else{
                                    Toast.makeText(activity, it.exception.toString(), Toast.LENGTH_SHORT).show()
                                    bindingProgressBar.visibility = View.GONE

                                }
                            }

                        }else{
                            Toast.makeText(activity, "Usuario no permitido!",Toast.LENGTH_SHORT).show()
                            bindingProgressBar.visibility = View.GONE
                        }
                    }else{
                        Toast.makeText(activity, "Error!",Toast.LENGTH_SHORT).show()
                        bindingProgressBar.visibility = View.GONE
                    }
                }
                if (count == 0){
                    Toast.makeText(activity, "No existe este usuario!",Toast.LENGTH_SHORT).show()
                    bindingProgressBar.visibility = View.GONE
                }
            }
        }.addOnCanceledListener {
            Toast.makeText(activity, "Error!",Toast.LENGTH_SHORT).show()
            bindingProgressBar.visibility = View.GONE
        }.addOnFailureListener {
            Toast.makeText(activity, "Error!",Toast.LENGTH_SHORT).show()
            bindingProgressBar.visibility = View.GONE
        }
    }


    fun getCurrentUserID(): String {
        // An Instance of currentUser using FirebaseAuth
        val currentUser = FirebaseAuth.getInstance().currentUser

        // A variable to assign the currentUserId if it is not null or else it will be blank.
        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid

        }
        return currentUserID
    }

    fun logout(loginActivity: Activity){

        var logout: FirebaseAuth = FirebaseAuth.getInstance()
        logout.signOut()
        loginActivity.startActivity(Intent(loginActivity,LoginActivity::class.java))
        loginActivity.finish()

    }


}








