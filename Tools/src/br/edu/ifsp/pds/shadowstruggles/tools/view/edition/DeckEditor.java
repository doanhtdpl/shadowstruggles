package br.edu.ifsp.pds.shadowstruggles.tools.view.edition;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;

import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Deck;

public class DeckEditor extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBox comboBox;
	private JTextArea textArea;
	private JButton btnInserir;
	private JButton btnCancel;
	private Deck deckModel;

	public DeckEditor() {
		setVisible(true);
		setTitle("Deck editor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(50, 30, 46, 14);
		contentPane.add(idLabel);

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(50, 65, 46, 14);
		contentPane.add(nameLabel);

		JLabel pointsLabel = new JLabel("Total Points");
		pointsLabel.setBounds(50, 106, 69, 14);
		contentPane.add(pointsLabel);

		JLabel imageLabel = new JLabel("Image");
		imageLabel.setBounds(50, 155, 46, 14);
		contentPane.add(imageLabel);

		JLabel cardsLabel = new JLabel("Cards");
		cardsLabel.setBounds(50, 194, 46, 14);
		contentPane.add(cardsLabel);

		textField = new JTextField();
		textField.setBounds(164, 27, 216, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(164, 62, 216, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(164, 103, 216, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		comboBox = new JComboBox<>();
		comboBox.setBounds(164, 152, 216, 20);
		contentPane.add(comboBox);

		textArea = new JTextArea();
		textArea.setBounds(164, 194, 238, 99);
		contentPane.add(textArea);

		btnInserir = new JButton("Insert");
		btnInserir.setBounds(50, 335, 89, 23);
		contentPane.add(btnInserir);
		btnInserir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String id = textField.getText().toString();
				String name = textField_1.getText().toString();
				int points = Integer.parseInt(textField_2.getText());
				// ArrayList<Card> cards = textArea.getText()
				// String deckImage = comboBox.getSelectedIndex();
			}
		});

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(313, 335, 89, 23);
		btnCancel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		contentPane.add(btnCancel);

		deckModel = new Deck();

	}
}
