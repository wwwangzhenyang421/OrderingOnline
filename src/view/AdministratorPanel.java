package view;

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


import controller.AddUser;
import controller.DeleteUser;
import controller.DeleteOrder;

@SuppressWarnings("serial")
public class AdministratorPanel extends JFrame implements ActionListener {
    /*
	 * Main screen after administrator login
	 */
	JPanel contain;
	String id, WindowID;
	JButton AddUserButton, DeleteUserButton, DeleteOrdersButton;

	public AdministratorPanel(String id) {
		super("administrator");
		this.id = id;
		setLocation(300, 200);
		setSize(300, 340);
		contain = new JPanel();
		contain.setLayout(null);
		add(contain);
		AddUserButton = new JButton("Add User");
		DeleteUserButton = new JButton("Delete User");
		DeleteOrdersButton = new JButton("Delete Order");

		AddUserButton.setBounds(70, 40, 160, 30);
		DeleteUserButton.setBounds(70, 80, 160, 30);
		DeleteOrdersButton.setBounds(70, 120, 160, 30);
		
		contain.add(AddUserButton);
		AddUserButton.addActionListener(this);
		contain.add(DeleteUserButton);
		DeleteUserButton.addActionListener(this);
		contain.add(DeleteOrdersButton);
		DeleteOrdersButton.addActionListener(this);
		
		setVisible(true);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == AddUserButton) {//add a new user
			new AddUser();
		}
		if (e.getSource() == DeleteUserButton) {//delete an existed user
			new DeleteUser();
		}
		if (e.getSource() == DeleteOrdersButton) {//delete an existed order
			new DeleteOrder();
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
