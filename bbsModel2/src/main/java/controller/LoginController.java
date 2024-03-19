package controller;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.MemberDAO;
import member.MemberDTO;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String) request.getParameter("id");
		String pwd = (String) request.getParameter("pwd");

		ServletContext application = request.getServletContext();
		
		String driver = application.getInitParameter("Driver");
		String url = application.getInitParameter("URL");
		String dbId = application.getInitParameter("dbId");
		String dbPwd = application.getInitParameter("dbPwd");

		MemberDAO dao = new MemberDAO(driver,url,dbId,dbPwd);
		MemberDTO dto = dao.getMemberInfo(id,pwd);
		
		HttpSession session = request.getSession();
		
		if(dto.getMemberId() != null){
			session.setAttribute("UserId",dto.getMemberId());
			session.setAttribute("UserName",dto.getName());
			session.setAttribute("UserPwd",dto.getPwd());
			response.sendRedirect("./list");
		}
		else if(dto.getPwd() == null){
			request.setAttribute("errMsg","비밀번호 오류");
			request.getRequestDispatcher("../login/login.jsp").forward(request,response);
		}
		dao.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
