import com.google.gson.*; //for JSON
import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@SuppressWarnings("serial")
public class GetName extends HttpServlet {
 
    public GetName() { }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
                                            throws ServletException, IOException {
        //String output = "";
        final JsonObject retVal = new JsonObject();
        String reqDataID = "NONE";
        if (request.getParameterMap().containsKey("ID")) {
            reqDataID = request.getParameter("ID");
        }
        String[] vals = reqDataID.split(":");
        String finVals = "";
        for(int i = 0; i < vals.length; i++) {
            if(i == (vals.length-1)) {
                finVals += "'"+vals[i]+"'";
            } else {
                finVals += "'"+vals[i]+"', ";
            }
        }
        System.out.println("GET request, ID: "+finVals);
        retVal.addProperty("prop1", "property1val");
        retVal.addProperty("prop2", "property2val");
        try {
            //output = retVal.toString();
            response.setStatus(HttpServletResponse.SC_OK);
 
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 
        } finally {
            response.addHeader("Access-Control-Allow-Origin", "*");
            //response.setContentType("text/html;charset=UTF-8");
            response.setContentType("application/json; charset=utf-8");
            //response.getWriter().println(retVal);
            response.getWriter().println(retVal);
            response.getWriter().close();
        }
    }
 
}
