package br.edu.ifsp.pds.shadowstruggles.tools.view.enemies;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MyCellRenderer extends DefaultListCellRenderer {
	private static final long serialVersionUID = 1L;

	public MyCellRenderer() {
		super();
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list,
			Object value, int index, boolean isSelected, boolean cellHasFocus) {
		final String text = value.toString();
		final JPanel panel = new JPanel(new BorderLayout());
		final JTextArea textArea = new JTextArea();
		Color background;
		Color foreground;

		textArea.setText(text);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		panel.add(textArea, BorderLayout.CENTER);

		// check if this cell represents the current DnD drop location
		JList.DropLocation dropLocation = list.getDropLocation();
		if (dropLocation != null && !dropLocation.isInsert()
				&& dropLocation.getIndex() == index) {

			background = Color.BLUE;
			foreground = Color.WHITE;

			// check if this cell is selected
		} else if (isSelected) {
			background = Color.BLUE;
			foreground = Color.WHITE;

			// unselected, and not the DnD drop location
		} else {
			background = Color.WHITE;
			foreground = Color.BLACK;
		}

		textArea.setBackground(background);
		textArea.setForeground(foreground);

		return panel;
	}

}
