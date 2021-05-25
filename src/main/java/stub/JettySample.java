package stub;


import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ResourceHandler;
import org.mortbay.jetty.servlet.Context;

/**
 * Stat Jetty in embedded mode and define a document root
 * Try this link: https://stackoverflow.com/questions/28037439/embedded-jetty-not-serving-empty-files
 */
public class JettySample {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8000);
        Context root = new Context(server, "/");
        root.setResourceBase("pom.xml"); // path = /home/ssenyonjo/Projects/junit-in-action/pom.xml
        System.out.println("Path: " + root.getResourceBase());
        root.setHandler(new ResourceHandler());
        server.start();
    }
}
