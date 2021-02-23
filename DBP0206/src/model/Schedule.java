package model;
import java.sql.Date;
public class Schedule {
	private int schejule_no;
	private int a_no;
	private Date avaliable_date;
	
	public int getSchejule_no() {
		return schejule_no;
	}
	public void setSchejule_no(int schejule_no) {
		this.schejule_no = schejule_no;
	}
	public int getA_no() {
		return a_no;
	}
	public void setA_no(int a_no) {
		this.a_no = a_no;
	}
	public Date getAvaliable_date() {
		return avaliable_date;
	}
	public void setAvaliable_date(Date avaliable_date) {
		this.avaliable_date = avaliable_date;
	}
	public Schedule(int schejule_no, int a_no, Date avaliable_date) {
		super();
		this.schejule_no = schejule_no;
		this.a_no = a_no;
		this.avaliable_date = avaliable_date;
	}
	
	public Schedule(int a_no, Date a) {
		this.a_no = a_no;
		this.avaliable_date = a;
	}

}
