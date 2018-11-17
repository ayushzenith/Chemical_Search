import javax.swing.*;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 *
 * @project: Chemical_Search
 * @package: PACKAGE_NAME
 * @author: ayushzenith
 * @date: 11/15/18
 * @time: 6:12 PM
 */
public class Interface {
    public static void main(String[] args) throws IOException {
        //Intro Card
        JOptionPane.showMessageDialog(null, "Welcome to the Molecular Compound Dictionary using PUBCHEM","Molecular Compound Dictionary",JOptionPane.PLAIN_MESSAGE);
        //Looped Prompt
        while (true){
            //Prompts for Compound Name or CID
            String input = (String) JOptionPane.showInputDialog(null, "Enter Compound Name or CID Number:", "Molecular Compound Dictionary", JOptionPane.PLAIN_MESSAGE, null, null, "2,2,4-Trimethylpentane");
            //Checks for exit conditions and responds
            if (input==null||input.equals("Exit")||input.equals("exit")){
                JOptionPane.showMessageDialog(null, "Thank you for using the Molecular Compound Dictionary","Shutting Down...",JOptionPane.PLAIN_MESSAGE);
                //This break will end the whole program by exiting the while loop
                break;
            }
            //Runs CID first and then Compound Name. Then outputs the information
            try {
                int CID = Integer.parseInt(input);
                Compound compound = new Compound(CID);
                JOptionPane.showMessageDialog(null, compound,"Molecular Compound Dictionary",JOptionPane.PLAIN_MESSAGE);
            } catch (Exception e) {
                Compound compound = new Compound(input);
                JOptionPane.showMessageDialog(null, compound,"Molecular Compound Dictionary",JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
}
