
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
public class AlarmSetter extends JFrame implements ActionListener {
    
    public Model model;
    public JSpinner hour;
    public JSpinner minute;
    public JRadioButton am;
    public JRadioButton pm;
    public JButton save;
    public JButton cancel;
          
    
    public AlarmSetter(Model model) {
        
        this.model = model;
        
        setTitle("Set Alarm");
        
        SpinnerModel hourValue = new SpinnerNumberModel(model.hour, //initial value  
                1, //minimum value  
                12, //maximum value  
                1); //step
        SpinnerModel minuteValue = new SpinnerNumberModel(model.minute, //initial value  
                0, //minimum value  
                59, //maximum value  
                1); //step
        
        save = new JButton("Save");
        cancel = new JButton("Cancel");
        JLabel hrLabel = new JLabel("Hour");
        JLabel mnLabel = new JLabel("Minute");
        JLabel amPmLabel = new JLabel("AM / PM");
        hour = new JSpinner(hourValue);
        minute = new JSpinner(minuteValue);
        am = new JRadioButton("am");    
        pm = new JRadioButton("pm");
        ButtonGroup bg = new ButtonGroup();
        bg.add(am);
        bg.add(pm);
        
        save.setBounds(40, 120, 75, 30);
        cancel.setBounds(120, 120, 75, 30);
        hrLabel.setBounds(40, 40, 40, 30);
        mnLabel.setBounds(90, 40, 40, 30);
        amPmLabel.setBounds(150, 40, 80, 30);
        hour.setBounds(40, 70, 40, 30);
        minute.setBounds(90, 70, 40, 30);
        am.setBounds(150, 70, 20, 20);
        pm.setBounds(175, 70, 20, 20);
        
        add(save);
        add(cancel);
        add(hrLabel);
        add(mnLabel);
        add(amPmLabel);
        add(hour);
        add(minute);
        add(am);
        add(pm);
        
        if("am".equals(model.amPm)) am.setSelected(true);
        else pm.setSelected(true);
        
        this.save.addActionListener(this);
        this.cancel.addActionListener(this);
        
        setSize(260, 240);
        setLayout(null);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == save) { //"save".equals(ae.getActionCommand())
            String selection = (am.isSelected()) ? "am" : "pm";
            //JOptionPane.showMessageDialog(null, (Integer)hour.getValue() + "," + (Integer)minute.getValue()+ "," + selection);
            model.alarm = new Alarm(model, (Integer)hour.getValue(), (Integer)minute.getValue(), selection); 
            dispose();
        } else {
            dispose();
        }
    }
}
