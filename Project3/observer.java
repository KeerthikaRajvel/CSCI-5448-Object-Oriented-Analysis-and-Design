import java.util.Observable;
import java.util.Observer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class observer implements Observer {
    private String Update;
    public observer()
    {
        //Redirecting System.out.println() output to a file.

//        PrintStream o = new PrintStream(new File("carSimulator.out")); //adapted from https://www.geeksforgeeks.org/redirecting-system-out-println-output-to-a-file-in-java/
//        System.setOut(o);
    }
    @Override
    public void update(Observable o, Object arg) {
        System.out.println((String)arg);
    }
}
