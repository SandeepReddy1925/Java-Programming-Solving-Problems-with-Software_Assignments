import edu.duke.StorageResource;

public class Part2 {

    //method to calculaate the character C or G ratio in dna Strand
    public  double cgratio(String dna)
    {
        double cgRatio=0.0;
        int count=0;

        for(char c:dna.toCharArray())
        {
            if(c=='C'||c=='G')
            {
                count++;
            }
        }

        cgRatio=(double)count/dna.length();

        return cgRatio;

    }

    //method to calculate the count of substring CTG
    public int countCTG(String dna)
    {
        int countofCTG=0;
        int CTGindex=0;

        while(CTGindex!=-1)
        {
            CTGindex=dna.indexOf("CTG",CTGindex);

            if(CTGindex==-1)
                break;

            countofCTG++;
            CTGindex+=3;
        }

        return countofCTG;
    }

    public static void main(String args[])
    {
        Part2 gene1=new Part2();

        System.out.println("C or G ratio in dna Strand is "+gene1.cgratio("ATGCCATAG"));

        System.out.println("CTG count in dna Strand is "+gene1.countCTG("ATGCTGCTGCTGCTGCTGAA"));
    }
}
