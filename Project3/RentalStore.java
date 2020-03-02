import java.util.*;

public class RentalStore extends Observable {
    int day;
    float day_amnt,total_amnt;
    Map<String, List<Car>> cars;
    List<customerRecord> customers;
    List<customerRecord> activeRecords;
    public RentalStore (int day) //Initial setup : instantiate cars and customers
    {
        this.day = day;
        this.day_amnt = 0;
        this.total_amnt=0;
        this.cars = new HashMap<String, List<Car>>();
        this.activeRecords = new ArrayList<customerRecord>();
        //Instantiate cars using factory
        this.setupCars();
        this.customers = new ArrayList<customerRecord>();
        this.setupCustomers();//Instantiating customers
    }
    public int randomGenerator(int min,int max)
    {
        Random rand = new Random(); 
        int r = rand.nextInt((max - min) + 1) + min;
        return r;
    }
    public void setupCars()
    {
        String[] carType = new String[]{"Economy", "Luxury", "Standard","Suv","Minivan"};
        for (String item : carType) {
            if (!this.cars.containsKey(item)) {
                this.cars.put(item, new ArrayList<Car>());
            }
        }
        CarFactory carFactory = new CarFactory();
        Car car;
        for (int i = 1; i <= 24; i++) {
            if(i<=10)
                car=carFactory.create(carType[(i-1)%5]);
            else {
                int r = this.randomGenerator(0,(carType.length - 1)); //Randomizing the car's type
                car = carFactory.create(carType[r]);
            }
            this.cars.get(car.getClass().getName()).add(car);
        }
    }
    public void setupCustomers() {
        String[] customerType = new String[]{"Casual", "Regular", "Business"};
        String[] carType = new String[]{"Economy", "Luxury", "Standard","Suv","Minivan"};
        Random rand = new Random(); 
        int r,count;
        customerRecord customer;
        for (int i = 1; i <= 12; i++) {
            r = this.randomGenerator(0,2);
            customer=new customerRecord("C"+Integer.toString(i),customerType[r]);
            switch (customerType[r])
            {
            case "Casual": // 1 car for 1 to 3 nights
            {
                count=0;
                while(count<5)
                {
                    r = this.randomGenerator(0,4); //Randomizing the car's type
                    if (this.cars.get(carType[r]).size()>0)
                    {
                        Car car=this.cars.get(carType[r]).get(0);
                        this.cars.get(carType[r]).remove(car);
                        customer.cars_rented.add(car);
                        car.day_rented=0;
                        car.day_due=this.randomGenerator(1,3);
                        total_amnt+=car.cost();
                        break;
                    }
                     
                    count++;
                }
                break;
            }
            case "Regular": // 1-3 cars for 3 to 5 nights
            {
                count=0;
                while(count<5)
                {
                    r = this.randomGenerator(0,4);//Randomizing the car's type
                    if (this.cars.get(carType[r]).size()>0)
                    {
                        Car car=this.cars.get(carType[r]).get(0);
                        this.cars.get(carType[r]).remove(car);
                        customer.cars_rented.add(car);
                        car.day_rented=0;
                        car.day_due=this.randomGenerator(3,5);
                        total_amnt+=car.cost();
                        break;
                    }
                    count++;
                }
                break;
            }
            case "Business": // 3 cars for 7 nights
            {
                for(int j=0;j<3;j++)
                {
                    count=0;
                    while(count<5)
                    {
                        r =this.randomGenerator(0,4); //Randomizing the car's type
                        if (this.cars.get(carType[r]).size()>0)
                        {
                            Car car=this.cars.get(carType[r]).get(0);
                            this.cars.get(carType[r]).remove(car);
                            customer.cars_rented.add(car);
                            car.day_rented=0;
                            car.day_due=7;
                            total_amnt+=car.cost();
                            break;
                        }
                        count++;
                    }
                }
                break;
            }
        }
        this.activeRecords.add(customer);
        this.customers.add(customer);
        }
    }
    public void returns(int day)
    {
        CarFactory carFactory = new CarFactory();
        List<customerRecord> remove_customers = new ArrayList<customerRecord>();
        for(int i=0;i<this.activeRecords.size();i++)
        {
            List<Car> remove_cars = new ArrayList<Car>();
            for(int j=0;j<activeRecords.get(i).cars_rented.size();j++)
            {
                Car car=activeRecords.get(i).cars_rented.get(j);
                if (car.day_due==day)
                {
                    remove_cars.add(car);
                }
            }
            for(Car car:remove_cars)
            {
                activeRecords.get(i).cars_rented.remove(car);
                setChanged();
                notifyObservers("Customer "+activeRecords.get(i).name+" returned "+car.ctype+" car with license plate "+car.license_no);
                String lno=car.license_no;
                String type=car.ctype;
                car=carFactory.create(type);
                car.license_no=lno;
                this.cars.get(type).add(car); //Currently available
            }
            if (activeRecords.get(i).cars_rented.size()==0)
                remove_customers.add(activeRecords.get(i));
        }
        for(customerRecord c:remove_customers)
        {
            this.activeRecords.remove(c);  
        }
    }
    public static void main(String[] args)  {

        RentalStore rentalStore = new RentalStore(0);
        for(int i=0;i<rentalStore.activeRecords.size();i++)
        {
            customerRecord c=rentalStore.activeRecords.get(i);
            System.out.println(c.name+" , "+c.type);
            for(int j=0;j<c.cars_rented.size();j++)
            {
                System.out.println(c.cars_rented.get(j).license_no+" , "+c.cars_rented.get(j).day_due+" , "+c.cars_rented.get(j).cost());
            }
        }       
        // Setting up the observer
        observer o=new observer();
        rentalStore.addObserver(o);
        for(int day=1;day<=35;day++)
        {
            rentalStore.day=day;
            rentalStore.returns(day);
            System.out.println(day+" , "+rentalStore.activeRecords.size());
        }
    }
}