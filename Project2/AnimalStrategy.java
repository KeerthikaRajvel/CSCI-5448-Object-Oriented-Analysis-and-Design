import java.util.Random;

public class AnimalStrategy { // Base Class
 public String name;
 public RoamBehaviour roamBehaviour; //creating an instance variable for the interface RoamBehaviour
 public AnimalStrategy(String name)
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
 public void setRoamBehaviour(RoamBehaviour rb) { //Using the strategy pattern, can change behaviour at run time
       roamBehaviour = rb;
 }
 public void performRoam(String name) {
     roamBehaviour.roam(name);
   }

 public void sleep()
 {
     System.out.println(this.name+" is going to sleep Zzzz.");
 }
}
//----STRATEGY PATTERN-------
interface RoamBehaviour { //Behaviour Interface ---this shows the implementation of STRATEGY PATTERN
  public void roam(String name);
}
//Different kinds of behaviour that implements the generic Behaviour interface
class WalkBehaviour implements RoamBehaviour {
    public void roam(String name) {
      System.out.println(name + " : walks.");
  }
}
class SwimBehaviour implements RoamBehaviour {
  public void roam(String name) {
    System.out.println(name + " : swims.");
  }
}
class RunBehaviour implements RoamBehaviour {
  public void roam(String name) {
    System.out.println(name + " : runs.");
  }
}
//----STRATEGY PATTERN-------
//Subclasses of AnimalStrategys
class Herbivore extends AnimalStrategy //Inherits the Class Animal
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
        roamBehaviour = new WalkBehaviour();
    }
    public void makeNoise()
    {
        Random rand=new Random(); // adapted from https://mkyong.com/java/java-generate-random-integers-in-a-range/
        int r=rand.nextInt((4 - 1) + 1) + 1; //Randomizing the Giraffe's response
        switch (r)
        {
            case 1: this.makeNoise(r);break;
            case 2: System.out.println("*** Misbehavior ***");super.feed();break;
            case 3: System.out.println("*** Misbehavior ***");roamBehaviour.roam(name);break;
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
            case 3: System.out.println("*** Misbehavior ***");roamBehaviour.roam(name);break;
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
            //case 3: System.out.println("*** Misbehavior ***");this.roam(r);break;
            case 4: super.sleep();break;
        }
    }
    public void makeNoise(int flag) //Method Overloading
    {
        System.out.println(this.name+" : I am a Giraffeee.");
    }

}
class Goat extends Herbivore  //Inherits the Class Herbivore
{
    public Goat(String name) {
        super(name);
        roamBehaviour = new WalkBehaviour();
    }
    public void makeNoise()
    {
        System.out.println(this.name+" : Bahhhhhh.");
    }

}
class Carnivore extends AnimalStrategy  //Inherits the Class Animal
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
        roamBehaviour = new SwimBehaviour();
    }
    public void makeNoise()
    {
        System.out.println(this.name+" : I am a Crocodileee.");
    }

}
class Lion extends Carnivore //Inherits the Class Carnivore
{
    public Lion(String name) {
        super(name);
        roamBehaviour = new RunBehaviour();
    }
    public void makeNoise()
    {
        System.out.println(this.name+" : Roars.");
    }
}
class Omnivore extends AnimalStrategy  //Inherits the Class Animal
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
        roamBehaviour = new RunBehaviour();
    }
    public void makeNoise()
    {
        System.out.println(this.name+" : Brrrrrr.");
    }

}
class Pig extends Omnivore  //Inherits the Class Omnivore
{
    public Pig(String name) {
        super(name);
        roamBehaviour = new WalkBehaviour();
    }
    public void makeNoise()
    {
        System.out.println(this.name+" : Oink Oink.");
    }

}
