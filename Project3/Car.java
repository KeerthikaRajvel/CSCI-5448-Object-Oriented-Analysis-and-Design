//package trial;
import java.util.*;

//CAR CLASS
public abstract class Car {
    String license_no, ctype, description;
    int day_rented  , day_due;

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


//SUBCLASSES OF CAR
class Economy extends Car {
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
    public Minivan(){
        super();
        description = " a MINIVAN";
    }
    @Override
    public float cost( ) {
        return (day_due-day_rented)*25;
    }
}