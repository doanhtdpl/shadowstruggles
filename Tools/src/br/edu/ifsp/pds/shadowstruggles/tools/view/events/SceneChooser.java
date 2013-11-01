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

public class SceneChooser extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SceneChooser frame = new SceneChooser();
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
	public SceneChooser() {
		setTitle("Scene Chooser");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 177);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChooseAScene = new JLabel("Choose a Scene:");
		lblChooseAScene.setBounds(38, 27, 99, 14);
		contentPane.add(lblChooseAScene);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(172, 24, 144, 20);
		contentPane.add(comboBox);
		
		JButton btnNewScene = new JButton("New Scene");
		btnNewScene.setBounds(335, 23, 89, 23);
		contentPane.add(btnNewScene);
		
		JButton btnChooseScene = new JButton("Choose Scene");
		btnChooseScene.setBounds(37, 77, 130, 50);
		contentPane.add(btnChooseScene);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(308, 77, 105, 50);
		contentPane.add(btnCancel);
	}
}
