//DECORATOR CLASS
abstract class CarsDecorator extends Car {
    public Car car;
    public abstract String getDescription();
    public String getType() {
        return car.getType();
    }
}

//SUBCLASSES OF DECORATORS
class ChildSeat extends CarsDecorator {
    public ChildSeat (Car car) {
        this.car = car;
    }
    public String getDescription() {
        return car.getDescription() + "Childseat \n";
    }
    public float cost() {
        float cost = car.cost();
        if(car.getType() == "ECONOMY") {
            cost += 5;
        }
        if(car.getType() == "STANDARD") {
            cost += 5.5;
        }
        if(car.getType() == "LUXURY") {
            cost += 10;
        }
        if(car.getType() == "SUV") {
            cost += 7;
        }
        if(car.getType() == "MINIVAN") {
            cost += 6;
        }
        return cost;
    }

}
class Gps extends CarsDecorator {
    //Cars car;
    public Gps(Car car) {
        this.car = car;
    }
    public String getDescription() {
        return car.getDescription() + "GPS \n";
    }
    public float cost() {
        float cost = car.cost();
        if(car.getType() == "ECONOMY") {
            cost += 10;
        }
        if(car.getType() == "STANDARD") {
            cost += 12;
        }
        if(car.getType() == "LUXURY") {
            cost += 17;
        }
        if(car.getType() == "SUV") {
            cost += 13;
        }
        if(car.getType() == "MINIVAN") {
            cost += 15;
        }
        return cost;
    }

}
class SatRadio extends CarsDecorator {
    public SatRadio(Car car) {
        this.car = car;
    }
    public String getDescription() {
        return car.getDescription() + "Satellite Radio \n";
    }
    public float cost() {
        float cost = car.cost();
        if(car.getType() == "ECONOMY") {
            cost += 15;
        }
        if(car.getType() == "STANDARD") {
            cost += 18;
        }
        if(car.getType() == "LUXURY"){
            cost += 20;
        }
        if(car.getType() == "SUV") {
            cost += 13;
        }
        if(car.getType() == "MINIVAN") {
            cost += 14;
        }
        return cost;

    }
}