package br.edu.ifsp.pds.shasdowstruggles.view.edition;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class FighterEditor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel lblNewLabel;
	private JTextArea textArea;
	private JCheckBox chckbxSellable;
	private JComboBox comboBox_3;
	private JComboBox comboBox_4;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	private JTextArea textArea_1;
	private JButton btnInsert;
	private JButton btnCancel;
	private JLabel lblNameVisualization;
	private JTextField textField_5;
	private JLabel lblHp;
	private JTextField textField_6;
	private JLabel lblRange;
	private JTextField textField_7;
	private JLabel lblSize;
	private JComboBox comboBox_5;
	private JCheckBox chckbxHasEffect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FighterEditor frame = new FighterEditor();
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
	public FighterEditor() {
		setTitle("Fighter Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(32, 24, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(210, 24, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(32, 222, 72, 14);
		contentPane.add(lblDescription);
		
		JLabel lblNewLabel_1 = new JLabel("Buy Cost");
		lblNewLabel_1.setBounds(261, 269, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		chckbxSellable = new JCheckBox("Sellable");
		chckbxSellable.setBounds(149, 265, 97, 23);
		contentPane.add(chckbxSellable);
		
		JCheckBox chckbxAvailableInShop = new JCheckBox("Available in Shop");
		chckbxAvailableInShop.setBounds(32, 265, 138, 23);
		contentPane.add(chckbxAvailableInShop);
		
		JLabel lblEnergyCost = new JLabel("Energy Cost");
		lblEnergyCost.setBounds(32, 313, 72, 14);
		contentPane.add(lblEnergyCost);
		
		JLabel lblIcon = new JLabel("Icon");
		lblIcon.setBounds(32, 350, 46, 14);
		contentPane.add(lblIcon);
		
		JLabel lblAction = new JLabel("Action");
		lblAction.setBounds(32, 375, 46, 14);
		contentPane.add(lblAction);
		
		JLabel lblIllustration = new JLabel("Illustration");
		lblIllustration.setBounds(32, 409, 84, 14);
		contentPane.add(lblIllustration);
		
		JLabel lblPreRequisites = new JLabel("Pre Requisites");
		lblPreRequisites.setBounds(32, 455, 84, 14);
		contentPane.add(lblPreRequisites);
		
		textField = new JTextField();
		textField.setBounds(64, 21, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(266, 21, 147, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setBounds(98, 217, 315, 33);
		contentPane.add(textArea);
		
		textField_2 = new JTextField();
		textField_2.setBounds(317, 266, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(114, 310, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setBounds(114, 347, 193, 20);
		contentPane.add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(114, 378, 193, 20);
		contentPane.add(comboBox_1);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(114, 406, 193, 20);
		contentPane.add(comboBox_2);
		
		textArea_1 = new JTextArea();
		textArea_1.setBounds(114, 450, 232, 51);
		contentPane.add(textArea_1);
		
		btnInsert = new JButton("Insert");
		btnInsert.setBounds(27, 528, 89, 23);
		contentPane.add(btnInsert);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(257, 528, 89, 23);
		contentPane.add(btnCancel);
		
		JLabel lblAttack = new JLabel("Damage");
		lblAttack.setBounds(32, 84, 72, 14);
		contentPane.add(lblAttack);
		
		textField_4 = new JTextField();
		textField_4.setBounds(114, 81, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblAttackSpeed = new JLabel("Attack Speed");
		lblAttackSpeed.setBounds(28, 115, 76, 14);
		contentPane.add(lblAttackSpeed);
		
		comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"SLOW", "NORMAL", "FAST"}));
		comboBox_3.setBounds(114, 112, 86, 20);
		contentPane.add(comboBox_3);
		
		JLabel lblMovSpeed = new JLabel("Mov. Speed");
		lblMovSpeed.setBounds(261, 109, 64, 14);
		contentPane.add(lblMovSpeed);
		
		comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"SLOW", "NORMAL", "FAST"}));
		comboBox_4.setBounds(335, 106, 86, 20);
		contentPane.add(comboBox_4);
		
		lblNameVisualization = new JLabel("Name Visualization");
		lblNameVisualization.setBounds(32, 49, 105, 14);
		contentPane.add(lblNameVisualization);
		
		textField_5 = new JTextField();
		textField_5.setBounds(149, 49, 86, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		lblHp = new JLabel("HP");
		lblHp.setBounds(276, 52, 46, 14);
		contentPane.add(lblHp);
		
		textField_6 = new JTextField();
		textField_6.setBounds(327, 52, 86, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		lblRange = new JLabel("Range");
		lblRange.setBounds(32, 147, 46, 14);
		contentPane.add(lblRange);
		
		textField_7 = new JTextField();
		textField_7.setBounds(114, 144, 86, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		lblSize = new JLabel("Size");
		lblSize.setBounds(261, 147, 46, 14);
		contentPane.add(lblSize);
		
		comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"SMALL", "MEDIUM", "LARGE"}));
		comboBox_5.setBounds(335, 137, 78, 20);
		contentPane.add(comboBox_5);
		
		chckbxHasEffect = new JCheckBox("Has Effect");
		chckbxHasEffect.setBounds(32, 182, 97, 23);
		contentPane.add(chckbxHasEffect);
	}
}
