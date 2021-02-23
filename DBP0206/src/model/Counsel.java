package model;

public class Counsel {
	private int counsel_no;
	private int item_no;
	private int a_no;
	private int c_no;
	private String counsel_detail;
	private String counsel_date;
	private String counsel_tf;
	private String a_name;
	
	public Counsel(int item_no, int a_no, int c_no, String counsel_detail, String counsel_date) {
		this.item_no = item_no;
		this.a_no = a_no;
		this.c_no = c_no;
		this.counsel_detail = counsel_detail;
		this.counsel_date = counsel_date;
	}
	
	public Counsel(int counsel_no, int item_no, int a_no, int c_no, String counsel_detail, String counsel_date,
			String counsel_tf) {
		super();
		this.counsel_no = counsel_no;
		this.item_no = item_no;
		this.a_no = a_no;
		this.c_no = c_no;
		this.counsel_detail = counsel_detail;
		this.counsel_date = counsel_date;
		this.counsel_tf = counsel_tf;
	}
	public Counsel(int counsel_no, int item_no, int a_no, int c_no, String counsel_detail, String counsel_date,
			String counsel_tf, String a_name) {
		super();
		this.counsel_no = counsel_no;
		this.item_no = item_no;
		this.a_no = a_no;
		this.c_no = c_no;
		this.counsel_detail = counsel_detail;
		this.counsel_date = counsel_date;
		this.counsel_tf = counsel_tf;
		this.a_name = a_name;
	}
	
	public String getA_name() {
		return a_name;
	}
	public void setA_name(String a_name) {
		this.a_name = a_name;
	}
	public int getCounsel_no() {
		return counsel_no;
	}
	public void setCounsel_no(int counsel_no) {
		this.counsel_no = counsel_no;
	}
	public int getItem_no() {
		return item_no;
	}
	public void setItem_no(int item_no) {
		this.item_no = item_no;
	}
	public int getA_no() {
		return a_no;
	}
	public void setA_no(int a_no) {
		this.a_no = a_no;
	}
	
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public String getCounsel_detail() {
		return counsel_detail;
	}
	public void setCounsel_detail(String counsel_detail) {
		this.counsel_detail = counsel_detail;
	}
	public String getCounsel_date() {
		return counsel_date;
	}
	public void setCounsel_date(String counsel_date) {
		this.counsel_date = counsel_date;
	}
	public String getCounsel_tf() {
		return counsel_tf;
	}
	public void setCounsel_tf(String counsel_tf) {
		this.counsel_tf = counsel_tf;
	}
}
