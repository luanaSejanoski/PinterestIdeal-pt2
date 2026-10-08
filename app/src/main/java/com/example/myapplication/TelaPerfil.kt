package com.example.myapplication


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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.data.Dados
import androidx.navigation.NavHostController



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
fun gararPasta(nomePasta: String, numeroPins: Int, onclick: () -> Unit){
    Column(
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .background(Color.Transparent)
                .size(height = 100.dp, width = 130.dp)
                .clickable{onclick()},
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


@Composable
fun TelaPerfil(navController: NavHostController, idUsuario: Int = 0) {
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
                            navController.navigate(Rotas.EDITARPERFIL)
//                            val intent = Intent(context, EditarPerfil::class.java)
//                                .putExtra("ID_USUARIO", idUsuario)
//                                context.startActivity(intent)
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
                    
                    Dados.pastas.forEach {
                        pasta ->
                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                gararPasta(pasta.nome, pasta.pins.size,
                                    onclick = {

                                        navController.navigate(
                                          Rotas.DETALHES_PASTA.replace("{idPasta}", pasta.id.toString())
                                        )
//                                        val intent = Intent(context, TelaItensPasta::class.java)
//                                            .putExtra("ID_PASTA", pasta.id)
//                                        context.startActivity(intent)
                                    })
                            }
                        }
                    }
                }
            }
        }
    }
}