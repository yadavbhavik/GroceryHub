

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.sql.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

@WebServlet("/Seller_registration")
public class Seller_registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Seller_registration() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		PrintWriter pw = response.getWriter();
		String Shop_category="",Shop_paymentmethod="";
		String Status="Pending";
		
		try
		{
			String Sellername = request.getParameter("sellername");
			String Selleremail = request.getParameter("selleremail");
			String Sellerconfirmemail = request.getParameter("sellerconfirmemail");
			String Selleraddress = request.getParameter("selleraddress");
			String Shopname = request.getParameter("shopname");
			String Shopaddress = request.getParameter("shopaddress");
			String Shopcategory[] = request.getParameterValues("shopcategory");
			String Shopemail = request.getParameter("shopemail");
			String Shoppaymentmethod[] = request.getParameterValues("shoppaymentmethod");
			String Sellerbankholdername = request.getParameter("sellerbankholdername");
			String Ifscno = request.getParameter("ifscno");
			String Gstno = request.getParameter("gstno");
			String Pancardno = request.getParameter("pancardno");
			String Aadharcardno = request.getParameter("aadharcardno");
			String Bankaccountno = request.getParameter("bankaccountno");
			
			String Sellermobileno = request.getParameter("sellermobileno");
			String Alternativemobileno = request.getParameter("alternativemobileno");
			String Shopmobileno = request.getParameter("shopmobileno");
			
			
			//shop category checkbox
			for(int i=0;i<Shopcategory.length;i++)
			{
				Shop_category+=Shopcategory[i]+",";
				if(i==(Shopcategory.length)-1)
				{
					Shop_category=Shop_category.substring(0,Shop_category.length()-1);
				}
			}
			
			//shop paymethod method checkbox
			for(int i=0;i<Shoppaymentmethod.length;i++)
			{
				Shop_paymentmethod+=Shoppaymentmethod[i]+",";
				if(i==(Shoppaymentmethod.length)-1)
				{
					Shop_paymentmethod=Shop_paymentmethod.substring(0,Shop_paymentmethod.length()-1);
				}
			}
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/groceryhub","root","");
			PreparedStatement pst = conn.prepareStatement("insert into sellerregistration(sellername,sellermobileno,alternativemobileno,email,confirmemail,selleraddress,shopname,shopaddress,shopcategory,shopemail,shopmobileno,shoppaymentmethod,aadharcardno,gstno,pancardno,bankaccountno,sellerbankholdername,ifscno,status) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pst.setString(1,Sellername);
			pst.setString(2,Sellermobileno);
			pst.setString(3,Alternativemobileno);
			pst.setString(4,Selleremail);
			pst.setString(5,Sellerconfirmemail);
			pst.setString(6,Selleraddress);
			pst.setString(7,Shopname);
			pst.setString(8,Shopaddress);
			pst.setString(9, Shop_category);
			pst.setString(10,Shopemail);
			pst.setString(11,Shopmobileno);
			pst.setString(12, Shop_paymentmethod);
			pst.setString(13,Aadharcardno);
			pst.setString(14,Gstno);
			pst.setString(15,Pancardno);
			pst.setString(16,Bankaccountno);
			pst.setString(17,Sellerbankholdername);
			pst.setString(18,Ifscno);
			pst.setString(19, Status);
			
			
			
			
			int i = pst.executeUpdate();
			if(i!=0)
			{
				pw.println("Data Inserted Successful");
			}
			else
			{
				pw.println("Data not Inserted!!!");				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
