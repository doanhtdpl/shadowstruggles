package br.edu.ifsp.pds.shasdowstruggles.view.edition;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class EventEditor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventEditor frame = new EventEditor();
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
	public EventEditor() {
		setTitle("Event Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(25, 11, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblTriggerType = new JLabel("Trigger Type:");
		lblTriggerType.setBounds(218, 11, 73, 14);
		contentPane.add(lblTriggerType);
		
		JLabel lblEventType = new JLabel("Event Type:");
		lblEventType.setBounds(25, 43, 73, 14);
		contentPane.add(lblEventType);
		
		JLabel lblTileX = new JLabel("Tile X:");
		lblTileX.setBounds(25, 134, 46, 14);
		contentPane.add(lblTileX);
		
		JLabel lblTileY = new JLabel("Tile Y:");
		lblTileY.setBounds(222, 134, 56, 14);
		contentPane.add(lblTileY);
		
		JLabel lblMap = new JLabel("Map:");
		lblMap.setBounds(25, 80, 46, 14);
		contentPane.add(lblMap);
		
		JLabel lblLayer = new JLabel("Layer:");
		lblLayer.setBounds(218, 80, 46, 14);
		contentPane.add(lblLayer);
		
		JLabel lblQuest = new JLabel("Quest:");
		lblQuest.setBounds(25, 109, 46, 14);
		contentPane.add(lblQuest);
		
		JLabel lblSprite = new JLabel("Sprite:");
		lblSprite.setBounds(218, 109, 46, 14);
		contentPane.add(lblSprite);
		
		JCheckBox chckbxTriggered = new JCheckBox("Triggered");
		chckbxTriggered.setBounds(208, 39, 97, 23);
		contentPane.add(chckbxTriggered);
		
		textField = new JTextField();
		textField.setBounds(81, 8, 108, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(91, 40, 98, 20);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(301, 8, 108, 20);
		contentPane.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(81, 77, 108, 20);
		contentPane.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(301, 77, 108, 20);
		contentPane.add(comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(81, 106, 108, 20);
		contentPane.add(comboBox_4);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBounds(301, 106, 108, 20);
		contentPane.add(comboBox_5);
		
		textField_1 = new JTextField();
		textField_1.setBounds(81, 134, 108, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(301, 134, 108, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(25, 183, 164, 42);
		contentPane.add(btnInsert);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(218, 183, 191, 42);
		contentPane.add(btnCancel);
	}

}
