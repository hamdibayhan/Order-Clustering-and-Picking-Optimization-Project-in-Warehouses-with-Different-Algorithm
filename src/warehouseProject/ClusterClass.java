/*        
 *        Developed by Hamdi Bayhan in 2016
 * --->   github.com/HamdiBayhan
 * --->	  linkedin.com/in/hamdi-bayhan-b3133248
 * 
 */

package warehouseProject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClusterClass {
	
	static MeasureDistance measureDistanceObject = new MeasureDistance();
	static nearestNeighborClass nearestNeighborObject = new nearestNeighborClass();
	initialPointClass initialPointObject = new initialPointClass();
	static MergeSort mergeSortObject = new MergeSort();
	static int payload;
	
	public void singleOrderCluster(Map<Integer, List<Integer>> orderMapDividedWithClock, Map<String, List<Integer>> temporaryMapClusterOrder)
	{   
		
		
		String seedGroup = "";

	    int s=1;
		LinkedList<Integer> temporaryListValue = new LinkedList<Integer>();
		LinkedList<Integer> temporaryListValueCluster = new LinkedList<Integer>();
		LinkedList<Integer> temporaryListKey = new LinkedList<Integer>();
		LinkedList<Integer> removeOrder = new LinkedList<Integer>();
		
		LinkedList<Integer> listKey = new LinkedList<Integer>();
        Iterator<Integer> iteratorKey = orderMapDividedWithClock.keySet().iterator();
        
        while(iteratorKey.hasNext())
        {
        	int key =  iteratorKey.next();
        	listKey.add(key);
        }
        
        for(int x=0; x<listKey.size(); x++)
        {
        	if(orderMapDividedWithClock.get(listKey.get(x)).size()==1)
        	{
        		temporaryListValue.addAll(orderMapDividedWithClock.get(listKey.get(x)));
        	}
        }
        
        if(temporaryListValue.size() != 0)
        {
        	initialPointObject.setListWithInitialPoint(temporaryListValue);
	        nearestNeighborObject.nearestNeighbor(temporaryListValue);  // en yakýn komþuluða göre gruplandýrdýðýmýz için
	        
	        
	        for(int y=0; y<temporaryListValue.size(); y++)
	        {
	        	for(int x=0; x<listKey.size(); x++)
	        	{
	        		if(orderMapDividedWithClock.get(listKey.get(x)).size()==1)
	            	{
	        			if(orderMapDividedWithClock.get(listKey.get(x)).get(0)==temporaryListValue.get(y))
	        			{
	        					if(!(temporaryListKey.contains(listKey.get(x))))
	        					{
	        						if(0 != seedGroup.length())
	        						{
	        							seedGroup = seedGroup + "-" +listKey.get(x) ;
	        						}
	        						else
	        						{
	        							seedGroup = String.valueOf(listKey.get(x));
	        						}
	        				
	        						temporaryListKey.add(listKey.get(x));
	        						temporaryListValueCluster.add(temporaryListValue.get(y));
	        						removeOrder.add(listKey.get(x));
	        					}
	        					
	        					if(y==(temporaryListValue.size()-1) || y==(payload*s-1))
	    						{
	        						if(y>=(payload*s-1) && y!=(temporaryListValue.size()-1))
	        						{   
	        							LinkedList<Integer> temp = new LinkedList<Integer>();
	        							temp.addAll(temporaryListValueCluster.subList(payload*(s-1), y+1));
	        							temporaryMapClusterOrder.put(seedGroup,temp);
	        						}
	        						
	        						if(y==(temporaryListValue.size()-1) && y>=(payload*(s-1)-1))
	        						{
	        							LinkedList<Integer> temp = new LinkedList<Integer>();
	            						temp.addAll(temporaryListValueCluster.subList(payload*(s-1), y+1));
	            						temporaryMapClusterOrder.put(seedGroup,temp);
	        						}
	        							
	    							seedGroup = "";
	    							s+=1;
	    						}
	        			}
	            	}
	        	}
	        }
	        
	        for(int e=0; e<removeOrder.size(); e++)
	        {
	        	for(int x=0; x<listKey.size(); x++)
	            {
	            	if( removeOrder.get(e) == listKey.get(x) )
	            	{
	            		orderMapDividedWithClock.remove(listKey.get(x));
	            	}
	            }
	        }
        }
	}
	
	public void orderCluster(Map<Integer, List<Integer>> clusterOrder, Map<String, List<Integer>> temporaryMapClusterOrder)
	{
		
		int seedGroupNumber = 5;
		
		LinkedList<Integer> listKey = new LinkedList<Integer>();
        Iterator<Integer> iteratorKey = clusterOrder.keySet().iterator();
        
        while(iteratorKey.hasNext())
        {
        	int key =  iteratorKey.next();
        	listKey.add(key);
        }
        
        Map<String, Double> seedGroupMap=new LinkedHashMap<String, Double>();
        Map<String, List<Integer>> seedGroupMap1 = new LinkedHashMap<String, List<Integer>>();
        
        for(int i=0; i<listKey.size()-1; i++)
        {
        	for(int x=i; x<listKey.size(); x++)
        	{
        		if(x!=i)
        		{
        			
        		ArrayList<Integer> temp = new ArrayList<Integer>();
        		temp.addAll(clusterOrder.get(listKey.get(i)));
                temp.addAll(clusterOrder.get(listKey.get(x)));                
                Set<Integer> setList = new LinkedHashSet<Integer>(temp);
                List<Integer> newList = new LinkedList<Integer>(setList);
                List<Integer> ListWithInitialPoint=new LinkedList<Integer>();
                ListWithInitialPoint.addAll(newList);
                initialPointObject.setListWithInitialPoint(ListWithInitialPoint);
               
                List<Integer> temporaryList = new LinkedList<Integer>();
                
                temporaryList.addAll(clusterOrder.get(listKey.get(i)));
                List<Integer> ListWithInitialPoint1=new LinkedList<Integer>();
                ListWithInitialPoint1.addAll(temporaryList);
                initialPointObject.setListWithInitialPoint(ListWithInitialPoint1);
                
                double  temporaryDistance = (double)nearestNeighborObject.nearestNeighbor( ListWithInitialPoint1 )/ (double)nearestNeighborObject.nearestNeighbor(ListWithInitialPoint);
                
                String seedGroup = listKey.get(i) + "-" + listKey.get(x);
                
                seedGroupMap.put(seedGroup, temporaryDistance);
                seedGroupMap1.put(seedGroup, newList);
                                                	
        		}
        	}
        }
        
        LinkedList<String> temporaryListKey = new LinkedList<String>();
        Iterator<String> temporaryIteratorKey = seedGroupMap.keySet().iterator();
        
        while(temporaryIteratorKey.hasNext())
        {
        	String key =  temporaryIteratorKey.next();
        	temporaryListKey.add(key);
        }
        
        List<Double> mergeList = new LinkedList<Double>();
        
        for(int q=0; q<temporaryListKey.size(); q++)
        {
        	mergeList.add(q, seedGroupMap.get(temporaryListKey.get(q)));
        }
        
        mergeSortObject.mergeSortLinkedList(mergeList);
        
        List <Integer> usedOrder = new LinkedList<Integer>();
        
        int b=0, n=0, controlNumber = 0;
        int clusterNumberControl=0;
        
        if((listKey.size()-seedGroupNumber)>0)
        {
        	clusterNumberControl = listKey.size()-seedGroupNumber;
        }
        
        if(seedGroupNumber< clusterNumberControl)
        {
        	controlNumber=seedGroupNumber;
        }
        else
        {
        	controlNumber=clusterNumberControl;
        }
        
        while(b<controlNumber)  
        {
        	for(int r=0; r<temporaryListKey.size(); r++)
        	{
        		if(mergeList.get(n) == seedGroupMap.get(temporaryListKey.get(r)))
        		{
        			boolean control = true;
        			List <Integer> controlList= new LinkedList<Integer>();
        			
        			String tempString=temporaryListKey.get(r);
        			tempString = tempString.replace(" ", "");
        	    	String[] changeStr = tempString.split("-");
        	    	for(String a: changeStr)
        	    	{
        	    		controlList.add(Integer.parseInt(a));
        	    	}
        	    	
        	    	for(int e=0; e<controlList.size(); e++)
        	    	{
        	    		for(int s=0; s<usedOrder.size(); s++)
        	    		{
        	    			if(usedOrder.get(s) == controlList.get(e))
        	    			{
        	    				control=false;
        	    			}
        	    		}
        	    	}
        	    	
        	    	if(control)
        	    	{
	        			List<Integer> listTemporaryMapClusterOrder = new LinkedList<Integer>();
	        			listTemporaryMapClusterOrder.addAll(seedGroupMap1.get(temporaryListKey.get(r)));
	        			temporaryMapClusterOrder.put(temporaryListKey.get(r), listTemporaryMapClusterOrder);
	        			
	        	    	for(String a: changeStr)
	        	    	{
	        	    		usedOrder.add(Integer.parseInt(a));
	        	    	}
	        	    	Set<Integer> setList = new LinkedHashSet<Integer>(usedOrder);
	        	    	usedOrder.clear();
	        	    	usedOrder.addAll(setList);
	        	    	
	        	    	for(int c=0; c<usedOrder.size() ;c++)
	        	        {
	        	        	clusterOrder.remove(usedOrder.get(c));
	        	        }
	        	    		
	        	    	b++;
        	    	}
        		}
        	}
        	n++;
        }
        
        LinkedList<String> temporaryListKey1 = new LinkedList<String>();
        Iterator<String> iteratorKey2 = temporaryMapClusterOrder.keySet().iterator();
        
        while(iteratorKey2.hasNext())
        {
        	String key =  iteratorKey2.next();
        	temporaryListKey1.add(key);
        }
        
        int control = temporaryListKey1.size();
        
        if(temporaryListKey1.size()<seedGroupNumber)
        {
        	seedGroupNumber=temporaryListKey1.size();
        }
        
        while(control>0)
        {
        	orderClusterMethod(clusterOrder, temporaryMapClusterOrder, seedGroupNumber);
        	
        	LinkedList<Integer> listKey1 = new LinkedList<Integer>();
            Iterator<Integer> iteratorKey1 = clusterOrder.keySet().iterator();
            
            while(iteratorKey1.hasNext())
            {
            	int key =  iteratorKey1.next();
            	listKey1.add(key);
            }
            control = listKey1.size();
            
        }
    }
	
	public void orderClusterMethod(Map<Integer, List<Integer>> clusterOrder, Map<String, List<Integer>> temporaryMapClusterOrder, int seedGroupNumber)
	{
		
		LinkedList<Integer> listKey1 = new LinkedList<Integer>();
        Iterator<Integer> iteratorKey1 = clusterOrder.keySet().iterator();
        
        while(iteratorKey1.hasNext())
        {
        	int key =  iteratorKey1.next();
        	listKey1.add(key);
        }
        
        LinkedList<String> temporaryListKey1 = new LinkedList<String>();
        Iterator<String> temporaryIteratorKey1 = temporaryMapClusterOrder.keySet().iterator();
        
        while(temporaryIteratorKey1.hasNext())
        {
        	String key =  temporaryIteratorKey1.next();
        	temporaryListKey1.add(key);
        }
        
        Map<String, Double> seedGroupMap2=new LinkedHashMap<String, Double>();
        Map<String, List<Integer>> seedGroupMap3 = new LinkedHashMap<String, List<Integer>>();
        
        for(int u=0; u<seedGroupNumber; u++)
        {
        	for(int z=0; z<listKey1.size(); z++)
        	{
        		ArrayList<Integer> temp = new ArrayList<Integer>();
        		temp.addAll(temporaryMapClusterOrder.get(temporaryListKey1.get(u)));
        		temp.addAll(clusterOrder.get(listKey1.get(z)));                
        		Set<Integer> setList = new LinkedHashSet<Integer>(temp);
        		List<Integer> newList = new ArrayList<Integer>(setList);
        		List<Integer> ListWithInitialPoint = new LinkedList<Integer>();
        		ListWithInitialPoint.addAll(newList);
        		
        		initialPointObject.setListWithInitialPoint(ListWithInitialPoint);
                
                List<Integer> temporaryList = new LinkedList<Integer>();
                
                temporaryList.addAll(temporaryMapClusterOrder.get(temporaryListKey1.get(u)));
                List<Integer> ListWithInitialPoint1=new LinkedList<Integer>();
                ListWithInitialPoint1.addAll(temporaryList);
                initialPointObject.setListWithInitialPoint(ListWithInitialPoint1);
                
               
        		double  temporaryDistance = (double)nearestNeighborObject.nearestNeighbor( ListWithInitialPoint1 )/ (double)nearestNeighborObject.nearestNeighbor( ListWithInitialPoint);
        		
                String seedGroup = temporaryListKey1.get(u) + "-" + listKey1.get(z);
                
                seedGroupMap2.put(seedGroup, temporaryDistance);
                seedGroupMap3.put(seedGroup, newList);
        	}
        }
        
        LinkedList<String> temporaryListKey4 = new LinkedList<String>();
        Iterator<String> temporaryIteratorKey5 = seedGroupMap2.keySet().iterator();
        
        while(temporaryIteratorKey5.hasNext())
        {
        	String key =  temporaryIteratorKey5.next();
        	temporaryListKey4.add(key);
        }
        
        List<Double> mergeList1 = new LinkedList<Double>();
        
        for(int q=0; q<temporaryListKey4.size(); q++)
        {
        	mergeList1.add(q, seedGroupMap2.get(temporaryListKey4.get(q)));
        }
        
        mergeSortObject.mergeSortLinkedList(mergeList1);
        
        int m=0, p=0, controlNumber=0;
        
        List <Integer> usedOrder1 = new LinkedList<Integer>();
        List <String> usedOrder2 = new LinkedList<String>();
        
        if(seedGroupNumber< listKey1.size())
        {
        	controlNumber=seedGroupNumber;
        }
        else
        {
        	controlNumber=listKey1.size();
        }
        
        while(m<controlNumber) 
        {
        	for(int r=0; r<temporaryListKey4.size(); r++)
        	{
        		if(mergeList1.get(p) == seedGroupMap2.get(temporaryListKey4.get(r)))
        		{
        			boolean control = true;
        			List <Integer> controlList= new LinkedList<Integer>();
        			
        			String tempString=temporaryListKey4.get(r);
        			tempString = tempString.replace(" ", "");
        	    	String[] changeStr = tempString.split("-");
        	    	for(String a: changeStr)
        	    	{
        	    		controlList.add(Integer.parseInt(a));
        	    	}
        	    	
        	    	for(int s=0; s<usedOrder1.size(); s++)
        	    	{
        	    		for(int i=0; i<controlList.size(); i++)
        	    		{
        	    			if(usedOrder1.get(s) == controlList.get(i))
        	    			{
        	    				control=false;
        	    			}
        	    		}
        	    	}
        	    	
        	    	if(control)
        	    	{
        	    		String tempChangeStr = "";
            	    	
            	    	for(int t=0; t<changeStr.length-1;t++)
            	    	{
            	    		if(0 != tempChangeStr.length())
        					{
            	    			tempChangeStr = tempChangeStr + "-" + changeStr[t];
        					}
        					else
        					{
        						tempChangeStr = String.valueOf(changeStr[t]);
        					}
            	    	}
            	    	
            	    	if(!(usedOrder2.contains(tempChangeStr)))
            	    	{
            	    		usedOrder2.add(tempChangeStr);
            	    	}
            	    	
        			List<Integer> listTemporaryMapClusterOrder = new LinkedList<Integer>();
        			
        			listTemporaryMapClusterOrder.addAll(seedGroupMap3.get(temporaryListKey4.get(r)));
        			temporaryMapClusterOrder.put(temporaryListKey4.get(r), listTemporaryMapClusterOrder);
        			
        	    	for(String a: changeStr)
        	    	{
        	    		usedOrder1.add(Integer.parseInt(a));
        	    	}
        	    	
        	    	Set<Integer> setList = new LinkedHashSet<Integer>(usedOrder1);
        	    	usedOrder1.clear();
        	    	usedOrder1.addAll(setList);
        	    	
        	    	for(int c=0; c<usedOrder2.size() ;c++)
        	        {
        	    		temporaryMapClusterOrder.remove(usedOrder2.get(c));
        	        }
        	    	
        	    	for(int c=0; c<usedOrder1.size() ;c++)
        	        {
        	        	clusterOrder.remove(usedOrder1.get(c));
        	        }
        	    	
        	    	m++;
        	    	}
        		}
        	}
        	p++;
        }
	}
}
