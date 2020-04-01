import edu.duke.*;
import org.apache.commons.csv.*;

public class CSVExport {

    //  1. Write a method named tester that will create your CSVParser and
    //    call each of the methods below in parts 2, 3, 4, and 5.
    public void tester()
    {

        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();

        listExportersTwoProducts(parser,"gold","diamonds");

        parser=fr.getCSVParser();
        System.out.println("no of countries exporting gold are  "+numberOfExporters(parser,"gold"));

        parser=fr.getCSVParser();
        bigExporters(parser,"$999,999,999");

    }


   /* 2.Write a method named countryInfo that has two parameters, parser is a CSVParser and country is a String.
    This method returns a string of information about the country or returns “NOT FOUND” if there is no information.
     The format of the string returned is the country, followed by “: “, followed by a list of the countries’ exports,
    followed by “: “, followed by the countries export value.
    */
    public String countryInfo(CSVParser parser,String country)
    {
        for(CSVRecord record:parser)
        {
            String countries=record.get("Country");

            if(countries.contains(country))
            {
                return record.get("Country")+": " +record.get("Exports")+": "+record.get("Value (dollars)");
            }
        }

        return "not found";

    }


    /*.Write a void method named listExportersTwoProducts that has three parameters, parser is a CSVParser,
       exportItem1 is a String and exportItem2 is a String. This method prints the names of all the countries
       that have both exportItem1 and exportItem2 as export items.
     */
    public void listExportersTwoProducts(CSVParser parser,String exportitem1,String exportitem2)
    {
        for(CSVRecord record:parser)
        {
            String exportitems=record.get("Exports");

            if(exportitems.contains(exportitem1)&&exportitems.contains(exportitem2))
            {
                System.out.println("Countries containing the exports "+exportitem1+","+exportitem2+" are"+record.get("Country"));
            }
        }
    }


    //Write a method named numberOfExporters, which has two parameters, parser is a CSVParser, and exportItem is a String.
    // This method returns the number of countries that export exportItem.
    public int numberOfExporters(CSVParser parser,String exportItem)
    {
        int count=0;

        for(CSVRecord record:parser)
        {
            String items=record.get("Exports");

            if(items.contains(exportItem))
            {
                count++;
            }
        }

        return count;
    }


    /*5.Write a void method named bigExporters that has two parameters, parser is a CSVParser,
        and amount is a String in the format of a dollar sign, followed by an integer number
        with a comma separator every three digits from the right.
        An example of such a string might be “$400,000,000”. This method prints the names of countries
        and their Value amount for all countries whose Value (dollars) string is longer than the amount string.
        You do not need to parse either string value as an integer, just compare the lengths of the strings.
     */
    public void bigExporters(CSVParser parser,String value)
    {
        for(CSVRecord record:parser)
        {
            System.out.println("countries with export value greate than "+value+ " are:");

            String amount=record.get("Value (dollars)");

            int difference=value.compareTo(amount);

            if(difference>0)
            {
                System.out.println(record.get("Country")+" "+record.get("Value (dollars)"));
            }
        }
    }

    public static void main(String args[])
    {
        CSVExport csv=new CSVExport();
        csv.tester();

    }
}
