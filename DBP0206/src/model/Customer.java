package model;

import java.io.Serializable;

/**
 * 사용자 관리를 위해 필요한 도메인 클래스. CUSTOMER 테이블과 대응됨
 */
public class Customer extends User implements Serializable{

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String user_no, String name, String email, String phone, String pwd, String addr) {
		super(user_no, name, email, phone, pwd, addr);
		// TODO Auto-generated constructor stub
	}
	
	public Customer(String no, String user_no, String name, String email, String phone, String pwd, String addr) {
		super(no, user_no, name, email, phone, pwd, addr);
		// TODO Auto-generated constructor stub
	}

}
