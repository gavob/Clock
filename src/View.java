import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class View extends JFrame {
    
    Model model;
    ClockPanel panel;
    DateLabel date;
    AlarmLabel alarm;
    
    public View(Model m) {
        model = m;
    
        if("analog".equals(model.display)) panel = new AnalogClockPanel(model); // Checks whether current view should be made in digital or analog
        else panel = new DigitalClockPanel(model);
        
        Container pane = getContentPane(); 
        
        date = new DateLabel(model);
        alarm = new AlarmLabel(model);
         
        JMenuBar menuBar = new JMenuBar(); 
        JMenu menu = new JMenu("Clock");
        JMenu addClock = new JMenu("Add Clock");
        JMenuItem analog = new JMenuItem("Analog");
        JMenuItem digital = new JMenuItem("Digital");
        JCheckBoxMenuItem dateDisplay = new JCheckBoxMenuItem("Display Date");
        JMenuItem addAlarm = new JMenuItem("Add Alarm");
        JMenuItem editAlarm = new JMenuItem("Edit Alarm");
        JMenuItem menuAbout = new JMenuItem("About");
        
        addClock.add(analog);
        addClock.add(digital);
        
        menu.add(addClock);
        menu.add(dateDisplay);
        menu.add(addAlarm);
        menu.add(editAlarm);
        menu.add(menuAbout);
        
        menuBar.add(menu);
        setJMenuBar(menuBar);
        
        date.setVisible(false); // Date is initially not visible untill specified from menu
        
        ClickActionListener clickListen = new ClickActionListener();
        
        analog.addActionListener(clickListen);
        digital.addActionListener(clickListen);
        dateDisplay.addActionListener(clickListen);
        addAlarm.addActionListener(clickListen);
        editAlarm.addActionListener(clickListen);
        menuAbout.addActionListener(clickListen);
        
        pane.add(alarm, BorderLayout.PAGE_START); 
        pane.add(panel, BorderLayout.CENTER);
        pane.add(date, BorderLayout.PAGE_END); 
        
        setContentPane(pane);
        setTitle("Java Clock");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    // Inner class for action listeners on all view UI elements
    class ClickActionListener extends JComponent implements ActionListener  {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            if("About".equals(ae.getActionCommand())) {
                JOptionPane.showMessageDialog(this, "Java Clock by Gavin Bruce");
            } else if("Analog".equals(ae.getActionCommand())) {
                model.display = "analog";
                new View(model); // Adds another view frame observing the model as an analog clock
            } else if("Digital".equals(ae.getActionCommand())) {
                model.display = "digital";
                new View(model); // Adds another view frame observing the model as a digital clock
            } else if("Display Date".equals(ae.getActionCommand())) {
                if(date.isVisible()) date.setVisible(false); // Toggles date display visibilty through date checkbox in menu
                else date.setVisible(true);
            } else if("Add Alarm".equals(ae.getActionCommand())) {
                AlarmPanel setAlarm = new AlarmPanel(model); // Setup alarm panel with current model time
                
                int result = JOptionPane.showConfirmDialog(this, setAlarm, "Set Alarm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE); // Displays add alarm dialog
                
                if(result  == JOptionPane.OK_OPTION) { // Runs if ok button is selected through add alarm dialog
                    model.alarm = new Alarm(model, setAlarm.getHour(), setAlarm.getMinute(), setAlarm.getAmPm(), setAlarm.getActive()); // Adds new alarm to model
                    //System.out.println("Hour: "+ setAlarm.getHour()+" - Minute: "+ setAlarm.getMinute()+ " - AMPM: "+ setAlarm.getAmPm()+ " - Active: "+ setAlarm.getActive()); //DEBUG SETALARM GETTERS
                }
            } else if("Edit Alarm".equals(ae.getActionCommand())) {
                Object[] options = {"Save","Delete"}; // Array for custom button text
                AlarmPanel editAlarm = new AlarmPanel(model.alarm); // Setup alarm panel with current alarm that is set in model
                
                int result = JOptionPane.showOptionDialog(this, editAlarm, "Edit Alarm", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null); // Displays edit alarm dialog with current alarm in model
                
                if(result  == JOptionPane.YES_OPTION) { // Runs if user selects save in edit alarm dialog
                    model.alarm = new Alarm(model, editAlarm.getHour(), editAlarm.getMinute(), editAlarm.getAmPm(), editAlarm.getActive()); // Assigns models alarm with new changes - DOESNT USE SETTERS
                    //System.out.println("Hour: "+ editAlarm.getHour()+" - Minute: "+ editAlarm.getMinute()+ " - AMPM: "+ editAlarm.getAmPm()+ " - Active: "+ editAlarm.getActive()); //DEBUG SETALARM GETTERS
                } else if(result  == JOptionPane.NO_OPTION) { // Runs if user selects delete in edit alarm dialog
                    model.alarm = null; // Deletes alarm in model
                }
            }
        }
    }
}
