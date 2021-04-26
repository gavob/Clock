
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
    
    public Model model;

    public AlarmLabel(Model m) {
        model = m;
        setPreferredSize(new Dimension(200, 30));
        setHorizontalAlignment(JLabel.CENTER);
        setOpaque(true);
        setText("No alarms set");
        model.addObserver(this);
    }

    @Override
    public void update(Observable o, Object o1) {
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) { 
        super.paintComponent(g);
        
        if(model.alarm != null) {
            if(model.alarm.getActive()) setBackground(Color.green); else setBackground(Color.lightGray);
            setText(model.alarm.getTime());
        } else {
            setBackground(Color.lightGray);
            setText("No alarms set");
        }
    }
    
}
