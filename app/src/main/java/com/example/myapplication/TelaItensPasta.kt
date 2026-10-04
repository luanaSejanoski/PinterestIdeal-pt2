package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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


data class Pin(
    val imagem: Int,
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
                    corTexto: Color = Color.White
){
    Box(contentAlignment = Alignment.Center) {
        gerarBloco(cor, x, y, raio, imagem, icon)
        if(!texto.isNullOrEmpty()){
            Text(text = texto, color = corTexto)
        }
    }
}

@Composable
fun gerarBlocoImagem(
    imagem: Int,
    proporcao: Float,
){
    Box(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
    ){
        Image(
           painter = painterResource(imagem),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
                .aspectRatio(proporcao),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun obterTamanhosImagens(listaImagens: MutableList<Pin>) {
    val context = LocalContext.current;

    val imagens = listOf(
        R.drawable.summer1,
        R.drawable.summer2,
        R.drawable.summer3,
    )

    //adiciona imagens na lista
    imagens.forEach { imagem ->
        val drawble = context.getDrawable(imagem)

        val largura = drawble?.intrinsicWidth?: 0
        val altura = drawble?.intrinsicHeight?: 0

        val pin = Pin(imagem,largura,altura)
        listaImagens.add(pin)
    }
}

@Composable
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



@Preview
@Composable
fun TelaItensPasta() {
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
            Column() {
                //topBar
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
                                Text(text = "Paisagens", color = Color.White, fontSize = 20.sp)
                                gerarBlocoIcone(
                                    Color(0xFF1e1e1e),
                                    30,
                                    5,
                                    icon = painterResource(R.drawable.editar)
                                )
                            }
                            Row() {
                                Text(text = "3 Pins", color = Color.White)
                            }
                        }
                    }

                    Row() {
                        gerarBlocoIcone(
                            Color(0xFF1e1e1e),
                            30,
                            5,
                            icon = painterResource(R.drawable.carregar_imagem)
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
                val listaImagens = mutableListOf<Pin>()
                obterTamanhosImagens(listaImagens)

                val colunaEsquerda = mutableListOf<Pin>()
                val colunaDireita = mutableListOf<Pin>()
                organizaImagensColuna(listaImagens, colunaEsquerda, colunaDireita)

                //área das imagens
                Row(modifier = Modifier.fillMaxWidth()
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
