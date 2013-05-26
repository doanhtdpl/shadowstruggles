package br.edu.ifsp.pds.shadowstrugglesonline.servidor.estrutura;

import java.util.ArrayList;

public class Chat {
    private ArrayList<Usuario> usuarios;
    private int id;

    public boolean repassarMensagem(String mensagem){
        return false;
    }

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
