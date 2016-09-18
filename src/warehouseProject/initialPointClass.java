/*        
 *        Developed by Hamdi Bayhan in 2016
 * --->   github.com/HamdiBayhan
 * --->	  linkedin.com/in/hamdi-bayhan-b3133248
 * 
 */
package warehouseProject;

import java.util.LinkedList;
import java.util.List;

public class initialPointClass {

	static MeasureDistance mDistance = new MeasureDistance();
	
	public void setListWithInitialPoint(List<Integer> list)
	{ 
		List<Integer> temporaryList = new LinkedList<Integer>();
		temporaryList.add(1);
		int temporaryDistance = 0;
		int tempIndex = 0;
		
		temporaryList.add(list.get(0));
		
		int smallDistance = mDistance.twoPointDistance(temporaryList);
		
		temporaryList.remove(1);
		
		for(int i=1; i<list.size(); i++)
		{
			temporaryList.add(list.get(i));
			temporaryDistance = mDistance.twoPointDistance(temporaryList);
			
			if(temporaryDistance < smallDistance)
			{
				smallDistance = temporaryDistance;
				tempIndex = i;
		
			}
			
			temporaryList.remove(1);
		}
		
		int tempNumber = list.get(tempIndex);
		list.remove(tempIndex); 
		list.add(0, tempNumber);
		
	}
}
