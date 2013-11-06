package br.edu.ifsp.pds.shadowstruggles.tools.view.modifiers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import br.edu.ifsp.pds.shadowstruggles.tools.model.modifiers.AmountModifier;
import br.edu.ifsp.pds.shadowstruggles.tools.model.modifiers.AmountModifier.ModifiedAttribute;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AmountModifierEditor extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Amount;
	public JButton btnAddModifier;
	private AmountModifier modifier;
	private JComboBox comboBox_Atributte;
	private JCheckBox chckbxReplace;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AmountModifierEditor frame = new AmountModifierEditor();
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
	public AmountModifierEditor() {
		modifier= new AmountModifier();
		setVisible(true);
		setTitle("Amount Modifier Editor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 328, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAtributeToModify = new JLabel("Atribute to Modify:");
		lblAtributeToModify.setBounds(22, 11, 96, 14);
		contentPane.add(lblAtributeToModify);
		
		comboBox_Atributte = new JComboBox();
		comboBox_Atributte.setModel(new DefaultComboBoxModel(ModifiedAttribute.values()));
		comboBox_Atributte.setBounds(128, 8, 147, 20);
		contentPane.add(comboBox_Atributte);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(22, 41, 46, 14);
		contentPane.add(lblAmount);
		
		chckbxReplace = new JCheckBox("Replace");
		chckbxReplace.setBounds(22, 72, 97, 23);
		contentPane.add(chckbxReplace);
		
		JLabel lblText = new JLabel("Text:");
		lblText.setBounds(22, 106, 46, 14);
		contentPane.add(lblText);
		
		textField_Amount = new JTextField();
		textField_Amount.setBounds(128, 38, 147, 20);
		contentPane.add(textField_Amount);
		textField_Amount.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(128, 118, 147, 92);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		btnAddModifier = new JButton("Add Modifier");
		btnAddModifier.setBounds(22, 227, 124, 41);
		contentPane.add(btnAddModifier);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(179, 227, 96, 41);
		contentPane.add(btnCancel);
	}
	
	public void buildModifier(){
		modifier.amount= Integer.parseInt(textField_Amount.getText());
		modifier.attribute=(ModifiedAttribute)comboBox_Atributte.getSelectedItem();
		modifier.text=textArea.getText();
		modifier.replace=chckbxReplace.isSelected();
	}
	
	public AmountModifier getModifier() {
		return modifier;
	}
}
