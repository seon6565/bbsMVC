package controller;

import java.io.IOException;
import java.util.List;

import fileupload.FileDAO;
import fileupload.FileDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileListController
 */
public class FileListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileDAO dao =new FileDAO();
		List<FileDTO> fileLists = dao.fileList(request.getParameter("idx"));
		if(fileLists!=null){
			request.setAttribute("fileLists", fileLists);
		}
		request.getRequestDispatcher("fileList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
