package br.edu.ifsp.pds.shadowstrugglesonline.servidor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import br.edu.ifsp.pds.shadowstrugglesonline.servidor.estrutura.Servidor;
import br.edu.ifsp.pds.shadowstrugglesonline.servidor.util.Login;
import br.edu.ifsp.pds.shadowstrugglesonline.servidor.util.comunicacao.DadosTelaPrincipal;
import br.edu.ifsp.pds.shadowstrugglesonline.servidor.util.comunicacao.Erro;
import br.edu.ifsp.pds.shadowstrugglesonline.servidor.util.comunicacao.SolicitacaoLogin;

public class ShadowStrugglesOnline_Server {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		boolean tmp = false;
		Class[] c = {DadosTelaPrincipal.class,Erro.class,SolicitacaoLogin.class,Login.class,HashMap.class};
		Servidor s = new Servidor(c);
		if (args.length > 0) {
			if (args[0].equals("1")) {
				Scanner scanner = new Scanner(System.in);
				System.out.println("Welcome!!");
				while (true) {
					String entrada = scanner.nextLine();
					if (entrada.equals("end")) {
						s.parar();
						System.out.println("Bye!!");
						System.exit(0);
						break;
					} else if (entrada.equals("pause")) {
						s.parar();
					} else if (entrada.equals("start")) {
						s.iniciar(38080, 28080);
					} else if (entrada.equals("help")) {
						System.out.println("");
						System.out.println("start - Inicia o servidor");
						System.out.println("pause - Pausa o servidor");
						System.out.println("end - Pausa o servidor e encerra a aplicaç~ao");
						System.out.println("");
					}
				}

				tmp = true;
			}
		}
		if (!tmp) {
			Janela j = new Janela(s);
		}

	}
}