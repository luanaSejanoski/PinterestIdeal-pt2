package com.example.myapplication

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier

object Rotas{
     const val HOME = "home"
     const val DETALHES_PASTA = "detalhesPasta/{idPasta}"
     const val EDITARPERFIL = "editarPerfil/0"
     const val PESQUISAR_PESSOAS = "pesquisarPerfil/0"
     const val CRIAR_PASTA = "criarPasta/0"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(navController: NavHostController) {
     Scaffold(
          bottomBar = {
               MenuInferior(navController)
          }
     ) { paddingValues ->

          NavHost(
               navController = navController,
               startDestination = Rotas.HOME,
               modifier = Modifier.padding(paddingValues)
          ) {
               composable(Rotas.HOME) {
                    TelaPerfil(navController)
               }

               composable(Rotas.EDITARPERFIL) {
                    TelaEditarPerfil(navController)
               }

               composable(Rotas.DETALHES_PASTA) { entrada ->
                    val idPasta = entrada.arguments?.getString("idPasta")?.toIntOrNull() ?: 0
                    TelaItensPasta(navController, idPasta = idPasta)
               }
               composable(Rotas.PESQUISAR_PESSOAS){
                    TelaPesquisa(navController)
               }

               composable(Rotas.CRIAR_PASTA){
                    TelaCriarPasta(navController)

               }
          }
     }
}
