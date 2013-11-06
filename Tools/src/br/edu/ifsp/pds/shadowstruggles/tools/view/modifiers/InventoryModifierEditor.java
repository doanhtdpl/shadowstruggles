package br.edu.ifsp.pds.shadowstruggles.tools.view.modifiers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import br.edu.ifsp.pds.shadowstruggles.tools.model.modifiers.InventoryModifier.ItemOperation;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InventoryModifierEditor extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryModifierEditor frame = new InventoryModifierEditor();
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
	public InventoryModifierEditor() {
		setTitle("Inventory Modifier Editor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 380, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOperation = new JLabel("Operation:");
		lblOperation.setBounds(10, 11, 61, 14);
		contentPane.add(lblOperation);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(ItemOperation.values()));
		comboBox.setBounds(91, 8, 150, 20);
		contentPane.add(comboBox);
		
		JLabel lblItems = new JLabel("Items:");
		lblItems.setBounds(10, 48, 46, 14);
		contentPane.add(lblItems);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(91, 48, 150, 139);
		contentPane.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		
		
		JButton btnAddModifier = new JButton("Add Modifier");
		btnAddModifier.setBounds(10, 198, 102, 37);
		contentPane.add(btnAddModifier);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(141, 198, 100, 37);
		contentPane.add(btnCancel);
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.setBounds(264, 44, 89, 23);
		contentPane.add(btnAddItem);
	}
}
