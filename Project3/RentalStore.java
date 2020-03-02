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

    public  Object[] customerIn(int day)
    {
        Random rand = new Random();
        int checkCustomer = rand.nextInt(1+1-0)+0;
        String[] customerType = new String[]{"Casual", "Regular", "Business"};
        String[] carType = new String[]{"Economy", "Luxury", "Standard","Suv","Minivan"};
        int flag = 0;

        if(checkCustomer == 1)
        {
            customerRecord customer;
            r = rand.nextInt((2 - 0) + 1) + 0; //Randomizing the customer's type
            customer=new customerRecord("C"+Integer.toString(i),customerType[r]);
            this.customers.add(customer);
        }
        else
        {
            int count = 0;
            while(count<=12)
            {
                int randCustomer = rand.nextInt(this.customers.size()+1-0)+0;
                String c = "C"+randCustomer;
                for(i=0;i<this.activeRecords.size();i++)
                {
                    customerRecord customer = activeRecords.get(randCustomer);
                    if(customer.name == c)
                    {
                        flag = 1;
                        if(customer.type == "Regular")
                        {
                            if(customer.cars_rented.size() < 3)
                            {
                                int car_count=0;
                                int total_cars = 0;
                                for(int i=0;i<5;i++)
                                {
                                    total_cars += this.cars.get(carType[i]).size();
                                }
                                int max = (3<total_cars)?3:total_cars;
                                int num_cars = rand.nextInt(max-customer.cars_rented.size()+1-1)+1;
                                for(i=0;i<num_cars;i++)
                                {
                                    car_count = 0;
                                    while(car_count<10) 
                                    {
                                        r = rand.nextInt((4 - 0) + 1) + 0; //Randomizing the car's type
                                        if (this.cars.get(carType[r]).size()>0)
                                        {
                                            Car car=this.cars.get(carType[r]).get(0);
                                            this.cars.get(carType[r]).remove(car);
                                            int gps = rand.nextInt(1+1-0)+0;
                                            int sat = rand.nextInt(1+1-0)+0;
                                            int childseat = rand.nextInt(4+1-0)+0;
                                            if(gps)
                                            car = new Gps(car);
                                            if(sat)
                                            car = new SatRadio(car);
                                            if(childseat)
                                            {
                                                for(i=0;i<childseat;i++)
                                                    car = new childseat(car);    
                                            }
                                            customer.cars_rented.add(car);
                                            car.day_rented=day;
                                            car.day_due= day + rand.nextInt((5 - 3) + 1) + 3;
                                            total_amnt+=car.cost();
                                            break;
                                        }
                                        car_count++;
                                    }
                                }
    
                            }
                        }
                    }
                }
            }
        }
        if(flag==0)
        {
            r = rand.nextInt((2 - 0) + 1) + 0; //Randomizing the customer's type
            switch (customerType[r])
            {
                case "Casual": // 1 car for 1 to 3 nights
                {
                    car_count=0; 
                    while(car_count<10)
                    {
                        r = rand.nextInt((4 - 0) + 1) + 0; //Randomizing the car's type
                        if (this.cars.get(carType[r]).size()>0)
                        {
                            Car car=this.cars.get(carType[r]).get(0);
                            this.cars.get(carType[r]).remove(car);
                            int gps = rand.nextInt(1+1-0)+0;
                            int sat = rand.nextInt(1+1-0)+0;
                            int childseat = rand.nextInt(4+1-0)+0;
                            if(gps)
                            car = new Gps(car);
                            if(sat)
                            car = new SatRadio(car);
                            if(childseat)
                            {
                                for(i=0;i<childseat;i++)
                                    car = new childseat(car);    
                            }
                            customer.cars_rented.add(car);
                            car.day_rented=day;
                            car.day_due= day + rand.nextInt((3 - 1) + 1) + 1;
                            total_amnt+=car.cost();
                            break;
                        }
                        car_count++;
                    }break;
                }
                case "Regular": // 1-3 cars for 3 to 5 nights
                {
                    int total_cars = 0;
                    for(int i=0;i<5;i++)
                    {
                        total_cars += this.cars.get(carType[i]).size();
                    }
                    int max = (3<total_cars)?3:total_cars;
                    num_cars = rand.nextInt(max+1-1)+1;
                    for(i=0;i<num_cars;i++)
                    {
                        car_count = 0;
                        while(car_count<10)
                        {
                            r = rand.nextInt((4 - 0) + 1) + 0; //Randomizing the car's type
                            if (this.cars.get(carType[r]).size()>0)
                            {
                                Car car=this.cars.get(carType[r]).get(0);
                                this.cars.get(carType[r]).remove(car);
                                int gps = rand.nextInt(1+1-0)+0;
                                int sat = rand.nextInt(1+1-0)+0;
                                int childseat = rand.nextInt(4+1-0)+0;
                                if(gps)
                                car = new Gps(car);
                                if(sat)
                                car = new SatRadio(car);
                                if(childseat)
                                {
                                    for(i=0;i<childseat;i++)
                                        car = new childseat(car);    
                                }
                                customer.cars_rented.add(car);
                                car.day_rented=day;
                                car.day_due= day + rand.nextInt((5 - 3) + 1) + 3;
                                total_amnt+=car.cost();
                                break;
                            }
                            car_count++;
                        }
                    }break;
                        
                }

                case "Business": // 3 cars for 7 nights
                {
                    int total_cars = 0;
                    for(int i=0;i<5;i++)
                    {
                        total_cars += this.cars.get(carType[i]).size();
                    }
                    if(total_cars>=3)
                    {
                        for(int j=0;j<3;j++)
                        {
                            car_count=0;
                            while(car_count<10)
                            {
                                r = rand.nextInt((4 - 0) + 1) + 0; //Randomizing the car's type
                                if (this.cars.get(carType[r]).size()>0)
                                {
                                    Car car=this.cars.get(carType[r]).get(0);
                                    this.cars.get(carType[r]).remove(car);
                                    int gps = rand.nextInt(1+1-0)+0;
                                    int sat = rand.nextInt(1+1-0)+0;
                                    int childseat = rand.nextInt(4+1-0)+0;
                                    if(gps)
                                    car = new Gps(car);
                                    if(sat)
                                    car = new SatRadio(car);
                                    if(childseat)
                                    {
                                        for(i=0;i<childseat;i++)
                                            car = new childseat(car);    
                                    }
                                    customer.cars_rented.add(car);
                                    car.day_rented=day;
                                    car.day_due=day+7;
                                    total_amnt+=car.cost();
                                    break;                                
                                }
                                car_count++;
                            }
                            
                        }
                    }break;
                    
                        
                }


                    
            }
            this.activeRecords.add(customer);

        }
                
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
            customerRecord c = (customerRecord)rentalStore.customerIn(day);
            System.out.println(c.name);


        }
    }
}