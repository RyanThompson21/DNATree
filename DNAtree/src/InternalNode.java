import java.util.*;

/**
 * @author ryanjt5
 * @version 3-23-20
 *
 */
public class InternalNode extends Node {

    private Node a;
    private Node c;
    private Node g;
    private Node t;
    private Node d;
    private int regionCount;


    /**
     * creates a new internal node with 5 children pointing to the fly weight
     * 
     * @param fly
     *            the flyweight
     * @param lvl
     *            level the node is on, determined on insert
     */
    public InternalNode(Node fly, int lvl) {
        a = fly;
        c = fly;
        g = fly;
        t = fly;
        d = fly;
        setLevel(lvl);
        regionCount = 0;
    }


    /**
     * setter for region count
     * 
     * @param x
     *            new region count
     */
    public void setRegionCount(int x) {
        regionCount = x;
    }


    /**
     * getter for regionCount
     * 
     * @return regoinCount
     */
    public int getRegionCount() {
        return regionCount;
    }


    /**
     * getter for A child
     * 
     * @return A child
     */
    public Node getA() {
        return a;
    }


    /**
     * setter for A child
     * 
     * @param a
     *            node being inserted
     */
    public void setA(Node a) {
        this.a = a;
    }


    /**
     * getter for C child
     * 
     * @return C child
     */
    public Node getC() {
        return c;
    }


    /**
     * setter for C child
     * 
     * @param c
     *            node being inserted
     */
    public void setC(Node c) {
        this.c = c;
    }


    /**
     * getter for G child
     * 
     * @return G child
     */
    public Node getG() {
        return g;
    }


    /**
     * setter for G child
     * 
     * @param g
     *            node being inserted
     */
    public void setG(Node g) {
        this.g = g;
    }


    /**
     * getter for T child
     * 
     * @return T child
     */
    public Node getT() {
        return t;
    }


    /**
     * setter for T child
     * 
     * @param t
     *            node being inserted
     */
    public void setT(Node t) {
        this.t = t;
    }


    /**
     * getter for $ child
     * 
     * @return the $ child
     */
    public Node getD() {
        return d;
    }


    /**
     * setter for the $ child
     * 
     * @param d
     *            node being inserted
     */
    public void setD(Node d) {
        this.d = d;
    }


