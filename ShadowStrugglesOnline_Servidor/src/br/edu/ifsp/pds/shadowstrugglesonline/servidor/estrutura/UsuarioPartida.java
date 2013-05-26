package br.edu.ifsp.pds.shadowstrugglesonline.servidor.estrutura;

import java.util.ArrayList;

import com.esotericsoftware.kryonet.Connection;

import br.edu.ifsp.pds.shadowstrugglesonline.servidor.batalha.Card;
import br.edu.ifsp.pds.shadowstrugglesonline.servidor.batalha.Deck;
import br.edu.ifsp.pds.shadowstrugglesonline.servidor.batalha.Field;

public class UsuarioPartida extends Usuario {
   
	private Deck deck;
    private ArrayList<Card> handCards;
    private Field field;
    private int energia;
    private int life;
    
    public UsuarioPartida(String login, Connection conexao) {
		super(login, conexao);
	}
    
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	public ArrayList<Card> getHandCards() {
		return handCards;
	}
	public void setHandCards(ArrayList<Card> handCards) {
		this.handCards = handCards;
	}
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	public int getEnergia() {
		return energia;
	}
	public void setEnergia(int energia) {
		this.energia = energia;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}

}
