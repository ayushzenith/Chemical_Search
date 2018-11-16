import java.net.*;
import java.io.*;

public class URLreader {
    private URL URL;
    private String HTML;

    //Constructor
    public URLreader(URL url) throws IOException {
        this.URL=url;
    }

    //Getter and Setter for URL
    public java.net.URL getURL() {
        return URL;
    }
    public void setURL(java.net.URL URL) {
        this.URL = URL;
    }

    //Reads the HTML file and outputs it in one string
    public String getHTML() throws IOException {
        String inputLine;
        BufferedReader in = new BufferedReader(new InputStreamReader(this.URL.openStream()));
        while ((inputLine = in.readLine()) != null){
            HTML=HTML+inputLine+"\n";
        }
        in.close();
        return HTML;
    }
}