package negocio;

import dao.DAOTorneio;
import dao.DAOTorneioSqlite;
import modelo.Jogador;

public class Genius {
	
	private DAOTorneio dao=new DAOTorneioSqlite();
	
	public void adicionarJogador(Jogador jogador,int id) throws Exception {
		dao.addJogador(jogador,id);
	}
	
//	public Contato buscarContato(String nome) throws Exception {
	//	return dao.buscarContato(nome);
	//}
	
	public void iniciarBD() throws Exception {
		dao.iniciarTables();
	}
	
	public int getCurrentJogId() throws Exception  {
		return dao.getCurrentJogadoresId(dao.getJogadoresBD());
	}

}
