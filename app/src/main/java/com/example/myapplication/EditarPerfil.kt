package com.example.myapplication
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
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
import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ChevronLeft
//import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.ui.graphics.vector.ImageVector


import androidx.compose.ui.text.font.FontWeight




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
fun gerarBloco(cor:Color?, x: Int, y: Int, z:Int? = null, imagem: Int? = null, icon: ImageVector? = null){
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
                imageVector = icon,
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
                       icon: ImageVector? = null,
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
fun CampoEditar(nomeCampo: String, placeholder: String? = null){
    var nome by remember { mutableStateOf("") }
    var focado by remember { mutableStateOf(false) }
    TextField(
        modifier = Modifier.fillMaxWidth()
            .border(width = 1.dp,
                color = if (focado) Color.White else Color.Gray,
                shape = RoundedCornerShape(8.dp))
            .onFocusChanged{campoFocado -> focado = campoFocado.isFocused},
        value = placeholder?: "",
        onValueChange = {novoNome -> nome = novoNome},
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




@Composable
fun OutrasConfiguracoesCaixa(titulo: String, descricao: String, icon: ImageVector){
    Row(modifier = Modifier.fillMaxWidth()
        .padding(horizontal = 8.dp, vertical = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = titulo, color = Color.White, fontWeight = FontWeight.Bold)
            Text(text = descricao, color = Color.Gray)
        }
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White
        )
    }
}


@Composable
fun geraBotao(x: Float, texto: String){
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
fun geraBotaoAtivacao(){
    var ativado by remember { mutableStateOf(false) }
    Switch(
        checked = ativado,
        onCheckedChange = {novoEstado -> ativado = novoEstado},
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.White,
            uncheckedThumbColor = Color.White,
            checkedTrackColor = Color(99,149, 238),
            uncheckedTrackColor = Color.Black,
            checkedBorderColor = Color(99,149, 238),
            uncheckedBorderColor = Color.White,
        )
    )


}






@Preview(showBackground = true)
@Composable
fun TelaEditarPerfil() {
    Surface(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        color = Color(0xFF1e1e1e),) {
        Row( modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 20.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row( modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    gerarConteudoBloco(Color(0xFF1e1e1e), 45, 5, icon = Icons.Default.KeyboardArrowLeft)
                    Text(text="Editar perfil", color = Color.White, textAlign = TextAlign.Center)
                    geraBotao(0.25f, "Feito")
                }




                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                ) {
                    Text(text = "Mantenha seus dados privado. As informações que você adiciona aqui ficam visíveis para qualquer pessoa que possa vizualizar seu perfil.", color = Color.Gray)
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        gerarConteudoBloco(x = 150, y = 5, cor = Color.Red, raio = 75, imagem = R.drawable.foto_perfil_editar)
                        geraBotao(0.2f,"Editar")
                    }
                }




                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(30.dp)) {
                        CampoEditar("Nome", "PinterestIdeal")
                        CampoEditar("Nome de usuário", "pinterest_Ideal")
                    }
                }


                Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)) {
                    Column{
                        OutrasConfiguracoesCaixa("Pronomes", "Compartilhe agora como as pessoas devem se dirigir a você", Icons.Default.KeyboardArrowRight)
                        OutrasConfiguracoesCaixa("Sobre", "Conte a sua história", Icons.Default.KeyboardArrowRight)
                    }
                }


                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(30.dp)) {
                        CampoEditar("Site", "Adicione um link para aumentar o tráfego no seu site")
                    }
                }


                Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 8.dp)) {
                    Text(text = "Layout", color= Color.White)
                }




                Row(modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f),
                    ) {


                        Text(text = "Mostrar todos os Pins", color = Color.White, fontWeight = FontWeight.Bold)
                        Text(text = "As pessoas que visitarem seu perfil poderão ver uma coleção de todos os Pins que você salvou." +
                                " Os Pins salvos em pastas secretas não ficarão visíveis.", color = Color.Gray)
                    }
                    geraBotaoAtivacao()


                }
            }
        }






    }


}





































































































































