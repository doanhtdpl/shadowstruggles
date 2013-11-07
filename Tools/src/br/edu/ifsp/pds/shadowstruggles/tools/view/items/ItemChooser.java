package br.edu.ifsp.pds.shadowstruggles.tools.view.items;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.edu.ifsp.pds.shadowstruggles.tools.Controller;
import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemChooser extends JFrame {

	private JPanel contentPane;
	private Controller controller;

	
	/**
	 * Create the frame.
	 */
	public ItemChooser(final Controller controller, Item itemToEdit) {
		setVisible(true);
		setTitle("Item Chooser");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 205, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnModifierItem = new JButton("Modifier Item");
		btnModifierItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ModifierItemEditor(controller);
				dispose();
			}
		});
		btnModifierItem.setBounds(31, 11, 121, 37);
		contentPane.add(btnModifierItem);
		
		JButton btnTextItem = new JButton("Text Item");
		btnTextItem.setBounds(31, 59, 121, 37);
		contentPane.add(btnTextItem);
		
		JButton btnPack = new JButton("Pack");
		btnPack.setBounds(31, 117, 121, 37);
		contentPane.add(btnPack);
		if(itemToEdit!=null){
			//TODO: instanciar a frame da classe do Item
		}
	}
	public Controller getController() {
		return controller;
	}
}
