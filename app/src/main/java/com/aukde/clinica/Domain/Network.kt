package com.aukde.clinica.Domain

import com.google.firebase.firestore.FirebaseFirestore

class Network {

    fun firestore() : FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }


}