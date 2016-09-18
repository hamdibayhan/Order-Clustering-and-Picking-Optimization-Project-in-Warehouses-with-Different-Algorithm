/*        
 *        Developed by Hamdi Bayhan in 2016
 * --->   github.com/HamdiBayhan
 * --->	  linkedin.com/in/hamdi-bayhan-b3133248
 * 
 */
package warehouseProject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class divideAndCluster {
	
	ClusterClass clusterObject = new ClusterClass();
	
	public void divideToOrdersWithClock(Map<Integer, String> divideToOrders, Map<Integer, List<Integer>> orderMap) throws SQLException
	{
		LinkedList<Integer> listKey = new LinkedList<Integer>();
        Iterator<Integer> iteratorKey = divideToOrders.keySet().iterator();
        
        while(iteratorKey.hasNext())
        {
        	int key =  iteratorKey.next();
        	listKey.add(key);
        }
        
        List<Integer> tempListClock= new LinkedList<Integer>();
        
        for(int x=0; x<listKey.size(); x++)
        {
        	List<Integer> tempList= new LinkedList<Integer>();
        	String tempString = divideToOrders.get(listKey.get(x)).replace(" ", "");
        	String[] changeStr = tempString.split("'");
        	for(String a: changeStr)
        	{
        		tempList.add(Integer.parseInt(a));
        	}
        
        	int temporaryValueMinute = tempList.get(0)*60 + tempList.get(1);
        	tempListClock.add(temporaryValueMinute);	
        }
        
    	Map<Integer, List<Integer>> dividedOrderMap1 = new LinkedHashMap<Integer, List<Integer>>();
        Map<Integer, List<Integer>> dividedOrderMap2 = new LinkedHashMap<Integer, List<Integer>>();
        Map<Integer, List<Integer>> dividedOrderMap3 = new LinkedHashMap<Integer, List<Integer>>();
        Map<Integer, List<Integer>> dividedOrderMap4 = new LinkedHashMap<Integer, List<Integer>>();
        Map<Integer, List<Integer>> dividedOrderMap5 = new LinkedHashMap<Integer, List<Integer>>();
        Map<Integer, List<Integer>> dividedOrderMap6 = new LinkedHashMap<Integer, List<Integer>>();
    	
        for(int x=0; x<tempListClock.size(); x++)
        {        	
        	if(570>tempListClock.get(x) && tempListClock.get(x)>=480){
        		dividedOrderMap1.put(listKey.get(x), orderMap.get(listKey.get(x)));
        	}
        	else if(660>tempListClock.get(x) && tempListClock.get(x)>=570){
        		dividedOrderMap2.put(listKey.get(x), orderMap.get(listKey.get(x)));
        	}
        	else if(750>tempListClock.get(x) && tempListClock.get(x)>=660){
        		dividedOrderMap3.put(listKey.get(x), orderMap.get(listKey.get(x)));
        	}
        	else if(840>tempListClock.get(x) && tempListClock.get(x)>=750){
        		dividedOrderMap4.put(listKey.get(x), orderMap.get(listKey.get(x)));
        	}
        	else if(930>tempListClock.get(x) && tempListClock.get(x)>=840){
        		dividedOrderMap5.put(listKey.get(x), orderMap.get(listKey.get(x)));
        	}
        	else if(1020>tempListClock.get(x) && tempListClock.get(x)>=930){
        		dividedOrderMap6.put(listKey.get(x), orderMap.get(listKey.get(x)));
        	}
        }
        
        insertDataToDataBase(dividedOrderMap1, 1, "s");
        insertDataToDataBase(dividedOrderMap2, 2, "s");
        insertDataToDataBase(dividedOrderMap3, 3, "s");
        insertDataToDataBase(dividedOrderMap4, 4, "s");
        insertDataToDataBase(dividedOrderMap5, 5, "s");
        insertDataToDataBase(dividedOrderMap6, 6, "s");
        
       
        insertDataToDataBase(dividedOrderMap1, 1, "m");
        insertDataToDataBase(dividedOrderMap2, 2, "m");
        insertDataToDataBase(dividedOrderMap3, 3, "m");
        insertDataToDataBase(dividedOrderMap4, 4, "m");
        insertDataToDataBase(dividedOrderMap5, 5, "m");
        insertDataToDataBase(dividedOrderMap6, 6, "m");
        
	}
	
	
	public void insertDataToDataBase(Map<Integer, List<Integer>> temporaryMapClusterOrder, int time, String sVm) throws SQLException
	{
		Map<String, List<Integer>> singleMultipleOrderMap = new LinkedHashMap<String, List<Integer>>();
		
		if( sVm == "s")
		{
			clusterObject.singleOrderCluster(temporaryMapClusterOrder, singleMultipleOrderMap );
		}
		else
		{
			clusterObject.orderCluster(temporaryMapClusterOrder, singleMultipleOrderMap);
			  
		}
		
		LinkedList<String> listKey = new LinkedList<String>();
        Iterator<String> iteratorKey = singleMultipleOrderMap.keySet().iterator();
        
        while(iteratorKey.hasNext())
        {
        	String key =  iteratorKey.next();
        	listKey.add(key);
        }
        
		
		ResultSet myRs = null;
		
		if(listKey.size() != 0)
		{
	        for(int x=0; x<listKey.size(); x++)
	        {
	        	String tempValue= "";
	        	for(int y=0; y<singleMultipleOrderMap.get(listKey.get(x)).size(); y++)
	        	{
	        		if(0 != tempValue.length())
					{
	        			tempValue = tempValue + "-" +singleMultipleOrderMap.get(listKey.get(x)).get(y) ;
					}
					else
					{
						tempValue = String.valueOf(singleMultipleOrderMap.get(listKey.get(x)).get(y));
					}
	        	}
	        	
	        	Statement myStmt = databaseClass.con.createStatement();;

	        	
	        	try {
	        		
					String sql = "insert into clusterorder" + " (whichInterval, svsm, ordernumber, seednumber) "
					+ "values ('"+time+"', '"+sVm+"', '"+listKey.get(x)+"','"+ tempValue +"')";
					
					myStmt.executeUpdate(sql);
				}
				catch (Exception exc) {
					exc.printStackTrace();
				}
				finally {
					if (myRs != null) {
						myRs.close();
					}
					
					if (myStmt != null) {
						myStmt.close();
					}
				}
	        }
		}
	}
}
