package Servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Download
 */
@WebServlet("/Download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Download() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//System.out.println();
		String localhost= getServletContext().getInitParameter("xunirul");
		HttpSession session = request.getSession(); 
		String sionLogin = (String) session.getAttribute("sionLogin"); 
		//System.out.println(sionLogin);
		PrintWriter out2 = response.getWriter();
		if(sionLogin != null){
			String url =localhost+"/"+request.getParameter("url");
			String name = request.getParameter("name");
			String aa = Download.class.getResource("/").getFile() + "../1.xls";
			System.out.println(name);
			
			  
			File pathsavefile = new File(url);  
			//System.out.println(pathsavefile);
			//保存窗口中显示的文件名 ， 这是 将在浏览器输入  http://localhost:8080/你的项目名/你的那个servlet 的URL映射，将显示下载的文件名称。 同时它是作为输出流的目的源。  
			String fileName=name;  
			//重新设置相应。  
			response.reset();   
			//设置内容文件的类型  一般有pdf , word execl 各有不同的设置。  
			response.setContentType("APPLICATION/OCTET-STREAM");   
			fileName=response.encodeURL(new String(fileName.getBytes(),"UTF-8"));//转码  
			//System.out.println(fileName);
			//以附件的形式提示用户下载， 就是你在浏览器打开那个servlet 时将弹出对话框提//示你下载还是保存。  
			response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");  
			//得到响应的输出流  即向客户端输出信息的输出流。  
			ServletOutputStream out = response.getOutputStream();  
			InputStream inStream=new FileInputStream(pathsavefile);  
			byte[] b = new byte[inStream.available()];  
			int len;  
			while((len=inStream.read(b)) >0)  
			out.write(b,0,len);  
			response.setStatus( response.SC_OK );  
			response.flushBuffer();  
			out.close();  
			inStream.close();  
		}else{
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			
			out2.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>");
			out2.println("<script type='text/javascript'>alert('请先登录');window.location.href='http://localhost:8080/NewsOS/newsWeb/cn/jk/team/newspage.html?id=5';</script>");
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
