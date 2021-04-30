import java.io.IOException;
import queuemanager.QueueOverflowException;

public class Clock {
    
    public static void main(String[] args) throws IOException, QueueOverflowException {
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(model, view);
    }
    
}
