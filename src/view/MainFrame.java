package view;

import controller.AddUser;
import controller.CheckInfo;
import java.awt.AWTEvent;
import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class MainFrame extends JFrame implements ActionListener {
	// Log in to the main interface
	private static final long serialVersionUID = 1L;
	JTextField idTextField;
	JPasswordField passwdTextField;
	JLabel idLabel, passwdLabel;
	Choice choice;
	JButton login, register;
	JPanel contain;
	
	int count = 0;

	public MainFrame() {
		super("Account login & register");
		setLocation(300, 200);
		setSize(300, 340);
		contain = new JPanel();
		contain.setLayout(null);
		idLabel = new JLabel("id");
		passwdLabel = new JLabel("PW");
		idTextField = new JTextField();
		passwdTextField = new JPasswordField();
		login = new JButton("login");
		register = new JButton("register");
		choice = new Choice();
		choice.addItem("Student");
		choice.addItem("Teacher");
		choice.addItem("Seller");
		choice.addItem("Administrator");
		idLabel.setBounds(40, 45, 75, 35);
		idTextField.setBounds(80, 45, 150, 35);
		passwdLabel.setBounds(40, 100, 75, 35);
		passwdTextField.setBounds(80, 100, 150, 35);
		choice.setBounds(80, 160, 150, 35);
		register.setBounds(30, 220, 100, 30);
		login.setBounds(150, 220, 100, 30);
		contain.add(idLabel);
		contain.add(idTextField);
		contain.add(passwdLabel);
		contain.add(passwdTextField);
		contain.add(choice);
		contain.add(register);
		contain.add(login);
		login.addActionListener(this);
		register.addActionListener(this);
		add(contain);
		setVisible(true);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == login) {
			String ch = (String) choice.getSelectedItem();
			if (ch == "Student") {
				if ((new CheckInfo().isMember("student", idTextField.getText(),
						new String(passwdTextField.getPassword()))) == 1) {
					setVisible(false);
					new StudentsPanel(idTextField.getText());
				} else {
					count += 1;
					if (count <= 5) {
						JOptionPane.showMessageDialog(null, "There is no such user, or the password is entered incorrectly!",
								"Error", JOptionPane.INFORMATION_MESSAGE);
					}
					if (count > 5) {
						JOptionPane.showMessageDialog(null, "More than 5 errors!",
								"Error", JOptionPane.INFORMATION_MESSAGE);
						this.dispose();
						setVisible(false);
						System.exit(0);
					}
				}
			} else if (ch == "Teacher") {
				if ((new CheckInfo().isMember("teacher", idTextField.getText(),
						new String(passwdTextField.getPassword(), 0,
								passwdTextField.getPassword().length))) == 1) {
					setVisible(false);
					new TeachersPanel(idTextField.getText());
				} else {
					count += 1;
					if (count <= 5) {
						JOptionPane.showMessageDialog(null, "There is no such user, or the password is entered incorrectly!",
								"Error", JOptionPane.INFORMATION_MESSAGE);
					}
					if (count > 5) {
						JOptionPane.showMessageDialog(null, "More than 5 errors!",
								"Error", JOptionPane.INFORMATION_MESSAGE);
						this.dispose();
						setVisible(false);
						System.exit(0);
					}
				}
			} else if (ch == "Seller") {
				if ((new CheckInfo().isMember("seller", idTextField
						.getText(), new String(passwdTextField.getPassword(),
						0, passwdTextField.getPassword().length))) == 1) {
					setVisible(false);
					new SellerPanel(idTextField.getText());
				} else {
					count += 1;
					if (count <= 5) {
						JOptionPane.showMessageDialog(null, "There is no such user, or the password is entered incorrectly!",
								"Error", JOptionPane.INFORMATION_MESSAGE);
					}
					if (count > 5) {
						JOptionPane.showMessageDialog(null, "More than 5 errors!",
								"Error", JOptionPane.INFORMATION_MESSAGE);
						this.dispose();
						setVisible(false);
						System.exit(0);
					}
				}
			} else if (ch == "Administrator") {
				if ((new CheckInfo().isMember("administrator", idTextField
						.getText(), new String(passwdTextField.getPassword(),
						0, passwdTextField.getPassword().length))) == 1) {
					setVisible(false);
					new AdministratorPanel(idTextField.getText());
				} else {
					count += 1;
					if (count <= 5) {
						JOptionPane.showMessageDialog(null, "There is no such user, or the password is entered incorrectly!",
								"Error", JOptionPane.INFORMATION_MESSAGE);
					}
					if (count > 5) {
						JOptionPane.showMessageDialog(null, "More than 5 errors!",
								"Error", JOptionPane.INFORMATION_MESSAGE);
						this.dispose();
						setVisible(false);
						System.exit(0);
					}
				}
			}
		}
		else if(e.getSource() == register){
			new AddUser();
		}
		
	}

	

	public void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			this.dispose();
			setVisible(false);
			System.exit(0);
		}
	}

	// 注意：应用程序的唯一入口已改为 src/App.java
	// 如需单独测试此窗口，可取消下面的注释
	// Note: The single entry point is now src/App.java
	// Uncomment below if you need to test this window separately
	/*
	public static void main(String[] args) {
		new MainFrame();
	}
	*/
}