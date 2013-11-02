package br.edu.ifsp.pds.shadowstruggles.tools.view.events;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JList;

import br.edu.ifsp.pds.shadowstruggles.tools.Controller;
import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;
import br.edu.ifsp.pds.shadowstruggles.tools.model.profiles.conditions.ItemCondition;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class ItemConditionChooser extends JFrame {

	private JPanel contentPane;
	private EventActionEditor previousScreen;
	private JList list;
	private JComboBox comboBox;
	private ArrayList<Item> items;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemConditionChooser frame = new ItemConditionChooser(null);
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
	public ItemConditionChooser(EventActionEditor previousScreen) {
		this.items= new ArrayList<Item>();
		this.previousScreen=previousScreen;
		
		setTitle("ItemConditionChooser");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConditionalItems = new JLabel("Conditional Items:");
		lblConditionalItems.setBounds(35, 11, 100, 14);
		contentPane.add(lblConditionalItems);
		
		ArrayList<Item> items=previousScreen.getController().getItems();
		comboBox = new JComboBox(new DefaultComboBoxModel<>(items.toArray()));
		comboBox.setBounds(160, 8, 143, 20);
		contentPane.add(comboBox);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel model = (DefaultListModel)list.getModel();
				model.addElement(comboBox.getSelectedItem());
				getItems().add((Item)comboBox.getSelectedItem());
				list.updateUI();
				
			}
		});
		btnAdd.setBounds(323, 7, 64, 23);
		contentPane.add(btnAdd);
		
		list = new JList();
		list.setBounds(35, 63, 268, 130);
		contentPane.add(list);
		
		JButton btnAddCondition = new JButton("Add Condition");
		btnAddCondition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemCondition itemCondition= new ItemCondition();
				itemCondition.items= getItems();
				getPreviousScreen().addCondition(itemCondition);
			}
		});
		btnAddCondition.setBounds(35, 210, 143, 40);
		contentPane.add(btnAddCondition);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(304, 210, 120, 40);
		contentPane.add(btnCancel);
	}
	
	public EventActionEditor getPreviousScreen() {
		return previousScreen;
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}

}
