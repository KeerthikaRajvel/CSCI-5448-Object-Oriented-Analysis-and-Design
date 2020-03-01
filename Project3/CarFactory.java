

class CarFactory{
    public Car create(String carType){
        if(carType == null){
            return null;
        }
        if(carType.equalsIgnoreCase("Economy")){
            return new Economy();
        }
        else if(carType.equalsIgnoreCase("Standard")){
            return new Standard();
        }
        else if(carType.equalsIgnoreCase("Luxury")){
            return new Luxury();
        }
        else if(carType.equalsIgnoreCase("SUV")){
            return new Suv();
        }
        else if(carType.equalsIgnoreCase("Minivan")){
            return new Minivan();
        }
        return null;
    }
}

//public class FactoryPatternCar{
//    public static void main(String[] args){
//        CarFactory carFactory = new CarFactory();
//
//        Car car1 = carFactory.getType("Economy");
//        car1.create_car();
//
//        Car car2 = carFactory.getType("Standard");
//        car2.create_car();
//
//        Car car3 = carFactory.getType("Luxury");
//        car3.create_car();
//
//        Car car4 = carFactory.getType("SUV");
//        car4.create_car();
//
//        Car car5 = carFactory.getType("Minivan");
//        car5.create_car();
//
//    }
//}
