/*        
 *        Developed by Hamdi Bayhan in 2016
 * --->   github.com/HamdiBayhan
 * --->	  linkedin.com/in/hamdi-bayhan-b3133248
 * 
 */
package warehouseProject;

import java.util.List;

public class SShapeClass {
	
	generateWarehouse gwObject = new generateWarehouse();
	int[][] sShapeWarehouse = gwObject.generateWarehouseMethod();
	
	public int totalDistanceSShape(List<Integer> order)
	 {   
		 int distance = 0;
		 boolean fromWhere= true;
		 int control=0;
		 
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
						if(sShapeWarehouse[i][a]==order.get(w))
						{
							temp1=i;
							temp2=a;
						}	
						
						if(sShapeWarehouse[i][a]==order.get(t))
						{
							temp3=i;
							temp4=a;
						}
					}
				}
				
				if(temp2!=temp4)
				{
					if( control%2==0 )
					{
						fromWhere= true;
						control++;
					}
					else
					{
						fromWhere= false;
						control++;
					}
				}
				distance = distance + sShapeDistance(temp1 , temp2, temp3, temp4, annex, fromWhere);
		 }
		 
		 return distance; 
	 }
	
	public int sShapeDistance(int temp1, int temp2, int temp3, int temp4, int annex, boolean fromWhere)
		{
			int distance = 0;
			int corridorDifference = 0;
			int aisleDifference = 0;
			
			if( (temp2+1)/2 == (temp4+1)/2 ) //ayni koridorda ise
			{
				aisleDifference = Math.abs(temp1/generateWarehouse.warehouseSubPartHeight - temp3/generateWarehouse.warehouseSubPartHeight);
				distance = distance + Math.abs(temp1 - temp3) + aisleDifference * generateWarehouse.xAisleWidth;
				corridorDifference=0;
	//			System.out.print("^^^^^^ ek: "+ ek);
	//			System.out.println(" temp1: " + temp1 + " temp2: "+ temp2+ " temp3: " + temp3 + " temp4: "+ temp4 + " distance: " + distance +  " ek: " + ek);
				
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
								annex+=2;
	//							System.out.print("***111 ek: "+ ek);
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
	//							System.out.print("***222 ek: "+ ek);
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
	//							System.out.print("***333 ek: "+ ek);
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
	//							System.out.print("***444 ek: "+ ek);
					    	}
					    }
					    
					    int difference = 0;
					    
					if(fromWhere)
					{
						difference = ( generateWarehouse.warehouseLength - temp1 ) + ( generateWarehouse.warehouseLength - temp3 ) - 2;
						aisleDifference = generateWarehouse.warehouseLength/generateWarehouse.warehouseSubPartHeight - temp1/generateWarehouse.warehouseSubPartHeight + generateWarehouse.warehouseLength/generateWarehouse.warehouseSubPartHeight - temp3/generateWarehouse.warehouseSubPartHeight;
					}
					
					else
					{
						difference=Math.abs(temp3+temp1);
						aisleDifference = temp1/generateWarehouse.warehouseSubPartHeight + temp3/generateWarehouse.warehouseSubPartHeight;
					}
					
					distance = distance + corridorDifference * generateWarehouse.yAisleWidth + Math.abs( temp2 - temp4) + difference  + annex + aisleDifference*generateWarehouse.xAisleWidth ;
	//				System.out.println(" temp1: " + temp1 + " temp2: "+ temp2+ " temp3: " + temp3 + " temp4: "+ temp4 + " distance: " + distance +  " ek: " + ek + " fark: " + fark + " gecitFarki: " + gecitFarki +" koridor farki:"+ koridorFarki);
					
			}
			
			return distance;
		 }
}
