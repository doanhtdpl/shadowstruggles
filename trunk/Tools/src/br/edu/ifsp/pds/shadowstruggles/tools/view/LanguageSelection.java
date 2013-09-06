package br.edu.ifsp.pds.shadowstruggles.tools.view;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import br.edu.ifsp.pds.shadowstruggles.tools.Controller;

public class LanguageSelection extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JRadioButton rdbtnSelectTheLanguage;
	private JRadioButton rdbtnNewLanguage;
	private JLabel lblCode;
	private JLabel lblName;
	private JComboBox<String> comboBox;
	private JTextField textField;
	private JTextField textField_1;
	private JFrame frame;
	private Controller controller;

	public LanguageSelection(Controller controller) {
		frame = this;
		this.controller = controller;
		setTitle("Languages");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] lang = new String[controller.getLanguages().size()];
		int i = 0;
		for (Map.Entry<String, String> entry : controller.getLanguages()
				.entrySet()) {
			lang[i] = entry.getKey() + " - " + entry.getValue();
			i++;
		}
		comboBox = new JComboBox<String>(lang);
		comboBox.setBounds(197, 27, 216, 20);
		contentPane.add(comboBox);

		rdbtnSelectTheLanguage = new JRadioButton("Select the Language:");
		rdbtnSelectTheLanguage.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rdbtnSelectTheLanguage.isSelected())
					comboBox.setEnabled(true);
				else
					comboBox.setEnabled(false);
			}
		});

		rdbtnSelectTheLanguage.setSelected(true);
		rdbtnSelectTheLanguage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnSelectTheLanguage.setBounds(20, 24, 170, 23);
		contentPane.add(rdbtnSelectTheLanguage);

		rdbtnNewLanguage = new JRadioButton("New Language");
		rdbtnNewLanguage.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rdbtnNewLanguage.isSelected()) {
					lblCode.setEnabled(true);
					lblName.setEnabled(true);
					textField.setEnabled(true);
					textField_1.setEnabled(true);
				} else {
					lblCode.setEnabled(false);
					lblName.setEnabled(false);
					textField.setEnabled(false);
					textField_1.setEnabled(false);
				}
			}
		});

		rdbtnNewLanguage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnNewLanguage.setBounds(20, 71, 148, 23);
		contentPane.add(rdbtnNewLanguage);

		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnNewLanguage);
		btnGroup.add(rdbtnSelectTheLanguage);
		lblCode = new JLabel("Code:");
		lblCode.setEnabled(false);
		lblCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCode.setBounds(122, 114, 46, 14);
		contentPane.add(lblCode);

		lblName = new JLabel("Name:");
		lblName.setEnabled(false);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(122, 150, 46, 14);
		contentPane.add(lblName);

		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(197, 113, 216, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setBounds(197, 149, 216, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnOk.setBounds(32, 206, 136, 33);
		contentPane.add(btnOk);

		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(265, 206, 148, 33);
		contentPane.add(btnNewButton);
	}
}
