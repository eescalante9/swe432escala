package libSurvey;

//Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//Import Java Libraries
import java.io.*;
import java.util.*;
import java.lang.*;
@WebServlet(name = "libSurvey", urlPatterns = "/libSurvey")

public class libSurvey extends HttpServlet
{
public void doPost(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException
{
  response.setContentType("text/html");
  PrintWriter out = response.getWriter();

  out.print("<html>\n<head>\n\n");
  out.print("<title>Library Survey Confirmation</title>\n");
  out.print("</head>\n");
  out.print("<body>\n");
  out.print("<center><h2>Survey Results</h2></center>\n");
  out.print("<hr>\n");
  
  
  String lib = request.getParameter("library");
  String light = request.getParameter("lighting");
  String noise = request.getParameter("noise");
  String books = request.getParameter("book_selection");
  String amenities = request.getParameter("amenities");
  out.print("You choose to rate the ");
  out.print(lib);
  out.print(" a ");
  out.print(light);
  out.print(" for overall lighting,");
  out.print(" a ");
  out.print(noise);
  out.print(" for overall noise level,");
  out.print(" a ");
  out.print(books);
  out.print(" for book selection,");
  out.print(" and a ");
  out.print(amenities);
  out.print(" for amenities. ");
  out.print("</body>\n");
  out.print("</html>\n");

  out.close ();
}
}
