package br.edu.ifsp.pds.shadowstruggles.tools.view.items;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.edu.ifsp.pds.shadowstruggles.tools.Controller;
import javax.swing.JButton;

public class ItemChooser extends JFrame {

	private JPanel contentPane;
	private Controller controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemChooser frame = new ItemChooser(null);
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
	public ItemChooser(Controller controller) {
		setTitle("Item Chooser");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 205, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnModifierItem = new JButton("Modifier Item");
		btnModifierItem.setBounds(31, 11, 121, 37);
		contentPane.add(btnModifierItem);
		
		JButton btnTextItem = new JButton("Text Item");
		btnTextItem.setBounds(31, 59, 121, 37);
		contentPane.add(btnTextItem);
		
		JButton btnPack = new JButton("Pack");
		btnPack.setBounds(31, 117, 121, 37);
		contentPane.add(btnPack);
	}
	public Controller getController() {
		return controller;
	}
}
