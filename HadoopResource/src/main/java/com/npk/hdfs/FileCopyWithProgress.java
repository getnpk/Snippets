package com.npk.hdfs;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

public class FileCopyWithProgress {

	public static void main (String[] args) throws IOException{
		
		String src = "NASDAQ.csv";
		String dest = "/hive/warehouse/somedest";
		Configuration conf = new Configuration();
		
		InputStream in = new BufferedInputStream( new FileInputStream(src)); 
		FileSystem fs = FileSystem.get(URI.create(dest), conf);
		
		OutputStream out = fs.create(new Path(dest), new Progressable() {
			@Override
			public void progress() {
				System.out.print(".");
			}
		});
		
		IOUtils.copyBytes(in, out, 4096,false);
		IOUtils.closeStream(in);
		IOUtils.closeStream(out);
	}
}
