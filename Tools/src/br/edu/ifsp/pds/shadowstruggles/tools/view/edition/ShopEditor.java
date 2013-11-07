package br.edu.ifsp.pds.shadowstruggles.tools.view.edition;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JButton;

import br.edu.ifsp.pds.shadowstruggles.tools.Controller;
import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;
import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Shop;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTextField;

public class ShopEditor extends JFrame {

	private JPanel contentPane;
	private HashMap<String, Item> items;	
	private JTextField textField;
	private JList list;
	private JComboBox comboBox;
	private Controller controller;
	private ArrayList<Item> shopItems;
	private Shop shop;

	/**
	 * Create the frame.
	 */
	public ShopEditor(Controller controller) {
		this.controller=controller;
		this.shop= new Shop();
		this.shopItems= new ArrayList<Item>();
		this.items= new HashMap<String, Item>();
		setVisible(true);
		setTitle("Shop Editor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 361, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblShopId = new JLabel("Shop ID:");
		lblShopId.setBounds(27, 11, 46, 14);
		contentPane.add(lblShopId);
		
		JLabel lblItemList = new JLabel("Item List:");
		lblItemList.setBounds(27, 106, 46, 14);
		contentPane.add(lblItemList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 120, 294, 158);
		contentPane.add(scrollPane);
		
		list = new JList();
		scrollPane.setViewportView(list);
		
		JPanel panel = new JPanel();
		panel.setBounds(27, 36, 294, 59);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblItemToAdd = new JLabel("Item to Add:");
		lblItemToAdd.setBounds(10, 3, 68, 14);
		panel.add(lblItemToAdd);
		
		
		comboBox = new JComboBox();
		ArrayList<Item> itemsArray = controller.getItems();		
		ArrayList<String> itemNames= new ArrayList<String>();
		for(Item item: itemsArray){
			items.put(item.name, item);
			itemNames.add(item.name);
		}
		if (itemsArray!= null) {			
			comboBox.setModel(new DefaultComboBoxModel(itemNames
					.toArray()));
		}
		comboBox.setBounds(114, 0, 170, 20);
		panel.add(comboBox);
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shopItems.add(items.get(comboBox.getSelectedItem().toString()));
				updateList();
			}
		});
		btnAddItem.setBounds(10, 28, 89, 23);
		panel.add(btnAddItem);
		
		JButton btnAddShop = new JButton("Add Shop");
		btnAddShop.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				buildShop();
				try {
					getController().createShop(shop);
					getController().updateTableToShop();
					dispose();
				} catch (IOException e1) {					
					e1.printStackTrace();
				}
			}
		});
		btnAddShop.setBounds(27, 286, 111, 41);
		contentPane.add(btnAddShop);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(221, 289, 100, 38);
		contentPane.add(btnCancel);
		
		textField = new JTextField();
		textField.setBounds(83, 8, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}
	
	private void updateList() {
		DefaultListModel model = new DefaultListModel();
		for (Item item : shopItems) {
			model.addElement(item);
		}
		list.setModel(model);
		list.updateUI();
	}
	
	public void buildShop(){
		this.shop.id=Integer.parseInt(textField.getText());
		this.shop.items=shopItems;
	}
	
	public Controller getController() {
		return controller;
	}
}
