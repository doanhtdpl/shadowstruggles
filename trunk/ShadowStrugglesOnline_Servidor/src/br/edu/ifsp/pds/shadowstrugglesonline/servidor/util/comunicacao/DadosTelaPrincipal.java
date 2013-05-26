package br.edu.ifsp.pds.shadowstrugglesonline.servidor.util.comunicacao;

import java.util.HashMap;

/**
 * Informações necessárias para exibição da tela principal do jogo no Client-Side
 * @author Felipe Brigalante
 *
 */
public class DadosTelaPrincipal {

	/**
	 * Nome do usuário
	 */
	public String nome;
	/**
	 * Dinheiro do usuário
	 */
	public int dinheiro;
	/**
	 * Nível do usuário
	 */
	public int nivel;
	/**
	 * Habilidade do usuário expressada por um valor numérico
	 */
	public int escolaridade;
	/**
	 * Lista dos amigos do usuário, sendo a chave o nome do usuário e o boolean se o usuário
	 * esta logado.
	 */
	public HashMap<String,Boolean> amigos;
	
	public DadosTelaPrincipal() {
		// TODO Auto-generated constructor stub
	}
	
	public DadosTelaPrincipal(String nome, int dinheiro, int nivel,
			int escolaridade, HashMap<String,Boolean> amigos) {
		super();
		this.nome = nome;
		this.dinheiro = dinheiro;
		this.nivel = nivel;
		this.escolaridade = escolaridade;
		this.amigos = amigos;
	}
		
}
