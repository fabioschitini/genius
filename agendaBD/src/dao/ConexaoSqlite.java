package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSqlite {
	
	private Connection conexao;
	private static ConexaoSqlite instancia;
	
	private ConexaoSqlite() throws SQLException {
		this.conexao=DriverManager.getConnection("jdbc:sqlite:agenda.db");
	}

	public static ConexaoSqlite getIntancia() throws SQLException {
		if(instancia==null)
			instancia=new ConexaoSqlite();
		return instancia;
	}
	
	public Connection getConexao() throws SQLException {
		return conexao;
	}
	
	

}
