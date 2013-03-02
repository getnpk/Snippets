
/*
Roshan's map reduce assginment. :)
*/

// Java classes
import java.lang.Math;
import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

// Apache Project classes
import org.apache.log4j.Logger;

// Hadoop classes
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.SequenceFileAsTextInputFormat;
import org.apache.hadoop.mapred.SequenceFileInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.mapred.lib.LongSumReducer;
import org.apache.hadoop.util.Progressable;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

//import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;




/**
* An example showing how to use the Common Crawl 'textData' files to efficiently
* work with Common Crawl corpus text content.
*
* @author Chris Stephens <chris@commoncrawl.org>
*/
public class ExampleTextWordCount extends Configured implements Tool {

  private static final Logger LOG = Logger.getLogger(ExampleTextWordCount.class);

  
  
  
  /**
* Perform a simple word count mapping on text data from the Common Crawl corpus.
*/
  public static class ExampleTextWordCountMapper
      extends MapReduceBase
      implements Mapper<Text, Text, Text, DataCount/*LongWritable*/> {

    private static final Boolean True = null;
	private static final Boolean False = null;
	// create a counter group for Mapper-specific statistics
    private final String _counterGroup = "Custom Mapper Counters";
    
    public void map(Text key, Text value, OutputCollector<Text, DataCount> output, Reporter reporter)
        throws IOException {

      reporter.incrCounter(this._counterGroup, "Records In", 1);

      try {

        // Get the text content as a string.
    	String url = key.toString();  //roshan
        String pageText = value.toString();
        String hostName = null;
        
         URL aURL = new URL(url);
		 hostName = aURL.getHost();		
        // Removes all punctuation.
       // pageText = pageText.replaceAll("[^a-zA-Z0-9 ]", "");

        // Normalizes whitespace to single spaces.
        pageText = pageText.replaceAll("\\s+", " ");

        if (pageText == null || pageText == "") {
          reporter.incrCounter(this._counterGroup, "Skipped - Empty Page Text", 1);
        }
        
        // Splits by space and outputs to OutputCollector.
        for (String word : pageText.split(" ")) {
        //	compare word with http://
        // store this URl in a string variable
        //	
        	DataCount dc = new DataCount(word,1);
        	
        	output.collect(new Text(hostName),dc);
         // output.collect(new Text(URL_variable+word.toLowerCase()), new LongWritable(1));
        }
      }
      catch (Exception ex) {
        LOG.error("Caught Exception", ex);
        reporter.incrCounter(this._counterGroup, "Exceptions", 1);
      }
    }
 
  }
  
  
/*
    
  public static class ExampleTextWordCountReducer 
    extends MapReduceBase
    implements Reducer<Text, DataCount, Text, LongWritable> {

    	public void reduce(Text key, Iterable<DataCount> values,
    			OutputCollector output, Reporter reporter) throws IOException {
    			
    		
    			int sum = 0;
    			while (values.hasNext()) {
    			
    			for (DataCount value : values){
    			//LongWritable value = (LongWritable) values.next();
    				DataCount dc = (DataCount) values.next();	
    			sum += dc.get(); // process value
    			
    		}

    output.collect(key, new LongWritable(sum));
  }
  
}
  
  */
/*	 
	 public static class ExampleTextWordCountReducer 
	  extends MapReduceBase
	  implements Reducer<Text, DataCount, Text, Text> {

	  public void reduce(Text key, Iterable<DataCount> values,
	  OutputCollector output, Reporter reporter) throws IOException {
	   
	   
	  int sum = 0;
	  StringBuffer sb = new StringBuffer();
	  sb.append(key);
	  sb.append(" -> (");
	   
	  for (DataCount value : values){
	  String word = value.getWord();
	  int count = value.getCount();
	  sb.append(word + " \"" + count + "\" ");
	  }

	  sb.append(")");
	  Text outputText = sb.toString();
	   
	  output.collect(key, new Text(outputText));
	  }
	   
	}	 
*/	 	 
	 public static class ExampleTextWordCountReducer
	 extends MapReduceBase
	 implements Reducer<Text, DataCount, Text, Text> {

	 public void reduce(Text key, Iterator<DataCount> values,OutputCollector<Text, Text> output, Reporter reporter) throws IOException {

	   StringBuffer sb = new StringBuffer();
	   sb.append(" -> (");  
	 
	   HashMap<String,Integer> map = new HashMap<String,Integer>();
	   
	   while(values.hasNext()){
	  // for (DataCount value : values){
		   DataCount dc = (DataCount) values.next();
	       String word = dc.getWord();
	       int count = dc.getCount();

	       if (map.containsKey(word))
	           map.put(word, map.get(word) + count);
	       else
	           map.put(word,count);
	   }

	   for (String word : map.keySet()){
		   sb.append(map.get(word) + "x ");
		   sb.append("\""+ word + "\",");
	     }
	   
	   sb.append(")");
	   
	   String outputText = sb.toString();
	   LOG.info("reduce debug:" + outputText+" "+key);
	   output.collect(key, new Text(outputText));
	 }
	 
	 } 

	
  /**
* Hadoop FileSystem PathFilter for ARC files, allowing users to limit the
* number of files processed.
*
* @author Chris Stephens <chris@commoncrawl.org>
*/
  public static class SampleFilter
      implements PathFilter {

    private static int count = 0;
    private static int max = 3;//999999999;

    public boolean accept(Path path) {

      if (!path.getName().startsWith("textData-"))
        return false;

      SampleFilter.count++;

      if (SampleFilter.count > SampleFilter.max)
        return false;

      return true;
    }
  }

