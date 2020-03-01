import java.util.Observable;
import java.util.Observer;

public class observer implements Observer {
    private String Update;
    @Override
    public void update(Observable o, Object arg) {
        System.out.println((String)arg);
    }
}
