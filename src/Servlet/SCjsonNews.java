package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AdminUserDAO;
import DAO.NewsInfoDAO;
import DAO.uploadfile;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class SCjsonNews
 */
@WebServlet("/SCjsonNews")
public class SCjsonNews extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SCjsonNews() {
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
		NewsInfoDAO objNewsInfoDAO = new NewsInfoDAO();
		PrintWriter out = response.getWriter();
		int option = 3;// Integer.parseInt(request.getParameter("option"));
		String _pagesize = request.getParameter("rows") == null ? "10" : request.getParameter("rows");

		String _pageindex = request.getParameter("pageindex") == null ? "1" : request.getParameter("pageindex");

		// String _where = request.getParameter("zhaxun") == null ?
		// "":request.getParameter("zhaxun");
		String NewsID = request.getParameter("NewsID");
		JSONObject objJSON = new JSONObject();
		JSONArray objJsonArray = new JSONArray();

		if (NewsID == null && request.getParameter("id") != null){
			
			int id = request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id"));
			if(id == 5){
				//下载列表
				uploadfile objuploadfile = new uploadfile();
				ResultSet rs = objuploadfile.selectfile();
				
				objJsonArray = ResultSetToJson(rs);

				objJSON.put("rows", objJsonArray);
				objJSON.put("total", objNewsInfoDAO.GetTotal(id));

				
			}else{
			ResultSet rs = objNewsInfoDAO.selectNewstype(_pageindex, _pagesize, id);
			
			objJsonArray = ResultSetToJson(rs);

			objJSON.put("rows", objJsonArray);
			objJSON.put("total", objNewsInfoDAO.GetTotal(id));

			
			}
		} else {
			
			ResultSet rs = objNewsInfoDAO.selectNewsInfo(NewsID); 
			objJsonArray = ResultSetToJson(rs);

			objJSON.put("rows", objJsonArray);
			
		}
		out.println(objJSON.toString());
		//System.out.println(objJSON.toString());
	}

	public JSONArray ResultSetToJson(ResultSet objRS) {
		JSONArray objJSONArray = new JSONArray();
		// 数据集元数据
		ResultSetMetaData objMeta;
		try {
			objMeta = objRS.getMetaData();

			while (objRS.next()) {
				JSONObject objJsonObject = new JSONObject();
				for(int i = 1; i<=objMeta.getColumnCount(); i++ )
				{
					
					if(objMeta.getColumnName(i).equals("Newstype") && objRS.getInt(i)== 0){
						
						objJsonObject.put(objMeta.getColumnName(i), "头条");
					}else if(objMeta.getColumnName(i).equals("Newstype") && objRS.getInt(i)== 1){
						
						objJsonObject.put(objMeta.getColumnName(i), "要闻");
					}else if(objMeta.getColumnName(i).equals("Newstype") && objRS.getInt(i)== 2){
						
						objJsonObject.put(objMeta.getColumnName(i), "深谈");
					}else if(objMeta.getColumnName(i).equals("Newstype") && objRS.getInt(i)== 3){
						
						objJsonObject.put(objMeta.getColumnName(i), "新议");
					}else if(objMeta.getColumnName(i).equals("NewsTitle") && objRS.getString(i).length() >= 20){
						
						objJsonObject.put(objMeta.getColumnName(i), objRS.getString(i).substring(0, 20)+"...");
					}else{
						objJsonObject.put(objMeta.getColumnName(i), objRS.getObject(i));
					}

				}

				objJSONArray.add(objJsonObject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return objJSONArray;
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
