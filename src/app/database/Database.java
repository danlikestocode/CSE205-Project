package app.database;

import java.sql.*;
import java.util.Arrays;
import java.util.Random;

//This class is our database controller it contains the methods that read, write and search the database.
public class Database {
	// Basic global variables that are used at different instants
	static Statement s;
	static Connection c;
	static ResultSet rs;
	public static double totalPrice;

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


	//Retruns an array of objects from the cart
	public static int[] returnArray(String columnName) {
		String fromDatabase;
		int[] result = new int[] {};
		try {

			if (rs.getArray(columnName) != null) { // make sure there's something there
				fromDatabase = rs.getArray(columnName).toString(); //array to string
				fromDatabase = fromDatabase.substring(1, fromDatabase.length() - 1); //remove the brackets
				String[] stringArr = fromDatabase.split(",");

				//translate string array to int array
				result = new int[stringArr.length];
				for (int i = 0; i < stringArr.length; i++) {
					result[i] = Integer.parseInt(stringArr[i]);
				}
			}
			//if (fromDatabase != null) result = (int[]) fromDatabase.getArray();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	//Method to create a user into postgres
	public static boolean createUser(String usernames, String password, String email, String fname, String lname,
									 String address, int designation) {
		boolean successful = false;
		try {
			//Creates the user
			s.addBatch("Insert into users VALUES ('" + usernames + "','" + password + "','" + email + "','" + fname + "','"
					+ lname + "','" + address + "', NULL ," + designation + ");");
			s.executeBatch();
			successful = true;
		} catch (SQLException e) {
			//Fails to make the user
			successful = false;
		}
		return successful;
	}

	//Update something in the database that is string base
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
	//Update something form the database that is int base
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

	//Update something in the database that is of a double
	public static boolean updateDouble(String datatable, String columnName, String identifyingID, double newValue, String idColumnName) {
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

	//Update something in the database to be either true or false
	public static boolean updateBoolean(String datatable, String columnName, String identifyingID, boolean newValue, String idColumnName) {
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

	//Update the array in the database
	public static boolean updateArray(String datatable, String columnName, String identifyingID, int[] newValue, String idColumnName) {
		boolean successful = false;

		//selects the correct ID column for the selected table
		String strArray = "{"+newValue[0];

		for (int i =1; i < newValue.length;i++) {
			strArray=strArray+","+newValue[i];
		}
		strArray = strArray+"}";
		//debug print
		//System.out.println("UPDATE " + datatable+ " SET " + columnName + " = '"+strArray+"' WHERE " + idColumnName + " = '"+identifyingID+"';");
		try {
			s.execute("UPDATE " + datatable+ " SET " + columnName + " = '"+strArray+"' WHERE " + idColumnName + " = '"+identifyingID+"'");
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
	//method runs to check out cart and get total and make appropriate changes
	public static void checkoutCart(String username) {
		Random rnd = new Random();
		// 1 = Apples - Price: 1.5
		// 2 = Banana - Price: 6.5
		// 3 = Soda - Price: 8.99
		// 4 = Pear - Price: 1.3
		// 5 = Cheese - Price: 19.99
		// 6 = Chips - Price: 0.3
		int aApples, aBanana, aSoda, aPear, aCheese, aChips;
		int cApples, cBanana, cSoda, cPear, cCheese, cChips;



		String strCart = "";
		int[] cart = null;
		int orderNumber  = rnd.nextInt(999999999);

		//uses database to get cart with amount of each product and assigns values to variables
		Database.searchForString("users", "usernames", User.getUsername());
		cart = Database.returnArray("cart");
		aApples = cart[1];
		aBanana = cart[2];
		aSoda = cart[3];
		aPear = cart[4];
		aCheese = cart[5];
		aChips = cart[6];

		// prints out amount of each products you are checking out with.
		strCart = Arrays.toString(cart);
		System.out.println(strCart);
		strCart = strCart.replaceAll("\\[" , "{").replaceAll("\\]", "}");

		try {
			s.execute("UPDATE users SET cart = '{0, 0, 0, 0, 0, 0, 0}' where usernames = '" + User.getUsername() + "';");
			s.execute("INSERT INTO purchases VALUES (' " + orderNumber + " ' , ' " + strCart + "' , 'false');");

		    rs = s.executeQuery("SELECT stock FROM products WHERE productname = 'Apples';");
		    rs.next();
		    cApples = rs.getInt("stock");
		    cApples = cApples - aApples;

			rs = s.executeQuery("SELECT stock FROM products WHERE productname = 'Banana';");
			rs.next();
			cBanana = rs.getInt("stock");
			cBanana = cBanana - aBanana;

			rs = s.executeQuery("SELECT stock FROM products WHERE productname = 'Soda';");
			rs.next();
			cSoda = rs.getInt("stock");
			cSoda = cSoda - aSoda;

			rs = s.executeQuery("SELECT stock FROM products WHERE productname = 'Pear';");
			rs.next();
			cPear = rs.getInt("stock");
			cPear = cPear - aPear;

			rs = s.executeQuery("SELECT stock FROM products WHERE productname = 'Cheese';");
			rs.next();
			cCheese = rs.getInt("stock");
			cCheese = cCheese - aCheese;

			rs = s.executeQuery("SELECT stock FROM products WHERE productname = 'Chips';");
			rs.next();
			cChips = rs.getInt("stock");
			cChips = cChips - aChips;

			s.execute("UPDATE products SET stock = '" + String.valueOf(cApples)+ "' WHERE productname = 'Apples';");
			s.execute("UPDATE products SET stock = '" + String.valueOf(cBanana)+ "' WHERE productname = 'Banana';");
			s.execute("UPDATE products SET stock = '" + String.valueOf(cSoda)+ "' WHERE productname = 'Soda';");
			s.execute("UPDATE products SET stock = '" + String.valueOf(cPear)+ "' WHERE productname = 'Pear';");
			s.execute("UPDATE products SET stock = '" + String.valueOf(cCheese)+ "' WHERE productname = 'Cheese';");
			s.execute("UPDATE products SET stock = '" + String.valueOf(cChips)+ "' WHERE productname = 'Chips';");

			//gets total price and prints it
			totalPrice = (aApples * 1.5) + (aBanana * 6.5) + (aSoda * 8.99) + (aPear * 1.3) + (aCheese * 19.99) + (aChips * .3);
			System.out.println(totalPrice);

		//Catches any exceptions and prints the error
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	//Returns a product that is searched in the project
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

	//Returns a product that is searched in the project
	public static ResultSet purchaseResultSet() {

		try {
			rs = s.executeQuery("select * from purchases where completed = false;");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return rs;
	}
}