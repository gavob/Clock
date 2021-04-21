
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
        model.addObserver(this); //who knows if this will work
    }

    @Override
    public void update(Observable o, Object o1) {
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) { 
        super.paintComponent(g);
        
        //Rectangle bounds = getBounds();
        
        
        setText(model.day);
        
        //String date = model.day;
        
        //Font font = new Font("SansSerif", Font.BOLD, 10);
        
        //gg.setFont(font);
        
        //gg.drawString(date, 15, 0);
        //Label date = new JLabel(model.day);
    }
    
}
