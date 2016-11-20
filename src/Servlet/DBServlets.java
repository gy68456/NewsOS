package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * Servlet implementation class DBServlets
 */
@WebServlet("/DBServlets")
public class DBServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String diverClass;
	private String userName;
	private String password;
	private String url;
    private String Databsae;
    private String mulu;
	
	@Override
	public void init() throws ServletException {
	   diverClass = /* getServletConfig(). */getServletContext().getInitParameter("driver");
	   userName = /* getServletConfig(). */getServletContext().getInitParameter("User");
	   password = /* getServletConfig(). */getServletContext().getInitParameter("password");
	   url = /* getServletConfig(). */getServletContext().getInitParameter("link");
	   Databsae = /* getServletConfig(). */getServletContext().getInitParameter("DataBase");
	   

	   try {
	    Class.forName(diverClass);
	   } catch (Exception e) {
	    e.printStackTrace();
	   }

	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBServlets() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection connection = null;
		   PreparedStatement statement = null;
		  
		   try {
		    connection = DriverManager.getConnection(url, userName, password);
		    
		    System.out.println(connection);
		    System.out.println(url+";Database="+Databsae+";user="+userName+";password="+password);
		    System.out.println(diverClass);
		    /*statement = connection.prepareStatement("select * from customer");
		    ResultSet rs = statement.executeQuery();

		    PrintWriter printWriter = response.getWriter();
		    while (rs.next()) {
		     printWriter.println(rs.getString("id"));
		     printWriter.println(rs.getString("name"));
		    }*/
		   } 
	catch (SQLException e) {
		    //e.printStackTrace();
		   } finally {
		    if (statement != null) {
		      //statement.close();
		     }

		     if (connection != null) {
		      //connection.close();
		     }
		   }
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