  /**
* Implmentation of Tool.run() method, which builds and runs the Hadoop job.
*
* @param args command line parameters, less common Hadoop job parameters stripped
* out and interpreted by the Tool class.
* @return 0 if the Hadoop job completes successfully, 1 if not.
*/
  @Override
  public int run(String[] args)
      throws Exception {

    String outputPath = null;
    String configFile = null;

    // Read the command line arguments.
    if (args.length < 1)
      throw new IllegalArgumentException("Example JAR must be passed an output path.");

    outputPath = args[0];

    if (args.length >= 2)
      configFile = args[1];

    // For this example, only look at a single text file.
    String inputPath = "input_commoncrawl";// "s3n://aws-publicdatasets/common-crawl/parse-output/segment/1341690166822/textData-01666";
 
    // Switch to this if you'd like to look at all text files. May take many minutes just to read the file listing.
  //String inputPath = "s3n://aws-publicdatasets/common-crawl/parse-output/segment/*/textData-*";

    // Read in any additional config parameters.
    if (configFile != null) {
      LOG.info("adding config parameters from '"+ configFile + "'");
      this.getConf().addResource(configFile);
    }

    // Creates a new job configuration for this Hadoop job.
    JobConf job = new JobConf(this.getConf());

    job.setJarByClass(ExampleTextWordCount.class);

    // Scan the provided input path for ARC files.
    LOG.info("setting input path to '"+ inputPath + "'");
    FileInputFormat.addInputPath(job, new Path(inputPath));
    FileInputFormat.setInputPathFilter(job, SampleFilter.class);

    // Delete the output path directory if it already exists.
    LOG.info("clearing the output path at '" + outputPath + "'");

    FileSystem fs = FileSystem.get(new URI(outputPath), job);

    if (fs.exists(new Path(outputPath)))
      fs.delete(new Path(outputPath), true);

    // Set the path where final output 'part' files will be saved.
    LOG.info("setting output path to '" + outputPath + "'");
    FileOutputFormat.setOutputPath(job, new Path(outputPath));
    FileOutputFormat.setCompressOutput(job, false);

    // Set which InputFormat class to use.
 //   job.setInputFormat(SequenceFileInputFormat.class);
    job.setInputFormat(SequenceFileAsTextInputFormat.class);
    
    // Set which OutputFormat class to use.
    job.setOutputFormat(TextOutputFormat.class);

    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(DataCount.class);
    // Set the output data types.
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);

    // Set which Mapper and Reducer classes to use.
    job.setMapperClass(ExampleTextWordCount.ExampleTextWordCountMapper.class);
    //job.setReducerClass(LongSumReducer.class);
    job.setReducerClass(ExampleTextWordCount.ExampleTextWordCountReducer.class);
    //job.setCombinerClass(ExampleTextWordCount.ExampleTextWordCountReducer.class);
    
    if (JobClient.runJob(job).isSuccessful())
      return 0;
    else
      return 1;
  }

  /**
* Main entry point that uses the {@link ToolRunner} class to run the example
* Hadoop job.
*/
  public static void main(String[] args)
      throws Exception {
    int res = ToolRunner.run(new Configuration(), new ExampleTextWordCount(), args);
    System.exit(res);
  }

	}
