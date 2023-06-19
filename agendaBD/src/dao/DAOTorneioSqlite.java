package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Campeonato;
import modelo.Jogador;

public class DAOTorneioSqlite implements DAOTorneio {

	@Override
	public void addJogador(Jogador jogador) throws Exception {
		PreparedStatement pt=ConexaoSqlite.getIntancia().getConexao()
				.prepareStatement("INSERT INTO JOGADORES (jog_name) VALUES (?)");
		pt.setString(1, jogador.getNome());
		pt.executeUpdate();
	}
	
	public void addCampeonato(Campeonato campeonato) throws Exception {
		PreparedStatement pt=ConexaoSqlite.getIntancia().getConexao()
				.prepareStatement("INSERT INTO CAMPEONATOS (cam_titulo,cam_relatorio,cam_completo,cam_data) VALUES (?,?,?,?)");
		pt.setString(1, campeonato.getTitulo());
		pt.setString(2, campeonato.getRelatorio());
		pt.setInt(3, campeonato.getCompleto());
		pt.setString(4, campeonato.getDataDoCampeonato());
		pt.executeUpdate();
	}


	public void iniciarTables() throws Exception {
		Statement st=ConexaoSqlite.getIntancia().getConexao().createStatement();
		st.executeUpdate("CREATE TABLE IF NOT EXISTS JOGADORES (jog_name VARCHAR(80) PRIMARY KEY )");
		st.executeUpdate("CREATE TABLE IF NOT EXISTS CAMPEONATOS" 
				+ " (cam_titulo VARCHAR(80) PRIMARY KEY,cam_relatorio VARCHAR(450),cam_completo INT(1),cam_data VARCHAR(45))");
		//st.executeUpdate("CREATE TABLE IF NOT EXISTS JOGADORES_CAMPEONATO (cam_titulo VARCHAR(80),jog_name VARCHAR(80),"
			//	+ "FOREIGN KEY(cam_titulo) REFERENCES CAMPEONATOS(cam_titulo),"
			//	+ "FOREIGN KEY(jog_name) REFERENCES JOGADORES(jog_name) )"
			//	);
	}
	
	public List<Jogador> getJogadoresBD() throws Exception {
		PreparedStatement pt=ConexaoSqlite.getIntancia().getConexao().prepareStatement("SELECT * FROM JOGADORES");
		ResultSet rs=pt.executeQuery();
		List<Jogador> lista = new ArrayList<Jogador>();
		while(rs.next()) {
			Jogador jogador= new Jogador(rs.getString("jog_name"));
			lista.add(jogador);
		}
		return lista;
	}
	
	public void deletarJogadores() throws SQLException {
		PreparedStatement pt=ConexaoSqlite.getIntancia().getConexao().prepareStatement("DELETE * FROM JOGADORES");
		ResultSet rs=pt.executeQuery();
	}
	
	/*public boolean checkIfJogadorRepete(String nome) throws Exception {
		List<Jogador> lista =getJogadoresBD();
		
        for(Jogador jogador : lista){
        	//System.out.println(jogador.getNome()+"/n");
        	//System.out.println(nome);
        	if(jogador.getNome().equals(nome)) {
            	System.out.println("repetido");
    			throw new Exception();
        	}
        }
        
        return true;
			} 
	
	public int getCurrentJogadoresId(List<Jogador> jogadores) throws Exception{
		return jogadores.size();
	};*/


}
