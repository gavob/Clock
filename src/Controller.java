import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import queuemanager.QueueOverflowException;

/*
* Gavin Bruce - 11000148
*/

public class Controller {
    
    ActionListener listener;
    Timer timer;
    
    Model model;
    View view;
    
    public Controller(Model m, View v) throws IOException, QueueOverflowException {
        model = m;
        view = v;
        
        File tmpDir = new File("c:/alarms.ics"); // Look for alarm save file
        boolean alarmsExist = tmpDir.exists(); // Check if alarm file exists
        
        if(alarmsExist) {
            int result = JOptionPane.showConfirmDialog(null, "Load Saved Alarms?", "Load", JOptionPane.OK_CANCEL_OPTION); // Query user to load saved alarms 
            
            if(result == JOptionPane.OK_OPTION) {
                String content = new String(Files.readAllBytes(Paths.get("c:/alarms.ics")), StandardCharsets.UTF_8); // Load saved alarm ical file
                int index = 1;
                
                for(int i=0; i<content.length(); i++) { // Looks through ical file
                    if(content.charAt(i)=='T'&&content.charAt(i-1)=='7') { // Looks for characters that precede the alarm time
                        String alarmTime = content.substring(i+1, i+5); // Extract time from alarm
                        int hour = Integer.parseInt(alarmTime.substring(0, 2)); // Extract hour and convert to integer
                        int min = Integer.parseInt(alarmTime.substring(2, 4)); // Extract min and convert to integer
                        String amPm;
                        
                        if(hour > 12) { // Check if in am or pm
                            hour -= 12;
                            amPm = "pm";
                        } else amPm = "am";
                        
                        Alarm newAlarm = new Alarm(model, hour, min, amPm, true); // Create alarm
                        model.alarms.add(newAlarm, index); // Add alarm to queue
                        index++;
                    }
                } 
            }
        }
        ////////////////////////////////////////
        listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.update();
            }
        };
        
        timer = new Timer(100, listener);
        timer.start();
        
        
    }
}