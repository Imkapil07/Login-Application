import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet { 
    Connection con; 
    
    public void init(ServletConfig config) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","7489");
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password"); 
        try {
            PreparedStatement st = con.prepareStatement("INSERT INTO users (name, username, password) VALUES (?, ?, ?)");
            st.setString(1, name);
            st.setString(2, username);
            st.setString(3, password);
            st.executeUpdate();
            out.println("Registered Successfully");
            out.println("<br>Click <a href=\"/Login.jsp\">here</a> to login");
        } catch (SQLException e) {
            out.println(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    } 

    public void destroy() {
        try {
            if (con != null)
                con.close();
        } catch(SQLException e) {
            System.out.println(e);
        }
    }
}
