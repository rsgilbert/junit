package stub;

import org.junit.*;
import org.mortbay.jetty.HttpHeaders;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.util.ByteArrayISO8859Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import static org.junit.Assert.*;

public class TestWebClientSkeleton {

    @BeforeClass
    public static void setUp() throws Exception {
        Server server = new Server(8000);
        TestWebClientSkeleton t = new TestWebClientSkeleton();

        // Set handler for content ok
        Context contentOkContext = new Context(server, "/testGetContentOk");
        contentOkContext.setHandler(t.new TestGetContentOkHandler()); // read about this

        // Set handler for content not found
        Context contentNotFoundContext = new Context(server, "/testGetContentNotFound");
        contentNotFoundContext.setHandler(t.new TestGetContentNotFoundHandler());

        server.setStopAtShutdown(true);
        server.start();
    }

    @AfterClass
    public static void tearDown() {
        // Stop Jetty. Intentionally left empty because
        // jetty has been programmed to stop at shutdown
    }


    @Test
    public void testGetContentOk() throws Exception {
        WebClient client = new WebClient();
        String result = client.getContent(new URL(
                "http://localhost:8000/testGetContentOk"
        ));
        assertEquals("Response by HTTP server does not match expected response",
                "It works", result);
    }

    @Test
    public void testGetContentNotFound() throws Exception {
        WebClient client = new WebClient();
        String result = client.getContent(new URL(
                "http://localhost:8000/testGetContentNotFound"
        ));
        assertNull(result);
    }
    /**
     * Handler for content ok
     */
    private class TestGetContentOkHandler extends AbstractHandler {
        @Override
        public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException {
            OutputStream out = response.getOutputStream();
            ByteArrayISO8859Writer writer = new ByteArrayISO8859Writer();
            writer.write("It works");
            writer.flush();
            System.out.println("how are you");
            // Jetty requires that we se the response content length
            response.setIntHeader(HttpHeaders.CONTENT_LENGTH, writer.size());
            writer.writeTo(out);
            out.flush();
        }
    }

    /**
     * Handler for content not found
     */
    private class TestGetContentNotFoundHandler extends AbstractHandler {
        @Override
        public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }


}
