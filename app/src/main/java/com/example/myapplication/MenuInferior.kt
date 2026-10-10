package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController

@Composable
fun MenuInferior(navController: NavHostController) {

    NavigationBar(
        containerColor = Color(0xFF757575)
    ) {
        NavigationBarItem(
            selected = true,
            onClick = {
                navController.navigate(Rotas.HOME)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Tela inicial",
                    tint = Color.White
                )
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate(Rotas.PESQUISAR_PESSOAS)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Pesquisar pessoas",
                    tint = Color.White,
                )
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate(Rotas.CRIAR_PASTA)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Criar pasta",
                    tint = Color.White
                )
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate(Rotas.HOME)
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.icon_msg),
                    contentDescription = "Mensagem",
                )
            }
        )
    }
}
