package com.example.myapplication

import android.R.attr.icon
import android.os.Bundle
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
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.foundation.Image
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource


class TelaPerfil : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MinhaTela()
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
fun gerarBotao(x: Float, texto: String){
    var habilitado by remember { mutableStateOf(false) }
    Button(
        modifier = Modifier.fillMaxWidth(x),
        contentPadding = PaddingValues(horizontal = 3.dp, vertical = 3.dp),
        onClick = {},
        enabled = habilitado,
        shape = RoundedCornerShape(13.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            disabledContentColor = Color.White,
            disabledContainerColor = (Color(0xFF757575))
        )){
        Text(text = texto)
    }
}

@Preview
@Composable
fun MinhaTela(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF1e1e1e)) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            //icones em cima
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                //icone voltar
                Box(
                    modifier = Modifier
                        .size(width = 50.dp, height = 50.dp)
                ){
                    GeraBloco(Color(0xFF1e1e1e), 50, icon = Icons.Default.KeyboardArrowLeft)
                }
                //icone compartilhar
                Box(
                    modifier = Modifier
                        .size(width = 60.dp, height = 60.dp).padding(vertical = 10.dp)
                ){
                    GeraBloco(Color(0xFF1e1e1e), 40, icon = Icons.Default.Share)
                }
            }
            //usuario
            Row(
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
//                    .background(Color(0xFF757575))
                        .height(60.dp)
                ){
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.foto_perfil_editar),
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
                        Text(text = "PinterestIdeal", color = Color.White, fontSize = 20.sp)
                        Text(text = "pinterest_ideal", color = Color.White, fontSize = 15.sp)
                    }
                }
            }
            //seguidores e bio
            Row(
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp, vertical = 5.dp)
                        .height(70.dp)
                ){
                    Column(
                        modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ){
                        Text(text = "20 seguidores ⦁ 34 seguindo",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "Sempre feliz salvando pins! <3",
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
            ){
                Surface(
                    modifier = Modifier.fillMaxSize().padding(start = 30.dp),
                    shape = RoundedCornerShape(15.dp),
                ){
                    gerarBotao(0.2f, "Editar perfil");
                }
            }
            //botoes criar e salvar
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                //criar
                Column(
                    modifier = Modifier
                        .size(height = 40.dp, width = 90.dp)
                        .padding(top = 10.dp, start = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "Criados",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                //salvos
                Column(
                    modifier = Modifier
                        .size(height = 40.dp, width = 90.dp)
                        .padding(top = 10.dp, end = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "Salvos",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier
                            .size(height = 2.dp, width = 100.dp)
                            .background(Color.White)
                    ){}
                }
            }
            //pins salvos
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceAround,
            ){
                //coluna esquerda
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    //primeira pasta MAIOR FOTO
                    Box(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .background(Color(0xFFd9d9d9))
                            .size(height = 100.dp, width = 130.dp)
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.summer1),
                            contentDescription = "summer 1",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(height = 100.dp, width = 65.dp)
                        )
                        //menor foto
                        Column(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .size(height = 100.dp, width = 65.dp)
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.summer2),
                                contentDescription = "summer 2",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(height = 50.dp, width = 65.dp)
                            )
                            //e outra foto
                            Image(
                                painter = painterResource(id = R.drawable.summer3),
                                contentDescription = "summer 3",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(height = 50.dp, width = 65.dp)
                            )
                        }
                    }
                    //texto embaixo da pasta
                    Column(
                        modifier = Modifier
                            .width(130.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(3.dp)
                    ){
                        Text(text = "Summer aesthetic",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                        Text(text = "45 Pins",
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }

                    //terceira pasta MAIOR FOTO (na horizontal)
                    Box(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .background(Color(0xFFd9d9d9))
                            .size(height = 100.dp, width = 130.dp)
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.outfit1),
                            contentDescription = "outfit 1",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(height = 100.dp, width = 65.dp)
                        )
                        //menor foto
                        Column(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .size(height = 100.dp, width = 65.dp)
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.outfit2),
                                contentDescription = "outfit 2",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(height = 50.dp, width = 65.dp)
                            )
                            //e outra foto
                            Image(
                                painter = painterResource(id = R.drawable.outfit3),
                                contentDescription = "outfit 3",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(height = 50.dp, width = 65.dp)
                            )
                        }
                    }
                    //texto embaixo da pasta
                    Column(
                        modifier = Modifier
                            .width(130.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(3.dp)
                    ){
                        Text(text = "Outfit inspo",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                        Text(text = "34 Pins",
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }
                //coluna da direita
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    //segunda pasta MAIOR FOTO (na horizontal)
                    Box(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .background(Color(0xFFd9d9d9))
                            .size(height = 100.dp, width = 130.dp)
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.receitas1),
                            contentDescription = "receitas 1",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(height = 100.dp, width = 130.dp)
                        )
                        //menor foto
                        Column(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .size(height = 100.dp, width = 65.dp)
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.receitas2),
                                contentDescription = "receitas 2",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(height = 50.dp, width = 65.dp)
                            )
                            //e outra foto
                            Image(
                                painter = painterResource(id = R.drawable.receitas3),
                                contentDescription = "receitas 3",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(height = 50.dp, width = 65.dp)
                            )
                        }
                    }
                    //texto embaixo da pasta
                    Column(
                        modifier = Modifier
                            .width(130.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(3.dp)
                    ){
                        Text(text = "Receitas",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                        Text(text = "28 Pins",
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                    //quarta pasta MAIOR FOTO (na horizontal)
                    Box(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .background(Color(0xFFd9d9d9))
                            .size(height = 100.dp, width = 130.dp)
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.desenhos1),
                            contentDescription = "desenhos 1",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(height = 100.dp, width = 65.dp)
                        )
                        //menor foto
                        Column(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .size(height = 100.dp, width = 65.dp)
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.desenhos2),
                                contentDescription = "desenhos 2",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(height = 50.dp, width = 65.dp)
                            )
                            //e outra foto
                            Image(
                                painter = painterResource(id = R.drawable.desenhos3),
                                contentDescription = "desenhos 3",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(height = 50.dp, width = 65.dp)
                            )
                        }
                    }
                    //texto embaixo da pasta
                    Column(
                        modifier = Modifier
                            .width(130.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(3.dp)
                    ){
                        Text(text = "Dicas de desenho",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                        Text(text = "108 Pins",
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }
            }
            //barra de opcoes no final
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ){
                Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color(0xFF757575))
                    .padding(vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ){
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