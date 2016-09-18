/*        
 *        Developed by Hamdi Bayhan in 2016
 * --->   github.com/HamdiBayhan
 * --->	  linkedin.com/in/hamdi-bayhan-b3133248
 * 
 */
package warehouseProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseClass {
	
	public static Connection con;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
	     Class.forName("org.sqlite.JDBC");
	     con = DriverManager.getConnection("jdbc:sqlite:WarehouseProject.db");
	     
	     return null;
	}
}
