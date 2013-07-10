package br.edu.ifsp.pds.shasdowstruggles.view.edition;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class BattleEditor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BattleEditor frame = new BattleEditor();
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
	public BattleEditor() {
		setTitle("Battle Editor");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 244);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(24, 11, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(225, 11, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblRules = new JLabel("Rules:");
		lblRules.setBounds(24, 46, 46, 14);
		contentPane.add(lblRules);
		
		JLabel lblEnemie = new JLabel("Enemie:");
		lblEnemie.setBounds(225, 46, 46, 14);
		contentPane.add(lblEnemie);
		
		JLabel lblMapImage = new JLabel("Map Image:");
		lblMapImage.setBounds(24, 82, 73, 14);
		contentPane.add(lblMapImage);
		
		JLabel lblMaxTime = new JLabel("Max Time:");
		lblMaxTime.setBounds(225, 82, 73, 14);
		contentPane.add(lblMaxTime);
		
		textField = new JTextField();
		textField.setBounds(97, 8, 103, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(97, 43, 103, 20);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(97, 79, 103, 20);
		contentPane.add(comboBox_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(298, 8, 126, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(298, 43, 126, 20);
		contentPane.add(comboBox_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(298, 82, 126, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(24, 148, 176, 36);
		contentPane.add(btnInsert);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setBounds(226, 148, 198, 36);
		contentPane.add(btnNewButton);
	}

}
