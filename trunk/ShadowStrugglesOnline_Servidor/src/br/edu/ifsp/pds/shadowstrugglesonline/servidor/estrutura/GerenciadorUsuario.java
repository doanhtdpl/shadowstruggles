package br.edu.ifsp.pds.shadowstrugglesonline.servidor.estrutura;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import br.edu.ifsp.pds.shadowstrugglesonline.servidor.util.Login;

import com.esotericsoftware.kryonet.Connection;

/**
 * Gerencia o login e logout dos usuários
 * @author Felipe Brigalante
 */
public class GerenciadorUsuario {
	
	/**
     * Lista de usuariosonline
     */
    private ArrayList<Usuario> usuariosOnline;
    /**
     * Servidor que este gerenciador está contido
     */
    private Servidor servidor;

    
    
    
    public GerenciadorUsuario(Servidor servidor) {
		super();
		this.servidor = servidor;
		this.usuariosOnline = new ArrayList<Usuario>();
	}



	/**
    *Adiciona o usuário com o login correspondente caso este seja válido.
    *@param login - Login de usuário que será adicionado
    *@return boolean - retorna true se o usuário é válido e false em qualquer outro caso 
    */
    public boolean conectarUsuario(Login login, Connection connection){
        boolean valido = false;
        try {
			if(servidor.getComunicador().verificaUsuario(login) && !estaLogado(login.login)){
				usuariosOnline.add(new Usuario(login.login,connection));
				valido = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return valido;
    }

    
    
    /**
     * Verifica se o usuário está logado
     * @param login - o login a ser verificado
     * @return boolean - retorna true se o usuário já está na lista de usuários logados e false em qualquer outro caso
     */
    private boolean estaLogado(String login) {
		boolean tmp = false;
    	for(Usuario u: usuariosOnline){
			if(u.getLogin().equals(login)){
				tmp = true;
				break;
			}
		}
    	return tmp;
	}
    
    /**
     * Retorna o usuário com o conexão especificada
     * @param c - conexão especificada
     * @return Usuario - o usuário com o conexão correspondende, ou null se o usuário não existir
     */
    public Usuario procuraPorConexao(Connection c){
		Usuario tmp = null;
    	for(Usuario u:usuariosOnline){
    		if(u.getConexao().equals(c)){
    			tmp = u;
    		}
    	}
    	return tmp;
    	
    }

    /**
     * Altera o valor das chaves para true se ele estiver online e false caso contrário
     * @param usuarios - um HashMap com os usuários listados
     */
    public void quemEstaOnline(HashMap<String,Boolean> usuarios){
    	for(String login:usuarios.keySet()){
    		if(estaLogado(login)){
    			usuarios.put(login, true);
    		}else{
    			usuarios.put(login, false);
    		}
    	}
    }


	public boolean desconectarUsuario(Connection conexao){
        return false;
    }

	
	/**
	 * tenta enviar o usuário para fila de espera
	 * @param usuario - o usuário a ser enviado para a fila
	 * @return boolean - true se o usuário foi inserido na fila, false em qualquer outro caso
	 */
    public boolean mandarPraFila(Usuario usuario){
        boolean tmp = false;
        try {
			 int escolaridade = servidor.getComunicador().getEscolaridade(usuario.login);
			 UsuarioFila user = new UsuarioFila(usuario.login, usuario.conexao, escolaridade);
			 usuariosOnline.remove(usuario);
			 usuariosOnline.add(user);
			 servidor.getGerenciadorFila().getFila().add(user);
			 tmp = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
        return tmp;
    }

	public ArrayList<Usuario> getUsuariosOnline() {
		return usuariosOnline;
	}

	public void setUsuariosOnline(ArrayList<Usuario> usuariosOnline) {
		this.usuariosOnline = usuariosOnline;
	}

	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

}
