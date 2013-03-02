import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;


public class DataCount implements Writable {
	  
	  public String word;
	  public Integer count;
	  
	  public DataCount(String word,int count) {
	    this.word = word;
	    this.count = count;
	    	  }

	  public DataCount() {
	    this(null, 0);
	  }

	  public String getWord(){
		  return this.word;
	  }
	  public int getCount(){
		  return this.count;
	  }
	  
	  public void write(DataOutput out) throws IOException {
	   // Text.writeString(out,word);
		  out.writeUTF(word);
		  out.writeInt(count);
	      }

	  public void readFields(DataInput in) throws IOException {
	  //  word = Text.readString(in);
		  word = in.readUTF();
		  count = in.readInt();
	     }
/*
	  public String toString() {
	    return UTF8.toString(word) + ", "
	        + Integer.toString(count);
	  }
	*/  
	  
  	}
