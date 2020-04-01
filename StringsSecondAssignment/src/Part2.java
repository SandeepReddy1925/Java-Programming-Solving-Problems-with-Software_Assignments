public class Part2 {
    public int howMany(String stringa,String stringb)
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
            }
            else {
                break;

            }

        }
        return count;

    }
    public void testHowMany()
    {
        String stringa="GAA";
        String stringb="ATGAACGAATTGAATC";

        int count=+howMany(stringa,stringb);
        System.out.println(count);

        stringa="AA";
        stringb="ATAAAA";

        count=+howMany(stringa,stringb);
        System.out.println(count);
    }


    public static void main(String args[])
    {
        Part2 howmany=new Part2();
        howmany.testHowMany();
    }
}
