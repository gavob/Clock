
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Clock {
    
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(model, view);
        
        /*Runtime.getRuntime().addShutdownHook(new Thread() { // Runs when program is shutdown - WILL KEEP HERE UNTILL BETTER PLACE
            
            @Override
            public void run() {
                System.out.println("uj");
                //int result = JOptionPane.showConfirmDialog(null, this, "Do you wish to save alarms?", JOptionPane.OK_CANCEL_OPTION);
                JOptionPane.showMessageDialog(null,"ALARM");
            }
        });*/
    }
    
}
