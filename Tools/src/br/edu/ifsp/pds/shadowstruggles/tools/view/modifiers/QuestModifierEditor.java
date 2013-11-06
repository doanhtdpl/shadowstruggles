package br.edu.ifsp.pds.shadowstruggles.tools.view.modifiers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest.QuestStatus;
import br.edu.ifsp.pds.shadowstruggles.tools.model.modifiers.QuestModifier.OperationType;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QuestModifierEditor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuestModifierEditor frame = new QuestModifierEditor();
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
	public QuestModifierEditor() {
		setTitle("Quest Modifier Editor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblQuest = new JLabel("Quest:");
		lblQuest.setBounds(31, 11, 46, 14);
		contentPane.add(lblQuest);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(120, 8, 134, 20);
		contentPane.add(comboBox);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(31, 46, 46, 14);
		contentPane.add(lblStatus);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(QuestStatus.values()));
		comboBox_1.setBounds(120, 43, 134, 20);
		contentPane.add(comboBox_1);
		
		JLabel lblOperationType = new JLabel("Operation Type:");
		lblOperationType.setBounds(31, 88, 79, 14);
		contentPane.add(lblOperationType);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(OperationType.values()));
		comboBox_2.setBounds(120, 85, 134, 20);
		contentPane.add(comboBox_2);
		
		JLabel lblRequirementToChange = new JLabel("Requirement to Change:");
		lblRequirementToChange.setBounds(31, 135, 118, 14);
		contentPane.add(lblRequirementToChange);
		
		textField = new JTextField();
		textField.setBounds(168, 132, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JCheckBox chckbxRequirementFullfilled = new JCheckBox("Requirement Fullfilled");
		chckbxRequirementFullfilled.setBounds(31, 166, 127, 23);
		contentPane.add(chckbxRequirementFullfilled);
		
		JButton btnAddModifier = new JButton("Add Modifier");
		btnAddModifier.setBounds(31, 207, 118, 43);
		contentPane.add(btnAddModifier);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(165, 207, 89, 43);
		contentPane.add(btnCancel);
	}
}
