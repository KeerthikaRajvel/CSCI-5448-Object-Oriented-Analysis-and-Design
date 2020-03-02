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
    public int getTotalCars()
    {
        String[] carType = new String[]{"Economy", "Luxury", "Standard","Suv","Minivan"};
        int total_cars=0;
        for(int j=0;j<5;j++)
        {
            total_cars += this.cars.get(carType[j]).size();
        }
       return total_cars;
    }
    public Car pickRandomCar()
    {
        String[] carType = new String[]{"Economy", "Luxury", "Standard","Suv","Minivan"};
        int car_count=0; 
        Car car=null;
        while(car_count<10)
        {
            int r = this.randomGenerator(0,4); //Randomizing the car's type
            if (this.cars.get(carType[r]).size()>0)
            {
                car=this.cars.get(carType[r]).get(0);
                break;
            }
            car_count++;
        }
        return car;

    }
    //Uses decorator to add options 
    public Car addOptions(Car car)
    {
        System.out.println("Adding options ");
        String license_temp = car.license_no;
        String ctype_temp = car.ctype;
        //Options using Decorator Pattern
        int gps = this.randomGenerator(0,1);
        int sat = this.randomGenerator(0,1);
        int childseat = this.randomGenerator(0,4);
        if(gps>0)
            car = new Gps(car);
        if(sat>0)
            car = new SatRadio(car);
        if(childseat>0)
        {
            for(int l=0;l<childseat;l++)
                car = new ChildSeat(car);    
        }
        car.license_no = license_temp;
        car.ctype = ctype_temp;
        return car;
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
            if(i<=10) //Minimum 2 cars of a type
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
        int r,n;
        customerRecord customer;
        for (int i = 1; i <= 12; i++) {
            r = this.randomGenerator(0,2);
            customer=new customerRecord("C"+Integer.toString(i),customerType[r]);
            n=1;
            if(customer.type=="Business")
                n=3;
            List<Car> cars_chosen=new ArrayList<Car>();
            for(int j=0;j<n;j++)
            {
                Car car = this.pickRandomCar();
                cars_chosen.add(car);
                this.cars.get(car.ctype).remove(car);
            }
            switch (customer.type)
            {
            case "Casual": // 1 car for 1 to 3 nights
            {
                cars_chosen.get(0).day_rented=0;
                cars_chosen.get(0).day_due=this.randomGenerator(1,3);
                customer.cars_rented.add(cars_chosen.get(0));
                total_amnt+=cars_chosen.get(0).cost();
                break;
            }
            case "Regular": // 1-3 cars for 3 to 5 nights
            {
                cars_chosen.get(0).day_rented=0;
                cars_chosen.get(0).day_due=this.randomGenerator(3,5);
                customer.cars_rented.add(cars_chosen.get(0));
                total_amnt+=cars_chosen.get(0).cost();
                break;
                   
            }
            case "Business": // 3 cars for 7 nights
            {
                for(int j=0;j<cars_chosen.size();j++)
                {
                    cars_chosen.get(j).day_rented=0;
                    cars_chosen.get(j).day_due=this.randomGenerator(3,5);
                    customer.cars_rented.add(cars_chosen.get(j));
                    total_amnt+=cars_chosen.get(j).cost();
                }
                break;
            }
        }
        this.activeRecords.add(customer);
        this.customers.add(customer);
        }
        
    }

    public  customerRecord customerIn(int day)
    {
        int newOrOld = this.randomGenerator(0,1); //New or old customer
        String[] customerType = new String[]{"Casual", "Regular", "Business"};
        boolean active = false;
        int r;
        customerRecord customer=null;
        if(newOrOld == 1) //New customer
        {
            r = this.randomGenerator(0,2); //Randomizing the customer's type
            customer=new customerRecord("C"+Integer.toString(this.customers.size()+1),customerType[r]);
            System.out.println("New Customer in : "+customer.name);
            this.customers.add(customer);
        }
        else //Existing Customer
        {
            int randCustomer = this.randomGenerator(0,this.customers.size()-1);
            customer=this.customers.get(randCustomer);
            System.out.println("Existing customer in : "+customer.name);
            //Checking if existing customer in active record
            for(int i=0;i<this.activeRecords.size();i++)
            {
                if(customer.name==this.activeRecords.get(i).name) 
                {
                    System.out.println("Active customer : "+this.activeRecords.get(i).name);
                    active = true;
                    break;
                }
            }
            //Only active regular customers having less than 3 cars can rent further
            if (active && customer.type == "Regular" && customer.cars_rented.size() < 3) 
            {           
                System.out.println("Active regular customer : "+customer.name);             
                int num_cars=0;
                if(this.getTotalCars()>1)
                    num_cars = this.randomGenerator(1,Math.min(3-customer.cars_rented.size(),this.getTotalCars()));
                else num_cars = 1;
                for(int k=0;k<num_cars;k++)
                {
                    Car car=this.pickRandomCar();
                    this.cars.get(car.ctype).remove(car);
                    car=this.addOptions(car);
                    car.day_rented=day;
                    car.day_due= day + this.randomGenerator(3,5);
                    customer.cars_rented.add(car);
                    total_amnt+=car.cost();
                }
            }

        }//Existing customer end
        if(!active) //Existing but not in active record and new customer
        {
            System.out.println("Adding "+customer.name+" in active record");
            Car car = this.pickRandomCar();
            switch (customer.type)
            {
                case "Casual": // 1 car for 1 to 3 nights
                {
                    this.cars.get(car.ctype).remove(car);
                    car=this.addOptions(car);
                    car.day_rented=day;
                    car.day_due= day + this.randomGenerator(1,3);
                    customer.cars_rented.add(car);
                    total_amnt+=car.cost();
                    break;
                }  
                case "Regular": // 1-3 cars for 3 to 5 nights
                {
                    int num_cars;
                    if(this.getTotalCars()>1)
                        num_cars = this.randomGenerator(1,Math.min(3,this.getTotalCars()));
                    else num_cars = 1;
                    for(int j=0;j<num_cars;j++)
                    {
                        car=this.pickRandomCar();
                        this.cars.get(car.ctype).remove(car);
                        this.addOptions(car);   
                        car.day_rented=day;
                        car.day_due= day + this.randomGenerator(3,5);
                        customer.cars_rented.add(car);
                        total_amnt+=car.cost();
                    }
                    break;
                }
                case "Business": // 3 cars for 7 nights
                {
                    if(this.getTotalCars()>=3)
                    {
                        for(int j=0;j<3;j++)
                        {
                            car=this.pickRandomCar();
                            this.cars.get(car.ctype).remove(car);
                            car = this.addOptions(car);    
                            car.day_rented=day;
                            car.day_due=day+7;
                            customer.cars_rented.add(car);
                            total_amnt+=car.cost();
                        }
                            
                    }
                    break;    
                }
            
            }//switch end
            this.activeRecords.add(customer);
        }//not active end

        return customer;
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
    public void printActiveRecords()
    {
        for(int i=0;i<this.activeRecords.size();i++)
        {
            customerRecord c= this.activeRecords.get(i);
            System.out.println(c.name+" , "+c.type);
            for(int j=0;j<c.cars_rented.size();j++)
            {
                System.out.println(c.cars_rented.get(j).license_no+" , "+c.cars_rented.get(j).getDescription()+" , "+c.cars_rented.get(j).day_rented+" , "+c.cars_rented.get(j).day_due+" , "+c.cars_rented.get(j).cost());
            }
        }
    }
    public static void main(String[] args)  {

        RentalStore rentalStore = new RentalStore(0);
        rentalStore.printActiveRecords();
        // Setting up the observer
        observer o=new observer();
        rentalStore.addObserver(o);
        for(int day=1;day<=35;day++)
        {
            System.out.println("******************* DAY "+day+"*******************");
            rentalStore.day=day;
            rentalStore.returns(day);
            customerRecord C = rentalStore.customerIn(day);
            System.out.println(C.name);
            rentalStore.printActiveRecords();
            // if(rentalStore.getTotalCars()>0)
            // {
            //     customerRecord c = rentalStore.customerIn(day);
            // System.out.println(c.name);
            // }
       
        }
    }
}