package br.edu.ifsp.pds.shadowstrugglesonline.servidor.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.HashMap;

import br.edu.ifsp.pds.shadowstrugglesonline.servidor.util.Login;
import br.edu.ifsp.pds.shadowstrugglesonline.servidor.util.comunicacao.DadosTelaPrincipal;

public class DBConnector {
	/**
	 * Conexão com o banco de dados
	 */
    private Connection conexao;  
    

    /**
     * Construtora da classe e já realiza a conexão com o banco de
     * dados
     * 
     * @throws ClassNotFoundException - Problema com a biblioteca
     * @throws SQLException - Errode SQL em relação as informações de conexão
     */
	public DBConnector() throws ClassNotFoundException, SQLException {
		super();
		
		Connection connection = null; // atributo do tipo Connection
		
		// Carregando o JDBC Driver padrão
		String driverName = "com.mysql.jdbc.Driver";
		Class.forName(driverName);

		// Configurando a nossa conexão com um banco de dados//
		String serverName = "localhost"; // caminho do servidor do BD
		String mydatabase = "sso2013"; // nome do seu banco de dados
		String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
		String username = "root"; // nome de um usuário de seu BD
		String password = "shadowstrugglesonline2013"; // sua senha de acesso
		connection = DriverManager.getConnection(url, username, password);
		this.conexao = connection;
	}
	/**
	 * Verifica se o usuário existe apartir do login;
	 * 
	 * @param login - O login enviado pelo usuário ao logar;
	 * @return true, se o usuário existe com a senha válida
	 * 		   false, se o usuário não existir ou a senha for inválida;
	 * @throws SQLException - Problema com a conexão;
	 */
	public boolean verificaUsuario(Login login) throws SQLException{
		String query = "SELECT COUNT(*) " +
				"FROM usuario " +
				"WHERE login = ? " +
				"AND senha = ?";
		
		PreparedStatement ps = conexao.prepareStatement(query);
		
		ps.setString(1, login.login);
		ps.setString(2, login.senha);
		
		ResultSet rs = ps.executeQuery();
		
		boolean retornar;
	
		if (rs.next()){
			if (rs.getInt(1) == 0){
				retornar = false;
			} else {
				retornar = true;
			}	
		}
		else{
			retornar = false;
		}
		
		rs.close();
		
		return retornar;
    }

	/**
	 * Cria um novo usuário no banco de dados apartir do login e senha
	 * todos os outros parâmetros são definidos por default
	 * @param login - dados de cadastro
	 * @throws SQLException - Erro na conexão com banco de dados ou query
	 */
	public void novoUsuario(Login login) throws SQLException{
		String query = "insert into usuario(login, senha) values(?, ?)";
		
		PreparedStatement ps = conexao.prepareStatement(query);
		
		ps.setString(1, login.login);
		ps.setString(2, login.senha);
		
		try{
    		ps.executeUpdate();
    	}catch(SQLException e){
    		throw new SQLException();
    	}
    	finally{
    		ps.close();
    	}
		
		ps.close();
	}
	
	/**
	 * Pega a pontuação de escolaridade do usuario;
	 * @param login - String com o login do usuário;
	 * @return Retorna -1 se não houver login
	 * se houver login, retorna int positivo;
	 * @throws SQLException - Erro de conexão com o banco ou query;
	 */
    public int getEscolaridade(String login) throws SQLException{
    	String query = "select exp_escolaridade FROM usuario WHERE login = ?";
    	
    	PreparedStatement ps = conexao.prepareStatement(query);
    	
    	ps.setString(1, login);
    	
    	ResultSet rs = ps.executeQuery();
    	
    	int escolaridade;
    	
    	if (rs.next()){
    		escolaridade = rs.getInt("exp_escolaridade");
    	}
    	else{
    		escolaridade = -1;
    	}
    	
    	rs.close();
    	
    	return escolaridade;
    }
    
    /**
     * Atualiza a pontuação de escolaridade no usuario (não faz cálculo)
     * @param login - A string login do usuário a ser modificado
     * @param escolaridade - A nova pontuação de escolaridade
     * @throws SQLException - Erro na conexão ou na query;
     */
    public void AtualizarEscolaridade(String login, int escolaridade) throws SQLException{
    	String query = "update usuario " +
    			"set exp_escolaridade=? " +
    			"where login=?";
    	
    	PreparedStatement ps = conexao.prepareStatement(query);
    	
    	ps.setInt(1, escolaridade);
    	ps.setString(2, login);
    	try{
    		ps.executeUpdate();
    	}catch(SQLException e){
    		throw new SQLException();
    	}
    	finally{
    		ps.close();
    	}
    }
    
    public DadosTelaPrincipal getDadosTelaPrincipal(String login){
    	String query = "select dinheiro, nivel, "
    	
    	return null;
    }
    

    /**
     * Faz commit no banco de dados e finaliza a conexão
     * @throws SQLException - Erro de conexão ao banco de dados
     */
    public void close() throws SQLException{
    	conexao.close();
    	
    }
}
