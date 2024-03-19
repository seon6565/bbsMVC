package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model2.bbs.BbsDAO;
import model2.bbs.BbsDTO;

/**
 * Servlet implementation class ModifyConnect
 */
public class ModifyConnect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BbsDAO dao = new BbsDAO(); 
		int idx = Integer.parseInt(request.getParameter("idx"));
		BbsDTO bbsView = dao.bbsView(idx);
		request.setAttribute("idx", bbsView.getIdx()+"");
		request.setAttribute("title", bbsView.getTitle());
		request.setAttribute("content", bbsView.getContent());
		dao.close();
		request.getRequestDispatcher("modify.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
