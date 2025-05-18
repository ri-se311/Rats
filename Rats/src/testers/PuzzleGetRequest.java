package testers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class is responsible solely for getting the first line of text from a web page.
 *
 * @version 1.2
 */
public class PuzzleGetRequest {

    /**
     * This method grabs the first string on any webpage.
     *
     * @param URL This is the URL destination of the page to grab the text from.
     * @return Returns the first line of a website.
     * @throws Exception This exception is thrown if the webpage cannot be found.
     */
    public String getStringFromSite(String URL) throws Exception {
        try {
            URL destinationUrl = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection) destinationUrl.openConnection();
            /*
             * Set the request to get information rather than post to the URL.
             * Then use a buffered reader to read only one line from the input stream.
             */
            connection.setRequestMethod("GET");
            InputStreamReader read = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferRead = new BufferedReader(read);
            return bufferRead.readLine();
        } catch (Exception e) {
            throw new Exception("Connection failed.");
        }
    }
}


