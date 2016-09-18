/*        
 *        Developed by Hamdi Bayhan in 2016
 * --->   github.com/HamdiBayhan
 * --->	  linkedin.com/in/hamdi-bayhan-b3133248
 * 
 */
package warehouseProject;

import java.util.LinkedList;
import java.util.List;

public class MergeSort {
	
	public void mergeSortLinkedList(List<Double> sortLHM)
	{
		List<Double> tempSortLHM = new LinkedList<Double>();
		doMergeSort(0, (sortLHM.size()-1), sortLHM, tempSortLHM);
	}
	private void doMergeSort(int lowerIndex, int higherIndex, List<Double> arrayL, List<Double> tempMergArr) {
        
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            doMergeSort(lowerIndex, middle, arrayL, tempMergArr);
            doMergeSort(middle + 1, higherIndex, arrayL, tempMergArr);
            mergeParts(lowerIndex, middle, higherIndex, arrayL, tempMergArr);
        }
    }
	private void mergeParts(int lowerIndex, int middle, int higherIndex, List<Double> arrayL, List<Double> tempMergArr) 
	{
		 
	    for (int i = lowerIndex; i <= higherIndex; i++) {
	    	
	        tempMergArr.add(i, arrayL.get(i));
	    }
	    int i = lowerIndex;
	    int j = middle + 1;
	    int k = lowerIndex;
	    while (i <= middle && j <= higherIndex) {
	        if (tempMergArr.get(i) <= tempMergArr.get(j)) {
	        	arrayL.remove(k);
	        	arrayL.add(k,tempMergArr.get(i));
	            i++;
	        } else {
	        	arrayL.remove(k);
	        	arrayL.add(k, tempMergArr.get(j));
	            j++;
	        }
	        k++;
	    }
	    while (i <= middle) {
	    	arrayL.remove(k);
	    	arrayL.add(k, tempMergArr.get(i));
	        k++;
	        i++;
	    }
	}
}
