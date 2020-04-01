import edu.duke.StorageResource;

public class Part1 {
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
        public void testFindStopCodon()
        {
            String dna="AAAATGAACGTTGAGCGCGTAAAAGGGGAATAAAA";
            int startIndex=dna.indexOf("ATG");
            System.out.println(startIndex);
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

        }
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
    public StorageResource getAllGenes(String dna) {
        StorageResource resource = new StorageResource();

        int startindex = 0;
        while (true) {
            String currentgene = findGene(dna, startindex);
            if (currentgene.isEmpty()) {
                break;
            }
            resource.add(currentgene);
            startindex = dna.indexOf(currentgene, startindex) + currentgene.length();
        }
        return resource;
    }
    public void testGetAllGenes()
    {
        StorageResource resource=new StorageResource();
        resource=getAllGenes("TAATGAAAAAATAAAATGAAAAAATAGAATGAAAAAATGA");
        for(String gene:resource.data())
            System.out.println(gene);

    }



        public static void main(String args[])
        {
            Part1 gene1=new Part1();

            gene1.testFindGene();

            gene1.testFindStopCodon();

            gene1.printAllGenes("TAATGAAAAAATAAAATGAAAAAATAGAATGAAAAAATGA");

            gene1.testGetAllGenes();



        }
    }

