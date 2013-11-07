package br.edu.ifsp.pds.shadowstruggles.tools.view.events;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Enemy;
import br.edu.ifsp.pds.shadowstruggles.tools.model.profiles.EnemyDefeat;
import br.edu.ifsp.pds.shadowstruggles.tools.model.profiles.conditions.BattleCondition;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JList;

public class BattleConditionChooser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JComboBox comboBox;
	private EventActionEditor previousScreen;
	private JList list;
	private ArrayList<EnemyDefeat> enemyDefeats;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BattleConditionChooser frame = new BattleConditionChooser(null);
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
	public BattleConditionChooser(EventActionEditor previousScreen) {
		setVisible(true);
		this.enemyDefeats= new ArrayList<EnemyDefeat>();
		this.previousScreen=previousScreen;
		setTitle("Battle Condition Chooser");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 347, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDefeatEnemy = new JLabel("Defeat Enemy:");
		lblDefeatEnemy.setBounds(37, 22, 84, 14);
		contentPane.add(lblDefeatEnemy);
		
		ArrayList<Enemy> enemies= previousScreen.getController().getEnemies();
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(enemies.toArray()));
		comboBox.setBounds(144, 19, 129, 20);
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(144, 50, 129, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblTimes = new JLabel("Times:");
		lblTimes.setBounds(37, 53, 46, 14);
		contentPane.add(lblTimes);
		
		JButton btnAddDefeat = new JButton("Add Defeat Condition");
		btnAddDefeat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Enemy enemy=(Enemy)comboBox.getSelectedItem();
				int times = Integer.parseInt(textField.getText());
				EnemyDefeat defeat = new EnemyDefeat(enemy, times);
				enemyDefeats.add(defeat);
				updatelist();
			}
		});
		btnAddDefeat.setBounds(37, 88, 157, 23);
		contentPane.add(btnAddDefeat);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(184, 283, 89, 23);
		contentPane.add(btnCancel);
		
		list = new JList();
		list.setBounds(37, 138, 236, 118);
		contentPane.add(list);
		
		JButton btnAddCondition = new JButton("Add Condition");
		btnAddCondition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BattleCondition battleCondition= new BattleCondition();
				battleCondition.battleRequirements=enemyDefeats;
				getPreviousScreen().addCondition(battleCondition);
				dispose();
			}
		});
		btnAddCondition.setBounds(38, 283, 117, 23);
		contentPane.add(btnAddCondition);
	}
	
	public void updatelist(){
		DefaultListModel model= new DefaultListModel();
		for(EnemyDefeat defeat : enemyDefeats){
			model.addElement(defeat);
		}
		list.setModel(model);
		list.updateUI();
	}
	
	public EventActionEditor getPreviousScreen() {
		return previousScreen;
	}
}
