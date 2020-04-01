import edu.duke.*;
import java.io.*;
public class Part4 {


    /*
     * For each word, check to see if “youtube.com” is in it. If it is, find the double quote to the left and
     * right of the occurrence of “youtube.com” to identify the beginning and end of the URL.
     * Note, the double quotation mark is a special character in Java. To look for a double quote, look for (\”), since the backslash (\) character indicates we want the literal quotation marks (“) and not the Java character.
     * The string you search for would be written “\”” for one double quotation mark.
     * This method returns the last match from the start of the string up to the num position, so it is a good option for
     * finding the opening quotation mark of a string searching backward from “youtube.com.”
     */
    public void readUrl()
    {
        String result="";
        String lowerCaseline="";

        URLResource url=new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");

        for(String line:url.words())
        {
            lowerCaseline=line.toLowerCase();

           int index=lowerCaseline.indexOf("youtube.com");

           if(index!=-1)
           {
               int start=lowerCaseline.indexOf("\"");
               int end=lowerCaseline.indexOf(">",start);
               result=line.substring(start,end);
               System.out.println(result);
           }
        }
    }

    public static void main(String args[])
    {
        Part4 p=new Part4();
        p.readUrl();
    }
}
