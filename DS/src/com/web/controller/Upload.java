package com.web.controller;	

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.web.view.JDBCConnect;
import com.web.view.Loader;


public class Upload extends HttpServlet{


	private File dumpDir;
	private File doneDir;
	private ArrayList<String> donefiles;
	
	private static final String dumpLocation = "/usr/local/tomcat7/webapps/DS/dump";
	private JDBCConnect connect;
	
	private ServletContext context;
	private String servletName;
	
	@Override
	public void init(ServletConfig config) throws ServletException {

		connect = JDBCConnect.getObject("","","");
		
		context = config.getServletContext();
		servletName = config.getServletName();
		
		dumpDir = new File(dumpLocation);
		if (!dumpDir.exists())
			dumpDir.mkdirs();
		
	}

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		RequestDispatcher view = req.getRequestDispatcher("upload.jsp");
		view.forward(req, resp);
	
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		String uploadername = null;
		
		if (session.getAttribute("username") != null)
			uploadername = (String) session.getAttribute("username");
		else{
			RequestDispatcher view = req.getRequestDispatcher("index.jsp");
			view.forward(req, resp);
		}
		
		
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		ServletFileUpload uploadHandler = new ServletFileUpload(dfif);
		
		donefiles = new ArrayList<String>();
		
		try {
			
			List<FileItem> items = uploadHandler.parseRequest(req);
			
			for (FileItem item : items){
				
				try {
					
					File file = new File(dumpDir, item.getName());
					
					if (!connect.isFileExists(item.getName())){
						item.write(file);
						donefiles.add(file.getName() + " uploaded.");
						context.log(servletName + ": " + " file uploaded " + file.getName());
					}else{
						donefiles.add(file.getName() + " already exists not uploaded! ");
						context.log(servletName + ": " + " file not uploaded " + file.getName());
					}
					
				} catch (Exception e) {
						e.printStackTrace();
					}
				
				}
			
			
			new Loader().load(uploadername);
			
	
			req.setAttribute("donefiles", donefiles);
			RequestDispatcher view = req.getRequestDispatcher("upload.jsp");
			view.forward(req, resp);
		
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		}
		
	

}
