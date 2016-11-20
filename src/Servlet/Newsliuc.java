package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.NewsInfoBean;
import DAO.NewsInfoDAO;
import net.sf.json.JSONObject;
import zjxTool.nowtime;

/**
 * Servlet implementation class Newsliuc
 */
@WebServlet("/Newsliuc")
public class Newsliuc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Newsliuc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		NewsInfoDAO objNewsInfoDAO =new NewsInfoDAO();
		NewsInfoBean objNewsInfoBean = new NewsInfoBean();
		PrintWriter out = response.getWriter();
		JSONObject objJSON = new JSONObject();
		nowtime objnowstime =new nowtime();		
		
		int op = Integer.parseInt(request.getParameter("op"));
		int NewsID = Integer.parseInt(request.getParameter("NewsID"));
		//op=0 审核 op=1 退回审核  op=2 暂不发布 op=3 发布  op=4 取消发布  op=5重新审核 op=6 发布
		// 审核情况 Auditing 0（已审核）1（未审核）
		//发布情况  Release 0（已发布）	1（未发布）
		if (op == 0) {
			objNewsInfoBean.setAuditing(0);
			objNewsInfoBean.setAuditingTime(objnowstime.nowtime());
			objNewsInfoBean.setRelease(1);
			objNewsInfoBean.setReleaseTime(null);
			
		} else if (op == 1) {
			objNewsInfoBean.setAuditing(1);			
			objNewsInfoBean.setAuditingTime(objnowstime.nowtime());
			objNewsInfoBean.setRelease(1);
			objNewsInfoBean.setReleaseTime(null);
		}else if(op == 2){
			objNewsInfoBean.setAuditing(0);			
			objNewsInfoBean.setAuditingTime(null);
			objNewsInfoBean.setRelease(1);
			objNewsInfoBean.setReleaseTime(objnowstime.nowtime());
			
		}else if(op == 3){
			objNewsInfoBean.setRelease(0);
			objNewsInfoBean.setReleaseTime(objnowstime.nowtime());
			objNewsInfoBean.setAuditing(0);			
			objNewsInfoBean.setAuditingTime(null);
		}else if (op == 4) {
			objNewsInfoBean.setRelease(1);			
			objNewsInfoBean.setAuditingTime(objnowstime.nowtime());
			objNewsInfoBean.setAuditing(0);			
			objNewsInfoBean.setAuditingTime(null);
		}else if(op == 5){
			objNewsInfoBean.setAuditing(1);
			objNewsInfoBean.setAuditingTime(null);			
			objNewsInfoBean.setRelease(1);	
			objNewsInfoBean.setReleaseTime(null);
			
		}else if(op == 6){
			objNewsInfoBean.setRelease(0);
			objNewsInfoBean.setReleaseTime(objnowstime.nowtime());
			objNewsInfoBean.setAuditing(0);			
			objNewsInfoBean.setAuditingTime(null);
		} 
		if(op == 7){
			//删除新闻
			//System.out.println("w s s c");
			if(objNewsInfoDAO.delNews(NewsID) == 1){
				objJSON.put("Secces", 11);
			}else{
				objJSON.put("Error", 21);
			}
		}else if(objNewsInfoDAO.optionNewsAud_Rel(op, NewsID, objNewsInfoBean) == 1){
			objJSON.put("Secces", 11);
		}else{
			objJSON.put("Error", 21);
		}
		
		out.print(objJSON.toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
