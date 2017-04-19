/**
 * 
 */
package team.union.nonbusiness.sys.utils;

import javax.servlet.http.HttpServletRequest;

import team.union.nonbusiness.sys.model.Users;



/**
 * @author Jack Zhang
 *
 */
public class WebUtils {
	public static Users getUser(HttpServletRequest req) {
		Users user = (Users) req.getSession().getAttribute("user");
		return user;
	}
	
}
