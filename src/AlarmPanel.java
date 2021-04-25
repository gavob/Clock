
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
    
    public AlarmPanel(Model m) {
        setPreferredSize(new Dimension(40, 80));
        
        JLabel hrLabel = new JLabel("Hour");
        JLabel mnLabel = new JLabel("Minute");
        JLabel aLabel = new JLabel("Active:");
        JRadioButton am = new JRadioButton();    
        JRadioButton pm = new JRadioButton();
        active = new JCheckBox("active");
        bg = new ButtonGroup();
        bg.add(am);
        bg.add(pm);
        
        SpinnerModel hourValue = new SpinnerNumberModel(m.hour, //initial value  
                1, //minimum value  
                12, //maximum value  
                1); //step
        SpinnerModel minuteValue = new SpinnerNumberModel(m.minute, //initial value  
                0, //minimum value  
                59, //maximum value  
                1); //step
        
        hour = new JSpinner(hourValue);
        minute = new JSpinner(minuteValue);
        
        setLayout(new GridLayout(4,2));
        
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
        
        if("am".equals(m.amPm)) am.setSelected(true);
        else pm.setSelected(true);
        
        active.setSelected(true);
    }
    
    public int getHour() {
        return (Integer)hour.getValue();
    }
    
    public int getMinute() {
        return (Integer)minute.getValue();
    }
    
    public String getAmPm() {
        return bg.getSelection().getActionCommand();
    }
    
    public boolean getActive() {
        return active.isSelected();
    }
}
