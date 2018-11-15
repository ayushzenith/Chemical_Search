import java.io.IOException;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 *
 * @project: Chemical_Search
 * @package: PACKAGE_NAME
 * @author: ayushzenith
 * @date: 11/15/18
 * @time: 6:12 PM
 */
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("WeLcOmE to cHeMiCaL SeArcH using PUBCHEM");
        Scanner reader = new Scanner(System.in);
        String input = null;
        while (true){
            System.out.println("Please enter Compound Name or cid number or Exit to quit:");
            input = reader.nextLine();
            if (input.equals("Exit")||input.equals("exit")){
                break;
            }
            try {
                int CID = Integer.parseInt(input);
                Compound Cid = new Compound(CID);
                System.out.println(Cid);
            } catch (Exception e) {
                Compound compound = new Compound(input);
                System.out.println(compound);
            }
        }
    }
}
