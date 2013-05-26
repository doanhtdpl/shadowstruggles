package br.edu.ifsp.pds.shadowstrugglesonline.servidor.util.comunicacao;


/**
 * Mensagem de erro gen´erica para ser enviado ao cliente quando ao n~ao ocorre corretamente
 * @author Felipe Brigalante
 *
 */
public class Erro {

	/**
	 * Mensagem de erro
	 */
	public String mensagem;

	public Erro() {
		// TODO Auto-generated constructor stub
	}
	
	public Erro(String mensagem) {
		super();
		this.mensagem = mensagem;
	}
}
