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
        if(car.getType() == "Economy") {
            cost += 5;
        }
        if(car.getType() == "Standard") {
            cost += 5.5;
        }
        if(car.getType() == "Luxury") {
            cost += 10;
        }
        if(car.getType() == "Suv") {
            cost += 7;
        }
        if(car.getType() == "Minivan") {
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
        if(car.getType() == "Economy") {
            cost += 10;
        }
        if(car.getType() == "Standard") {
            cost += 12;
        }
        if(car.getType() == "Luxury") {
            cost += 17;
        }
        if(car.getType() == "Suv") {
            cost += 13;
        }
        if(car.getType() == "Minivan") {
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
        if(car.getType() == "Economy") {
            cost += 15;
        }
        if(car.getType() == "Standard") {
            cost += 18;
        }
        if(car.getType() == "Luxury"){
            cost += 20;
        }
        if(car.getType() == "Suv") {
            cost += 13;
        }
        if(car.getType() == "Minivan") {
            cost += 14;
        }
        return cost;

    }
}
