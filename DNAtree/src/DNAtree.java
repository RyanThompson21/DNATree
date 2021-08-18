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

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

