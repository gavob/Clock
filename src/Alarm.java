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
    
    public Model model;
    
    private int hour;
    private int minute;
    private String amPm;
    private boolean active;
    
    public Alarm(Model m, int hr, int min, String ap, boolean act) { // Alarm constructor passing current model and alarm time and whether its active
        model = m;
        model.addObserver(this); // Make this instance of an alarm an observer of the model
        hour = hr;
        minute = min;
        amPm = ap;
        active = act; 
    }

    @Override
    public void update(Observable o, Object o1) { // Called every second so that alarm instance is always observing changes in model
        if(model.hour==hour && model.minute==minute && model.second == 0 && model.amPm==amPm && active) // If model time matches alarm time code within runs
            JOptionPane.showMessageDialog(null,"ALARM"); // Calls alarm
    }
    
    public void setHour(int hr) {
        hour = hr; // Set hour
    }
    
    public void setMinute(int min) {
        minute = min; // Set minute
    }
    
    public void setAmPm(String ap) {
        amPm = ap; // Set amPm
    }
    
    public void setActive(boolean isActive) {
        active = isActive; // Set active
    }
    
    public String getTime() {
        String time = "";
        
        if(hour<10) time = "0"; // Add a 0 before the hour if alarm before 10 o'clock 
        time += hour + ":"; // Add alarm hour
        if(minute<10) time += "0"; // Add a 0 if less than 10 minutes
        time += minute; // Add alarm minutes
        time += amPm; // Add am or pm
        if(active) time += " (Active)"; else time += " (Disabled)"; // Add text informing whether alarm is active
        
        return time; // Returns time into a readable formatted string
    }
    
    public int getHour() {
        return hour; // Return alarm hour
    }
    
    public int getMinute() {
        return minute; // Return alarm minute
    }
    
    public String getAmPm() {
        return amPm; // Return string of am or pm of alarm
    }
    
    public boolean getActive() {
        return active; // Return whether alarm is active in true or false
    }
    
}
