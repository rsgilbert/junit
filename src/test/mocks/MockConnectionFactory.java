package mocks;

import java.io.InputStream;

/**
 * A mock for connection factory. 
 */
public class MockConnectionFactory implements ConnectionFactory {
    private InputStream inputStream;

    public void setData(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public InputStream getData() throws Exception {
        return this.inputStream;
    }
}
