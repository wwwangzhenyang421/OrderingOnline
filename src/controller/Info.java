package controller;

import java.awt.AWTEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.*;

public class Info extends JFrame {    
	/**
	 * User access to personal information
	 */
	private static final long serialVersionUID = 1L;
	JLabel idLabel, nameLabel, addressLabel, phoneLabel;
	String id, name, pwd, address, windowID, phone;
	int flag;
	JPanel contain;
	
	Student stu;
	Teacher t;
	Seller ad;

	public Info(String id, int flag) {
		super("Information");
		this.id = id;
		this.flag = flag;
		setSize(300, 340);
		setLocation(600, 400);
		contain = new JPanel();
        contain.setLayout(null);
		String file = "";
		if (flag==1){
			// file = "D://test//student.txt";
			file = System.getProperty("user.dir")+"/data/student.txt";
		}
		else if(flag==2){
			// file = "D://test//teacher.txt";
			file = System.getProperty("user.dir")+"/data/teacher.txt";
		}else{
			file = System.getProperty("user.dir")+"/data/seller.txt";
		}
		
		 //StringBuilder result = new StringBuilder();
	        try{
	            BufferedReader br = new BufferedReader(new FileReader(file));
	            String s = null;
	            while((s = br.readLine())!=null){
	            	String[] result = s.split(" ");
	            	if(result[0].equals(id)){
	            		id = result[0];
	            		name = result[1];
						pwd = result[2];
	            		address = result[3];
						if(flag == 1 || flag == 2){
							phone = result[4];
							System.out.println(result[4]);
						}
	            		
	            		
	            		if(flag == 1){
	            			stu = new Student( id, pwd, name, address, phone);
	            			idLabel = new JLabel("ID:" + " " + stu.getId());
							idLabel.setBounds(25, 60, 200, 35);
	            			nameLabel = new JLabel("Name:" + " " + stu.getName());
							nameLabel.setBounds(25, 20, 200, 35);
	            			addressLabel = new JLabel("Address:" + " " + stu.getAddress());
							addressLabel.setBounds(25,100, 200, 35);
							phoneLabel = new JLabel("PhoneNumber" + " " + stu.getPhoneNum());
							phoneLabel.setBounds(25,140, 200, 35);

	            			
	            		}else if(flag==2){
	            			t = new Teacher( id,  pwd, name, address, phone);
	            			idLabel = new JLabel("ID:" + " " + t.getId());
							idLabel.setBounds(25, 60, 200, 35);
	            			nameLabel = new JLabel("Name:" + " " + t.getName());
							nameLabel.setBounds(25, 20, 200, 35);
	            			addressLabel = new JLabel("Address:" + " " + t.getAddress());
							addressLabel.setBounds(25,100, 200, 35);
							phoneLabel = new JLabel("PhoneNumber" + " " + t.getPhoneNum());
							phoneLabel.setBounds(25,140, 200, 35);
	            			
	            		}else{
							ad=new Seller(id,pwd,name,address);
							idLabel = new JLabel("ID:" + " " + ad.getId());
							idLabel.setBounds(25, 60, 200, 35);
	            			nameLabel = new JLabel("Name:" + " " + ad.getName());
							nameLabel.setBounds(25, 20, 200, 35);
	            			addressLabel = new JLabel("WindowID:" + " " + ad.getWindowID());
							addressLabel.setBounds(25,100, 200, 35);
						}
	            		
	            	}
	            }
	            br.close();    
	        }catch(Exception e){
	            e.printStackTrace();
	        }
			
		
		contain.add(idLabel);
		contain.add(nameLabel);
		if(flag == 1 || flag ==2){
			contain.add(phoneLabel);
		}
		contain.add(addressLabel);
		add(contain);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		setVisible(true);
		
	}

	public void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			this.dispose();
			setVisible(false);
		}
	}

	public static void main(String[] args) {
		new Info("A01", 3);
	}
}
