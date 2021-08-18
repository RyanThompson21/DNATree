import java.io.*;
import java.util.Scanner;

/**
 * @author ryanjt5
 * @version 3/31/2020
 */
public class Parser {

    private File file;
    private Scanner fscan;
    private Scanner lscan;
    private Tree tree;


    /**
     * creates a new parser with given file
     * 
     * @param fileName
     *            file
     * @throws FileNotFoundException
     *             yup
     */
    public Parser(String fileName) throws FileNotFoundException {
        file = new File(fileName);
        fscan = new Scanner(file);
        tree = new Tree();
    }


    /**
     * parses a given line
     * 
     * @param line
     *            line to be parsed
     */
    public void parseLine(String line) {
        lscan = new Scanner(line);
        String[] lineContents = new String[10];
        int counter = 0;

        while (lscan.hasNext()) {
            lineContents[counter] = lscan.next().trim();
            counter++;
        }

        String command = lineContents[0];

        if (command.equalsIgnoreCase("insert")) {
            // "sequence" is the sequence to be inserted
            String sequence = lineContents[1];

            // searchResult is the number of nodes visited to find sequence, includes
            // t or f at the end
            String searchResult = tree.search(sequence);

            // res will either be "t" or "f"
            String res = searchResult.substring(searchResult.length() - 1);

            // check validity of sequence first
            if (!isValid(sequence, command)) {
                System.out.println("sequence " + sequence + " is invalid");
            }
            // if the sequence was found then it is a duplicate and not inserted
            else if (res.equalsIgnoreCase("t")) {
                System.out.println("sequence " + sequence + " already exists");
            }
            else {
                tree.insert(sequence);
            }

        }
        else if (command.equalsIgnoreCase("remove")) {
            String sequence = lineContents[1];
            if (!isValid(sequence, command)) {
                System.out.println("sequence " + sequence + " is invalid");
            }
            else {
                String res = tree.remove(sequence);
                System.out.println(res);
            }
        }
        else if (command.equalsIgnoreCase("print")) {
            // just the "print" command
            if (counter == 1) {
                System.out.println("tree dump:");
                String res = tree.dump(tree.getRoot(), 0);
                System.out.println(res);
            }
            // either print stats or print lengths commands
            else {
                if (lineContents[1].equalsIgnoreCase("stats")) {
                    System.out.println("tree dump:");
                    String res = tree.printStats(tree.getRoot(), 0);
                    System.out.println(res);
                }
                else {
                    System.out.println("tree dump:");
                    String res = tree.printLength(tree.getRoot(), 0);
                    System.out.println(res);
                }
            }
        }
        if (command.equalsIgnoreCase("search")) {
            String seq = lineContents[1];
            if (!isValid(seq, command)) {
                System.out.println("sequence " + seq + " is invalid");
            }
            else {
                // exact match
                if (seq.substring(seq.length() - 1).equalsIgnoreCase("$")) {
                    String res = tree.search(seq.substring(0, seq.length()
                        - 1));
                    String[] temp = res.split(" ");
                    if (temp[1].equalsIgnoreCase("t")) {
                        System.out.println("# of nodes visited: " + temp[0]);
                        System.out.println("sequence: " + seq.substring(0, seq
                            .length() - 1));
                    }
                    else {
                        System.out.println("# of nodes visited: " + temp[0]);
                        System.out.println("no sequence found");
                    }

                }
                else {
                    String res = tree.regionPrint(seq);
                    System.out.println(res);
                }
            }
        }
        lscan.close();
    }


    /**
     * parses the file
     */
    public void parse() {
        while (fscan.hasNextLine()) {
            String wholeLine = fscan.nextLine().trim();
            if (!wholeLine.isEmpty()) {
                parseLine(wholeLine);
            }
        }
        fscan.close();
        file = null;
        lscan = null;
    }


    /**
     * checks if a sequence is valid based on its command
     * 
     * @param seq
     *            sequence being checked
     * @param com
     *            the command it is issued with
     * @return true if sequence is valid false otherwise
     */
    public boolean isValid(String seq, String com) {
        char[] temp = seq.toCharArray();
        if (com.contains("search")) { // only time $ is acceptable
            for (char x : temp) {
                if (x != 'A' && x != 'C' && x != 'G' && x != 'T' && x != '$') {
                    return false;
                }
            }
        }
        else { // $ not valid input
            for (char x : temp) {
                if (x != 'A' && x != 'C' && x != 'G' && x != 'T') {
                    return false;
                }
            }
        }
        return true;
    }
}
