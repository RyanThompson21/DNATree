import java.util.*;

/**
 * @author ryanjt5
 * @version 3-23-20
 *
 */
public class Tree {
    private Node root;
    private Node fly = new FlyWeight();


    /**
     * creates a new tree with the root set as a flyweight
     */
    public Tree() {
        root = fly;
    }


    /**
     * getter for root of the tree
     * 
     * @return the tree's root
     */
    public Node getRoot() {
        return root;
    }


    /**
     * getter for the flyweight of the tree
     * 
     * @return tree's flyweight
     */
    public Node getFly() {
        return fly;
    }


    /**
     * generates a pre-order string representation of the tree
     * 
     * @param node
     *            node to start the process
     * @param count
     *            keeps track of how many spaces required to precede string
     * @return the string representation
     */
    public String dump(Node node, int count) {
        String ret = (node.print(count)); // 'root' first
        if (node instanceof InternalNode) {
            ret = ret + "\n" + dump(((InternalNode)node).getA(), count + 1);
            ret = ret + "\n" + dump(((InternalNode)node).getC(), count + 1);
            ret = ret + "\n" + dump(((InternalNode)node).getG(), count + 1);
            ret = ret + "\n" + dump(((InternalNode)node).getT(), count + 1);
            ret = ret + "\n" + dump(((InternalNode)node).getD(), count + 1);
        }
        return ret;
    }


    /**
     * slight difference from print
     * prints the lengths of each sequence as well
     * 
     * @param node
     *            node recursion is currently on
     * @param count
     *            keep track of how many spaces
     * @return a string decomp of the tree with lengths of each sequence
     */
    public String printLength(Node node, int count) {
        String ret = (node.printLength(count)); // 'root' first
        if (node instanceof InternalNode) {
            ret = ret + "\n" + printLength(((InternalNode)node).getA(), count
                + 1);
            ret = ret + "\n" + printLength(((InternalNode)node).getC(), count
                + 1);
            ret = ret + "\n" + printLength(((InternalNode)node).getG(), count
                + 1);
            ret = ret + "\n" + printLength(((InternalNode)node).getT(), count
                + 1);
            ret = ret + "\n" + printLength(((InternalNode)node).getD(), count
                + 1);
        }
        return ret;
    }


    /**
     * slight difference from print
     * prints the % of each letter in the sequence also
     * 
     * @param node
     *            node recursion is currently on
     * @param count
     *            keep track of how many spaces
     * @return a string decomp of the tree with lengths of each sequence
     */
    public String printStats(Node node, int count) {
        String ret = (node.printStats(count)); // 'root' first
        if (node instanceof InternalNode) {
            ret = ret + "\n" + printStats(((InternalNode)node).getA(), count
                + 1);
            ret = ret + "\n" + printStats(((InternalNode)node).getC(), count
                + 1);
            ret = ret + "\n" + printStats(((InternalNode)node).getG(), count
                + 1);
            ret = ret + "\n" + printStats(((InternalNode)node).getT(), count
                + 1);
            ret = ret + "\n" + printStats(((InternalNode)node).getD(), count
                + 1);
        }
        return ret;
    }


    /**
     * starts the process of inserting a new sequence into the tree
     * 
     * @param in
     *            sequence in question
     */
    public void insert(String in) {
        root = root.insert(root, in, 0);
    }


    /**
     * Handles exact search
     * 
     * @param in
     *            the sequence being searched for
     * @return a string containing the number of nodes visited
     *         during the search and a t (true) or f (false) depending on if it was found or
     *         not
     */
    public String search(String in) {
        return root.search(root, in, 0);
    }


    /**
     * this is the first half of regionSearch
     * this method returns the root of the subtree where all the leaves have the
     * given sequence as a prefix
     * 
     * @param in
     *            sequence in question
     * @return root of subtree
     */
    public Node regionSearch(String in) {
        Node boi = root.regionSearch(root, in, 0);
        return boi;
    }


    /**
     * second half of region search
     * this starts at the root of the method above and gathers all the leaves
     * 
     * @param in
     *            desired prefix
     * @return a string with all the sequences that have in as a prefix
     */
    public String regionPrint(String in) {
        Node start = this.regionSearch(in);
        int count = start.getLevel();
        if (start instanceof InternalNode) {
            Stack<Node> stack = new Stack<Node>();
            stack = ((InternalNode)start).regionPrint(start, stack);
            int nodes = stack.size() + count;
            String ret = "# of nodes visited: " + nodes;
            while (!stack.isEmpty()) {
                Node node = stack.pop();
                if (node instanceof LeafNode) {
                    ret += "\nsequence: " + ((LeafNode)node).getSeq();
                }
            }
            return ret;
        }
        else if (start instanceof LeafNode) {
            String print = "# of nodes visited: " + (count + 1) + "\n";
            String rootSeq = ((LeafNode)start).getSeq();
            if (rootSeq.contains(in)) { // found a match
                print += "sequence: " + rootSeq;
            }
            else { // not a match
                print += "no sequence found";
            }
            return print;
        }
        else { // flyweight means sequence DNE in the tree
            String help = this.search(in);
            help = help.substring(0, help.length() - 2);
            count = Integer.parseInt(help);
            return "# of nodes visited: " + (count) + "\n"
                + "no sequence found";
        }
    }


    /**
     * starts the removal process of a given string
     * 
     * @param in
     *            string to be removed
     * @return a string depending on whether the removal was succesful or not
     */
    public String remove(String in) {
        // check to see if sequence is in the tree
        String ser = this.search(in);
        if (ser.contains("f")) { // sequence isn't in the tree
            return "sequence " + in + " does not exist";
        }
        else {
            root = root.remove(root, in, 0);
            return "sequence " + in + " removed";
        }
    }

}
