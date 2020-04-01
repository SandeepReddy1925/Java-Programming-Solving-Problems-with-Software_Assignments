import edu.duke.StorageResource;

public class Part3 {


   /*
   Write the void method processGenes that has one parameter sr, which is a StorageResource of strings.
   This method processes all the strings in sr to find out information about them. Specifically, it should:
    print all the Strings in sr that are longer than 9 characters
    print the number of Strings in sr that are longer than 9 characters
    print the Strings in sr whose C-G-ratio is higher than 0.35
    print the number of strings in sr whose C-G-ratio is higher than 0.35
    print the length of the longest gene in sr
    */
    public void processGenes(StorageResource sr) {

        StorageResource genesLenNine=new StorageResource();

        StorageResource cgRatioGene=new StorageResource();

        int genesWithLenNine=0;
        int genesWithCG=0;
        int longestString=0;

        for (String genes : sr.data()) {

            if (genes.length() > 9) {

                genesLenNine.add(genes);
                genesWithLenNine++;
            }

            double CGratio = new Part2().cgratio(genes);

            if (CGratio > 0.35) {

                cgRatioGene.add(genes);
                genesWithCG++;
            }

            if (genes.length() > longestString) {
                longestString = genes.length();
            }
        }

        System.out.println("Strings with length greater than 9 are ");
        
        for (String gene : genesLenNine.data()) {
            System.out.println(gene);
        }

        System.out.println("No of Strings with length greater than 9 are " + genesWithLenNine);

        System.out.println("Strings with C or G  ratio greater than 0.35 are ");

        for (String gene : cgRatioGene.data()) {
            System.out.println(gene);
        }

        System.out.println("No of Strings with C or G  ratio greater than 0.35 are " + genesWithCG);

        System.out.println("longest gene in Storage Resource is " + longestString);

    }
    public static void main(String args[])
    {
        Part3 gene1=new Part3();
        Part1 refgene=new Part1();

        StorageResource res1=new StorageResource();
        res1=refgene.getAllGenes("ATGAAAAAATAAATGAAAAACGCGCCGTGAATGCGCGCGCAATAG");
        gene1.processGenes(res1);

        StorageResource res2=new StorageResource();
        res1=refgene.getAllGenes("ATGAAAAAATAAATGAAAAACGCGCCGT");
        gene1.processGenes(res2);

        StorageResource res3=new StorageResource();
        res3=refgene.getAllGenes("ATGAAAAAATAAATGAAAAACGCGCCGTAAATGAACCGGCGCGCGCGCGCGCGCGCGCGCGCGTAA");
        gene1.processGenes(res3);


    }
}

