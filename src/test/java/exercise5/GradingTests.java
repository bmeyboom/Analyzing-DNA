package exercise5;

import org.junit.Test;
import java.util.HashSet;
import static org.junit.Assert.*;

public class GradingTests {

    @Test
    public void test1_create_getMass() {
        DNA dna1 = new DNA("xATGCCAxCTATGGTAG");
        assertEquals(2178.8, dna1.totalMass(), 0.001);
    }

    @Test
    public void test2_create_checkProtein() {
        DNA dna2 = new DNA("ATGCCAACATGGATGCCCGATAT++GGATTG+xA!");
        assertTrue(dna2.isProtein());
    }

    @Test(expected=IllegalArgumentException.class)
    public void test3_create_invalidSeq() {
        DNA dna3 = new DNA("BB-T?A");
    }

    @Test
    public void test4_nucleotideCount() {
        DNA dna4 = new DNA("AAAGGTTACTGA");
        assertEquals(5, dna4.nucleotideCount('A'));
    }

    @Test
    public void test5_codonSet() {
        DNA dna5 = new DNA("AAAGGTTACTGAAAA");
        HashSet<String> expectedSet = new HashSet<>();
        expectedSet.add("AAA");
        expectedSet.add("GGT");
        expectedSet.add("TAC");
        expectedSet.add("TGA");
        assertEquals(expectedSet, dna5.codonSet());
    }

    @Test
    public void test6_getSequence() {
        DNA dna6 = new DNA("AXTTAAAGGTTACTGA");
        assertEquals("AXTTAAAGGTTACTGA", dna6.sequence());
    }

    @Test
    public void test7_mutate() {
        DNA dna7 = new DNA("AAAGGTTACTG+A");
        dna7.mutateCodon("TGA", "GAT");
        assertEquals("AAAGGTTACGAT", dna7.sequence());
    }

    @Test
    public void test8_mutate() {
        DNA dna7 = new DNA("AAAGGTTACTG+A");
        dna7.mutateCodon("TGA", "G+T");
        assertEquals("AAAGGTTACTG+A", dna7.sequence());
    }

    @Test
    public void test9_mutate() {
        DNA dna9 = new DNA("ATGCCAxCTATGGTAG");
        dna9.mutateCodon("CTA", "ACC");
        assertEquals(1964.8, dna9.totalMass(), 0.001);
        assertEquals("ATGCCAACCTGGTAG", dna9.sequence());
    }
}
