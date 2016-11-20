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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class SCjson
 */

public class SCjson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SCjson() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		AdminUserDAO objAdminUserDAO =new AdminUserDAO();
		PrintWriter out = response.getWriter();
		String _pagesize = request.getParameter("rows") == null ? "10":request.getParameter("rows");
		
		String _pageindex = request.getParameter("page")== null ? "1":request.getParameter("page");
		
		String _where = request.getParameter("zhaxun") == null ? "":request.getParameter("zhaxun");
		
		
		ResultSet rs = objAdminUserDAO.selectuserinfo(_pageindex,_pagesize,_where);
		
		
		JSONObject objJSON = new JSONObject();
		JSONArray objJsonArray = new JSONArray();
		objJsonArray = ResultSetToJson(rs);
		objJSON.put("rows", objJsonArray);
		objJSON.put("total", objAdminUserDAO.GetTotal(_where));
		
		
		/*try {
			ResultSetMetaData objmeta = rs.getMetaData();
			
			
			while(rs.next()){
				
				if(sbcolunm.toString().isEmpty()){
					
					sbcolunm.append("{");
				
				}else{
					sbcolunm.append(",{");
				}
				
				for(int i=1;i<=objmeta.getColumnCount();i++){
					if(i== 1){
						sbcolunm.append(("\""+objmeta.getColumnName(i)+"\":\""+rs.getObject(i)+"\""));
					
					}else{
					sbcolunm.append((",\""+objmeta.getColumnName(i)+"\":\""+rs.getObject(i)+"\""));
					}
					
				}
				
				/*sb.append("\"LoginID\":\""+rs.getString("LoginID").replace(" ", "")+"\",");
				sb.append("\"Name\":\""+rs.getString("Name").replace(" ", "")+"\",");
				sb.append("\"Authorizations\":\""+rs.getString("Authorizations").replace(" ", "")+"\"");
				sbcolunm.append("}");
				
			}
			
		} catch (SQLException ex) {
			
			
		}
		sb.append("{\"rows\": [");
		sb.append(sbcolunm.toString());
		sb.append("],\"total\":\""+objAdminUserDAO.GetTotal()+"\"}");
		//sb.append("\"Secces\":"+"\""+0+"\"");
		//sb.append(",\"Error\":"+"\""+1+"\"");
		//System.out.println(sb.toString());*/
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
					if(objMeta.getColumnName(i).equals("Authorizations")  && objRS.getInt(i)== 0){ 
						
						objJsonObject.put(objMeta.getColumnName(i), "超级管理员");
					}else if(objMeta.getColumnName(i).equals("Authorizations") && objRS.getInt(i)== 1){
						
						objJsonObject.put(objMeta.getColumnName(i), "管理员");
					}else if(objMeta.getColumnName(i).equals("Authorizations") && objRS.getInt(i)== 2){
						
						objJsonObject.put(objMeta.getColumnName(i), "部门经理");
					}else if(objMeta.getColumnName(i).equals("Authorizations") && objRS.getInt(i)== 3){
						
						objJsonObject.put(objMeta.getColumnName(i), "组长");
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
