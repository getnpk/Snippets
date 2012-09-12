package com.npk.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class NReducer extends Reducer< TextPair, IntWritable, TextPair, IntWritable>{
	@Override
	protected void reduce(TextPair key, Iterable<IntWritable> values, Context context) 
			throws IOException, InterruptedException {
		
		int counter = 0;
		for (IntWritable value : values){
			++counter;
		}
		
		
		context.write(key, new IntWritable(counter));
	}
}