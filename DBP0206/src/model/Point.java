package model;

import java.sql.Date;

public class Point {
	private int pay_no;
	private int a_no;
	private int pay_price;
	private Date pay_date;
	
	public Point(int pay_no, int a_no, int pay_price, Date pay_date) {
		this.pay_no = pay_no;
		this.a_no = a_no;
		this.pay_price = pay_price;
		this.pay_date = pay_date;
	}
	public int getPay_no() {
		return pay_no;
	}
	public void setPay_no(int pay_no) {
		this.pay_no = pay_no;
	}
	public int getA_no() {
		return a_no;
	}
	public void setA_no(int a_no) {
		this.a_no = a_no;
	}
	public int getPay_price() {
		return pay_price;
	}
	public void setPay_price(int pay_price) {
		this.pay_price = pay_price;
	}
	public Date getPay_date() {
		return pay_date;
	}
	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}
}