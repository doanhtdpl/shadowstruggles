package br.edu.ifsp.pds.shadowstruggles.tools.view.scenes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;

import br.edu.ifsp.pds.shadowstruggles.tools.model.modifiers.AmountModifier;
import br.edu.ifsp.pds.shadowstruggles.tools.model.scenes.ProfileControl;
import br.edu.ifsp.pds.shadowstruggles.tools.view.modifiers.AmountModifierEditor;

public class ProfileControlEditor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JComboBox comboBox;
	private SceneEditor previousScreen;
	private ProfileControl control;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfileControlEditor frame = new ProfileControlEditor(null);
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
	public ProfileControlEditor(SceneEditor previousScreen) {
		setVisible(true);
		this.previousScreen=previousScreen;
		setTitle("Profile Control Editor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 187);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblModifierType = new JLabel("Modifier Type:");
		lblModifierType.setBounds(27, 11, 80, 14);
		contentPane.add(lblModifierType);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Attribute"}));
		comboBox.setBounds(132, 8, 126, 20);
		contentPane.add(comboBox);
		
		JButton btnAddModifier = new JButton("Add Modifier");
		btnAddModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String modifierType = comboBox.getSelectedItem().toString();
				switch(modifierType){
				case "Attribute":
					final AmountModifierEditor editor = new AmountModifierEditor();
					editor.btnAddModifier.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							editor.buildModifier();
							control=new ProfileControl(editor.getModifier());
							System.out.println(control.modifier);
							textField.setText(control.modifier.toString());
							editor.dispose();
						}
					});
				}
			}
		});
		btnAddModifier.setBounds(288, 7, 112, 23);
		contentPane.add(btnAddModifier);
		
		JLabel lblModifier = new JLabel("Modifier:");
		lblModifier.setBounds(27, 40, 46, 14);
		contentPane.add(lblModifier);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(132, 39, 268, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnAddControl = new JButton("Add Control");
		btnAddControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPreviousScreen().addSceneItem(control);
				dispose();
			}
		});
		btnAddControl.setBounds(27, 81, 126, 38);
		contentPane.add(btnAddControl);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(275, 81, 125, 38);
		contentPane.add(btnCancel);
	}
	public SceneEditor getPreviousScreen() {
		return previousScreen;
	}

}
