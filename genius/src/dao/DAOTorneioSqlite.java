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
	
	private Campeonato torneio;

	@Override
	public void addJogador(Jogador jogador) throws Exception {
		PreparedStatement pt=ConexaoSqlite.getIntancia().getConexao()
				.prepareStatement("INSERT INTO JOGADORES (jog_name) VALUES (?)");
		pt.setString(1, jogador.getNome());
		pt.executeUpdate();
	}
	
	public void addCampeonato(Campeonato campeonato) throws Exception {
		PreparedStatement pt=ConexaoSqlite.getIntancia().getConexao()
				.prepareStatement("INSERT INTO CAMPEONATOS (cam_titulo,cam_relatorio,cam_data) VALUES (?,?,?)");
		pt.setString(1, campeonato.getTitulo());
		pt.setString(2, campeonato.getRelatorio());
		pt.setString(3, campeonato.getDataDoCampeonato());
		pt.executeUpdate();
	}
	
	public void deletarTorneios() throws SQLException{
		Statement st=ConexaoSqlite.getIntancia().getConexao().createStatement();
		st.executeUpdate("DELETE FROM CAMPEONATOS");
	}
	public void addRelatorio(String titulo,String relatorio) throws Exception {
		System.out.println(titulo);
		System.out.println(relatorio);
		PreparedStatement pt=ConexaoSqlite.getIntancia().getConexao()
				.prepareStatement("UPDATE CAMPEONATOS SET cam_relatorio ='"+relatorio+"' WHERE cam_titulo='"+titulo+"'");
				pt.executeUpdate();
	}


	public void iniciarTables() throws Exception {
		Statement st=ConexaoSqlite.getIntancia().getConexao().createStatement();
		st.executeUpdate("CREATE TABLE IF NOT EXISTS JOGADORES (jog_name VARCHAR(80) PRIMARY KEY )");
		st.executeUpdate("CREATE TABLE IF NOT EXISTS CAMPEONATOS" 
				+ " (cam_titulo VARCHAR(80) PRIMARY KEY,cam_relatorio VARCHAR(850),cam_data VARCHAR(45))");
	}
	
	public ArrayList<Jogador> getJogadoresBD() throws Exception {
		PreparedStatement pt=ConexaoSqlite.getIntancia().getConexao().prepareStatement("SELECT * FROM JOGADORES");
		ResultSet rs=pt.executeQuery();
		ArrayList<Jogador> lista = new ArrayList<Jogador>();
		while(rs.next()) {
			Jogador jogador= new Jogador(rs.getString("jog_name"));
			lista.add(jogador);
		}
		return lista;
	}
	
	public Campeonato getTorneioAtual() {
		return torneio;
	}
	
	public ArrayList<String> getCampeonatos() throws SQLException{
		PreparedStatement pt=ConexaoSqlite.getIntancia().getConexao().prepareStatement("SELECT *FROM CAMPEONATOS");
		ResultSet rs=pt.executeQuery();
		ArrayList<String> lista = new ArrayList<String>();
		while(rs.next()) {
			lista.add(rs.getString("cam_titulo"));
		}
		return lista;
	}
	 
	public void setTorneioAtual(Campeonato torneio) throws Exception {
		this.torneio=torneio; 
		this.torneio.setJogadores(getJogadoresBD());
	}
	
	public void deletarJogadores() throws SQLException {
		Statement st=ConexaoSqlite.getIntancia().getConexao().createStatement();
		st.executeUpdate("DELETE FROM JOGADORES");
	}
	
	public int getQuantidadeJogadores(List<Jogador> jogadores) throws Exception{
		return jogadores.size();
	};
	
	public String retornarRelatorio(String titulo) throws SQLException {
		PreparedStatement pt=ConexaoSqlite.getIntancia().getConexao().prepareStatement("SELECT * FROM CAMPEONATOS");
		ResultSet rs=pt.executeQuery();
		ArrayList<Campeonato> lista = new ArrayList<Campeonato>();
		while(rs.next()) {
			Campeonato campeonato= new Campeonato(rs.getString("cam_titulo"),
					rs.getString("cam_relatorio"));
			lista.add(campeonato);
		}
		for(Campeonato campeonato : lista){
			if(campeonato.getTitulo().equals(titulo)) {
				return campeonato.getRelatorio();
			}
}
		return "";
}

	
}
