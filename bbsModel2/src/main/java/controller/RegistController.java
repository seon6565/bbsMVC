package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import common.CommonUtil;
import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model2.bbs.BbsDAO;
import model2.bbs.BbsDTO;

/**
 * Servlet implementation class RegistController
 */
@MultipartConfig(
		maxFileSize = 1024*1024*1,
		maxRequestSize = 1024*1024*10
)
public class RegistController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String directory = getServletContext().getRealPath("/Upload");
		String directory = "D:\\java4\\JSP\\bbsModel2\\upload";
		BbsDAO dao = new BbsDAO();
		BbsDTO dto = new BbsDTO();
		String a = (String)request.getParameter("user_id").trim();
		String b = (String)request.getParameter("title").trim();
		String c = (String)request.getParameter("content").trim();
		int idx = 0;
		System.out.println(a+b+c);
		if(CommonUtil.isValue(a)&&CommonUtil.isValue(b)&&CommonUtil.isValue(c)){
			dto.setUser_id(a);
			dto.setTitle(b);
			dto.setContent(c);
			dao.bbsRegist(dto);
			if(request.getPart("file")!=null) {
			ArrayList<String> arrFileName = FileUtil.uploadFile2(request, directory);
			try {
				dao.psmt = dao.conn.prepareStatement("SELECT LAST_INSERT_ID(idx) FROM tbl_bbs order by idx DESC LIMIT 1;");
				dao.rs = dao.psmt.executeQuery();
				dao.rs.next();
				idx = dao.rs.getInt("LAST_INSERT_ID(idx)");
				System.out.print("idx"+idx);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(String orgFileName:arrFileName) {
				
				String savedFileName = FileUtil.renameFile(directory, orgFileName);
				FileUtil.registFile(request, orgFileName, savedFileName,idx);
				}
			}
			else {
				System.out.print("파일이 없습니다.");
			}
			response.sendRedirect("list");
		}
		else{
			response.sendRedirect("list?result=fail");
		}
		dao.close();
		
	}

}
