
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gav
 */
public class AlarmPanel extends JPanel {
     
    private JSpinner hour;
    private JSpinner minute;
    private ButtonGroup bg;
    private JCheckBox active;
    
    public AlarmPanel(Model m) { // Constructor for creating new alarm which passes model for filling out UI with current time
        int panelHour = m.hour;
        int panelMinute = m.minute;
        String panelAmPm = m.amPm;
        boolean panelActive = true;
        
        buildPanel(panelHour, panelMinute, panelAmPm, panelActive); // Build UI with models parameters
    }
    
    public AlarmPanel(Alarm a) { // Constructor for editing an alarm by passing in an Alarm instance
        int panelHour = a.getHour();
        int panelMinute = a.getMinute();
        String panelAmPm = a.getAmPm();
        boolean panelActive = a.getActive();
        
        buildPanel(panelHour, panelMinute, panelAmPm, panelActive); // Build UI with Alarm instance parameter
    }
    
    public void buildPanel(int hr, int min, String ap, boolean act) { // Constructs UI with parameters from either constructors 
        setPreferredSize(new Dimension(40, 80)); // Set size of panel
        
        JLabel hrLabel = new JLabel("Hour");
        JLabel mnLabel = new JLabel("Minute");
        JLabel aLabel = new JLabel("Active:");
        JRadioButton am = new JRadioButton("am");    
        JRadioButton pm = new JRadioButton("pm");
        active = new JCheckBox("active");
        bg = new ButtonGroup(); // Group for radio buttons
        bg.add(am);
        bg.add(pm);
        
        SpinnerModel hourValue = new SpinnerNumberModel(hr, //initial value  
                1, //minimum value  
                12, //maximum value  
                1); //step
        SpinnerModel minuteValue = new SpinnerNumberModel(min, //initial value  
                0, //minimum value  
                59, //maximum value  
                1); //step
        
        hour = new JSpinner(hourValue);
        minute = new JSpinner(minuteValue);
        
        setLayout(new GridLayout(4,2)); // Create a grid layout for displaying UI elements
        
        add(hrLabel);
        add(mnLabel);
        add(hour);
        add(minute);
        add(am);
        add(pm);
        add(aLabel);
        add(active);
        
        am.setActionCommand("am");
        pm.setActionCommand("pm");
        
        if("am".equals(ap)) am.setSelected(true); // Checks whether am or pm and sets initial selection of radio buttons
        else pm.setSelected(true);
        
        active.setSelected(act);
    }
    
    public int getHour() {
        return (Integer)hour.getValue(); // Returns selected values from hour spinner and casts it as an integer
    }
    
    public int getMinute() {
        return (Integer)minute.getValue(); // Returns selected values from minute spinner and casts it as an integer
    }
    
    public String getAmPm() {
        return bg.getSelection().getActionCommand(); // Returns selected radio buttons action command string from the button group 
    }
    
    public boolean getActive() {
        return active.isSelected(); // Returns true or false whether active checkbox is checked
    }
}
