package br.edu.ifsp.pds.shadowstrugglesonline.servidor.estrutura;

import java.util.ArrayList;

public class GerenciadorChat {
    private ArrayList<Chat> chats;
    private Servidor servidor;

    
    
	public GerenciadorChat(Servidor servidor) {
		super();
		this.servidor = servidor;
	}

	public ArrayList<Chat> getChats() {
		return chats;
	}

	public void setChats(ArrayList<Chat> chats) {
		this.chats = chats;
	}

}
