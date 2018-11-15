import java.net.*;
import java.io.*;

public class URLreader {
    private URL URL;
    private String HTML;

    public URLreader(URL url) throws IOException {
        this.URL=url;
    }

    public java.net.URL getURL() {
        return URL;
    }

    public void setURL(java.net.URL URL) {
        this.URL = URL;
    }

    public String getHTML() throws IOException {
        String inputLine;
        BufferedReader in = new BufferedReader(new InputStreamReader(this.URL.openStream()));

        while ((inputLine = in.readLine()) != null){
            HTML=HTML+inputLine+"\n";
            System.out.println(inputLine);
        }
        in.close();
        return HTML;
    }

    //tester
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://pubchem.ncbi.nlm.nih.gov/rest/pug_view/data/compound/280/XML/?response_type=display");
        URLreader test = new URLreader(url);
        System.out.println(test.getHTML());

    }
}