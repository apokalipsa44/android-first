package com.michau.firebase_chat.model

data class User(val name: String,
                val bio: String,
                val profilePicturePatch: String?,
                val registrationTokens: MutableList<String>){
    constructor():this("", "", null, mutableListOf())//firestore reqiured

}