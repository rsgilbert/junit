package stub;

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
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            InputStream is = connection.getInputStream();
            byte[] buffer = new byte[2048];
            int count;
            // When is.read returns -1, it means no byte was read, that is, the stream is at its end
            while(-1 != (count = is.read(buffer))) {
                content.append(new String(buffer, 0, count));
            }
        }catch (IOException e) {
            // For testing purposes, returning null rather than throwing an exception is fine
            return null;
        }
        return content.toString();


    }
}
