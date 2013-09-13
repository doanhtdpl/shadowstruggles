package br.edu.ifsp.pds.shadowstruggles.tools.view.enemies;

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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SequenceEditorFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<Condition> conditionsList;
	private JList<Action> actionsList;

	private Sequence sequence;
	public JButton btnAddStrategy;

	public SequenceEditorFrame(Sequence seq) {
		this.sequence = seq;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 12, 424, 301);
		contentPane.add(tabbedPane);

		JPanel actionsPanel = new JPanel();
		tabbedPane.addTab("Actions", null, actionsPanel, null);
		actionsPanel.setLayout(null);

		actionsList = new JList<Action>();
		actionsList.setBounds(12, 12, 395, 195);
		JScrollPane actionsScrollPane = new JScrollPane(actionsList);
		actionsScrollPane.setBounds(actionsList.getBounds());
		actionsPanel.add(actionsScrollPane);

		JButton btnEditAction = new JButton("Edit");
		btnEditAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Action selected = actionsList.getSelectedValue();
				if (selected != null) {
					ActionFrame frame = new ActionFrame(selected);
					frame.setVisible(true);
					frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							updateSequence();
						}
					});
				}
			}
		});
		btnEditAction.setBounds(12, 219, 84, 25);
		actionsPanel.add(btnEditAction);

		JButton btnRemoveAction = new JButton("Remove");
		btnRemoveAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!actionsList.isSelectionEmpty()) {
					sequence.actions.remove(actionsList.getSelectedValue());
					updateSequence();
				}
			}
		});
		btnRemoveAction.setBounds(167, 219, 97, 25);
		actionsPanel.add(btnRemoveAction);

		JButton btnNewAction = new JButton("New");
		btnNewAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final ActionFrame frame = new ActionFrame(null);
				frame.setVisible(true);
				frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						if (frame.getAction() != null) {
							sequence.actions.add(frame.getAction());
							updateSequence();
						}
					}
				});
			}
		});
		btnNewAction.setBounds(329, 219, 78, 25);
		actionsPanel.add(btnNewAction);
		final SequenceEditorFrame frame = this;

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
						NumericConditionFrame frame = new NumericConditionFrame(
								selected);
						frame.setVisible(true);
						frame.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								updateSequence();
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
					sequence.conditions.remove(conditionsList
							.getSelectedValue());
					updateSequence();
				}
			}
		});
		btnRemoveCondition.setBounds(162, 219, 95, 25);
		conditionsPanel.add(btnRemoveCondition);

		JButton btnNewCondition = new JButton("New");
		btnNewCondition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final ConditionSelectionFrame frame = new ConditionSelectionFrame();
				frame.setVisible(true);

				frame.addWindowListener(new WindowAdapter() {

					@Override
					public void windowClosed(WindowEvent e) {
						if (frame.getCondition() != null) {
							sequence.conditions.add(frame.getCondition());
							updateSequence();
						}
					}
				});
			}
		});

		btnNewCondition.setBounds(319, 219, 88, 25);
		conditionsPanel.add(btnNewCondition);
		
		btnAddStrategy = new JButton("Add strategy");
		btnAddStrategy.setBounds(22, 332, 123, 23);
		contentPane.add(btnAddStrategy);
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(335, 332, 89, 23);
		contentPane.add(btnCancel);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});

		

		

		if (sequence == null)
			sequence = new Sequence();

		updateSequence();
	}

	public void updateSequence() {
		DefaultListModel<Condition> conditionsModel = new DefaultListModel<Condition>();
		for (Condition condition : sequence.conditions) {
			conditionsModel.addElement(condition);
		}
		conditionsList.setModel(conditionsModel);

		DefaultListModel<Action> actionsModel = new DefaultListModel<Action>();
		for (Action action : sequence.actions) {
			actionsModel.addElement(action);
		}
		actionsList.setModel(actionsModel);
	}

	public Sequence getSequence() {
		return this.sequence;
	}
}
