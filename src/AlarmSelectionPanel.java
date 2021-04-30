import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import queuemanager.QueueUnderflowException;

/**
 *
 * @author Gavin Bruce - 11000148
 */
public class AlarmSelectionPanel extends JPanel {
    
    private int selected;
    
    AlarmSelectionPanel(Model m) {
        setPreferredSize(new Dimension(100, 60)); 
        JComboBox selection;
        String alarms[] = new String[m.alarmCount]; // Creates array with size to amount of alarms
        selected = 1;
        
        for(int i=1; i<=m.alarmCount; i++) { // Loop to look through all alarms
            String alarm;
            try {
                alarm = i + " - " + m.alarms.head(i).getTime(); // Create string for alarm title
                alarms[i-1] = alarm; // Add alarm title to array
            } catch (QueueUnderflowException ex) {
                Logger.getLogger(AlarmSelectionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        selection = new JComboBox(alarms); // Create a drop selection box with the array full of alarm titles
        selection.setBounds(50, 50, 70, 30);
        add(selection);
        
        selection.addActionListener((ActionEvent ae) -> {
            selected = selection.getSelectedIndex() + 1; // Listen to user selection in drop down menu
        });
    }
    
    public int getSelected() {
        return selected;
    }
    
}
