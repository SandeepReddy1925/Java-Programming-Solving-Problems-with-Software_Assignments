import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;

public class BabyBirths {

    /*
     * Write a method totalBirths  to  print the number of girls names ,
     * the number of boys names and the total names in the file.
     */
    public void totalBirths() {

        FileResource fileResource=new FileResource();

        int totalBirths = 0;
        int totalgirlNames = 0;
        int totalboyNames = 0;

        for (CSVRecord rec : fileResource.getCSVParser(false)) {

            totalBirths += Integer.parseInt(rec.get(2));

            if (rec.get(1).equals("M"))
                totalboyNames++;
            else
                totalgirlNames++;
        }

        System.out.println("Total Births are : "+totalBirths);
        System.out.println("Number of girls names : "+totalgirlNames);
        System.out.println("Number of boys names : "+totalboyNames);

    }


/*
 *Write the method named getRank that has three parameters: an integer named year, a string named name, and a string named gender (F for female and M for male).
 * This method returns the rank of the name in the file for the given gender, where rank 1 is the name with the largest number of births.
 * If the name is not in the file, then -1 is returned.
 * For example, in the file "yob2012short.csv", given the name Mason, the year 2012 and the gender ‘M’,
 * the number returned is 2, as Mason is the boys name with the second highest number of births.
 */
 public int getRank(int year,String name,String gender) {

        int rank = 0;

        System.out.println("Select file " + "yob" + year + ".csv");

        FileResource fileResource = new FileResource();

        for (CSVRecord record : fileResource.getCSVParser(false)) {

            if (record.get(1).equals(gender)) {
                rank++;
                if (record.get(0).equals(name))
                    return rank;
            }
        }

        return -1;
    }


/*
 * Write the method named getName that has three parameters: an integer named year, an integer named rank, and a string named gender (F for female and M for male).
 * This method returns the name of the person in the file at this rank, for the given gender,
 * where rank 1 is the name with the largest number of births.
 * If the rank does not exist in the file, then “NO NAME” is returned.
 */
    public String getName(int year,int rank,String gender) {

        System.out.println("Open file  yob" + year + ".csv");

        int rankOfFile=0;


        FileResource fileResource = new FileResource();

        for (CSVRecord record : fileResource.getCSVParser(false)) {

                    rankOfFile++;

                    if(rankOfFile==rank && record.get(1).equals(gender))
                        return record.get(0);
            }

        return "NO NAME";
    }

    public void testGetRank()
    {
        int rank=+getRank(1902,"Sandeep","M");
        System.out.println("Rank of person is "+rank);
    }


    //method to test getName()
    public void testGetName()
    {
        String name=getName(1902,64,"F");

        if(name.isEmpty())
            System.out.println(name);

        else
            System.out.println("name is "+name);

    }


/*
 * What would your name be if you were born in a different year? Write the void method named whatIsNameInYear that has four parameters:
 * a string name, an integer named year representing the year that name was born, an integer named newYear and a string named gender
 * This method determines what name would have been named if they were born in a different year,
 * based on the same popularity. That is, you should determine the rank of name in the year they were born,
 * and then print the name born in newYear that is at the same rank and same gender.
 * For example, using the files "yob2012short.csv" and "yob2014short.csv", notice that in 2012 Isabella is the third most popular girl's name. If Isabella was born in 2014 instead,
 * she would have been named Sophia, the third most popular girl's name that year.
 */
    public void whatIsNameInYear(String name,int year,int newYear,String gender)
    {
        int rank=+getRank(year,name,gender);

        String newName=getName(newYear,rank,gender);

        System.out.println(name+" born in "+year+" would be "+newName+" if she was born in "+newYear);
    }


/*
 * Write the method yearOfHighestRank that has two parameters: a string name, and a string named gender.
 * This method selects a range of files to process and returns an integer, the year with the highest rank
 * for the name and gender. If the name and gender are not in any of the selected files, it should return -1.
 * For example, calling yearOfHighestRank with name Mason and gender ‘M’ and selecting the three test files above results in returning the year 2012.
 * That is because Mason was ranked the 2nd most popular name in 2012, ranked 4th in 2013 and ranked 3rd in 2014.
 * His highest ranking was in 2012.
 */
    public int yearOfHighestRank(String name,String gender) {

        int highestRank = Integer.MAX_VALUE;
        int year = 0;
        int highestRankYear =0;

        DirectoryResource directoryResource = new DirectoryResource();

        for (File file : directoryResource.selectedFiles()) {

            String fileName = file.getName();

            year = Integer.parseInt(fileName.substring(3, 7));

            int currentrank = +getRank(year, name, gender);

            if (currentrank<highestRank) {

                highestRankYear = year;
                highestRank = currentrank;

            }
        }

        System.out.println(highestRank);

        if (highestRank > 0)
            return highestRankYear;
        else
            return -1;

    }


