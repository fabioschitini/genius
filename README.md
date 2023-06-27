# genius




Um projeto em que voce joga um torneio do jogo genius.Nele, deve ser permitirdo que mais de um jogador dispute um campeonato. A
cada rodada devem ser computados e exibidos os pontos(totais e da rodada) de cada Jogador. Um jogador
deve jogar até errar sua sequência e depois passar a vez. Em caso de empate, uma rodada extra deve ser
sugerida pelo sistema para os jogadores empatados e isso deve se repetir até que um jogador vença. No fim
do campeonato deve ser possível imprimir um relatório onde sejam contemplados os campos :tempo total de
jogadas de cada jogador, Total de pontos, Nome, Apelido, Data do Campeonato, Título do Campeonato e
Jogada mais rápida de cada jogador.


# Tecnologias

* Java

# Ferramentas

* Eclipse
* Linux terminal
* Git and GitHub
* Windows Builder
* Node Libraries
* 
# Resultados
* Usamos Git e GitHub para ajudar na organização e updates do trabalho.
* Tentamos manter um codigo limpo e funcional.
* Usamos SQLLite para criar um banco de daods para armarzenar os dados dos jogadores e torneios cadastrados
* Implementamos o banco de dados no package dao
* Organizamos as funcoes de acesso e modificao do banco de dadoes no package negocio
* Criamos os modelos das classes jogadores e torneios na pasta modelo
* Por fim criamos a interface de usuario e a logica do jogo no package ui
* Dividimos a ui em 4 tabs:CadastrarJogador,CadastrarTorneio,MostrarRelatorios e a tab do jogo em si
* Na tab de cadastrar jogadores tem a opcao de castrar um jogador e deletar todos eles
* Na tab de cadastrar torneios tem a opca de cadastrar e deletar todos os torneios
* Na tab de relatorios voce escolhe o torneio cadastrado e pesquisa sobre seu relatorio
* Na tab de jogo tem a opcao de selecionar o torneio que voce quer jogar,a dificuldade e a velocidade

# Instruções
* Baixe o zip do repositorio ou faça um clone dele
* Instalae a biblioteca sqlite jdbc atraves do arquvio rar da pasta
* Para instalar clique om o botao direito na pasta onde esta o seu projeto no eclipse e clique em Properties->Java Build Path->ClassPath->Add External Jars e selecione o arquivo sqlite-jdbc-3.41.2.1.jar
* Abra o projeto pelo eclipse e clique em Run
* Enjoy 
# Resumo

## O que aprendemos

* Melhoramos nosso conhecimento de POO e Java
* Como usar Windown Builder e as funcoes graficas de Java
* Como compartilhar codigo em conjunto com o gitHub


## Autores

GitHub: [fabioschitini](https://github.com/fabioschitini)
