package com.example.myapplication

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.data.Dados

@Composable
fun TelaVerPastaUsuario(navController: NavController, idPasta: Int = 0){

    val pasta = Dados.pastas.find { it.id == idPasta } ?: return

    var nomePasta by remember { mutableStateOf(pasta.nome) }
    val context = LocalContext.current

    val selecionarImagens = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) { uris ->
        uris.forEach { uri ->
            val drawable = context.contentResolver
                .openInputStream(uri)
                ?.use {
                    Drawable.createFromStream(it, null)
                }
            val largura = drawable?.intrinsicWidth ?: 0
            val altura = drawable?.intrinsicHeight ?: 0
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF1e1e1e)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BotaoVoltar(
                    navController = navController,
                    onClick = { navController.popBackStack() }
                )

                Text(
                    text = "Pasta",
                    color = Color.White,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                // Informações da pasta
                Column {
                    Row {
                        Text(text = "$nomePasta", color = Color.White, fontSize = 20.sp)
                    }
                    Row {
                        Text(text = "${pasta.pins.size} Pins", color = Color.White)
                    }
                }

                val colunaEsquerda = mutableListOf<Pin>()
                val colunaDireita = mutableListOf<Pin>()
                val pinsDaPasta = Dados.pins.filter { pin -> pin.id in pasta.pins }
                organizaImagensColuna(pinsDaPasta, colunaEsquerda, colunaDireita)

                // Área das imagens
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(3.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        colunaEsquerda.forEach { item ->
                            val proporcao = item.largura.toFloat() / item.altura.toFloat()
                            gerarBlocoImagem(item.imagem, proporcao)
                        }
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        colunaDireita.forEach { item ->
                            val proporcao = item.largura.toFloat() / item.altura.toFloat()
                            gerarBlocoImagem(item.imagem, proporcao)
                        }
                    }
                }
            }
        }
    }
}