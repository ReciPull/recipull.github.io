import org.json.simple.*; //for JSON parsing
import org.json.simple.parser.*; //for JSON parsing
import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@SuppressWarnings("serial")
public class SaveArray extends HttpServlet {
 
    public SaveArray() {}
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                                             throws ServletException, IOException {
        final long timeStamp = System.currentTimeMillis();
        JSONParser parser = new JSONParser();
        String line = null;
        try {
            System.out.println("POST (incoming array): ");
            BufferedReader reader = request.getReader();
            line = reader.readLine(); //comes in as a single String line
            Object obj = parser.parse(line);
            JSONArray arr = (JSONArray)obj;
            for (int i = 0; i < arr.size(); i++) {
                JSONObject ele = (JSONObject)arr.get(i);
                System.out.println("\t"+ele);
            }
            response.setStatus(HttpServletResponse.SC_OK);
 
        } catch (Exception ex) {
            System.out.println("\tError");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 
        } finally {
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().println(new JSONObject());
            response.getWriter().close();
        }
    }
 
}
