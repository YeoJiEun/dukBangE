package model;

public class User {
	String NO;
	String USER_NO;
	String NAME;
	String EMAIL;
	String PHONE;
	String PWD;
	String ADDR;
	
	public User() { }	
	
	public User(String user_no, String name, String email, String phone, String pwd,
			String addr) {
		this.USER_NO = user_no;
		this.NAME = name;
		this.EMAIL = email;
		this.PHONE = phone;
		this.PWD = pwd;
		this.ADDR = addr;
	}
	
	public User(String no,String user_no, String name, String email, String phone, String pwd,
			String addr) {
		this.NO = no;
		this.USER_NO = user_no;
		this.NAME = name;
		this.EMAIL = email;
		this.PHONE = phone;
		this.PWD = pwd;
		this.ADDR = addr;
	}
	
	public String getNO() {
		return NO;
	}

	public void setNO(String no) {
		NO = no;
	}	
	
	public String getUSER_NO() {
		return USER_NO;
	}

	public void setUSER_NO(String user_no) {
		USER_NO = user_no;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String email) {
		EMAIL = email;
	}

	public String getPHONE() {
		return PHONE;
	}

	public void setPHONE(String phone) {
		PHONE = phone;
	}

	public String getPWD() {
		return PWD;
	}

	public void setPWD(String pwd) {
		PWD = pwd;
	}

	public String getADDR() {
		return ADDR;
	}

	public void setADDR(String addr) {
		ADDR = addr;
	}

	/* 비밀번호 검사 */
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.PWD.equals(password);
	}
	
	public boolean isSameUser(String userid) {
        return this.EMAIL.equals(userid);
    }

	@Override
	public String toString() {
		return "user [NO=" + NO +"USER_NO=" + USER_NO + ", NAME=" + NAME + ", EMAIL=" + EMAIL
				+ ", PHONE=" + PHONE + ", PWD=" + PWD + ", ADDR=" + ADDR + "]";
	}
}
