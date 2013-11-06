package br.edu.ifsp.pds.shadowstruggles.tools.view.scenes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import br.edu.ifsp.pds.shadowstruggles.tools.model.scenes.EventControl.EventManipulation;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EventControlChooser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventControlChooser frame = new EventControlChooser();
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
	public EventControlChooser() {
		setTitle("Event Control Chooser");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 346, 243);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblManipulationType = new JLabel("Manipulation Type:");
		lblManipulationType.setBounds(25, 25, 101, 14);
		contentPane.add(lblManipulationType);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(EventManipulation.values()));
		comboBox.setBounds(154, 22, 143, 20);
		contentPane.add(comboBox);
		
		JLabel lblNewValue = new JLabel("New Value:");
		lblNewValue.setBounds(25, 64, 64, 14);
		contentPane.add(lblNewValue);
		
		textField = new JTextField();
		textField.setBounds(154, 61, 143, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnAddControl = new JButton("Add Control");
		btnAddControl.setBounds(25, 134, 116, 44);
		contentPane.add(btnAddControl);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(196, 134, 101, 44);
		contentPane.add(btnCancel);
	}
}
