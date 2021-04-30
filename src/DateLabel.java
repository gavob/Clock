import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;

/**
 *
 * @author Gavin Bruce - 11000148
 */
public class DateLabel extends JLabel implements Observer {
    
    private Model model;
    
    public DateLabel(Model m) {
        model = m;
        setPreferredSize(new Dimension(200, 30)); // Sets size of label
        setOpaque(true); // Removes transparency from label
        setHorizontalAlignment(JLabel.CENTER); // Aligns labels text to the center
        setBackground(Color.lightGray); // Sets labels background colour
        model.addObserver(this); // Sets this label as an observer of the model
    }

    @Override
    public void update(Observable o, Object o1) {
        this.repaint(); // Calls to recreate the display in every change of the model
    }
    
    @Override
    public void paintComponent(Graphics g) { 
        super.paintComponent(g); // Calls this method from its parent class
        
        setText(model.day); // Sets the text of this label as the current date from the model
    }
    
}
