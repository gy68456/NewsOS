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

import DAO.uploadimg;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class SCimg
 */
@WebServlet("/SCimg")
public class SCimg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SCimg() {
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
		JSONObject objJSON = new JSONObject();
		JSONArray objJsonArray = new JSONArray();
		PrintWriter out = response.getWriter();
		uploadimg objuploadimg = new uploadimg();
		ResultSet rs = objuploadimg.selectimg();

		
		objJsonArray = ResultSetToJson(rs);
		objJSON.put("rows", objJsonArray);
		
		out.println(objJSON.toString());
		
	}

	
	public JSONArray ResultSetToJson(ResultSet objRS)
	{
		JSONArray objJSONArray = new JSONArray();
		//数据集元数据
		ResultSetMetaData objMeta;
		try {
			objMeta = objRS.getMetaData();
			while(objRS.next())
			{
				JSONObject objJsonObject = new JSONObject();
				for(int i = 1; i<=objMeta.getColumnCount(); i++ )
				{
					objJsonObject.put(objMeta.getColumnName(i), objRS.getObject(i));
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
