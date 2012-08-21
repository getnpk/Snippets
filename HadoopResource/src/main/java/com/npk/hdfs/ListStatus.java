package com.npk.hdfs;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

public class ListStatus {

	public static void main(String[] args) throws Exception {
		String uri = "/hive/warehouse";
		
		Configuration conf = new Configuration();
		
		FileSystem fs = FileSystem.get(URI.create(uri), conf);
		
		Path path = new Path(uri);
		
		FileStatus[] status = fs.listStatus(path);
		Path[] listedPaths = FileUtil.stat2Paths(status);
		for (Path p : listedPaths) {
			System.out.println(p);
		}
	}
}