/*
 * Hbase administration basic tools.
 *
 * */

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.ClusterStatus;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseAdmin {

    public HbaseAdmin(){

    }

    public void addColumn (String tablename, String  colunmnname) throws IOException{

        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(conf);
        admin.addColumn(tablename, new HColumnDescriptor (colunmnname));
        System.out.println("Added column : " + colunmnname + "to table " + tablename);
    }

    public void delColumn (String tablename, String  colunmnname) throws IOException{

        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(conf);
        admin.deleteColumn(tablename, colunmnname);
        System.out.println("Deleted column : " + colunmnname + "from table " + tablename);
    }

    public void createTable (String tablename, String familyname) throws IOException {

        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(conf);

        HTableDescriptor tabledescriptor = new HTableDescriptor(Bytes.toBytes(tablename));

        tabledescriptor.addFamily(new HColumnDescriptor (familyname));

        admin.createTable(tabledescriptor);

    }

    public void majorCompact (String mytable) throws IOException{

        //Create the required configuration.
        Configuration conf = HBaseConfiguration.create();
        //Instantiate a new client.
        HTable table = new HTable(conf,mytable);

        HBaseAdmin admin = new HBaseAdmin(conf);

        String tablename = table.toString();
        try{
        admin.majorCompact(tablename);
        System.out.println("Compaction done!");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void checkIfRunning() throws MasterNotRunningException, ZooKeeperConnectionException{
        //Create the required configuration.
        Configuration conf = HBaseConfiguration.create();
        //Check if Hbase is running
        try{
        HBaseAdmin.checkHBaseAvailable(conf);
        }catch(Exception e){
            System.err.println("Exception at " + e);
            System.exit(1);
        }
    }

    public void minorcompact(String trname) throws IOException, InterruptedException{
        //Create the required configuration.
        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(conf);
        admin.compact(trname);
    }

    public void deletetable(String tablename) throws IOException{
        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(conf);
        admin.deleteTable(tablename);
    }

    public void disabletable(String tablename) throws IOException{
        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(conf);
        admin.disableTable(tablename);
    }

    public void enabletable(String tablename) throws IOException{
        //Create the required configuration.
        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(conf);
        admin.enableTable(tablename);
    }

    public void flush(String trname) throws IOException{
        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(conf);
        admin.disableTable(trname);
    }

    public ClusterStatus getclusterstatus () throws IOException{
        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(conf);
        return admin.getClusterStatus();
    }

 
    public void isTableAvailable(String tablename) throws IOException{
        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(conf);
        boolean result = admin.isTableAvailable(tablename);
        System.out.println("Table " + tablename + " available ?" + result);
    }

    public void isTableEnabled(String tablename) throws IOException{
        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(conf);
        boolean result = admin.isTableEnabled(tablename);
        System.out.println("Table " + tablename + " enabled ?" + result);
    }

    public void isTableDisabled(String tablename) throws IOException{
        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(conf);
        boolean result = admin.isTableDisabled(tablename);
        System.out.println("Table " + tablename + " disabled ?" + result);
    }

    public void tableExists(String tablename) throws IOException{
        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(conf);
        boolean result = admin.tableExists(tablename);
        System.out.println("Table " + tablename + " exists ?" + result);
    }

    public void shutdown() throws IOException{
        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(conf);
        System.out.println("Shutting down..");
        admin.shutdown();
    }

    public void listTables () throws IOException{
        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(conf);
        admin.listTables();
    }

    public void modifyTable(String tablename, String newtablename) throws IOException{
        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(conf);
        admin.modifyTable(Bytes.toBytes(tablename), new HTableDescriptor(newtablename));

    }

    public void split(String tablename) throws IOException, InterruptedException{
        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(conf);
        admin.split(tablename);
    }

    public void isMasterRunning() throws MasterNotRunningException, ZooKeeperConnectionException{
        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin administer = new HBaseAdmin(conf);
        System.out.println( "Master running ? "+ administer.isMasterRunning());
    }

    public static void main (String[] args) throws IOException{

        
        HbaseAdmin admin = new HbaseAdmin();

        //Check if Hbase is running properly
        HbaseAdmin.checkIfRunning();
        admin.isMasterRunning();
        //admin.createTable("hbase_nasdaq", "details");

        //other functions based on arguments.

    }
}

