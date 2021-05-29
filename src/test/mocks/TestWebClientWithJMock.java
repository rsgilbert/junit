package mocks;

import org.easymock.cglib.transform.ClassVisitorTee;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.api.Imposteriser;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

public class TestWebClientWithJMock {
    private Mockery context = new JUnit4Mockery() {
        {
            // We intend to mock the InputStream which is a class
            // and not an interface so we use the ClassImposteriser
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };

    /**
     * Test that WebClient#getContent returns the expected content
     * @throws Exception
     */
    @Test
    public void testGetContentOk() throws Exception {
        final ConnectionFactory connectionFactory = context.mock(ConnectionFactory.class);
        final InputStream inputStream = context.mock(InputStream.class);

        context.checking(new Expectations() {
            {
                oneOf(connectionFactory).getData();
                will(returnValue(inputStream));

                atLeast(1).of(inputStream).read();
                will(onConsecutiveCalls(
                        returnValue((int) (byte) 'S'),
                        returnValue((int) (byte) 'W'),
                        returnValue((int) (byte) 'E'),
                        returnValue((int) (byte) 'E'),
                        returnValue((int) (byte) 'T'),
                        returnValue(-1)
                ));
                oneOf(inputStream).close();
            }
        });

        WebClient client = new WebClient();
        String result = client.getContent(connectionFactory);
        assertEquals("getContent should return the correct content", "SWEET", result);
    }

    /**
     * Test that getContent returns null if an exception is thrown in InputStream#close
     * @throws Exception
     */
    @Test
    public void testGetContentCannotCloseInputStream() throws Exception{
        final ConnectionFactory connectionFactory = context.mock(ConnectionFactory.class);
        final InputStream inputStream = context.mock(InputStream.class);

        context.checking(new Expectations(){
            {
                oneOf(connectionFactory).getData();
                will(returnValue(inputStream));

                oneOf(inputStream).read();
                will(returnValue(-1));
                oneOf(inputStream).close();
                will(throwException(new IOException("Can not close because I the mock says so")));

            }
        });

        WebClient client = new WebClient();
        String result = client.getContent(connectionFactory);
        // when client.getContent catches an exception, it should return null as the content
        assertNull(result);
    }
}
