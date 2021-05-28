package mocks;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Contains a method getContent for opening an HTTP connection to
 * a given URL and reading the content found at that URL
 */
public class WebClient {


    // Uses a connection factory
    // We've made the retrieval of the data content independent of the way we get the connection
    public String getContent(ConnectionFactory connectionFactory) {
        StringBuffer content = new StringBuffer();

        try {
            InputStream is = connectionFactory.getData();
            int count;
            // Read all contents
            // While body implemented differently from the WebClient in stub package
            while(-1 != (count = is.read())) {
                content.append(new String(Character.toChars(count)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            // For testing purposes, returning null rather than throwing an exception is fine
            return null;
        }
        return content.toString();


    }

    protected HttpURLConnection createHttpURLConnection(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }
}
