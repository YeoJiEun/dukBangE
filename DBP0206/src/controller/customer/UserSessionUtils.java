package controller.customer;

import javax.servlet.http.HttpSession;

public class UserSessionUtils {
    public static final String USER_SESSION_KEY = "user";

    /* ���� �α����� ������� ID�� ���� */
    public static String getUserFromSession(HttpSession session) {
        String c_no = (String)session.getAttribute(USER_SESSION_KEY);
        return c_no;
    }

    /* �α����� ���������� �˻� */
    public static boolean isLogined(HttpSession session) {
        if (getUserFromSession(session) != null) {
            return true;
        }
        return false;
    }
 
    /* ���� �α����� ������� ID�� userId���� �˻� */
    public static boolean isLoginCustomer(String c_no, HttpSession session) {
        if (!isLogined(session)) {
            return false;
        }
        if (c_no == null) {
            return false;
        }
        return c_no.equals(getUserFromSession(session));
    }    
}
