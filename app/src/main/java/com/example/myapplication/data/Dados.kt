
package com.example.myapplication.data

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import com.example.myapplication.Pin
import com.example.myapplication.R
import com.example.myapplication.Usuario

object Dados {
    //var pastas = mutableListOf<Pasta>()
    //var usuarios = mutableListOf<Usuario>()
    val usuarios = mutableStateListOf(
        Usuario(0, "Maria Silva", "maria001", "Amo fotografia 📷", "maria@email.com", null, mutableListOf(1)),
        Usuario(1, "João Souza", "joao002", "", "joao@email.com", null, mutableListOf(0)),
        Usuario(2, "Juca", "Juquinha2", "", "juquinha@email.com", null, mutableListOf(0))

    )

    val pastas = mutableStateListOf(
        Pasta(0, "Paisagens", "Lugares esbeltos!", mutableListOf(2,3,4)),
        Pasta(1, "Looks", "Looks bafônicos!", mutableListOf(0,1))
    )

    val pins = mutableStateListOf(

        Pin(
            0,
            Uri.parse("android.resource://com.example.myapplication/${R.drawable.outfit1}"),
            600,
            468
        ),

        Pin(
            1,
            Uri.parse("android.resource://com.example.myapplication/${R.drawable.outfit2}"),
            700,
            1080
        ),
        Pin(
            2,
            Uri.parse("android.resource://com.example.myapplication/${R.drawable.summer1}"),
            598,
            280
        ),

        Pin(
            3,
            Uri.parse("android.resource://com.example.myapplication/${R.drawable.summer2}"),
            900,
            560
        ),

        Pin(
            4,
            Uri.parse("android.resource://com.example.myapplication/${R.drawable.summer3}"),
            1080,
            720
    )
    )

    fun proximoIdPasta():Int{
        return if(pins.isEmpty()){
            0
        }
        else{
            pastas.maxOf { it.id } + 1
        }
    }


    fun proximoIdPin(): Int{
       return if(pins.isEmpty()){
            0
        }
        else{
            pins.maxOf { it.id } + 1
       }
    }
}

