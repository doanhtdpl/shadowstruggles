package br.edu.ifsp.pds.shadowstruggles.tools.view.edition;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;

import br.edu.ifsp.pds.shadowstruggles.tools.Controller;
import br.edu.ifsp.pds.shadowstruggles.tools.model.BattleMap;
import br.edu.ifsp.pds.shadowstruggles.tools.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.tools.model.DefaultRules;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Enemy;
import br.edu.ifsp.pds.shadowstruggles.tools.model.modifiers.AmountModifier;
import br.edu.ifsp.pds.shadowstruggles.tools.model.modifiers.Modifier;
import br.edu.ifsp.pds.shadowstruggles.tools.view.modifiers.AmountModifierEditor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BattleEditor extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField textField_Id;
	private JTextField textField_MapId;
	private JTextField textField_MapName;
	private BattlePlatform battle;
	private Controller controller;
	private JCheckBox chckbxTutorial;
	private JList list;
	private JComboBox comboBox_Enemies;
	private JComboBox comboBox_RewardType;
	private HashMap<String, Enemy> enemies;

	public BattleEditor(Controller controller) {
		this.battle = new BattlePlatform();
		this.enemies= new HashMap<String,Enemy>();
		battle.rewards = new ArrayList<Modifier>();
		this.controller = controller;
		setTitle("Battle Editor");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 283);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(24, 11, 46, 14);
		contentPane.add(lblId);

		JLabel lblEnemie = new JLabel("Enemie:");
		lblEnemie.setBounds(226, 11, 46, 14);
		contentPane.add(lblEnemie);

		JLabel lblMapName = new JLabel("Map Name:");
		lblMapName.setBounds(24, 82, 73, 14);
		contentPane.add(lblMapName);

		textField_Id = new JTextField();
		textField_Id.setBounds(97, 8, 103, 20);
		contentPane.add(textField_Id);
		textField_Id.setColumns(10);

		comboBox_Enemies = new JComboBox();		
		ArrayList<Enemy> enemiesArray = controller.getEnemies();
		System.out.println("Enemy quantity: "+enemiesArray.size());
		ArrayList<String> enemiesNames= new ArrayList<String>();
		for(Enemy enemy: enemiesArray){
			enemies.put(enemy.name, enemy);
			enemiesNames.add(enemy.name);
		}
		if (enemiesArray!= null) {			
			comboBox_Enemies.setModel(new DefaultComboBoxModel(enemiesNames
					.toArray()));
		}
		comboBox_Enemies.setBounds(298, 8, 126, 20);
		contentPane.add(comboBox_Enemies);

		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buildBattle();
				try {
					getController().createBattle(battle);
					getController().updateTableToBattles();
					dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnInsert.setBounds(24, 191, 176, 36);
		contentPane.add(btnInsert);

		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(226, 191, 198, 36);
		contentPane.add(btnNewButton);

		chckbxTutorial = new JCheckBox("Tutorial");
		chckbxTutorial.setBounds(24, 106, 97, 23);
		contentPane.add(chckbxTutorial);

		JLabel lblMapId = new JLabel("Map ID:");
		lblMapId.setBounds(24, 46, 46, 14);
		contentPane.add(lblMapId);

		textField_MapId = new JTextField();
		textField_MapId.setBounds(97, 43, 103, 20);
		contentPane.add(textField_MapId);
		textField_MapId.setColumns(10);

		textField_MapName = new JTextField();
		textField_MapName.setText("");
		textField_MapName.setBounds(97, 79, 103, 20);
		contentPane.add(textField_MapName);
		textField_MapName.setColumns(10);

		JLabel lblRewards = new JLabel("Rewards:");
		lblRewards.setBounds(226, 46, 46, 14);
		contentPane.add(lblRewards);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(226, 71, 198, 82);
		contentPane.add(scrollPane);

		list = new JList();
		scrollPane.setViewportView(list);

		JButton btnAddReward = new JButton("Add Reward");
		btnAddReward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String modifierType = comboBox_RewardType.getSelectedItem()
						.toString();
				switch (modifierType) {
				case "Attribute":
					final AmountModifierEditor modifierEditor = new AmountModifierEditor();
					modifierEditor.btnAddModifier
							.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									modifierEditor.buildModifier();
									addReward(modifierEditor.getModifier());
									modifierEditor.dispose();
								}
							});

				}
			}
		});
		btnAddReward.setBounds(327, 157, 97, 23);
		contentPane.add(btnAddReward);

		comboBox_RewardType = new JComboBox();
		comboBox_RewardType.setModel(new DefaultComboBoxModel(new String[] {
				"Item", "Attribute", "Quest", "Unlock", "" }));
		comboBox_RewardType.setBounds(226, 158, 91, 20);
		contentPane.add(comboBox_RewardType);
	}

	public void buildBattle() {
		battle.id = Integer.parseInt(textField_Id.getText());
		battle.map = new BattleMap(Integer.parseInt(textField_MapId.getText()),
				textField_MapName.getText());
		battle.opponent = getSelectedEnemy();
		battle.rules = new DefaultRules();
		battle.tutorial = chckbxTutorial.isSelected();
	}
	
	private Enemy getSelectedEnemy(){
		Enemy enemy = enemies.get(comboBox_Enemies.getSelectedItem().toString());
		return enemy;
	}

	public Controller getController() {
		return controller;
	}

	public void addReward(Modifier modifier) {
		battle.rewards.add(modifier);
		updateList();
	}

	private void updateList() {
		DefaultListModel model = new DefaultListModel();
		for (Modifier modifier : battle.rewards) {
			model.addElement(modifier);
		}
		list.setModel(model);
		list.updateUI();
	}

}
