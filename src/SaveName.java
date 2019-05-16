import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@SuppressWarnings("serial")
public class SaveName extends HttpServlet {
 
    public SaveName() { }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                                             throws ServletException, IOException {
        try {
            final long timeStamp = System.currentTimeMillis();
            System.out.println("POST (incoming key/value String pairs): ");
            final String name = request.getParameter("name");
            final String surName = request.getParameter("surName");
            System.out.println("name:"+name+" surname:"+surName);
            response.setStatus(HttpServletResponse.SC_OK);
 
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 
        } finally {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("");
            response.getWriter().close();
        }
    }
 
}
