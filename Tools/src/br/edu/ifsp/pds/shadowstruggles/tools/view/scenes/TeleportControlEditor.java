package br.edu.ifsp.pds.shadowstruggles.tools.view.scenes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeleportControlEditor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeleportControlEditor frame = new TeleportControlEditor();
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
	public TeleportControlEditor() {
		setTitle("Teleport Control Editor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 358, 287);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDestinyX = new JLabel("Destiny X:");
		lblDestinyX.setBounds(28, 11, 64, 14);
		contentPane.add(lblDestinyX);
		
		JLabel lblDestinyY = new JLabel("Destiny Y:");
		lblDestinyY.setBounds(204, 11, 69, 14);
		contentPane.add(lblDestinyY);
		
		JLabel lblDestinyMap = new JLabel("Destiny Map:");
		lblDestinyMap.setBounds(28, 42, 69, 14);
		contentPane.add(lblDestinyMap);
		
		JLabel lblDestinyLayer = new JLabel("Destiny Layer:");
		lblDestinyLayer.setBounds(28, 77, 79, 14);
		contentPane.add(lblDestinyLayer);
		
		JLabel lblEvent = new JLabel("Event:");
		lblEvent.setBounds(28, 118, 46, 14);
		contentPane.add(lblEvent);
		
		textField = new JTextField();
		textField.setBounds(118, 8, 46, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(272, 8, 46, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(118, 36, 133, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(117, 74, 134, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(118, 118, 133, 20);
		contentPane.add(comboBox);
		
		JButton btnAddControl = new JButton("Add Control");
		btnAddControl.setBounds(28, 177, 111, 42);
		contentPane.add(btnAddControl);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(216, 177, 102, 42);
		contentPane.add(btnCancel);
	}

}