    /**
     * prints out I, for internal, with a buffer of spaces depending on it's
     * level
     * 
     * @param numSpaces
     *            a count used to determine how many spaces are required when
     *            printing out the node
     * @return returns a string representation of this node
     */
    public String print(int numSpaces) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numSpaces; i++) { // pad with spaces
            sb.append("  ");
        }
        sb.append("I");
        return sb.toString();
    }


    /**
     * inserts the given sequence into the tree
     * 
     * @param node
     *            where the search recursion starts from
     * @param in
     *            sequence being inserted
     * @param count
     *            to keep track of the level
     * @return the node containing inserted sequence
     */
    @Override
    public Node insert(Node node, String in, int count) {
        if (count >= in.length()) {
            Node dollar = ((InternalNode)node).getD();
            this.setD(dollar.insert(d, in, count + 1));
            return this;
        }
        else {
            if (in.charAt(count) == 'A') {
                Node aa = ((InternalNode)node).getA();
                this.setA(aa.insert(aa, in, count + 1));
                return this;
            }
            else if (in.charAt(count) == 'C') {
                Node cc = ((InternalNode)node).getC();
                this.setC(cc.insert(cc, in, count + 1));
                return this;
            }
            else if (in.charAt(count) == 'G') {
                Node gg = ((InternalNode)node).getG();
                this.setG(gg.insert(gg, in, count + 1));
                return this;
            }
            else { // must be T
                Node tt = ((InternalNode)node).getT();
                this.setT(tt.insert(tt, in, count + 1));
                return this;
            }
        }
    }


    /**
     * This directs the search for the node to be removed
     * 
     * @param node
     *            where the recursion is currently
     * @param in
     *            sequence being removed
     * @param count
     *            to keep track of the level
     * @return the node THIS should be after removal
     */
    @Override
    public Node remove(Node node, String in, int count) {
        if (count >= in.length()) {
            Node dollar = ((InternalNode)node).getD();
            this.setD(dollar.remove(dollar, in, count + 1));
        }
        else {
            if (in.charAt(count) == 'A') {
                Node aa = ((InternalNode)node).getA();
                this.setA(aa.remove(aa, in, count + 1));
            }
            else if (in.charAt(count) == 'C') {
                Node cc = ((InternalNode)node).getC();
                this.setC(cc.remove(cc, in, count + 1));
            }
            else if (in.charAt(count) == 'G') {
                Node gg = ((InternalNode)node).getG();
                this.setG(gg.remove(gg, in, count + 1));
            }
            else {
                Node tt = ((InternalNode)node).getT();
                this.setT(tt.remove(tt, in, count + 1));
            }
        }
        // check for 'folding'
        if (this.childrenCount() >= 2) {
            // There is enough children
            return this;
        }
        else { // MIGHT need to be collapsed
            Node temp = this.child();
            // if the single child is internal then this node can't collapse
            if (temp instanceof InternalNode) {
                return this;
            }
            else {
                node = temp;
                node.setLevel(node.getLevel() - 1); // update its level
                return node;
            }
        }
    }


    /**
     * counts the number of leafnode/internalnode children this node has
     * 
     * @return the number of leafnode/internalnode children
     */
    private int childrenCount() {
        int count = 0;
        if (this.getA() instanceof LeafNode || this
            .getA() instanceof InternalNode) {
            count++;
        }
        if (this.getC() instanceof LeafNode || this
            .getC() instanceof InternalNode) {
            count++;
        }
        if (this.getG() instanceof LeafNode || this
            .getG() instanceof InternalNode) {
            count++;
        }
        if (this.getT() instanceof LeafNode || this
            .getT() instanceof InternalNode) {
            count++;
        }
        if (this.getD() instanceof LeafNode || this
            .getD() instanceof InternalNode) {
            count++;
        }
        return count;
    }


    /**
     * finds the non empty child.
     * this method is only used when having exactly
     * one non empty child is guaranteed
     * 
     * @return the non-empty child
     */
    private Node child() {
        if (this.getA() instanceof LeafNode || this
            .getA() instanceof InternalNode) {
            return this.getA();
        }
        else if (this.getC() instanceof LeafNode || this
            .getC() instanceof InternalNode) {
            return this.getC();
        }
        else if (this.getG() instanceof LeafNode || this
            .getG() instanceof InternalNode) {
            return this.getG();
        }
        else if (this.getT() instanceof LeafNode || this
            .getT() instanceof InternalNode) {
            return this.getT();
        }
        else { // must be $
            return this.getD();
        }
    }


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
    @Override
    public String search(Node node, String in, int count) {
        if (count >= in.length()) {
            Node dollaBills = ((InternalNode)node).getD();
            return dollaBills.search(d, in, count + 1);
        }
        else {
            if (in.charAt(count) == 'A') {
                Node aa = ((InternalNode)node).getA();
                return aa.search(aa, in, count + 1);
            }
            else if (in.charAt(count) == 'C') {
                Node cc = ((InternalNode)node).getC();
                return cc.search(cc, in, count + 1);
            }
            else if (in.charAt(count) == 'G') {
                Node gg = ((InternalNode)node).getG();
                return gg.search(gg, in, count + 1);
            }
            else { // Must be T
                Node tt = ((InternalNode)node).getT();
                return tt.search(tt, in, count + 1);
            }
        }
    }


    /**
     * finds the correct starting point for regionPrint
     * 
     * @param node
     *            node at current state of recursion
     * @param in
     *            sequence being searched for
     * @param count
     *            count of how many nodes are visited
     * @return the root of the subtree with sequence as prefix
     */
    @Override
    public Node regionSearch(Node node, String in, int count) {
        if (in.length() <= count) { // found the correct starting point
            return this;
        }
        else { // more searching required
            if (in.charAt(count) == 'A') {
                Node aa = ((InternalNode)node).getA();
                return aa.regionSearch(aa, in, count + 1);
            }
            else if (in.charAt(count) == 'C') {
                Node cc = ((InternalNode)node).getC();
                return cc.regionSearch(cc, in, count + 1);
            }
            else if (in.charAt(count) == 'G') {
                Node gg = ((InternalNode)node).getG();
                return gg.regionSearch(gg, in, count + 1);
            }
            else if (in.charAt(count) == 'T') {
                Node tt = ((InternalNode)node).getT();
                return tt.regionSearch(tt, in, count + 1);
            }
            else {
                Node dollas = ((InternalNode)node).getD();
                return dollas.regionSearch(dollas, in, count + 1);
            }

        }
    }


    /**
     * Starts at the given node and gets all leaves that have the given sequence
     * as a prefix
     * 
     * @param node
     *            root of the subtree
     * @param stack
     *            stack of all leaves of subtree
     * @return a string containing the sequences with the given string as a
     *         prefix
     *         and the number of nodes visited
     */
    @Override
    public Stack<Node> regionPrint(Node node, Stack<Node> stack) {
        stack.push(this);

        Node aa = ((InternalNode)node).getA();
        stack = (aa.regionPrint(aa, stack));

        Node cc = ((InternalNode)node).getC();
        stack = (cc.regionPrint(cc, stack));

        Node gg = ((InternalNode)node).getG();
        stack = (gg.regionPrint(gg, stack));

        Node tt = ((InternalNode)node).getT();
        stack = (tt.regionPrint(tt, stack));

        Node dolla = ((InternalNode)node).getD();
        stack = (dolla.regionPrint(dolla, stack));

        return stack;
    }


    /**
     * prints out I, for internal, with a buffer of spaces depending on it's
     * level
     * Nothing changes for internal nodes when printing stat
     * 
     * @param numSpaces
     *            a count used to determine how many spaces are required when
     *            printing out the node
     * @return returns a string representation of this node
     */
    @Override
    public String printLength(int numSpaces) {
        return this.print(numSpaces);
    }


    /**
     * prints out I, for internal, with a buffer of spaces depending on it's
     * level
     * Nothing changes for internal nodes when printing length
     * 
     * @param numSpaces
     *            a count used to determine how many spaces are required when
     *            printing out the node
     * @return returns a string representation of this node
     */
    @Override
    public String printStats(int numSpaces) {
        return this.print(numSpaces);
    }
}
