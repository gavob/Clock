import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;

public class View extends JFrame {
    
    ClockPanel panel;
    //JLabel date; // SHOULD BE USING DATE OBSERVER
    DateLabel date;
    
    public View(Model model) {
    
        if("analog".equals(model.display)) panel = new AnalogClockPanel(model);
        else panel = new DigitalClockPanel(model);
        
        //--------------------- TODO moving variables initialisation?
        Container pane = getContentPane();
        
        //date = new JLabel(model.day); //SHOULD BE USING DATE OBSERVER CLASS SO AS TO LISTEN TO UPDATES
        date = new DateLabel(model);
         
        JMenuBar menuBar = new JMenuBar(); 
        JMenu menu = new JMenu("Clock");
        JMenu addClock = new JMenu("Add Clock");
        JMenuItem analog = new JMenuItem("Analog");
        JMenuItem digital = new JMenuItem("Digital");
        JCheckBoxMenuItem dateDisplay = new JCheckBoxMenuItem("Display Date");
        JMenuItem menuAbout = new JMenuItem("About");
        
        addClock.add(analog);
        addClock.add(digital);
        
        menu.add(addClock);
        menu.add(dateDisplay);
        menu.add(menuAbout);
        
        menuBar.add(menu);
        
        setJMenuBar(menuBar);
        
        date.setVisible(false);
        //date.
        
        ClickActionListener clickListen = new ClickActionListener();
        
        analog.addActionListener(clickListen);
        digital.addActionListener(clickListen);
        dateDisplay.addActionListener(clickListen);
        menuAbout.addActionListener(clickListen);
        
        pane.add(panel, BorderLayout.CENTER);
        pane.add(date, BorderLayout.PAGE_END); //SHOULD BE ADDING DATE LIKE THE CLOCK PANEL
        
        
        //---------------------------------------
        
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
                panel.model.display = "analog";
                new View(panel.model);
            } else if("Digital".equals(ae.getActionCommand())) {
                panel.model.display = "digital";
                new View(panel.model);
            } else if("Display Date".equals(ae.getActionCommand())) {
                if(date.isVisible()) date.setVisible(false); // MAY NEED CHANGED AFTER ABOVE DATE CHANGES
                else date.setVisible(true);
            }
        }
    
    }
}
