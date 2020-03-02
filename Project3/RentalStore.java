import java.util.*;
import java.util.Random;

public class RentalStore extends Observable {
    int day;
    int day_amnt;
    List<customerRecord> customers;
    List<Object> cars,eco_cars,lux_cars,std_cars,suv_cars,mini_cars;
    List<customerRecord> activeRecords;
    public RentalStore (int day) //Initial setup : instantiate cars a   nd customers
    {
        this.day = day;
        this.day_amnt = 0;
        // this.cars = new ArrayList<Object>();
        this.eco_cars = new ArrayList<Object>();
        this.lux_cars = new ArrayList<Object>();
        this.std_cars = new ArrayList<Object>();
        this.suv_cars = new ArrayList<Object>();
        this.mini_cars = new ArrayList<Object>();

        //Instantiate cars using factory
        this.setupCars();
        this.customers = new ArrayList<customerRecord>();
        this.setupCustomers();//Instantiating customers
    }
    public void setupCars()
    {
        String[] carType = new String[]{"ECONOMY", "LUXURY", "STANDARD","SUV","MINIVAN"};
        Random rand = new Random(); // adapted from https://mkyong.com/java/java-generate-random-integers-in-a-range/
        int r;
        CarFactory carFactory = new CarFactory();
        Car car;
        for (int i = 1; i <= 24; i++) {
            if(i<=10)
                car=carFactory.create(carType[(i-1)%5]);
            else {
                r = rand.nextInt(((carType.length - 1) - 0) + 1) + 0; //Randomizing the car's type
                car = carFactory.create(carType[r]);
            }
            switch(car.getClass().getName())
            {
                case "Economy":Economy.count++; this.eco_cars.add(car); break;
                case "Luxury":Luxury.count++; this.lux_cars.add(car); break;
                case "Standard":Standard.count++; this.std_cars.add(car); break;
                case "Suv":Suv.count++; this.suv_cars.add(car); break;
                case "Minivan":Minivan.count++; this.mini_cars.add(car); break;
            }
            this.cars.add(car);
        }
    }
    public void setupCustomers() {
        String[] customerType = new String[]{"CASUAL", "REGULAR", "BUSINESS"};
        String[] carType = new String[]{"ECONOMY", "LUXURY", "STANDARD","SUV","MINIVAN"};
        Random rand = new Random(); // adapted from https://mkyong.com/java/java-generate-random-integers-in-a-range/
        int r;
        int cr,nights;
        for (int i = 1; i <= 12; i++) {
            r = rand.nextInt((2 - 0) + 1) + 0; //Randomizing the customer's type
            switch (customerType[r])
            {
            case "CASUAL": // 1 car for 1 to 3 nights
                cr = rand.nextInt(((carType.length-1) - 0) + 1) + 0; //Randomizing the car's type
                
                cr=1;
                nights=rand.nextInt((3 - 1) + 1) + 1;
                break;
            case "REGULAR": // 1-3 cars for 3 to 5 nights
                cr=rand.nextInt((3 - 1) + 1) + 1;
                nights=rand.nextInt((5 - 3) + 1) + 3;
                break;
            case "BUSINESS": // 3 cars for 7 nights
                cr=3;
                nights=7;
                break;
            }
            this.customers.add(new customerRecord("C" + Integer.toString(i), customerType[r]));

        }
    }
    //Returns random customer object, type of car, number of cars and days
    public  Object[] customerIn()
    {
        String[] carType = new String[]{"ECONOMY", "LUXURY", "STANDARD","SUV","MINIVAN"};
        Random rand = new Random(); // adapted from https://mkyong.com/java/java-generate-random-integers-in-a-range/
        int customerIndex = rand.nextInt(((this.customers.size()-1) - 0) + 1) + 0;
        int carTypeIndex = rand.nextInt(((carType.length-1) - 0) + 1) + 0;
        customerRecord C=this.customers.get(customerIndex);
        int cars=1,nights=1;
        switch (C.type)
        {
            case "CASUAL": // 1 car for 1 to 3 nights
                cars=1;
                nights=rand.nextInt((3 - 1) + 1) + 1;
                break;
            case "REGULAR": // 1-3 cars for 3 to 5 nights
                cars=rand.nextInt((3 - 1) + 1) + 1;
                nights=rand.nextInt((5 - 3) + 1) + 3;
                break;
            case "BUSINESS": // 3 cars for 7 nights
                cars=3;
                nights=7;
                break;
        }
        return new Object[]{C,carType[carTypeIndex],cars,nights};
    }
    public void returns(int day)
    {
        for(int i=0;i<activeRecords.size();i++)
        {
            customerRecord C = activeRecords.get(i);
            for(int j=0;j<C.cars_rented.size();j++)
            {
                Car car=C.cars_rented.get(j);
                if (car.day_due==day)
                    C.cars_rented.remove(car);
                    
            }
        }
    }
    public static void main(String[] args)  {

        RentalStore rentalStore = new RentalStore(0);
        // Setting up the observer
        observer o=new observer();
        rentalStore.addObserver(o);
//        System.out.println(Economy.count+" , "+Luxury.count+" , "+Standard.count+" , "+Minivan.count+" , "+Suv.count);
//        for(int i=0;i<rentalStore.cars.size();i++)
//        {
//            Object c= rentalStore.cars.get(i);
//            System.out.println(i);
//            System.out.println(c.getClass());
//        }
        Object[] customerRequest;
        customerRecord C;
        for(int day=1;day<=35;day++)
        {
            rentalStore.day=day;
            rentalStore.returns(day);
//             customerRequest=rentalStore.customerIn();
//             C=(customerRecord)customerRequest[0];
//             System.out.println(C.name+" , "+C.type+" , "+customerRequest[1]+" , "+customerRequest[2]+" , "+customerRequest[3]);
// //          c.canRent(customer.getValue(),0,0);
        }
    }
}