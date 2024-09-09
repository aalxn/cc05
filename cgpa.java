import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cgpa")
public class CGPACalculatorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // HTML template with embedded Java
    private static final String HTML_TEMPLATE = 
        "<!DOCTYPE html>" +
        "<html>" +
        "<head>" +
        "<title>CGPA Calculator</title>" +
        "</head>" +
        "<body>" +
        "<h1>CGPA Calculator</h1>" +
        "<form method='post'>" +
        "<label for='grade1'>Enter grade for subject 1:</label>" +
        "<input type='text' id='grade1' name='grade1' required><br><br>" +
        "<label for='grade2'>Enter grade for subject 2:</label>" +
        "<input type='text' id='grade2' name='grade2' required><br><br>" +
        "<label for='grade3'>Enter grade for subject 3:</label>" +
        "<input type='text' id='grade3' name='grade3' required><br><br>" +
        "<label for='grade4'>Enter grade for subject 4:</label>" +
        "<input type='text' id='grade4' name='grade4' required><br><br>" +
        "<input type='submit' value='Calculate CGPA'>" +
        "</form>" +
        "<div>" +
        "<h2>Result:</h2>" +
        "<p>%s</p>" +
        "</div>" +
        "</body>" +
        "</html>";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display the form on GET request
        response.setContentType("text/html");
        response.getWriter().print(String.format(HTML_TEMPLATE, ""));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result;
        try {
            // Retrieve the grades from the form
            double grade1 = Double.parseDouble(request.getParameter("grade1"));
            double grade2 = Double.parseDouble(request.getParameter("grade2"));
            double grade3 = Double.parseDouble(request.getParameter("grade3"));
            double grade4 = Double.parseDouble(request.getParameter("grade4"));

            // Calculate the CGPA
            double cgpa = (grade1 + grade2 + grade3 + grade4) / 4.0;

            // Format result
            result = String.format("Your CGPA is: %.2f", cgpa);
        } catch (NumberFormatException e) {
            result = "Invalid input. Please enter numeric values.";
        }

        // Display the result
        response.setContentType("text/html");
        response.getWriter().print(String.format(HTML_TEMPLATE, result));
    }
}
