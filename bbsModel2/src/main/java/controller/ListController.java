package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.BbsPage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model2.bbs.BbsDAO;
import model2.bbs.BbsDTO;

public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int total_count = 0;
		int total_page = 1;
		int page_no = 1;
		int page_size = 10;
		int page_skip_cnt = 10;
		int page_block_size = 10;
		int page_block_start = 1;
		int page_block_end = 1;
		
		Map<String,Object> params = new HashMap<String, Object>();
		String search_category = req.getParameter("search_category");
		String search_word = req.getParameter("search_word");
		page_no = (req.getParameter("page_no")!=null ? Integer.parseInt(req.getParameter("page_no")) : 1);
		page_skip_cnt = (page_no-1)*page_size;
		
		if((search_category !=null && !search_category.isEmpty() && search_word != null && !search_word.isEmpty())){
			params.put("search_category",search_category);
			params.put("search_word",search_word);
		}
		
		params.put("page_skip_cnt", page_skip_cnt);
		params.put("page_size", page_size);
		params.put("page_no", page_no);
		
		BbsDAO dao = new BbsDAO();
		total_count = dao.bbsTotalCount(params);
		List<BbsDTO> bbsList = dao.bbsList(params);
		dao.close();
		
		total_page = (int)Math.ceil(total_count/(double)page_size);
		page_block_size = 10;
	    page_block_start = (int)Math.floor((page_no-1)/(double)page_size)*page_size +1;
		page_block_end = (int)Math.ceil(page_no/(double)page_size)*page_size;
		page_block_end = (page_block_end>total_page?total_page:page_block_end);
		
		params.put("total_count", total_count);
		params.put("total_page", total_page);
		params.put("page_block_start", page_block_start);
		params.put("page_block_end", page_block_end);
		params.put("page_block_size", page_block_size);
		
		String pagingArea = BbsPage.pagingArea(total_page,page_no, page_block_start, page_block_end, "list?",search_category,search_word);
		params.put("paging", pagingArea);
		
		req.setAttribute("bbsList", bbsList);
		req.setAttribute("params",params);
	
		req.getRequestDispatcher("/bbs/list.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
