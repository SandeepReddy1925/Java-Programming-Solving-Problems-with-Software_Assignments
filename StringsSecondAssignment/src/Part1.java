public class Part1 {


    /**
     * Write the method findStopCodon that has three parameters, a String parameter named dna, an integer parameter
     * named startIndex that represents where the first occurrence of ATG occurs in dna,
     * and a String parameter named stopCodon. This method returns the index of the first occurrence of stopCodon
     * that appears past startIndex and is a multiple of 3 away from startIndex.
     * If there is no such stopCodon, this method returns the length of the dna strand.
     */
    public int findStopCodon(String dna,int startIndex,String stopCodon)
    {

        int currindex=dna.indexOf(stopCodon,startIndex+3);

        while(currindex!=-1) {

            int diff=currindex-startIndex;

            if (diff% 3== 0)
                return currindex;
            else
                 currindex=dna.indexOf(stopCodon,currindex+2);
        }

        return dna.length();

    }

    /**
     *Write the void method testFindGene that has no parameters. You should create five DNA strings.
     *  The strings should have specific test cases such as DNA with no “ATG”, DNA with “ATG” and one valid stop codon,
     *  DNA with “ATG” and multiple valid stop codons, DNA with “ATG” and no valid stop codons
     */
    public void testFindStopCodon()
    {

        String dna="AAAATGAACGTTGAGCGCGTAAAAGGGGAATAAAA";

        int startIndex=dna.indexOf("ATG");

        int genelength=+findStopCodon(dna,startIndex,"TAA");

        if(genelength==dna.length())
            System.out.println("gene not found");
        else
            System.out.println("gene found");

        dna="ACGGCGCAGATGAAAGCCGCGCGTAATAGCCAATAGGGGAGA";

        startIndex=dna.indexOf("ATG");

        genelength=+findStopCodon(dna,startIndex,"TAG");

        if(genelength!=dna.length())
            System.out.println("gene found");

        else
            System.out.println("gene not found");

    }


/**
 * Write the method findGene that has one String parameter dna, representing a string of DNA. In this method you should do the following:
 * Find the index of the first occurrence of the start codon “ATG”. If there is no “ATG”, return the empty string.
 * Find the index of the first occurrence of the stop codon “TAA” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”.
 * Find the index of the first occurrence of the stop codon “TAG” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”. Find the index of the first occurrence of the
 * stop codon “TGA” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”.
 */
    public String findGene(String dna)
    {

        int startIndex=dna.indexOf("ATG");

        if(startIndex==-1)
            return "";

        int stopIndexTAA=+findStopCodon(dna,startIndex,"TAA");
        int stopIndexTGA=+findStopCodon(dna,startIndex,"TGA");
        int stopIndexTAG=+findStopCodon(dna,startIndex,"TAG");

        int min=Math.min(stopIndexTAA,Math.min(stopIndexTGA,stopIndexTAG));

        if(min==dna.length())
            return "";
        else
            return dna.substring(startIndex,min+3);

    }

    public String findGene(String dna,int where)
    {

        int startIndex=dna.indexOf("ATG",where);

        if(startIndex==-1)
            return "";

        int stopIndexTAA=+findStopCodon(dna,startIndex,"TAA");
        int stopIndexTGA=+findStopCodon(dna,startIndex,"TGA");
        int stopIndexTAG=+findStopCodon(dna,startIndex,"TAG");

        int min=Math.min(stopIndexTAA,Math.min(stopIndexTGA,stopIndexTAG));

        if(min==dna.length())
            return "";
        else
            return dna.substring(startIndex,min+3);
    }

    public void testFindGene()
    {
        String dna="GGGGGGGGGGGAAAAAAATATATATGTAGTAGTAGTAA";
        System.out.println("DNA Strand is "+dna+"\ngene is "+findGene(dna));

        dna="AAGGATGAAAAAAAAAGGGGGTAGATAATGA";
        System.out.println("DNA Strand is "+dna+"\ngene is "+findGene(dna));

    }


    /**
     * Write the void method printAllGenes
     * that has one String parameter dna, representing a string of DNA
     */
    public void printAllGenes(String dna)
    {
        int startindex=0;

        while(true)
        {
            String currentgene=findGene(dna,startindex);

            if(currentgene.isEmpty())
            {
                break;
            }

            System.out.println(currentgene);

            startindex=dna.indexOf(currentgene,startindex)+currentgene.length();
        }
    }

    public static void main(String args[])
    {
        Part1 gene1=new Part1();
        gene1.testFindStopCodon();
        gene1.testFindGene();
        gene1.printAllGenes("TAATGAAAAAATAAAATGAAAAAATAGAATGAAAAAATGA");
    }
}
