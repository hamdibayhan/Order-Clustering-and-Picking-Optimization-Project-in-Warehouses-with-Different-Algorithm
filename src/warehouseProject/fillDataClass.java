/*        
 *        Developed by Hamdi Bayhan in 2016
 * --->   github.com/HamdiBayhan
 * --->	  linkedin.com/in/hamdi-bayhan-b3133248
 * 
 */
package warehouseProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class fillDataClass {
	
	public void fillDataMap(Map<Integer, List<Integer>> tempMap, String tempData1, String tempData2)
	{
		int orderLoad1;
    	List<Integer> orderLoad2= new ArrayList<Integer>();
    	
    	tempData1 = tempData1.replace(" ", "");
    	tempData2 = tempData2.replace(" ", "");
    	
    	String[] changeStr = tempData2.split("-");
    	
    	orderLoad1=Integer.parseInt(tempData1);
    	
    	for(String a: changeStr)
    	{
    		orderLoad2.add(Integer.parseInt(a));
    	}
    	
    	tempMap.put(orderLoad1, orderLoad2);
	}
	
	public void fillDataMap1(Map<Integer, String> tempMap, String tempData1, String tempData2)
	{
		int orderLoad1;
    	String orderLoad2;
    	
    	tempData1 = tempData1.replace(" ", "");
    	tempData2 = tempData2.replace(" ", "");
    	
    	orderLoad1=Integer.parseInt(tempData1);
    	orderLoad2=tempData2;
    	
    	tempMap.put(orderLoad1, orderLoad2);
	}
}
