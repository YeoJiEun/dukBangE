package model;

import java.io.Serializable;

/**
 * ����� ������ ���� �ʿ��� ������ Ŭ����. CUSTOMER ���̺�� ������
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
