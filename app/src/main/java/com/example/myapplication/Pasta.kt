package com.example.myapplication.data

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import coil.disk.DiskCache
import com.example.myapplication.Pin

data class Pasta(
    val id:Int,
    var privado: Boolean,
    var nome: String,
    var descricao: String,
    var pins: SnapshotStateList<Int> = mutableStateListOf()
   )