package br.edu.ifsp.pds.shadowstruggles.tools.view.enemie;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;

import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Action;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Action.Attribute;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Action.DynamicValue;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Card;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ActionFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private Action action;

	private JPanel contentPane;
	private JTextField valueTField;
	private JComboBox<String> attributeCBox;
	private JComboBox<String> typeCBox;
	private JLabel lblValue;

	public ActionFrame(Action act) {
		this.action = act;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 462, 191);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblField = new JLabel("Field:");
		lblField.setBounds(31, 12, 70, 15);
		contentPane.add(lblField);

		JLabel lblValueType = new JLabel("Value type:");
		lblValueType.setBounds(184, 12, 89, 15);
		contentPane.add(lblValueType);

		lblValue = new JLabel("Value:");
		lblValue.setBounds(366, 12, 70, 15);
		contentPane.add(lblValue);

		attributeCBox = new JComboBox<String>();
		attributeCBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Card", "Lane", "Column" }));
		attributeCBox.setBounds(12, 39, 89, 24);
		contentPane.add(attributeCBox);

		typeCBox = new JComboBox<String>();
		typeCBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (typeCBox.getSelectedItem().equals("Constant value")) {
					lblValue.setVisible(true);
					valueTField.setVisible(true);
				}
			}
		});
		typeCBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Random lane", "Random column", "Constant value" }));
		typeCBox.setBounds(148, 39, 161, 24);
		contentPane.add(typeCBox);

		valueTField = new JTextField();
		valueTField.setBounds(334, 42, 114, 19);
		contentPane.add(valueTField);
		valueTField.setColumns(10);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Attribute attribute = null;
				DynamicValue dynamicType = null;
				Object value = null;

				if (attributeCBox.getSelectedItem().equals("Card"))
					attribute = Attribute.CARD;
				if (attributeCBox.getSelectedItem().equals("Lane"))
					attribute = Attribute.LANE;
				if (attributeCBox.getSelectedItem().equals("Column"))
					attribute = Attribute.COLUMN;

				if (typeCBox.getSelectedItem().equals("Random lane"))
					dynamicType = DynamicValue.RANDOM_LANE;
				if (typeCBox.getSelectedItem().equals("Random column"))
					dynamicType = DynamicValue.RANDOM_COLUMN;
				if (typeCBox.getSelectedItem().equals("Constant value")) {
					value = new Card(valueTField.getText());
					if (attribute != Attribute.CARD)
						value = Integer.parseInt((String) value);
				}

				if (action != null) {
					action.type = attribute;
					action.dynamicType = dynamicType;
					action.value = value;
				} else
					action = new Action(attribute, value, dynamicType);

				dispose();
			}
		});
		btnOk.setBounds(100, 108, 89, 25);
		contentPane.add(btnOk);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(283, 108, 101, 25);
		contentPane.add(btnCancel);

		lblValue.setVisible(false);
		valueTField.setVisible(false);

		if (action != null)
			fillFields();
	}

	private void fillFields() {
		String attribute = "";
		String type = "";

		if (action.type == Attribute.CARD)
			attribute = "Card";
		if (action.type == Attribute.LANE)
			attribute = "Lane";
		if (action.type == Attribute.COLUMN)
			attribute = "Column";

		if (action.dynamicType == DynamicValue.RANDOM_LANE)
			type = "Random lane";
		else if (action.dynamicType == DynamicValue.RANDOM_COLUMN)
			type = "Random column";
		else
			type = "Constant value";

		attributeCBox.setSelectedItem(attribute);
		typeCBox.setSelectedItem(type);

		if (type.equals("Constant value")) {
			valueTField.setText(action.value.toString());
			lblValue.setVisible(true);
			valueTField.setVisible(true);
		}
	}

	public Action getAction() {
		return this.action;
	}
}
