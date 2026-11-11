import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Circle extends Shape implements Drawable{
    @Override
    public void draw(){
        repaint();
    }

    @Override
    public double calcPerimeter(double givenLength){
        return 2*Math.PI*givenLength;
    }
    @Override
    public double calcArea(double givenLength){
        return Math.PI*givenLength*givenLength;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.MAGENTA);
        g.fillOval(150,150,75,75);
    }
    
    @Override
    public Dimension getPreferredSize() {
    //make shapes visible upon generation
        return new Dimension(500, 500); 
    }
}
