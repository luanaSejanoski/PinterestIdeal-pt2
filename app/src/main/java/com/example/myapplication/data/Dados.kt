package com.example.myapplication.data

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import com.example.myapplication.Mensagem
import com.example.myapplication.Pin
import com.example.myapplication.R
import com.example.myapplication.Usuario

object Dados {
    val mensagens = mutableStateListOf<Mensagem>()

    val usuarios = mutableStateListOf(
        Usuario(0, "Maria Silva", "maria001", "Amo fotografia 📷", "maria@email.com", null, mutableListOf(1, 5, 6, 7, 11), mutableListOf(0, 1, 2, 3)),
        Usuario(1, "João Souza", "joao002", "", "joao@email.com", null, mutableListOf(0), mutableListOf()),
        Usuario(2, "Juca", "Juquinha2", "", "juquinha@email.com", null, mutableListOf(0), mutableListOf()),
        Usuario(3, "Mariana Costa", "maricosta", "Fotógrafa amadora e entusiasta de viagens.", "mariana.costa@email.com", null, mutableListOf(1, 7, 8, 9), mutableListOf(4, 5, 6)),
        Usuario(4, "Lucas Oliveira", "lucas_oli", "Gamer, programador e fã de sci-fi.", "lucas.oliveira@email.com", null, mutableListOf(2, 5, 11), mutableListOf(7, 8, 9)),
        Usuario(5, "Beatriz Lima", "bialima", "Arquiteta e designer de interiores.", "beatriz.lima@email.com", null, mutableListOf(1, 4, 12), mutableListOf(10, 11, 12)),
        Usuario(6, "Gabriel Santos", "gabriel_s", "Estudante de Ciência da Computação.", "gabriel.santos@email.com", null, mutableListOf(2, 13, 14), mutableListOf(13, 14, 15)),
        Usuario(7, "Juliana Almeida", "ju_almeida", "Escritora de nascença e leitora voraz.", "juliana.almeida@email.com", null, mutableListOf(3, 15), mutableListOf(16, 17, 18)),
        Usuario(8, "Rafael Rocha", "rafaelrocha", "Músico e produtor de conteúdo digital.", "rafael.rocha@email.com", null, mutableListOf(3, 16, 17), mutableListOf(19, 20, 21)),
        Usuario(9, "Camila Mendes", "camilamendes", "Apaixonada por cães, gatos e desenvolvimento mobile.", "camila.mendes@email.com", null, mutableListOf(3, 18), mutableListOf(22, 23, 24)),
        Usuario(10, "Bruno Ferreira", "bruno_ferreira", "Engenheiro de software e ciclista nos fins de semana.", "bruno.ferreira@email.com", null, mutableListOf(1, 19), mutableListOf(25, 26, 27)),
        Usuario(11, "Larissa Martins", "lari_martins", "Diretora de arte e criadora de pins visuais.", "larissa.martins@email.com", null, mutableListOf(4, 20), mutableListOf(28, 29, 30)),
        Usuario(12, "Thiago Barbosa", "thibarbosa", "Especialista em segurança da informação e redes.", "thiago.barbosa@email.com", null, mutableListOf(5), mutableListOf()),
        Usuario(13, "Fernanda Ribeiro", "fe_ribeiro", "Nutricionista e entusiasta de uma vida saudável.", "fernanda.ribeiro@email.com", null, mutableListOf(6), mutableListOf()),
        Usuario(14, "Rodrigo Cardoso", "rodrigocardoso", "Editor de vídeos e motion designer.", "rodrigo.cardoso@email.com", null, mutableListOf(6), mutableListOf()),
        Usuario(15, "Patrícia Nogueira", "paty_nogs", "Psicóloga clínica e leitora de romances históricos.", "patricia.nogueira@email.com", null, mutableListOf(7), mutableListOf()),
        Usuario(16, "Diego Moreira", "diegomoreira", "Analista de dados e fã de futebol.", "diego.moreira@email.com", null, mutableListOf(8), mutableListOf()),
        Usuario(17, "Vanessa Ramos", "van_ramos", "Ilustradora digital e criadora de personagens.", "vanessa.ramos@email.com", null, mutableListOf(8), mutableListOf()),
        Usuario(18, "Eduardo Pires", "dudupires", "Empreendedor e entusiasta de startups.", "eduardo.pires@email.com", null, mutableListOf(9), mutableListOf()),
        Usuario(19, "Tatiane Duarte", "tati_duarte", "Professora de inglês e intercambista experiente.", "tatiane.duarte@email.com", null, mutableListOf(10), mutableListOf()),
        Usuario(20, "Matheus Castro", "matheuscastro", "Desenvolvedor Full Stack apaixonado por inovação.", "matheus.castro@email.com", null, mutableListOf(11), mutableListOf()),
        Usuario(21, "Beatriz Souza", "beasouza", "Estudante de medicina veterinária.", "beatriz.souza@email.com", null, mutableListOf(12), mutableListOf()),
        Usuario(22, "Lucas Mendes", "lucasmendes", "Designer gráfico e skatista nos tempos livres.", "lucas.mendes@email.com", null, mutableListOf(13), mutableListOf())
    )

