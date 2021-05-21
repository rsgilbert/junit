package controller;

public class ErrorResponse implements Response {
    private Request originalRequest;
    private Exception originalException;
    private static final String NAME = "ERROR_RESPONSE";

    public ErrorResponse(Request request, Exception exception) {
        this.originalRequest = request;
        this.originalException = exception;
    }

    public String getName() { return NAME; }
    public boolean equals(Object object) {
        boolean result = false;
        if(object instanceof ErrorResponse)
            result = ((ErrorResponse) object).getName().equals(getName());
        return result;
    }
    public int hashCode() {
        return NAME.hashCode();
    }
    public Request getOriginalRequest() {
        return originalRequest;
    }
    public Exception getOriginalException() {
        return originalException;
    }
}
