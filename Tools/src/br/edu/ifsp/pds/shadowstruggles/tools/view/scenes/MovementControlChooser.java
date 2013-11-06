package br.edu.ifsp.pds.shadowstruggles.tools.view.scenes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import br.edu.ifsp.pds.shadowstruggles.tools.model.scenes.MovementControl.Direction;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MovementControlChooser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovementControlChooser frame = new MovementControlChooser();
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
	public MovementControlChooser() {
		setTitle("Movement Control Chooser");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 326, 243);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTargetEvent = new JLabel("Target Event:");
		lblTargetEvent.setBounds(29, 11, 85, 14);
		contentPane.add(lblTargetEvent);
		
		JLabel lblDirection = new JLabel("Direction:");
		lblDirection.setBounds(29, 48, 58, 14);
		contentPane.add(lblDirection);
		
		JLabel lblSteps = new JLabel("Steps:");
		lblSteps.setBounds(29, 88, 46, 14);
		contentPane.add(lblSteps);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(124, 8, 145, 20);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(Direction.values()));
		comboBox_1.setBounds(124, 45, 145, 20);
		contentPane.add(comboBox_1);
		
		textField = new JTextField();
		textField.setBounds(124, 85, 145, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnAddControl = new JButton("Add Control");
		btnAddControl.setBounds(25, 134, 112, 37);
		contentPane.add(btnAddControl);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(171, 134, 98, 37);
		contentPane.add(btnCancel);
	}

}
