package com.web.model;

public class DBFile {

	private String filename;
	private String filetype;
	private String filesize;
	private String userRequested;
	
	public void setFilename(String filename){
		this.filename = filename;
	}
	
	public void setFiletype(String filetype){
		this.filetype = filetype;
	}
	
	public void setFilesize(String filesize){
		this.filesize = filesize;
	}
	
	public void setUserRequested(String userRequested){
		this.userRequested = userRequested;
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
		return userRequested;
	}
	
}
