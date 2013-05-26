package br.edu.ifsp.pds.shadowstrugglesonline.servidor;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import br.edu.ifsp.pds.shadowstrugglesonline.servidor.estrutura.Servidor;

public class Janela extends Frame {

	private Label texto;
	private Servidor server;
	private Button botao = new Button("Iniciar");

	public Janela(Servidor s) {
		this.server = s;
		this.setLayout(null);
		this.setTitle("Best Java MMO - Server");
		this.texto = new Label("O servidor está desligado...");
		this.texto.setBounds(20, 35, 200, 30);
		this.botao.setBounds(230,35,50,30);
		botao.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(botao.getLabel().equals("Iniciar")){
					try {
						server.iniciar(54555, 54777);
						botao.setLabel("Pausar");
						texto.setText("O servidor está ligado...");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else{
					server.parar();
					botao.setLabel("Iniciar");
					texto.setText("O servidor está desligado...");
				}
				
			}
		});
		this.setBounds(100, 100, 300, 80);
		this.setResizable(false);
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				
				System.exit(0);
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		this.add(texto);
		this.add(botao);
		this.setResizable(false);
		this.setVisible(true);
		
	}
}
