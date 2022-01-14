import java.util.Arrays;

/**
 * Main class for A3
 * @author Smaranjeet Singh
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

             String file;
             String test[] = {};
             GUI gui = null;

             if (args.length == 0) {
                 System.out.println("************************************************");
                 System.out.println("******No file name used, creating new file******");
                 System.out.println("************************************************");
                 file = "my_Investments.txt";
                 if (Arrays.equals(args, test)) {
                     gui = new GUI(file);

                 }
             } else {
                 gui = new GUI(args[0]);

             }

             gui.setVisible(true);
    }

}
