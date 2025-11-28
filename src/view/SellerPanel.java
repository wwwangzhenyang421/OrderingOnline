package view;

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.OrderView;
import controller.DishView;
import controller.EditInfo;
import controller.Info;
import controller.AddDishes;
import controller.DeleteDishes;
import controller.DishesEditInfo;



@SuppressWarnings("serial")
public class SellerPanel extends JFrame implements ActionListener {
	/*
	 * Main screen after seller login
	 */
	JPanel contain;
	String id, WindowID;
	JButton DishButton, OrderButton, AddDishesButton, DeleteDishesButton, DishesEditButton, editButton, InfoButton;

	public SellerPanel(String id) {
		super("seller");
		this.id = id;
		try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/data/seller.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (line.contains(id)) {
					String[] parts = line.split(" ");
					WindowID = parts[3];
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			// Handle exceptions that may occur when reading files
		}
		setLocation(300, 200);
		setSize(300, 420);
		contain = new JPanel();
		contain.setLayout(null);
		add(contain);
		DishButton = new JButton("Menu Search");
		OrderButton = new JButton("Order Search");
		AddDishesButton = new JButton("Add Dishes");
		DeleteDishesButton = new JButton("Delete Dishes");
		DishesEditButton = new JButton("Edit Dishes");
		editButton = new JButton("Edit Info");
		InfoButton = new JButton("seller Info");
		DishButton.setBounds(70, 40, 160, 30);
		OrderButton.setBounds(70, 80, 160, 30);
		AddDishesButton.setBounds(70, 120, 160, 30);
		DeleteDishesButton.setBounds(70, 160, 160, 30);
		DishesEditButton.setBounds(70, 200, 160, 30);
		editButton.setBounds(70, 240, 160, 30);
		InfoButton.setBounds(70, 280, 160, 30);
		
		contain.add(DishButton);
		DishButton.addActionListener(this);
		contain.add(OrderButton);
		OrderButton.addActionListener(this);
		contain.add(AddDishesButton);
		AddDishesButton.addActionListener(this);
		contain.add(DeleteDishesButton);
		DeleteDishesButton.addActionListener(this);
		contain.add(DishesEditButton);
		DishesEditButton.addActionListener(this);
		contain.add(editButton);
		editButton.addActionListener(this);
		contain.add(InfoButton);
		InfoButton.addActionListener(this);
		setVisible(true);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == DishButton) {//search dishes
			new DishView(id, 3);
		}
		if (e.getSource() == OrderButton) {//view orders
			new OrderView(id, 3);
		}
		if (e.getSource() == AddDishesButton) {//add a new dish
			new AddDishes(WindowID);
		}
		if (e.getSource() == DeleteDishesButton) {//delete an existed dish
			new DeleteDishes();
		}
		if (e.getSource() == DishesEditButton) {//edit existed dishes
			new DishesEditInfo(WindowID);
		}
		if (e.getSource() == editButton) {//edit personal info
			new EditInfo(id);
		}
		if (e.getSource() == InfoButton) {//view personal info
			new Info(id, 3);
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
