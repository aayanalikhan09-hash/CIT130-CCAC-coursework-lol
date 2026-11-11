import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Triangle extends Shape implements Drawable{
    @Override
    public void draw(){
        repaint();  
    }

    @Override
    public double calcPerimeter(double givenLength){
        return 3*givenLength;
    }
    @Override
    public double calcArea(double givenLength){
        return (Math.sqrt(3)/4)*givenLength*givenLength;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

            g.setColor(Color.MAGENTA);
            int[] xPoints = {150,200,250};
            int[] yPoints = {275,200,275};
            g.fillPolygon(xPoints, yPoints, xPoints.length);

    }

    @Override
    public Dimension getPreferredSize() {
    //make shapes visible upon generation
        return new Dimension(500, 500); 
    }
}