package br.edu.ifsp.pds.shadowstruggles.tools.view.events;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShopChooser extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopChooser frame = new ShopChooser();
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
	public ShopChooser() {
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(170, 21, 140, 20);
		contentPane.add(comboBox);
		
		JButton btnNewShop = new JButton("New Shop");
		btnNewShop.setBounds(335, 20, 89, 23);
		contentPane.add(btnNewShop);
		
		JButton btnChooseShop = new JButton("Choose Shop");
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

}
