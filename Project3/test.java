public class test
{

public static void main(String[] args)
{
    String descp="abc";
    String d = descp.replace("with Childseat","");
    int childSeatCount=(descp.length() - d.length())/"with Childseat".length();
    if (childSeatCount>0)
        d+="with "+Integer.toString(childSeatCount)+" Childseat";
    System.out.println(d);
}
}