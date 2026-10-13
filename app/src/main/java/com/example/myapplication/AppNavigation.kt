package com.example.myapplication

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument

object Rotas{
     const val HOME = "home"
     const val DETALHES_PASTA = "detalhesPasta/{idPasta}"
     const val EDITARPERFIL = "editarPerfil/0"
     const val PESQUISAR_PESSOAS = "pesquisarPerfil/0"
     const val CRIAR_PASTA = "criarPasta/0"
     const val MENSAGENS = "veramigos/0"
     const val CHAT = "chat"
     const val VER_PERFIL_USUARIO = "verPerfilUsuario/{idUsuario}"
     const val VER_PASTA_USUARIO = "verPastaUsuario/{idPasta}"
     const val VER_AMIGOS = "verAmigos/{idUsuario}"
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
               composable(Rotas.MENSAGENS){
                    TelaVerAmigos(navController)
               }

               composable(Rotas.CHAT){
                    TelaMsg(navController)
               }

               composable(Rotas.VER_PERFIL_USUARIO,
                    arguments = listOf(navArgument("idUsuario") { type = NavType.IntType })
               ){ entrada ->
                    val idUsuario = entrada.arguments?.getInt("idUsuario") ?: 0
                    TelaVerPerfilUsuario(navController, idUsuario = idUsuario)
               }
               composable(Rotas.VER_PASTA_USUARIO){  entrada ->
                    val idPasta = entrada.arguments?.getString("idPasta")?.toIntOrNull()
                    TelaVerPastaUsuario(navController, idPasta = idPasta)
               }

          }
     }
}
