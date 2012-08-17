package com.npk.hadoop;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.io.WritableUtils;

public class TextPair implements WritableComparable<TextPair>{

	
	static {
	    WritableComparator.define(TextPair.class, new Comparator());
	}
	
	
	private Text first;
	private Text second;
	
	public void set(Text first, Text second){
		this.first = first;
		this.second = second;
	}
	
	public TextPair(){
		set(new Text(), new Text());
	}

	public TextPair(Text first, Text second){
		set(first, second);
	}
	
	public TextPair(String first , String second){
		set (new Text(first), new Text(second));
	}
	
	
	public Text getFirst(){ 
		return first; 
	}
	
	public Text getSecond(){
		return second;
	}
	
	
	@Override
	public void readFields(DataInput in) throws IOException {
		first.readFields(in);
		second.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		first.write(out);
		second.write(out);
	}

	@Override
	public int compareTo(TextPair textpair) {
		
		int cmp = first.compareTo(textpair.first);
		if (cmp != 0)
			return cmp;
		
		return second.compareTo(textpair.second);
	}
	
	@Override
	public boolean equals (Object o){
		
		if (o instanceof TextPair){
			TextPair textpair = (TextPair) o;
			return first.equals(textpair.first) && second.equals(textpair.second);
		}else
			return false;
	}
	
	@Override
	public int hashCode(){
	
		return first.hashCode() * 31 + second.hashCode();
	}
	
	@Override
	public String toString(){
		return first + "\t" + second;
	}

	
	public static class Comparator extends WritableComparator {
	    
	    private static final Text.Comparator TEXT_COMPARATOR = new Text.Comparator();
	    
	    public Comparator() {
	      super(TextPair.class);
	    }

	    @Override
	    public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
	      
	      try {
	    	// lengths of each Text field in byte stream.
	        int length_one = WritableUtils.decodeVIntSize(b1[s1]) + readVInt(b1, s1);
	        int length_two = WritableUtils.decodeVIntSize(b2[s2]) + readVInt(b2, s2);
	        int cmp = TEXT_COMPARATOR.compare(b1, s1, length_one, b2, s2, length_two);
	        if (cmp != 0) {
	          return cmp;
	        }
	        return TEXT_COMPARATOR.compare(b1, s1 + length_one, l1 - length_one, 
	                                       b2, s2 + length_two, l2 - length_two);
	      } catch (IOException e) {
	        throw new IllegalArgumentException(e);
	      }
	    }
	  }

	  
	  public static class CustomComparator extends WritableComparator {
		    
		    private static final Text.Comparator TEXT_COMPARATOR = new Text.Comparator();
		    
		    public CustomComparator() {
		      super(TextPair.class);
		    }

		    @Override
		    public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
		      
		      try {
		        int length_one = WritableUtils.decodeVIntSize(b1[s1]) + readVInt(b1, s1);
		        int length_two = WritableUtils.decodeVIntSize(b2[s2]) + readVInt(b2, s2);
		        int cmp = TEXT_COMPARATOR.compare(b1, s1, length_one, b2, s2, length_two);
		        if (cmp != 0) {
		        	return cmp;
			    }
			    return TEXT_COMPARATOR.compare(b1, s1 + length_one, l1 - length_one, 
			                                       b2, s2 + length_two, l2 - length_two);
			        
		      } catch (IOException e) {
		        throw new IllegalArgumentException(e);
		      }
		    }
		    
		    @Override
		    public int compare(WritableComparable a, WritableComparable b) {
		      if (a instanceof TextPair && b instanceof TextPair) {
		        return ((TextPair) a).first.compareTo(((TextPair) b).first);
		      }
		      return super.compare(a, b);
		    }
		  }
	
	
	
}
