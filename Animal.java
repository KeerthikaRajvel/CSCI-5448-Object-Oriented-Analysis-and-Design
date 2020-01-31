import java.util.Random;

public class Animal { // Base Class
 public String name;
 public Animal(String name)
 {
     this.name=name;
 }
 public void wakeUp()
 {
     System.out.println(this.name+" is waking up.");
 }
 //Functions that would be overridden by the SubClasses
 public void makeNoise(){}
 public void feed(){}
 public void roam(){}

 public void sleep()
 {
     System.out.println(this.name+" is going to sleep Zzzz.");
 }
}
class Herbivore extends Animal //Inherits the Class Animal
{
    public Herbivore(String name) {
        super(name);
    }
    public void feed()
    {
        System.out.println(this.name+" is eating greens.");
    }
}
class Giraffe extends Herbivore //Inherits the Class Herbivore
{
    public Giraffe(String name) {
        super(name);
    }
    public void makeNoise()
    {
        Random rand=new Random(); // adapted from https://mkyong.com/java/java-generate-random-integers-in-a-range/
        int r=rand.nextInt((4 - 1) + 1) + 1; //Randomizing the Giraffe's response
        switch (r)
        {
            case 1: this.makeNoise(r);break;
            case 2: System.out.println("*** Misbehavior ***");super.feed();break;
            case 3: System.out.println("*** Misbehavior ***");this.roam(r);break;
            case 4: System.out.println("*** Misbehavior ***");super.sleep();break;
        }
    }
    public void feed()
    {
        Random rand=new Random();
        int r=rand.nextInt((4 - 1) + 1) + 1; //Randomizing the Giraffe's response
        switch (r)
        {
            case 1: System.out.println("*** Misbehavior ***");this.makeNoise(r);break;
            case 2: super.feed();break;
            case 3: System.out.println("*** Misbehavior ***");this.roam(r);break;
            case 4: System.out.println("*** Misbehavior ***");super.sleep();break;
        }
    }
    public void roam()
    {
        Random rand=new Random();
        int r=rand.nextInt((4 - 1) + 1) + 1; //Randomizing the Giraffe's response
        switch (r)
        {
            case 1: System.out.println("*** Misbehavior ***");this.makeNoise(r);break;
            case 2: System.out.println("*** Misbehavior ***");super.feed();break;
            case 3: this.roam(r);break;
            case 4: System.out.println("*** Misbehavior ***");super.sleep();break;
        }
    }
    public void sleep()
    {
        Random rand=new Random();
        int r=rand.nextInt((4 - 1) + 1) + 1; //Randomizing the Giraffe's response
        switch (r)
        {
            case 1: System.out.println("*** Misbehavior ***");this.makeNoise(r);break;
            case 2: System.out.println("*** Misbehavior ***");super.feed();break;
            case 3: System.out.println("*** Misbehavior ***");this.roam(r);break;
            case 4: super.sleep();break;
        }
    }
    public void makeNoise(int flag) //Method Overloading
    {
        System.out.println(this.name+" : I am a Giraffeee.");
    }
    public void roam(int flag) //Method Overloading
    {
        System.out.println(this.name+" walks.");
    }
}
class Goat extends Herbivore  //Inherits the Class Herbivore
{
    public Goat(String name) {
        super(name);
    }
    public void makeNoise()
    {
        System.out.println(this.name+" : Bahhhhhh.");
    }
    public void roam()
    {
        System.out.println(this.name+" grazes.");
    }
}
class Carnivore extends Animal  //Inherits the Class Animal
{
    public Carnivore(String name) {
        super(name);
    }
    public void feed()
    {
        System.out.println(this.name+" is eating meat.");
    }
}
class Crocodile extends Carnivore //Inherits the Class Carnivore
{
    public Crocodile(String name) {
        super(name);
    }
    public void makeNoise()
    {
        System.out.println(this.name+" : I am a Crocodileee.");
    }
    public void roam()
    {
        System.out.println(this.name+" swims.");
    }
}
class Lion extends Carnivore //Inherits the Class Carnivore
{
    public Lion(String name) {
        super(name);
    }
    public void makeNoise()
    {
        System.out.println(this.name+" : Roars.");
    }
    public void roam()
    {
        System.out.println(this.name+" runs.");
    }
}
class Omnivore extends Animal  //Inherits the Class Animal
{
    public Omnivore(String name) {
        super(name);
    }
    public void feed()
    {
        System.out.println(this.name+" is eating greens and meat.");
    }
}
class Bear extends Omnivore //Inherits the Class Omnivore
{
    public Bear(String name) {
        super(name);
    }
    public void makeNoise()
    {
        System.out.println(this.name+" : Brrrrrr.");
    }
    public void roam()
    {
        System.out.println(this.name+" moves.");
    }
}
class Pig extends Omnivore  //Inherits the Class Omnivore
{
    public Pig(String name) {
        super(name);
    }
    public void makeNoise()
    {
        System.out.println(this.name+" : Oink Oink.");
    }
    public void roam()
    {
        System.out.println(this.name+" jumps.");
    }
}
