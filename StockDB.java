import java.io.*;
import java.util.*;

class StockDB { 

    private Map<String, String> classList; 
    private String [] fields; 
int i=0;
    public StockDB(String cvsFile, String key, String val)  { //key=reg no ,val=name
	FileReader fileRd=null; 
	BufferedReader reader=null; 

	try { 
	    fileRd = new FileReader(cvsFile); 
	    reader = new BufferedReader(fileRd); 

	    /* read the CSV file's first line which has 
	     * the names of fields. 
	     */
	    String header = reader.readLine(); 
	    fields = header.split(",");// keep field names 
	    
	    //test fields length
	    //System.out.println("feilds length = "+fields.length);//OP=3
	    //test for headers
	    
	    //System.out.println(fields[0]+" "+fields[2]); //output= Symbol and Price
	    
	    // find where the key and the value are 
	    int keyIndex = findIndexOf(key); 
	    int valIndex = findIndexOf(val); 
             //test for what are key and val
            // System.out.println("key ="+key);//output key=Symbol
            // System.out.println("val ="+val);//output val=Price
             
             //test for keyIndex and valIndex
             //System.out.println("keyIndex ="+keyIndex);
             //System.out.println("valIndex ="+valIndex);
	    if(keyIndex == -1 || valIndex == -1) 
		throw new IOException("CVS file does not have data"); 
	    // note how you can throw a new exception 

	    // get a new hash map
	    classList = new HashMap<String, String>(); 

	    /* read each line, getting it split by , 
	     * use the indexes to get the key and value 
	     */
	    String [] tokens; 
	    for(String line = reader.readLine(); 
		line != null; 
		line = reader.readLine()) { 
		tokens = line.split(","); 
		classList.put(tokens[keyIndex], tokens[valIndex]); 
	    }
	    
	    if(fileRd != null) fileRd.close();
	    if(reader != null) reader.close();
	    
	    // I can catch more than one exceptions 
	} catch (IOException e) { 
	    System.out.println(e);
	    System.exit(-1); 
	} catch (ArrayIndexOutOfBoundsException e) { 
	    System.out.println("Malformed CSV file");
	    System.out.println(e);
	}
    }

    private int findIndexOf(String keys) { //this is to find the index of fields
           int j=-1;
	for(int i=0; i < fields.length; i++) {
	//System.out.println("i = "+i+"key/val ="+keys+"fields[i] ="+fields[i]);
	    if(fields[i].equals(keys)) {j=i;} 
	    
	    }
	 return j;
    }
	
    // public interface 
    public String findName(String key) { 
	return classList.get(key); 
    }

}
	    
