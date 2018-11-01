package com.caps;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.mysql.jdbc.Driver;

public class UpdatePassword 
{
	private static Scanner sc;

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
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
			 
			String dbUrl="jdbc:mysql://localhost:3306/capsV3_db";
			String filePath = "E:/db.properties";
			FileReader reader = new FileReader(filePath);
			Properties prop = new Properties();
			prop.load(reader);
			
			con = DriverManager.getConnection(dbUrl, prop);
			
			
			System.out.println("Connected...");

			/*
			 * 3. Issue the SQL query via connection
			 */
			String sql = "update students_info set password=? where sid=? and password=?";
			System.out.println("enter the sid:");
			sc = new Scanner(System.in);
			int sid=Integer.parseInt(sc.nextLine());
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(2,sid);
			System.out.println("enter the current password:");
			String oldp=sc.nextLine();
			pstmt.setString(3,oldp);
			System.out.println("enter the new password:");
			String newp=sc.nextLine();
			pstmt.setString(1,newp);
			System.out.println("enter the new confirmed password:");
			String newc=sc.nextLine();
			if(newp.equals(newc)) {
			int count = pstmt.executeUpdate();
			if(count>0)
			{
				System.out.println("updated succesfully");
			}
			else
			{
				System.out.println("please enter correct old password");
			}
			}
			else 
			{
			System.out.println("new and confirmed password does not match");	
			}

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
			}
*/
		} catch (Exception e) {
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
			if(pstmt != null){
				try {
					pstmt.close();
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


}
