package stub;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class StubHttpURLConnection extends HttpURLConnection {
    private boolean isInput = true;
    protected StubHttpURLConnection(URL url) { super(url); }
    public InputStream getInputStream() throws IOException {
        if(!isInput) {
            throw new ProtocolException(
                    "Can not read from URL Connection if doInput = false (call setDoInput(true)"
            );
        }
        return new ByteArrayInputStream(
                "It works".getBytes()
        );
    }
    public void disconnect() {}
    public void connect() throws IOException {}
    public boolean usingProxy() { return false; }
}
