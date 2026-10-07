package com.example.myapplication

import android.net.Uri

data class Usuario(
    val id: Int,
    val nomeExibicao: String,
    val nomeUsuario: String,
    val biografia: String,
    val email: String,
    val foto: Uri?,

    val amigos: MutableList<Int>?

)



//    val usuarios = listOf(
//        Usuario(1, "João"),
//        Usuario(2, "Maria"),
//        Usuario(3, "Pedro")
//    )
//
//    var amigos by remember {
//    mutableStateOf(
//    listOf(
//    Usuario(1, "João"),
//    Usuario(3, "Pedro")
//    )
//    )



