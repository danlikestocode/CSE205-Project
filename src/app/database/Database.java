package app.database;

import java.sql.*;
import java.util.ArrayList;

//This class is our database controller it contains the methods that read, write and search the database.
public class Database {
	//Basic global variables that are used at different instants 
	static Statement s;
	static Connection c;
	static ResultSet rs;

	//This method connects the database as connection c and statement s which will be
	//used throughout the database controller
	public static void databaseconnect() {

		try {
			c = DriverManager.getConnection("jdbc:postgresql://" + Keys.address + "/usersdb", Keys.username,
					Keys.password);

			s = c.createStatement();
			System.out.println("Connected to the database");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	//basic retrieval method to get an integer from the very first row of the selected 
	//data table in the selected column
	public static int getInt(String dataTable, String ColumnName) {
		int result = 0;

			try {
				//sets rs to search the data table to be read
				rs = s.executeQuery("select * from " + dataTable + ";" );
				//moves the query to the first line of the table
				rs.next();
				//gets the integer value of the first row of the selected column
				result = rs.getInt(ColumnName);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				

		return result;
	}
	
	//Same as the above method but for a string
	public static String getString(String dataTable, String ColumnName) {
		String result = "";

			try {
				rs = s.executeQuery("select * from " + dataTable + ";" );
				rs.next();
				result = rs.getString(ColumnName);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				

		return result;
		
		
	}
	
	//searches the selected database for a specified string in a specified column and database
	public static void searchForString(String dataTable, String columnName, String searchItem) {
		boolean match = false;
		String result = "";
		try {
			//select the table to be searched
			rs = s.executeQuery("Select * FROM " + dataTable + ";");
			//search the column for the matching string
			while(!match) {
				rs.next();
				result = rs.getString(columnName);
				//test result to see if it is a match
				if (result.equals(searchItem)) {
					match = true;
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//searches the selected database for a specified int in a specified column and database
	public static void searchForInt(String dataTable, String columnName, int searchItem) {
		boolean match = false;
		int result;
		try {
			//select the table to be searched
			rs = s.executeQuery("Select * FROM " + dataTable + ";");
			//search the column for the matching int
			while(!match) {
				rs.next();
				result = rs.getInt(columnName);
				//test result to see if it is a match
				if (result == searchItem) {
					match = true;
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//returns a string from the input column from the currently selected row of the
	//currently selected data table
	public static String returnCurrentString(String columnName) {
		String result = "";
		try {
		result = rs.getString(columnName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public static int returnCurrentInt(String columnName) {
		int result=0;
		try {
		result = rs.getInt(columnName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static Integer[][] return2DArray(String columnName){
		Array fromDatabase;
		Integer[][] result = null;
		try {
		fromDatabase = rs.getArray(columnName);
		result = (Integer[][])fromDatabase.getArray();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		 
		return result;
		
	}

}