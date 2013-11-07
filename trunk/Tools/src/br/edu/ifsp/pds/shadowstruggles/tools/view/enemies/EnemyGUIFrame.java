package br.edu.ifsp.pds.shadowstruggles.tools.view.enemies;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import br.edu.ifsp.pds.shadowstruggles.tools.Controller;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Enemy;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Sequence;

import com.esotericsoftware.jsonbeans.Json;

public class EnemyGUIFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idTextField;
	private JTextField nameTextField;
	private JList<Sequence> list;
	private Enemy enemy;
	private JButton btnAddEnemy;
	private Controller controller;

	public EnemyGUIFrame(Controller controller) {
		setVisible(true);
		setTitle("Enemy Editor");		
		this.controller=controller;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 550);
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
				try{
				enemy.id = Integer.parseInt(idTextField.getText());
				}catch(NumberFormatException e){
					//e.printStackTrace();
				}
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

		JButton btnEditSequence = new JButton("Edit");
		btnEditSequence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SequenceEditorFrame frame = new SequenceEditorFrame(list
						.getSelectedValue(), getController());
				frame.setVisible(true);
				frame.addWindowListener(new WindowAdapter() {@Override
				public void windowClosed(WindowEvent e) {					
					updateEnemy();
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
				final SequenceEditorFrame frame = new SequenceEditorFrame(null,getController());
				frame.setVisible(true);
				frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						if (frame.getSequence() != null) {				
							
							updateEnemy();
						}
					}
				});
				frame.btnAddStrategy.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						enemy.strategy.add(frame.getSequence());
						frame.dispose();
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
		btnGenerateJson.setBounds(135, 476, 139, 25);
		contentPane.add(btnGenerateJson);
		
		JButton btnCancel = new JButton("Cancel");
		final EnemyGUIFrame frame = this;
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(271, 422, 89, 23);
		contentPane.add(btnCancel);
		
		btnAddEnemy = new JButton("Add Enemy");
		btnAddEnemy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					enemy.name=nameTextField.getText();
					getController().createEnemy(enemy);
					getController().updateTableToEnemies();
					dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAddEnemy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {				
				enemy.name=nameTextField.getText();				
			}
		});
		btnAddEnemy.setBounds(91, 422, 114, 23);
		contentPane.add(btnAddEnemy);
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 88, 548, 275);
				contentPane.add(scrollPane);
				
						list = new JList<Sequence>();
						scrollPane.setViewportView(list);
						list.setCellRenderer(new MyCellRenderer());
						list.setToolTipText("");
						list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		if (enemy != null)
			updateEnemy();
		else enemy= new Enemy();
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
	
	public Controller getController() {
		return controller;
	}
}
