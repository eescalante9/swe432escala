package assignment7;


//Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//Import Java Libraries
import java.io.*;
import java.util.*;
import java.lang.*;


@WebServlet(name = "assignment7", urlPatterns = "/assignment7")

public class assignment7 extends HttpServlet
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

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	out.println("<html>");
	out.println("<head>");
	out.println("<title> Library Survey</title>");
	out.println("</head>");
	   
	out.println("<body bgcolor=#0373FC>"); 
	out.println("<ul><h1>Library Survey</h1></ul>");
	out.println("<form Action =\"libSurvey\" method=\"post\">");
	out.println("<p1>");
	out.println("<p>Select a Library to rate:</p>");
			out.println("<select name=\"library\" id=\"library_name\" onchange=\"nextStep()\">");
			out.println("<option value=(Select)>(Select)</option>");
			out.println("<option value=\"Arlington Campus Library\">Arlington Campus Library</option>");
			out.println("<option value=\"Arlington Law Library\">Arlington Law Library</option>");
			out.println("<option value=\"Fairfax  Fenwick Library\">Fairfax Fenwick Library</option>");
			out.println("<option value=\"Fairfax  Gateway Library\">Fairfax Gateway Library</option>");
			out.println("<option value=\"Manassas Mercer Library\">Manassas Mercer Library</option>");
			out.println("</select>");
					
out.println("<b>");
out.println("<table id=\"main\" border = 1 align=\"left\" style=\"visibility:hidden\" bgcolor=#C7E0FF>");
		out.println("<p> Rate 1(Poor) to 5(Great)");
		out.println("<tr><td>Overall Lighting </td>");
		out.println("	<td> ");
		out.println("1");
		out.println("<input type = \"radio\" name = \"lighting\" id=\"1\" value=\"1\" required>");
		out.println("||2");
		out.println("<input type = \"radio\" name = \"lighting\" id=\"2\" value=\"2\">");
		out.println("||3");
		out.println("<input type = \"radio\" name = \"lighting\" id=\"3\" value=\"3\">");
	    out.println("||4");
		out.println("<input type = \"radio\" name = \"lighting\" id=\"4\" value=\"4\">");
		out.println("||5");
		out.println("<input type = \"radio\" name = \"lighting\" id=\"5\" value=\"5\">");
		out.println("</td></tr>");

		out.println("<tr><td>Noise level </td>");
		out.println("<td> ");
		out.println("1");
		out.println("<input type = \"radio\" name = \"noise\" id=\"1\" value=\"1\" required>");
		out.println("||2");
		out.println("<input type = \"radio\" name = \"noise\" id=\"2\" value=\"2\">");
		out.println("||3");
		out.println("<input type = \"radio\" name = \"noise\" id=\"3\" value=\"3\">");
		out.println("||4");
		out.println("<input type = \"radio\" name = \"noise\" id=\"4\" value=\"4\">");
		out.println("||5");
		out.println("<input type = \"radio\" name = \"noise\" id=\"5\" value=\"5\">");
		out.println("</td></tr>");

		out.println("<tr><td>Book Selection </td>");
		out.println("<td> ");
		out.println("1");
		out.println("<input type = \"radio\" name = \"book_selection\" id=\"1\" value=\"1\" required>");
		out.println("||2");
		out.println("<input type = \"radio\" name = \"book_selection\" id=\"2\" value=\"2\">");
		out.println("||3");
		out.println("<input type = \"radio\" name = \"book_selection\" id=\"3\" value=\"3\">");
		out.println("||4");
		out.println("<input type = \"radio\" name = \"book_selection\" id=\"4\" value=\"4\">");
		out.println("||5");
		out.println("<input type = \"radio\" name = \"book_selection\" id=\"5\" value=\"5\">");
		out.println("</td></tr>");

		out.println("<tr><td>Amenities </td>");
		out.println("<td> ");
		out.println("1");
		out.println("<input type = \"radio\" name = \"amenities\" id=\"1\" value=\"1\" required>");
		out.println("||2");
		out.println("<input type = \"radio\" name = \"amenities\" id=\"2\" value=\"2\">");
		out.println("||3");
		out.println("<input type = \"radio\" name = \"amenities\" id=\"3\" value=\"3\">");
		out.println("||4");
		out.println("<input type = \"radio\" name = \"amenities\" id=\"4\" value=\"4\">");
		out.println("||5");
		out.println("<input type = \"radio\" name = \"amenities\" id=\"5\" value=\"5\">");
		out.println("</td></tr>");


		out.println("<tr> <td>");
		out.println("<input type=\"submit\" name=\"submit_button\" value=\"Submit\" onClick=\"confirmation()\" >");
		out.println("</td></tr>");

		out.println("</table>");
		out.println("</span>");
		out.println("<script>");
		out.println("function confirmation()");
		out.println("{");
		out.println("   alert(\"Survey Submitted\");");
		out.println("}");
		out.println("function nextStep()");
		out.println("{");
		out.println("   document.getElementById('main').style.visibility = \"visible\";");
		out.println("}");
		out.println("</script>");
		out.println("</body>");
		out.println("</html>");
}	
}
