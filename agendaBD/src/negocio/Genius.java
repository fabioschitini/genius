package negocio;

import java.util.List;

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
}
