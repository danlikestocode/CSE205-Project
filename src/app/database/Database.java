package app.database;

import java.sql.*;
import java.util.Arrays;

//This class is our database controller it contains the methods that read, write and search the database.
public class Database {
	// Basic global variables that are used at different instants
	static Statement s;
	static Connection c;
	static ResultSet rs;

	public Database() {
		databaseconnect();
	}

	// This method connects the database as connection c and statement s which will
	// be
	// used throughout the database controller
	private static void databaseconnect() {

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

	// basic retrieval method to get an integer from the very first row of the
	// selected
	// data table in the selected column
	public static int getInt(String dataTable, String ColumnName) {
		int result = 0;

		try {
			// sets rs to search the data table to be read
			rs = s.executeQuery("select * from " + dataTable + ";");
			// moves the query to the first line of the table
			rs.next();
			// gets the integer value of the first row of the selected column
			result = rs.getInt(ColumnName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	// Same as the above method but for a string
	public static String getString(String dataTable, String ColumnName) {
		String result = "";

		try {
			rs = s.executeQuery("select * from " + dataTable + ";");
			rs.next();
			result = rs.getString(ColumnName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	// searches the selected database for a specified string in a specified column
	// and database
	public static boolean searchForString(String dataTable, String columnName, String searchItem) {
		boolean match = false;
		String result = "";
		try {
			// select the table to be searched
			rs = s.executeQuery("Select * FROM " + dataTable + ";");
			// search the column for the matching string
			while (!match) {
				rs.next();
				result = rs.getString(columnName);
				// test result to see if it is a match
				if (result.equals(searchItem)) {
					match = true;
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
			match = false;
		}
		return match;
	}

	// searches the selected database for a specified int in a specified column and
	// database
	public static boolean searchForInt(String dataTable, String columnName, int searchItem) {
		boolean match = false;
		int result;
		try {
			// select the table to be searched
			rs = s.executeQuery("Select * FROM " + dataTable + ";");
			// search the column for the matching int
			while (!match) {
				rs.next();
				result = rs.getInt(columnName);
				// test result to see if it is a match
				if (result == searchItem) {
					match = true;
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
			match = false;
		}
		return match;
	}

	// returns a string from the input column from the currently selected row of the
	// currently selected data table
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
		int result = 0;
		try {
			result = rs.getInt(columnName);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static double returnCurrentDouble(String columnName) {
		double result = 0;
		try {
			result = rs.getDouble(columnName);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static int[] returnArray(String columnName) {
		Array fromDatabase;
		int[] result = null;
		try {
			fromDatabase = rs.getArray(columnName);
			if (fromDatabase != null) result = (int[]) fromDatabase.getArray();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}

	public static boolean createUser(String usernames, String password, String email, String fname, String lname,
									 String address, int designation) {
		boolean successful = false;

		try {

			s.addBatch("Insert into users VALUES ('" + usernames + "','" + password + "','" + email + "','" + fname + "','"
					+ lname + "','" + address + "', NULL ," + designation + ");");
			s.executeBatch();
			successful = true;
		} catch (SQLException e) {

			successful = false;
		}

		return successful;
	}

	public static boolean updateString(String datatable, String columnName, String identifyingID, String newValue, String idColumnName) {
		
		boolean successful = false;


		try {
			//sends the command to update the specified column in the specified table
			s.execute("Update " + datatable + " set " + columnName + " = \'" + newValue + "\' where " + idColumnName + " = \'" + identifyingID + "\';");
			successful = true;

		} catch (SQLException e) {
			successful = false;
		}
		//returns whether the update was successful
		return successful;
	}
	
//<<<<<<< HEAD
	public static boolean updateInt(String datatable, String columnName, String identifyingID, int newValue, String idColumnName) {
		
		boolean successful = false;


		try {
			//sends the command to update the specified column in the specified table
			s.execute("Update " + datatable + " set " + columnName + " = " + newValue + " where " + idColumnName + " = \'" + identifyingID + "\';");
			successful = true;

		} catch (SQLException e) {
			successful = false;
		}
		//returns whether the update was successful
		return successful;
	}


	public static boolean updateArray(String datatable, String columnName, String identifyingID, int[] newValue, String idColumnName) {

	
		boolean successful = false;
		//selects the correct ID column for the selected table
	
		String strArray = "{"+newValue[0];
		
		
		for (int i =1; i < newValue.length;i++) {
			strArray=strArray+","+newValue[i];
		}
		strArray = strArray+"}";
		
		System.out.println("UPDATE " + datatable+ " SET " + columnName + " = "+strArray+" WHERE '" + idColumnName + " = '"+identifyingID+"';");
		try {
			
			
			s.execute("UPDATE " + datatable+ " SET " + columnName + " = "+strArray+" WHERE '" + idColumnName + " = '"+identifyingID+"'");
			
			
			//PreparedStatement arrayStatement = c.prepareStatement("UPDATE " + datatable+ " SET " + columnName + " = ? WHERE '" + idColumnName + " = '"+identifyingID+"'");
			//sends the command to update the specified column in the specified table
			//s.execute("Update " + datatable + " set " + columnName + " = \'" + newValue + "\' where " + idColumnName + " = \'" + identifyingID + "\';");
			//arrayStatement.setArray(1, updatedArray);
			//arrayStatement.executeUpdate();
			successful = true;
			
		

		} catch (SQLException e) {
			successful = false;
		}
		//returns whether the update was successful
		return successful;
	}

	public boolean createProduct(String productName, int stock, double price, String description) {
		boolean successful = false;

		try {

			s.execute("Insert into products (productname, stock, price, description) VALUES ('" + productName + "'," + stock + "," + price + ",'" + description + "');");
			successful = true;

		} catch (SQLException e) {
			successful = false;
		}

		return successful;
	}


	public static ResultSet productResultSet(String search) {

		try {
			rs = s.executeQuery("select * from products where productname ~* '" + search + "'; ");
			// ~ means "includes" and the * means it's not case-sensitive
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return rs;
	}
}