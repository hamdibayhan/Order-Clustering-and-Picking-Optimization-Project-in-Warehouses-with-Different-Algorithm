package warehouseProject;

import java.util.LinkedList;
import java.util.List;

public class mainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Integer> asd = new LinkedList<Integer>();
		asd.add(5);
		asd.add(22);
		asd.add(13);
		asd.add(30);
		asd.add(1);
		asd.add(11);
		
		
		MeasureDistance mdis = new MeasureDistance();
		System.out.println(mdis.totalDistance(asd));
	}

}
