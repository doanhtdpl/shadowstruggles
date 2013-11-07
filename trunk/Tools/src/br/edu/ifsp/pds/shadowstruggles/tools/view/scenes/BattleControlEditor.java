package br.edu.ifsp.pds.shadowstruggles.tools.view.scenes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import br.edu.ifsp.pds.shadowstruggles.tools.Controller;
import br.edu.ifsp.pds.shadowstruggles.tools.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.tools.model.scenes.BattleControl;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class BattleControlEditor extends JFrame {

	private JPanel contentPane;
	private BattlePlatform battle;
	private JComboBox comboBox;	
	private HashMap<Integer, BattlePlatform> battles;
	private SceneEditor previousScreen;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BattleControlEditor frame = new BattleControlEditor(null);
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
	public BattleControlEditor(SceneEditor previousScreen) {		
		this.previousScreen=previousScreen;
		this.battle= new BattlePlatform();
		this.battles= new HashMap<Integer,BattlePlatform>();
		setVisible(true);
		setTitle("Battle Control Editor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 368, 177);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChooseTheBattle = new JLabel("Choose the Battle:");
		lblChooseTheBattle.setBounds(32, 25, 101, 14);
		contentPane.add(lblChooseTheBattle);
		
		ArrayList<BattlePlatform> battlesArray = getController().getBattles();
		ArrayList<Integer> battleIds = new ArrayList<Integer>();
		for(BattlePlatform bat: battlesArray){
			battles.put(bat.id, bat);
			battleIds.add(bat.id);
		}
		comboBox = new JComboBox();
		if(battlesArray!=null ){
			comboBox.setModel(new DefaultComboBoxModel(battleIds.toArray()));
		}
		
		comboBox.setBounds(143, 22, 156, 20);
		contentPane.add(comboBox);
		
		JButton btnAddControl = new JButton("Add Control");
		btnAddControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BattleControl control = new BattleControl();
				control.nextBattle=battles.get(Integer.parseInt(comboBox.getSelectedItem().toString()));
				System.out.println("BCE: "+control.nextBattle);
				getPreviousScreen().addSceneItem(control);
				dispose();
			}
		});
		btnAddControl.setBounds(25, 74, 108, 37);
		contentPane.add(btnAddControl);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(169, 74, 108, 37);
		contentPane.add(btnCancel);
	}
	
	
	public Controller getController() {
		return previousScreen.getController();
	}
	
	public SceneEditor getPreviousScreen() {
		return previousScreen;
	}

}
