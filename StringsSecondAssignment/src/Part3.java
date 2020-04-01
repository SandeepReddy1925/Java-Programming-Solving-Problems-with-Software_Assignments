public class Part3 {


    /**
     * Copy your methods from Part1 to find one gene and print all genes.
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


    /**
     * Write the method named countGenes that has a String parameter named dna representing a string of DNA.
     * This method returns the number of genes found in dna. For example the call countGenes(“ATGTAAGATGCCCTAGT”) returns 2,
     * finding the gene ATGTAA first and then the gene ATGCCCTAG. Hint: This is very similar to finding all genes and printing them,
     * except that instead of printing all the genes you will count them.
     */
    public int countGenes(String dna)
    {

        int startindex=0;
        int count=0;

        while(true)
        {
            String currentgene=findGene(dna,startindex);

            if(currentgene.isEmpty())
            {
                break;
            }

           count++;

            startindex=dna.indexOf(currentgene,startindex)+currentgene.length();
        }

        return count;
    }

  public void  testCountGenes()
    {

        int countGenes=+countGenes("TAATGAAAAAATAAAATGAAAAAATAGAATGAAAAAATGA");
        System.out.println(countGenes);

        countGenes=+countGenes("TAATGAAAAAATAAAATGAAAAAATAGAATGAAAAAATGAATGGGGAAATAA");
        System.out.println(countGenes);

    }

    public static void main(String args[])
    {
        Part3 countGenes=new Part3();
        countGenes.testCountGenes();
    }


}
