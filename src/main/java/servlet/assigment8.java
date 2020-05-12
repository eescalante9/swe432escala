package assignment8;

//Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//Import Java Libraries
import java.io.*;
import java.util.*;
import java.lang.*;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;


@WebServlet(name = "assignment8", urlPatterns = "/assignment8")

public class assigment8 extends HttpServlet
{
	static enum Data {LIBRARY, LIGHTING, NOISE, BOOK, AMENITIES, ENTRY, ENTRIES};

	  static String RESOURCE_FILE = "entries.xml";

	  static String Domain  = "";
	  static String Path    = "/";
	  static String Servlet = "xml";

	  // Button labels
	  static String OperationAdd = "Add";

	  public class Entry {
	    String library;
	    String lighting;
	    String noise;
	    String book;
	    String amenities;
	  }

	  List<Entry> entries;

	  public class EntryManager {
	    private String filePath = null;
	    private XMLEventFactory eventFactory = null;
	    private XMLEvent LINE_END = null;
	    private XMLEvent LINE_TAB = null;
	    private XMLEvent ENTRIES_START = null;
	    private XMLEvent ENTRIES_END = null;
	    private XMLEvent ENTRY_START = null;
	    private XMLEvent ENTRY_END = null;


	    public EntryManager(){
	      eventFactory = XMLEventFactory.newInstance();
	      LINE_END = eventFactory.createDTD("\n");
	      LINE_TAB = eventFactory.createDTD("\t");

	      ENTRIES_START = eventFactory.createStartElement(
	        "","", Data.ENTRIES.name());
	      ENTRIES_END =eventFactory.createEndElement(
	        "", "", Data.ENTRIES.name());
	      ENTRY_START = eventFactory.createStartElement(
	        "","", Data.ENTRY.name());
	      ENTRY_END =eventFactory.createEndElement(
	        "", "", Data.ENTRY.name());
	    }
	    public void setFilePath(String filePath) {
	      this.filePath = filePath;
	    }

	    public List<Entry> save(String library, String lighting, String noise, String book, String amenities)
	      throws FileNotFoundException, XMLStreamException{
	      List<Entry> entries = getAll();
	      Entry newEntry = new Entry();
	      newEntry.library = library;
	      newEntry.lighting = lighting;
	      newEntry.noise = noise;
	      newEntry.book = book;
	      newEntry.amenities = amenities;
	      entries.add(newEntry);

	      XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
	      XMLEventWriter eventWriter = outputFactory
	              .createXMLEventWriter(new FileOutputStream(filePath));

	      eventWriter.add(eventFactory.createStartDocument());
	      eventWriter.add(LINE_END);

	      eventWriter.add(ENTRIES_START);
	      eventWriter.add(LINE_END);

	      for(Entry entry: entries){
	        addEntry(eventWriter, entry.library, entry.lighting, entry.noise, entry.book, entry.amenities);
	      }

	      eventWriter.add(ENTRIES_END);
	      eventWriter.add(LINE_END);

	      eventWriter.add(eventFactory.createEndDocument());
	      eventWriter.close();
	      return entries;
	    }

	    private void addEntry(XMLEventWriter eventWriter, String library, String lighting, String noise, String book,
	      String amenities) throws XMLStreamException {
	        eventWriter.add(ENTRY_START);
	        eventWriter.add(LINE_END);
	        createNode(eventWriter, Data.LIBRARY.name(), library);
	        createNode(eventWriter, Data.LIGHTING.name(), lighting);
	        createNode(eventWriter, Data.NOISE.name(), noise);
	        createNode(eventWriter, Data.BOOK.name(), book);
	        createNode(eventWriter, Data.AMENITIES.name(), amenities);
	        eventWriter.add(ENTRY_END);
	        eventWriter.add(LINE_END);

	    }

	    private void createNode(XMLEventWriter eventWriter, String name,
	          String value) throws XMLStreamException {
	      StartElement sElement = eventFactory.createStartElement("", "", name);
	      eventWriter.add(LINE_TAB);
	      eventWriter.add(sElement);

	      Characters characters = eventFactory.createCharacters(value);
	      eventWriter.add(characters);

	      EndElement eElement = eventFactory.createEndElement("", "", name);
	      eventWriter.add(eElement);
	      eventWriter.add(LINE_END);

	    }

