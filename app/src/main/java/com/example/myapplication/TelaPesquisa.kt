package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class TelaPesquisa : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    TelaPesquisar()
                }
            }
        }
    }
}

@Preview
@Composable
fun TelaPesquisar() {

        val usuarios = listOf(
            Usuario(
                id = 1,
                nomeExibicao = "Luana",
                nomeUsuario = "luanabanana",
                biografia = "",
                email = "",
                foto = null
            ),
            Usuario(
                id = 2,
                nomeExibicao = "Leticia",
                nomeUsuario = "let_07",
                biografia = "",
                email = "",
                foto = null
            ),
            Usuario(
                id = 3,
                nomeExibicao = "Maria",
                nomeUsuario = "mariaria_franca",
                biografia = "",
                email = "",
                foto = null
            )
        )

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFF1e1e1e)
        ) {

            Box(
                modifier = Modifier.fillMaxSize()
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 50.dp)
                ) {

                    Spacer(modifier = Modifier.height(40.dp))

                    BarraPesquisa(
                        usuarios = usuarios,
                        onAdicionarAmigo = { usuario ->
                            println("Adicionando ${usuario.nomeExibicao}")
                        }
                    )
                }


            }
        }
    }

    @Composable
    fun criaBotao(x: Float,
                  texto: String,
                  onClick: () -> Unit) {
        var habilitado by remember { mutableStateOf(false) }
        Button(
            modifier = Modifier.fillMaxWidth(x),
            contentPadding = PaddingValues(horizontal = 3.dp, vertical = 3.dp),
            onClick = {

            },
            enabled = habilitado,
            shape = RoundedCornerShape(13.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                disabledContentColor = Color.LightGray,
                disabledContainerColor = Color.DarkGray
            )
        ) {
            Text(text = texto)
        }
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraPesquisa(
    usuarios: List<Usuario>,
    onAdicionarAmigo: (Usuario) -> Unit
) {
    var pesquisarP by remember { mutableStateOf("") }

    val usuariosFiltrados = usuarios.filter {
        it.nomeExibicao.contains(pesquisarP, ignoreCase = true) ||
                it.nomeUsuario.contains(pesquisarP, ignoreCase = true)
    }

    Column {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(56.dp),
            color = Color(0xFF2c2c2c),
            shape = RoundedCornerShape(5.dp),
            border = BorderStroke(2.dp, Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "buscar",
                    tint = Color.White
                )

                Spacer(modifier = Modifier.width(5.dp))

                TextField(
                    value = pesquisarP,
                    onValueChange = { pesquisarP = it },
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                    textStyle = LocalTextStyle.current.copy(
                        color = Color.White
                    ),
                    placeholder = {
                        Text(
                            text = "Pesquisar pessoas",
                            color = Color.Gray
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent
                    )
                )
            }
        }

        // Usuários encontrados
        usuariosFiltrados.forEach { usuario ->

            Usuarios(
                nome = usuario.nomeExibicao,
                usuario = usuario.nomeUsuario,
                imagem = R.drawable.user
            )
        }
    }
}

    @Composable
    fun Usuarios(nome: String, usuario: String, imagem: Int? = null) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {

            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Avatar (circulo)
                Surface(
                    modifier = Modifier.size(40.dp),
                    color = Color.White,
                    shape = CircleShape
                ) {


                    if (imagem != null) {
                        Image(
                            painter = painterResource(id = imagem),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text(text = nome, color = Color.White)
                    Text(text = "@$usuario", color = Color.White)
                }

            }
            criaBotao(
                0.25f,
                "Adicionar",
                onClick = {
                    // adicionar amigo
                }
            )
        }
    }

