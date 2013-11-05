package br.edu.ifsp.pds.shadowstruggles.tools.view.scenes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class SceneEditor extends JFrame {

	private JPanel contentPane;
	private JTextField textField_NvBackground;
	private JTextField textField_ID;
	private JTextField textField_Name;
	private JTextField textField_EndID;
	private JList list;
	private JTextField textField_EndName;
	private JTextField textField_EndPath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SceneEditor frame = new SceneEditor();
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
	public SceneEditor() {
		setTitle("Scene Editor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 570, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(24, 11, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(24, 36, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblEndingId = new JLabel("Ending ID:");
		lblEndingId.setBounds(24, 67, 67, 14);
		contentPane.add(lblEndingId);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(31, 169, 65, 14);
		contentPane.add(lblDescription);
		
		JCheckBox chckbxNovel = new JCheckBox("Novel");
		chckbxNovel.setBounds(24, 232, 97, 23);
		contentPane.add(chckbxNovel);
		
		JPanel panel = new JPanel();
		panel.setBounds(24, 262, 284, 132);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNoveltext = new JLabel("Text:");
		lblNoveltext.setBounds(10, 11, 94, 14);
		panel.add(lblNoveltext);
		
		JLabel lblNovelBackground = new JLabel("Background:");
		lblNovelBackground.setBounds(10, 95, 70, 14);
		panel.add(lblNovelBackground);
		
		textField_NvBackground = new JTextField();
		textField_NvBackground.setBounds(80, 92, 194, 20);
		panel.add(textField_NvBackground);
		textField_NvBackground.setColumns(10);
		
		JTextArea textAreaNvtext = new JTextArea();
		textAreaNvtext.setBounds(80, 6, 194, 78);
		panel.add(textAreaNvtext);
		
		textField_ID = new JTextField();
		textField_ID.setBounds(101, 8, 114, 20);
		contentPane.add(textField_ID);
		textField_ID.setColumns(10);
		
		textField_Name = new JTextField();
		textField_Name.setBounds(101, 36, 114, 20);
		contentPane.add(textField_Name);
		textField_Name.setColumns(10);
		
		textField_EndID = new JTextField();
		textField_EndID.setBounds(101, 64, 114, 20);
		contentPane.add(textField_EndID);
		textField_EndID.setColumns(10);
		
		JTextArea textAreaDesc = new JTextArea();
		textAreaDesc.setBounds(105, 164, 203, 56);
		contentPane.add(textAreaDesc);
		
		JButton btnAddScene = new JButton("Add Scene");
		btnAddScene.setBounds(31, 405, 117, 41);
		contentPane.add(btnAddScene);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(184, 405, 124, 41);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnCancel);
		
		JLabel lblSceneItemsList = new JLabel("Scene Items List:");
		lblSceneItemsList.setBounds(327, 11, 132, 14);
		contentPane.add(lblSceneItemsList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(327, 48, 217, 201);
		contentPane.add(scrollPane);
		
		list = new JList();
		scrollPane.setViewportView(list);
		
		JLabel lblEndingName = new JLabel("Ending Name:");
		lblEndingName.setBounds(24, 92, 67, 14);
		contentPane.add(lblEndingName);
		
		JLabel lblEndingPath = new JLabel("Ending Path:");
		lblEndingPath.setBounds(24, 117, 67, 14);
		contentPane.add(lblEndingPath);
		
		textField_EndName = new JTextField();
		textField_EndName.setBounds(101, 89, 114, 20);
		contentPane.add(textField_EndName);
		textField_EndName.setColumns(10);
		
		textField_EndPath = new JTextField();
		textField_EndPath.setBounds(101, 114, 114, 20);
		contentPane.add(textField_EndPath);
		textField_EndPath.setColumns(10);
		
		JLabel lblChooseAScene = new JLabel("Choose a Scene Item to Add:");
		lblChooseAScene.setBounds(337, 262, 185, 14);
		contentPane.add(lblChooseAScene);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(9);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Decision", "Dialogue", "Event Control", "Layer Control", "Movement Control", "Profile Control", "Scene Control", "Scene Item", "Teleport Control"}));
		comboBox.setBounds(336, 287, 175, 20);
		contentPane.add(comboBox);
		
		JButton btnAddSceneItem = new JButton("Add Scene item");
		btnAddSceneItem.setBounds(337, 323, 122, 23);
		contentPane.add(btnAddSceneItem);
		this.setVisible(true);
	}
}
