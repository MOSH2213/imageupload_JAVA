package img;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Dimg")
public class Dimg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//prints th below mesage in the console
		System.out.println("In do post method of Display Image servlet.");
		//takes the value coming from the inpt tags present in thr displayImage.jsp file
		String imageId=request.getParameter("imageId");
		//converts it into the integer format
		int id=Integer.parseInt(imageId);
		
		//getting database connection (jdbc code)
		Connection connection=null;
		//variable intitialized to 0
		int imgId=0;
		//sting variable initialized to null
		String imgFileName=null;
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/imageTutorial","root","#True12345");
			Statement stmt;
			String query="select * from image";
			stmt=connection.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			
			while(rs.next())
			{	
				//checks the database image id with the id entered in the input tags of the displayImage.jsp
				if(rs.getInt("imageId")==id)
				{
					//the database image id is stored in the initially 0 initialzed variable
					imgId=rs.getInt("imageId");
					//file name of the image in dataase is stored in the newly initialized vaiable in 34
					imgFileName=rs.getString("imageFileName");
				}
			}
				
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		RequestDispatcher rd;
		//set attrbute and hence this could be used in the jsp pages to print in the browser
		request.setAttribute("id",String.valueOf(imgId));  //valueOf
		request.setAttribute("img",imgFileName);
		//redirects to the displayImage.jsp file
		rd=request.getRequestDispatcher("displayImage.jsp");
		rd.forward(request, response);
	}

}