	    private List<Entry> getAll(){
	      List entries = new ArrayList();

	      try{

	        File file = new File(filePath);
	        if(!file.exists()){
	          return entries;
	        }

	        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
	        InputStream in = new FileInputStream(file);
	        XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

	        Entry entry = null;
	        while (eventReader.hasNext()) {
	          // <ENTRIES> not needed for the example
	          XMLEvent event = eventReader.nextEvent();

	          if (event.isStartElement()) {
	              StartElement startElement = event.asStartElement();
	              if (startElement.getName().getLocalPart()
	                .equals(Data.ENTRY.name())) {
	                  entry = new Entry();
	              }

	              if (event.isStartElement()) {
	                  if (event.asStartElement().getName().getLocalPart()
	                          .equals(Data.LIBRARY.name())) {
	                      event = eventReader.nextEvent();
	                      entry.library =event.asCharacters().getData();
	                      continue;
	                  }
	              }
	              if (event.isStartElement()) {
	                  if (event.asStartElement().getName().getLocalPart()
	                          .equals(Data.LIGHTING.name())) {
	                      event = eventReader.nextEvent();
	                      entry.lighting =event.asCharacters().getData();
	                      continue;
	                  }
	              }
	              if (event.isStartElement()) {
	                  if (event.asStartElement().getName().getLocalPart()
	                          .equals(Data.NOISE.name())) {
	                      event = eventReader.nextEvent();
	                      entry.noise =event.asCharacters().getData();
	                      continue;
	                  }
	              }
	              if (event.isStartElement()) {
	                  if (event.asStartElement().getName().getLocalPart()
	                          .equals(Data.BOOK.name())) {
	                      event = eventReader.nextEvent();
	                      entry.book =event.asCharacters().getData();
	                      continue;
	                  }
	              }
	              if (event.isStartElement()) {
	                  if (event.asStartElement().getName().getLocalPart()
	                          .equals(Data.AMENITIES.name())) {
	                      event = eventReader.nextEvent();
	                      entry.amenities =event.asCharacters().getData();
	                      continue;
	                  }
	              }
	             
	          }

	          if (event.isEndElement()) {
	              EndElement endElement = event.asEndElement();
	              if (endElement.getName().getLocalPart()
	              .equals(Data.ENTRY.name())) {
	                  entries.add(entry);
	              }
	          }

	        }

	      }catch (FileNotFoundException e) {
	        e.printStackTrace();
	      }catch (XMLStreamException e) {
	        e.printStackTrace();
	      }catch(IOException ioException){
	        ioException.printStackTrace();
	      }

	      return entries;
	    }

