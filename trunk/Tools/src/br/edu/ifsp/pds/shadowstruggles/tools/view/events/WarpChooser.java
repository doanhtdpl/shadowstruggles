package br.edu.ifsp.pds.shadowstruggles.tools.view.events;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WarpChooser extends JFrame {

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
					WarpChooser frame = new WarpChooser();
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
	public WarpChooser() {
		setTitle("Warp Chooser");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 391, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rdbtnChooseAWarppoint = new JRadioButton("Choose a WarpPoint:");
		rdbtnChooseAWarppoint.setBounds(36, 18, 139, 23);
		contentPane.add(rdbtnChooseAWarppoint);
		
		JRadioButton rdbtnCreateAWarp = new JRadioButton("Create a Warp Point");
		rdbtnCreateAWarp.setBounds(36, 56, 139, 23);
		contentPane.add(rdbtnCreateAWarp);
		
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(rdbtnCreateAWarp);
		radioGroup.add(rdbtnChooseAWarppoint);
		JLabel lblDestinyX = new JLabel("Destiny X:");
		lblDestinyX.setBounds(69, 96, 72, 14);
		contentPane.add(lblDestinyX);
		
		textField = new JTextField();
		textField.setBounds(151, 93, 42, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblDestinyY = new JLabel("Destiny Y:");
		lblDestinyY.setBounds(237, 96, 63, 14);
		contentPane.add(lblDestinyY);
		
		textField_1 = new JTextField();
		textField_1.setBounds(304, 93, 42, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDestinyMap = new JLabel("Destiny Map:");
		lblDestinyMap.setBounds(69, 124, 72, 14);
		contentPane.add(lblDestinyMap);
		
		textField_2 = new JTextField();
		textField_2.setBounds(151, 121, 195, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblDestinyLayer = new JLabel("Destiny Layer:");
		lblDestinyLayer.setBounds(69, 163, 72, 14);
		contentPane.add(lblDestinyLayer);
		
		textField_3 = new JTextField();
		textField_3.setBounds(151, 160, 195, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnChooseWarpPoint = new JButton("Choose Warp Point");
		btnChooseWarpPoint.setBounds(52, 203, 141, 47);
		contentPane.add(btnChooseWarpPoint);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(227, 203, 119, 47);
		contentPane.add(btnCancel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(181, 19, 165, 20);
		contentPane.add(comboBox);
	}
}
