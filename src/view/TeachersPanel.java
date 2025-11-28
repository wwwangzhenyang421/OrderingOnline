package view;

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.OrderView;
import controller.DishView;
import controller.EditInfo;
import controller.Info;



@SuppressWarnings("serial")
public class TeachersPanel extends JFrame implements ActionListener {
	/*
	 * Main screen after teacher login
	 */
	JPanel contain;
	String id;
	JButton DishButton, OrderButton, editButton, InfoButton;

	public TeachersPanel(String id) {
		super("teacher");
		this.id = id;
		setLocation(300, 200);
		setSize(300, 340);
		contain = new JPanel();
		contain.setLayout(null);
		add(contain);
		DishButton = new JButton("Menu Search");
		OrderButton = new JButton("Order Search");
		editButton = new JButton("Edit Info");
		InfoButton = new JButton("teacher Info");
		DishButton.setBounds(70, 40, 160, 30);
		OrderButton.setBounds(70, 80, 160, 30);
		editButton.setBounds(70, 120, 160, 30);
		InfoButton.setBounds(70, 160, 160, 30);
		contain.add(DishButton);
		DishButton.addActionListener(this);
		contain.add(OrderButton);
		OrderButton.addActionListener(this);
		contain.add(editButton);
		editButton.addActionListener(this);
		contain.add(InfoButton);
		InfoButton.addActionListener(this);
		setVisible(true);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == DishButton) {//search dishes
			new DishView(id, 2);
		}
		if (e.getSource() == OrderButton) {//view orders
			new OrderView(id, 2);
		}
		if (e.getSource() == editButton) {//edit personal info
			new EditInfo(id);
		}
		if (e.getSource() == InfoButton) {//view personal info
			new Info(id, 2);
		}
	}

	public void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			this.dispose();
			setVisible(false);
			System.exit(0);
		}
	}
}
