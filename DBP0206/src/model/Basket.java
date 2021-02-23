package model;

public class Basket {
	private int item_no;
	private int c_no;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public Basket(int item_no, int c_no) {
		super();
		this.item_no = item_no;
		this.c_no = c_no;
	}
	public int getItem_no() {
		return item_no;
	}
	public void setItem_no(int item_no) {
		this.item_no = item_no;
	}
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}

}
