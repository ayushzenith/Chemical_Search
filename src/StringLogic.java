import java.net.*;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @project: Chemical_Search
 * @package: PACKAGE_NAME
 * @author: ayushzenith
 * @date: 11/10/18
 * @time: 6:03 PM
 */
public class StringLogic {
    private String elementName;
    private String molecularFormula;
    private String molecularWeight;
    private int CID;

    public StringLogic() {
    }
    public int getCID(int cid) throws IOException {
        String string = "https://pubchem.ncbi.nlm.nih.gov/compound/"+cid;
        URL url = new URL(string);
        URLreader urLreader = new URLreader(url);

        int cidPlacement = urLreader.getHTML().indexOf("CID");
        int cidPlacementEnd = urLreader.getHTML().indexOf(" ",cidPlacement+4);
        String stringCid = urLreader.getHTML().substring(cidPlacement+3,cidPlacementEnd).trim();
        this.CID = Integer.parseInt(stringCid);
        return CID;
    }
    public int getCID(String elementName) throws IOException {
        if (elementName.indexOf(' ')!=-1){
            elementName=elementName.replaceAll(" ", "_");
        }

        String string = "https://pubchem.ncbi.nlm.nih.gov/compound/"+elementName;
        URL url = new URL(string);
        URLreader urLreader = new URLreader(url);

        int cidPlacement = urLreader.getHTML().indexOf("CID");
        int cidPlacementEnd = urLreader.getHTML().indexOf(" ",cidPlacement+4);
        String stringCid = urLreader.getHTML().substring(cidPlacement+3,cidPlacementEnd).trim();
        this.CID = Integer.parseInt(stringCid);
        return CID;
    }
    //tester
    public static void main(String[] args) throws IOException {
        StringLogic test = new StringLogic();
        System.out.println(test.getCID("2,2,4-Trimethylpentane"));
    }

}
