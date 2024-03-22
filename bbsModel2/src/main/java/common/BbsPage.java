package common;

public class BbsPage {
	public static String pagingArea(int total_page, int page_no, int page_block_start, int page_block_end, String linkUrl, String category,String search_word) {
		StringBuilder sb = new StringBuilder();
		String tmpLinkURL = (linkUrl!=null&&!linkUrl.isEmpty()?linkUrl:"?");

		sb.append((page_no>1? "<a class="+"pagehover "+ "href='"+tmpLinkURL+"search_category="+category+"&"+"search_word="+search_word+"&"+"page_no=1'><strong><<</strong></a>&nbsp;&nbsp;" : "<<&nbsp;&nbsp;"));
		sb.append((page_block_start>1? "<a href='"+tmpLinkURL+"search_category="+category+"&"+"search_word="+search_word+"&"+"page_no="+(page_block_start-1)+"'><strong><</strong></a>&nbsp;&nbsp;" : "<&nbsp;&nbsp;"));
		for (int i=page_block_start; i<=page_block_end; i++) {
			if ( page_no == i ){
				sb.append("<strong>"+i+"</strong>");
			} else {
				sb.append("<a class="+"pagehover "+ "href='"+tmpLinkURL+"search_category="+category+"&"+"search_word="+search_word+"&"+"page_no="+i+"'>"+i+"</a>");
			}
			if (i!=page_block_end) {
				sb.append("&nbsp;|&nbsp;");
			}
		}

		sb.append((total_page>page_block_end? "&nbsp;&nbsp;<a href='"+tmpLinkURL+"search_category="+category+"&"+"search_word="+search_word+"&"+"page_no="+(page_block_end+1)+"'><strong>></strong></a>" : "&nbsp;&nbsp;>"));
		sb.append((total_page>page_block_end? "&nbsp;&nbsp;<a href='"+tmpLinkURL+"search_category="+category+"&"+"search_word="+search_word+"&"+"page_no="+(total_page)+"'><strong>>></strong></a>" : "&nbsp;&nbsp;>>"));

		return sb.toString();
	}
}
