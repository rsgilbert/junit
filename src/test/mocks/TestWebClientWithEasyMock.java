package mocks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;

public class TestWebClientWithEasyMock {
    private ConnectionFactory connectionFactory;
    private InputStream inputStream;

    @Before
    public void setup() {
        connectionFactory = createMock("connectionFactory", ConnectionFactory.class);
        inputStream = createMock("inputStream", InputStream.class);
    }

    @Test
    public void testGetContentOk() throws Exception {
        // Expectations
        expect(connectionFactory.getData()).andReturn(inputStream);
        expect(inputStream.read()).andReturn((int) (byte) 'N');
        expect(inputStream.read()).andReturn((int) (byte) 'I');
        expect(inputStream.read()).andReturn((int) (byte) 'C');
        expect(inputStream.read()).andReturn((int) (byte) 'E');
        expect(inputStream.read()).andReturn((int) (byte) '!');
        expect(inputStream.read()).andReturn(-1);
        inputStream.close();

        // End of expectations
        replay(connectionFactory, inputStream);
        WebClient client = new WebClient();
        String content = client.getContent(connectionFactory);
        assertEquals("getContent must return correct content", "NICE!", content);
    }

    @Test
    public void testGetContentCannotCloseInputStream() throws Exception {
        expect(connectionFactory.getData()).andReturn(inputStream);
        expect(inputStream.read()).andReturn(-1);
        inputStream.close();
        // purposely throw an error after close is called to test null result
        expectLastCall().andThrow(new IOException("Can not close"));
        replay(connectionFactory, inputStream);
        WebClient client = new WebClient();
        String result = client.getContent(connectionFactory);
        assertNull(result);
    }

    @After
    public void tearDown() {
        verify(connectionFactory, inputStream);
    }
}
