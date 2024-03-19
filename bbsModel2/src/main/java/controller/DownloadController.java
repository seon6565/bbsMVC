package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadController
 */
public class DownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String directory = getServletContext().getRealPath("/Upload");
		String saveDirectory = "D:\\java4\\JSP\\bbsModel2\\upload";
		String saveFileName = request.getParameter("saveFile");
		String orgFileName = request.getParameter("orgFile");
		
		try{
			File file = new File(saveDirectory, saveFileName);
			InputStream is = new FileInputStream(file);
			String client = request.getHeader("User-Agent");
			if(client.indexOf("WOW64") == -1){
				orgFileName = new String(orgFileName.getBytes("UTF-8"),"ISO-8859-1");
			}
			else{
				orgFileName = new String(orgFileName.getBytes("KSC5601"),"ISO-8859-1");
			}
			//출력헤더 설정
			response.reset();
			response.setContentType("application/octect-stream");
			response.setHeader("Content-Disposition","attachment; filename=\""+orgFileName + "\"");
			response.setHeader("Content-Length",""+file.length());
			
			//out.clear();
			//out = pageContext.pushBody();
			
			
			OutputStream oStream = response.getOutputStream();
			byte b[] = new byte[(int)file.length()];
			int readBuffer = 0;
			while ((readBuffer = is.read(b)) > 0){
				oStream.write(b,0,readBuffer);
			}
			is.close();
			oStream.close();
		}
		catch(FileNotFoundException e){
			System.out.println("파일을 찾을 수 없습니다.");
		}
		
		catch(Exception e){
			System.out.println("파일 다운로드시 에러 발생");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
