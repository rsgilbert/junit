package mocks;

import java.io.IOException;
import java.io.InputStream;

public class MockInputStream extends InputStream {
    private String buffer;
    private int position = 0;
    private int closeCount = 0;

    // Tell mock what the read method return
    public void setBuffer(String buffer) { this.buffer = buffer; }

    public int read() throws IOException {
        if(position == this.buffer.length())
            return -1;
        return this.buffer.charAt(position++);
    }

    public void close() throws IOException {
        // Count number of times close is called
        closeCount++;
        super.close();
    }

    // Verify expectations are met
    // An expectation is a feature built into the mock that verifies
    // whether the external class calling this mock has the correct behaviour
    public void verify() throws AssertionError {
        if(closeCount != 1)
            throw new AssertionError("close() method should be called exactly one time but has been called " + closeCount + " times");
    }


}
