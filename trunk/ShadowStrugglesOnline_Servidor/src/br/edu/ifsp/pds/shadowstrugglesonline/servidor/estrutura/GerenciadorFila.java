package br.edu.ifsp.pds.shadowstrugglesonline.servidor.estrutura;

import java.util.ArrayList;

public class GerenciadorFila {
    private ArrayList<UsuarioFila> fila;
    private Servidor servidor;

    
    
    public GerenciadorFila(Servidor servidor) {
		super();
		this.servidor = servidor;
	}

	public void run(){
    }

    public boolean selecionarParaPartida(Usuario usuario1, Usuario usuario2){
        return false;
    }

	public ArrayList<UsuarioFila> getFila() {
		return fila;
	}

	public void setFila(ArrayList<UsuarioFila> fila) {
		this.fila = fila;
	}

	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

}
