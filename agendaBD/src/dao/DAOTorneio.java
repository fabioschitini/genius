package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Campeonato;
import modelo.Jogador;

public interface DAOTorneio {
	
	public void addJogador(Jogador jogador) throws Exception;
	public void addCampeonato(Campeonato campeonato) throws Exception;
	public void iniciarTables()throws Exception;
	public List<Jogador>  getJogadoresBD() throws Exception;
	public int getQuantidadeJogadores(List<Jogador> jogadores) throws Exception;
	public void deletarJogadores() throws SQLException; 
	public Campeonato getTorneioAtual()  throws Exception;
	public void setTorneioAtual(Campeonato torneio)  throws Exception;
	public ArrayList<String> getCampeonatos() throws SQLException;
}
