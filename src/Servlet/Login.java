package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import net.sf.*;
import net.sf.json.JSONObject;
import zjxTool.Security;
import DAO.LoginDAO;

/**
 * Servlet implementation class Login
 */
//@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		JSONObject objJsonObject = new JSONObject();
		Security objSecurity =new Security();
		LoginDAO objLoginDAO  = new LoginDAO();
		String login = request.getParameter("inputlogin");
		String pwd = request.getParameter("inputpwd");
		int index = request.getParameter("index")== null ? 2:Integer.parseInt(request.getParameter("index"));
		System.out.println(index+login+pwd);
		HttpSession session = request.getSession(); 
		if(index == 1){
			if(objLoginDAO.selectAdminUser(login, objSecurity.SSL_MD5(pwd))== 1){
				session.setAttribute("sionLogin", login); 
				//objJsonObject.put("Secces", 1);
				 out.println("<script type='text/javascript'>window.location.href='../newsWeb/cn/jk/team/index.html?id=0';</script>");
				 
			}else{
				//objJsonObject.put("Error", 0);
				
				 out.println("<script type='text/javascript'>window.location.href='../newsWeb/cn/jk/team/index.html?id=0';</script>");
				 
			}
		}else{
			if(objLoginDAO.selectAdminUser(login, objSecurity.SSL_MD5(pwd))== 1){
				session.setAttribute("sionLogin", login); 
				objJsonObject.put("Secces", 1);
				 //out.println("<script type='text/javascript'>window.location.href='../Admin/default.jsp';</script>");
			}else{
				objJsonObject.put("Error", 0);
				
				 //out.println("<script type='text/javascript'>window.location.href='../Admin/login.jsp';</script>");
				 
			}
		}
		
		
		out.print(objJsonObject.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
