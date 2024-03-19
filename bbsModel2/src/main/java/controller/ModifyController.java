package controller;

import java.io.IOException;
import java.util.ArrayList;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model2.bbs.BbsDAO;
import model2.bbs.BbsDTO;

/**
 * Servlet implementation class ModifyController
 */
@MultipartConfig(
		maxFileSize = 1024*1024*1,
		maxRequestSize = 1024*1024*10
)
public class ModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String directory = getServletContext().getRealPath("/Upload");
		String directory = "D:\\java4\\JSP\\bbsModel2\\upload";
		BbsDAO dao = new BbsDAO(); 
		int idx = Integer.parseInt(request.getParameter("idx"));
		BbsDTO bbsView = dao.bbsView(idx);
		bbsView.setIdx(idx);
		bbsView.setTitle((String)request.getParameter("title"));
		bbsView.setContent((String)request.getParameter("content"));
		dao.bbsModify(bbsView);
		
		if(request.getPart("file")!=null) {
		ArrayList<String> arrFileName = FileUtil.uploadFile2(request, directory);

		for(String orgFileName:arrFileName) {
			
			String savedFileName = FileUtil.renameFile(directory, orgFileName);
			FileUtil.registFile(request, orgFileName, savedFileName,idx);
			}
		}
		else {
			System.out.print("파일이 없습니다.");
		}
		dao.close();
		response.sendRedirect("list");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
