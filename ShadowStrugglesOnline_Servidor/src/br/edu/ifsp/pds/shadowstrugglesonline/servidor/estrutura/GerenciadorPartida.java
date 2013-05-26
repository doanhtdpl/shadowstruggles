package br.edu.ifsp.pds.shadowstrugglesonline.servidor.estrutura;

import java.util.ArrayList;

public class GerenciadorPartida {
    private ArrayList<Partida> partidas;
    private Servidor servidor;

    
    
    public GerenciadorPartida(Servidor servidor) {
		super();
		this.servidor = servidor;
	}

	public void run(){
    }

	public ArrayList<Partida> getPartidas() {
		return partidas;
	}

	public void setPartidas(ArrayList<Partida> partidas) {
		this.partidas = partidas;
	}

	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

}
