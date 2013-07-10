package br.edu.ifsp.pds.shasdowstruggles.view.edition;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class EnemieEditor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JComboBox comboBox;
	private JTextArea textArea;
	private JButton btnInsert;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnemieEditor frame = new EnemieEditor();
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
	public EnemieEditor() {
		setTitle("Enemie Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(21, 11, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(217, 11, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblDeck = new JLabel("Deck:");
		lblDeck.setBounds(21, 47, 46, 14);
		contentPane.add(lblDeck);
		
		JLabel lblMaxHp = new JLabel("Max HP:");
		lblMaxHp.setBounds(217, 47, 46, 14);
		contentPane.add(lblMaxHp);
		
		JLabel lblMaxEnergy = new JLabel("Max Energy:");
		lblMaxEnergy.setBounds(21, 82, 78, 14);
		contentPane.add(lblMaxEnergy);
		
		JLabel lblInitialEnergy = new JLabel("Initial Energy:");
		lblInitialEnergy.setBounds(217, 82, 78, 14);
		contentPane.add(lblInitialEnergy);
		
		JLabel lblEnergyRecovery = new JLabel("Energy Recovery:");
		lblEnergyRecovery.setBounds(21, 117, 101, 14);
		contentPane.add(lblEnergyRecovery);
		
		JLabel lblNewLabel = new JLabel("Deck Capacity:");
		lblNewLabel.setBounds(217, 117, 78, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblDoubleDrawRate = new JLabel("Double Draw rate:");
		lblDoubleDrawRate.setBounds(21, 153, 101, 14);
		contentPane.add(lblDoubleDrawRate);
		
		JLabel lblStrategy = new JLabel("Strategy:");
		lblStrategy.setBounds(21, 189, 46, 14);
		contentPane.add(lblStrategy);
		
		textArea = new JTextArea();
		textArea.setBounds(95, 184, 329, 66);
		contentPane.add(textArea);
		
		textField = new JTextField();
		textField.setBounds(95, 8, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(307, 8, 117, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setBounds(95, 44, 86, 20);
		contentPane.add(comboBox);
		
		textField_2 = new JTextField();
		textField_2.setBounds(307, 44, 117, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(95, 79, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(305, 79, 119, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(119, 114, 86, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(305, 114, 119, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(119, 150, 86, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(307, 150, 117, 20);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblCardPoints = new JLabel("Card Points:");
		lblCardPoints.setBounds(215, 153, 80, 14);
		contentPane.add(lblCardPoints);
		
		btnInsert = new JButton("Insert");
		btnInsert.setBounds(21, 274, 198, 37);
		contentPane.add(btnInsert);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(254, 274, 170, 37);
		contentPane.add(btnCancel);
	}

}
