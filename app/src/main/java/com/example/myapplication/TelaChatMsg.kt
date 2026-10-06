
package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.widthIn
import androidx.compose.ui.text.TextStyle
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.LaunchedEffect





import com.example.myapplication.ui.theme.MyApplicationTheme


class TelaChatMsg : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    TelaMsg()
                }
            }
        }
    }
}

@Preview
@Composable
fun TelaMsg() {

    var mensagem by remember {
        mutableStateOf("")
    }

    var mensagens by remember {
        mutableStateOf(listOf<String>())
    }

    val listaState = rememberLazyListState()

    LaunchedEffect(mensagens.size) {
        if (mensagens.isNotEmpty()) {
            listaState.animateScrollToItem(mensagens.lastIndex)
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF1e1e1e)
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            // cabeçalho
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 10.dp,
                        vertical = 20.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Voltar",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )

                Text(
                    text = "Mensagem",
                    color = Color.White,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = 80.dp,
                        bottom = 100.dp,
                        end = 20.dp
                    ),
                state = listaState,
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {

                items(mensagens.size) { index ->

                    val mensagemEnviada = mensagens[index]

                    Surface(
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                            .widthIn(
                                min = 90.dp,
                                max = 250.dp
                            ),
                        color = Color.Transparent,
                        shape = RoundedCornerShape(18.dp),
                        border = BorderStroke(
                            1.dp,
                            Color.White
                        )
                    ) {
                        Text(
                            text = mensagemEnviada,
                            color = Color.White,
                            modifier = Modifier.padding(
                                horizontal = 15.dp,
                                vertical = 10.dp
                            )
                        )
                    }
                }
            }

            // campo de mensagem
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(
                        horizontal = 5.dp,
                        vertical = 30.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {

                CampoMsg(
                    mensagem = mensagem,
                    onMensagemChange = {
                        mensagem = it
                    },
                    onEnviar = {
                        if (mensagem.isNotBlank()) {
                            mensagens = mensagens + mensagem
                            mensagem = ""
                        }
                    },
                    modifier = Modifier.weight(1f)
                )

                Spacer(
                    modifier = Modifier.width(5.dp)
                )

                // botao enviar
                Button(
                    onClick = {
                        if (mensagem.isNotBlank()) {
                            mensagens = mensagens + mensagem
                            mensagem = ""
                        }
                    },
                    modifier = Modifier
                        .width(40.dp)
                        .height(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    ),
                    shape = RoundedCornerShape(3.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {

                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Enviar",
                        tint = Color.White,
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CampoMsg(
    mensagem: String,
    onMensagemChange: (String) -> Unit,
    onEnviar: () -> Unit,
    modifier: Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color(0xFF2c2c2c),
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(1.dp, Color.White)
    ) {

        BasicTextField(
            value = mensagem,
            onValueChange = onMensagemChange,

            modifier = Modifier
                .fillMaxWidth()
                .onPreviewKeyEvent { evento ->

                    if (
                        evento.key == Key.Enter &&
                        evento.type == KeyEventType.KeyDown
                    ) {
                        onEnviar()
                        true
                    } else {
                        false
                    }
                }
                .padding(
                    horizontal = 12.dp,
                    vertical = 12.dp
                ),

            textStyle = TextStyle(
                color = Color.White
            ),
            singleLine = true,
            decorationBox = { innerTextField ->
                if (mensagem.isEmpty()) {
                    Text(
                        text = "Digite uma mensagem...",
                        color = Color.Gray
                    )
                }
                innerTextField()
            }
        )
    }
}

