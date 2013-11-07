package br.edu.ifsp.pds.shadowstruggles.tools.view.events;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import br.edu.ifsp.pds.shadowstruggles.tools.model.events.WarpAction;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WarpChooser extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldX;
	private JTextField textFieldY;
	private JTextField textFieldMap;
	private JTextField textFieldLayer;
	private JTextField textFieldId;
	private WarpAction action;
	private EventActionEditor previousScreen;
	

	/**
	 * Create the frame.
	 */
	public WarpChooser(EventActionEditor previousScreen) {
		this.previousScreen=previousScreen;
		setVisible(true);
		this.action= new WarpAction();
		setTitle("Warp Chooser");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 358, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ButtonGroup radioGroup = new ButtonGroup();
		JLabel lblDestinyX = new JLabel("Destiny X:");
		lblDestinyX.setBounds(27, 35, 72, 14);
		contentPane.add(lblDestinyX);
		
		textFieldX = new JTextField();
		textFieldX.setBounds(109, 32, 42, 20);
		contentPane.add(textFieldX);
		textFieldX.setColumns(10);
		
		JLabel lblDestinyY = new JLabel("Destiny Y:");
		lblDestinyY.setBounds(195, 35, 63, 14);
		contentPane.add(lblDestinyY);
		
		textFieldY = new JTextField();
		textFieldY.setBounds(262, 32, 42, 20);
		contentPane.add(textFieldY);
		textFieldY.setColumns(10);
		
		JLabel lblDestinyMap = new JLabel("Destiny Map:");
		lblDestinyMap.setBounds(27, 63, 72, 14);
		contentPane.add(lblDestinyMap);
		
		textFieldMap = new JTextField();
		textFieldMap.setBounds(109, 60, 195, 20);
		contentPane.add(textFieldMap);
		textFieldMap.setColumns(10);
		
		JLabel lblDestinyLayer = new JLabel("Destiny Layer:");
		lblDestinyLayer.setBounds(27, 102, 72, 14);
		contentPane.add(lblDestinyLayer);
		
		textFieldLayer = new JTextField();
		textFieldLayer.setBounds(109, 99, 195, 20);
		contentPane.add(textFieldLayer);
		textFieldLayer.setColumns(10);
		
		JButton btnChooseWarpPoint = new JButton("Choose Warp Point");
		btnChooseWarpPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buildWarpPoint();
				getPreviousScreen().setAction(action);
				dispose();
			}
		});
		btnChooseWarpPoint.setBounds(10, 142, 141, 47);
		contentPane.add(btnChooseWarpPoint);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(185, 142, 119, 47);
		contentPane.add(btnCancel);
		
		JLabel lblTargetId = new JLabel("Target Id:");
		lblTargetId.setBounds(27, 10, 63, 14);
		contentPane.add(lblTargetId);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(109, 7, 42, 20);
		contentPane.add(textFieldId);
		textFieldId.setColumns(10);
	}
	
	public void buildWarpPoint(){
		action.destinyLayer=textFieldLayer.getText();
		action.destinyMap=textFieldMap.getText();
		action.destinyX=Integer.parseInt(textFieldX.getText());
		action.destinyY=Integer.parseInt(textFieldY.getText());
		action.targetId=Integer.parseInt(textFieldId.getText());
	}
	
	public EventActionEditor getPreviousScreen() {
		return previousScreen;
	}
}
