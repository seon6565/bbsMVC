/**
 * 
 */
package common;

/**
 * 
 */
public class CommonUtil {
	public static boolean isValue(String str) {
		if(str != null && !str.isEmpty() && !str.isBlank()) {
		return true;
		}
		else {
		return false;
		}
	}
	
	public static boolean isNumeric(String str) {
		if(str != null && !str.isEmpty() && !str.isBlank() && str.matches("[0-9]+")) {
		return true;
		}
		else {
		return false;
		}
	}
	
	public static int parseIntCheck(String no) {
		if(isNumeric(no)) {
		return Integer.parseInt(no);
		}
		else {
			return 0;
		}
	}
	
}
