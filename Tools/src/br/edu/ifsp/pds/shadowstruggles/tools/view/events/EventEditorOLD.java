package br.edu.ifsp.pds.shadowstruggles.tools.view.events;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import br.edu.ifsp.pds.shadowstruggles.tools.model.events.SceneAction;

public class EventEditorOLD extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfTile_x;
	private JTextField tfTile_y;
	JLabel lblId;
	JLabel lblTriggerType;
	JLabel lblEventType;
	JLabel lblTileX;
	JLabel lblTileY;
	JLabel lblMap;
	JLabel lblLayer;
	JLabel lblQuest;
	JLabel lblSprite;
	JCheckBox chckbxTriggered;
	JComboBox<String> jcbEventType;
	JComboBox<String> jcbTriggerType;
	JComboBox jcbMap;
	JComboBox jcbLayer;
	JComboBox jcbQuest;
	JComboBox jcbSprite;
	JButton btnInsert;
	JButton btnCancel;

	private String id;

	private String triggerType;

	private Boolean triggered;

	private String map;

	private String quest;

	private String layer;

	private String sprite;

	private int tile_x;

	private int tile_y;

	
	public EventEditorOLD() {
		setVisible(true);
		setTitle("Event Editor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblId = new JLabel("ID:");
		lblId.setBounds(25, 11, 46, 14);
		contentPane.add(lblId);

		lblTriggerType = new JLabel("Trigger Type:");
		lblTriggerType.setBounds(218, 11, 73, 14);
		contentPane.add(lblTriggerType);

		lblEventType = new JLabel("Event Type:");
		lblEventType.setBounds(25, 43, 73, 14);
		contentPane.add(lblEventType);

		lblTileX = new JLabel("Tile X:");
		lblTileX.setBounds(25, 134, 46, 14);
		contentPane.add(lblTileX);

		lblTileY = new JLabel("Tile Y:");
		lblTileY.setBounds(222, 134, 56, 14);
		contentPane.add(lblTileY);

		lblMap = new JLabel("Map:");
		lblMap.setBounds(25, 80, 46, 14);
		contentPane.add(lblMap);

		lblLayer = new JLabel("Layer:");
		lblLayer.setBounds(218, 80, 46, 14);
		contentPane.add(lblLayer);

		lblQuest = new JLabel("Quest:");
		lblQuest.setBounds(25, 109, 46, 14);
		contentPane.add(lblQuest);

		lblSprite = new JLabel("Sprite:");
		lblSprite.setBounds(218, 109, 46, 14);
		contentPane.add(lblSprite);

		chckbxTriggered = new JCheckBox("Triggered");
		chckbxTriggered.setBounds(208, 39, 97, 23);
		contentPane.add(chckbxTriggered);

		tfId = new JTextField();
		tfId.setBounds(81, 8, 108, 20);
		contentPane.add(tfId);
		tfId.setColumns(10);

		jcbEventType = new JComboBox<String>();
		jcbEventType.setBounds(91, 40, 98, 20);
		contentPane.add(jcbEventType);
		jcbEventType.addItem("");
		jcbEventType.addItem("Modifier Event");
		jcbEventType.addItem("Save Event");
		jcbEventType.addItem("Scene Event");
		jcbEventType.addItem("Shop Event");
		jcbEventType.addItem("Warp Event");

		jcbTriggerType = new JComboBox<String>();
		jcbTriggerType.setBounds(301, 8, 108, 20);
		contentPane.add(jcbTriggerType);
		jcbTriggerType.addItem("");
		jcbTriggerType.addItem("Automatic");
		jcbTriggerType.addItem("Interact");
		jcbTriggerType.addItem("Touch");

		jcbMap = new JComboBox();
		jcbMap.setBounds(81, 77, 108, 20);
		jcbMap.addItem("");
		contentPane.add(jcbMap);

		jcbLayer = new JComboBox();
		jcbLayer.setBounds(301, 77, 108, 20);
		jcbLayer.addItem("");
		contentPane.add(jcbLayer);

		jcbQuest = new JComboBox();
		jcbQuest.setBounds(81, 106, 108, 20);
		jcbQuest.addItem("");
		contentPane.add(jcbQuest);

		jcbSprite = new JComboBox();
		jcbSprite.setBounds(301, 106, 108, 20);
		jcbSprite.addItem("");
		contentPane.add(jcbSprite);

		tfTile_x = new JTextField();
		tfTile_x.setBounds(81, 134, 108, 20);
		contentPane.add(tfTile_x);
		tfTile_x.setColumns(10);

		tfTile_y = new JTextField();
		tfTile_y.setBounds(301, 134, 108, 20);
		contentPane.add(tfTile_y);
		tfTile_y.setColumns(10);

		btnInsert = new JButton("Insert");
		btnInsert.setBounds(25, 183, 164, 42);
		contentPane.add(btnInsert);
		btnInsert.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				Insert();
			}
		});

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(218, 183, 191, 42);
		contentPane.add(btnCancel);
		btnCancel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
	}

	public void Insert() {
		// TODO insert event
		
		readViewerComponents();
		printEventData();
		switch(jcbEventType.getSelectedItem().toString()){
		case "Modifier Event":
			System.out.println("Mod");
			//Criar novo ModifierAction
			break;
		case "Save Event":
			System.out.println("Sav");
			//Criar novo SaveAction
			break;
		case "Scene Event":
			System.out.println("Sce");
			//Criar novo SceneAction			
			break;
		case "Shop Event":
			System.out.println("Sho");
			//Criar novo ShopAction
			break;
		case "Warp Event":
			System.out.println("War");
			//Criar novo WarpAction
			break;
		default:
			JOptionPane.showMessageDialog(this, "Escolha um tipo de evento!");
			break;
		}
	}

	private void printEventData() {
		// TODO Auto-generated method stub
		System.out.print("\nEvent data:\n\n");
		System.out.println("Id: \t\t"+id);
		System.out.println("Trigger Type: \t"+triggerType);
		System.out.println("Triggered: \t"+triggered.toString());
		System.out.println("Map: \t\t"+map);
		System.out.println("Quest: \t\t"+quest);
		System.out.println("Layer: \t\t"+layer);
		System.out.println("Sprite: \t"+sprite);
		System.out.println("Tile X: \t"+tile_x);
		System.out.println("Tile Y: \t"+tile_y);
	}

	private void readViewerComponents() {
		id = tfId.getText();
		triggerType = jcbTriggerType.getSelectedItem().toString();
		triggered = chckbxTriggered.isSelected();
		map = jcbMap.getSelectedItem().toString();
		quest = jcbQuest.getSelectedItem().toString();
		layer = jcbLayer.getSelectedItem().toString();
		sprite = jcbLayer.getSelectedItem().toString();
		tile_x = Integer.parseInt(tfTile_x.getText());
		tile_y = Integer.parseInt(tfTile_y.getText());
	}

}
