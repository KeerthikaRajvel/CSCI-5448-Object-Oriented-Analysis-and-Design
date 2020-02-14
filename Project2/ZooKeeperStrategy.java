import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
public class ZooKeeperStrategy {
    String name;
    public ZooKeeperStrategy(String name) //Constructor initializing the ZooKeeper's name
    {
        this.name=name;
    }
    public void wakeup(AnimalStrategy[] animals) //ZooKeeper wakes up all the animals
    {
        for(int i=0;i<animals.length;i++)
        {
            System.out.println("Zookeeper : Waking up "+animals[i].name+" the "+animals[i].getClass().getName()+".");
            animals[i].wakeUp();
        }

    }
    public void rollCall(AnimalStrategy[] animals) //ZooKeeper roll calls all the animals
    {
        for(int i=0;i<animals.length;i++)
        {
            System.out.println("Zookeeper : Calling "+animals[i].name+" the "+animals[i].getClass().getName()+"!!!");
            animals[i].makeNoise();

        }
    }
    public void feed(AnimalStrategy[] animals) //ZooKeeper feeds all the animals
    {
        for(int i=0;i<animals.length;i++)
        {
            System.out.println("Zookeeper : Feeding "+animals[i].name+" the "+animals[i].getClass().getName()+"!!!");
            animals[i].feed();
        }
    }
    public void exercise(AnimalStrategy[] animals, String[] names) //ZooKeeper makes all the animals exercise
    {
        for(int i=0;i<animals.length;i++)
        {
            System.out.println("Zookeeper : Making "+animals[i].name+" the "+animals[i].getClass().getName()+" exercise.");
            animals[i].performRoam(names[i]);
        }

    }
    public void shutDown(AnimalStrategy[] animals) //ZooKeeper makes all the animals sleep
    {
        for(int i =0;i<animals.length;i++)
        {
            System.out.println("Zookeeper : Putting "+animals[i].name+" the "+animals[i].getClass().getName()+" to sleep.");
            animals[i].sleep();
        }
        System.out.println(" ******** Zoo shut down ******** ");
    }
    public static void main(String[] args) throws FileNotFoundException {

        // Initializing 12 Animal objects

        AnimalStrategy[] animals = new AnimalStrategy[12];
        String[] names = new String[]{"Gabi","Geno","Gal","Gag","Carl","Cassey","Lily","Lee","Ben","Boo","Pepa","Pooh"};
        for (int i=0;i<12;i+=2)
        {
            if (i<2)
            {
                animals[i]=new Giraffe(names[i]);
                animals[i+1]=new Giraffe(names[i+1]);
                continue;
            }
            if (i<4)
            {
                animals[i]=new Goat(names[i]);
                animals[i+1]=new Goat(names[i+1]);
                continue;
            }
            if (i<6)
            {
                animals[i]=new Crocodile(names[i]);
                animals[i+1]=new Crocodile(names[i+1]);
                continue;
            }
            if (i<8)
            {
                animals[i]=new Lion(names[i]);
                animals[i+1]=new Lion(names[i+1]);
                continue;
            }
            if (i<10)
            {
                animals[i]=new Bear(names[i]);
                animals[i+1]=new Bear(names[i+1]);
                continue;
            }
            if (i<12)
            {
                animals[i]=new Pig(names[i]);
                animals[i+1]=new Pig(names[i+1]);
                continue;
            }
        }

        //Redirecting System.out.println() output to a file.

        PrintStream o = new PrintStream(new File("dayatthezoo_usingStrategyPattern.out")); //adapted from https://www.geeksforgeeks.org/redirecting-system-out-println-output-to-a-file-in-java/
        System.setOut(o);

        //ZooKeeper executing each of his responsibilities in order

        ZooKeeperStrategy zooKeeper = new ZooKeeperStrategy("Me");
        zooKeeper.wakeup(animals);
        System.out.println("--------------------------------------------------------");
        zooKeeper.rollCall(animals);
        System.out.println("--------------------------------------------------------");
        zooKeeper.feed(animals);
        System.out.println("--------------------------------------------------------");
        zooKeeper.exercise(animals, names);
        System.out.println("--------------------------------------------------------");
        zooKeeper.shutDown(animals);
    }
}
