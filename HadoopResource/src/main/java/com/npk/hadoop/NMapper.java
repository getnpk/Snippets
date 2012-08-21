package com.npk.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class NMapper extends Mapper<LongWritable, Text, TextPair, IntWritable>{
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		String line = value.toString();
		String first = line.split(",")[0];
		String second = line.split(",")[1];
		
		context.write(new TextPair(first, second), new IntWritable(1));
	}
}
