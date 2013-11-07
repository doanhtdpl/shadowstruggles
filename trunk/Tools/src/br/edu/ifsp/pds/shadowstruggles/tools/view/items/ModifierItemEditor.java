package br.edu.ifsp.pds.shadowstruggles.tools.view.items;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.edu.ifsp.pds.shadowstruggles.tools.Controller;
import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;
import br.edu.ifsp.pds.shadowstruggles.tools.model.items.ModifierItem;
import br.edu.ifsp.pds.shadowstruggles.tools.model.modifiers.AmountModifier;
import br.edu.ifsp.pds.shadowstruggles.tools.view.modifiers.AmountModifierEditor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class ModifierItemEditor extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Modifier;
	private JTextField textField_Id;
	private JTextField textField_Name;
	private JTextField textField_LName;
	private JTextField textField_BCost;
	private JTextField textField_SCost;
	private JTextField textField_Icon;
	private JCheckBox chckbxSellable;
	private JCheckBox chckbxAvailableInMain;
	private JCheckBox chckbxConsumable;
	private JComboBox comboBox;
	private JTextArea textArea;
	private ModifierItem item;
	private Controller controller;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifierItemEditor frame = new ModifierItemEditor(null);
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
	public ModifierItemEditor(Controller controller) {
		setVisible(true);
		this.controller=controller;
		this.item= new ModifierItem();
		setTitle("Modifier Item Editor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(22, 11, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(22, 36, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblLocalizedName = new JLabel("Localized Name:");
		lblLocalizedName.setBounds(22, 61, 84, 14);
		contentPane.add(lblLocalizedName);
		
		JLabel lblBuyCost = new JLabel("Buy Cost:");
		lblBuyCost.setBounds(22, 86, 62, 14);
		contentPane.add(lblBuyCost);
		
		JLabel lblSellCost = new JLabel("Sell Cost:");
		lblSellCost.setBounds(22, 121, 46, 14);
		contentPane.add(lblSellCost);
		
		chckbxSellable = new JCheckBox("Sellable");
		chckbxSellable.setBounds(22, 142, 97, 23);
		contentPane.add(chckbxSellable);
		
		JLabel lblIcon = new JLabel("Icon:");
		lblIcon.setBounds(22, 182, 46, 14);
		contentPane.add(lblIcon);
		
		chckbxAvailableInMain = new JCheckBox("Available in Main Shop");
		chckbxAvailableInMain.setBounds(22, 202, 136, 23);
		contentPane.add(chckbxAvailableInMain);
		
		chckbxConsumable = new JCheckBox("Consumable");
		chckbxConsumable.setBounds(22, 228, 97, 23);
		contentPane.add(chckbxConsumable);
		
		JLabel lblModifierType = new JLabel("Modifier type:");
		lblModifierType.setBounds(22, 273, 76, 14);
		contentPane.add(lblModifierType);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Attribute"}));
		comboBox.setBounds(108, 270, 122, 20);
		contentPane.add(comboBox);
		
		JButton btnNewModifier = new JButton("New Modifier");
		btnNewModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String modifierType = comboBox.getSelectedItem().toString();
				switch(modifierType){
				case "Attribute":final AmountModifierEditor modifierEditor=new AmountModifierEditor();
				modifierEditor.btnAddModifier.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						modifierEditor.buildModifier();
						item.modifier=modifierEditor.getModifier();
						textField_Modifier.setText(item.modifier.toString());
						modifierEditor.dispose();						
					}
				});
				}
			}
		});
		btnNewModifier.setBounds(256, 269, 112, 23);
		contentPane.add(btnNewModifier);
		
		JLabel lblModifier = new JLabel("Modifier:");
		lblModifier.setBounds(22, 298, 46, 14);
		contentPane.add(lblModifier);
		
		textField_Modifier = new JTextField();
		textField_Modifier.setEditable(false);
		textField_Modifier.setBounds(108, 295, 260, 20);
		contentPane.add(textField_Modifier);
		textField_Modifier.setColumns(10);
		
		textField_Id = new JTextField();
		textField_Id.setBounds(108, 8, 97, 20);
		contentPane.add(textField_Id);
		textField_Id.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(266, 11, 76, 14);
		contentPane.add(lblDescription);
		
		textField_Name = new JTextField();
		textField_Name.setBounds(108, 33, 122, 20);
		contentPane.add(textField_Name);
		textField_Name.setColumns(10);
		
		textField_LName = new JTextField();
		textField_LName.setBounds(108, 58, 122, 20);
		contentPane.add(textField_LName);
		textField_LName.setColumns(10);
		
		textField_BCost = new JTextField();
		textField_BCost.setBounds(108, 83, 122, 20);
		contentPane.add(textField_BCost);
		textField_BCost.setColumns(10);
		
		textField_SCost = new JTextField();
		textField_SCost.setBounds(108, 118, 122, 20);
		contentPane.add(textField_SCost);
		textField_SCost.setColumns(10);
		
		textField_Icon = new JTextField();
		textField_Icon.setBounds(106, 179, 124, 20);
		contentPane.add(textField_Icon);
		textField_Icon.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(266, 48, 158, 87);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					getController().createItem(item);
					getController().updateTableToItems();
					dispose();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnAddItem.setBounds(22, 337, 136, 36);
		contentPane.add(btnAddItem);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(266, 337, 102, 36);
		contentPane.add(btnCancel);
	}
	
	public void buildItem(){
		item.id=Integer.parseInt(textField_Id.getText());
		item.name=textField_Name.getText();
		item.localizedName=textField_LName.getText();
		item.availableInMainShop=chckbxAvailableInMain.isSelected();
		item.buyCost=Integer.parseInt(textField_BCost.getText());
		item.sellCost= Integer.parseInt(textField_SCost.getText());
		item.sellable=chckbxSellable.isSelected();
		item.consumable=chckbxConsumable.isSelected();
		item.description=textArea.getText();
		item.icon=textField_Icon.getText();		
	}
	public Controller getController() {
		return controller;
	}
}
