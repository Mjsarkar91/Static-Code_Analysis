import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/search")
public class VulnerableSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the search query from the request
        String query = request.getParameter("query");

        // Set response content type
        response.setContentType("text/html");

        // Prepare the HTML response
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Search Results</title></head>");
        out.println("<body>");

        // Display the search query directly in the response (vulnerable to XSS)
        out.println("<h1>Search Results for: " + query + "</h1>");

        // Simulate a search result (just an example, no actual database logic)
        out.println("<p>Sorry, no results found for <b>" + query + "</b>.</p>");

        // Close the HTML response
        out.println("</body>");
        out.println("</html>");
    }
}
