package negocio;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.ConexaoSqlite;
import dao.DAOTorneio;
import dao.DAOTorneioSqlite;
import modelo.Campeonato;
import modelo.Jogador;

public class Genius {
	
	private DAOTorneio dao=new DAOTorneioSqlite();
	
	public void adicionarJogador(Jogador jogador) throws Exception {
		dao.addJogador(jogador);
	}
	
	public void adicionarCampeonato(Campeonato campeonato) throws Exception {
		dao.addCampeonato(campeonato);
	}
	
	public void iniciarBD() throws Exception {
		dao.iniciarTables();
	}
	
	public List<Jogador> getJogadores() throws Exception{
		return dao.getJogadoresBD();
	}
	
	public void deletarJogadores() throws Exception {
		dao.deletarJogadores();
	}
	
	public int getQuantidadeJogadores() throws Exception {
		return dao.getQuantidadeJogadores(getJogadores());
	}
	
	public Campeonato getTorneioAtual() throws Exception {
		return dao.getTorneioAtual();
	}
	
	public void setTorneioAtual(Campeonato torneio) throws Exception {
		dao.setTorneioAtual(torneio);
	}
	
	public ArrayList<String> getCampeonatos() throws SQLException{
		return dao.getCampeonatos();
	}
	
	public String retornarRelatorio(String titulo) throws SQLException {
		return dao.retornarRelatorio(titulo);
	}
	
	public void addRelatorio(String titulo,String relatorio) throws Exception {
		dao.addRelatorio(titulo, relatorio);
	}
	
	public void deletarTorneios() throws SQLException{
		dao.deletarTorneios();
	}
	
}
