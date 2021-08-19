import java.io.FileNotFoundException;

/**
 * @author ryanjt5
 * @version 3-23-20
 *
 */
public class DNAtree {

    /**
     * Main method that runs the parser on a given input file
     * 
     * @param args
     *            first item in array is the input file
     * @throws FileNotFoundException
     */
    
    public static void main(String[] args) throws FileNotFoundException {
        Parser p = new Parser(args[0]);
        p.parse();
    }
}
