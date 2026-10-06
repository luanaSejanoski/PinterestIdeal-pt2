package com.example.myapplication
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
//import androidx.compose.material.icons.filled.ChevronLeft
//import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.painter.Painter


import com.example.myapplication.data.Dados


class EditarPerfil : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    TelaEditarPerfil()
                }
            }
        }
    }
}


@Composable
fun gerarBloco(cor:Color?, x: Int, y: Int, z:Int? = null, imagem: Int? = null, icon: Painter? = null){
    Surface(
        modifier = Modifier
            .requiredSize(x.dp)
            .padding(y.dp),
        color = cor?: Color.Transparent,
        shape = RoundedCornerShape((z?:0).dp)
    ) {
        if(imagem != null){
            Image(
                painter = painterResource(id = imagem),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }


        if(icon != null){
            Icon(
                painter = icon,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
fun gerarConteudoBloco(cor: Color? = null,
                       x: Int,
                       y: Int,
                       raio: Int? = 0,
                       imagem: Int? = null,
                       icon: Painter? = null,
                       texto: String? = null,
                       corTexto: Color = Color.White
){
    Box(contentAlignment = Alignment.Center) {
        gerarBloco(cor, x, y, raio, imagem, icon)
        if(!texto.isNullOrEmpty()){
            Text(text = texto, color = corTexto)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoEditar(nomeCampo: String,
                valor: String?,
                onValueChange: (String) -> Unit){
    var focado by remember { mutableStateOf(false) }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = if (focado) Color.White else Color.Gray,
                shape = RoundedCornerShape(8.dp)
            )
            .onFocusChanged { campoFocado -> focado = campoFocado.isFocused },
        value = valor?: "",
        onValueChange = {novoNome -> onValueChange(novoNome)},
        label = { Text(text = nomeCampo)},
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White,
            textColor = Color.White,
            placeholderColor = Color.Gray
        )
    )
}

fun salvarAlteracoes(usuario: Usuario, nomeExibicao: String,
                     nomeUsuario: String, biografia: String,
                     email: String
){
   val usuarioAtualizado = usuario.copy(
       nomeExibicao = nomeExibicao,
       nomeUsuario = nomeUsuario,
       biografia = biografia,
       email = email
   )

    val indice = Dados.usuarios.indexOfFirst {
       it.id == usuario.id
    }

    if(indice != -1){
        Dados.usuarios[indice] = usuarioAtualizado
    }
}


@Composable
fun geraBotao(x: Float, texto: String, habilitado: Boolean, onclick: () -> Unit, ){
    Button(
        modifier = Modifier.fillMaxWidth(x),
        contentPadding = PaddingValues(horizontal = 3.dp, vertical = 4.dp),
        onClick = onclick,
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


@Preview(showBackground = true)
@Composable
fun TelaEditarPerfil() {
    Dados.usuarios.add(
        Usuario(
            id = 0,
            nomeExibicao = "Maria Silva",
            nomeUsuario = "maria001",
            biografia = "Amo fotografia 📷",
            email = "maria@email.com",
            foto = null
        )
    )

    val usuario = Dados.usuarios.find { it.id == 0 }

    if (usuario == null) {
        return
    }

        var nomeExibicao by remember { mutableStateOf(usuario.nomeExibicao) }
        var nomeUsuario by remember { mutableStateOf(usuario.nomeUsuario) }
        var biografia by remember { mutableStateOf(usuario.biografia) }
        var email by remember { mutableStateOf(usuario.email) }
        var fotoPerfil by remember { mutableStateOf(usuario.foto) }


    Surface(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),
        color = Color(0xFF1e1e1e),) {
        Row( modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 20.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    gerarConteudoBloco(
                        Color(0xFF1e1e1e),
                        45,
                        5,
                        icon = painterResource(R.drawable.voltar)
                    )
                    Text(text = "Editar perfil", color = Color.White, textAlign = TextAlign.Center)

                    val habilitado =
                        nomeExibicao != usuario.nomeExibicao ||
                                nomeUsuario != usuario.nomeUsuario ||
                                biografia != usuario.biografia ||
                                email != usuario.email

                        geraBotao(0.25f, "Feito",
                            habilitado,
                            onclick = {
                                salvarAlteracoes(
                                    usuario, nomeExibicao, nomeUsuario,
                                    biografia, email
                                )
                            }
                        )
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 30.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        gerarConteudoBloco(
                            x = 150,
                            y = 5,
                            cor = Color.Red,
                            raio = 75,
                            imagem = R.drawable.foto_perfil_editar
                        )
                        geraBotao(0.2f, "Editar", habilitado = false) {}
                    }
                }


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(30.dp)
                    ) {
                        CampoEditar("Nome",
                            nomeExibicao,
                            onValueChange = { novoValor ->
                            nomeExibicao = novoValor
                        })
                        CampoEditar("Nome de usuário",
                            nomeUsuario,
                            onValueChange = {novoValor -> nomeUsuario = novoValor})
                        CampoEditar("Biografia",
                            biografia,
                            onValueChange = {novoValor -> biografia = novoValor})
                        CampoEditar("Email",
                            email,
                            onValueChange = {novoValor -> email = novoValor})
                    }
                }
            }
        }
    }
}





































































































































