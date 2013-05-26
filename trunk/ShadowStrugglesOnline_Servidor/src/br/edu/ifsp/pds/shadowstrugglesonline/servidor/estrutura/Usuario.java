package br.edu.ifsp.pds.shadowstrugglesonline.servidor.estrutura;

import com.esotericsoftware.kryonet.Connection;

/**
 * Representação de um usuário conectado ao servidor 
 * @author Felipe Brigalante
 *
 */
public class Usuario {
	/**
	 * Login do usuário
	 */
    protected String login;
    /**
     * Conexão do usuário com o servidor. 
     */
    protected Connection conexao;    
    
	public Usuario(String login, Connection conexao) {
		super();
		this.login = login;
		this.conexao = conexao;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Connection getConexao() {
		return conexao;
	}
	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}

}
