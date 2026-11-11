import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

interface Drawable{
    void draw();
}
abstract public class Shape extends JPanel{

    abstract double calcPerimeter(double givenLength);
    abstract double calcArea(double givenData);
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.MAGENTA);

    }
}

