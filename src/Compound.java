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
public class Compound {
    private String name;
    private String molecularFormula;
    private int CID;
    private double molecularWeight;

    public Compound() {
    }

    //Constructor - CID in
    public Compound(int CID) throws IOException{
        this.CID = CID;
        this.name = getCompoundName(CID);
        this.molecularFormula = getMolecularFormula(CID);
        this.molecularWeight = getMolecularWeight(CID);
    }
    //Constructor - Name in
    public Compound(String name) throws IOException{
        this.name = name;
        this.CID = getCID(name);
        this.molecularFormula = getMolecularFormula(name);
        this.molecularWeight = getMolecularWeight(name);
    }

    public String getName() {
        return name;
    }

    public String getMolecularFormula() {
        return molecularFormula;
    }

    public int getCID() {
        return CID;
    }

    public double getMolecularWeight() {
        return molecularWeight;
    }

    //Returns the CID of the compound with the given name
    private int getCID(String compound) throws IOException {
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
    private String getCompoundName(int cid) throws IOException{
        String string = "https://pubchem.ncbi.nlm.nih.gov/compound/"+cid;
        URL url = new URL(string);
        URLreader urLreader = new URLreader(url);

        int name = urLreader.getHTML().indexOf("<meta name=\"description\" content=\"");
        int nameEnd = urLreader.getHTML().indexOf(" |",name+34);
        String stringName = urLreader.getHTML().substring(name+34,nameEnd).trim();
        this.name = stringName;
        return this.name;
    }

    //Returns the molecular formula with the given name
    private String getMolecularFormula(String name) throws IOException{
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
    private String getMolecularFormula(int cid) throws IOException{
        String string = "https://pubchem.ncbi.nlm.nih.gov/compound/"+cid;
        URL url = new URL(string);
        URLreader urLreader = new URLreader(url);

        int formula = urLreader.getHTML().indexOf("|");
        int formulaEnd = urLreader.getHTML().indexOf(" ",formula+2);
        String molecularFormula = urLreader.getHTML().substring(formula+2,formulaEnd).trim();
        this.molecularFormula = molecularFormula;
        return this.molecularFormula;
    }

    private double getMolecularWeight(int cid) throws IOException{
        String string = "https://pubchem.ncbi.nlm.nih.gov/rest/pug_view/data/compound/"+cid+"/XML/?response_type=display";
        URL url = new URL(string);
        URLreader urLreader = new URLreader(url);
        System.out.println(urLreader.getHTML());

        int number = urLreader.getHTML().indexOf("Molecular Weight");
        int number2 = urLreader.getHTML().indexOf("<NumValue>",number);
        int number3 = urLreader.getHTML().indexOf("</NumValue>",number);
        String molecularWeight = urLreader.getHTML().substring(number2+10,number3).trim();
        this.molecularWeight = Double.parseDouble(molecularWeight);
        return this.molecularWeight;
    }

    private double getMolecularWeight(String name) throws IOException{
        int cid = getCID(name);
        String string = "https://pubchem.ncbi.nlm.nih.gov/rest/pug_view/data/compound/"+cid+"/XML/?response_type=display";

        URL url = new URL(string);
        URLreader urLreader = new URLreader(url);
        System.out.println(urLreader.getHTML());

        int number = urLreader.getHTML().indexOf("Molecular Weight");
        int number2 = urLreader.getHTML().indexOf("<NumValue>",number);
        int number3 = urLreader.getHTML().indexOf("</NumValue>",number);
        String molecularWeight = urLreader.getHTML().substring(number2+10,number3).trim();
        this.molecularWeight = Double.parseDouble(molecularWeight);
        return this.molecularWeight;
    }

    public String toString() {
        return name + "\n" + CID + "\n"+ molecularFormula + "\n" + molecularWeight;
    }

    //tester
    public static void main(String[] args) throws IOException {
        Compound test = new Compound(280);
        System.out.println(test);
    }

}
