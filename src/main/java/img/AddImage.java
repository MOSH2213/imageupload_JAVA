package img;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet("/AddImage")
public class AddImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddImage() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		// section of image fetching starts
		System.out.println("In do post method of Add Image servlet.");
		//used in order to fetch file names 
		Part file = request.getPart("image");
		//i the vaariable stores the file name o the iage submitted in the addimage.jsp page
		String imageFileName = file.getSubmittedFileName();
		//prints the relevant image file name in the console
		System.out.println("Selected Image File Name : " + imageFileName);
		//makes the image upload to the relevant path below given
		String uploadPath = "E:/1.SLIIT/YEAR 2/SEM 1/Java_Eclipse/ImageTutorial/src/main/webapp/img/" + imageFileName; // upload																										// image
		//prins the path of the upload
		System.out.println("Upload Path : " + uploadPath);

		// Uploading our selected image into the images folder
		try {
			//uploads to the erlevant path given in line 40 (file handling based codes)
			FileOutputStream fos = new FileOutputStream(uploadPath);
			InputStream is = file.getInputStream();

			byte[] data = new byte[is.available()];
			is.read(data);
			fos.write(data);
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// getting database connection (jdbc code)
				Connection connection = null;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/imageTutorial", "root", "#True12345");
					PreparedStatement stmt;
					//query to isert upload image name to database
					String query = "insert into image(imageFileName) values(?)";
					stmt = connection.prepareStatement(query);
					//sets the value of imagefilename 
					stmt.setString(1, imageFileName);
					int row = stmt.executeUpdate(); // it returns no of rows affected.

					if (row > 0) {
						System.out.println("Image added into database successfully.\nImage name : "+imageFileName);
						//redirects to displayImage.jsp page
						response.sendRedirect("displayImage.jsp");
					}

					else {
						System.out.println("Failed to upload image.");
					}

				} catch (Exception e) {
					System.out.println(e);
				}
	}

}
