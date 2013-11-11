package br.edu.ifsp.pds.shadowstruggles.tools.view.edition;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;

import br.edu.ifsp.pds.shadowstruggles.tools.Controller;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Card;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Deck;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Fighter;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class DeckEditor extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton btnAddDeck;
	private JButton btnCancel;
	private Deck deck;
	private Controller controller;
	private JTextField textFieldId;
	private JTextField textFieldName;
	private JTextField textFieldLocName;
	private JTextField textFieldIcon;
	private JTextField textFieldMinCap;
	private JTextField textFieldMaxCap;
	private JTextField textFieldCardPoints;
	private JTable table;
	private ArrayList<Card> cards;
	private HashMap<String, Card> cardMap;
	private JComboBox comboBox;
	private JTextArea textArea;

	public DeckEditor(Controller controller, Deck deck) {
		this.cards= new ArrayList<Card>();
		this.cardMap= new HashMap<String,Card>();
		setVisible(true);
		this.controller=controller;
		this.deck=deck;
		setTitle("Deck editor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 382, 670);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnAddDeck = new JButton("Add Deck");
		btnAddDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAddDeck.setBounds(21, 551, 110, 37);
		contentPane.add(btnAddDeck);
		btnAddDeck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				createDeck();
			}
		});

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(246, 551, 97, 36);
		btnCancel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		contentPane.add(btnCancel);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(21, 11, 322, 192);
		contentPane.add(panel);
		
		JLabel label = new JLabel("Name");
		label.setBounds(10, 36, 46, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("ID:");
		label_1.setBounds(10, 11, 46, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Localized Name:");
		label_2.setBounds(10, 61, 84, 14);
		panel.add(label_2);
		
		textFieldId = new JTextField();
		textFieldId.setColumns(10);
		textFieldId.setBounds(109, 8, 86, 20);
		panel.add(textFieldId);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(109, 33, 130, 20);
		panel.add(textFieldName);
		
		textFieldLocName = new JTextField();
		textFieldLocName.setColumns(10);
		textFieldLocName.setBounds(109, 58, 130, 20);
		panel.add(textFieldLocName);
		
		JLabel label_5 = new JLabel("Description");
		label_5.setBounds(10, 86, 72, 14);
		panel.add(label_5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 108, 211, 44);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel label_6 = new JLabel("Icon:");
		label_6.setBounds(10, 163, 46, 14);
		panel.add(label_6);
		
		textFieldIcon = new JTextField();
		textFieldIcon.setColumns(10);
		textFieldIcon.setBounds(66, 163, 155, 20);
		panel.add(textFieldIcon);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(21, 215, 322, 282);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblMinimumCapacity = new JLabel("Minimum Capacity:");
		lblMinimumCapacity.setBounds(10, 11, 90, 14);
		panel_1.add(lblMinimumCapacity);
		
		JLabel lblMaximumCapacity = new JLabel("Maximum Capacity:");
		lblMaximumCapacity.setBounds(159, 11, 102, 14);
		panel_1.add(lblMaximumCapacity);
		
		textFieldMinCap = new JTextField();
		textFieldMinCap.setBounds(102, 8, 42, 20);
		panel_1.add(textFieldMinCap);
		textFieldMinCap.setColumns(10);
		
		textFieldMaxCap = new JTextField();
		textFieldMaxCap.setBounds(259, 8, 42, 20);
		panel_1.add(textFieldMaxCap);
		textFieldMaxCap.setColumns(10);
		
		JLabel lblMaxCardPoints = new JLabel("Max Card Points:");
		lblMaxCardPoints.setBounds(10, 44, 90, 14);
		panel_1.add(lblMaxCardPoints);
		
		textFieldCardPoints = new JTextField();
		textFieldCardPoints.setBounds(112, 41, 42, 20);
		panel_1.add(textFieldCardPoints);
		textFieldCardPoints.setColumns(10);
		
		JLabel lblCards = new JLabel("Cards:");
		lblCards.setBounds(10, 77, 46, 14);
		panel_1.add(lblCards);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 102, 302, 139);
		panel_1.add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		
		comboBox = new JComboBox();
		fillComboBox();
		comboBox.setBounds(10, 252, 164, 20);
		panel_1.add(comboBox);
		
		JButton btnAddCard = new JButton("Add Card");
		btnAddCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCard();
			}
		});
		btnAddCard.setBounds(193, 252, 119, 23);
		panel_1.add(btnAddCard);
		
		JLabel lblFirstAdd = new JLabel("* First, add the cards. And then, the quantity");
		lblFirstAdd.setBounds(31, 508, 301, 14);
		contentPane.add(lblFirstAdd);

		if(deck!=null)
			fillDeckFields();
		else this.deck = new Deck();

	}
	
	public void buildDeck(){
		deck.id=Integer.parseInt(textFieldId.getText());
		deck.name= textFieldName.getText();
		deck.localizedName=textFieldLocName.getText();
		deck.icon= textFieldIcon.getText();
		deck.description=textArea.getText();
		deck.buyCost=0;
		deck.sellCost=0;
		deck.availableInMainShop=false;
		deck.sellable=false;
		deck.consumable=false;
		deck.minCapacity=Integer.parseInt(textFieldMinCap.getText());
		deck.maxCapacity= Integer.parseInt(textFieldMaxCap.getText());
		deck.totalCardPoints= Integer.parseInt(textFieldCardPoints.getText());
		
		if( table.getCellEditor()!=null)table.getCellEditor().stopCellEditing();
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		for (int i =0; i<model.getRowCount();i++){
			for(int j = 0;j < Integer.parseInt(model.getValueAt(i, 1).toString());j++){
				cards.add(cardMap.get((String)model.getValueAt(i, 0)));
			}
		}
		deck.cards=cards;
		for(Card card : cards){
			System.out.println(card.id);
			System.out.println(card.name);
		}
	}
	
	public void createDeck(){
		buildDeck();
		try {
			System.out.println(deck.cards.size());
			controller.createDeck(deck);
			controller.updateTableToDecks();
			dispose();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	private void fillComboBox(){		
		ArrayList<Card> cardArray = controller.getCards();
		ArrayList<String> cardNames= new ArrayList<String>();
		for(Card card: cardArray){
			cardMap.put(card.name, card);
			cardNames.add(card.name);
		}	
		
		
		if (cardArray!= null) {			
			comboBox.setModel(new DefaultComboBoxModel(cardNames
					.toArray()));
		}
	}
	
	private void fillDeckFields(){
		textFieldId.setText(String.valueOf(deck.id));
		textFieldCardPoints.setText(String.valueOf(deck.totalCardPoints));
		textFieldMaxCap.setText(String.valueOf(deck.maxCapacity));
		textFieldMinCap.setText(String.valueOf(deck.minCapacity));
		textArea.setText(deck.description);
		textFieldIcon.setText(deck.icon);
		textFieldLocName.setText(deck.localizedName);
		textFieldName.setText(deck.name);
	}
	public void addCard(){
		Card card = cardMap.get(comboBox.getSelectedItem().toString());
		cards.add(card);			
		String[] columnNames={"Name","Quantity"};
		Object[][] tableData = new Object[cards.size()][10];
		int i = 0;
		for(Card c : cards){			
			tableData[i][0]=c.name;			
			i++;
		}
		DefaultTableModel dataModel = new DefaultTableModel(tableData, columnNames);
		table.setModel(dataModel);				
		dataModel.fireTableDataChanged();
	}
}
