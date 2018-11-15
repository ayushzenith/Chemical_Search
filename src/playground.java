/**
 * Created by IntelliJ IDEA.
 *
 * @project: Chemical_Search
 * @package: PACKAGE_NAME
 * @author: ayushzenith
 * @date: 11/14/18
 * @time: 2:35 PM
 */
import java.io.*;
import java.net.URL;

import javax.swing.text.html.parser.DTD;

public class playground {

    public static void main(String[] args) throws IOException {
        URL URL = new URL("https://pubchem.ncbi.nlm.nih.gov/rest/pug_view/data/compound/280/XML/?response_type=display");

        String inputLine;
        String HTML = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(URL.openStream()));

        while ((inputLine = in.readLine()) != null){
            HTML=HTML+inputLine+"\n";
            System.out.println(inputLine);
        }
        in.close();

        PrintWriter out = new PrintWriter("filename.txt");
        out.println(HTML);
        out.close();
    }

}
