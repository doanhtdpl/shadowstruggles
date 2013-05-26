package br.edu.ifsp.pds.shadowstrugglesonline.servidor.estrutura;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import br.edu.ifsp.pds.shadowstrugglesonline.servidor.db.DBConnector;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import br.edu.ifsp.pds.shadowstrugglesonline.servidor.util.*;
import br.edu.ifsp.pds.shadowstrugglesonline.servidor.util.comunicacao.DadosTelaPrincipal;
import br.edu.ifsp.pds.shadowstrugglesonline.servidor.util.comunicacao.Erro;
import br.edu.ifsp.pds.shadowstrugglesonline.servidor.util.comunicacao.SolicitacaoEntrarFila;
import br.edu.ifsp.pds.shadowstrugglesonline.servidor.util.comunicacao.SolicitacaoLogin;
import br.edu.ifsp.pds.shadowstrugglesonline.servidor.util.comunicacao.entrouNaFila;



/**
 * Servidor do jogo, responsável por gerenciar a comunicação com os clientes.
 * Possui os Gerenciadores responsáveis por cuidar de tarefas específicas do servidor.
 * @author Felipe Brigalante
 */
public class Servidor {
	
	/**
	 * Responsável efetivamente por cuidar da comunicação com o cliente (Envio e recebimento de pacotes)
	 */
    private Server server;
    /**
     * Responsável pela comunicação com o banco de dados
     */
    private DBConnector comunicador;
    /**
     * Gerencia o login e logout dos usuários
     */
    private GerenciadorUsuario gerenciadorUsuario;
    /**
     * Gerencia a fila para partidas, gerando partidas mais equilibradas quanto for o possível
     */
    private GerenciadorFila gerenciadorFila;
    /**
     * Gerencia as partidas, cuidando dos main loop das partidas e da entrada dos usuários 
     */
    private GerenciadorPartida gerenciadorPartida;
    /**
     * Gerencia as conversações dos jogadores;
     */
    private GerenciadorChat gerenciadorChat;

    public Servidor(Class[] classes) {
		server = new Server();
		this.gerenciadorChat = new GerenciadorChat(this);
		this.gerenciadorFila = new GerenciadorFila(this);
		this.gerenciadorPartida = new GerenciadorPartida(this);
		this.gerenciadorUsuario = new GerenciadorUsuario(this);
		registrar(classes);
		try {
			comunicador = new DBConnector();
		} catch (Exception e) {
			System.out.println("Não foi possível conectar com o banco de dados");
			//System.exit(0);
		}
		server.addListener(new Listener(){
			
			@Override
			public void connected(Connection arg0) {
					
			}
			
			@Override
			public void disconnected(Connection arg0) {
				
			}
			
			@Override
			public void received(Connection conexao, Object objeto) {
				/*
				 * Caso o pacote recebido seja uma solicitacao de login, 
				 * retorna para o cliente as informaç~oes para preencher a tela principal do usuario caso este seja valido,
				 * sen~ao retorna uma mensagem de erro.
				 */
				if(objeto instanceof SolicitacaoLogin){
					SolicitacaoLogin sl = (SolicitacaoLogin) objeto;
					if(gerenciadorUsuario.conectarUsuario(sl.login,conexao)){
						DadosTelaPrincipal dtp = comunicador.getDadosTelaPrincipal(sl.login.login);
						gerenciadorUsuario.quemEstaOnline(dtp.amigos);
						conexao.sendTCP(dtp);
					}else{
						conexao.sendTCP(new Erro("N~ao foi possivel conectar ao servidor, tente novamente"));
					}
				}else if(objeto instanceof SolicitacaoEntrarFila){
					if(gerenciadorUsuario.mandarPraFila(gerenciadorUsuario.procuraPorConexao(conexao))){
						conexao.sendTCP(new entrouNaFila());
					}else{
						conexao.sendTCP(new Erro("N~ao foi possivel entrar na fila"));
					}
				}
			}
		});
	}

	public DBConnector getComunicador() {
		return comunicador;
	}

	public void setComunicador(DBConnector comunicador) {
		this.comunicador = comunicador;
	}

	public GerenciadorUsuario getGerenciadorUsuario() {
		return gerenciadorUsuario;
	}

	public void setGerenciadorUsuario(GerenciadorUsuario gerenciadorUsuario) {
		this.gerenciadorUsuario = gerenciadorUsuario;
	}

	public GerenciadorFila getGerenciadorFila() {
		return gerenciadorFila;
	}

	public void setGerenciadorFila(GerenciadorFila gerenciadorFila) {
		this.gerenciadorFila = gerenciadorFila;
	}

	public GerenciadorPartida getGerenciadorPartida() {
		return gerenciadorPartida;
	}

	public void setGerenciadorPartida(GerenciadorPartida gerenciadorPartida) {
		this.gerenciadorPartida = gerenciadorPartida;
	}

	public GerenciadorChat getGerenciadorChat() {
		return gerenciadorChat;
	}

	public void setGerenciadorChat(GerenciadorChat gerenciadorChat) {
		this.gerenciadorChat = gerenciadorChat;
	}

	/**
	 * Inicia o servidor nas portas TCP e UDP especificadas
	 * @param TCPPort - Porta TCP
	 * @param UDPPort - Porta UDP
	 * @throws IOException
	 */
	public void iniciar(int TCPPort, int UDPPort) throws IOException {
		server.bind(TCPPort, UDPPort);
		server.start();
	}

	
	/**
	 * Registra as classes no Kryo do servidor para que ele possa enviar e receber
	 * classes desse tipo. 
	 * @param classes - Classes a serem registradas
	 */
	private void registrar(Class[] classes) {
		Kryo kryo = server.getKryo();
		for (Class c : classes) {
			kryo.register(c);
		}
	}

	

	

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	
	/**
	 * Pausa o servidor
	 */
	public void parar() {
		server.stop();
	}
    
}
