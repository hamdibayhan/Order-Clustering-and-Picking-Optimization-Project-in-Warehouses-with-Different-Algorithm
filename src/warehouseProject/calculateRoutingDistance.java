/*        
 *        Developed by Hamdi Bayhan in 2016
 * --->   github.com/HamdiBayhan
 * --->	  linkedin.com/in/hamdi-bayhan-b3133248
 * 
 */

package warehouseProject;

import java.sql.*;
import java.util.*;


public class calculateRoutingDistance {
	
	static boolean generateFileButtonControl = false;
	SShapeClass sShapeObject = new SShapeClass();
	twoOrOptClass twoOrOptObject = new twoOrOptClass();
	initialPointClass initialPointObject = new initialPointClass();
	
	public int implementRouting(String method) throws SQLException
	{
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			myStmt = databaseClass.con.createStatement();
			myRs = myStmt.executeQuery("select * from clusterorder");
			
			Statement state2 = databaseClass.con.createStatement();
			
			DatabaseMetaData dbm = databaseClass.con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "totaldata", null);
			if (!(tables.next())) {
				state2.executeUpdate("create table totaldata(id integer,"
			              + "warehouse_length integer," + "xAisle_width integer,"+ "yAisle_width integer,"  
			              + "subpart_height integer," + "which_routing varchar(60)," + "distance integer);");
			}
			
			List<Integer> whichInterval = new LinkedList<Integer>();
			List<String> seedNumber = new LinkedList<String>();
			List<Integer> loadDistance = new LinkedList<Integer>();
			
			while (myRs.next()) {
				whichInterval.add(myRs.getInt("whichInterval"));
				seedNumber.add(myRs.getString("seednumber"));
			}
			
			int[] totalAralikDistance= new int[]{0,0,0,0,0,0};
			int totalDistance = 0;
			
			for(int x=0; x<seedNumber.size(); x++)
			{
		    	List<Integer> orderLoad= new ArrayList<Integer>();
		    	seedNumber.get(x).replace(" ", "");
		    	String[] changeStr = seedNumber.get(x).split("-");
		    	
		    	for(String a: changeStr)
		    	{
		    		orderLoad.add(Integer.parseInt(a));
		    	}
		    	
		    	initialPointObject.setListWithInitialPoint(orderLoad);
		    	
		    	if(method == "NNtwoOpt")
		    	{
		    		loadDistance.add(twoOrOptObject.twoOpt(orderLoad, method));
		    	}
		    	else if(method == "NNorOpt")
		    	{
		    		loadDistance.add(twoOrOptObject.orOpt(orderLoad, method));
		    	}
		    	else if(method == "SShape")
		    	{
		    		loadDistance.add(sShapeObject.totalDistanceSShape(orderLoad));
		    	}
		    	else if(method == "CWStwoOpt")
		    	{
		    		loadDistance.add(twoOrOptObject.twoOpt(orderLoad, method));
		    	}
		    	else if(method == "CWSorOpt")
		    	{
		    		loadDistance.add(twoOrOptObject.orOpt(orderLoad, method));
		    	}
		    	
		    	switch(whichInterval.get(x))
		        {
		           case 1 :
		        	   totalAralikDistance[0] = totalAralikDistance[0] + loadDistance.get(x);
		        	   totalDistance = totalDistance + loadDistance.get(x);
		              break;
		           case 2 :
		        	   totalAralikDistance[1] = totalAralikDistance[1] + loadDistance.get(x);
		        	   totalDistance = totalDistance + loadDistance.get(x);
		        	   break;
		           case 3 :
		        	   totalAralikDistance[2] = totalAralikDistance[2] + loadDistance.get(x);
		        	   totalDistance = totalDistance + loadDistance.get(x);
		              break;
		           case 4 :
		        	   totalAralikDistance[3] = totalAralikDistance[3] + loadDistance.get(x);
		        	   totalDistance = totalDistance + loadDistance.get(x);
		        	   break;
		           case 5 :
		        	   totalAralikDistance[4] = totalAralikDistance[4] + loadDistance.get(x);
		        	   totalDistance = totalDistance + loadDistance.get(x);
		              break;
		           case 6 :
		        	   totalAralikDistance[5] = totalAralikDistance[5] + loadDistance.get(x);
		        	   totalDistance = totalDistance + loadDistance.get(x);
			          break;
		           default :
		              
		        }
		    }
			
			String sql2 = "insert into totaldata" + " (warehouse_length, xAisle_width, yAisle_width, subpart_height, which_routing, distance) "
					+ "values ('"+generateWarehouse.warehouseLength+"', '" +generateWarehouse.xAisleWidth+ "', '" 
					+ generateWarehouse.xAisleWidth + "', '" + generateWarehouse.warehouseSubPartHeight + "', '"  
					+ method + "', '" + totalDistance + "')";
					
					state2.executeUpdate(sql2);
			
			
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
		
		return 0;
	}

}
