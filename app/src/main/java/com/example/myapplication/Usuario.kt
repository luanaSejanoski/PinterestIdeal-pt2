package com.example.myapplication

import android.net.Uri

data class Usuario(
    val id: Int,
    val nomeExibicao: String,
    val nomeUsuario: String,
    val biografia: String,
    val email: String,
    val foto: Uri?
)