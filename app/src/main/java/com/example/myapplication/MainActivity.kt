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
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    TelaMensagem()
                }
            }
        }
    }
}


@Composable
fun GerarBloco(cor: Color, altura: Int, largura: Int = altura, texto: String = "", icon: ImageVector ?= null){
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
@Preview
@Composable
fun TelaMensagem() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF1e1e1e)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 20.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                GerarBloco(Color(0xFF1e1e1e), 50, icon = Icons.Default.KeyboardArrowLeft)

                Text(
                    text = "Nova Mensagem",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 14.dp, start = 70.dp),
                    color = Color.White)

                criaBotao(0.7f, "Avançar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            BarraPesquisa()

            Spacer(modifier = Modifier.height(16.dp))

            Usuarios("Luana", "luanabanana", imagem = R.drawable.user)
            Usuarios("Leticia", "let_07", imagem = R.drawable.user)
            Usuarios("Maria", "mariaria_franca", imagem = R.drawable.user)
        }
    }
}

@Composable
fun criaBotao(x: Float, texto: String){
    var habilitado by remember { mutableStateOf(false) }
    Button(
        modifier = Modifier.fillMaxWidth(x),
        contentPadding = PaddingValues(horizontal = 3.dp, vertical = 3.dp),
        onClick = {},
        enabled = habilitado,
        shape = RoundedCornerShape(13.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            disabledContentColor = Color.LightGray,
            disabledContainerColor = Color.DarkGray
        )){
        Text(text = texto)
    }
}
@Composable
fun BarraPesquisa(){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        color = Color(0xFF2c2c2c),
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(2.dp, Color.White)
    ){
        Row(
            modifier = Modifier.padding(horizontal = 1.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "buscar",
                tint = Color.White
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                text = "Pesquisar pessoas",
                color = Color.Gray,
            )
        }
    }
}

@Composable
fun Usuarios(nome: String, usuario: String, imagem: Int ?= null){
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
            ){


                if(imagem != null){
                    Image(
                        painter = painterResource(id = imagem),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column{
                Text(text = nome, color = Color.White)
                Text(text = "@$usuario", color = Color.White)
            }

        }
        // Botão Adicionar
        criaBotao(0.25f, "Adicionar")

    }
}
