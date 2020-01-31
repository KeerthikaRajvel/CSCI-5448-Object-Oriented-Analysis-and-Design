import java.util.Random;

public class Animal {
 public String name;
 public Animal(String name)
 {
     this.name=name;
 }
 public void wakeUp()
 {
     System.out.println(this.name+" is waking up.");
 }
 public void makeNoise()
 {
 }
 public void feed()
 {
 }
 public void roam()
 {
 }
 public void randomBehavior(int flag) //Only for the Subclass Giraffe
 {

 }
 public void sleep()
 {
     System.out.println(this.name+" is going to sleep Zzzz.");
 }
}
class Herbivore extends Animal
{

    public Herbivore(String name) {
        super(name);
    }
    public void feed()
    {
        System.out.println(this.name+" is eating greens.");
    }
}
class Giraffe extends Herbivore {

    public Giraffe(String name) {
        super(name);
    }
    public void randomBehavior(int flag)
    {
        Random rand=new Random();
        int r=rand.nextInt((4 - 1) + 1) + 1;
        if (r!=flag)
            System.out.println("*** Misbehavior ***");
        switch (r)
        {
            case 1: this.makeNoise();break;
            case 2: this.feed();break;
            case 3: this.roam();break;
            case 4: this.sleep();break;
        }
    }
    public void makeNoise()
    {
        System.out.println("I am a Giraffeee.");
    }
    public void roam()
    {
        System.out.println(this.name+" walks.");
    }
}
class Goat extends Herbivore {

    public Goat(String name) {
        super(name);
    }
    public void makeNoise()
    {
        System.out.println("Bahhhhhh.");
    }
    public void roam()
    {
        System.out.println(this.name+" grazes.");
    }
}
class Carnivore extends Animal {
    public Carnivore(String name) {
        super(name);
    }
    public void feed()
    {
        System.out.println(this.name+" is eating meat.");
    }
}
class Crocodile extends Carnivore {

    public Crocodile(String name) {
        super(name);
    }
    public void makeNoise()
    {
        System.out.println("I am a Crocodileee.");
    }
    public void roam()
    {
        System.out.println(this.name+" swims.");
    }
}
class Lion extends Carnivore {

    public Lion(String name) {
        super(name);
    }
    public void makeNoise()
    {
        System.out.println("Roars.");
    }
    public void roam()
    {
        System.out.println(this.name+" runs.");
    }
}
class Omnivore extends Animal {
    public Omnivore(String name) {
        super(name);
    }
    public void feed()
    {
        System.out.println(this.name+" is eating greens and meat.");
    }
}
class Bear extends Omnivore {

    public Bear(String name) {
        super(name);
    }
    public void makeNoise()
    {
        System.out.println("Brrrrrr.");
    }
    public void roam()
    {
        System.out.println(this.name+" moves.");
    }
}
class Pig extends Omnivore {

    public Pig(String name) {
        super(name);
    }
    public void makeNoise()
    {
        System.out.println("Oink Oink.");
    }
    public void roam()
    {
        System.out.println(this.name+" jumps.");
    }
}
