package app.database;

import java.sql.*;

public class Database {
	static Statement s;
	static Connection c;
	static ResultSet rs;

	public static void databaseconnect() {
		// TODO Auto-generated method stub

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

	public static int getInt(String dataTable, String ColumnName) {
		int result = 0;

			try {
				rs = s.executeQuery("select * from " + dataTable + ";" );
				rs.next();
				result = rs.getInt(ColumnName);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				

		return result;
	}
	
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
	
	public static void searchForString(String dataTable, String columnName, String searchItem) {
		boolean match = false;
		String result = "";
		try {
			rs = s.executeQuery("Select * FROM " + dataTable + ";");
			while(!match) {
				rs.next();
				result = rs.getString(columnName);
				
				if (result.equals(searchItem)) {
					match = true;
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void searchForInt(int searchItem) {
		
	}
	
	public static String returnCurrentString(String columnName) {
		String result = "";
		try {
		result = rs.getString(columnName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}


}