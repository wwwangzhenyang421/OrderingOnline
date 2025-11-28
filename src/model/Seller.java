package model;


public class Seller extends User {

	private String WindowID;
	
	public Seller() {
		super();
	}


	public Seller(String id, String pwd, String name, String WindowID) {
		super(id, pwd, name);
		this.WindowID = WindowID;
	}
	
	
	public String getWindowID() {
		return WindowID;
	}
	public void setWindowID(String WindowID) {
		this.WindowID = WindowID;
	}
}
