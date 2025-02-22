
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	Connection con; 
	public void init(ServletConfig config){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","7489");
		}catch(Exception e){
			System.out.println(e);
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password"); 
		try {
			PreparedStatement st = con.prepareStatement("select * from users where username='"+username+"' and password ='"+password+"'"); 
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				String name = rs.getString("name");
				out.println("Welcome "+name);
				out.println("<br>Click <a href=\"/LoginApplication/Login.jsp\">here</a> to login");
			}else {
				out.println("Login Failed");
				out.println("<br>Click <a href=\"/LoginApplication/Login.jsp\">here</a> to login");
			}
		} catch (SQLException e) {
			out.println(e);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public void  destroy() {
		try {
			con.close();
		}catch(Exception e) {
			
		}
	}
}
