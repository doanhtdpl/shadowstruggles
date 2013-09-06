package br.edu.ifsp.pds.shadowstruggles.tools.view.edition;

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

public class EffectEditor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EffectEditor frame = new EffectEditor();
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
	public EffectEditor() {
		setVisible(true);
		setTitle("Effect Editor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 523, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(27, 11, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(284, 11, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblNameVisalization = new JLabel("Name Visalization:");
		lblNameVisalization.setBounds(27, 36, 104, 14);
		contentPane.add(lblNameVisalization);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(27, 61, 68, 14);
		contentPane.add(lblDescription);
		
		JLabel lblIllustration = new JLabel("Illustration");
		lblIllustration.setBounds(27, 121, 85, 14);
		contentPane.add(lblIllustration);
		
		JLabel lblEnergyCost = new JLabel("Energy Cost:");
		lblEnergyCost.setBounds(284, 36, 68, 14);
		contentPane.add(lblEnergyCost);
		
		JLabel lblIcon = new JLabel("Icon");
		lblIcon.setBounds(284, 121, 46, 14);
		contentPane.add(lblIcon);
		
		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setBounds(27, 146, 46, 14);
		contentPane.add(lblDuration);
		
		JCheckBox chckbxImediateEffect = new JCheckBox("Imediate Effect");
		chckbxImediateEffect.setBounds(281, 142, 123, 23);
		contentPane.add(chckbxImediateEffect);
		
		JLabel lblBuyCost = new JLabel("Buy Cost:");
		lblBuyCost.setBounds(27, 183, 68, 14);
		contentPane.add(lblBuyCost);
		
		JCheckBox chckbxSellable = new JCheckBox("Sellable");
		chckbxSellable.setBounds(284, 179, 97, 23);
		contentPane.add(chckbxSellable);
		
		JCheckBox chckbxAvailableInShop = new JCheckBox("Available in Shop");
		chckbxAvailableInShop.setBounds(377, 179, 120, 23);
		contentPane.add(chckbxAvailableInShop);
		
		JLabel lblNewLabel = new JLabel("Action");
		lblNewLabel.setBounds(27, 226, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPrerequisites = new JLabel("Pre-requisites:");
		lblPrerequisites.setBounds(27, 269, 85, 14);
		contentPane.add(lblPrerequisites);
		
		textField = new JTextField();
		textField.setBounds(130, 8, 131, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(130, 33, 131, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(382, 8, 115, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(382, 33, 115, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(130, 61, 367, 49);
		contentPane.add(textArea);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(128, 118, 133, 20);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(130, 223, 131, 20);
		contentPane.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(382, 118, 115, 20);
		contentPane.add(comboBox_2);
		
		textField_4 = new JTextField();
		textField_4.setBounds(130, 146, 131, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(130, 180, 131, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(130, 264, 367, 78);
		contentPane.add(textArea_1);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(23, 357, 202, 34);
		contentPane.add(btnInsert);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(310, 353, 187, 38);
		contentPane.add(btnCancel);
		
		JCheckBox chckbxOnFighter = new JCheckBox("On Fighter");
		chckbxOnFighter.setBounds(284, 222, 97, 23);
		contentPane.add(chckbxOnFighter);
	}
}
