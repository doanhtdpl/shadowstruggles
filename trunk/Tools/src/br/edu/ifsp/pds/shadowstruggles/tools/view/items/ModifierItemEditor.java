package br.edu.ifsp.pds.shadowstruggles.tools.view.items;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JCheckBox;

public class ModifierItemEditor extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifierItemEditor frame = new ModifierItemEditor();
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
	public ModifierItemEditor() {
		setTitle("Modifier Item Editor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 316);
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
		
		JCheckBox chckbxSellable = new JCheckBox("Sellable");
		chckbxSellable.setBounds(22, 142, 97, 23);
		contentPane.add(chckbxSellable);
		
		JLabel lblIcon = new JLabel("Icon:");
		lblIcon.setBounds(22, 182, 46, 14);
		contentPane.add(lblIcon);
		
		JCheckBox chckbxAvailableInMain = new JCheckBox("Available in Main Shop");
		chckbxAvailableInMain.setBounds(22, 202, 136, 23);
		contentPane.add(chckbxAvailableInMain);
		
		JCheckBox chckbxConsumable = new JCheckBox("Consumable");
		chckbxConsumable.setBounds(22, 228, 97, 23);
		contentPane.add(chckbxConsumable);
	}
}
