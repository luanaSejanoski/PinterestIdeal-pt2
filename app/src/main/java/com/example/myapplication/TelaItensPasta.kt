package com.example.myapplication

import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateListOf
import coil.compose.rememberAsyncImagePainter



class TelaItensPasta : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    telaItensPasta()
                }
            }
        }
    }
}

data class Pin(
    val imagem: Uri,
    val largura: Int,
    val altura: Int
)

@Composable
fun gerarBlocoIcone(cor: Color? = null,
                    x: Int,
                    y: Int,
                    raio: Int? = 0,
                    imagem: Int? = null,
                    icon: Painter? = null,
                    texto: String? = null,
                    corTexto: Color = Color.White,
                    onClick: () -> Unit = {},
){
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.clickable {
            onClick()
        }) {
        gerarBloco(cor, x, y, raio, imagem, icon)
        if(!texto.isNullOrEmpty()){
            Text(text = texto, color = corTexto)
        }
    }
}


@Composable
fun gerarBlocoImagem(
    imagem: Uri,
    proporcao: Float,
){
    Box(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
    ){
        Image(
           painter = rememberAsyncImagePainter(imagem),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(proporcao),
            contentScale = ContentScale.Crop
        )
    }
}

//
//@Composable
//fun obterTamanhosImagens( listaImagens: MutableList<Pin>) {
//    val context = LocalContext.current;
//
//    val uris = mutableListOf<Uri>()
//
//    listaImagens.forEach { imagem ->
//        uris.add(imagem.imagem)
//    }
//
//    //adiciona imagens na lista
//    uris.forEach { uri ->
//        //transforma o endereço da imagem(uri) e transforma em um drawable
//        val drawable = context.contentResolver
//            .openInputStream(uri)
//            ?.use {
//                Drawable.createFromStream(it, null)
//            }
//        val largura = drawable?.intrinsicWidth?: 0
//        val altura = drawable?.intrinsicHeight?: 0
//
//        val pin = Pin(uri,largura,altura)
//        listaImagens.add(pin)
//    }
//}


fun organizaImagensColuna(
    listaPins: List<Pin>,
    colunaEsquerda: MutableList<Pin>,
    colunaDireita: MutableList<Pin>
){
    var alturaEsquerda = 0f
    var alturaDireita = 0f


    listaPins.forEach { imagem ->
        // Decidi em qual coluna colocar (menor)
        val alturaRelativa= imagem.altura.toFloat() / imagem.largura.toFloat() //quao alta a imagem é

        if(alturaEsquerda <= alturaDireita){
            colunaEsquerda.add(imagem)
            alturaEsquerda += alturaRelativa
        }else{
            colunaDireita.add(imagem)
            alturaDireita += alturaRelativa
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun telaItensPasta() {
        var nomePasta by remember { mutableStateOf("Nome_Pasta") }
        var mostrarDialog by remember { mutableStateOf(false) }
        var novoNome by remember { mutableStateOf("") }


    val context = LocalContext.current

    val listaImagens = remember { mutableStateListOf<Pin>() }

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

            listaImagens.add(
                Pin(
                    imagem = uri,
                    largura = largura,
                    altura = altura
                )
            )
        }
    }

    Surface(modifier = Modifier
        .fillMaxSize(),
        color = Color(0xFF1e1e1e),) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 20.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column( modifier = Modifier.fillMaxWidth()) {
                //topBar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                   BotaoVoltar()
                }
                //informações da pasta
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 20.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row() {
                        Column() {
                            Row() {
                               Text(text = "$nomePasta", color = Color.White, fontSize = 20.sp)
                                gerarBlocoIcone(
                                    Color(0xFF1e1e1e),
                                    30,
                                    5,
                                    icon = painterResource(R.drawable.editar),
                                    onClick = {
                                        novoNome = nomePasta
                                        mostrarDialog = true
                                    }
                                )
                            }
                            if (mostrarDialog) {
                                AlertDialog(
                                    onDismissRequest = {
                                        mostrarDialog = false
                                    },

                                    title = {
                                        Text("Renomear pasta")
                                    },

                                    text = {
                                        TextField(
                                            value = novoNome,
                                            onValueChange = {
                                                novoNome = it
                                            },
                                            singleLine = true
                                        )
                                    },

                                    confirmButton = {
                                        TextButton(
                                            onClick = {
                                                nomePasta = novoNome
                                                mostrarDialog = false
                                            }
                                        ) {
                                            Text("Salvar")
                                        }
                                    },

                                    dismissButton = {
                                        TextButton(
                                            onClick = {
                                                mostrarDialog = false
                                            }
                                        ) {
                                            Text("Cancelar")
                                        }
                                    }
                                )
                            }
                            Row() {
                                Text(text = "${listaImagens.size} Pins", color = Color.White)
                            }
                        }
                    }

                    Row() {
                        gerarBlocoIcone(
                            Color(0xFF1e1e1e),
                            30,
                            5,
                            icon = painterResource(R.drawable.carregar_imagem),
                            onClick = {selecionarImagens.launch("image/*")}
                        )
                        gerarBlocoIcone(
                            Color(0xFF1e1e1e),
                            30,
                            5,
                            icon = painterResource(R.drawable.excluir)
                        )
                    }
                }



                //cria lista das imagens

//                obterTamanhosImagens(listaImagens)


                val colunaEsquerda = mutableListOf<Pin>()
                val colunaDireita = mutableListOf<Pin>()
                organizaImagensColuna(listaImagens, colunaEsquerda, colunaDireita)


                //área das imagens
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(3.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                    )
                {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        colunaEsquerda.forEach { item ->
                            // Define a proporção da imagem para que ela mantenha suas medidas originais na tela
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
