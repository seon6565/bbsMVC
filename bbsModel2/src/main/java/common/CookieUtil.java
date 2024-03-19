package common;
import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtil{
	public static void setCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String id = (String)request.getParameter("id");
		String id_check = (String)request.getParameter("id_save");
		if(id_check!=null && id_check.equals("checked")){
		Cookie cookie = new Cookie("id_save",id);
		response.addCookie(cookie);
		Cookie cookie2 = new Cookie("id_save_flag",id_check);
		response.addCookie(cookie2);
		}
		response.sendRedirect("login.jsp");
	}
	
	public static ArrayList<String> getCookie(HttpServletRequest request) {
		ArrayList<String> valList = new ArrayList<>();
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie c : cookies){
				String name = c.getName();
				String val = c.getValue();
				if(name.equals("id_save_flag")){
					valList.add("checked");
				}
				if(name.equals("id_save")){
					valList.add(val);
				}
			}
		}
		return valList;
	}
	
	public static void deleteCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie c : cookies){
				String name = c.getName();
				if(name.equals("id_save_flag")){
					c.setMaxAge(0);
					response.addCookie(c);
				}
				if(name.equals("id_save")){
					c.setMaxAge(0);
					response.addCookie(c);
				}
			}
		}
		response.sendRedirect("login.jsp");
	}

}