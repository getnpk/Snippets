package com.web.model;

public class DBFile {

	private String filename;
	private String filetype;
	private String filesize;
	private String user_requested;
	
	public void setFilename(String filename){
		this.filename = filename;
	}
	
	public void setFiletype(String filetype){
		this.filetype = filetype;
	}
	
	public void setFilesize(String filesize){
		this.filesize = filesize;
	}
	
	public void setUserRequested(String user_requested){
		this.user_requested = user_requested;
	}
	
	public String getFilename(){
		return filename;
	}
	
	public String getFiletype(){
		return filetype;
	}
	
	public String getFilesize(){
		return filesize;
	}
	
	public String getUserRequested(){
		return user_requested;
	}
	
}
