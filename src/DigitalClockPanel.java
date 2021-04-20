import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gav
 */
public class DigitalClockPanel extends ClockPanel {
    
    public DigitalClockPanel(Model m) {
        super(m);
    }
    
    @Override
    public void paintComponent(Graphics g) { 
        super.paintComponent(g);
        
        Rectangle bounds = getBounds();
        
        Graphics2D gg = (Graphics2D) g;
        int x0 = bounds.width / 2;
        int y0 = bounds.height / 2;
        
        int size = Math.min(x0, y0);
        
        int hour = model.hour;
        int min = model.minute;
        int sec = model.second;
        
        String time;
        
        if(hour < 10) time = "0" + hour + ":";
        else time = hour + ":";
        
        if(min < 10) time += "0" + min + ":";
        else time += min + ":";
        
        if(sec < 10) time += "0" + sec;
        else time += Integer.toString(sec);
        
        time += model.amPm;
        
        Font font = new Font("SansSerif", Font.BOLD, size/3);
        
        gg.setFont(font);
        
        gg.drawString(time, 15, y0);
    }
}
    

