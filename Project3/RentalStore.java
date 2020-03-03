import java.io.FileNotFoundException;
import java.util.*;

public class RentalStore extends Observable {
    int day;
    float day_amnt,total_amnt;
    Map<String, List<Car>> cars;
    Map<String,Integer> completedRentals;
    List<customerRecord> customers;
    List<customerRecord> activeRecords;
    //---- CONSTRUCTOR : INITIAL SETUP - INSTANTIATE CARS AND CUSTOMERS -----
    public RentalStore (int day) 
    {
        this.day = day;
        this.day_amnt = 0;
        this.total_amnt=0;
        this.cars = new HashMap<String, List<Car>>();
        this.activeRecords = new ArrayList<customerRecord>();
        this.completedRentals=new HashMap<String,Integer>();
        this.setupCars();  //----INSTANTIATE CARS USING FACTORY ------
        this.customers = new ArrayList<customerRecord>();
        this.setupCustomers();  //----INSTANTIATIATING CUSTOMERS ------
    }
    //----RANDOM NUMBER GENERATOR ------
    public int randomGenerator(int min,int max)
    {
        Random rand = new Random(); 
        int r = rand.nextInt((max - min) + 1) + min;
        return r;
    }
    //----TOTAL CARS IN INVENTORY CALCULATOR ------
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
    //----PICKING RANDOM CAR------
    public Car pickRandomCar()
    {
        String[] carType = new String[]{"Economy", "Luxury", "Standard","Suv","Minivan"};
        int car_count=0; 
        Car car=null;
        while(car_count<10)
        {
            int r = this.randomGenerator(0,4); 
            if (this.cars.get(carType[r]).size()>0)
            {
                car=this.cars.get(carType[r]).get(0);
                break;
            }
            car_count++;
        }
        return car;

    }
    //----DECORATOR : ADDING OPTIONS TO CARS ------
    public Car addOptions(Car car)
    {
        String license_temp = car.license_no, ctype_temp = car.ctype;
        int day_due_temp = car.day_due, day_rent_temp = car.day_rented;
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
        car.day_due = day_due_temp;
        car.day_rented = day_rent_temp;
        return car;
    }
    //----INITIAL SETUP OF 24 CARS USING SIMPLE FACTORY PATTERN ------
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
                int r = this.randomGenerator(0,(carType.length - 1)); 
                car = carFactory.create(carType[r]);
            }
            this.cars.get(car.getClass().getName()).add(car);
        }
        
    }
    //----ADDING CAR TO CUSTOMER RECORDS----
    public void addingCars(int day_r, customerRecord cust)
    {
        Car car = this.pickRandomCar();
        this.cars.get(car.ctype).remove(car);
        car.day_rented=day_r;
        if(cust.type == "Regular")
            car.day_due= day_r + this.randomGenerator(3,5);
        else if(cust.type == "Casual")
            car.day_due= day_r + this.randomGenerator(1,3);
        else 
            car.day_due= day_r + 7;
        car=this.addOptions(car);
        cust.cars_rented.add(car);
        this.day_amnt+=car.cost(); 
    }

    //----INITIAL SETUP OF 12 CUSTOMERS ------
    public void setupCustomers() {
        String[] customerType = new String[]{"Casual", "Regular", "Business"};
        int r;
        customerRecord customer;
        for (int i = 1; i <= 12; i++) {
            r = this.randomGenerator(0,2);
            customer=new customerRecord("C"+Integer.toString(i),customerType[r]);
            switch (customer.type)
            {
            case "Casual": // 1 car for 1 to 3 nights
                addingCars(0, customer);
                break;
            case "Regular": // 1-3 cars for 3 to 5 nights
            {
                int num_cars;
                if(this.getTotalCars()>1)
                    num_cars = this.randomGenerator(1,Math.min(3,this.getTotalCars()));
                else num_cars = 1;
                for(int j=0;j<num_cars;j++) { 
                    addingCars(day, customer);
                } 
                break;
                   
            }
            case "Business": // 3 cars for 7 nights
            {
                if(this.getTotalCars()>=3) {
                    for(int j=0;j<3;j++)
                        { addingCars(day, customer); }
                }
                break;
            }
            }
        this.activeRecords.add(customer);
        this.customers.add(customer);
        }
        
    }


    //----EVERYDAY CUSTOMERS THAT COME TO THE SHOP ------
    public  customerRecord customerIn(int day)
    {
        int newOrOld = this.randomGenerator(0,1); 
        String[] customerType = new String[]{"Casual", "Regular", "Business"};
        boolean active = false;
        int r;
        customerRecord customer=null;
        if(newOrOld == 1) { //New customer
            r = this.randomGenerator(0,2); //Randomizing the customer's type
            customer=new customerRecord("C"+Integer.toString(this.customers.size()+1),customerType[r]);
            this.customers.add(customer);
        }
        else  { //Existing Customer
            int randCustomer = this.randomGenerator(0,this.customers.size()-1);
            customer=this.customers.get(randCustomer);
            for(int i=0;i<this.activeRecords.size();i++) { //Checking if existing customer in active record
                if(customer.name==this.activeRecords.get(i).name) {
                    active = true;
                    break;
                }
            }
            if (active && customer.type == "Regular" && customer.cars_rented.size() < 3) { //Only active regular customers having less than 3 cars can rent further      
                int num_cars;
                if(this.getTotalCars()>1)
                    num_cars = this.randomGenerator(1,Math.min(3,this.getTotalCars()));
                else num_cars = 1;
                for(int j=0;j<num_cars;j++)
                    addingCars(day, customer);
            }

        }
        if(!active) { //Existing but not in active record or new customer
            switch (customer.type) {
                case "Casual": // 1 car for 1 to 3 nights
                    addingCars(day, customer);
                    break;
                case "Regular": // 1-3 cars for 3 to 5 nights
                {
                    int num_cars;
                    if(this.getTotalCars()>1)
                        num_cars = this.randomGenerator(1,Math.min(3,this.getTotalCars()));
                    else num_cars = 1;
                    for(int j=0;j<num_cars;j++)
                        addingCars(day, customer);
                    break;
                }
                case "Business": // 3 cars for 7 nights
                {
                    if(this.getTotalCars()>=3) {
                        for(int j=0;j<3;j++) {
                            addingCars(day, customer); }
                    }
                    break;    
                }
            
            }//switch end
            this.activeRecords.add(customer);
        }
        return customer;
    }
    //----RETURNS OF THE DAY ------
    public void returns(int day)
    {
        CarFactory carFactory = new CarFactory();
        List<customerRecord> remove_customers = new ArrayList<customerRecord>();
        List<Car> remove_cars = new ArrayList<Car>();
        String s="";
        int carsRemoved=0;
        for(int i=0;i<this.activeRecords.size();i++)
        {
            remove_cars = new ArrayList<Car>();
            for(int j=0;j<activeRecords.get(i).cars_rented.size();j++)
            {
                Car car=activeRecords.get(i).cars_rented.get(j);
                if (car.day_due==day)
                {
                    remove_cars.add(car);
                    this.addCompletedRental(activeRecords.get(i).type);
                }
            }
            carsRemoved+=remove_cars.size();
            // s =remove_cars.size()+" Number of Completed rentals on Day "+Integer.toString(day)+"\n\n";
            for(Car car:remove_cars)
            {
                activeRecords.get(i).cars_rented.remove(car);
                // setChanged();
                s+="Customer "+activeRecords.get(i).name+" returned "+car.ctype+" car with license plate "+car.license_no+" , "+this.getDescription(car)+" , Day Rented : "+car.day_rented+" , Day Due : "+car.day_due+" , Total Cost : "+car.cost()+"\n";
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
        if(carsRemoved>0)
            this.alertObserver("\n--- "+carsRemoved+" Number of Completed rentals on Day "+Integer.toString(day)+" ---\n\n"+s);
    }
    //----PRINTS ACTIVE RENTALS ------
    public void printActiveRecords()
    {
        String s = "\tRENTAL STORE'S ACTIVE RECORD : Total ( "+this.activeRecords.size()+" )\n";
        for(int i=0;i<this.activeRecords.size();i++)
        {
            customerRecord c= this.activeRecords.get(i);
            s += "\nCustomer Name : "+c.name+" , Customer Type: "+c.type+"\n\nNumber of cars rented : "+c.cars_rented.size()+" \n\n";
            for(int j=0;j<c.cars_rented.size();j++)
            {
                
                s+="Car : "+c.cars_rented.get(j).license_no+" , "+this.getDescription(c.cars_rented.get(j))+" , Day Rented : "+c.cars_rented.get(j).day_rented+" , Day Due : "+c.cars_rented.get(j).day_due+" , Total Cost : "+c.cars_rented.get(j).cost()+"\n";
            }
        }
        this.alertObserver(s);
    }
    public void alertObserver(String s)
    {
        setChanged();
        notifyObservers(s);
    }
    public String getDescription(Car car)
    {
        String descp=car.getDescription();
        String d = descp.replace("with Childseat","");
        int childSeatCount=(descp.length() - d.length())/"with Childseat".length();
        if (childSeatCount>0)
            d+="with "+Integer.toString(childSeatCount)+" Childseat";
        return d;
}
    public void addCompletedRental(String customerType)
    {
        if (this.completedRentals.containsKey(customerType)) 
            this.completedRentals.put(customerType, this.completedRentals.get(customerType)+1);
        else
        this.completedRentals.put(customerType, 1);
  
    }
    public void finalStep()
    {
        String[] customerType = new String[]{"Casual", "Regular", "Business"};
        String s = "\n\n******************* After 35 Days *******************\n\n";
        String t="";
        int totalRents=0;
        for(int j=0;j<3;j++)
        {
            if (this.completedRentals.containsKey(customerType[j])) 
            {
                totalRents += this.completedRentals.get(customerType[j]);
                t+=customerType[j]+" : "+this.completedRentals.get(customerType[j])+"\n";
            }
        }
       s+="Total Completed Rentals : "+totalRents+"\n\n"+t;
       this.alertObserver(s);
        
    }
    //----MAIN FUNCTION ------
    public static void main(String[] args) throws FileNotFoundException {

        int day = 0;
        String s=null;
        RentalStore rentalStore = new RentalStore(0);
        observer o=new observer(); // Setting up the observer
        rentalStore.addObserver(o);
        rentalStore.printActiveRecords();
        rentalStore.day_amnt = 0;
        int num_cust, n;
        for(day=1;day<=5;day++)
        {
            s ="\n\n******************* DAY "+day+" *******************";
            rentalStore.alertObserver(s);
            rentalStore.day=day;
            rentalStore.returns(day);
            if(rentalStore.getTotalCars() == 0)
            {   
                s= "\n\t-- No Cars available in the Rental store today --\t";
                rentalStore.alertObserver(s);
            }
            else {
                n = 1;
                num_cust = rentalStore.randomGenerator(1, 5);
                while(rentalStore.getTotalCars() > 0 && n <= num_cust ) {
                    customerRecord C = rentalStore.customerIn(day);
                    n++;
                }
            }
            s="\n---- Rental records of Day "+day+"-----\n\n";
            for(int j=0;j<rentalStore.activeRecords.size();j++)
            {
                customerRecord c= rentalStore.activeRecords.get(j);
                for(int k=0;k<c.cars_rented.size();k++)
                {
                    if(c.cars_rented.get(k).day_rented == day)
                        s+=c.name+" rented"+ rentalStore.getDescription(c.cars_rented.get(k))+" ("+c.cars_rented.get(k).license_no+"), for "+(c.cars_rented.get(k).day_due-c.cars_rented.get(k).day_rented)+" night(s), for a total cost of $"+c.cars_rented.get(k).cost()+".\n";
                }
            }
            rentalStore.alertObserver(s);
            s="\n---- Cars left in the inventory on Day "+day+" : "+ rentalStore.getTotalCars()+" -----\n\n";
            Car car;
            for (String car_type : rentalStore.cars.keySet()) {
                for(int j = 0; j < rentalStore.cars.get(car_type).size(); j++) {
                    car = rentalStore.cars.get(car_type).get(j);
                    s+="Car Type : "+car_type+" with license plate : "+car.license_no+".\n";
                }
            }
            rentalStore.alertObserver(s);
            s="\n--- Total Amount made on Day "+day+" : " + rentalStore.day_amnt+" ---\n";
            rentalStore.alertObserver(s);
            rentalStore.total_amnt += rentalStore.day_amnt;   
            rentalStore.day_amnt = 0;
        }
        rentalStore.finalStep();
        
    }
}