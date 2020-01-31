import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
public class ZooKeeper {
    String name;
    public ZooKeeper(String name)
    {
        this.name=name;
    }
    public void wakeup(Animal[] animals)
    {
        for(int i=0;i<animals.length;i++)
        {
            System.out.println("Zookeeper : Waking up "+animals[i].name+" the "+animals[i].getClass().getName()+".");
            animals[i].wakeUp();
        }

    }
    public void rollCall(Animal[] animals)
    {
        for(int i=0;i<animals.length;i++)
        {
            System.out.println("Zookeeper : Calling "+animals[i].name+" the "+animals[i].getClass().getName()+"!!!");
            if(!animals[i].getClass().getName().equals("Giraffe"))
            {
                animals[i].makeNoise();
            }
            else
            {
                animals[i].randomBehavior(1);
            }
        }
    }
    public void feed(Animal[] animals)
    {
        for(int i=0;i<animals.length;i++)
        {
            System.out.println("Zookeeper : Feeding "+animals[i].name+" the "+animals[i].getClass().getName()+"!!!");
            if(!animals[i].getClass().getName().equals("Giraffe"))
            {
                animals[i].feed();
            }
            else
            {
                animals[i].randomBehavior(2);
            }
        }
    }
    public void exercise(Animal[] animals)
    {
        for(int i=0;i<animals.length;i++)
        {
            System.out.println("Zookeeper : Making "+animals[i].name+" the "+animals[i].getClass().getName()+" exercise.");
            if(!animals[i].getClass().getName().equals("Giraffe"))
            {
                animals[i].roam();
            }
            else
            {
                animals[i].randomBehavior(3);
            }
        }

    }
    public void shutDown(Animal[] animals)
    {
        for(int i =0;i<animals.length;i++)
        {
            System.out.println("Zookeeper : Putting "+animals[i].name+" the "+animals[i].getClass().getName()+" to sleep.");
            if(!animals[i].getClass().getName().equals("Giraffe"))
            {
                animals[i].sleep();
            }
            else
            {
                animals[i].randomBehavior(4);
            }
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        // Initializing Animals
        Animal[] animals = new Animal[12];
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
        /*
        for (int i=0;i<12;i++)
            System.out.println(animals[i].name+"\t"+animals[i].getClass());

         */
        PrintStream o = new PrintStream(new File("dayatthezoo.out"));
        System.setOut(o);

        ZooKeeper zooKeeper = new ZooKeeper("Me");
        zooKeeper.wakeup(animals);
        System.out.println("----------------------------");
        zooKeeper.rollCall(animals);
        System.out.println("----------------------------");
        zooKeeper.feed(animals);
        System.out.println("----------------------------");
        zooKeeper.exercise(animals);
        System.out.println("----------------------------");
        zooKeeper.shutDown(animals);
    }
}
