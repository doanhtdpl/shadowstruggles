package br.edu.ifsp.pds.shadowstrugglesonline.servidor.estrutura;

import br.edu.ifsp.pds.shadowstrugglesonline.servidor.batalha.Mapa;

public class Partida {
    private UsuarioPartida usuario1;
    private UsuarioPartida usuario2;
    private Mapa mapa;
	public UsuarioPartida getUsuario1() {
		return usuario1;
	}
	public void setUsuario1(UsuarioPartida usuario1) {
		this.usuario1 = usuario1;
	}
	public UsuarioPartida getUsuario2() {
		return usuario2;
	}
	public void setUsuario2(UsuarioPartida usuario2) {
		this.usuario2 = usuario2;
	}
	public Mapa getMapa() {
		return mapa;
	}
	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}

}
