package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.AdminUserBean;
import DAO.AdminUserDAO;
import DB.sqlDB;
import net.sf.ezmorph.*;
import net.sf.json.*;

/**
 * Servlet implementation class AdminUser
 */
@WebServlet("/AdminUser")
public class AdminUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		int option = Integer.parseInt(request.getParameter("option"));
		AdminUserDAO objAdminUserDAO = new AdminUserDAO();
		AdminUserBean objAdminUserBean = new AdminUserBean();
		System.out.println("进来了");
		JSONObject objJSON = new JSONObject();
		
		PrintWriter out = response.getWriter();
		
		if (option == 1) {
			System.out.println(objAdminUserDAO.selectAdminUser(request.getParameter("loginid"), null));
			if (objAdminUserDAO.selectAdminUser(request.getParameter("loginid"), null) == 0) {
				objAdminUserBean.setLoginID(request.getParameter("loginid"));
				objAdminUserBean.setPwd(request.getParameter("pwd"));
				objAdminUserBean.setName(request.getParameter("names"));
				objAdminUserBean.setAuthorizations(request.getParameter("authorizations"));
				try {
					if (objAdminUserDAO.AddAdminUser(objAdminUserBean) == 1) {
						objJSON.put("Secces", 11);
						//sb.append("\"Secces\":" + 11);

					} else {
						objJSON.put("Error", 21);
						//sb.append("\"Error\":" + 21);
					}

				} catch (Exception ex) {

				}
			} else if(objAdminUserDAO.selectAdminUser(request.getParameter("loginid"), null) == 1){
				objJSON.put("Error", 22);
				
				//sb.append(",\"Error\":" + 22);
			}
		} else if (option == 2) {
			System.out.println(request.getParameter("authorizations"));
			if (objAdminUserDAO.selectAdminUser(request.getParameter("CKid"), null) == 1) {

				objAdminUserBean.setLoginID(request.getParameter("loginid"));
				objAdminUserBean.setPwd(request.getParameter("pwd"));
				objAdminUserBean.setName(request.getParameter("names"));
				objAdminUserBean.setAuthorizations(request.getParameter("authorizations"));
				
				if (objAdminUserDAO.updateAdminUser(objAdminUserBean) == 1) {
					objJSON.put("Secces", 11);
					//sb.append(",\"Secces\":" + 11);
				} else {
					objJSON.put("Error", 21);
					//sb.append(",\"Error\":" + 21);
				}
			} 
		}else if(option == 3){
			
			HttpSession session = request.getSession(); 
			String sionLogin = (String) session.getAttribute("sionLogin"); 
			String delID = request.getParameter("del");
			
			int dlqx = objAdminUserDAO.selectAuthorizations(sionLogin);
			int scqx = objAdminUserDAO.selectAuthorizations(delID);
			
			if(dlqx < scqx){
				
				if(objAdminUserDAO.delAdminUser(delID)== 1){
					objJSON.put("Secces", 12);
					//out.println("<script type='text/javascript'>alert('成功删除');</script>");
				}
			}else{
				//out.println("<script type='text/javascript'>alert('权限不足');</script>");
				objJSON.put("Error", 23);
			}
		
			
			
		}
		
		System.out.println(objJSON.toString());
		out.println(objJSON.toString());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
