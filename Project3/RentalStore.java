import javafx.util.Pair;
import java.util.*;
import java.util.Random;

public class RentalStore extends Observable {
    int day;
    int day_amnt;
    List<customerRecord> customers;
    List<Object> cars;

    public RentalStore (int day) //Initial setup : instantiate cars and customers
    {
        this.day = day;
        this.day_amnt = 0;
        this.cars = new ArrayList<Object>();
        //Instantiate cars using factory
        this.setupCars();
        this.customers = new ArrayList<customerRecord>();
        this.setupCustomers();//Instantiating customers
    }
    public void setupCars()
    {
        String[] carType = new String[]{"Economy", "Luxury", "Standard","SUV","Minivan"};
        Random rand = new Random(); // adapted from https://mkyong.com/java/java-generate-random-integers-in-a-range/
        int r;
        CarFactory carFactory = new CarFactory();
        Car car;
        for (int i = 1; i <= 24; i++) {
            r = rand.nextInt(((carType.length-1) - 0) + 1) + 0; //Randomizing the customer's type
            car=carFactory.getType(carType[r]);
            this.cars.add(car);
        }
    }
    public void setupCustomers() {
        String[] customerType = new String[]{"Casual", "Regular", "Business"};
        Random rand = new Random(); // adapted from https://mkyong.com/java/java-generate-random-integers-in-a-range/
        int r;
        for (int i = 1; i <= 12; i++) {
            r = rand.nextInt((2 - 0) + 1) + 0; //Randomizing the customer's type
            this.customers.add(new customerRecord("C" + Integer.toString(i), customerType[r]));
        }
    }
    //Returns random customer object and type of car
    public  Pair<customerRecord, String> customerIn()
    {
        String[] carType = new String[]{"Economy", "Luxury", "Standard","SUV","Minivan"};
        Random rand = new Random(); // adapted from https://mkyong.com/java/java-generate-random-integers-in-a-range/
        int customerIndex = rand.nextInt(((this.customers.size()-1) - 0) + 1) + 0;
        int carTypeIndex = rand.nextInt(((carType.length-1) - 0) + 1) + 0;
        Pair<customerRecord,String> result =new Pair<>(this.customers.get(customerIndex),carType[carTypeIndex]);
        return result;
    }
    public static void main(String[] args)  {

        RentalStore rentalStore = new RentalStore(0);
        // Setting up the observer
        observer o=new observer();
        rentalStore.addObserver(o);
//        for(int i=0;i<rentalStore.cars.size();i++)
//        {
//            Object c= rentalStore.cars.get(i);
//            System.out.println(i);
//            System.out.println(c.getClass());
//        }
        Pair<customerRecord,String> customer;
        for(int day=1;day<=35;day++)
        {
            rentalStore.day=day;
            customer=rentalStore.customerIn();
            customerRecord c=customer.getKey();
            System.out.println("Customer "+c.name+" asking for "+customer.getValue());
            c.canRent(customer.getValue(),0);
        }
    }
}