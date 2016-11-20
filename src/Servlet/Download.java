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
			//���洰������ʾ���ļ��� �� ���� �������������  http://localhost:8080/�����Ŀ��/����Ǹ�servlet ��URLӳ�䣬����ʾ���ص��ļ����ơ� ͬʱ������Ϊ�������Ŀ��Դ��  
			String fileName=name;  
			//����������Ӧ��  
			response.reset();   
			//���������ļ�������  һ����pdf , word execl ���в�ͬ�����á�  
			response.setContentType("APPLICATION/OCTET-STREAM");   
			fileName=response.encodeURL(new String(fileName.getBytes(),"UTF-8"));//ת��  
			//System.out.println(fileName);
			//�Ը�������ʽ��ʾ�û����أ� ����������������Ǹ�servlet ʱ�������Ի�����//ʾ�����ػ��Ǳ��档  
			response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");  
			//�õ���Ӧ�������  ����ͻ��������Ϣ���������  
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
			out2.println("<script type='text/javascript'>alert('���ȵ�¼');window.location.href='http://localhost:8080/NewsOS/newsWeb/cn/jk/team/newspage.html?id=5';</script>");
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
