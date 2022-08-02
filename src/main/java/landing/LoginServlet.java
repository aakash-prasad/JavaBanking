package landing;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


// Once the user lands at the landing page and login this class will get called
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 try {
			Connection con = SqlConnection.initializeDatabase();
			System.out.println(con);
			PrintWriter out = response.getWriter();
			//Getting the inputs entered by the user in the form:
			String entered_id  = request.getParameter("customer_id");
			System.out.println("The id entered by user is: "+entered_id);
			String entered_password  = request.getParameter("password");
			System.out.println("The password entered by user is: "+entered_password);
			
			//Input field validation if the user has entered data properly
			if(entered_id == "") {
				out.print("<body><center><div style=padding:50px;><h1>Customer id cannot be blank</h1></div></center></body>");
			}
			if(entered_password == "") {
				out.print("<body><center><div style=padding:50px;><h1>Password cannot be blank</h1></div></center></body>");
			}
			
			//check if the id is matching with any in the database
			try {
				PreparedStatement check_id_exist = con.prepareStatement("SELECT * FROM userData WHERE "+entered_id+" in (customer_id)");
				ResultSet id_exist = check_id_exist.executeQuery();
				
				if(id_exist.next()) {
					//After the id matches we will set the static methods
					UserAccess.setCustomer_id(Integer.valueOf(entered_id));
					UserAccess.setCustomer_name(id_exist.getString(2));
					UserAccess.setAmount(Integer.valueOf(id_exist.getInt(4)));
					
					System.out.println("Customer Found");
					// Check if the user is active or not also validate the password
					if(id_exist.getBoolean(5) && entered_password.equals( id_exist.getString(3))) {
						
						
						//out.print("<body><center><div style=padding:50px;><h1>Hello User Welcome</h1></div></center></body>");
						out.print("<html><head>\r\n"
								+ " <link rel=\"stylesheet\" href=\"./CSS/style.css\">\r\n"
								+ "</head><body><center>"
								+ "<div class=card ><h1>Hello "+id_exist.getString(2)+" Welcome to JSW</h1></div>"
								+ "<div class= card><div class style= padding-top:50px; ><h1>What are you upto today</h1>\r\n"
								+ "<form action=\"./DepositServlet\" method=\"post\">\r\n"
								+ "  <input type=\"number\" id=\"to_deposit\" name=\"to_deposit\" value=\"\">\r\n"
								+ "  <input type=\"submit\" value=\"Deposit Amount\">\r\n"
								+ "</form>"
								+ "<form action=\"./WithdrawlServlet\" method=\"post\">\r\n"
								+ "  <input type=\"number\" id=\"to_deposit\" name=\"to_withdraw\" value=\"\">\r\n"
								+ "  <input type=\"submit\" value=\"Withdraw Now\">\r\n"
								+ "</form>"
								+ "<form action=\"./CheckBalanceServlet\" method=\"post\">\r\n"
								+ "  <input type=\"submit\" value=\"Check Balance\">\r\n"
								+ "</form> \r\n"
								+ "<form action=\"./LogoutServlet\" method=\"post\">\r\n"
								+ "  <input type=\"submit\" value=\"Logout\">\r\n"
								+ "</form></div></div></center> </body></html>");
					}
					else {
						//Password doesn't match, user will go to the login page again
						out.print("<body><center><div style=padding:50px;><h1>Incorrect Password</h1></div></center></body>");
						
					}
				}
				else {
					// Using nested if else here because user can actually know which particular field is not
					// matching weather it is customer id or password
					out.print("<body><center><div style=padding:50px;><h1>Customer does not exist</h1></div></center></body>");
				}
							
			}catch(Exception e) {System.out.println("Customer Does Not exist");}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

