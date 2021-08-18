import java.util.*;

/**
 * 
 * @author ryanjt5
 * @version 3-23-20
 */
public class FlyWeight extends Node {

    /**
     * creates a flyweight, which holds no data
     */
    public FlyWeight() {
        // INTENTIONALLY LEFT EMPTY
    }


    /**
     * prints E, for empty, buffered by spaces determined by its level in the
     * tree
     * 
     * @param numSpaces
     *            number of buffer spaces needed during printing
     * @return the string "E" preceded by the appropriate amount of spaces
     */
    @Override
    public String print(int numSpaces) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numSpaces; i++) { // pad with spaces
            sb.append("  ");
        }
        sb.append("E");

        return sb.toString();
    }


    /**
     * inserts the given sequence into the tree
     * 
     * @param node
     *            where the recursion starts from
     * @param in
     *            sequence being inserted
     * @param count
     *            to keep track of the level
     * @return returns the node this should be after insertion
     */
    @Override
    public Node insert(Node node, String in, int count) {
        node = new LeafNode(in, count, this);
        System.out.println("sequence " + in + " inserted at level " + count);
        return node;
    }


    /**
     * searches for a given sequence
     * reaching a flyWeight means the sequence isn't present
     * 
     * @param node
     *            where the recursion starts from
     * @param in
     *            sequence being searched for
     * @param count
     *            to keep track of how many nodes visited
     */
    @Override
    public String search(Node node, String in, int count) {
        return (count + 1) + " f";
    }


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
    @Override
    public Node regionSearch(Node node, String in, int count) {
        return this;
    }


    /**
     * this node should add nothing to the regionsearch since it has no sequence
     * 
     * @param node
     *            where the recursion starts from
     * @param stack
     *            stack with all the nodes with given prefix
     * @return a string with just the number of nodes visited
     */
    @Override
    public Stack<Node> regionPrint(Node node, Stack<Node> stack) {
        stack.push(this);
        return stack;
    }


    /**
     * this node will have no bearing on a remove
     * 
     * @param node
     *            where the recursion starts from
     * @param in
     *            sequence being searched for
     * @param count
     *            to keep track of how many nodes visited
     * @return a string with just the number of nodes visited
     */
    @Override
    public Node remove(Node node, String in, int count) {
        return this;
    }


    /**
     * prints E, for empty, buffered by spaces determined by its level in the
     * tree
     * 
     * @param numSpaces
     *            number of buffer spaces needed during printing
     * @return the string "E" preceded by the appropriate amount of spaces
     */
    @Override
    public String printLength(int numSpaces) {
        return this.print(numSpaces);
    }


    /**
     * 
     * prints E, for empty, buffered by spaces determined by its level in the
     * tree
     * 
     * @param numSpaces
     *            number of buffer spaces needed during printing
     * @return the string "E" preceded by the appropriate amount of spaces
     */
    @Override
    public String printStats(int numSpaces) {
        return this.print(numSpaces);
    }

}
