//package trial;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

//CARS CLASS
public abstract class Car {
    String license_no, ctype = "UNK", description;
    int day_rented = 2 , day_due = 5, no_childseats , no_gps , no_satradio;
    static int total_cars;

    void create_car(){
        System.out.println("Overall");
    }
    public String getDescription() {
        return description;
    }
    public void setType(String ctype) {
        this.ctype = ctype;
    }
    public String getType() {
        return this.ctype;
    }
    public abstract float cost();
}


//SUBCLASSES OF CARS
class Economy extends Car {
    static int count=0;
    public Economy(){
        description = " an ECONOMY car with the following options : \n";
    }
    @Override
    public void create_car(){
        System.out.println("Inside Economy");
    }
    @Override
    public float cost( ) {
        return (day_due-day_rented)*20;
    }
}
class Standard extends Car {
    static int count=0;
    public Standard(){
        description = " a STANDARD car with the following options : \n";
    }
    @Override
    public void create_car(){
        System.out.println("Inside Standard");
    }
    @Override
    public float cost( ) {
        return (day_due-day_rented)*30;
    }
}
class Luxury extends Car {
    static int count=0;
    public Luxury(){
        description = " a LUXURY car with the following options : \n";
    }
    @Override
    public void create_car(){
        System.out.println("Inside Luxury");
    }
    @Override
    public float cost( ) {
        return (day_due-day_rented)*70;
    }
}
class Suv extends Car {
    static int count=0;
    public Suv(){
        description = " a SUV with the following options : \n";
    }
    @Override
    public void create_car(){
        System.out.println("Inside SUV");
    }
    @Override
    public float cost( ) {
        return (day_due-day_rented)*60;
    }
}
class Minivan extends Car {
    static int count=0;
    public Minivan(){
        description = " a MINIVAN with the following options : \n";
    }
    @Override
    public void create_car(){
        System.out.println("Inside Minivan");
    }
    @Override
    public float cost( ) {
        return (day_due-day_rented)*25;
    }
}