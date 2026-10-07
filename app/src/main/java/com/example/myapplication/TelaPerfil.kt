package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.service.autofill.OnClickAction
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.data.Dados


class TelaPerfil : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val idUsuario = intent.getIntExtra("ID_USUARIO", 0)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MinhaTela(idUsuario)
                }
            }
        }
    }
}

@Composable
fun GeraBloco(cor: Color, altura: Int, largura: Int = altura, texto: String = "", icon: ImageVector ?= null){
    Surface(
        modifier = Modifier
            .width(largura.dp)
            .height(altura.dp)
            .padding(5.dp),
        color = cor,
        shape = RoundedCornerShape(5.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ){

            if(icon != null){
                Icon(
                    imageVector = icon,
                    contentDescription = texto,
                    tint = Color.White,
                    modifier = Modifier.size(60.dp)
                )
            }
            else if(texto.isNotEmpty())
                Text(text = texto,
                    color = Color.White
                )
        }
    }
}
@Composable
fun gerarBotao(x: Float,
               texto: String,
               habilitado: Boolean,
               onclick: () -> Unit = {}){
    Button(
        modifier = Modifier.fillMaxWidth(x),
        contentPadding = PaddingValues(horizontal = 3.dp, vertical = 3.dp),
        onClick = onclick,
        enabled = habilitado,
        shape = RoundedCornerShape(13.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White,
            disabledContainerColor = (Color(0xFF757575))
        )){
        Text(text = texto)
    }
}

@Composable
fun gararPasta(nomePasta: String, numeroPins: Int){
    Column(
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .background(Color.Transparent)
                .size(height = 100.dp, width = 130.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.pasta),
                contentDescription = "pasta",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize().padding(4.dp)
            )
        }

            Text(text = "$nomePasta",
                color = Color.White,
                fontSize = 14.sp
            )
            Text(text = "$numeroPins Pins",
                color = Color.White,
                fontSize = 12.sp
            )

    }
}


@Preview(showBackground = true)
@Composable
fun MinhaTela(idUsuario: Int = 0) {
    val context  = LocalContext.current

    val usuario = Dados.usuarios.find { it.id == idUsuario }?: return

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF1e1e1e)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            //usuario
            Row(modifier = Modifier.padding(top = 60.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(60.dp)
                ) {
                    Column {
                        Image(
                            painter = rememberAsyncImagePainter(usuario.foto?: R.drawable.foto_perfil_editar),
                            contentDescription = "Foto de perfil",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                        )
                    }
                    Column(
                        modifier = Modifier.padding(start = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(text = "${usuario.nomeExibicao}", color = Color.White, fontSize = 20.sp)
                        Text(text = "${usuario.nomeUsuario}", color = Color.White, fontSize = 15.sp)
                    }
                }
            }
            //amigos e bio
            Row(
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 5.dp)
                        .height(70.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = "${usuario.amigos?.size} amigos",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${usuario.biografia}",
                            color = Color.White,
                            fontSize = 15.sp
                        )
                    }

                }
            }
            //editar perfil
            Row(
                modifier = Modifier
                    .padding(vertical = 10.dp).size(height = 40.dp, width = 130.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize().padding(start = 30.dp),
                    shape = RoundedCornerShape(15.dp),
                ) {
                    gerarBotao(0.2f, "Editar perfil", habilitado = true,
                        onclick = {
                            val intent = Intent(context, EditarPerfil::class.java)
                                .putExtra("ID_USUARIO", idUsuario)
                                context.startActivity(intent)
                        });
                }
            }
            //área de pastas
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                //pastas
                Column(
                    modifier = Modifier
                        .size(height = 40.dp, width = 90.dp)
                        .padding(top = 10.dp, end = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Pastas",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier
                            .size(height = 2.dp, width = 100.dp)
                            .background(Color.White)
                    ) {}
                }
            }
            //pastas criadas
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2)
                ) {

                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            gararPasta("Paisagens", 3)
                        }
                    }

                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            gararPasta("Rock", 2)
                        }
                    }

                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            gararPasta("Wallpapers", 10)
                        }
                    }

                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            gararPasta("Patinhos", 15)
                        }
                    }

                }
                //barra de opcoes no final
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(Color(0xFF757575))
                            .padding(vertical = 5.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Inicio",
                            tint = Color.White,
                            modifier = Modifier.size(35.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Pesquisar",
                            tint = Color.White,
                            modifier = Modifier.size(35.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Adicionar",
                            tint = Color.White,
                            modifier = Modifier.size(35.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Mensagem",
                            tint = Color.White,
                            modifier = Modifier.size(35.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Perfil",
                            tint = Color.White,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                }
            }
        }
    }
}