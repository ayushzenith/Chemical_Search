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
    private double molecularWeight;
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

    //Returns the compound name with the given CID
    public String getCompoundName(int cid) throws IOException{
        String string = "https://pubchem.ncbi.nlm.nih.gov/compound/"+cid;
        URL url = new URL(string);
        URLreader urLreader = new URLreader(url);

        int name = urLreader.getHTML().indexOf("<meta name=\"description\" content=\"");
        int nameEnd = urLreader.getHTML().indexOf(" |",name+34);
        String stringName = urLreader.getHTML().substring(name+34,nameEnd).trim();
        this.compoundName = stringName;
        return this.compoundName;
    }

    //Returns the compound name with the given formula
    public String getCompoundName(String molecularFormula) throws IOException{
        String string = "https://pubchem.ncbi.nlm.nih.gov/compound/"+molecularFormula;
        URL url = new URL(string);
        URLreader urLreader = new URLreader(url);

        int name = urLreader.getHTML().indexOf("<meta name=\"description\" content=\"");
        int nameEnd = urLreader.getHTML().indexOf(" |",name+34);
        String stringName = urLreader.getHTML().substring(name+34,nameEnd).trim();
        this.compoundName = stringName;
        return this.compoundName;
    }

    //Returns the molecular formula with the given name
    public String getMolecularFormula(String name) throws IOException{
        String string = "https://pubchem.ncbi.nlm.nih.gov/compound/"+name;
        URL url = new URL(string);
        URLreader urLreader = new URLreader(url);

        int formula = urLreader.getHTML().indexOf("|");
        int formulaEnd = urLreader.getHTML().indexOf(" ",formula+2);
        String molecularFormula = urLreader.getHTML().substring(formula+2,formulaEnd).trim();
        this.molecularFormula = molecularFormula;
        return this.molecularFormula;
    }

    //Returns the molecular formula with the given CID
    public String getMolecularFormula(int cid) throws IOException{
        String string = "https://pubchem.ncbi.nlm.nih.gov/compound/"+cid;
        URL url = new URL(string);
        URLreader urLreader = new URLreader(url);

        int formula = urLreader.getHTML().indexOf("|");
        int formulaEnd = urLreader.getHTML().indexOf(" ",formula+2);
        String molecularFormula = urLreader.getHTML().substring(formula+2,formulaEnd).trim();
        this.molecularFormula = molecularFormula;
        return this.molecularFormula;
    }

    //tester
    public static void main(String[] args) throws IOException {
        StringLogic test = new StringLogic();
        System.out.println(test.getCompoundName(280));
        System.out.println(test.getCompoundName("C10H14N2"));
        System.out.println(test.getMolecularFormula("356"));
    }

}
