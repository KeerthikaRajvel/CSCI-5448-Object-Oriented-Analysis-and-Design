import java.util.*;
public class customerRecord {
    String name;
    String type;
    List<Object> cars_rented;
    public customerRecord(String name,String type) //Instantiates new customer record
    {
        this.name=name;
        this.type=type;
        this.cars_rented=new ArrayList<Object>();
    }
    public Boolean canRent(String car_type,int cars,int days) //Checks if the customer is eligible to rent a particular car
    {
        return false;
    }
}