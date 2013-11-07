package br.edu.ifsp.pds.shadowstruggles.tools.view.events;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProfileConditionChooser extends JFrame {

	private JPanel contentPane;
	private EventActionEditor previousScreen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfileConditionChooser frame = new ProfileConditionChooser(null);
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
	public ProfileConditionChooser(EventActionEditor previousScreen) {
		setVisible(true);
		this.previousScreen=previousScreen;
		setTitle("Condition Chooser");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 339, 191);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChooseACondition = new JLabel("Choose a condition type!");
		lblChooseACondition.setBounds(38, 11, 169, 14);
		contentPane.add(lblChooseACondition);
		
		JButton btnBattleCondition = new JButton("Battle Condition");
		btnBattleCondition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BattleConditionChooser(getPreviousScreen());
				dispose();
			}
		});
		btnBattleCondition.setBounds(38, 36, 121, 40);
		contentPane.add(btnBattleCondition);
		
		JButton btnNewButton = new JButton("Item Condition");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ItemConditionChooser(getPreviousScreen());
				dispose();
			}
		});
		btnNewButton.setBounds(169, 36, 114, 40);
		contentPane.add(btnNewButton);
		
		JButton btnQuestCondition = new JButton("Quest Condition");
		btnQuestCondition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new QuestChooser(getPreviousScreen());
				dispose();
			}
		});
		btnQuestCondition.setBounds(38, 87, 121, 40);
		contentPane.add(btnQuestCondition);
		
		JButton btnStoryCondition = new JButton("Story Condition");
		btnStoryCondition.setBounds(169, 87, 114, 40);
		contentPane.add(btnStoryCondition);
	}
	public EventActionEditor getPreviousScreen() {
		return previousScreen;
	}
}
