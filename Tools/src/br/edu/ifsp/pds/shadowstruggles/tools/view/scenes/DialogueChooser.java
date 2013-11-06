package br.edu.ifsp.pds.shadowstruggles.tools.view.scenes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

import br.edu.ifsp.pds.shadowstruggles.tools.model.scenes.Dialogue;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogueChooser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private SceneEditor previousScreen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DialogueChooser frame = new DialogueChooser(null);
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
	public DialogueChooser(SceneEditor previousScreen) {
		this.previousScreen=previousScreen;
		setVisible(true);
		setTitle("Dialogue Chooser");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCharacterName = new JLabel("Character Name:");
		lblCharacterName.setBounds(32, 11, 88, 14);
		contentPane.add(lblCharacterName);
		
		JLabel lblText = new JLabel("Text:");
		lblText.setBounds(32, 48, 46, 14);
		contentPane.add(lblText);
		
		textField = new JTextField();
		textField.setBounds(130, 8, 169, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(130, 48, 250, 95);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnAddDialogue = new JButton("Add Dialogue");
		btnAddDialogue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textArea.getText();
				String charName = textField.getText();
				Dialogue dialogue = new Dialogue(text, charName);
				getPreviousScreen().addSceneItem(dialogue);
				dispose();
			}
		});
		btnAddDialogue.setBounds(31, 178, 146, 47);
		contentPane.add(btnAddDialogue);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(255, 178, 125, 47);
		contentPane.add(btnCancel);
	}
	
	public SceneEditor getPreviousScreen() {
		return previousScreen;
	}
}
