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
    private String compoundName;
    private String molecularFormula;
    private String molecularWeight;
    private int CID;

    public StringLogic() {
    }

    //Returns the CID of the compound given the CID of the compound
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

    //Returns the CID of the compound with the given name or molecular formula
    public int getCID(String compound) throws IOException {
        if (compound.indexOf(' ')!=-1){
            compound=compound.replaceAll(" ", "_");
        }

        String string = "https://pubchem.ncbi.nlm.nih.gov/compound/"+compound;
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
