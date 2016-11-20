package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.NewsInfoBean;
import DAO.LoginDAO;
import DAO.NewsInfoDAO;
import net.sf.json.JSONObject;
import zjxTool.Security;

/**
 * Servlet implementation class AddNewsinfo
 */
@WebServlet("/AddNewsinfo")
public class AddNewsinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewsinfo() {
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
		NewsInfoBean objNewsInfoBean =new NewsInfoBean();
		NewsInfoDAO objNewsInfoDAO  = new NewsInfoDAO();
		objNewsInfoBean.setNewsTitle(request.getParameter("txttitle"));
		objNewsInfoBean.setNewstype(Integer.parseInt(request.getParameter("Newstype"))); 
		objNewsInfoBean.setDepict(request.getParameter("txtdepict"));   
		objNewsInfoBean.setNewsID(objNewsInfoDAO.selcetnewsIDMax()+1);
		objNewsInfoBean.setNewsContent(request.getParameter("content"));
		objNewsInfoBean.setAuditing(1);
		objNewsInfoBean.setRelease(1);
		if(objNewsInfoDAO.AddNewsInfo(objNewsInfoBean)== 1){
			
			objJsonObject.put("Secces", 1);
			 //out.println("<script type='text/javascript'>window.location.href='../Admin/default.jsp';</script>");
		}else{
			objJsonObject.put("Error", 0);
			
			 //out.println("<script type='text/javascript'>window.location.href='../Admin/login.jsp';</script>");
			 
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