    val pastas = mutableStateListOf(
        Pasta(0, "Paisagens", "Lugares esbeltos!", mutableListOf(2, 3, 4)),
        Pasta(1, "Looks", "Looks bafônicos!", mutableListOf(0, 1)),
        Pasta(2, "Desenhos", "Inspirações de ilustrações e artes", mutableListOf(5, 6, 7)),
        Pasta(3, "Looks & Outfits", "Ideias de roupas e combinações", mutableListOf(8, 9, 10)),
        Pasta(4, "Viagens dos Sonhos", "Destinos pelo mundo", mutableListOf(2, 4)),
        Pasta(5, "Fotografia Urbana", "Clicks na cidade", mutableListOf(3, 14)),
        Pasta(6, "Câmeras e Lentes", "Equipamentos favoritos", mutableListOf(0, 15)),
        Pasta(7, "Setup Gamer", "Meu espaço ideal", mutableListOf(5, 8)),
        Pasta(8, "Códigos e Snippets", "Dicas de programação", mutableListOf(11, 12)),
        Pasta(9, "Sci-Fi Art", "Conceitos futuristas", mutableListOf(6, 7)),
        Pasta(10, "Decoração Minimalista", "Ambientes limpos", mutableListOf(2, 11)),
        Pasta(11, "Salas Acolhedoras", "Inspiração de layout", mutableListOf(3, 13)),
        Pasta(12, "Cozinhas Modernas", "Projetos arquitetônicos", mutableListOf(4, 12)),
        Pasta(13, "Algoritmos", "Estudos de lógica", mutableListOf(5, 10)),
        Pasta(14, "Projetos Open Source", "Ideias em Kotlin e Compose", mutableListOf(8, 11)),
        Pasta(15, "Café e Código", "A rotina do dev", mutableListOf(12, 14)),
        Pasta(16, "Poesia e Prosa", "Trechos favoritos", mutableListOf(6, 9)),
        Pasta(17, "Biblioteca Pessoal", "Livros para ler", mutableListOf(7, 10)),
        Pasta(18, "Escrita Criativa", "Dicas e estruturas", mutableListOf(11, 15)),
        Pasta(19, "Beats e Instrumentos", "Estúdio caseiro", mutableListOf(2, 8)),
        Pasta(20, "Produção Musical", "Plugins e sintetizadores", mutableListOf(5, 12)),
        Pasta(21, "Shows e Festivais", "Memórias inesquecíveis", mutableListOf(3, 14)),
        Pasta(22, "Pets Fofinhos", "Cães e gatos lindos", mutableListOf(4, 11)),
        Pasta(23, "Apps Mobile", "UI/UX em Flutter e Compose", mutableListOf(9, 13)),
        Pasta(24, "Rotina Pet", "Cuidados com os animais", mutableListOf(7, 15)),
        Pasta(25, "Ciclismo Urbano", "Rotas de bike", mutableListOf(2, 14)),
        Pasta(26, "Engenharia Civil", "Estruturas fascinantes", mutableListOf(3, 12)),
        Pasta(27, "Manutenção de Bikes", "Dicas mecânicas", mutableListOf(10, 16)),
        Pasta(28, "Identidade Visual", "Branding e logotipos", mutableListOf(5, 8)),
        Pasta(29, "Paletas de Cores", "Combinações visuais", mutableListOf(6, 9)),
        Pasta(30, "Mockups", "Apresentação de projetos", mutableListOf(7, 11))
    )

    val pins = mutableStateListOf(
        Pin(0, Uri.parse("android.resource://com.example.myapplication/${R.drawable.outfit1}"), 600, 468),
        Pin(1, Uri.parse("android.resource://com.example.myapplication/${R.drawable.outfit2}"), 700, 1080),
        Pin(2, Uri.parse("android.resource://com.example.myapplication/${R.drawable.summer1}"), 598, 280),
        Pin(3, Uri.parse("android.resource://com.example.myapplication/${R.drawable.summer2}"), 900, 560),
        Pin(4, Uri.parse("android.resource://com.example.myapplication/${R.drawable.summer3}"), 1080, 720),
        Pin(5, Uri.parse("android.resource://com.example.myapplication/drawable/desenhos1"), 800, 1000),
        Pin(6, Uri.parse("android.resource://com.example.myapplication/drawable/desenhos2"), 800, 1200),
        Pin(7, Uri.parse("android.resource://com.example.myapplication/drawable/desenhos3"), 800, 900),
        Pin(8, Uri.parse("android.resource://com.example.myapplication/drawable/outfit1"), 800, 1100),
        Pin(9, Uri.parse("android.resource://com.example.myapplication/drawable/outfit2"), 800, 1000),
        Pin(10, Uri.parse("android.resource://com.example.myapplication/drawable/outfit3"), 800, 1300),
        Pin(11, Uri.parse("android.resource://com.example.myapplication/drawable/receitas1"), 800, 800),
        Pin(12, Uri.parse("android.resource://com.example.myapplication/drawable/receitas2"), 800, 1000),
        Pin(13, Uri.parse("android.resource://com.example.myapplication/drawable/receitas3"), 800, 1100),
        Pin(14, Uri.parse("android.resource://com.example.myapplication/drawable/summer1"), 800, 1000),
        Pin(15, Uri.parse("android.resource://com.example.myapplication/drawable/summer2"), 800, 1200),
        Pin(16, Uri.parse("android.resource://com.example.myapplication/drawable/summer3"), 800, 950)
    )

    fun proximoIdPasta(): Int {
        return if (pastas.isEmpty()) 0 else pastas.maxOf { it.id } + 1
    }

    fun proximoIdPin(): Int {
        return if (pins.isEmpty()) 0 else pins.maxOf { it.id } + 1
    }
}