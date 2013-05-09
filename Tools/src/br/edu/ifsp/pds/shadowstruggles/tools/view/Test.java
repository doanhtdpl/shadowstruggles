package br.edu.ifsp.pds.shadowstruggles.tools.view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Test {

	private JFrame frmTitle;
	private JTextField textField;

	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
		frmTitle.setVisible(true);
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
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(49, 71, 46, 54);
		frmTitle.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(137, 88, 86, 20);
		frmTitle.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnOk.setBounds(255, 87, 89, 23);
		frmTitle.getContentPane().add(btnOk);
	}
}
