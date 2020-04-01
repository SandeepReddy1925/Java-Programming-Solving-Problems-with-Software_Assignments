import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;

/*
 * Write a method named coldestHourInFile that has one parameter, a CSVParser named parser.
 * This method returns the CSVRecord with the coldest temperature in the file and
 * thus all the information about the coldest temperature, such as the hour of the coldest temperature.
 */
public class CSVWeather {

    public CSVRecord coldestHourInFile(CSVParser parser) {

        CSVRecord lowestSoFar = null;

        for (CSVRecord currentRecord : parser)
        {
            lowestSoFar = lowestOfTwo(currentRecord, lowestSoFar);
        }

        return lowestSoFar;

    }

    //method to get lowest of Two values and return lowest value record
    public CSVRecord lowestOfTwo(CSVRecord currentRecord, CSVRecord lowestSoFar) {

        if (lowestSoFar == null)
            lowestSoFar = currentRecord;

        else {

            double currentTemp = Double.parseDouble(currentRecord.get("TemperatureF"));
            double lowestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));

            if (currentTemp != -9999 && currentTemp < lowestTemp)
                lowestSoFar = currentRecord;

        }

        return lowestSoFar;

    }

    /*
     * Write the method fileWithColdestTemperature that has no parameters.
     * This method should return a string that is the name of the file from
     * selected files that has the coldest temperature.
     */
    public String fileWithColdestTemperature() {

        String filename = "";

        CSVRecord coldestSoFar = null;

        DirectoryResource dr = new DirectoryResource();

        for (File file : dr.selectedFiles()) {

            FileResource fileResource = new FileResource(file);
            CSVRecord currentRecord = coldestHourInFile(fileResource.getCSVParser());

            if (coldestSoFar == null)
                coldestSoFar = currentRecord;

            else {

                double currentTemp = Double.parseDouble(currentRecord.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));

                if (currentTemp != -9999 && currentTemp < lowestTemp)
                    coldestSoFar = currentRecord;

                filename = file.getName();
            }

        }

        return filename;

    }

    /*
     * Write a method named lowestHumidityInFile that has one parameter, a CSVParser named parser.
     * This method returns the CSVRecord that has the lowest humidity.
     * If there is a tie, then return the first such record that was found.
     */
    public CSVRecord lowestHumidityInFile(CSVParser parser) {

        CSVRecord lowestHumidSoFar = null;

        for (CSVRecord currentRecord : parser)
        {
            lowestHumidSoFar = lowestOfTwoHumid(currentRecord, lowestHumidSoFar);
        }

        return lowestHumidSoFar;

    }

   //method to get lowest of Two humid values and return record with lowest humid value;
    public CSVRecord lowestOfTwoHumid(CSVRecord currentRecord, CSVRecord lowestSoFar) {

        if (lowestSoFar == null)
            lowestSoFar = currentRecord;

        else {

            String currentHumidity = currentRecord.get("Humidity");

            if (!(currentHumidity.equals("N/A"))) {
                Double currentHumid = Double.parseDouble(currentRecord.get("Humidity"));
                Double lowestHumid = Double.parseDouble(lowestSoFar.get("Humidity"));

                if (currentHumid < lowestHumid)
                    lowestSoFar = currentRecord;

            }

        }

        return lowestSoFar;
    }


    /*
     * Write the method lowestHumidityInManyFiles that has no parameters.
     * This method returns a CSVRecord that has the lowest humidity over all the files.
     * If there is a tie, then return the first such record that was found.
     */
    public CSVRecord lowestHumidityInManyFiles() {

        CSVRecord lowestHumiditySofar = null;

        DirectoryResource dr = new DirectoryResource();

        for (File file : dr.selectedFiles())
        {
            FileResource fileResource = new FileResource(file);

            CSVRecord currentHumidity = lowestHumidityInFile(fileResource.getCSVParser());

            lowestHumiditySofar = lowestOfTwoHumid(currentHumidity,lowestHumiditySofar);

        }

        return lowestHumiditySofar;

    }


    /*
     * Write the method averageTemperatureInFile that has one parameter, a CSVParser named parser.
     * This method returns a double that represents the average temperature in the file.
     */
    public double averageTemperatureInFile(CSVParser parser)
    {
        double averageTemperature=0.0;
        double count=0;
        double totalTemperature=0.0;

        for(CSVRecord record:parser)
        {
            if(record.get("TemperatureF")=="-9999")
                continue;

            totalTemperature+=Double.parseDouble(record.get("TemperatureF"));
            count++;
        }

        averageTemperature=totalTemperature/count;

        return averageTemperature;
    }


    /*
     * Write the method averageTemperatureWithHighHumidityInFile that has two parameters, a CSVParser named parser
     * and an integer named value. This method returns a double that represents the average temperature of only those
     * temperatures when the humidity was greater than or equal to value.
     */
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value)
    {
        double count=0;
        double totalTemperature=0.0;

        for(CSVRecord record:parser) {

            if (record.get("TemperatureF") == "-9999")
                continue;

            else if (Integer.parseInt(record.get("Humidity")) >= value)
            {
                totalTemperature += Double.parseDouble(record.get("TemperatureF"));
                count++;
            }

        }

        if(totalTemperature>0.0)
        {
            double averageTemperature = totalTemperature / count;
            return averageTemperature;
        }

        else
        {
            return totalTemperature;
        }

    }

 //method to test averageTemperatureWithHighHumidityInFile();
    public void testAverageTemperatureWithHighHumidityInFile()
    {
        FileResource file=new FileResource();
        CSVParser parser=file.getCSVParser();

        double avgTempwithHighHum=+averageTemperatureWithHighHumidityInFile(parser,80);

        if(avgTempwithHighHum==0.0)
            System.out.println("No temperatures with that humidity");

        else
            System.out.println("Average Temp when high Humidity is "+avgTempwithHighHum);

    }


    //method to test averageTemperatureInFile();
    public void testAverageTemperatureInFile()
    {
        FileResource file=new FileResource();

        CSVParser parser=file.getCSVParser();

        System.out.println("Average temperatur in file is "+averageTemperatureInFile(parser));

    }

    //method to test lowestHumidityInManyFiles();
    public void testLowestHumidityInManyFiles()
    {
        CSVRecord lowestHumidity=lowestHumidityInManyFiles();

        System.out.println("lowest humidity in file is "
                                                      +lowestHumidity.get("Humidity") +"  occured at "

                                                                                +lowestHumidity.get("DateUTC"));
    }


    //method to test lowestHumidityInFile();
    public void testLowestHumidityInFile()
    {

        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestHumidity = lowestHumidityInFile(parser);

        System.out.println("lowest humidity in file is "
                                                +lowestHumidity.get("Humidity")+"  occured at" +

                                                                      " "+lowestHumidity.get("DateUTC"));
    }

    //method to test fileWithColdestTemperature()
    public void testFileWithColdestTemperature()
    {
        String coldestfilename=fileWithColdestTemperature();
        System.out.println("coldest day was on file "+coldestfilename);

        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();

        CSVRecord coldestTempRecord=coldestHourInFile(parser);


        System.out.println("coldest temperature on that day was "+coldestTempRecord.get("TemperatureF"));

        System.out.println("All the temperatures on that day are ");
        parser=fr.getCSVParser();
        for(CSVRecord record:parser)
        {
            System.out.println(record.get("DateUTC")+"  "+record.get("TemperatureF") );
        }

    }


    //method to test coldestHourInFile()
    public void testColdestHourInFile ()
    {
        FileResource file = new FileResource();
        CSVParser parser = file.getCSVParser();
        CSVRecord coldestRecord = coldestHourInFile(parser);

        System.out.println("lowest temperature in file is " + coldestRecord.get("TemperatureF")
                                                             + "  occuers at hour " + coldestRecord.get("TimeEST"));


    }



    public static void main (String args[])
    {
        CSVWeather csv= new CSVWeather();

        csv.testColdestHourInFile();

        csv.testFileWithColdestTemperature();

        csv.testLowestHumidityInFile();

        csv.testLowestHumidityInManyFiles();

        csv.testAverageTemperatureInFile();

        csv.testAverageTemperatureWithHighHumidityInFile();
    }
}
