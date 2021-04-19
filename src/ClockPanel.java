
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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
public abstract class ClockPanel extends JPanel {
    
    public Model model;
    
    public ClockPanel(Model m) {
        model = m;
        setPreferredSize(new Dimension(200, 200));
        setBackground(Color.white);
    }
    
}
