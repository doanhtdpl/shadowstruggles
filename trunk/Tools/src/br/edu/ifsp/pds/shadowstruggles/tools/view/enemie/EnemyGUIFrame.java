package br.edu.ifsp.pds.shadowstruggles.tools.view.enemie;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Card;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Action;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Action.Attribute;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Action.DynamicValue;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Condition.Comparator;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Enemy;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.EnergyCondition;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.HealthCondition;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Sequence;

import com.esotericsoftware.jsonbeans.Json;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EnemyGUIFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idTextField;
	private JTextField nameTextField;
	private JList<Sequence> list;
	private Enemy enemy;

	public EnemyGUIFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(57, 25, 27, 15);
		contentPane.add(lblId);

		idTextField = new JTextField();
		idTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				enemy.id = Integer.parseInt(idTextField.getText());
			}
		});
		idTextField.setBounds(102, 25, 47, 19);
		contentPane.add(idTextField);
		idTextField.setColumns(10);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(220, 25, 70, 15);
		contentPane.add(lblName);

		nameTextField = new JTextField();
		nameTextField.setBounds(280, 23, 114, 19);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);

		JLabel lblStrategy = new JLabel("Strategy:");
		lblStrategy.setBounds(184, 65, 70, 15);
		contentPane.add(lblStrategy);

		list = new JList<Sequence>();
		list.setCellRenderer(new MyCellRenderer());
		list.setToolTipText("");
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(57, 88, 337, 275);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(57, 88, 337, 275);
		contentPane.add(scrollPane);

		JButton btnEditSequence = new JButton("Edit");
		btnEditSequence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SequenceEditorFrame frame = new SequenceEditorFrame(list
						.getSelectedValue());
				frame.setVisible(true);
				frame.addWindowListener(new WindowListener() {

					@Override
					public void windowOpened(WindowEvent e) {
					}

					@Override
					public void windowIconified(WindowEvent e) {
					}

					@Override
					public void windowDeiconified(WindowEvent e) {
					}

					@Override
					public void windowDeactivated(WindowEvent e) {
					}

					@Override
					public void windowClosing(WindowEvent e) {
					}

					@Override
					public void windowClosed(WindowEvent e) {
						updateEnemy();
					}

					@Override
					public void windowActivated(WindowEvent e) {
					}
				});
			}
		});
		btnEditSequence.setBounds(91, 374, 61, 25);
		contentPane.add(btnEditSequence);

		JButton btnRemoveSequence = new JButton("Remove");
		btnRemoveSequence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!list.isSelectionEmpty()) {
					enemy.strategy.remove(list.getSelectedValue());
					updateEnemy();
				}
			}
		});
		btnRemoveSequence.setBounds(164, 374, 89, 25);
		contentPane.add(btnRemoveSequence);

		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final SequenceEditorFrame frame = new SequenceEditorFrame(null);
				frame.setVisible(true);
				frame.addWindowListener(new WindowListener() {

					@Override
					public void windowOpened(WindowEvent e) {
					}

					@Override
					public void windowIconified(WindowEvent e) {
					}

					@Override
					public void windowDeiconified(WindowEvent e) {
					}

					@Override
					public void windowDeactivated(WindowEvent e) {
					}

					@Override
					public void windowClosing(WindowEvent e) {
					}

					@Override
					public void windowClosed(WindowEvent e) {
						if (frame.getSequence() != null) {
							enemy.strategy.add(frame.getSequence());
							updateEnemy();
						}
					}

					@Override
					public void windowActivated(WindowEvent e) {
					}
				});
			}
		});
		btnNew.setBounds(271, 374, 89, 25);
		contentPane.add(btnNew);

		JButton btnGenerateJson = new JButton("Generate JSON");
		btnGenerateJson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				generateJson();
			}
		});
		btnGenerateJson.setBounds(32, 447, 139, 25);
		contentPane.add(btnGenerateJson);

		JButton btnExample = new JButton("Example");
		btnExample.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateExample();
			}
		});
		btnExample.setBounds(295, 447, 117, 25);
		contentPane.add(btnExample);

		if (enemy != null)
			updateEnemy();
	}

	private void updateEnemy() {
		idTextField.setText(Integer.toString(enemy.id));
		nameTextField.setText(enemy.name);
		DefaultListModel<Sequence> listModel = new DefaultListModel<Sequence>();

		for (Sequence sequence : enemy.strategy) {
			listModel.addElement(sequence);
		}

		list.setModel(listModel);
	}

	private void generateJson() {
		if (enemy == null)
			JOptionPane.showMessageDialog(this, "Incomplete object");
		else {
			JOptionPane.showMessageDialog(this,
					new Json().prettyPrint(enemy));
			System.out.println(new Json().prettyPrint(enemy));
		}
	}

	private void generateExample() {
		enemy = new Enemy(20, "Enemy", new ArrayList<Sequence>());

		Sequence sequence = new Sequence();
		sequence.conditions.add(new EnergyCondition(
				Comparator.GREATER_THAN_OR_EQUAL_TO, 20));
		sequence.conditions.add(new HealthCondition(
				Comparator.LESS_THAN_OR_EQUAL_TO, 90));
		sequence.actions.add(new Action(Attribute.LANE, null,
				DynamicValue.RANDOM_LANE));
		sequence.actions.add(new Action(Attribute.COLUMN, null,
				DynamicValue.RANDOM_COLUMN));
		sequence.actions.add(new Action(Attribute.CARD, new Card("DR-002"),
				null));

		enemy.strategy.add(sequence);
		updateEnemy();
	}
}
