package core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Display extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
			int flag = 0;
			
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
		
			HttpSession session = req.getSession();
			String info = (String) session.getAttribute(getServletInfo());
			String servlet_name = (String) session.getAttribute(getServletName());
			String name = (String) session.getAttribute("username");
		
			
			Cookie[] cookies = req.getCookies();
			if (cookies != null && cookies.length > 0){
				for (Cookie c : cookies){
					if (c.getValue().equals(name)){
						out.println("<br> Hi! Welcome back " + name);
						flag=1;
					}
				}
				if (flag == 0)
					out.println("<br> Hi! So you are new here " + name);
			}
			
			
			
			
			
	}

	
}
