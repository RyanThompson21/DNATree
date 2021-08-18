import java.util.*;

/**
 * @author ryanjt5
 * @version 3-23-20
 */
public abstract class Node {

    private int level;


    /**
     * prints a string representation of the node
     * 
     * @param numSpaces
     *            used to determine how many spaces are needed when printing
     *            based on the node's level
     * @return a string representation of the node
     */
    public abstract String print(int numSpaces);


    /**
     * is a small variation on print above
     * just prints the length of sequences right next to them
     * 
     * @param numSpaces
     *            counter to determine how many spaces must be printed before
     *            node
     * @return a string decomp of the tree
     */
    public abstract String printLength(int numSpaces);


    /**
     * is a small variation on print above
     * just prints the % of all letters in the sequences right next to them
     * 
     * @param numSpaces
     *            counter to determine how many spaces must be printed before
     *            node
     * @return a string decomp of the tree
     */
    public abstract String printStats(int numSpaces);


    /**
     * inserts the given sequence into the tree
     * 
     * @param node
     *            where the search recursion starts from
     * @param in
     *            sequence being inserted
     * @param count
     *            to keep track of the level
     * @return the node THIS should be after insertion
     */
    public abstract Node insert(Node node, String in, int count);


    /**
     * this is exact search
     * 
     * @param node
     *            node at current state of recursion
     * @param in
     *            sequence being searched for
     * @param count
     *            count of how many nodes are visited
     * @return a string of how many nodes were visited and the sequence that was
     *         returned
     */
    public abstract String search(Node node, String in, int count);


    /**
     * if you've hit a flyweight during region search, then there are no
     * sequences
     * with the given sequence as a prefix
     * 
     * @param node
     *            where the recursion starts from
     * @param in
     *            sequence being searched for
     * @param count
     *            to keep track of how many nodes visited
     * @return a string with just the number of nodes visited
     */
    public abstract Node regionSearch(Node node, String in, int count);


    /**
     * starts from a given node and puts all the leaves in a stack
     * 
     * @param node
     *            where the recursion starts from
     * @param stack
     *            stack with all the nodes with given prefix
     * @return a string with just the number of nodes visited
     */
    public abstract Stack<Node> regionPrint(Node node, Stack<Node> stack);


    /**
     * removes a given sequence from the tree
     * 
     * @param node
     *            where the recursion starts from
     * @param in
     *            sequence being searched for
     * @param count
     *            to keep track of how many nodes visited
     * @return the node THIS node should be after removal
     */
    public abstract Node remove(Node node, String in, int count);


    /**
     * setter for level
     * 
     * @param lvl
     *            the level the node is on, determined during insert
     */
    public void setLevel(int lvl) {
        level = lvl;
    }


    /**
     * getter for the level of the node
     * 
     * @return int of level
     */
    public int getLevel() {
        return level;
    }

}
