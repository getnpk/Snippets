package view;

/*
 * Hbase CRUD operations tools.
 *
 * */

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;

import org.apache.hadoop.hbase.util.Bytes;



public class Hbase {
	
	Configuration conf;
	HTable table;
   
    /* Function getvalue()
     * Retrieves a row from the HBase table based on rowkey, cfamily and qulifier.
     */
    public String getvalue(String rowkey, String cfamily, String qualifier) throws IOException{
       
        Get get = new Get(Bytes.toBytes(rowkey));//Create get with specific row.
       
        get.addColumn(Bytes.toBytes(cfamily), Bytes.toBytes(qualifier));//Add a column to the get.
       
        Result result = table.get(get);
        byte[] val = result.getValue(Bytes.toBytes(cfamily),Bytes.toBytes(qualifier));
       
        return Bytes.toString(val);
    }
   
   
   public Hbase(){
    
   	 conf = HBaseConfiguration.create();
       
     try {
		table = new HTable(conf,"hbase_nasdaq");
    	} catch (IOException e) {

		e.printStackTrace();
	}
       
       
       

       
     
    }

}