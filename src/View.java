import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import queuemanager.QueueOverflowException;
import queuemanager.QueueUnderflowException;

/*
* Gavin Bruce - 11000148
*/

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
        
        addWindowListener(new WindowAdapter() { // Run when closed to prompt user to save alarms
            @Override
            public void windowClosing(WindowEvent e) { 
                if(model.alarms.isEmpty() == false) { // Only prompt user if there is alarms to be saved
                    int result = JOptionPane.showConfirmDialog(null, "Save Alarms?", "Save", JOptionPane.OK_CANCEL_OPTION); // Query user to save alarms dialog
                
                    if(result == JOptionPane.OK_OPTION) { 
                        String alarmFile = "BEGIN:VCALENDAR\r\nVERSION:2.0"; // Starting text of ical file
                        alarmFile += "\r\nPRODID:1\r\nBEGIN:VEVENT\r\nDTSTAMP:20210324T120000Z";
                        alarmFile += "\r\nUID:uidgav@example.com\r\nDTSTART:20210413T141711Z";

                        for(int i=1; i<=model.alarmCount; i++) { // Repeats adding this text for each alarm
                            try { 
                                Alarm a = model.alarms.head(i); // Retrieve alarm
                                int hr;
                                int mn = a.getMinute();

                                if("pm".equals(a.getAmPm())) {
                                    hr = a.getHour() + 12;
                                    if(hr==24) hr = 0;
                                } else hr = a.getHour();

                                alarmFile += "\r\nBEGIN:VALARM";
                                alarmFile += "\r\nTRIGGER;VALUE=DATE-TIME:20210317T";
                                if(hr<10) alarmFile += "0";
                                alarmFile += hr; // Add hour 
                                if(mn<10) alarmFile += "0";
                                alarmFile += mn; // Add minute
                                alarmFile += "00Z";
                                alarmFile += "\r\nREPEAT:1\r\nDURATION:PT1M";
                                alarmFile += "\r\nACTION:AUDIO\r\nEND:VALARM";
                            } catch (QueueUnderflowException ex) { Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex); }
                        }

                        alarmFile += "\r\nEND:VEVENT\r\nEND:VCALENDAR";

                        try {
                            Files.write(Paths.get("c:/alarms.ics"), alarmFile.getBytes()); // Write the ical file to c drive
                            JOptionPane.showMessageDialog(null,"Alarms Saved"); // Confirm alarms have been saved
                        } catch (IOException ex) {
                            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.exit(0); 
                    } else System.exit(0); 
                } else System.exit(0);
            }
        });
        
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
                
                if(result == JOptionPane.OK_OPTION) { // Runs if ok button is selected through add alarm dialog
                    addAlarm(setAlarm);
                }
                
            } else if("Edit Alarm".equals(ae.getActionCommand())) {
                AlarmSelectionPanel alarmChoice = new AlarmSelectionPanel(model);
                
                int result = JOptionPane.showConfirmDialog(this, alarmChoice, "Select Alarm to Edit", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                
                if(result == JOptionPane.OK_OPTION) {
                    int selection = alarmChoice.getSelected(); // Get index of selected alarm
                    try {
                        Alarm alarm = model.alarms.head(selection); // Get selected alarm
                        AlarmPanel editAlarm = new AlarmPanel(alarm); // Setup alarm panel with selected alarm
                        Object[] options = {"Save","Delete"}; // Array for custom button text
                        
                        int editResult = JOptionPane.showOptionDialog(this, editAlarm, "Edit Alarm", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null); // Displays edit alarm dialog
                       
                        if(editResult  == JOptionPane.YES_OPTION) { // Runs if user selects save in edit alarm dialog
                            model.alarms.remove(selection); // Deletes old version of alarm
                            
                            addAlarm(editAlarm);
                        } else if(editResult  == JOptionPane.NO_OPTION) { // Runs if user selects delete in edit alarm dialog
                            model.alarms.remove(selection); // Deletes alarm from list
                        }
                    } catch (QueueUnderflowException ex) {
                        Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                }
                
                
            }
        }
        
        public void addAlarm(AlarmPanel setAlarm) {
            Alarm newAlarm = new Alarm(model, 
                setAlarm.getHour(), 
                setAlarm.getMinute(), 
                setAlarm.getAmPm(), 
                setAlarm.getActive()); // Adds new alarm to model
                    
            if(model.alarms.isEmpty()) { // If priority queue is empty add new alarm
                try {
                    model.alarms.add(newAlarm, 1); // Add new alarm to empty priorityqueue
                } catch (QueueOverflowException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Calendar cal = Calendar.getInstance();
                long currentTS = cal.getTimeInMillis();
                long newTS = newAlarm.getTimeStamp();
                long currentDiff = newTS - currentTS;
                int newPriority = 1;
                
                try {
                    for (int i = 1; i <= model.alarmCount; i++) {
                        long diff = model.alarms.head(i).getTimeStamp() - currentTS; // Get time difference between alarm being checked in queue and current time 

                        if(currentDiff <= diff) { // If time difference of added alarm is less or equal to one in queue then added alarm takes its priority
                            newPriority = i;
                            break;
                        } else newPriority++; 
                    }
                } catch (QueueUnderflowException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    model.alarms.add(newAlarm, newPriority); // Add new alarm to linked list queue
                } catch (QueueOverflowException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
    
}
