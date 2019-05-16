public class App {
 
    public static void main(String[] args) {
            final Jetty jetty = new Jetty(8080);
            try{ 
                jetty.start();
                Thread.sleep(500);
                if (false == jetty.isStarted()) {
                    throw new Exception("Cannot start jetty server");
                }
            } catch(Exception e)  { }
    }
}
