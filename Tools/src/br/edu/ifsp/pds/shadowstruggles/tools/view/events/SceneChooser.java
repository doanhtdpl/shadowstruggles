package br.edu.ifsp.pds.shadowstruggles.tools.view.events;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import br.edu.ifsp.pds.shadowstruggles.tools.model.events.SceneAction;
import br.edu.ifsp.pds.shadowstruggles.tools.model.scenes.Scene;
import br.edu.ifsp.pds.shadowstruggles.tools.view.scenes.SceneEditor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class SceneChooser extends JFrame {

	private JPanel contentPane;
	private EventActionEditor previousScreen;
	private JComboBox comboBox;
	private HashMap<String, Scene> scenes;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SceneChooser frame = new SceneChooser(null);
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
	public SceneChooser(EventActionEditor previousScreen) {
		setVisible(true);
		this.previousScreen=previousScreen;
		this.scenes= new HashMap<String, Scene>();
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
		
		comboBox = new JComboBox();
		ArrayList<Scene> scenesArray = getPreviousScreen().getController().getScenes();		
		ArrayList<String> scenesNames= new ArrayList<String>();
		for(Scene scene: scenesArray){
			scenes.put(scene.name, scene);
			scenesNames.add(scene.name);
		}
		if (scenesArray!= null) {			
			comboBox.setModel(new DefaultComboBoxModel(scenesNames
					.toArray()));
		}
		comboBox.setBounds(172, 24, 144, 20);
		contentPane.add(comboBox);
		
		JButton btnNewScene = new JButton("New Scene");
		btnNewScene.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SceneEditor(getPreviousScreen().getController());
			}
		});
		btnNewScene.setBounds(335, 23, 89, 23);
		contentPane.add(btnNewScene);
		
		JButton btnChooseScene = new JButton("Choose Scene");
		btnChooseScene.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SceneAction action = new SceneAction();
				action.scene=scenes.get(comboBox.getSelectedItem().toString());
				getPreviousScreen().setAction(action);
				dispose();
			}
		});
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
	
	public EventActionEditor getPreviousScreen() {
		return previousScreen;
	}
}
