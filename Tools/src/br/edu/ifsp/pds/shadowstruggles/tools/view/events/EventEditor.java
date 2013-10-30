package br.edu.ifsp.pds.shadowstruggles.tools.view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
import br.edu.ifsp.pds.shadowstruggles.tools.model.events.Event;
import br.edu.ifsp.pds.shadowstruggles.tools.model.events.Event.TriggerType;
import br.edu.ifsp.pds.shadowstruggles.tools.model.events.EventAction;
import br.edu.ifsp.pds.shadowstruggles.tools.view.enemies.MyCellRenderer;

import com.esotericsoftware.jsonbeans.Json;

public class EventEditor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idTextField;
	private JTextField xTextField;
	private JList<EventAction> list;
	private ArrayList<EventAction> actions;
	private Event event;
	private JButton btnAddEvent;
	private Controller controller;
	private JTextField yTextField;
	private JTextField widthTextField;
	private JTextField heightTextField;
	private JTextField mapTextField;
	private JTextField layerTextField;
	private JTextField spriteTextField;
	private JComboBox comboBox;
	private JCheckBox chckbxCollidable;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EventEditor(Controller controller) {
		this.controller = controller;
		this.actions = new ArrayList<EventAction>();
		event = new Event();
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
				try {
					event.id = Integer.parseInt(idTextField.getText());
				} catch (NumberFormatException e) {
					// e.printStackTrace();
				}
			}
		});
		idTextField.setBounds(102, 25, 47, 19);
		contentPane.add(idTextField);
		idTextField.setColumns(10);

		JLabel lblX = new JLabel("Position X:");
		lblX.setBounds(220, 25, 70, 15);
		contentPane.add(lblX);

		xTextField = new JTextField();
		xTextField.setBounds(280, 23, 38, 19);
		contentPane.add(xTextField);
		xTextField.setColumns(10);

		JLabel lblActions = new JLabel("Actions:");
		lblActions.setBounds(220, 133, 70, 15);
		contentPane.add(lblActions);

		JButton btnEditAction = new JButton("Edit");
		btnEditAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventActionEditor frame = new EventActionEditor(list
						.getSelectedValue());
				frame.setVisible(true);
				frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						updateEventActions();
					}
				});
			}
		});
		btnEditAction.setBounds(91, 374, 61, 25);
		contentPane.add(btnEditAction);

		JButton btnRemoveAction = new JButton("Remove");
		btnRemoveAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!list.isSelectionEmpty()) {
					event.actions.remove(list.getSelectedValue());
					updateEventActions();
				}
			}
		});
		btnRemoveAction.setBounds(164, 374, 89, 25);
		contentPane.add(btnRemoveAction);

		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final EventActionEditor frame = new EventActionEditor(null);
				frame.setVisible(true);
				frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						if (frame.getAction() != null) {
							updateEventActions();
						}
					}
				});
				frame.btnAddAction.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						frame.buildAction();
						actions.add(frame.getAction());
						updateEventActions();
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
		final EventEditor frame = this;
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(271, 422, 89, 23);
		contentPane.add(btnCancel);

		btnAddEvent = new JButton("Add Event");
		btnAddEvent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				event.id = Integer.parseInt(idTextField.getText());
				event.x = Integer.parseInt(xTextField.getText());
				event.y = Integer.parseInt(yTextField.getText());
				event.width = Integer.parseInt(widthTextField.getText());
				event.height = Integer.parseInt(heightTextField.getText());
				event.map = mapTextField.getText();
				event.layer = layerTextField.getText();
				event.sprite = spriteTextField.getText();
				event.triggerType = (TriggerType) comboBox
						.getSelectedItem();
				event.collidable = chckbxCollidable.isSelected();
				event.actions=actions;
				try {
					createEvent();
					getController().updateTableToEvents();
					dispose();
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
		});
		btnAddEvent.setBounds(91, 422, 114, 23);
		contentPane.add(btnAddEvent);

		list = new JList<EventAction>();
		contentPane.add(list);
		list.setCellRenderer(new MyCellRenderer());
		list.setToolTipText("");
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(22, 170, 518, 194);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 159, 548, 204);
		contentPane.add(scrollPane);

		JLabel lblY = new JLabel("Y:");
		lblY.setBounds(369, 25, 19, 14);
		contentPane.add(lblY);

		yTextField = new JTextField();
		yTextField.setBounds(398, 22, 38, 20);
		contentPane.add(yTextField);
		yTextField.setColumns(10);

		JLabel lblWidth = new JLabel("Width:");
		lblWidth.setBounds(220, 51, 46, 14);
		contentPane.add(lblWidth);

		widthTextField = new JTextField();
		widthTextField.setBounds(280, 51, 38, 20);
		contentPane.add(widthTextField);
		widthTextField.setColumns(10);

		JLabel lblHeight = new JLabel("Height:");
		lblHeight.setBounds(342, 51, 46, 14);
		contentPane.add(lblHeight);

		heightTextField = new JTextField();
		heightTextField.setBounds(398, 51, 38, 20);
		contentPane.add(heightTextField);
		heightTextField.setColumns(10);

		JLabel lblMap = new JLabel("Map:");
		lblMap.setBounds(46, 57, 46, 14);
		contentPane.add(lblMap);

		mapTextField = new JTextField();
		mapTextField.setBounds(102, 55, 89, 20);
		contentPane.add(mapTextField);
		mapTextField.setColumns(10);

		JLabel lblLayer = new JLabel("Layer:");
		lblLayer.setBounds(38, 82, 46, 14);
		contentPane.add(lblLayer);

		layerTextField = new JTextField();
		layerTextField.setBounds(102, 79, 89, 20);
		contentPane.add(layerTextField);
		layerTextField.setColumns(10);

		JLabel lblSprite = new JLabel("Sprite:");
		lblSprite.setBounds(220, 85, 46, 14);
		contentPane.add(lblSprite);

		spriteTextField = new JTextField();
		spriteTextField.setBounds(280, 82, 119, 20);
		contentPane.add(spriteTextField);
		spriteTextField.setColumns(10);

		JLabel lblTriggerType = new JLabel("Trigger Type:");
		lblTriggerType.setBounds(22, 113, 80, 14);
		contentPane.add(lblTriggerType);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(TriggerType.values()));
		comboBox.setBounds(102, 110, 89, 20);
		contentPane.add(comboBox);

		chckbxCollidable = new JCheckBox("Collidable");
		chckbxCollidable.setBounds(221, 106, 97, 23);
		contentPane.add(chckbxCollidable);

		if (event != null)
			updateEventActions();
	}

	private void createEvent() throws IOException {
		controller.createEvent(event);
	}

	private void updateEventActions() {
		idTextField.setText(Integer.toString(event.id));

		DefaultListModel<EventAction> listModel = new DefaultListModel<EventAction>();

		for (EventAction action : event.actions) {
			listModel.addElement(action);
		}

		list.setModel(listModel);
	}

	private void generateJson() {
		if (event == null)
			JOptionPane.showMessageDialog(this, "Incomplete object");
		else {
			JOptionPane.showMessageDialog(this, new Json().prettyPrint(event));
			System.out.println(new Json().prettyPrint(event));
		}
	}
	
	public Controller getController() {
		return controller;
	}
}
