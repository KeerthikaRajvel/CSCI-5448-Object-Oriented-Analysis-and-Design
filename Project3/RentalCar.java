import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class RentalStore{
    Integer day;
    Integer day_amnt;
    List<Object> customers = new ArrayList<Object>();
    List<Object> cars = new ArrayList<Object>();

    public void check_new(){
    }
    
}


public interface Car{
    void create_car();
}

public class Economy implements Car{
    @override
    public void create_car(){

    }
}

public class Standard implements Car{
    @override
    public void create_car(){
        
    }
}

public class Luxury implements Car{
    @override
    public void create_car(){
        
    }
}

public class SUV implements Car{
    @override
    public void create_car(){
        
    }
}

public class Minivan implements Car{
    @override
    public void create_car(){
        
    }
}





public static void main(String[] args) throws FileNotFoundException {
    int days = 35;

}