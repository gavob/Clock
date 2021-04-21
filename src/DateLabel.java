
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gav
 */
public class DateLabel extends JLabel implements Observer {
    
    public Model model;
    
    public DateLabel(Model m) {
        model = m;
        setPreferredSize(new Dimension(200, 30));
        setBackground(Color.gray);
        model.addObserver(this);
    }

    @Override
    public void update(Observable o, Object o1) {
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) { 
        super.paintComponent(g);
        
        setText(model.day);
    }
    
}
