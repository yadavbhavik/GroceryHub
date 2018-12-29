

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.mysql.jdbc.Driver;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;


@WebServlet("/Shop_verify_by_admin")
public class Shop_verify_by_admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Shop_verify_by_admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
			
		int id = Integer.parseInt(request.getParameter("sellerid"));
		
		try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/groceryhub", "root", "");
				Statement stmt = (Statement)conn.createStatement();
				String sql = "select sellermobileno from sellerregistration where sellerid="+id;
				ResultSet rs = (ResultSet)stmt.executeQuery(sql);
				
				JSONObject jsonObject = new JSONObject();
				
				if(rs.next())
				{
					jsonObject.put("sellermobileno", rs.getString("sellermobileno"));
				}
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().println(jsonObject.toString());
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
