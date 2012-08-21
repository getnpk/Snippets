package com.npk.hdfs;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class FileSystemCat {

	public static void main(String[] args) throws IOException{
	
		String uri = "hdfs://localhost:40000/hive/warehouse/ntable";
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(uri), conf);
		InputStream in = null;
		
		in = fs.open(new Path(uri));
		IOUtils.copyBytes(in, System.out, 4096, false);
		IOUtils.closeStream(in);
	
		/*
		 *  With Random data access and seek capabilities
		 *  Used in place of regular InputStream object. 
		 */
		
		FSDataInputStream input = null;
		input = fs.open(new Path(uri));
		IOUtils.copyBytes(input, System.out, 4096, false);
		input.seek(0);
		IOUtils.copyBytes(input, System.out, 4096, false);
		IOUtils.closeStream(in);
	
		
	}
}
