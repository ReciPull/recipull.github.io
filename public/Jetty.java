import com.google.gson.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
 
public class Jetty extends Server {
 
    public Jetty(int port) {
        super(port);
 
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler
        .NO_SESSIONS);
        context.setContextPath("/test");
        context.addServlet(new ServletHolder(new GetName()), "/GetName/*");
        context.addServlet(new ServletHolder(new GetVote()), "/GetVote/*");
        context.addServlet(new ServletHolder(new GetFilter()), "/GetFilter/*");
        context.addServlet(new ServletHolder(new GetNameAlexa()), "/GetNameAlexa/*");
        //context.addServlet(new ServletHolder(new SaveName()), "/SaveName/*");
        //context.addServlet(new ServletHolder(new SaveArray()), "/SaveArray/*");
        this.setHandler(context);
        this.setStopAtShutdown(true);
    }
 
}
