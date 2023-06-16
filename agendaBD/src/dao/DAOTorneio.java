package dao;

import java.util.List;

import modelo.Jogador;

public interface DAOTorneio {
	
	public void addJogador(Jogador jogador,int id) throws Exception;
	//public void addCampeonato(Campeonato campeonato) throws Exception;
	//public Contato buscarContato(String nome)throws Exception;
	public void iniciarTables()throws Exception;
	public List<Jogador>  getJogadoresBD() throws Exception;
	public int getCurrentJogadoresId(List<Jogador> jogadores) throws Exception;

}
