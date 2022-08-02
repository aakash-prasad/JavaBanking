package operations;
import landing.SqlConnection;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;


import landing.UserAccess;

@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		// Get the input entered by the user
		String entered_amount  = request.getParameter("to_deposit");
		System.out.println("The id entered by user is: "+entered_amount);
		
		//Field validation: cannot be empty and greater then 0
		if( entered_amount =="") {
			out.print("<body><center><div style=padding:50px;><h1>Please Enter amount to deposit</h1></div></center></body>");
		}
		if(Integer.parseInt(entered_amount)<0) {
			out.print("<body><center><div style=padding:50px;><h1>Anmount cannot be less then 0</h1></div></center></body>");
		}
		else {
			
			//Getting the details of logged in customer
			int current_balance = UserAccess.getAmount();
			int current_user = UserAccess.getCustomer_id();
			int to_be_inserted = current_balance + Integer.valueOf(entered_amount);
			System.out.println("Current Balance is: "+current_balance);
			
			System.out.println("To be inserted: "+to_be_inserted);
			//import the connection and run an insert query
			try {
				Connection conn = SqlConnection.initializeDatabase();
				System.out.println("Object from DepositServlet: "+conn);
				
				PreparedStatement update_query_stmt = conn.prepareStatement("UPDATE userData SET balance = "+to_be_inserted+" WHERE customer_id= "+current_user+";");
				update_query_stmt.executeUpdate();
				
			}catch(Exception e) {System.out.println("There is an exception: "+e);}
			
			
			out.print("<html><head><link rel=\"stylesheet\" href=\"./CSS/style.css\"></head><body><center><div class = card><div style= padding-top:10px;><h2>Deposit Succesful</h2></div></div></center><</body><html>");
			
			
			
		}
		}

}