    //method to test yearOfHighestRank();
    public void testYearOfHighestRank()
    {
        int highestRankYear= yearOfHighestRank("Mason","M");

        if(highestRankYear!=-1)
            System.out.println("Highest rank year "+highestRankYear);
        else
            System.out.println("NO RECORD");

    }


/*
 * Write the method getAverageRank that has two parameters: a string name, and a string named gender (F for female and M for male).
 * This method selects a range of files to process and returns a double representing the average rank of the name and gende over the selected files.
 * It should return -1.0 if the name is not ranked in any of the selected files.
 */
    public double getAverageRank(String name,String gender) {
        int year = 0;

        double averageRank = 0.0;

        int totalRank = 0;

        int noOfFiles=0;
        DirectoryResource directoryResource = new DirectoryResource();

        for (File file : directoryResource.selectedFiles()) {

            String fileName = file.getName();

            year = Integer.parseInt(fileName.substring(3, 7));

            int currentrank = +getRank(year, name, gender);

            noOfFiles++;

            totalRank += currentrank;
        }
        averageRank=totalRank/noOfFiles;

        if(averageRank>0.0)
            return averageRank;

        else
            return -1;

    }

    //method to test averageRank();
    public void testAverageRank()
    {
        double averageRank=+getAverageRank("Mason","M");

        if(averageRank>0)
            System.out.println("Average rank of Mason is "+averageRank);

        else
            System.out.println("No such record");

    }


/**\
 * Write the method getTotalBirthsRankedHigher that has three parameters: an integer named year, a string named name, and a string named gender (
 *  This method returns an integer, the total number of births of those names with the same gender and same year who are ranked higher than name.
 *  For example, if getTotalBirthsRankedHigher accesses the "yob2012short.csv" file with name set to “Ethan”, gender set to “M”, and year set to 2012,
 *  then this method should return 15, since Jacob has 8 births and Mason has 7 births, and those are the only two ranked higher than Ethan.
 */
    public int getTotalBirthsRankedHigher(int year,String name,String gender)
    {
        int currentrank=0;
        int totalBirths=0;

        int rank=+getRank(year,name,gender);

        System.out.println("select file yob"+year+".csv");

        FileResource fileResource=new FileResource();
        for(CSVRecord record:fileResource.getCSVParser(false))
        {
            if(record.get(1).equals(gender))
                currentrank++;
            if(record.get(1).equals(gender) && currentrank<rank)
            {

                totalBirths+=Integer.parseInt(record.get(2));
            }
        }

        return totalBirths;
    }

    //method to test getTotalBirthsRankedHigher()
    public void testGetTotalBirthsRankedHigher()
    {
        int totalBirths=+getTotalBirthsRankedHigher(1902,"Willie","F");
        System.out.println(totalBirths);
    }



    public static void main(String args[])
    {
        BabyBirths births=new BabyBirths();
        births.totalBirths();

        births.testGetRank();

        births.testGetName();

        births.whatIsNameInYear("Isabella",2012,2014,"F");

        births.testYearOfHighestRank();

        births.testAverageRank();

        births.testGetTotalBirthsRankedHigher();

    }

}
