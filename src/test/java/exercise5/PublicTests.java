package exercise5;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class PublicTests {

    @Test
    public void test1_create_getMass() {
        DNA dna1 = new DNA("ATGCCAxCTATGGTAG");
        assertEquals(2078.8, dna1.totalMass(), 0.001);
    }

    @Test
    public void test2_create_checkProtein() {
        DNA dna2 = new DNA("ATGCCAACATGGATGCCCGATAT++GGATTG+A!");
        assertTrue(dna2.isProtein());
    }

    @Test(expected=IllegalArgumentException.class)
    public void test3_create_invalidSeq() {
        DNA dna3 = new DNA("-TA");
    }

    @Test
    public void test4_nucleotideCount() {
        DNA dna4 = new DNA("AAAGGTTACTGA");
        assertEquals(5, dna4.nucleotideCount('A'));
    }

    @Test
    public void test5_codonSet() {
        DNA dna5 = new DNA("AAAGGTTACTGA");
        HashSet<String> expectedSet = new HashSet<>();
        expectedSet.add("AAA");
        expectedSet.add("GGT");
        expectedSet.add("TAC");
        expectedSet.add("TGA");
        assertEquals(expectedSet, dna5.codonSet());
    }

    @Test
    public void test6_getSequence() {
        DNA dna6 = new DNA("AAAGGTTACTGA");
        assertEquals("AAAGGTTACTGA", dna6.sequence());
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
        dna9.mutateCodon("CTA", "ATC");
        assertEquals(1978.8, dna9.totalMass(), 0.001);
        assertEquals("ATGCCAATCTGGTAG", dna9.sequence());
    }
}
