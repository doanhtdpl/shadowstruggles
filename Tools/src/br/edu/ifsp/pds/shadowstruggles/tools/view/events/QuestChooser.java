package br.edu.ifsp.pds.shadowstruggles.tools.view.events;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest;
import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Requirement;
import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest.QuestStatus;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;

import br.edu.ifsp.pds.shadowstruggles.tools.model.profiles.conditions.QuestCondition;
import br.edu.ifsp.pds.shadowstruggles.tools.model.profiles.conditions.QuestCondition.EvaluationType;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class QuestChooser extends JFrame {

	private JPanel contentPane;
	private EventActionEditor previousScreen;
	private JComboBox comboBox;
	private JTextField textField;
	private JComboBox comboBox_1;
	private JTextArea textArea;
	private JCheckBox chckbxRequirementFulfilled;
	private JComboBox comboBox_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuestChooser frame = new QuestChooser(null);
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
	public QuestChooser(EventActionEditor previousScreen) {
		this.previousScreen=previousScreen;
		setTitle("Quest Chooser");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChooseTheQuest = new JLabel("Choose the Quest:");
		lblChooseTheQuest.setBounds(39, 22, 101, 14);
		contentPane.add(lblChooseTheQuest);
		
		ArrayList<Quest> quests = previousScreen.getController().getQuests();
		comboBox = new JComboBox(new DefaultComboBoxModel(quests.toArray()));
		comboBox.setBounds(177, 19, 149, 20);
		contentPane.add(comboBox);
		
		JLabel lblConditionalStatus = new JLabel("Conditional Status:");
		lblConditionalStatus.setBounds(39, 58, 101, 14);
		contentPane.add(lblConditionalStatus);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(QuestStatus.values()));
		comboBox_1.setBounds(177, 55, 149, 20);
		contentPane.add(comboBox_1);
		
		JLabel lblRequirimentName = new JLabel("Requirement name:");
		lblRequirimentName.setBounds(39, 94, 101, 14);
		contentPane.add(lblRequirimentName);
		
		textField = new JTextField();
		textField.setBounds(177, 91, 149, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblRequirementDescription = new JLabel("Requirement Description:");
		lblRequirementDescription.setBounds(39, 136, 121, 14);
		contentPane.add(lblRequirementDescription);
		
		textArea = new JTextArea();
		textArea.setBounds(177, 131, 149, 53);
		contentPane.add(textArea);
		
		chckbxRequirementFulfilled = new JCheckBox("Requirement fulfilled");
		chckbxRequirementFulfilled.setBounds(177, 194, 149, 23);
		contentPane.add(chckbxRequirementFulfilled);
		
		JLabel lblEvaluationType = new JLabel("Evaluation Type:");
		lblEvaluationType.setBounds(39, 231, 121, 14);
		contentPane.add(lblEvaluationType);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(EvaluationType.values()));
		comboBox_2.setBounds(177, 224, 149, 20);
		contentPane.add(comboBox_2);
		
		JButton btnNewButton = new JButton("Add Condition");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Quest quest = (Quest)comboBox.getSelectedItem();
				Requirement requirement = null;
				//TODO: create requirement Chooser
				boolean fullfilled = chckbxRequirementFulfilled.isSelected();
				QuestCondition condition = new QuestCondition();
				condition.quest=quest;
				condition.requirement=requirement;
				condition.requirementFulfilled=fullfilled;
				condition.questStatus=(QuestStatus)comboBox_1.getSelectedItem();
				condition.type=(EvaluationType)comboBox_2.getSelectedItem();
				getPreviousScreen().addCondition(condition);
			}
		});
		btnNewButton.setBounds(39, 270, 137, 40);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(216, 270, 113, 40);
		contentPane.add(btnNewButton_1);
	}
	
	public EventActionEditor getPreviousScreen() {
		return previousScreen;
	}
}
