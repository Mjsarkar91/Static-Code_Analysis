import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/contact")
public class ContactFormServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/ecommerce";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "yourpassword";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO contact_form (name, email, message) VALUES ('" + name + "', '" + email + "', '" + message + "')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Thank you for your submission, " + name + "!</h2>");
        out.println("</body></html>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM contact_form";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            out.println("<html><body>");
            out.println("<h2>Contact Form Submissions</h2>");
            while (resultSet.next()) {
                out.println("<p><strong>" + resultSet.getString("name") + ":</strong> " + resultSet.getString("message") + "</p>");
            }
            out.println("</body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
