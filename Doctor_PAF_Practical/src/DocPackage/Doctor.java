/**
 * 
 */
package DocPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author dyasa
 *
 */
public class Doctor {

	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/doctor", "root", "root");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	
	public String readDoctors()
	{
			String output = "";
			
			try
			{
					Connection con = connect();
					
					if (con == null)
					{
							return "Error while connecting to the database for reading.";
					}
					
					// Prepare the html table to be displayed
					output = "<table class=\"table table-success\" border='1'><tr><th>NIC</th>"
							+ "<th>Name</th>"
							+"<th>Email</th>"
							+ "<th>Contact Number</th>"
							+ "<th>Gender</th>"
							+ "<th>Appointment Fee</th>"
							+ "<th>Specification</th>"
							+ "<th>Hospital</th>"
							+ "<th>Number of appointments</th>"
							+ "<th>Password</th>"
							+ "<th>Update</th><th>Remove</th></tr>";
					
					String query = "select * from doctor"; // table name doctor
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					
					// iterate through the rows in the result set
					while (rs.next())
					{
							String docNic = rs.getString("docNic");
							String docName = rs.getString("docName");
							String docEmail = rs.getString("docEmail");
							String docContact = rs.getString("docContact");
							String docGender = rs.getString("docGender");
							String docFee = Double.toString(rs.getDouble("docFee"));
							String docSpec = rs.getString("docSpec");
							String docHospital = rs.getString("docHospital");
							String docNumAppointments = Integer.toString(rs.getInt("docNumAppointments"));
							String docPassword = rs.getString("docPassword");
							
							// Add into the html table
							output += "<tr><td><input id='hidItemIDUpdate'"
								   + "	name='hidItemIDUpdate'"
								   + " type='hidden' value='" + docNic
								   + "'>" + docNic + "</td>";
							output += "<td>" + docName + "</td>";
							output += "<td>" + docEmail + "</td>";
							output += "<td>" + docContact + "</td>";
							output += "<td>" + docGender + "</td>";
							output += "<td>" + docFee + "</td>";
							output += "<td>" + docSpec + "</td>";
							output += "<td>" + docHospital + "</td>";
							output += "<td>" + docNumAppointments + "</td>";
							output += "<td>" + docPassword + "</td>";
							
							// buttons
							output += "<td><input name='btnUpdate'"
								   + "type='button' value='Update'"
								   + "class='btnUpdate btn btn-secondary'></td>"
								   + "<td><input name='btnRemove'"
								   + "type='button' value='Remove'"
								   + " class='btnRemove btn btn-danger'	"
								   + "data-itemid='"
							       + docNic + "'>" + "</td></tr>";
					}
					con.close();
					// Complete the html table
					output += "</table>";
			}
			catch (Exception e)
			{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
			}
			return output;
	}
	
    public String insertdoctors(String docNic, String docName, String docEmail, String docContact,String docGender,String docFee, String docSpec, String docHospital,String docNumAppointments,String docPassword  ) {
		
		String output = "";
		
		try {
				Connection con = connect();

				if (con == null) {
					
					return "Error while connecting to the database";
					
				}

			// create a prepared statement
			String query = " insert into doctor " + "(`docNic`,`docName`,`docEmail`,`docContact`,`docGender`,`docFee`,`docSpec`,`docHospital`,`docNumAppointments`,`docPassword`)"
					+ " values (?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, docNic);
			preparedStmt.setString(2, docName);
			preparedStmt.setString(3, docEmail);
			preparedStmt.setString(4, docContact);
			preparedStmt.setString(5, docGender);
			preparedStmt.setDouble(6, Double.parseDouble(docFee));
			preparedStmt.setString(7, docSpec);
			preparedStmt.setString(8, docHospital);
			preparedStmt.setInt(9,Integer.parseInt(docNumAppointments) );
			preparedStmt.setString(10, docPassword);

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newdoc = readDoctors();
			output = "{\"status\":\"success\", \"data\": \"" + newdoc + "\"}";

		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the Doctor Deatils.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String UpdateDoctors(String docNic,String docEmail, String docContact,String docFee,String docHospital,String docNumAppointments,String docPassword  ) {
		String output = "";
		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = "update doctor set docEmail = ?,docContact = ?,docFee = ?,docHospital = ?,docNumAppointments = ?,docPassword = ? where docNic = ?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			preparedStmt.setString(1, docEmail);
			preparedStmt.setString(2, docContact);
			preparedStmt.setDouble(3, Double.parseDouble(docFee));
			preparedStmt.setString(4, docHospital);
			preparedStmt.setInt(5,Integer.parseInt(docNumAppointments) );
			preparedStmt.setString(6, docPassword);
			
			preparedStmt.setString(7, docNic);
			

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newdoc = readDoctors();
			output = "{\"status\":\"success\", \"data\": \"" + newdoc + "\"}";

		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while updating the Doctor Details.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
public String deletedoctor(String docNic) {
		
		String output = "";

		try {

				Connection con = connect();
	
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
	
				// create a prepared statement
				String query = "delete from doctor where docNic=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, docNic);
	
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				String newdoc = readDoctors();
				output = "{\"status\":\"success\", \"data\": \"" + newdoc + "\"}";
		}		
		catch (Exception e)
		{
				output = "{\"status\":\"error\", \"data\":\"Error while deleting a Doctor.\"}";
				System.err.println(e.getMessage());
		}
		
		return output;
	}

}
