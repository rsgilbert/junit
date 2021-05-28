package mocks;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class TestWebClient {

    @Test
    public void testContentOkUsingConnectionFactory() throws Exception {
        String streamContent = "I_LOVED_HER";
        MockConnectionFactory mockConnectionFactory = new MockConnectionFactory();
        MockInputStream mockInputStream = new MockInputStream();
        mockInputStream.setBuffer(streamContent);
        mockConnectionFactory.setData(mockInputStream);

        WebClient client = new WebClient();
        String result = client.getContent(mockConnectionFactory);

        assertEquals("Content from connection must match", streamContent, result);
        mockInputStream.verify();
    }

    @Test
    public void testGetContentOk() throws Exception {
    // Commented out because it wont compile due to missing classes but not deleted for future reference
    /*
        // Create a mock HttpURLConnection
        MockHttpConnection mockConnection = new MockHttpConnection();
        mockConnection.setExpectedInputStream(new ByteArrayInputStream("It works".getBytes()));

//        // Create a mock URL
//        MockURL mockURL = new MockURL();
//        mockURL.setupOpenConnection(mockConnection);


        // Test the getContent method
        TestableWebClient client = new TestableWebClient();
        client.setHttpURLConnection(mockConnection);
        String result = client.getContent(new URL("Http://localhost"));
        assertEquals("It works", result);
*/

    }
}
