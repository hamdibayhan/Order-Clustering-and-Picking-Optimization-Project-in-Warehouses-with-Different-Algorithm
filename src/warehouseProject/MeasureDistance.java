/*        
 *        Developed by Hamdi Bayhan in 2016
 * --->   github.com/HamdiBayhan
 * --->	  linkedin.com/in/hamdi-bayhan-b3133248
 * 
 */
package warehouseProject;

import java.util.*;

public class MeasureDistance {
	
	initialPointClass initialPointObject = new initialPointClass();
	generateWarehouse gwObject = new generateWarehouse();
	
	
	public int calculateDistance(int temp1, int temp2, int temp3, int temp4, int annex)
	 {
		int distance = 0;
		int corridorDifference = 0;
		int aisleDifference = 0;
		
		if( (temp2+1)/2 == (temp4+1)/2 ) //ayni koridorda ise
		{
			aisleDifference = Math.abs(temp1/generateWarehouse.warehouseSubPartHeight - temp3/generateWarehouse.warehouseSubPartHeight);
			distance = distance + Math.abs(temp1 - temp3) + aisleDifference * generateWarehouse.xAisleWidth;
			corridorDifference=0;
	
		}
		
		else 
		{
			if(temp1/generateWarehouse.warehouseSubPartHeight == temp3/generateWarehouse.warehouseSubPartHeight)
			{
				    if(temp2<temp4)
				    {
				    	if(temp2%2==0)
				    	{   
				    		double tempp1=(double)temp2;
				    	    double tempp2=(double)temp4;
				    		double x=Math.abs( (tempp1 - tempp2) / 2)- 0.5;
				    		corridorDifference = (int)x;
				    		
							if(corridorDifference<0)
							{
								corridorDifference=0;
							}
							
							if(temp4%2==0)
							{
								annex=-1;
							}
							annex+=2;

				    	}
				    	else
				    	{
				    		corridorDifference = Math.abs( (temp2 - temp4) / 2 )-1;
							if(corridorDifference<0)
							{
								corridorDifference=0;
							}
							
							if(temp4%2==0)
							{
								annex=-1;
							}
							annex+=1;
				    	}
				    }
				    else
				    {
				    	if(temp4%2==0)
				    	{
				    		
				    		double tempp1=(double)temp2;
				    	    double tempp2=(double)temp4;
				    		double x=Math.abs( (tempp1 - tempp2) / 2)- 0.5;
				    		corridorDifference = (int)x;
				    		
							if(corridorDifference<0)
							{
								corridorDifference=0;
							}
							
							if(temp2%2==0)
							{
								annex=-1;
							}
							annex+=2;
				    	}
				    	else
				    	{
				    		
				    		corridorDifference = Math.abs( (temp2 - temp4) / 2 )-1;
							if(corridorDifference<0)
							{
								corridorDifference=0;
							}
							
							if(temp2%2==0)
							{
								annex=-1;
							}
							
							annex+=1;
				    	}
				    }
				    
				int mode1= temp1%3 +1;
				int mode2= temp3%3 +1;
				
				int[] difference = new int[2];
				
				difference[0] = (generateWarehouse.warehouseSubPartHeight-mode1) + (generateWarehouse.warehouseSubPartHeight-mode2);
				difference[1] = mode1 + mode2 -2 ;
				Arrays.sort(difference);
				
				distance = distance + corridorDifference * generateWarehouse.yAisleWidth + Math.abs( temp2 - temp4) + difference[0]  + annex;
				}
			
			else
			{
				if(temp2<temp4)
			    {
			    	if(temp2%2==0)
			    	{   
			    		double tempp1=(double)temp2;
			    	    double tempp2=(double)temp4;
			    		double x=Math.abs( (tempp1 - tempp2) / 2)- 0.5;
			    		corridorDifference = (int)x;
			    		
						if(corridorDifference<0)
						{
							corridorDifference=0;
						}
						
						if(temp4%2==0)
						{
							annex=-1;
						}
						annex+=1;
			    	}
			    	else
			    	{
			    		corridorDifference = Math.abs( (temp2 - temp4) / 2 )-1;
						if(corridorDifference<0)
						{
							corridorDifference=0;
						}
						
						if(temp4%2==0)
						{
							annex=-1;
						}
			    	}
			    }
			    else
			    {
			    	if(temp4%2==0)
			    	{
			    		double tempp1=(double)temp2;
			    	    double tempp2=(double)temp4;
			    		double x=Math.abs( (tempp1 - tempp2) / 2)- 0.5;
			    		corridorDifference = (int)x;
						if(corridorDifference<0)
						{
							corridorDifference=0;
						}
						if(temp2%2==0)
						{
							annex=-1;
						}
						annex+=1;
			    	}
			    	else
			    	{
			    		corridorDifference = Math.abs( (temp2 - temp4) / 2 )-1;
						if(corridorDifference<0)
						{
							corridorDifference=0;
						}
						if(temp2%2==0)
						{
							annex=-1;
						}
			    	}
			    }
				
				aisleDifference = Math.abs(temp1/generateWarehouse.warehouseSubPartHeight - temp3/generateWarehouse.warehouseSubPartHeight);
				
				distance = distance + corridorDifference * generateWarehouse.yAisleWidth + Math.abs( temp1 - temp3) + Math.abs( temp2 - temp4) + annex + aisleDifference*generateWarehouse.xAisleWidth;
			}	
		}
		
		return distance;
	 }
	
	public int twoPointDistance(List<Integer> order)
	{
		int distance = 0;
		int annex=0;
		int temp1=0, temp2=0, temp3=0, temp4=0;
		int[][] measureWarehouse = gwObject.generateWarehouseMethod(); 
		
		for(int i=0; i<generateWarehouse.warehouseLength; i++)
		{
			for(int a=0; a<generateWarehouse.warehouseLength; a++)
			{
				if(measureWarehouse[i][a]==order.get(0))
				{
					temp1=i;
					temp2=a;
				}	
				
				if(measureWarehouse[i][a]==order.get(1))
				{
					temp3=i;
					temp4=a;
				}
			}
		}
		distance = calculateDistance(temp1 , temp2, temp3, temp4, annex);
		
		return distance;
	}
	
	public int totalDistance( List<Integer> orderList)
	 {   
		 int distance = 0;
		 int[][] measureWarehouse = gwObject.generateWarehouseMethod();
		 List<Integer> order = new LinkedList<Integer>();
		 
		 initialPointObject.setListWithInitialPoint(orderList);
		 order.addAll(orderList);
		 order.add(0, 1);

		 for(int w=0; w<order.size(); w++)
		 {
			 int annex=0;
			 int t=w+1;
			 int temp1=0, temp2=0, temp3=0, temp4=0;
			 
		 	 if(w==(order.size()-1))
		 	 {  
		 		 t=0;
		 	 }
		 		
				for(int i=0; i<generateWarehouse.warehouseLength; i++)
				{
					for(int a=0; a<generateWarehouse.warehouseLength; a++)
					{
						if(measureWarehouse[i][a]==order.get(w))
						{
							temp1=i;
							temp2=a;
						}	
						
						if(measureWarehouse[i][a]==order.get(t))
						{
							temp3=i;
							temp4=a;
						}
					}
				}
				
				distance = distance + calculateDistance(temp1 , temp2, temp3, temp4, annex);
		 }
		 
		 return distance; 
	 }
}
