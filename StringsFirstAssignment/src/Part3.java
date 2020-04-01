public class Part3 {

    /*
     * Write the method named twoOccurrences that has two String parameters named stringa and stringb.
     * This method returns true if stringa appears at least twice in stringb, otherwise it returns false.
     */
    public boolean twoOccurrences(String stringa,String stringb)
    {
        int index=0;
        int count=0;

        while(true)
        {
            index=stringb.indexOf(stringa,index);

            if(index!=-1)
            {
                count++;
                index+=stringa.length();

                if(count>=2)
                    return true;

            }
            else
                break;

        }

        return false;

    }


    /**
     * Write the method named lastPart that has two String parameters named stringa and stringb.
     * This method finds the first occurrence of stringa in stringb, and returns the part of stringb that follows stringa.
     * If stringa does not occur in stringb, then return stringb.
     */
    public String lastPart(String stringa,String stringb)
    {
        int index=stringb.indexOf(stringa);

        if(index!=-1)
        {
            return stringb.substring(index+stringa.length(),stringb.length());
        }
        else
        {
            return stringb;
        }
    }
    public void testing()
    {
        String string1="sandy";
        String string2="have u met sandy, sandy asked you";
        System.out.println("two occurences of\n"
                                         + string1+ " \nin\n"+string2+
                                                        "\nis==> "+twoOccurrences(string1,string2));

        string1="sandy";
        string2="have u met sandy,he asked you";
        System.out.println("two occurences of \n"
                                         + string1 + " \nin\n"+string2+
                                                        "\nis ==> "+twoOccurrences(string1,string2) );

        System.out.println("last part is ==> "+lastPart(string1,string2));

        string1="vamshi";

        System.out.println("last part is ==> "+lastPart(string1,string2));
    }

    public static void main(String args[])
    {
        Part3 test1=new Part3();
        test1.testing();
    }
}
