package br.edu.ifsp.pds.shadowstruggles.tools.view.edition;

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

import br.edu.ifsp.pds.shadowstruggles.tools.Controller;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.CardAction;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Fighter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JList;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Fighter.FighterSize;

public class FighterEditor extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField textFieldBuyCost;
	private JLabel lblNewLabel;
	private JCheckBox chckbxSellable;
	private JButton btnInsert;
	private JButton btnCancel;
	private JTextField textFieldName;
	private JCheckBox chckbxAvailableInShop;
	private Controller controller;
	private Fighter fighter;
	private JTextField textFieldId;
	private JTextField textFieldLocName;
	private JTextField textFieldSellCost;
	private JTextField textFieldIcon;
	private JTextField textFieldEnergyCost;
	private JTextField textFieldRequisites;	
	private JTextField textFieldHP;
	private JTextField textFieldDamage;
	private JTextField textFieldRange;
	private JTextArea textArea;

	public FighterEditor(Controller controller, Fighter fighter) {
		this.controller = controller;
		setVisible(true);
		setTitle("Fighter Editor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 613);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.fighter = fighter;

		btnInsert = new JButton("Insert");
		btnInsert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {		

			}
		});
		btnInsert.setBounds(36, 516, 119, 42);
		contentPane.add(btnInsert);

		btnCancel = new JButton("Cancel");		
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();				
			}
		});
		btnCancel.setBounds(311, 516, 106, 42);
		contentPane.add(btnCancel);

		JPanel panel = new JPanel();
		panel.setBounds(36, 11, 381, 192);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 36, 46, 14);
		panel.add(lblName);
		lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel.add(lblNewLabel);

		JLabel lblLocalizedName = new JLabel("Localized Name:");
		lblLocalizedName.setBounds(10, 61, 84, 14);
		panel.add(lblLocalizedName);

		textFieldId = new JTextField();
		textFieldId.setBounds(109, 8, 86, 20);
		panel.add(textFieldId);
		textFieldId.setColumns(10);

		textFieldName = new JTextField();
		textFieldName.setBounds(109, 33, 130, 20);
		panel.add(textFieldName);
		textFieldName.setColumns(10);

		textFieldLocName = new JTextField();
		textFieldLocName.setBounds(109, 58, 130, 20);
		panel.add(textFieldLocName);
		textFieldLocName.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Buy Cost:");
		lblNewLabel_1.setBounds(259, 8, 62, 14);
		panel.add(lblNewLabel_1);

		textFieldBuyCost = new JTextField();
		textFieldBuyCost.setBounds(331, 5, 40, 20);
		panel.add(textFieldBuyCost);
		textFieldBuyCost.setColumns(10);

		JLabel lblSellCost = new JLabel("Sell Cost:");
		lblSellCost.setBounds(259, 61, 46, 14);
		panel.add(lblSellCost);

		chckbxSellable = new JCheckBox("Sellable");
		chckbxSellable.setBounds(249, 32, 72, 23);
		panel.add(chckbxSellable);

		textFieldSellCost = new JTextField();
		textFieldSellCost.setBounds(331, 58, 40, 20);
		panel.add(textFieldSellCost);
		textFieldSellCost.setColumns(10);

		JLabel lblDescription_1 = new JLabel("Description");
		lblDescription_1.setBounds(10, 86, 72, 14);
		panel.add(lblDescription_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 108, 211, 44);
		panel.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		chckbxAvailableInShop = new JCheckBox("Available in Main Shop");
		chckbxAvailableInShop.setBounds(238, 97, 137, 23);
		panel.add(chckbxAvailableInShop);

		JLabel lblIcon_1 = new JLabel("Icon:");
		lblIcon_1.setBounds(10, 163, 46, 14);
		panel.add(lblIcon_1);

		textFieldIcon = new JTextField();
		textFieldIcon.setBounds(66, 163, 155, 20);
		panel.add(textFieldIcon);
		textFieldIcon.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(36, 214, 381, 162);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblEnergyCost = new JLabel("Energy Cost:");
		lblEnergyCost.setBounds(10, 11, 63, 14);
		panel_1.add(lblEnergyCost);

		textFieldEnergyCost = new JTextField();
		textFieldEnergyCost.setBounds(97, 8, 46, 20);
		panel_1.add(textFieldEnergyCost);
		textFieldEnergyCost.setColumns(10);

		JLabel lblAction = new JLabel("Action:");
		lblAction.setBounds(10, 43, 46, 14);
		panel_1.add(lblAction);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(97, 39, 118, 20);
		panel_1.add(comboBox);

		JButton btnNewAction = new JButton("New Action");
		btnNewAction.setBounds(237, 39, 106, 23);
		panel_1.add(btnNewAction);

		JLabel lblPreRequisites_1 = new JLabel("Pre requisites:");
		lblPreRequisites_1.setBounds(10, 71, 69, 14);
		panel_1.add(lblPreRequisites_1);

		textFieldRequisites = new JTextField();
		textFieldRequisites.setBounds(97, 70, 118, 20);
		panel_1.add(textFieldRequisites);
		textFieldRequisites.setColumns(10);

		JButton btnAddRequisite = new JButton("Add Requisite");
		btnAddRequisite.setBounds(237, 67, 106, 23);
		panel_1.add(btnAddRequisite);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 102, 205, 49);
		panel_1.add(scrollPane_1);
		
		JList list = new JList();
		scrollPane_1.setViewportView(list);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(36, 387, 381, 118);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblFighterSize = new JLabel("Fighter Size:");
		lblFighterSize.setBounds(10, 11, 60, 14);
		panel_2.add(lblFighterSize);

		JComboBox comboBoxSize = new JComboBox();
		comboBoxSize.setModel(new DefaultComboBoxModel(FighterSize.values()));
		comboBoxSize.setBounds(94, 8, 86, 20);
		panel_2.add(comboBoxSize);

		JLabel lblMaxHp = new JLabel("Max HP:");
		lblMaxHp.setBounds(10, 36, 46, 14);
		panel_2.add(lblMaxHp);

		textFieldHP = new JTextField();
		textFieldHP.setBounds(94, 33, 86, 20);
		panel_2.add(textFieldHP);
		textFieldHP.setColumns(10);

		JLabel lblDamage = new JLabel("Damage:");
		lblDamage.setBounds(251, 11, 46, 14);
		panel_2.add(lblDamage);

		textFieldDamage = new JTextField();
		textFieldDamage.setBounds(307, 8, 46, 20);
		panel_2.add(textFieldDamage);
		textFieldDamage.setColumns(10);
		
		JLabel lblRange = new JLabel("Range:");
		lblRange.setBounds(251, 36, 46, 14);
		panel_2.add(lblRange);
		
		textFieldRange = new JTextField();
		textFieldRange.setBounds(307, 33, 46, 20);
		panel_2.add(textFieldRange);
		textFieldRange.setColumns(10);
		
		JLabel lblMovSpeed = new JLabel("Mov. Speed:");
		lblMovSpeed.setBounds(10, 61, 76, 14);
		panel_2.add(lblMovSpeed);
		
		JComboBox comboBoxMovSpeed = new JComboBox();
		comboBoxMovSpeed.setModel(new DefaultComboBoxModel(new String[] {"SLOW", "NORMAL", "FAST"}));
		comboBoxMovSpeed.setBounds(94, 58, 86, 20);
		panel_2.add(comboBoxMovSpeed);
		
		JLabel lblAtkSpeed = new JLabel("Atk. Speed:");
		lblAtkSpeed.setBounds(10, 86, 60, 14);
		panel_2.add(lblAtkSpeed);
		
		JComboBox comboBoxAtkSpeed = new JComboBox();
		comboBoxAtkSpeed.setModel(new DefaultComboBoxModel(new String[] {"SLOW", "NORMAL", "FAST"}));
		comboBoxAtkSpeed.setBounds(94, 83, 86, 20);
		panel_2.add(comboBoxAtkSpeed);
		
		JCheckBox chckbxHasEffect = new JCheckBox("Has Effect");
		chckbxHasEffect.setBounds(251, 57, 97, 23);
		panel_2.add(chckbxHasEffect);
		if (this.fighter == null)
			this.fighter = new Fighter();
		else fillFields();
	}

	public void buildFighter() {
		fighter.id=Integer.parseInt(textFieldId.getText());
		fighter.name=textFieldName.getText();
		fighter.localizedName=textFieldLocName.getText();
		fighter.buyCost=Integer.parseInt(textFieldBuyCost.getText());
		fighter.sellCost=Integer.parseInt(textFieldSellCost.getText());
		fighter.sellable=chckbxSellable.isSelected();
		fighter.description=textArea.getText();
		fighter.icon=textFieldIcon.getText();
		fighter.availableInMainShop=chckbxAvailableInShop.isSelected();
		fighter.consumable=false;
		fighter.energyCost=Integer.parseInt(textFieldEnergyCost.getText());
		
	}

	public void saveFighter(Fighter fighter) {
		try {
			controller.createFighter(fighter);
			controller.updateTableToFighter();
			this.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void fillFields(){
		
	}
}
