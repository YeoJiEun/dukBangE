package controller.customer;

import javax.servlet.http.HttpSession;

public class UserSessionUtils {
    public static final String USER_SESSION_KEY = "user";

    /* 현재 로그인한 사용자의 ID를 구함 */
    public static String getUserFromSession(HttpSession session) {
        String c_no = (String)session.getAttribute(USER_SESSION_KEY);
        return c_no;
    }

    /* 로그인한 상태인지를 검사 */
    public static boolean isLogined(HttpSession session) {
        if (getUserFromSession(session) != null) {
            return true;
        }
        return false;
    }
 
    /* 현재 로그인한 사용자의 ID가 userId인지 검사 */
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
