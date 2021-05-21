package controller;

import controller.DefaultController;
import controller.Request;
import controller.RequestHandler;
import controller.Response;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

///// Flawless apps. No bugs
// Anything that can go wrong will, junit book author(s)
public class TestDefaultController {
    private DefaultController controller;
    Request request;
    RequestHandler handler;


    @Before
    public void instantiate() throws Exception {
        // domain object
        controller = new DefaultController();
        request = new SampleRequest();
        handler = new SampleHandler();
        controller.addHandler(request, handler);
    }

    // test classes
    private class SampleRequest implements Request {
        private String name;
        public SampleRequest(){
            name = "TEST";
        }
        public SampleRequest(String name) { this.name = name; }

        public String getName() { return name; }


    }

    /**
     * Request handler for normal cases
     */
    private class SampleHandler implements RequestHandler {
        public Response process(Request request) throws Exception {
            return new SampleResponse();
        }
    }

    /**
     * Request handler for exception cases. Throws an exception
     */
    private class SampleExceptionHandler implements RequestHandler {
        public Response process(Request request) throws Exception {
            // The handler throws an exception when processing a request
            throw new Exception("Error processing request");
        }
    }

    /**
     * Sample response
     */
    private class SampleResponse implements Response {
        private static final String NAME = "TEST";
        public String getName() { return NAME; }
        public boolean equals(Object object) {
            boolean result = false;
            if(object instanceof SampleResponse)
                result = ((SampleResponse) object).getName().equals(getName());
            return result;
        }
        public int hashCode() {
            return NAME.hashCode();
        }
    }

    /**
     * Test addHandler method to see if it correct adds a request handler
     * A domain object is the object under test, in this case the domain object is the controller
     * A test object is any of the objects used to test the application. In this case
     * our test objects include request and handler.
     */
    @Test
    public void testAddHandler() {
        RequestHandler handler2 = controller.getHandler(request);
        assertSame("Handler we set in controller should be same as handler we get",
                handler2, handler);
    }

    @Test
    public void testProcessRequestAnswersErrorResponse() {
        SampleRequest request = new SampleRequest("test error response");
        SampleExceptionHandler handler = new SampleExceptionHandler();
        controller.addHandler(request, handler);

        // Act
        Response response = controller.processRequest(request);

        // Assert
        assertNotNull("Must not return a null response", response);
        assertEquals(ErrorResponse.class, response.getClass());
    }

    @Test
    public void testProcessRequest() {
        Response response = controller.processRequest(request);
        assertNotNull("Must not return null response", response);
        assertEquals(new SampleResponse(), response);
    }
}
