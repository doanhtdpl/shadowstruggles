package br.edu.ifsp.pds.shadowstruggles.tools.view.enemie;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;

import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Condition;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.True;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ConditionSelectionFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Condition condition;
	
	private JPanel contentPane;
	
	public ConditionSelectionFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNumeric = new JButton("Numeric");
		btnNumeric.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final NumericConditionFrame frame = new NumericConditionFrame(null);
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
						if(frame.getCondition() != null) {
							condition = frame.getCondition();
							dispose();
						}
					}
					
					@Override
					public void windowActivated(WindowEvent e) {
					}
				});
			}
		});
		btnNumeric.setBounds(12, 12, 117, 25);
		contentPane.add(btnNumeric);
		
		JButton btnTrue = new JButton("True");
		btnTrue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				condition = new True();
				dispose();
			}
		});
		btnTrue.setBounds(141, 12, 117, 25);
		contentPane.add(btnTrue);
		
		JButton btnFalse = new JButton("False");
		btnFalse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				condition = new True();
				condition.not = true;
				dispose();
			}
		});
		btnFalse.setBounds(270, 12, 117, 25);
		contentPane.add(btnFalse);
	}
	
	public Condition getCondition() {
		return this.condition;
	}
}
