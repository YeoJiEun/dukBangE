package model;

import java.sql.Date;

/**
 * 물건 관리를 위해 필요한 도메인 클래스. ITEM 테이블과 대응됨
 */
public class Item {
	private String a_name;
	
	private int item_no;
	private int a_no;
	private String item_addr;
	private String item_deal_type;
	private String item_type;
	private int item_cost;
	private int item_deposit;
	private int item_layer;
	private int item_size;
	private int item_manage_cost;
	private String item_park_tf;
	private String item_ele_tf;
	private String item_pet_tf;
	private Date item_avail_date;
	private String item_close_tf;
	private int item_hits;

	public Item() { }		// 기본 생성자
	
	public Item(int item_no, String item_addr) {
		this.item_no = item_no;
		this.item_addr = item_addr;
	}
	
	public Item(int item_no, String item_addr, String item_deal_type, int item_size, int item_cost, int item_deposit) {
		this.item_no = item_no;
		this.item_addr = item_addr;
		this.item_deal_type = item_deal_type;
		this.item_size = item_size;
		this.item_cost = item_cost;
		this.item_deposit = item_deposit;
	}//찜목록
	
	public Item(int item_no, String item_addr, String item_deal_type, String item_type, int item_cost, int item_deposit) {
		this.item_no = item_no;
		this.item_addr = item_addr;
		this.item_deal_type = item_deal_type;
		this.item_type = item_type;
		this.item_cost = item_cost;
		this.item_deposit = item_deposit;
	}
	
	public Item(int item_no, String item_addr, String item_deal_type, String item_type, int item_cost, int item_deposit, int item_hits) {
		this.item_no = item_no;
		this.item_addr = item_addr;
		this.item_deal_type = item_deal_type;
		this.item_type = item_type;
		this.item_cost = item_cost;
		this.item_deposit = item_deposit;
		this.item_hits = item_hits;
	}

	public Item(int item_no, int a_no, String item_addr, String item_deal_type, String item_type, int item_cost,
			int item_deposit, int item_layer, int item_size, int item_manage_cost, String item_park_tf,
			String item_ele_tf, String item_pet_tf, Date item_avail_date, String item_close_tf, int item_hits) {
		super();
		this.item_no = item_no;
		this.a_no = a_no;
		this.item_addr = item_addr;
		this.item_deal_type = item_deal_type;
		this.item_type = item_type;
		this.item_cost = item_cost;
		this.item_deposit = item_deposit;
		this.item_layer = item_layer;
		this.item_size = item_size;
		this.item_manage_cost = item_manage_cost;
		this.item_park_tf = item_park_tf;
		this.item_ele_tf = item_ele_tf;
		this.item_pet_tf = item_pet_tf;
		this.item_avail_date = item_avail_date;
		this.item_close_tf = item_close_tf;
		this.item_hits = item_hits;
	}
	
	public Item(String a_name, int item_no, int a_no, String item_addr, String item_deal_type, String item_type, int item_cost,
			int item_deposit, int item_layer, int item_size, int item_manage_cost, String item_park_tf,
			String item_ele_tf, String item_pet_tf, Date item_avail_date, String item_close_tf, int item_hits) {
		super();
		this.a_name = a_name;
		this.item_no = item_no;
		this.a_no = a_no;
		this.item_addr = item_addr;
		this.item_deal_type = item_deal_type;
		this.item_type = item_type;
		this.item_cost = item_cost;
		this.item_deposit = item_deposit;
		this.item_layer = item_layer;
		this.item_size = item_size;
		this.item_manage_cost = item_manage_cost;
		this.item_park_tf = item_park_tf;
		this.item_ele_tf = item_ele_tf;
		this.item_pet_tf = item_pet_tf;
		this.item_avail_date = item_avail_date;
		this.item_close_tf = item_close_tf;
		this.item_hits = item_hits;
	}

	public void update(Item updateItem) {
		this.item_no = updateItem.item_no;
		this.a_no = updateItem.a_no;
		this.item_addr = updateItem.item_addr;
		this.item_deal_type = updateItem.item_deal_type;
		this.item_type = updateItem.item_type;
		this.item_cost = updateItem.item_cost;
		this.item_deposit = updateItem.item_deposit;
		this.item_layer = updateItem.item_layer;
		this.item_size = updateItem.item_size;
		this.item_manage_cost = updateItem.item_manage_cost;
		this.item_park_tf = updateItem.item_park_tf;
		this.item_ele_tf = updateItem.item_ele_tf;
		this.item_pet_tf = updateItem.item_pet_tf;
		this.item_avail_date = updateItem.item_avail_date;
		this.item_close_tf = updateItem.item_close_tf;
		this.item_hits = updateItem.item_hits;
    }
	
	public String getA_name() {
		return a_name;
	}

	public void setA_name(String a_name) {
		this.a_name = a_name;
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

	public String getItem_addr() {
		return item_addr;
	}

	public void setItem_addr(String item_addr) {
		this.item_addr = item_addr;
	}

	public String getItem_deal_type() {
		return item_deal_type;
	}

	public void setItem_deal_type(String item_deal_type) {
		this.item_deal_type = item_deal_type;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public int getItem_cost() {
		return item_cost;
	}

	public void setItem_cost(int item_cost) {
		this.item_cost = item_cost;
	}

	public int getItem_deposit() {
		return item_deposit;
	}

	public void setItem_deposit(int item_deposit) {
		this.item_deposit = item_deposit;
	}

	public int getItem_layer() {
		return item_layer;
	}

	public void setItem_layer(int item_layer) {
		this.item_layer = item_layer;
	}

	public int getItem_size() {
		return item_size;
	}

	public void setItem_size(int item_size) {
		this.item_size = item_size;
	}

	public int getItem_manage_cost() {
		return item_manage_cost;
	}

	public void setItem_manage_cost(int item_manage_cost) {
		this.item_manage_cost = item_manage_cost;
	}

	public String getItem_park_tf() {
		return item_park_tf;
	}

	public void setItem_park_tf(String item_park_tf) {
		this.item_park_tf = item_park_tf;
	}

	public String getItem_ele_tf() {
		return item_ele_tf;
	}

	public void setItem_ele_tf(String item_ele_tf) {
		this.item_ele_tf = item_ele_tf;
	}

	public String getItem_pet_tf() {
		return item_pet_tf;
	}

	public void setItem_pet_tf(String item_pet_tf) {
		this.item_pet_tf = item_pet_tf;
	}

	public Date getItem_avail_date() {
		return item_avail_date;
	}

	public void setItem_avail_date(Date item_avail_date) {
		this.item_avail_date = item_avail_date;
	}

	public String getItem_close_tf() {
		return item_close_tf;
	}

	public void setItem_close_tf(String item_close_tf) {
		this.item_close_tf = item_close_tf;
	}

	public int getItem_hits() {
		return item_hits;
	}

	public void setItem_hits(int item_hits) {
		this.item_hits = item_hits;
	}

	/* 비밀번호 검사 -> 은영이가 해야 될 부분 */
//	public boolean matchPassword(String password) {
//		if (password == null) {
//			return false;
//		}
//		return this.password.equals(password);
//	}
//	
//	public boolean isSameUser(String userid) {
//        return this.userId.equals(userid);
//    }

	//아마 필요 없는 코드일듯...?
//	@Override
//	public String toString() {
//		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + ", phone="
//				+ phone + "]";
//	}	
}
