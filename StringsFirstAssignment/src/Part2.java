public class Part2 {

    /**
     * Copy and paste the  methods findSimpleGene
     * from the Part1 class into the Part2 class.
     */

    public String findSimpleGene(String dna,String startCodon,String stopCodon) {
        String gene="";

        if(Character.isLowerCase(dna.charAt(0))) {

            startCodon=startCodon.toLowerCase();

            stopCodon=stopCodon.toLowerCase();
        }

        int startIndex=dna.indexOf(startCodon);

        if(startIndex==-1)
            return "";

        int stopIndex=dna.indexOf(stopCodon,startIndex+3);

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
        System.out.println("gene is  "+findSimpleGene(DNA1,"ATG","TAA"));

        String DNA2="AAAATGATATATATATA";
        System.out.println("gene is  "+findSimpleGene(DNA2,"ATG","TAA"));

        String DNA3="AAAATATGATATACGCGCAAAAATAA";
        System.out.println("gene is  "+findSimpleGene(DNA3,"ATG","TAA"));

        String DNA4="AAAAAAATGGGGCGCGCGCGACG";
        System.out.println("gene is  "+findSimpleGene(DNA4,"ATG","TAA"));

        String DNA5="AAATGAAAAAGCGACGAGTCTAA";
        System.out.println("gene is  "+findSimpleGene(DNA5,"ATG","TAA"));

        String DNA6="AAATGAAAAAGCGTGCTAA";
        System.out.println("gene is  "+findSimpleGene(DNA6,"ATG","TAA"));

        String DNA7="aaaatgaaagggccctaa";
        System.out.println("gene is  "+findSimpleGene(DNA7,"ATG","TAA"));
    }

    public static void main(String[] args) {
        Part2 gene1=new Part2();
        gene1.testSimpleGene();
    }
}

