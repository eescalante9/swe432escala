package exam;

//Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//Import Java Libraries
import java.io.*;
import java.util.*;
import java.lang.*;


@WebServlet(name = "exam", urlPatterns = "/exam")

public class exam extends HttpServlet
{
public void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException
{
	
	
	
	
	
response.setContentType("text/html");
PrintWriter out = response.getWriter();

out.print("<html>\n<head>\n\n");
out.print("<title>Truth Table</title>\n");
out.print("</head>\n");
out.print("<body>\n");
out.print("<center><h2>Truth Table</h2></center>\n");
out.print("<hr>\n");


String entry = request.getParameter("statement");

String before;
String after;
if (entry.contains("&&"))
{
	after = entry.substring( 0, entry.indexOf("&&"));
	before = entry.substring(entry.indexOf("&&")+3, entry.length());
	out.println("Truth Table for  ");
	out.println(entry);
	out.println("<br>");
	out.println("<br>");
	out.println(after+"|||"+ before + "|||" + after+"  AND  "+before );
	out.println("<br>");
	out.println("|T   &nbsp  |||      T      |||      T       " );
	out.println("<br>");
	out.println("|T   &nbsp  |||      F      |||      F       " );
	out.println("<br>");
	out.println("|F   &nbsp  |||      T      |||      F       " );
	out.println("<br>");
	out.println("|F   &nbsp  |||      F      |||      F       " );
}
else if (entry.contains("AND"))
{
	after = entry.substring( 0, entry.indexOf("AND"));
	before = entry.substring(entry.indexOf("AND")+3, entry.length());
	out.println("Truth Table for  ");
	out.println(entry);
	out.println("<br>");
	out.println("<br>");
	out.println(after+"|||"+ before + "|||" + after+"  AND  "+before );
	out.println("<br>");
	out.println("|T   &nbsp  |||      T      |||      T       " );
	out.println("<br>");
	out.println("|T   &nbsp  |||      F      |||      F       " );
	out.println("<br>");
	out.println("|F   &nbsp  |||      T      |||      F       " );
	out.println("<br>");
	out.println("|F   &nbsp  |||      F      |||      F       " );
}
else if (entry.contains("||"))
{
	after = entry.substring( 0, entry.indexOf("||"));
	before = entry.substring(entry.indexOf("||")+3, entry.length());
	out.println("Truth Table for  ");
	out.println(entry);
	out.println("<br>");
	out.println("<br>");
	out.println(after+"|||"+ before + "|||" + after+"  OR  "+before );
	out.println("<br>");
	out.println("|T   &nbsp  |||      T      |||      T       " );
	out.println("<br>");
	out.println("|T   &nbsp  |||      F      |||      T       " );
	out.println("<br>");
	out.println("|F   &nbsp  |||      T      |||      T       " );
	out.println("<br>");
	out.println("|F   &nbsp  |||      F      |||      F       " );
	
	
}
else if (entry.contains("OR"))
{
	after = entry.substring( 0, entry.indexOf("OR"));
	before = entry.substring(entry.indexOf("OR")+3, entry.length());
	out.println("Truth Table for  ");
	out.println(entry);
	out.println("<br>");
	out.println("<br>");
	out.println(after+"|||"+ before + "|||" + after+"  OR  "+before );
	out.println("<br>");
	out.println("|T   &nbsp  |||      T      |||      T       " );
	out.println("<br>");
	out.println("|T   &nbsp  |||      F      |||      T       " );
	out.println("<br>");
	out.println("|F   &nbsp  |||      T      |||      T       " );
	out.println("<br>");
	out.println("|F   &nbsp  |||      F      |||      F       " );
	
	
}
else if (entry.contains("XOR"))
{
	after = entry.substring( 0, entry.indexOf("XOR"));
	before = entry.substring(entry.indexOf("XOR")+3, entry.length());
	out.println("Truth Table for  ");
	out.println(entry);
	out.println("<br>");
	out.println("<br>");
	out.println(after+"|||"+ before + "|||" + after+"XOR  "+before );
	out.println("<br>");
	out.println("|T   &nbsp  |||      T      |||      F       " );
	out.println("<br>");
	out.println("|T   &nbsp  |||      F      |||      T       " );
	out.println("<br>");
	out.println("|F   &nbsp  |||      T      |||      T       " );
	out.println("<br>");
	out.println("|F   &nbsp  |||      F      |||      F       " );
	
	
}
else if (entry.contains("X"))
{
	after = entry.substring( 0, entry.indexOf("X"));
	before = entry.substring(entry.indexOf("X")+3, entry.length());
	out.println("Truth Table for  ");
	out.println(entry);
	out.println("<br>");
	out.println("<br>");
	out.println(after+"|||"+ before + "|||" + after+"XOR  "+before );
	out.println("<br>");
	out.println("|T   &nbsp  |||      T      |||      F       " );
	out.println("<br>");
	out.println("|T   &nbsp  |||      F      |||      T       " );
	out.println("<br>");
	out.println("|F   &nbsp  |||      T      |||      T       " );
	out.println("<br>");
	out.println("|F   &nbsp  |||      F      |||      F       " );
	
	
}
else {
	out.println("Logical operator NOT FOUND");
	
}



out.print("</body>\n");
out.print("</html>\n");

out.close ();
}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	out.println("<html>");
	out.println("<head>");
	out.println("<title> Truth table Maker</title>");
	out.println("</head>");
	   
	
	out.println("<ul><h1>Truth Table Maker</h1></ul>");
	out.println("<form Action =\"exam\" method=\"post\"  >");
	out.println("<table>");
    
	out.println("<tr>");
	out.println("<td>Enter statement:");
	out.println("<td><input type=\"text\" name=\"statement\" ");
	out.println("</tr>");
	out.println("<br>");
	out.println("(Use only || , OR, &&, AND, X, XOR)");
	out.println("<br>");
	out.println("<tr> <td>");
	out.println("<input type=\"submit\" name=\"submit_button\" value=\"Submit\" onClick=\"confirmation()\" >");
	out.println("</td></tr>");
	
	out.println("<script>");
	out.println("function confirmation()");
	out.println("{");
	out.println("var str = \"||\" ");
	out.println("if (str.includes(\"||\"))"); 
	out.println("{"); 
	
	out.println("   alert(\"Statement Submitted\");");
	out.println("}");
	out.println("}");
	
	
	
	
	
	
	
		out.println("</script>");
		out.println("</body>");
		out.println("</html>");
}	






}