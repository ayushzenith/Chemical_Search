import java.net.*;
import java.io.*;

public class URLreader {
    private URL URL;
    private String HTML;
    public URLreader(URL url) throws IOException {
        this.URL=url;
    }
    public String getHTML() throws IOException {
        String inputLine;
        BufferedReader in = new BufferedReader(new InputStreamReader(this.URL.openStream()));

        while ((inputLine = in.readLine()) != null){
            HTML=HTML+inputLine;
        }
        in.close();
        return HTML;
    }

    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.google.com/");
        URLreader test = new URLreader(url);
        System.out.println(test.getHTML());

    }
}