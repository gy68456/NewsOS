package filit;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sun.security.krb5.Config;

/**
 * Servlet Filter implementation class filtLogin
 */
@WebFilter(filterName="filterLogin",urlPatterns={
		"/Admin/AddNewsInfo.jsp",
		"/Admin/AuditingList.jsp",
		"/Admin/default.jsp",
		"/Admin/home.jsp",
		"/Admin/ReleaseList.jsp",
		"/Admin/uploadfile.jsp",
		"/Admin/uploadimgs.jsp",
		"/Admin/Userlist.jsp",
		
		})
public class filtLogin implements Filter {

	private FilterConfig config;
    /**
     * Default constructor. 
     */
    public filtLogin() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain		
		System.out.println("½øÈë¹ýÂË");
		request.setCharacterEncoding("utf-8");		
		response.setCharacterEncoding("utf-8");
		//String loginpage = config.getInitParameter("loginpage");
		//String prologin = config.getInitParameter("prologin");
		HttpServletRequest Hsr = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse) response;  
		HttpSession session =Hsr.getSession(true);
		String url= Hsr.getServletPath();
		
		String path = Hsr.getContextPath();  
		
		String basePath = Hsr.getScheme()+"://"+Hsr.getServerName()+":"+Hsr.getServerPort()+path;  		
		
	
			if(session.getAttribute("sionLogin") == null /*&& url.endsWith(loginpage) == false &&  url.endsWith(prologin) == false*/){
				
					
					//request.setAttribute("tip", "Äú»¹Ã»ÓÐµÇÂ½£¬ÇëµÇÂ¼");
				
					//request.getRequestDispatcher(loginpage).forward(request, response);
				
					//System.out.println("Î´µÇÂ¼");
					resp.sendRedirect(basePath+"/Admin/login.jsp");
				
				
			}else{
				
				chain.doFilter(request, response);
			}
		
		
			
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.config = fConfig;
	}

}
