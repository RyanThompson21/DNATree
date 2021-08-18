import java.util.*;

/**
 * @author ryanjt5
 * @version 3-23-20
 *
 */
public class LeafNode extends Node {

    private String seq;
    private Node fly;


    /**
     * creates a new leafnode with a string sequence, a level determined when
     * inserted, and a flyweight, which is used for creating a new internal
     * node during a 'split
     * 
     * @param in
     *            sequence of new node
     * @param lvl
     *            level new node is on
     * @param fly
     *            the flyweight to be passed
     */
    public LeafNode(String in, int lvl, Node fly) {
        seq = in;
        setLevel(lvl);
        this.fly = fly;
    }


    /**
     * setter for the nodes flyweight, which is used for creating a new internal
     * node
     * during a 'split
     * 
     * @param node
     *            flyweight being passed
     */
    public void setFly(Node node) {
        fly = node;
    }


    /**
     * getter for the nodes sequence
     * 
     * @return a string of the nodes sequence
     */
    public String getSeq() {
        return seq;
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
     */
    @Override
    public Node insert(Node node, String in, int count) {
        String seqa = ((LeafNode)node).getSeq();
        // not a duplicate
        if (!in.equalsIgnoreCase(seqa)) {
            // SPLIT
            int lvl = node.getLevel();
            node = new InternalNode(fly, lvl);
            // reinsert nodes
            Node child = new LeafNode(seqa, count + 1, this.fly);
            if (count >= seqa.length()) {
                ((InternalNode)node).setD(child);
            }
            else {
                if (seqa.charAt(count) == 'A') {
                    ((InternalNode)node).setA(child);
                }
                else if (seqa.charAt(count) == 'C') {
                    ((InternalNode)node).setC(child);
                }
                else if (seqa.charAt(count) == 'G') {
                    ((InternalNode)node).setG(child);
                }
                else { // Must be T
                    ((InternalNode)node).setT(child);
                }
            }
            node.insert(node, in, count);
            return node;
        }
        else {
            return node;
        }
    }


    /**
     * exact search
     * 
     * @param node
     *            where the search recursion starts from
     * @param in
     *            sequence being searched for
     * @param count
     *            to keep track of nodes visited
     */
    @Override
    public String search(Node node, String in, int count) {
        String seqa = ((LeafNode)node).getSeq();
        if (in.equals(seqa)) { // found it
            return (count + 1) + " t";
        }
        else {
            return (count + 1) + " f";
        }
    }


    /**
     * prints this node
     * 
     * @param numSpaces
     *            determines how many indentations are needed
     *            based on node level
     * @return string representation of this node
     */
    public String print(int numSpaces) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numSpaces; i++) { // pad with spaces
            sb.append("  ");
        }
        sb.append(seq);
        return sb.toString();
    }


    /**
     * prints this node with its length as well
     * 
     * @param numSpaces
     *            determines how many indentations are needed
     *            based on node level
     * @return string representation of this node
     */
    @Override
    public String printLength(int numSpaces) {
        String ret = this.print(numSpaces);
        ret = ret + " " + (this.seq.length());
        return ret;
    }


    /**
     * prints this node with the % of each letter also
     * 
     * @param numSpaces
     *            determines how many indentations are needed
     *            based on node level
     * @return string representation of this node
     */
    public String printStats(int numSpaces) {
        String ret = this.print(numSpaces);
        double aa = ((charCount(this.seq, 'A')) / ((double)(this.seq.length())))
            * 100;
        double a = Math.round((aa * 100)) / 100.0;
        String astr = printStatHelp(a);
        double c = Math.round(((charCount(this.seq, 'C')) / (this.seq.length()))
            * 10000) / 100.0;
        String cstr = printStatHelp(c);
        double g = Math.round(((charCount(this.seq, 'G')) / (this.seq.length()))
            * 10000) / 100.0;
        String gstr = printStatHelp(g);
        double t = Math.round(((charCount(this.seq, 'T')) / (this.seq.length()))
            * 10000) / 100.0;
        String tstr = printStatHelp(t);
        ret = ret + " A:" + astr + " C:" + cstr + " G:" + gstr + " T:" + tstr;
        return ret;
    }


    /**
     * Stops x at 2 decimal places
     * 
     * @param x
     *            number to be cut off
     * @return the sliced number
     */
    private static String printStatHelp(double x) {
        String xstr = "" + x;
        if (xstr.length() < 5) {
            for (int i = 0; i < (5 - xstr.length()); i++) {
                xstr += "0";
            }
        }
        else {
            xstr = xstr.substring(0, 5);
        }
        if (x > 99) {
            xstr += "0";
        }
        return xstr;
    }


    /**
     * counts the number of a certain char in a given string
     * 
     * @param in
     *            string counting from
     * @param num
     *            character in question
     * @return number of character num in String in
     */
    private double charCount(String in, char num) {
        double ret = 0;
        for (int i = 0; i < in.length(); i++) {
            Character x = in.charAt(i);
            if (x.equals(num)) {
                ret++;
            }
        }
        return ret;
    }


    /**
     * if you've hit a leaf node during region search then there is only one
     * node with the given sequence as a prefix
     * 
     * @param node
     *            start of search
     * @param in
     *            sequence being searched for
     * @param count
     *            how many nodes the search as visited
     * @return a string containing, first, the number of nodes visited
     *         then the nodes sequence
     */
    @Override
    public Node regionSearch(Node node, String in, int count) {
        return this;
    }


    /**
     * 
     * @param node
     *            root of the subtree
     * @param stack
     *            a stack of all the nodes with a given string as a prefix
     * @return a stack containing the nodes with the given string as a
     *         prefix
     */
    @Override
    public Stack<Node> regionPrint(Node node, Stack<Node> stack) {
        stack.push(this);
        return stack;
    }


    /**
     * removes this node
     * 
     * @param node
     *            start of search
     * @param in
     *            sequence being searched for
     * @param count
     *            how many nodes the search as visited
     */
    @Override
    public Node remove(Node node, String in, int count) {
        // make sure we have the correct leaf
        if (in.equalsIgnoreCase(this.getSeq())) {
            node = fly;
            return node;
        }
        // should never get here
        return null;
    }

}
