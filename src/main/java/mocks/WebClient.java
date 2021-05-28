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


    // Method for opening an HTTP connecting and reading the content there
    public String getContent(URL url) {
        StringBuffer content = new StringBuffer();

        try {
            HttpURLConnection connection = createHttpURLConnection(url);
            connection.setDoInput(true);
            InputStream is = connection.getInputStream();
            int count;
            // Read all contents
            // While body implemented differently from the WebClient in stub package
            while(-1 != (count = is.read())) {
                content.append(new String(Character.toChars(count)));
            }
        } catch (IOException e) {
            // For testing purposes, returning null rather than throwing an exception is fine
            return null;
        }
        return content.toString();


    }

    protected HttpURLConnection createHttpURLConnection(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }
}
