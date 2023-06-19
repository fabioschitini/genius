package dao;

import java.sql.SQLException;
import java.util.List;

import modelo.Campeonato;
import modelo.Jogador;

public interface DAOTorneio {
	
	public void addJogador(Jogador jogador) throws Exception;
	public void addCampeonato(Campeonato campeonato) throws Exception;
	//public void addCampeonato(Campeonato campeonato) throws Exception;
	//public Contato buscarContato(String nome)throws Exception;
	public void iniciarTables()throws Exception;
	public List<Jogador>  getJogadoresBD() throws Exception;
	//public int getCurrentJogadoresId(List<Jogador> jogadores) throws Exception;
	//public boolean checkIfJogadorRepete(String nome) throws Exception;
	public void deletarJogadores() throws SQLException;
}
