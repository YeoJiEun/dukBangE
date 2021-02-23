package model;

import java.io.Serializable;

public class Agent extends User implements Serializable{

	private String OFFICE;
	private String POINT;
	private String BAN;
	
	public Agent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Agent(String user_no, String name, String email, String phone, String pwd, String addr,String office) {
		super(user_no, name, email, phone, pwd, addr);
		this.OFFICE = office;
	}

	public Agent(String no, String user_no, String name, String email, String phone, String pwd, String addr, String office, String point, String ban) {
		super(no, user_no, name, email, phone, pwd, addr);
		this.OFFICE = office;
		this.POINT = point;
		this.BAN = ban;
	}

	public String getOFFICE() {
		return OFFICE;
	}

	public void setOFFICE(String oFFICE) {
		OFFICE = oFFICE;
	}

	public String getPOINT() {
		return POINT;
	}

	public void setPOINT(String pOINT) {
		POINT = pOINT;
	}

	public String getBAN() {
		return BAN;
	}

	public void setBAN(String bAN) {
		BAN = bAN;
	}
	
}
