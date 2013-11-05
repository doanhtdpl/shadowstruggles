package br.edu.ifsp.pds.shadowstruggles.tools.view.scenes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class ChoiceChooser extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChoiceChooser frame = new ChoiceChooser();
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
	public ChoiceChooser() {
		setTitle("Choice Chooser");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblText = new JLabel("Text:");
		lblText.setBounds(34, 11, 46, 14);
		contentPane.add(lblText);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(104, 11, 282, 83);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel lblConsequenceType = new JLabel("Consequence Type:");
		lblConsequenceType.setBounds(34, 127, 106, 22);
		contentPane.add(lblConsequenceType);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(9);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Decision", "Dialogue", "Event Control", "Layer Control", "Movement Control", "Profile Control", "Scene Control", "Scene Item", "Teleport Control"}));
		comboBox.setBounds(165, 128, 140, 20);
		contentPane.add(comboBox);
		
		JLabel lblConsequenceItem = new JLabel("Consequence Item:");
		lblConsequenceItem.setBounds(34, 170, 106, 14);
		contentPane.add(lblConsequenceItem);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(165, 167, 140, 20);
		contentPane.add(comboBox_1);
		
		JButton btnNew = new JButton("New");
		btnNew.setBounds(324, 166, 64, 23);
		contentPane.add(btnNew);
		
		JButton btnAddChoice = new JButton("Add Choice");
		btnAddChoice.setBounds(32, 207, 108, 43);
		contentPane.add(btnAddChoice);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(267, 205, 119, 45);
		contentPane.add(btnCancel);
	}
}
