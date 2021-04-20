import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;

public class View extends JFrame implements Observer {
    
    ClockPanel panel;
    
    public View(Model model) {
    
        if("analog".equals(model.display)) panel = new AnalogClockPanel(model);
        else panel = new DigitalClockPanel(model);
        
        //--------------------- TODO moving variables initialisation?
        Container pane = getContentPane();
        
        JButton about = new JButton("About");
        
        JMenuBar menuBar = new JMenuBar(); 
        JMenu menu = new JMenu("Clock");
        JMenu addClock = new JMenu("Add Clock");
        JMenuItem analog = new JMenuItem("Analog");
        JMenuItem digital = new JMenuItem("Digital");
        JMenuItem menuAbout = new JMenuItem("About");
        
        addClock.add(analog);
        addClock.add(digital);
        
        menu.add(addClock);
        menu.add(menuAbout);
        
        menuBar.add(menu);
        
        setJMenuBar(menuBar);
        
        ClickActionListener clickListen = new ClickActionListener();
        
        analog.addActionListener(clickListen);
        digital.addActionListener(clickListen);
        about.addActionListener(clickListen);
        menuAbout.addActionListener(clickListen);
        
        pane.add(panel, BorderLayout.CENTER);
        pane.add(about, BorderLayout.PAGE_END);
        
        
        //---------------------------------------
        
        setContentPane(pane);
        setTitle("Java Clock");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    public void update(Observable o, Object arg) {
        panel.repaint();
    }
    
    // Inner class for actionlistener TODO does this effectively follow MVC pattern?
    class ClickActionListener extends JComponent implements ActionListener  {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if("About".equals(ae.getActionCommand())) {
                JOptionPane.showMessageDialog(this, "Java Clock by Gavin Bruce");
            } else if("Analog".equals(ae.getActionCommand())) {
                panel.model.display = "analog";
                panel.model.addObserver(new View(panel.model));
            } else if("Digital".equals(ae.getActionCommand())) {
                panel.model.display = "digital";
                panel.model.addObserver(new View(panel.model));
                
            }
        }
    
    }
}
