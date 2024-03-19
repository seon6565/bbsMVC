package fileupload;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class FileUtil {
	
	public static String uploadFile(HttpServletRequest req, String directory) throws ServletException, IOException {
		Part part = req.getPart("file");
		String pHeader = part.getHeader("content-disposition");
		System.out.println("pHeader: "+ pHeader);
		
		String[] arrPartHeader = pHeader.split("filename=");
		String orgFileName = arrPartHeader[1].trim().replace("\"","");
		System.out.println("part테스트"+directory+File.separator+orgFileName);
		if(!orgFileName.isEmpty()) {
			part.write(directory+File.separator+orgFileName);
		}
		
		return orgFileName;
	}
	
	public static String renameFile(String directory, String fileName) {
		String ext = fileName.substring(fileName.lastIndexOf("."));
		String now = new SimpleDateFormat("yyyyMMdd_Hms").format(new Date());
		String newFileName = now+ext;
		
		File oldFile = new File(directory + File.separator + fileName);
		File newFile = new File(directory + File.separator + newFileName);
		oldFile.renameTo(newFile);
		
		return newFileName;
	}
	
	public static ArrayList<String> uploadFile2(HttpServletRequest req, String directory) throws ServletException, IOException {
		
		ArrayList<String> listFiles = new ArrayList<>();
		Collection<Part> parts = req.getParts();
		for(Part part : parts) {
			if(!part.getName().equals("file")) continue;
			
			String partHeader = part.getHeader("content-disposition");
			System.out.println("partHeader: "+ partHeader);
			
			String[] arrPartHeader = partHeader.split("filename=");
			String orgFileName = arrPartHeader[1].trim().replace("\"","");
			System.out.println("orgFileName: "+ orgFileName);
			System.out.println("part테스트"+directory+File.separator+orgFileName);
			if(!orgFileName.isEmpty()) {
				part.write(directory+File.separator+orgFileName);
			}
			listFiles.add(orgFileName);
		}
		
		return listFiles;
	}
	
	public static void registFile(HttpServletRequest req, String orgFileName, String sFileName, int idx) {
		String title = req.getParameter("title");
		StringBuffer cb = new StringBuffer();
		System.out.println("파일 외 폼값: "+ title + "\n"+cb.toString());
		
		FileDTO dto = new FileDTO();
		dto.setTitle(title);
		dto.setBbs_idx(idx);
		dto.setOrgFile(orgFileName);
		dto.setSaveFile(sFileName);
		
		FileDAO dao = new FileDAO();
		dao.registFile(dto);
		dao.close();
	}
	
}
