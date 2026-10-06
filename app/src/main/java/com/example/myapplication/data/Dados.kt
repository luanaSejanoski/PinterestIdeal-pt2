
package com.example.myapplication.data

import androidx.compose.runtime.mutableStateListOf
import com.example.myapplication.Usuario

object Dados {
    var pastas = mutableListOf<Pasta>()
    //var usuarios = mutableListOf<Usuario>()
    val usuarios = mutableStateListOf(
        Usuario(0, "Maria Silva", "maria001", "Amo fotografia 📷", "maria@email.com", null, mutableListOf(1)),
        Usuario(1, "João Souza", "joao002", "", "joao@email.com", null, mutableListOf(0))
    )
}

