package br.edu.ifsp.pds.shadowstrugglesonline.servidor.batalha;

import java.util.ArrayList;

public class Mapa {
    private ArrayList<CardInGame>[][] tiles;

	public ArrayList<CardInGame>[][] getTiles() {
		return tiles;
	}

	public void setTiles(ArrayList<CardInGame>[][] tiles) {
		this.tiles = tiles;
	}

}
