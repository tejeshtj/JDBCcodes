package com.caps;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver; //required for jdbc to connect to mySql database



public class BatchExec {
	

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			/*
			 * 1. Load the Driver
			 */
			java.sql.Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			System.out.println("Driver Loaded...");
			/*
			 * 2. Get the DB Connection via Driver
			 */
						String dbUrl="jdbc:mysql://localhost:3306/capsv4_db"
								+ "?user=root&password=root";
			con = DriverManager.getConnection(dbUrl);
			System.out.println("Connected...");
			/*
			 * 3. Issue the SQL query via connection
			 */
			// Create statement object
             stmt = con.createStatement();

			// Set auto-commit to false
			con.setAutoCommit(false);

			// Create SQL statement
			String SQL = "insert into students_info values(13,'sai','tejesh','m','a','password')";
			// Add above SQL statement in the batch.
			stmt.addBatch(SQL);

			// Create one more SQL statement
			 SQL = "insert into students_info values(12,'sai','tejesh','m','a','password')";
			// Add above SQL statement in the batch.
			stmt.addBatch(SQL);

			// Create one more SQL statement
		     SQL = "UPDATE students_info SET password ='fuckoff'" +
			             "WHERE sid = 1001";
			// Add above SQL statement in the batch.
			stmt.addBatch(SQL);
			
			
			// Create an int[] to hold returned values
			int[] count = stmt.executeBatch();
			System.out.println("done");
			
			//Explicitly commit statements to apply changes
			con.commit();

			
			

			/*
			 * 4. Process the results
			 */

			/*while(rs.next()){
				int regno = rs.getInt("sid");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String gender = rs.getString("gender");
				String type=rs.getString("type"); 
				String passwd = rs.getString("password");

				System.out.println(regno);
				System.out.println(firstname);
				System.out.println(lastname);
				System.out.println(gender);
				System.out.println(type);
				System.out.println(passwd);
				System.out.println("*********************");
			}*/

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			/*
			 * 5. Close all the JDBC Objects
			 */

			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}//end of main

}//End of Class0