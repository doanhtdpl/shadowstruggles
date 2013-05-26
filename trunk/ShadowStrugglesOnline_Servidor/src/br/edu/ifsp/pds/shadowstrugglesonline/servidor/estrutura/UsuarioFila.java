package br.edu.ifsp.pds.shadowstrugglesonline.servidor.estrutura;

import com.esotericsoftware.kryonet.Connection;

public class UsuarioFila extends Usuario {
    
	private int escolaridade;

	
	public UsuarioFila(String login, Connection conexao, int escolaridade) {
		super(login,conexao);
		this.escolaridade = escolaridade;
	}


	public int getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(int escolaridade) {
		this.escolaridade = escolaridade;
	}

}
