package br.edu.ifsp.pds.shadowstruggles.tools.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Window {

	private JFrame frmTitle;
	private JTextField textField;

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTitle = new JFrame();
		frmTitle.setTitle("Title");
		frmTitle.setBounds(100, 100, 450, 300);
		frmTitle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTitle.getContentPane().setLayout(null);
		
		JLabel lblLabel = new JLabel("Label");
		lblLabel.setBounds(59, 110, 46, 14);
		frmTitle.getContentPane().add(lblLabel);
		
		textField = new JTextField();
		textField.setBounds(139, 107, 86, 20);
		frmTitle.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(278, 106, 89, 23);
		frmTitle.getContentPane().add(btnOk);
	}
}
