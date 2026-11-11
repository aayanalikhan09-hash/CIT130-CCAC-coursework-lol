import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;


public class Square extends Shape implements Drawable{
    @Override
    public void draw(){
        repaint();  
    }

    @Override
    public double calcPerimeter(double givenLength){
        return 4*givenLength;
    }
    @Override
    public double calcArea(double givenLength){
        return givenLength*givenLength;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.MAGENTA);
        g.fillRect(150, 150, 75, 75);

    }

    @Override
    public Dimension getPreferredSize() {
    //make shapes visible upon generation
        return new Dimension(500, 500); 
    }
}