package br.edu.ifsp.pds.shadowstruggles.tools.view.events;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import br.edu.ifsp.pds.shadowstruggles.tools.model.profiles.conditions.ProfileCondition.NumericComparator;
import br.edu.ifsp.pds.shadowstruggles.tools.model.profiles.conditions.StoryCondition;
import br.edu.ifsp.pds.shadowstruggles.tools.model.profiles.conditions.StoryCondition.EvaluationType;
import br.edu.ifsp.pds.shadowstruggles.tools.model.scenes.Ending;

import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class StoryConditionChooser extends JFrame {

	private JPanel contentPane;
	private EventActionEditor previousScreen;
	private JTextField textFieldStoryPt;
	private JTextField textFieldPath;
	private JTextField textFieldEId;
	private JTextField textFieldEName;
	private JTextField textFieldEPath;
	private JComboBox comboBoxStoryPt;
	private JComboBox comboBoxEval;
	private JList list;
	private ArrayList<Ending> endings;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StoryConditionChooser frame = new StoryConditionChooser(null);
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
	public StoryConditionChooser(EventActionEditor previousScreen) {
		this.endings= new ArrayList<Ending>();
		setTitle("Conditional Story Chooser");
		this.previousScreen=previousScreen;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 569, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStoryPoints = new JLabel("Story points:");
		lblStoryPoints.setBounds(37, 11, 75, 14);
		contentPane.add(lblStoryPoints);
		
		comboBoxStoryPt = new JComboBox();
		comboBoxStoryPt.setModel(new DefaultComboBoxModel(NumericComparator.values()));
		comboBoxStoryPt.setBounds(122, 8, 89, 20);
		contentPane.add(comboBoxStoryPt);
		
		textFieldStoryPt = new JTextField();
		textFieldStoryPt.setBounds(236, 8, 86, 20);
		contentPane.add(textFieldStoryPt);
		textFieldStoryPt.setColumns(10);
		
		JLabel lblPath = new JLabel("Path:");
		lblPath.setBounds(37, 42, 46, 14);
		contentPane.add(lblPath);
		
		textFieldPath = new JTextField();
		textFieldPath.setBounds(122, 39, 200, 20);
		contentPane.add(textFieldPath);
		textFieldPath.setColumns(10);
		
		JLabel lblEvaluationtype = new JLabel("Evaluation Type:");
		lblEvaluationtype.setBounds(37, 67, 89, 14);
		contentPane.add(lblEvaluationtype);
		
		comboBoxEval = new JComboBox();
		comboBoxEval.setModel(new DefaultComboBoxModel(EvaluationType.values()));
		comboBoxEval.setBounds(132, 64, 190, 20);
		contentPane.add(comboBoxEval);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBounds(37, 95, 285, 80);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblEndingName = new JLabel("Ending Name:");
		lblEndingName.setBounds(10, 33, 66, 14);
		panel.add(lblEndingName);
		
		JLabel lblEndingId = new JLabel("Ending ID:");
		lblEndingId.setBounds(10, 11, 50, 14);
		panel.add(lblEndingId);
		
		JLabel lblEndingPath = new JLabel("Ending Path:");
		lblEndingPath.setBounds(10, 58, 66, 14);
		panel.add(lblEndingPath);
		
		textFieldEId = new JTextField();
		textFieldEId.setBounds(86, 8, 189, 20);
		panel.add(textFieldEId);
		textFieldEId.setColumns(10);
		
		textFieldEName = new JTextField();
		textFieldEName.setBounds(86, 30, 189, 20);
		panel.add(textFieldEName);
		textFieldEName.setColumns(10);
		
		textFieldEPath = new JTextField();
		textFieldEPath.setBounds(86, 55, 189, 20);
		panel.add(textFieldEPath);
		textFieldEPath.setColumns(10);
		
		list = new JList();
		list.setBounds(389, 41, 154, 136);
		contentPane.add(list);
		
		JButton btnAdd = new JButton(">");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ending ending = new Ending();
				ending.id=Integer.parseInt(textFieldEId.getText());
				ending.name=textFieldEName.getText();
				ending.path=textFieldEPath.getText();
				endings.add(ending);
				((DefaultListModel)list.getModel()).addElement(ending);
				list.updateUI();
				
			}
		});
		btnAdd.setBounds(332, 115, 46, 33);
		contentPane.add(btnAdd);
		
		JLabel lblEndingList = new JLabel("Ending List:");
		lblEndingList.setBounds(389, 11, 75, 14);
		contentPane.add(lblEndingList);
		
		JButton btnAddCondition = new JButton("Add Condition");
		btnAddCondition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StoryCondition condition = new StoryCondition();
				condition.storyPoints=Integer.parseInt(textFieldStoryPt.getText());
				condition.path=textFieldPath.getText();
				condition.endings=endings;
				condition.pointsComparator=(NumericComparator)comboBoxStoryPt.getSelectedItem();
				condition.type=(EvaluationType)comboBoxEval.getSelectedItem();
				getPreviousScreen().addCondition(condition);
				dispose();
			}
		});
		btnAddCondition.setBounds(23, 186, 143, 42);
		contentPane.add(btnAddCondition);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(216, 186, 106, 42);
		contentPane.add(btnCancel);
	}
	
	public EventActionEditor getPreviousScreen() {
		return previousScreen;
	}
}
