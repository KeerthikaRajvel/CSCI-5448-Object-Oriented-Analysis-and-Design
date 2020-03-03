//package trial;
import java.util.*;

//CARS CLASS
public abstract class Car {
    String license_no, ctype, description;
    int day_rented  , day_due, no_childseats , no_gps , no_satradio;
    static int total_cars;

    public Car()
    {
        this.day_rented=0;
        this.day_due=0;
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
    public void set_license(String s)
    {
        this.license_no=s;
    }
    public abstract float cost();
}


//SUBCLASSES OF CARS
class Economy extends Car {
    static int count=0;
    public Economy(){
        super();
        description = " an ECONOMY car";
    }
    @Override
    public float cost( ) {
        return (day_due-day_rented)*20;
    }
}
class Standard extends Car {
    static int count=0;
    public Standard(){
        super();
        description = " a STANDARD car";
    }
    @Override
    public float cost( ) {
        return (day_due-day_rented)*30;
    }
}
class Luxury extends Car {
    static int count=0;
    public Luxury(){
        super();
        description = " a LUXURY car";
    }
    @Override
    public float cost( ) {
        return (day_due-day_rented)*70;
    }
}
class Suv extends Car {
    static int count=0;
    public Suv(){
        super();
        description = " a SUV";
    }
    @Override
    public float cost( ) {
        return (day_due-day_rented)*60;
    }
}
class Minivan extends Car {
    static int count=0;
    public Minivan(){
        super();
        description = " a MINIVAN";
    }
    @Override
    public float cost( ) {
        return (day_due-day_rented)*25;
    }
}