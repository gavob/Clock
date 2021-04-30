import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Gavin Bruce - 11000148
 */
public class DigitalClockPanel extends ClockPanel {
    
    public DigitalClockPanel(Model m) {
        super(m); // Uses the constructor of this classes abstract class
    }
    
    @Override
    public void paintComponent(Graphics g) { 
        super.paintComponent(g); // Calls this method from its parent class
        
        Rectangle bounds = getBounds(); // Gets the size of the clocks parent frame
        
        Graphics2D gg = (Graphics2D) g;
        int x0 = bounds.width / 2;
        int y0 = bounds.height / 2;
        
        int size = Math.min(x0, y0);
        
        int hour = model.hour;
        int min = model.minute;
        int sec = model.second;
        
        String time;
        
        if(hour < 10) time = "0" + hour + ":"; // Adds a 0 if hour is less than 10
        else time = hour + ":";
        
        if(min < 10) time += "0" + min + ":"; // Adds a 0 if minute is less than 10
        else time += min + ":";
        
        if(sec < 10) time += "0" + sec; // Adds a 0 if second is less than 10
        else time += Integer.toString(sec);
        
        time += model.amPm;
        
        Font font = new Font("SansSerif", Font.BOLD, size/3);
        
        gg.setFont(font);
        
        gg.drawString(time, 15, y0); // Prints time to panel
    }
}
    

