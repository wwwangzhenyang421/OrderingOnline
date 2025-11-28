package model;


public class Student extends User {

	private String address;
	private String phoneNum;
	
	public Student() {
		super();
	}


	public Student(String id, String pwd, String name, String address, String phoneNum) {
		super(id, pwd, name);
		this.address = address;
		this.phoneNum = phoneNum;
	}
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
}
