

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;


@WebServlet("/send_login_details")
public class send_login_details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public send_login_details() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int id = Integer.parseInt(request.getParameter("sellerid"));
		String Status = "Success";
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/groceryhub", "root", "");
			Statement stmt = conn.createStatement();
			String sql = "select sellermobileno from sellerregistration where sellerid="+id;
			ResultSet rs = (ResultSet)stmt.executeQuery(sql);
			if(rs.next())
			{
				String mobile = rs.getString("sellermobileno");
				
				//send username and password
				try
				{	
					Random rand = new Random();
					int no = rand.nextInt(9000)+1000;
					
					 //Your authentication key
			        String authkey = "141319AYoCTFO1hhVt58a2f318";
			        //Multiple mobiles numbers separated by comma
			        String mobiles =mobile;
			        //Sender ID,While using route4 sender id should be 6 characters long.
			        String senderId = "GHub";
			        //Your message to send, Add URL encoding here.
			        String message = "Your Verification code is i.e OTP "+no;
			        //define route
			        String route="4";

			        //Prepare Url
			        URLConnection myURLConnection=null;
			        URL myURL=null;
			        BufferedReader reader=null;

			        //encoding message
			        String encoded_message=URLEncoder.encode(message);

			        //Send SMS API
			        String mainUrl="http://api.msg91.com/api/sendhttp.php?";

			        //Prepare parameter string
			        StringBuilder sbPostData= new StringBuilder(mainUrl);
			        sbPostData.append("authkey="+authkey);
			        sbPostData.append("&mobiles="+mobiles);
			        sbPostData.append("&message="+encoded_message);
			        sbPostData.append("&route="+route);
			        sbPostData.append("&sender="+senderId);

			        //final string
			        mainUrl = sbPostData.toString();
			        
			        response.getWriter().println(mainUrl);
			            //prepare connection
			            myURL = new URL(mainUrl);
			            myURLConnection = myURL.openConnection();
			            myURLConnection.connect();
			            reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
			            //reading response
			            String res;
			            while ((res = reader.readLine()) != null)
			            //print response
			            System.out.println(res);

			            //finally close connection
			            reader.close();
			            
			            System.out.println(no); 
			            
			            //insert username and password in sellerlogin table in database
			            PreparedStatement pst = (PreparedStatement)conn.prepareStatement("insert into sellerlogin(username,password) values(?,?)");
			            pst.setString(1, mobile);
			            pst.setString(2, String.valueOf(no));
			            int i = pst.executeUpdate();
			            JSONObject jsonObject = new JSONObject();
			            if(i!=0)
			            {	
			            	String query = "update sellerregistration set status = ? where sellermobileno = ?";
			                PreparedStatement preparedStmt = (PreparedStatement)conn.prepareStatement(query);
			                preparedStmt.setString(1,Status);
			                preparedStmt.setString(2,mobile);
			                preparedStmt.executeUpdate();
			            	
			            	jsonObject.put("message", "successful");
			            }
			            response.setContentType("text/html;charset=UTF-8");
						response.getWriter().println(jsonObject.toString());

					}   
			        catch (Exception e1)
			        {	 	
			                e1.printStackTrace();
			        }
				
				
			}
			else
			{
				System.out.println("modal mobile no. not received");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

}
