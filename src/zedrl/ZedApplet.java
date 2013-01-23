/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl;
import java.applet.Applet;
import asciiPanel.AsciiPanel;
/**
 *
 * @author Brandon
 */
public class ZedApplet extends Applet
{
    private AsciiPanel term;
    
    public ZedApplet(){ // Calls Applet superconst, creates new AP inst and writes a simple msg
        super();
        term = new AsciiPanel(80, 60);
        term.write("Zed RL", 1, 1);
        add(term);
    }
    
    @Override
    public void init(){ // Initializes applet with proper size
        super.init();
        this.setSize(term.getWidth() + 20, term.getHeight() + 20);
    }
    
    @Override
    public void repaint(){
        super.repaint();
        term.repaint();
    }
}
