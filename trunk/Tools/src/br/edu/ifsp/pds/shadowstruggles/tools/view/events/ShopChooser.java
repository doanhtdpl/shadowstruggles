package br.edu.ifsp.pds.shadowstruggles.tools.view.events;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import br.edu.ifsp.pds.shadowstruggles.tools.Controller;
import br.edu.ifsp.pds.shadowstruggles.tools.model.events.ShopAction;
import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Shop;
import br.edu.ifsp.pds.shadowstruggles.tools.view.edition.ShopEditor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class ShopChooser extends JFrame {

	private JPanel contentPane;
	private EventActionEditor previousScreen;
	private JComboBox comboBox;
	private HashMap<Integer, Shop> shopMap;
	

	

	/**
	 * Create the frame.
	 */
	public ShopChooser(EventActionEditor previousScreen) {
		this.previousScreen=previousScreen;
		this.shopMap= new HashMap<Integer, Shop>();
		setTitle("Shop Chooser");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 181);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChooseAShop = new JLabel("Choose a Shop:");
		lblChooseAShop.setBounds(40, 24, 92, 14);
		contentPane.add(lblChooseAShop);
		
		comboBox = new JComboBox();
		fillComboBox();
		comboBox.setBounds(170, 21, 140, 20);
		contentPane.add(comboBox);
		
		JButton btnNewShop = new JButton("New Shop");
		btnNewShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ShopEditor(getController());
			}
		});
		btnNewShop.setBounds(335, 20, 89, 23);
		contentPane.add(btnNewShop);
		
		JButton btnChooseShop = new JButton("Choose Shop");
		btnChooseShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Shop shop = shopMap.get(Integer.parseInt(comboBox.getSelectedItem().toString()));
				getPreviousScreen().setAction(new ShopAction(shop));
				dispose();
			}
		});
		btnChooseShop.setBounds(43, 72, 111, 40);
		contentPane.add(btnChooseShop);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(335, 72, 89, 40);
		contentPane.add(btnCancel);
	}
	
	public Controller getController(){
		return previousScreen.getController();
	}
	
	private void fillComboBox(){		
		ArrayList<Shop> shopArray = getController().getShops();
		ArrayList<Integer> shopIds= new ArrayList<Integer>();
		for(Shop shop: shopArray){
			shopMap.put(shop.id, shop);
			shopIds.add(shop.id);
		}	
		
		
		if (shopArray!= null) {			
			comboBox.setModel(new DefaultComboBoxModel(shopIds
					.toArray()));
		}
	}
	
	public EventActionEditor getPreviousScreen() {
		return previousScreen;
	}
	

}
