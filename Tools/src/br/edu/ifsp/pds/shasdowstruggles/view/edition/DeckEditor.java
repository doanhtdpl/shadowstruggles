package br.edu.ifsp.pds.shasdowstruggles.view.edition;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class DeckEditor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBox comboBox;
	private JTextArea textArea;
	private JButton btnInserir;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeckEditor frame = new DeckEditor();
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
	public DeckEditor() {
		setTitle("Deck editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(50, 30, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(50, 65, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Total Points");
		lblNewLabel_2.setBounds(50, 106, 69, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Image");
		lblNewLabel_3.setBounds(50, 155, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Cards");
		lblNewLabel_4.setBounds(50, 194, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(164, 27, 216, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(164, 62, 216, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(164, 103, 216, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setBounds(164, 152, 216, 20);
		contentPane.add(comboBox);
		
		textArea = new JTextArea();
		textArea.setBounds(164, 194, 238, 99);
		contentPane.add(textArea);
		
		btnInserir = new JButton("Insert");
		btnInserir.setBounds(50, 335, 89, 23);
		contentPane.add(btnInserir);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(313, 335, 89, 23);
		contentPane.add(btnCancel);
	}
}
