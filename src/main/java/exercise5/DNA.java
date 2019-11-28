package exercise5;// TODO: Implement the DNA datatype from scratch!
// Use the test cases to guide you.

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DNA {

    final double A_MASS= 135.128;
    final double C_MASS = 111.103;
    final double G_MASS = 151.128;
    final double T_MASS = 125.107;
    final double JUNK_MASS = 100.000;

    public String sequence;
    public Set<String> codonSet;
    public String noJunkSequence;
    public double mass;
    public ArrayList<String> codonArr;

    /*
    protein starts with ATG, ends with TAA, TAG, or TGA
    has at least 5 codons (start and stop included), each complete (sequence of 3)
    if not complete throw IllegalArgumentException
    C and G account for at least 30% mass
    make sure to reject invalid sequences
    * */
    public DNA(String nucleotides) {
        if (!isValid(nucleotides)) {
            throw new IllegalArgumentException("Invalid DNA sequence");
        }
        this.sequence = nucleotides;
        this.codonSet = codonSet();
        this.codonArr = toArrayList(nucleotides);
        this.noJunkSequence = noJunk(nucleotides);
        this.mass = totalMass();
    }

    /*
    * checks if DNA is a protein by making sure the first codon is ATG
    * if the sequence ends with TAA, TAG or TGA
    * if there are at least 5 codons
    * if 30% fo the total mass is G and C
    * @return boolean whether this DNA is a protein
     */
    public boolean isProtein() {
        // check to make sure enough codons
        if (this.codonArr.size() < 5) {
            return false;
        }
        // check first and last codons
        if (!(this.codonArr.get(0).equals("ATG"))) {
           return false;
        }
        String end = this.codonArr.get(codonArr.size()-1);
        if (!(end.equals("TAA") || end.equals("TAG")|| end.equals("TGA"))) {
            return false;
        }

        // check if G and C account for at least 30% of the total mass

        double gcMass = nucleotideCount('C')*C_MASS + nucleotideCount('G')*G_MASS;

        if ((gcMass/this.mass) >= 0.3) {
            return true;
        } else {
            return false;
        }
    }

    public double totalMass() {
        double totalMass = 0; // keeps track of total mass

        for (char n: this.sequence.toCharArray()) { // goes through each nucleotide
            if (n == 'A') {
                totalMass+= A_MASS;
            } else if (n == 'C') {
                totalMass+= C_MASS;
            } else if (n == 'G') {
                totalMass+= G_MASS;
            } else if (n == 'T') {
                totalMass+= T_MASS;
            } else {
                totalMass += JUNK_MASS;
            }
        }
        return Math.round(totalMass*10.0)/10.0; // returns total mass with 1 decimal point
    }

    /*
    * Checks how many of the input characters are in the DNA sequence
    * @param c is a nucleotide
    * @returns the number of nucleotides input that occur in the sequence
     */
    public int nucleotideCount(char c) {
        if (!isNucleotide(c)) {
            return 0;
        }
        int count = 0;
        char[] nucleotides = this.noJunkSequence.toCharArray();
        for(char n: nucleotides) {
            if (n == c) {
                count++;
            }
        }
        return count;
    }

    public Set<String> codonSet() {
        System.out.println("Setting codons: " + this.sequence);
        Set<String> cSet = new HashSet<>();
        String noJunk = noJunk(this.sequence);
        System.out.println(noJunk);
        for (int i = 0; i < noJunk.length();) {
            String codon = "";
            for (int k = 0; k < 3; k++) {
                codon += noJunk.charAt(i);
                i++;
            }
            System.out.println(codon);
            cSet.add(codon);
        }

        return cSet;
    }

    public void mutateCodon(String originalCodon, String newCodon) {

        // check to make sure new and old codons are valid
        if (!(isValid(originalCodon) && isValid(newCodon))) {
            return;
        }

        this.sequence = noJunk(this.sequence);
        this.sequence = this.sequence.replaceAll(originalCodon, newCodon);
        this.codonSet = codonSet();
        this.codonArr = toArrayList(this.sequence);
        this.noJunkSequence = noJunk(this.sequence);
        this.mass = totalMass();
    }

    /*
    *@return String that is the sequence of nucleotides in the DNA sequence
     */
    public String sequence() {
        return this.sequence;

    }

    /*
    * @param checks to make sure each codon has 3 nucleotides
    * @returns whether the input DNA sequence is valid ie. has all codons length 3
    * */
    public boolean isValid(String sequence) {

        String noJunk = noJunk(sequence);
        if (noJunk.length()%3 == 0) {
            return true;
        }
        return false;
    }

    /*
    * @param s is a sequence of nucleoties
    * @returns ArrayList of codons from the nucleotide sequence
     */
    public ArrayList<String> toArrayList(String s) {
        ArrayList<String> cArrList = new ArrayList<>();
        int nCount = 0; // counts number of nucleotides
        String codon = ""; // string to create a codon

        // iterate through string collecting only the nucleotides and grouping them
        for(int i = 0; i < this.sequence.length(); i++) {
            // if the current codon is already 3 long, add it to the set and reset
            if (nCount == 3) {
                nCount = 0;
                cArrList.add(codon);
                codon = "";
            } else {
                char nucleotide = this.sequence.charAt(i);
                // if the character is a nucleotide add it to the current codon
                if (isNucleotide(nucleotide)) {
                    codon += nucleotide;
                    nCount++;
                }
            }
        }
        return cArrList;
    }

    /*
    * @param nucleotide is a character from the DNA sequence
    * @returns whether the character is a nucleotide A C G or T and false otherwise
     */
    public boolean isNucleotide(char nucleotide) {
        if (nucleotide == 'A'|| nucleotide == 'C' || nucleotide == 'G' || nucleotide == 'T') {
            return true;
        }
        return false;
    }

    /*
    * @param String of nucleotides and junk
    * @returns sequence with no junk
     */
    public String noJunk(String sequence) {
        String noJunk = "";
        for (int i = 0; i < sequence.length(); i++) {
            if (isNucleotide(sequence.charAt(i))) {
                noJunk += sequence.charAt(i);
            }
        }
        return noJunk;
    }
}
