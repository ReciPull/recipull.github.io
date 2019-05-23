import com.google.gson.*; //for JSON
import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@SuppressWarnings("serial")
public class GetVote extends HttpServlet {
 
    public GetVote() { }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
                                            throws ServletException, IOException {
        final JsonObject retVal = new JsonObject();
        String reqDataID = "NONE";
        if (request.getParameterMap().containsKey("ID")) {
            reqDataID = request.getParameter("ID");
        }
        String[] vals = reqDataID.split("`");
        String recipe = vals[0];
        int vote = Integer.parseInt(vals[1]);
        Driver d = new Driver();
        d.updateVote(vote, recipe);
        //retVal.addProperty(s, "final value");
        try {
            response.setStatus(HttpServletResponse.SC_OK);
 
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 
        } finally {
            response.addHeader("Access-Control-Allow-Origin", "*");
            //response.setContentType("text/html;charset=UTF-8");
            response.setContentType("application/json; charset=utf-8");
            //response.getWriter().println(retVal);
            response.getWriter().close();
        }
    }
 
}
