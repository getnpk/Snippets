package com.npk.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.GzipCodec;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;



public class NDriver implements Tool{

	private Configuration conf;
	
	public int run(String[] args) throws Exception {
	
		
		Job job = new Job(getConf(), "N job");
		job.setJobName("N JOB");
		
		job.setJarByClass(NDriver.class);
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		
		/*
		FileOutputFormat.setCompressOutput(job, true);
		FileOutputFormat.setOutputCompressorClass(job, GzipCodec.class);
		*/
		
		job.setMapperClass(NMapper.class);
		job.setCombinerClass(NReducer.class);
		job.setReducerClass(NReducer.class);
		
	
		job.setOutputKeyClass(TextPair.class);
		job.setOutputValueClass(IntWritable.class);
		
		return (job.waitForCompletion(true) ? 0 : 1);
	}

	public static void main (String[] args) throws Exception{
		
		
		ToolRunner.run(new Configuration(), new NDriver(), args);
		
	}

	@Override
	public void setConf(Configuration conf) {
		this.conf = conf;
		
	}

	@Override
	public Configuration getConf() {
		return conf;
	}
	
	
	
}
