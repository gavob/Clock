
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
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
public abstract class ClockPanel extends JPanel implements Observer {
    
    public Model model;
    
    public ClockPanel(Model m) {
        model = m;
        setPreferredSize(new Dimension(200, 200));
        setBackground(Color.white);
        model.addObserver(this); 
    }
    
    @Override
    public void update(Observable o, Object o1) {
        this.repaint();
    }
}
