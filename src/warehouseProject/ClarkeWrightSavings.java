/*        
 *        Developed by Hamdi Bayhan in 2016
 * --->   github.com/HamdiBayhan
 * --->	  linkedin.com/in/hamdi-bayhan-b3133248
 * 
 */

package warehouseProject;

import java.util.*;

public class ClarkeWrightSavings {
	
	initialPointClass initialPointObject = new initialPointClass();
	
	public void clarkeWrightSavingsMethod(List<Integer> orderList)
	{
		
		MeasureDistance measureDistanceObject = new MeasureDistance();
		Map<Integer, Integer> tempDistanceWithInitial = new LinkedHashMap<Integer, Integer>();
		Map<String, Integer> tempSavingsMap = new LinkedHashMap<String, Integer>();
		List<List<Integer>> willUseSavingsList = new LinkedList<List<Integer>>();
		List<Integer> setRouting = new LinkedList<Integer>();
		List<String> tempKey = new LinkedList<String>();
		List<Integer> tempSavingList = new LinkedList<Integer>();
		List<Integer> getDistanceWithInitial = new LinkedList<Integer>();
		
		if(orderList.size()>1)
		{
			getDistanceWithInitial.add(1);
			
			for(int x=0; x<orderList.size(); x++)
			{
				getDistanceWithInitial.add(orderList.get(x));
				tempDistanceWithInitial.put(orderList.get(x), measureDistanceObject.twoPointDistance(getDistanceWithInitial)+1);
				getDistanceWithInitial.remove(1);
			}
			
			for(int x=0; x<orderList.size()-1; x++)
			{
				for(int y=x+1; y<orderList.size(); y++)
				{
					List<Integer> getDistanceWithTwoPoint = new LinkedList<Integer>();
					getDistanceWithTwoPoint.add(orderList.get(x));
					getDistanceWithTwoPoint.add(orderList.get(y));
					
					int tempDistanceWithTwoPoint = measureDistanceObject.twoPointDistance(getDistanceWithTwoPoint);
					int saving = tempDistanceWithInitial.get(orderList.get(x)) + tempDistanceWithInitial.get(orderList.get(y)) - tempDistanceWithTwoPoint;
					
					String twoPoint = orderList.get(x) + "-" + orderList.get(y);
					
					tempSavingsMap.put(twoPoint, saving);
					tempSavingList.add(saving);
					tempKey.add(twoPoint);
					
				}
			}
			
			Collections.sort(tempSavingList);
			
			Map<String, Integer> tempSavingsPermanentlyMap = new LinkedHashMap<String, Integer>();
			
			for(int x= tempSavingList.size()-1; x>=0; x--)
			{
				for(int y=0; y<tempKey.size(); y++)
				{
					if(tempSavingList.get(x) == tempSavingsMap.get(tempKey.get(y)))
					{
						tempSavingsPermanentlyMap.put(tempKey.get(y), tempSavingList.get(x));
						tempSavingsMap.replace(tempKey.get(y), tempSavingList.get(x), -1);
					}
				}
			}
			
		    LinkedList<String> listKey = new LinkedList<String>();
	        Iterator<String> iteratorKey = tempSavingsPermanentlyMap.keySet().iterator();
	        
	        while(iteratorKey.hasNext())
	        {
	        	String key =  iteratorKey.next();
	        	listKey.add(key);
	        }
			
			List<Integer> firstTempList = new LinkedList<Integer>();
			
			    	String[] changeStr = listKey.get(0).split("-");
			    	
			    	for(String a: changeStr)
			    	{
			    		firstTempList.add(Integer.parseInt(a));
			    	}
					
					willUseSavingsList.add(firstTempList);
			
			for(int x = 1 ; x<listKey.size(); x++)
			{
				
				List<Integer> tempList = new LinkedList<Integer>();
				changeStr = listKey.get(x).split("-");
		    	
		    	for(String a: changeStr)
		    	{
		    		tempList.add(Integer.parseInt(a));
		    	}
		    	
				if(setRouting.size()>0)
				{
					if(!(setRouting.contains(tempList.get(0)) && setRouting.contains(tempList.get(1))))
					{
						if(setRouting.contains(tempList.get(0)) || setRouting.contains(tempList.get(1)))
						{
							if(setRouting.get(0)== tempList.get(0))
							{
								setRouting.add(0, tempList.get(1));
							}
							else if(setRouting.get(0)== tempList.get(1))
							{
								setRouting.add(0, tempList.get(0));
							}
							else if(setRouting.get(setRouting.size()-1)== tempList.get(0))
							{
								setRouting.add(tempList.get(1));
							}
							else if(setRouting.get(setRouting.size()-1)== tempList.get(1))
							{
								setRouting.add(tempList.get(0));
							}
							
							for(int r=0; r<willUseSavingsList.size(); r++)
							{
								
								if(willUseSavingsList.get(r).contains(setRouting.get(0)) || willUseSavingsList.get(r).contains(setRouting.get(setRouting.size()-1))) 
								{
									if(!(setRouting.containsAll(willUseSavingsList.get(r))))
									{
										if(setRouting.get(0) == willUseSavingsList.get(r).get(0))
										{
											setRouting.add(0, willUseSavingsList.get(r).get(1));
										}
										else if(setRouting.get(0) == willUseSavingsList.get(r).get(1))
										{
											setRouting.add(0, willUseSavingsList.get(r).get(0));
										}
										else if(setRouting.get(setRouting.size()-1) == willUseSavingsList.get(r).get(0))
										{
											setRouting.add(willUseSavingsList.get(r).get(1));
										}
										else if(setRouting.get(setRouting.size()-1) == willUseSavingsList.get(r).get(1))
										{
											setRouting.add(willUseSavingsList.get(r).get(0));
										}
										willUseSavingsList.remove(r);
										List<Integer> pass = new LinkedList<Integer>();
										willUseSavingsList.add(r, pass);
									}	
								}
							}
						}
						
						else
						{
							willUseSavingsList.add(tempList);
						}
					}
				}
				
				else
				{
					for(int r=0; r<willUseSavingsList.size(); r++)
					{
						if(willUseSavingsList.get(r).contains(tempList.get(1)) || willUseSavingsList.get(r).contains(tempList.get(0)))
						{
								if(willUseSavingsList.get(r).get(0) == tempList.get(0))
								{
									setRouting.add(willUseSavingsList.get(r).get(1));
									setRouting.add(willUseSavingsList.get(r).get(0));
									setRouting.add(tempList.get(1));
								}
								else if(willUseSavingsList.get(r).get(1) == tempList.get(0))
								{
									setRouting.add(willUseSavingsList.get(r).get(0));
									setRouting.add(willUseSavingsList.get(r).get(1));
									setRouting.add(tempList.get(1));
								}
								else if(willUseSavingsList.get(r).get(0) == tempList.get(1))
								{
									setRouting.add(willUseSavingsList.get(r).get(1));
									setRouting.add(willUseSavingsList.get(r).get(0));
									setRouting.add(tempList.get(0));
								}
								else if(willUseSavingsList.get(r).get(1) == tempList.get(1))
								{
									setRouting.add(willUseSavingsList.get(r).get(0));
									setRouting.add(willUseSavingsList.get(r).get(1));
									setRouting.add(tempList.get(0));
								}
								
								willUseSavingsList.remove(r);
						}
						else
						{
							willUseSavingsList.add(tempList);
						}
					}
				}
			}
		}
	}
	
	public int getDistanceCWS(List<Integer> orderList)
	{
		MeasureDistance measureDistanceObject = new MeasureDistance();
		List<Integer> tempList = new LinkedList<Integer>();
		
		clarkeWrightSavingsMethod(orderList);
		tempList.addAll(orderList);
		
		initialPointObject.setListWithInitialPoint(tempList);
		
		int tempInitialIndex = 0;
		
		for(int x=0; x<tempList.size(); x++)
		{
			if(tempList.get(0) == orderList.get(x))
			{
				tempInitialIndex = x;
			}
		}
		
		tempList.clear();
		tempList.addAll(orderList.subList(tempInitialIndex, orderList.size()));
		tempList.addAll(orderList.subList(0, tempInitialIndex));
		
		return measureDistanceObject.totalDistance(tempList);
	}
	
}

