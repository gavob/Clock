
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gav
 */
public class AlarmLabel extends JLabel implements Observer {
    
    private Model model;

    public AlarmLabel(Model m) {
        model = m;
        setPreferredSize(new Dimension(200, 30)); // Set size of label
        setHorizontalAlignment(JLabel.CENTER); // Align text to center of its container
        setOpaque(true); // Set label to have a background colour instead of being transparent
        setText("No alarms set"); // Set initial text which will be used if there are no alarms
        model.addObserver(this); // Set this instance as an observer of the model
    }

    @Override
    public void update(Observable o, Object o1) {
        this.repaint(); // Will clear and reset display every change in the model incase the date changes with the time
    }
    
    @Override
    public void paintComponent(Graphics g) { 
        super.paintComponent(g); // Call this method from its parent class
        
        if(model.alarm != null) { // Run code if there is an alarm
            if(model.alarm.getActive()) setBackground(Color.green); else setBackground(Color.lightGray); // Set background as green if alarm is active
            setText(model.alarm.getTime()); // Display time of alarm
        } else {
            setBackground(Color.lightGray);
            setText("No alarms set"); // Set text for when there are no alarms
        }
    }
    
}
