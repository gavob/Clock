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
    
    public int hour;
    public int minute;
    public String amPm;
    public boolean active;
    
    public Alarm(Model m, int hr, int min, String ap) {
        model = m;
        model.addObserver(this);
        
        hour = hr;
        minute = min;
        amPm = ap;
        active = true; /// if all alarms are initially active when created
    }
    
    public void setActive(boolean isActive) {
        active = isActive;
    }

    @Override
    public void update(Observable o, Object o1) {
        //Can alarm be activated from here? aye
        if(model.hour==hour && model.minute==minute && model.second == 0 && model.amPm==amPm && active) //needs tested again
            JOptionPane.showMessageDialog(null,"ALARM");
    }
    
}
