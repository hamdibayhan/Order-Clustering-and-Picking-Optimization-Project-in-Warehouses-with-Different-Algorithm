/*        
 *        Developed by Hamdi Bayhan in 2016
 * --->   github.com/HamdiBayhan
 * --->	  linkedin.com/in/hamdi-bayhan-b3133248
 * 
 */
package warehouseProject;

import java.util.ArrayList;
import java.util.List;

public class nearestNeighborClass {
	
	
	public int nearestNeighbor(List<Integer> NN)
	{
		
		int tempBestNN = 0;
		int tempTemporary = 0;
		MeasureDistance measureDistanceObject = new MeasureDistance();
		
		for(int i=0; i<NN.size() - 1; i++)
		{
			int tempDistance = (int) Math.pow(generateWarehouse.warehouseLength, 2);
			
			if(i != NN.size()-2)
			{
			
			for(int x=i+1; x<NN.size(); x++)
			{
				List<Integer> temporaryArray = new ArrayList<Integer>();
				temporaryArray.add(NN.get(i));
				temporaryArray.add(NN.get(x));
				
				int newTempDistance = measureDistanceObject.twoPointDistance(temporaryArray );
				
				if(tempDistance>newTempDistance)
				{
					tempDistance = newTempDistance;
					tempBestNN = x;
				}
			}
			
			tempTemporary = NN.get(tempBestNN);
			NN.remove(tempBestNN);
			NN.add(i+1, tempTemporary);
			
			}			
		}
		
		int getDistance = measureDistanceObject.totalDistance(NN);
		
		return getDistance;
	}
	
}
