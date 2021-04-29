
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import queuemanager.QueueUnderflowException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gav
 */
public class AlarmSelectionPanel extends JPanel {
    
    private int selected;
    
    AlarmSelectionPanel(Model m) {
        setPreferredSize(new Dimension(100, 60));
        JComboBox selection;
        String alarms[] = new String[m.alarmCount];
        selected = 1;
        
        for(int i=1; i<=m.alarmCount; i++) {
            String alarm;
            try {
                alarm = i + " - " + m.alarms.head(i).getTime();
                alarms[i-1] = alarm;
            } catch (QueueUnderflowException ex) {
                Logger.getLogger(AlarmSelectionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        selection = new JComboBox(alarms);
        selection.setBounds(50, 50, 70, 30);
        add(selection);
        
        selection.addActionListener((ActionEvent ae) -> {
            selected = selection.getSelectedIndex() + 1;
        });
    }
    
    public int getSelected() {
        return selected;
    }
    
}
