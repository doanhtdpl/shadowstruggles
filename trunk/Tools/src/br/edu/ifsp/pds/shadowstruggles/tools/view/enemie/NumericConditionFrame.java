package br.edu.ifsp.pds.shadowstruggles.tools.view.enemie;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;

import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Condition;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Condition.Comparator;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.EnergyCondition;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.HealthCondition;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NumericConditionFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private Condition condition;

	private JComboBox<String> conditionCBox;
	private JComboBox<String> comparatorCBox;
	private JPanel contentPane;
	private JTextField textField;

	public NumericConditionFrame(Condition cond) {
		this.condition = cond;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 181);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		conditionCBox = new JComboBox<String>();
		conditionCBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Health", "Energy" }));
		conditionCBox.setBounds(12, 36, 125, 24);
		contentPane.add(conditionCBox);

		comparatorCBox = new JComboBox<String>();
		comparatorCBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				"=", ">", "<", ">=", "<=" }));
		comparatorCBox.setBounds(173, 36, 125, 24);
		contentPane.add(comparatorCBox);

		textField = new JTextField();
		textField.setBounds(322, 39, 114, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Comparator comparator = null;
				String comparatorString = (String) comparatorCBox
						.getSelectedItem();
				String conditionString = (String) conditionCBox
						.getSelectedItem();
				int value = Integer.parseInt(textField.getText());

				if (comparatorString.equals("="))
					comparator = Comparator.EQUAL_TO;
				if (comparatorString.equals(">"))
					comparator = Comparator.GREATER_THAN;
				if (comparatorString.equals("<"))
					comparator = Comparator.LESS_THAN;
				if (comparatorString.equals(">="))
					comparator = Comparator.GREATER_THAN_OR_EQUAL_TO;
				if (comparatorString.equals("<="))
					comparator = Comparator.LESS_THAN_OR_EQUAL_TO;

				if (conditionString.equals("Health")) {
					if (condition != null) {
						((HealthCondition) condition).comparator = comparator;
						((HealthCondition) condition).baseHealth = value;
					} else
						condition = new HealthCondition(comparator, value);
				}
				if (conditionString.equals("Energy")) {
					if (condition != null) {
						((EnergyCondition) condition).comparator = comparator;
						((EnergyCondition) condition).baseEnergy = value;
					} else
						condition = new EnergyCondition(comparator, value);
				}

				dispose();
			}
		});
		btnOk.setBounds(88, 104, 81, 25);
		contentPane.add(btnOk);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(263, 104, 117, 25);
		contentPane.add(btnCancel);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(12, 9, 70, 15);
		contentPane.add(lblStatus);

		JLabel lblSign = new JLabel("Sign:");
		lblSign.setBounds(173, 9, 70, 15);
		contentPane.add(lblSign);

		JLabel lblValue = new JLabel("Value:");
		lblValue.setBounds(322, 12, 70, 15);
		contentPane.add(lblValue);

		if (condition != null)
			fillFields();
	}

	private void fillFields() {
		String status = "";
		String sign = "";
		int value = 0;

		if (condition instanceof HealthCondition) {
			status = "Health";
			value = ((HealthCondition) condition).baseHealth;
			sign = getSign(((HealthCondition) condition).comparator);
		}
		if (condition instanceof EnergyCondition) {
			status = "Energy";
			value = ((EnergyCondition) condition).baseEnergy;
			sign = getSign(((EnergyCondition) condition).comparator);
		}

		conditionCBox.setSelectedItem(status);
		comparatorCBox.setSelectedItem(sign);
		textField.setText(Integer.toString(value));
	}

	private String getSign(Comparator comparator) {
		String sign = "";

		if (comparator == Comparator.EQUAL_TO)
			sign = "=";
		if (comparator == Comparator.LESS_THAN)
			sign = "<";
		if (comparator == Comparator.GREATER_THAN)
			sign = ">";
		if (comparator == Comparator.LESS_THAN_OR_EQUAL_TO)
			sign = "<=";
		if (comparator == Comparator.GREATER_THAN_OR_EQUAL_TO)
			sign = ">=";

		return sign;
	}

	public Condition getCondition() {
		return condition;
	}
}
