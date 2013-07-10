package br.edu.ifsp.pds.shasdowstruggles.view.edition;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class SceneEditor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SceneEditor frame = new SceneEditor();
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
	public SceneEditor() {
		setTitle("Scene Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 247);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(20, 11, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(229, 11, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(20, 51, 63, 14);
		contentPane.add(lblDescription);
		
		JLabel lblEnding = new JLabel("Ending:");
		lblEnding.setBounds(20, 96, 46, 14);
		contentPane.add(lblEnding);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(93, 93, 182, 20);
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(92, 8, 127, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(285, 8, 139, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(93, 46, 331, 36);
		contentPane.add(textArea);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(20, 137, 199, 49);
		contentPane.add(btnInsert);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(251, 137, 173, 49);
		contentPane.add(btnCancel);
	}

}
