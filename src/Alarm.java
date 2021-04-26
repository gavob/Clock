import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gav
 */
public class Alarm implements Observer {
    
    private Model model;
    
    private int hour;
    private int minute;
    private String amPm;
    private boolean active;
    
    public Alarm(Model m, int hr, int min, String ap, boolean act) {
        model = m;
        model.addObserver(this);
        
        hour = hr;
        minute = min;
        amPm = ap;
        active = act; 
    }

    @Override
    public void update(Observable o, Object o1) {
        //Can alarm be activated from here? aye
        if(model.hour==hour && model.minute==minute && model.second == 0 && model.amPm==amPm && active)
            JOptionPane.showMessageDialog(null,"ALARM");
    }
    
    public void setActive(boolean isActive) {
        active = isActive;
    }
    
    public String getTime() {
        String time = "";
        
        if(hour<10) time = "0";
        time += hour + ":";
        if(minute<10) time += "0";
        time += minute;
        if(active) time += " (Active)"; else time += " (Disabled)";
        
        return time;
    }
    
    public int getHour() {
        return hour;
    }
    
    public int getMinute() {
        return minute;
    }
    
    public String getAmPm() {
        return amPm;
    }
    
    public boolean getActive() {
        return active;
    }
    
}
