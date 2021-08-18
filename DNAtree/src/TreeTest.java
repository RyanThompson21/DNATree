
/**
 * @author ryanjt5
 * @version 3-23-20
 *
 */
public class TreeTest extends student.TestCase {
    private Tree tree;


    /**
     * sets up each test with a predetermined populated tree
     */
    public void setUp() {
        tree = new Tree();
        tree.insert("ACGT");

        tree.insert("CGT");
        tree.insert("CTG");

        tree.insert("GAT");
        tree.insert("GTA");

        tree.insert("TAG");
        tree.insert("TAA");
        tree.insert("TA");
        tree.insert("AAAA");
    }


    /**
     * tests insert and exact search
     */
    public void testInsert() {
        tree = new Tree();
        //System.out.println(tree.regionPrint("A"));
        // inserting into an empty tree
        tree.insert("ACGT");
        assertTrue("ACGT".equals(tree.dump(tree.getRoot(), 0)));
        assertEquals("1 t", tree.search("ACGT"));
        // insert a duplicate
        tree.insert("ACGT");
        assertTrue("ACGT".equals(tree.dump(tree.getRoot(), 0)));

        // test the next 3 letters for correct 'split'
        tree.insert("CGT");
        tree.insert("CTG");
        assertEquals("3 t", tree.search("CGT"));
        assertEquals("3 t", tree.search("CTG"));
        tree.insert("GAT");
        tree.insert("GTA");
        assertEquals("3 t", tree.search("GAT"));
        assertEquals("3 t", tree.search("GTA"));
        tree.insert("TAG");
        tree.insert("TAA");
        tree.insert("TA");
        assertEquals("4 t", tree.search("TAG"));
        assertEquals("4 t", tree.search("TAA"));
        assertEquals("4 t", tree.search("TA"));
        assertEquals("4 f", tree.search("TAT"));

        // force a split later
        tree.insert("AAAA");
        assertEquals("3 t", tree.search("AAAA"));
    }


    /**
     * tests remove
     */
    public void testRemove() {
        // simple remove, no restructuring required
        tree.remove("TA");
        assertEquals("4 f", tree.search("TA"));
        assertEquals("4 t", tree.search("TAG"));
        assertEquals("4 t", tree.search("TAA"));
        // only one 'collapse' required
        tree.remove("GTA");
        assertEquals("2 f", tree.search("GTA"));
        assertEquals("2 t", tree.search("GAT"));
        // two collapses required
        tree.remove("TAG");
        assertEquals("2 f", tree.search("TAG"));
        assertEquals("2 t", tree.search("TAA"));
        // Reset for other tests
        tree = new Tree();
        assertEquals("sequence AC does not exist", tree.remove("AC"));
        tree.insert("ACGT");
        assertEquals("sequence ACGT removed", tree.remove("ACGT"));
        assertEquals("1 f", tree.search("ACGT"));
    }


    /**
     * tests the region search process
     */
    public void testRegionSearch() {
        // should visit 12 nodes
        System.out.println(tree.regionPrint("T"));
        assertTrue(tree.regionPrint("T").contains("12"));
        assertTrue(tree.regionPrint("T").contains("TAA"));
        assertTrue(tree.regionPrint("T").contains("TAG"));
        assertTrue(tree.regionPrint("T").contains("TA"));
        // System.out.println(tree.regionPrint("TA"));
        // should visit 8 nodes
        assertTrue(tree.regionPrint("TA").contains("8"));
        assertTrue(tree.regionPrint("TA").contains("TAA"));
        assertTrue(tree.regionPrint("TA").contains("TAG"));
        assertTrue(tree.regionPrint("TA").contains("TA"));
        // System.out.println(tree.regionPrint("A"));
        assertTrue(tree.regionPrint("A").contains("7"));
        // System.out.println(tree.regionPrint("C"));
        assertTrue(tree.regionPrint("C").contains("7"));
        // System.out.println(tree.regionPrint("G"));
        assertTrue(tree.regionPrint("G").contains("7"));
        // System.out.println(tree.regionPrint("TAT"));
        assertTrue(tree.regionPrint("TAT").contains("4"));
        assertTrue(tree.regionPrint("TAT").contains("no "));
        assertTrue(tree.regionPrint("AA").contains("3"));
        assertTrue(tree.regionPrint("AA").contains("AAAA"));
        // System.out.println(tree.dump(tree.getRoot(), 0));
    }


    /**
     * tests regular print
     */
    public void testPrints() {
        // System.out.println(tree.dump(tree.getRoot(), 0));
        assertTrue(tree.dump(tree.getRoot(), 0).contains("      TA"));
        assertTrue(tree.dump(tree.getRoot(), 0).contains("  AAAA"));
        assertTrue(tree.dump(tree.getRoot(), 0).contains("  CGT"));
        assertTrue(tree.dump(tree.getRoot(), 0).contains("  GTA"));
        assertTrue(tree.dump(tree.getRoot(), 0).contains("  I"));
    }


    /**
     * tests printLength
     */
    public void testPrintLength() {
        // System.out.println(tree.printLength(tree.getRoot(), 0));
        assertTrue(tree.printLength(tree.getRoot(), 0).contains("AAAA 4"));
        assertTrue(tree.printLength(tree.getRoot(), 0).contains("CGT 3"));
        assertTrue(tree.printLength(tree.getRoot(), 0).contains("TA 2"));
    }

    /**
     * tests print stats
     */
    public void testPrintStats() {
        //System.out.println(tree.printStats(tree.getRoot(), 0));
        assertTrue(tree.printStats(tree.getRoot(), 0).contains("A:100.00"));
        assertTrue(tree.printStats(tree.getRoot(), 0).contains("A:33.33"));
        assertTrue(tree.printStats(tree.getRoot(), 0).contains("A:66.67"));
        assertTrue(tree.printStats(tree.getRoot(), 0).contains("A:0.00"));
    }

}
