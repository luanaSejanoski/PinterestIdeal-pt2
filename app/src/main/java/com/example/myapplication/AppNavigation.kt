package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

object Rotas{
     val HOME = "home"
     val DETALHES_PASTA = "detalhesPasta/{idPasta}"
     val EDITARPERFIL = "editarPerfil/0"
}

@Composable
fun AppNavigation(navController: NavHostController){
     NavHost(
          navController = navController,
          startDestination = Rotas.HOME
     ){
          composable(Rotas.HOME){
               TelaPerfil(navController)
          }

          composable(Rotas.EDITARPERFIL){
               TelaEditarPerfil(navController)
          }

          composable(Rotas.DETALHES_PASTA){ entrada ->
              val idPasta = entrada.arguments?.getString("idPasta")?.toIntOrNull()?: 0
               TelaItensPasta(navController, idPasta = idPasta)
          }

     }
}


