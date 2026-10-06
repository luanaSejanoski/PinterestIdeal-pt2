package com.example.myapplication

import android.os.Bundle
import androidx.compose.foundation.layout.Column
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.onFocusChanged
import com.example.myapplication.data.Dados
import com.example.myapplication.data.Pasta

class CriarPasta : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    TelaCriarPasta()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TelaCriarPasta(){

    var nomePasta by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var nomeFocado by remember { mutableStateOf(false) }
    var descricaoFocada by remember { mutableStateOf(false) }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF1e1e1e)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Cabeçalho
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Voltar",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )

                Text(
                    text = "Criar uma Pasta",
                    color = Color.White,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.padding(100.dp))

            Column(
                modifier = Modifier.fillMaxSize()
                .offset(y = 130.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                painter = painterResource(id = R.drawable.pasta),
                contentDescription = "Pasta",
                modifier = Modifier
                    .width(120.dp)
                    .height(120.dp)
            )

                Spacer(modifier = Modifier.height(70.dp))

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                        .padding(horizontal = 20.dp),
                    color = Color.Transparent,
                    shape = RoundedCornerShape(5.dp),
                    border = BorderStroke(1.dp,
                        if(nomeFocado) Color.Red else Color.White)
                ) {
                    TextField(
                        value = nomePasta,
                        onValueChange = { nomePasta = it },
                        modifier = Modifier.onFocusChanged {
                            nomeFocado = it.isFocused
                        },
                        textStyle = LocalTextStyle.current.copy(
                            color = Color.White
                        ),
                        placeholder = {
                            Text(
                                text = "Nome da pasta",
                                color = Color.White
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent
                        )
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .padding(horizontal = 20.dp),
                    color = Color.Transparent,
                    shape = RoundedCornerShape(5.dp),
                    border = BorderStroke(1.dp,
                        if(descricaoFocada) Color.Red else Color.White)
                ) {
                        TextField(
                            value = descricao,
                            onValueChange = { descricao = it },
                            modifier = Modifier.onFocusChanged {
                                descricaoFocada = it.isFocused
                            },
                            textStyle = LocalTextStyle.current.copy(
                                color = Color.White
                            ),
                            placeholder = {
                                Text(
                                    text = "Descrição (opcional)",
                                    color = Color.White
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent
                            )
                        )
                }
            }

            val novaPasta = Pasta(0, nomePasta, descricao)

            BtnCriarPasta(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = {
                    Dados.pastas.add(novaPasta)
                    Dados.pastas.forEach { println(novaPasta) }

                }
            )
        }
    }
}

@Composable
fun BtnCriarPasta(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 30.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red
        ),
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(
            text = "Criar pasta",
            color = Color.White
        )
    }
}