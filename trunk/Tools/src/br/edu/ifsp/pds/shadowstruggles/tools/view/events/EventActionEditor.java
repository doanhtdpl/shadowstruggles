package br.edu.ifsp.pds.shadowstruggles.tools.view.events;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JList;

import javax.swing.JButton;

import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Action;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Condition;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.EnergyCondition;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.HealthCondition;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Sequence;
import br.edu.ifsp.pds.shadowstruggles.tools.model.events.EventAction;
import br.edu.ifsp.pds.shadowstruggles.tools.model.events.ModifierAction;
import br.edu.ifsp.pds.shadowstruggles.tools.model.events.SavePointAction;
import br.edu.ifsp.pds.shadowstruggles.tools.model.events.SceneAction;
import br.edu.ifsp.pds.shadowstruggles.tools.model.events.ShopAction;
import br.edu.ifsp.pds.shadowstruggles.tools.model.events.WarpAction;
import br.edu.ifsp.pds.shadowstruggles.tools.model.profiles.conditions.ProfileCondition;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class EventActionEditor extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<Condition> conditionsList;
	private ArrayList<ProfileCondition> actionConditions;
	// private ArrayList<> conditions;
	private EventAction action;
	public JButton btnAddAction;
	private JComboBox comboBox;

	public EventActionEditor(EventAction action) {
		this.action = action;
		this.actionConditions = new ArrayList<ProfileCondition>();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 42, 424, 271);
		contentPane.add(tabbedPane);
		final EventActionEditor frame = this;

		JPanel conditionsPanel = new JPanel();
		tabbedPane.addTab("Conditions", null, conditionsPanel, null);
		conditionsPanel.setLayout(null);

		conditionsList = new JList<Condition>();
		conditionsList.setBounds(12, 12, 395, 195);
		JScrollPane conditionsScrollPane = new JScrollPane(conditionsList);
		conditionsScrollPane.setBounds(conditionsList.getBounds());
		conditionsPanel.add(conditionsScrollPane);

		JButton btnEditCondition = new JButton("Edit");
		btnEditCondition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Condition selected = conditionsList.getSelectedValue();
				if (selected != null) {
					if (selected instanceof HealthCondition
							|| selected instanceof EnergyCondition) {
						/*
						 * NumericConditionFrame frame = new
						 * NumericConditionFrame( selected);
						 */
						frame.setVisible(true);
						frame.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								// updateSequence();
							}
						});
					}
				}
			}
		});
		btnEditCondition.setBounds(12, 219, 77, 25);
		conditionsPanel.add(btnEditCondition);

		JButton btnRemoveCondition = new JButton("Remove");
		btnRemoveCondition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!conditionsList.isSelectionEmpty()) {
					getAction().conditions.remove(conditionsList
							.getSelectedValue());
					// updateSequence();
				}
			}
		});
		btnRemoveCondition.setBounds(162, 219, 95, 25);
		conditionsPanel.add(btnRemoveCondition);

		JButton btnNewCondition = new JButton("New");
		btnNewCondition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// final ConditionSelectionFrame frame = new
				// ConditionSelectionFrame();
				frame.setVisible(true);

				frame.addWindowListener(new WindowAdapter() {

					@Override
					public void windowClosed(WindowEvent e) {
						/*
						 * if (frame.getCondition() != null) {
						 * sequence.conditions.add(frame.getCondition());
						 * updateSequence(); }
						 */
					}
				});
			}
		});

		btnNewCondition.setBounds(319, 219, 88, 25);
		conditionsPanel.add(btnNewCondition);

		btnAddAction = new JButton("Choose Action");
		btnAddAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnAddAction.setBounds(22, 332, 123, 23);
		contentPane.add(btnAddAction);
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(335, 332, 89, 23);
		contentPane.add(btnCancel);

		JLabel lblChooseAAction = new JLabel("Choose a Action Type:");
		lblChooseAAction.setBounds(22, 11, 123, 14);
		contentPane.add(lblChooseAAction);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {
				"Modifier Action", "Save Point Action", "Scene Action",
				"Shop Action", "Warp Action" }));
		comboBox.setBounds(170, 8, 160, 20);
		contentPane.add(comboBox);

		JButton btnBuild = new JButton("Build");
		btnBuild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buildAction();
			}
		});
		btnBuild.setBounds(335, 7, 89, 23);
		contentPane.add(btnBuild);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
	}

	public void buildAction() {
		String actionType = comboBox.getSelectedItem().toString();

		switch (actionType) {
		case "Modifier Action":
			this.action = new ModifierAction();
			new ModifierChooser();
			break;
		case "Save Point Action":
			this.action = new SavePointAction();
			break;
		case "Scene Action":
			this.action = new SceneAction();
			new SceneChooser();
			break;
		case "Shop Action":
			this.action = new ShopAction();
			new ShopChooser();
			break;
		case "Warp Action":
			this.action = new WarpAction();
			new WarpChooser();
			break;
		}
		this.action.conditions = actionConditions;
	}

	/*
	 * public void updateSequence() { DefaultListModel<Condition>
	 * conditionsModel = new DefaultListModel<Condition>(); for (Condition
	 * condition : sequence.conditions) { conditionsModel.addElement(condition);
	 * } conditionsList.setModel(conditionsModel);
	 * 
	 * DefaultListModel<Action> actionsModel = new DefaultListModel<Action>();
	 * for (Action action : sequence.actions) { actionsModel.addElement(action);
	 * } actionsList.setModel(actionsModel); }
	 */

	public EventAction getAction() {
		return this.action;
	}
}
