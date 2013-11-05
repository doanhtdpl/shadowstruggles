package br.edu.ifsp.pds.shadowstruggles.tools.view.scenes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;

import br.edu.ifsp.pds.shadowstruggles.tools.model.scenes.Decision;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DecisionChooser extends JFrame {

	private JPanel contentPane;
	private Decision decision;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DecisionChooser frame = new DecisionChooser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DecisionChooser() {
		this.decision= new Decision();
		setTitle("Decision Chooser");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 321);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChoices = new JLabel("Choices:");
		lblChoices.setBounds(35, 11, 46, 14);
		contentPane.add(lblChoices);
		
		JList list = new JList();
		list.setBounds(35, 50, 364, 119);
		contentPane.add(list);
		
		JButton btnNewChoice = new JButton("New Choice");
		btnNewChoice.setBounds(35, 180, 89, 23);
		contentPane.add(btnNewChoice);
		
		JButton btnAddDecision = new JButton("Add Decision");
		btnAddDecision.setBounds(35, 226, 131, 45);
		contentPane.add(btnAddDecision);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(284, 226, 115, 45);
		contentPane.add(btnCancel);
	}
}
