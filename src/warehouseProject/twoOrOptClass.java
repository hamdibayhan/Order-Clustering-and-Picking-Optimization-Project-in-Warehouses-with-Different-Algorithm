/*        
 *        Developed by Hamdi Bayhan in 2016
 * --->   github.com/HamdiBayhan
 * --->	  linkedin.com/in/hamdi-bayhan-b3133248
 * 
 */
package warehouseProject;

import java.util.LinkedList;
import java.util.List;

public class twoOrOptClass {
	
	ClarkeWrightSavings CWSobject = new ClarkeWrightSavings();
	nearestNeighborClass nearestNeighborObject = new nearestNeighborClass();
	localSearch localSearchObject = new localSearch();
	
	public int twoOpt(List<Integer> orderList, String select)
	{
		MeasureDistance measureDistanceObject = new MeasureDistance();
		
		int temporaryDistance = 0;
		
		if(select == "NNtwoOpt")
		{
			temporaryDistance = nearestNeighborObject.nearestNeighbor(orderList);
		}
		else if(select == "CWStwoOpt")
		{
			CWSobject.clarkeWrightSavingsMethod(orderList);
			temporaryDistance = CWSobject.getDistanceCWS(orderList);
		}
		
		List <Integer> temp= new LinkedList<Integer>();
		temp.addAll(orderList);
		
		if(temp.size()>2)
		{
			for(int i=0 ; i<5; i++)
			{
				localSearchObject.twoOpt(temp);
				int newTemporaryDistance = measureDistanceObject.totalDistance( temp);
				
				if(newTemporaryDistance < temporaryDistance)
				{
					orderList.clear();
					orderList.addAll(temp);
				}
				else
				{
					temp.clear();
					temp.addAll(orderList);
				}
				
				if(temporaryDistance > newTemporaryDistance )
				{
					temporaryDistance = newTemporaryDistance;
				}
				
			}
		}
		return temporaryDistance;
	}
	
	public int orOpt(List<Integer> orderList, String select)
	{
		MeasureDistance measureDistanceObject = new MeasureDistance();
		int temporaryDistance = 0;
		
		if(select == "NNorOpt")
		{
			temporaryDistance = nearestNeighborObject.nearestNeighbor( orderList);
		}
		else if(select == "CWSorOpt")
		{
			CWSobject.clarkeWrightSavingsMethod(orderList);
			temporaryDistance = CWSobject.getDistanceCWS(orderList);
		}
			
		List <Integer> temp= new LinkedList<Integer>();
		
		temp.addAll(orderList);

		if(temp.size()>2)
		{
			for(int i=0 ; i<5; i++)
			{
				localSearchObject.orOpt(temp);
				
				int newTemporaryDistance = measureDistanceObject.totalDistance( temp);
	
				if(newTemporaryDistance < temporaryDistance)
				{
					orderList.clear();
					orderList.addAll(temp);
				}
				else
				{
					temp.clear();
					temp.addAll(orderList);
				}
				
				if(temporaryDistance > newTemporaryDistance )
				{
					temporaryDistance = newTemporaryDistance;
				}
				
			}
		}
		return temporaryDistance;
	}
	
}
