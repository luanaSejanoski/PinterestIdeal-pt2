package com.example.myapplication.data

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import com.example.myapplication.Pin

data class Pasta(
    val id:Int,
    var nome: String,
    var descricao: String,
    var pins: MutableList<Int> = mutableStateListOf()
   )