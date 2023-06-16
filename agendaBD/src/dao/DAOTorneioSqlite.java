package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Jogador;

public class DAOTorneioSqlite implements DAOTorneio {

	@Override
	public void addJogador(Jogador jogador,int id) throws Exception {
		PreparedStatement pt=ConexaoSqlite.getIntancia().getConexao()
				.prepareStatement("INSERT INTO JOGADORES (ID,NOME) VALUES (?,?)");
		pt.setInt(1, id);
		pt.setString(2, jogador.getNome());
		pt.executeUpdate();
	}


	public void iniciarTables() throws Exception {
		Statement st=ConexaoSqlite.getIntancia().getConexao().createStatement();
		st.executeUpdate("CREATE TABLE IF NOT EXISTS JOGADORES (jog_id INT, jog_name VARCHAR(80) )");
	}
	
	public List<Jogador> getJogadoresBD() throws Exception {
		PreparedStatement pt=ConexaoSqlite.getIntancia().getConexao().prepareStatement("SELECT * FROM JOGADORES");
		ResultSet rs=pt.executeQuery();
		List<Jogador> lista = new ArrayList<Jogador>();
		while(rs.next()) {
			Jogador jogador= new Jogador(rs.getString("jog_name"), rs.getInt("jog_id"));
			lista.add(jogador);
		}
		return lista;
		
	}
	
	public int getCurrentJogadoresId(List<Jogador> jogadores) throws Exception{
		return jogadores.size();
	};


}
