import java.util.*;
public class customerRecord {
    String name;
    String type;
    List<Car> cars_rented;
    public customerRecord(String name,String type) //Instantiates new customer record
    {
        this.name=name;
        this.type=type;
        this.cars_rented=new ArrayList<Car>();
    }
}