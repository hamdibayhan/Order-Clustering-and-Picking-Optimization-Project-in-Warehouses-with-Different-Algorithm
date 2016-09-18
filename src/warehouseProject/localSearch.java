/*        
 *        Developed by Hamdi Bayhan in 2016
 * --->   github.com/HamdiBayhan
 * --->	  linkedin.com/in/hamdi-bayhan-b3133248
 * 
 */
package warehouseProject;

import java.util.LinkedList;
import java.util.List;

public class localSearch {

	public void orOpt(List <Integer> listOrOpt)
	{
		int tempLength = 3;
		int noOfElements = listOrOpt.size();
		int s=0, newS=0;
		
		do
		{
			List<Integer> tempList = new LinkedList<Integer>();
			s=(int)(Math.random() * (noOfElements));
			for(int x=0; x<tempLength; x++)
			{
				if(s>=listOrOpt.size())
				{
					tempList.add(listOrOpt.get(0));
					listOrOpt.remove(0);
				}
				else
				{
				tempList.add(listOrOpt.get(s));
				listOrOpt.remove(s);
				}
			}
			
			newS=(int)(Math.random() * (listOrOpt.size()));
				for(int x=tempLength-1; x>=0; x--)
				{
					listOrOpt.add(newS, tempList.get(x));
				}
		} 
		while(s==newS);
		
	}

	public void twoOpt(List <Integer> listTwoOpt)
	{
		int noOfElements = listTwoOpt.size();
		int temp = 0;
		int s=0, newS=0;
			
			do
			{
			s=(int)(Math.random() * (noOfElements));
			newS=1+(int)(Math.random() * (noOfElements -1));
			
			temp = listTwoOpt.get(s);
			listTwoOpt.set(s, listTwoOpt.get(newS));
			listTwoOpt.set(newS, temp);
			} 
			while(s==newS);
	}
	
}
