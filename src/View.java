import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;

public class View implements Observer {
    
    ClockPanel panel;
    
    public View(Model model) {
        JFrame frame = new JFrame();
        
        
        panel = new DigitalClockPanel(model);
        
        //Added code so far--------------------- TODO think about abstract jframe? moving variables initialisation?
        Container pane = frame.getContentPane();
        
        JButton about = new JButton("About");
        
        JMenuBar menuBar = new JMenuBar(); 
        JMenu menu = new JMenu("Clock");
        JMenuItem menuAbout = new JMenuItem("About");
        
        menu.add(menuAbout);
        menuBar.add(menu);
        
        frame.setJMenuBar(menuBar);
        
        ClickActionListener clickListen = new ClickActionListener();
        
        about.addActionListener(clickListen);
        menuAbout.addActionListener(clickListen);
        
        pane.add(panel, BorderLayout.CENTER);
        pane.add(about, BorderLayout.PAGE_END);
        
        
        //---------------------------------------
        
        frame.setContentPane(pane);
        frame.setTitle("Java Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void update(Observable o, Object arg) {
        panel.repaint();
    }
    
    // Inner class for actionlistener
    
    class ClickActionListener extends JComponent implements ActionListener  {

        @Override
        public void actionPerformed(ActionEvent ae) {
            JOptionPane.showMessageDialog(this, "Java Clock by Gavin Bruce");
        }
    
    }
}
