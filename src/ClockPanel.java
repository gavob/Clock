import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 *
 * @author Gavin Bruce - 11000148
 */
public abstract class ClockPanel extends JPanel implements Observer {
    
    public Model model;
    
    public ClockPanel(Model m) {
        model = m;
        setPreferredSize(new Dimension(200, 200)); // Sets size of clock panel
        setBackground(Color.white); // Sets clocks background colour
        model.addObserver(this); // Sets this abstract instance as an observer of the model
    }
    
    @Override
    public void update(Observable o, Object o1) {
        this.repaint(); // Calls to recreate the display on every change in the model
    }
}
