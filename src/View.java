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
    
        if("analog".equals(model.display)) panel = new AnalogClockPanel(model);
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
        
        date.setVisible(false);
        
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
    
    // Inner class for actionlistener TODO does this effectively follow MVC pattern?
    class ClickActionListener extends JComponent implements ActionListener  {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if("About".equals(ae.getActionCommand())) {
                JOptionPane.showMessageDialog(this, "Java Clock by Gavin Bruce");
            } else if("Analog".equals(ae.getActionCommand())) {
                model.display = "analog";
                new View(model);
            } else if("Digital".equals(ae.getActionCommand())) {
                model.display = "digital";
                new View(model);
            } else if("Display Date".equals(ae.getActionCommand())) {
                if(date.isVisible()) date.setVisible(false); 
                else date.setVisible(true);
            } else if("Add Alarm".equals(ae.getActionCommand())) {
                AlarmPanel setAlarm = new AlarmPanel(model); //setup alarm panel with current model time
                
                int result = JOptionPane.showConfirmDialog(this, setAlarm, "Set Alarm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                
                if(result  == JOptionPane.OK_OPTION) {
                    model.alarm = new Alarm(model, setAlarm.getHour(), setAlarm.getMinute(), setAlarm.getAmPm(), setAlarm.getActive());
                    System.out.println("Hour: "+ setAlarm.getHour()+" - Minute: "+ setAlarm.getMinute()+ " - AMPM: "+ setAlarm.getAmPm()+ " - Active: "+ setAlarm.getActive()); //DEBUG SETALARM GETTERS
                }
            } else if("Edit Alarm".equals(ae.getActionCommand())) {
                Object[] options = {"Save","Delete"}; //array for custom button text
                AlarmPanel editAlarm = new AlarmPanel(model.alarm); //setup alarm panel with current alarm that is set
                
                int result = JOptionPane.showOptionDialog(this, editAlarm, "Edit Alarm", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
                
                if(result  == JOptionPane.YES_OPTION) {
                    model.alarm = new Alarm(model, editAlarm.getHour(), editAlarm.getMinute(), editAlarm.getAmPm(), editAlarm.getActive());
                    
                    System.out.println("Hour: "+ editAlarm.getHour()+" - Minute: "+ editAlarm.getMinute()+ " - AMPM: "+ editAlarm.getAmPm()+ " - Active: "+ editAlarm.getActive()); //DEBUG SETALARM GETTERS
                } else if(result  == JOptionPane.NO_OPTION) {
                    model.alarm = null; // delete alarm
                }
            }
        }
    
    }
}
