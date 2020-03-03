import java.util.Observable;
import java.util.Observer;
import java.io.*; 

public class observer implements Observer {
    public observer() throws FileNotFoundException
    {
        //Redirecting System.out.println() output to a file
       File f=new File("carSimulator.out");
       PrintStream o = new PrintStream(f); //adapted from https://www.geeksforgeeks.org/redirecting-system-out-println-output-to-a-file-in-java/
       System.setOut(o);
    }
    @Override
    public void update(Observable o, Object arg) {
        System.out.println((String)arg);
    }
}
