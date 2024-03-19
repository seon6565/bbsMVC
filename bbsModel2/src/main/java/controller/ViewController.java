package controller;

import java.io.IOException;

import common.CommonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model2.bbs.BbsDAO;
import model2.bbs.BbsDTO;

/**
 * Servlet implementation class ViewController
 */
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BbsDAO dao = new BbsDAO(); 
		String bbs_idx = request.getParameter("idx");
		int idx = 0;
		//idx = Integer.parseInt(request.getParameter("idx"));
		idx = CommonUtil.parseIntCheck(bbs_idx.trim());
		//idx = (bbs_idx !=null && !bbs_idx.isEmpty() ? Integer.parseInt("bbs_idx") :0);
		BbsDTO bbsView = dao.bbsView(idx);
		dao.close();
		
		int read_cnt=0;
		String user_id="";
		String reg_date="";
		String title="";
		String content="";
		if(bbsView!=null){
			read_cnt= bbsView.getRead_cnt();
			user_id=bbsView.getUser_id();
			if(reg_date!=null && reg_date!=""){
				reg_date=bbsView.getReg_date().toString();
			}
			title=bbsView.getTitle();
			content=bbsView.getContent();
			content = (content!=null ? content.replace("\n\r","<br>"):"");
			content = (content!=null ? content.replace(" ","&nbsp"):"");
			
			request.setAttribute("idx", idx);
			request.setAttribute("read_cnt", read_cnt);
			request.setAttribute("user_id", user_id);
			request.setAttribute("title", title);
			request.setAttribute("content", content);
			request.setAttribute("reg_date", reg_date);
		}
		
		request.getRequestDispatcher("/bbs/view.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
