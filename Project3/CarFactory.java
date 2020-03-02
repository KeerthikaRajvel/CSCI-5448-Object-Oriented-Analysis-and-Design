

class CarFactory{
    int e = 0;
    int l = 0;
    int st = 0;
    int s = 0;
    int m = 0;
    
    public Car create(String carType){
        if(carType == null){
            return null;
        }
        if(carType.equalsIgnoreCase("Economy")){
            e++;
            Car c = new Economy();
            c.setType("Economy");
            String li = "eco" + e;
            c.set_license(li);
            return c;
        }
        else if(carType.equalsIgnoreCase("Standard")){
            st++;
            Car c = new Standard();
            c.setType("Standard");
            String li = "std" + st;
            c.set_license(li);
            return c;
        }
        else if(carType.equalsIgnoreCase("Luxury")){
            l++;
            Car c = new Luxury();
            c.setType("Luxury");
            String li = "lux" + l;
            c.set_license(li);
            return c;
        }
        else if(carType.equalsIgnoreCase("SUV")){
            s++;
            Car c = new Suv();
            c.setType("Suv");
            String li = "suv" + s;
            c.set_license(li);
            return c;
        }
        else if(carType.equalsIgnoreCase("Minivan")){
            m++;
            Car c = new Minivan();
            c.setType("Minivan");
            String li = "mini" + m;
            c.set_license(li);
            return c;
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
