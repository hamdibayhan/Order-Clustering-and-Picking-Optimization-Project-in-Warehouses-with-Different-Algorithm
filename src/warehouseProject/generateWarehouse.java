/*        
 *        Developed by Hamdi Bayhan in 2016
 * --->   github.com/HamdiBayhan
 * --->	  linkedin.com/in/hamdi-bayhan-b3133248
 * 
 */
package warehouseProject;

public class generateWarehouse {

	static int warehouseLength=7;
	static int xAisleWidth=1;
	static int yAisleWidth=1;
	static int warehouseSubPartHeight=3;
	static int [][]warehouse;
	
	public int[][] generateWarehouseMethod()
	{
		warehouse= new int[warehouseLength][warehouseLength];
		int productID =1;
		
		for(int i=0; i<warehouseLength; i++)
		{
			for(int a=0; a<warehouseLength;a++)
			{
				warehouse[i][a]=productID;
				productID++;
			}
		}
		
		return warehouse;
	}
	
}

