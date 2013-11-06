package br.edu.ifsp.pds.shadowstruggles.tools.view.scenes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LayerControlChooser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LayerControlChooser frame = new LayerControlChooser();
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
	public LayerControlChooser() {
		setTitle("Layer Control Chooser");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 330, 213);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLayer = new JLabel("New Layer:");
		lblNewLayer.setBounds(46, 49, 74, 14);
		contentPane.add(lblNewLayer);
		
		JLabel lblMap = new JLabel("Map:");
		lblMap.setBounds(46, 11, 46, 14);
		contentPane.add(lblMap);
		
		textField = new JTextField();
		textField.setBounds(134, 8, 134, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(132, 46, 134, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnAddControl = new JButton("Add Control");
		btnAddControl.setBounds(41, 100, 110, 40);
		contentPane.add(btnAddControl);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(191, 100, 94, 40);
		contentPane.add(btnCancel);
	}

}
