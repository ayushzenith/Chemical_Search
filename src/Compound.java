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

    //Constructor - CID in
    public Compound(int CID) throws IOException{
        this.CID = CID;
        this.name = getCompoundName(CID);
        this.molecularFormula = getMolecularFormula(CID);
        this.molecularWeight = getMolecularWeight(CID);
    }
    //Constructor - Name in
    public Compound(String name) throws IOException{
        this.CID = getCID(name);
        this.name = getCompoundName(getCID(name));
        this.molecularFormula = getMolecularFormula(name);
        this.molecularWeight = getMolecularWeight(name);
    }

    //Getters
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
        //Checks and removes any spaces from the inputted name
        if (compound.indexOf(' ')!=-1){
            compound=compound.replaceAll(" ", "_");
        }

        //Creates the URL with the inputted compound name
        String string = "https://pubchem.ncbi.nlm.nih.gov/compound/"+compound;
        URL url = new URL(string);
        URLreader urLreader = new URLreader(url);

        //Searches for the mention of CID and records the index number
        int cidPlacement = urLreader.getHTML().indexOf("CID");
        //Searches for the first space after the CID mention
        int cidPlacementEnd = urLreader.getHTML().indexOf(" ",cidPlacement+4);
        //Substrings the CID number using the CID index and the space index
        String stringCid = urLreader.getHTML().substring(cidPlacement+3,cidPlacementEnd).trim();
        //Converts CID from a String to an int and returns it
        this.CID = Integer.parseInt(stringCid);
        return CID;
    }

    //Returns the compound name with the given CID
    private String getCompoundName(int cid) throws IOException{
        //Creates URL using the CID number
        String string = "https://pubchem.ncbi.nlm.nih.gov/compound/"+cid;
        URL url = new URL(string);
        URLreader urLreader = new URLreader(url);

        //Searches the HTML for the section of code directly in front of the location of the compound name
        int name = urLreader.getHTML().indexOf("<meta name=\"description\" content=\"");
        //Searches for the character directly after the compound name in the HTML code
        int nameEnd = urLreader.getHTML().indexOf(" |",name+34);
        //Substrings the Compound Name using the indexes received above and returns it
        String stringName = urLreader.getHTML().substring(name+34,nameEnd).trim();
        this.name = stringName;
        return this.name;
    }

    //Getting the Molecular Formula
    //Returns the molecular formula with the given name
    private String getMolecularFormula(String name) throws IOException{
        //Checks and removes any spaces from the inputted name
        if (name.indexOf(' ')!=-1){
            name=name.replaceAll(" ", "_");
        }

        //Creates URL from the modified compound name
        String string = "https://pubchem.ncbi.nlm.nih.gov/compound/"+name;
        URL url = new URL(string);
        URLreader urLreader = new URLreader(url);

        //Finds indexes of the character before and the character after the chemical formula and uses those to substring
        int formula = urLreader.getHTML().indexOf("|");
        int formulaEnd = urLreader.getHTML().indexOf(" ",formula+2);
        String molecularFormula = urLreader.getHTML().substring(formula+2,formulaEnd).trim();

        //Checks for numbers and reformats them as subscript
        for (int x = molecularFormula.length(); x>0; x--){
            String y = Character.toString(molecularFormula.charAt(x-1));
            if (y.equals("1")){
                molecularFormula = molecularFormula.replaceAll("1", "₁");
            }else if (y.equals("2")){
                molecularFormula = molecularFormula.replaceAll("2", "₂");
            }else if (y.equals("3")){
                molecularFormula = molecularFormula.replaceAll("3", "₃");
            }else if (y.equals("4")){
                molecularFormula = molecularFormula.replaceAll("4", "₄");
            }else if (y.equals("5")){
                molecularFormula = molecularFormula.replaceAll("5", "₅");
            }else if (y.equals("6")){
                molecularFormula = molecularFormula.replaceAll("6", "₆");
            }else if (y.equals("7")){
                molecularFormula = molecularFormula.replaceAll("7", "₇");
            }else if (y.equals("8")){
                molecularFormula = molecularFormula.replaceAll("8", "₈");
            }else if (y.equals("9")){
                molecularFormula = molecularFormula.replaceAll("9", "₉");
            }else if (y.equals("0")){
                molecularFormula = molecularFormula.replaceAll("0", "₀");
            }
        }

        //Returns the modified final molecular formula
        this.molecularFormula = molecularFormula;
        return this.molecularFormula;
    }
    //Returns the molecular formula with the given CID
    private String getMolecularFormula(int cid) throws IOException{
        //Creates URL using the CID
        String string = "https://pubchem.ncbi.nlm.nih.gov/compound/"+cid;
        URL url = new URL(string);
        URLreader urLreader = new URLreader(url);

        //Finds the index of the characters before and after the molecualr formula and substrings the molecular formula
        int formula = urLreader.getHTML().indexOf("|");
        int formulaEnd = urLreader.getHTML().indexOf(" ",formula+2);
        String molecularFormula = urLreader.getHTML().substring(formula+2,formulaEnd).trim();

        //Checks and reformats numbers to subscript
        for (int x = molecularFormula.length(); x>0; x--){
            String y = Character.toString(molecularFormula.charAt(x-1));
            if (y.equals("1")){
                molecularFormula = molecularFormula.replaceAll("1", "₁");
            }else if (y.equals("2")){
                molecularFormula = molecularFormula.replaceAll("2", "₂");
            }else if (y.equals("3")){
                molecularFormula = molecularFormula.replaceAll("3", "₃");
            }else if (y.equals("4")){
                molecularFormula = molecularFormula.replaceAll("4", "₄");
            }else if (y.equals("5")){
                molecularFormula = molecularFormula.replaceAll("5", "₅");
            }else if (y.equals("6")){
                molecularFormula = molecularFormula.replaceAll("6", "₆");
            }else if (y.equals("7")){
                molecularFormula = molecularFormula.replaceAll("7", "₇");
            }else if (y.equals("8")){
                molecularFormula = molecularFormula.replaceAll("8", "₈");
            }else if (y.equals("9")){
                molecularFormula = molecularFormula.replaceAll("9", "₉");
            }else if (y.equals("0")){
                molecularFormula = molecularFormula.replaceAll("0", "₀");
            }
        }
        //Returns the molecular formula
        this.molecularFormula = molecularFormula;
        return this.molecularFormula;
    }

    //Getting the Molecular Weight
    //Returns the Molecular Weight with the given CID
    private double getMolecularWeight(int cid) throws IOException{
        //Creates URL with CID
        String string = "https://pubchem.ncbi.nlm.nih.gov/rest/pug_view/data/compound/"+cid+"/XML/?response_type=display";
        URL url = new URL(string);
        URLreader urLreader = new URLreader(url);

        //Finds the indexes of the HTML flags that encompasses the Molecular Weight and substrings the molecular weight
        int number = urLreader.getHTML().indexOf("Molecular Weight");
        int number2 = urLreader.getHTML().indexOf("<NumValue>",number);
        int number3 = urLreader.getHTML().indexOf("</NumValue>",number);
        String molecularWeight = urLreader.getHTML().substring(number2+10,number3).trim();

        //Passes the molecular weight into a double and returns it
        this.molecularWeight = Double.parseDouble(molecularWeight);
        return this.molecularWeight;
    }
    //Returns the Molecular Weight with the given Compound Name
    private double getMolecularWeight(String name) throws IOException{
        //Converts the inputted name to the CID
        int cid = getCID(name);

        //Creates URL with CID
        String string = "https://pubchem.ncbi.nlm.nih.gov/rest/pug_view/data/compound/"+cid+"/XML/?response_type=display";
        URL url = new URL(string);
        URLreader urLreader = new URLreader(url);

        //Finds and indexes the HTML Flags that encompasses the molecular weight and substrings the molecular weight
        int number = urLreader.getHTML().indexOf("Molecular Weight");
        int number2 = urLreader.getHTML().indexOf("<NumValue>",number);
        int number3 = urLreader.getHTML().indexOf("</NumValue>",number);
        String molecularWeight = urLreader.getHTML().substring(number2+10,number3).trim();

        //Passes the molecular weight to a double and returns it
        this.molecularWeight = Double.parseDouble(molecularWeight);
        return this.molecularWeight;
    }

    //toString
    public String toString() {
        return "Compound: " + name + "\n" + "CID#: " + CID + "\n"+ "Molecular Formula: " + molecularFormula + "\n" + "Molecular Weight: " + molecularWeight+" g/mol";
    }
}
