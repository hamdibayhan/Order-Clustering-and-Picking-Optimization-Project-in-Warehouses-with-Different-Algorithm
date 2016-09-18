/*        
 *        Developed by Hamdi Bayhan in 2016
 * --->   github.com/HamdiBayhan
 * --->	  linkedin.com/in/hamdi-bayhan-b3133248
 * 
 */
package warehouseProject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class fileClass {
	
	static int numberOfOrder;
	static int maxNumberOfSeed;
	static java.io.File file;
	fillDataClass fillDataObject = new fillDataClass();
	
	public void readFileOrder(Map<Integer, List<Integer>> orderMap, Map<Integer, String> orderMap1) throws FileNotFoundException
	{
		
		Scanner scanner = new Scanner(new FileReader(file));
			
     	while(scanner.hasNextLine())
     	{
     		String line = scanner.nextLine();
     		fillDataObject.fillDataMap(orderMap, line.split(":")[0], line.split(":")[1]);
     		fillDataObject.fillDataMap1(orderMap1, line.split(":")[0], line.split(":")[2]);
     		System.out.println("orderMap.size()" +orderMap.size());
     	}
     	scanner.close();
	}
	
	public void generateOrderList() throws IOException
	{
		
        BufferedWriter output = null;
        try {
            File file = new File("denemeReadFile.txt");
            output = new BufferedWriter(new FileWriter(file));
            
            for(int x=0; x<numberOfOrder ; x++)
            {
            	String line= String.valueOf(x)+ ": ";
            	
            	Integer[] arr = new Integer[generateWarehouse.warehouseLength*generateWarehouse.warehouseLength];
                for (int i = 0; i < arr.length; i++) 
                {    
                	int r=i+1;
                    arr[i] = r;
                }
                Collections.shuffle(Arrays.asList(arr));
                
                int s=	(int)(Math.random() * (maxNumberOfSeed))+1;
                
                for(int y=0; y<s; y++)
                {
                	if(y!=(s-1))
                	{
                		line= line + String.valueOf(arr[y])+"-";
                	}
                	else
                	{
                		line= line + String.valueOf(arr[y]);
                	}
                }
                
                int tempClock = (int)(Math.random() * 9) + 8;
            	int tempMinute = (int)(Math.random() * 60);
            	
            	if(tempMinute<10)
            	{
            		line = line + "      :    " + tempClock + "'0" + tempMinute ;
            	}
            	else
            	{
            		line = line + "      :    " + tempClock + "'" + tempMinute ;
            	}
            	
            	output.write(line);
            	output.newLine();
            }
        }
        
        catch ( IOException e ) {
           e.printStackTrace();
        } 
        
        finally 
        {
          if ( output != null ) {
            output.close();
          }
        }
 
	}
}
