//package trial;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

//CARS CLASS
public abstract class Cars {
  String license_no;
  //public enum Ctype {UNK, ECO, LUX, STD, SUV, MINI};
  String ctype = "UNK";
  //Ctype ctype = Ctype.UNK;
  int day_rented = 0
  int day_due = 0;
  static int total_cars;
  int no_childseats = 4, no_gps = 1 , no_satradio = 1;
  public void setType(String ctype) {
    this.ctype = ctype;
  }
  public String getType() {
    return this.ctype;
  }
  public abstract float cost();
}

//DECORATOR CLASS
abstract class CarsDecorator extends Cars {
  public Cars car;
  //public abstract String getdesc();
  public String getType() {
    return car.getType();
  }

}

//SUBCLASSES OF CARS
class Economy extends Cars {
  static int total_eco_cars;
  /*public Economy(){
    carType = "Economy";
  }*/
  public float cost( ) {
    return (day_due-day_rented)*20;
  }
}
class Standard extends Cars {
  static int total_std_cars;
  /*public Standard(){
    carType = "Standard";
  }*/
  public float cost( ) {
    return (day_due-day_rented)*30;
  }
}
class Luxury extends Cars {
  static int total_lux_cars;
  /*public Luxury(){
    carType = "Luxury";
  }*/
  public float cost( ) {
    return (day_due-day_rented)*70;
  }
}
class Suv extends Cars {
  static int total_suv_cars;
  /*public Suv(){
    carType = "Suv";
  }*/
  public float cost( ) {
    return (day_due-day_rented)*60;
  }
}
class Minivan extends Cars {
  static int total_mini_cars;
  /*public Minivan(){
    carType = "Minivan";
  }*/
  public float cost( ) {
    return (day_due-day_rented)*25;
  }
}

//SUBCLASSES OF DECORATORS
class ChildSeat extends CarsDecorator {
  //Cars car;
  public ChildSeat (Cars car) {
    this.car = car;
  }
  public float cost() {
    float cost = car.cost();
    //System.out.println("ChildSeat "+car.cost());
    //return cost+5;

    if(car.getType() == "ECO") {
      //System.out.println("Hiii");
      cost += 5;
    }
    if(car.getType() == "STD") {
      cost += 5.5;
    }
    if(car.getType() == "LUX") {
      cost += 10;
    }
    if(car.getType() == "SUV") {
      cost += 7;
    }
    if(car.getType() == "MINI") {
      cost += 6;
    }
    return cost;
  }

}
class Gps extends CarsDecorator {
  //Cars car;
  public Gps(Cars car) {
    this.car = car;
  }
  public float cost() {
    float cost = car.cost();
    //System.out.println("GPS "+car.cost());
    //return cost+10;

    if(car.getType() == "ECO") {
      cost += 10;
    }
    if(car.getType() == "STD") {
      cost += 12;
    }
    if(car.getType() == "LUX") {
      cost += 17;
    }
    if(car.getType() == "SUV") {
      cost += 13;
    }
    if(car.getType() == "MINI") {
      cost += 15;
    }
    return cost;
  }

}
class SatRadio extends CarsDecorator {
  Cars car;
  public SatRadio(Cars car) {
    this.car = car;
  }
  public float cost() {
    float cost = car.cost();
    //System.out.println("SatRadio "+car.cost());
    //return cost+15;

    if(car.getType() == "ECO") {
      cost += 15;
    }
    if(car.getType() == "STD") {
      cost += 18;
    }
    if(car.getType() == "LUX"){
      cost += 20;
    }
    if(car.getType() == "SUV") {
      cost += 13;
    }
    if(car.getType() == "MINI") {
      cost += 14;
    }
    return cost;

  }
}
