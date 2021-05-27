package stub;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

import static org.junit.Assert.*;

public class TestWebClient {

    @BeforeClass
    public static void setup() {
        TestWebClient t = new TestWebClient();
        URL.setURLStreamHandlerFactory(t.new StubStreamHandlerFactory());
    }

    private class StubStreamHandlerFactory implements URLStreamHandlerFactory {
        public URLStreamHandler createURLStreamHandler(String protocol) {
            return new StubHttpURLStreamHandler();
        }
    }

    private class StubHttpURLStreamHandler extends URLStreamHandler{
        protected URLConnection openConnection(URL url) throws IOException {
            return new StubHttpURLConnection(url);
        }
    }

    @Test
    public void testGetContentOk() throws Exception {
        WebClient client = new WebClient();
        String result = client.getContent(new URL("http://localhost"));
        assertEquals("It works", result);
    }
}