	  public String getAllAsHTMLTable(List<Entry> entries){
	    StringBuilder htmlOut = new StringBuilder("<table>");
	    htmlOut.append("<tr><th>Library</th><th>Overall Lighting</th><th>Noise Level</th><th>Book Selection</th><th>Amenities</th></tr>");
	    if(entries == null || entries.size() == 0){
	      htmlOut.append("<tr><td>No entries yet.</td></tr>");
	    }else{
	      for(Entry entry: entries){
	         htmlOut.append("<tr><td>"+entry.library+"</td><td>"+entry.lighting+"</td><td>"+entry.noise+"</td><td>"+entry.book+"</td><td>"+entry.amenities+"</td></tr>");
	      }
	    }
	    htmlOut.append("</table>");
	    return htmlOut.toString();
	  }


	}  
	
	
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


String lib = request.getParameter(Data.LIBRARY.name());
String light = request.getParameter(Data.LIGHTING.name());
String noise = request.getParameter(Data.NOISE.name());
String books = request.getParameter(Data.BOOK.name());
String amenities = request.getParameter(Data.AMENITIES.name());

EntryManager entryManager = new EntryManager();
entryManager.setFilePath(RESOURCE_FILE);
List<Entry> newEntries= null;
try{
  newEntries=entryManager.save(lib, light, noise, books,amenities);
}catch(FileNotFoundException e){
  e.printStackTrace();
}
catch(XMLStreamException e){
  e.printStackTrace();
}

out.println("<body>");
out.println("<p>");
out.println("");
out.println("</p>");
out.println("");
out.println(entryManager.getAllAsHTMLTable(newEntries));
out.println("");
out.println("</body>");
out.print("</html>\n");
out.close();

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
	out.println("<form Action =\"assignment8\" method=\"post\">");
	out.println("<p1>");
	out.println("<p>Select a Library to rate:</p>");
			out.println("<select name=\""+ Data.LIBRARY.name() + "\" id=\"library_name\" onchange=\"nextStep()\">");
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
		out.println("<input type = \"radio\" name = \"" + Data.LIGHTING.name() + "\" id=\"1\" value=\"1\" required>");
		out.println("2");
		out.println("<input type = \"radio\" name = \"" + Data.LIGHTING.name() + "\" id=\"2\" value=\"2\">");
		out.println("3");
		out.println("<input type = \"radio\" name = \"" + Data.LIGHTING.name() + "\" id=\"3\" value=\"3\">");
	    out.println("4");
		out.println("<input type = \"radio\" name = \"" + Data.LIGHTING.name() + "\" id=\"4\" value=\"4\">");
		out.println("5");
		out.println("<input type = \"radio\" name = \"" + Data.LIGHTING.name() + "\" id=\"5\" value=\"5\">");
		out.println("</td></tr>");

		out.println("<tr><td>Noise level </td>");
		out.println("<td> ");
		out.println("1");
		out.println("<input type = \"radio\" name = \""+ Data.NOISE.name() +"\" id=\"1\" value=\"1\" required>");
		out.println("2");
		out.println("<input type = \"radio\" name = \""+ Data.NOISE.name() +"\" id=\"2\" value=\"2\">");
		out.println("3");
		out.println("<input type = \"radio\" name = \""+ Data.NOISE.name() +"\" id=\"3\" value=\"3\">");
		out.println("4");
		out.println("<input type = \"radio\" name = \""+ Data.NOISE.name() +"\" id=\"4\" value=\"4\">");
		out.println("5");
		out.println("<input type = \"radio\" name = \""+ Data.NOISE.name() +"\" id=\"5\" value=\"5\">");
		out.println("</td></tr>");

		out.println("<tr><td>Book Selection </td>");
		out.println("<td> ");
		out.println("1");
		out.println("<input type = \"radio\" name = \""+ Data.BOOK.name() +"\" id=\"1\" value=\"1\" required>");
		out.println("2");
		out.println("<input type = \"radio\" name = \""+ Data.BOOK.name() +"\" id=\"2\" value=\"2\">");
		out.println("3");
		out.println("<input type = \"radio\" name = \""+ Data.BOOK.name() +"\" id=\"3\" value=\"3\">");
		out.println("4");
		out.println("<input type = \"radio\" name = \""+ Data.BOOK.name() +"\" id=\"4\" value=\"4\">");
		out.println("5");
		out.println("<input type = \"radio\" name = \""+ Data.BOOK.name() +"\" id=\"5\" value=\"5\">");
		out.println("</td></tr>");

		out.println("<tr><td>Amenities </td>");
		out.println("<td> ");
		out.println("1");
		out.println("<input type = \"radio\" name = \""+ Data.AMENITIES.name() +"\" id=\"1\" value=\"1\" required>");
		out.println("2");
		out.println("<input type = \"radio\" name = \""+ Data.AMENITIES.name() +"\" id=\"2\" value=\"2\">");
		out.println("3");
		out.println("<input type = \"radio\" name = \""+ Data.AMENITIES.name() +"\" id=\"3\" value=\"3\">");
		out.println("4");
		out.println("<input type = \"radio\" name = \""+ Data.AMENITIES.name() +"\" id=\"4\" value=\"4\">");
		out.println("5");
		out.println("<input type = \"radio\" name = \""+ Data.AMENITIES.name() +"\" id=\"5\" value=\"5\">");
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