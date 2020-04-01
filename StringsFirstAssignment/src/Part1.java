public class Part1 {

    /*
     * Write the method findSimpleGene that has one String parameter dna, representing a string of DNA. This method does the following:
     * Finds the index position of the start codon “ATG”. If there is no “ATG”, return the empty string.
     * Finds the index position of the first stop codon “TAA” appearing after the “ATG” that was found. If there is no such “TAA”, return the empty string.
     * If the length of the substring between the “ATG” and “TAA” is a multiple of 3, then return the substring that starts with that “ATG” and ends with that “TAA”.
     */
    public String findSimpleGene(String dna) {

        String gene="";

        int startIndex=dna.indexOf("ATG");

        if(startIndex==-1)
            return "";

        int stopIndex=dna.indexOf("TAA",startIndex+3);

        if(stopIndex==-1)
            return "";

        if((startIndex-stopIndex-3)%3==0)
            gene=dna.substring(startIndex,stopIndex+3);

        else
            gene="";

        return gene;
    }


    public void testSimpleGene()
    {
        String DNA1="AAAAAAATAA";
        System.out.println("gene is  "+findSimpleGene(DNA1));

        String DNA2="AAAATGATATATATATA";
        System.out.println("gene is  "+findSimpleGene(DNA2));

        String DNA3="AAAATATGATATACGCGCAAAAATAA";
        System.out.println("gene is  "+findSimpleGene(DNA3));

        String DNA4="AAAAAAATGGGGCGCGCGCGACG";
        System.out.println("gene is  "+findSimpleGene(DNA4));

        String DNA5="AAATGAAAAAGCGACGAGTCTAA";
        System.out.println("gene is  "+findSimpleGene(DNA5));

        String DNA6="AAATGAAAAAGCGTGCTAA";
        System.out.println("gene is  "+findSimpleGene(DNA6));
    }

    public static void main(String[] args) {
        Part1 gene1=new Part1();
        gene1.testSimpleGene();
    }

}
