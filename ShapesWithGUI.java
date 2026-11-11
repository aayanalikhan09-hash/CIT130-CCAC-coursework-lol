import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class ShapesWithGUI extends JFrame{

    //input boxes
    private final JTextField circleRadius = new JTextField(12);
    private final JTextField triangleSide = new JTextField(12);
    private final JTextField squareSide = new JTextField(12);

    //output
    private final JLabel squareLabel = new JLabel("Square Side Length: -- Square Area: --");
    private final JLabel triangleLabel = new JLabel("Triangle Side Length: -- Triangle Area: --");
    private final JLabel circleLabel = new JLabel("Circle Radius: -- Circle Area: --");

    public Circle c = new Circle();    
    public Square s = new Square();
    public Triangle t = new Triangle();
    
    public ShapesWithGUI() {
        //name of tab
        super("Shape Calc");

        //draw shapes
        c.draw();
        s.draw();
        t.draw();

        //set "hit x button" behavior
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,1));

        //create different grid layouts to organize ui
        JPanel circlePanel = new JPanel(new GridLayout(1,4,8,8));
        JPanel squarePanel = new JPanel(new GridLayout(1,4,8,8));
        JPanel trianglePanel = new JPanel(new GridLayout(1,4,8,8));

        //add shape data
        circlePanel.add(c);
        circlePanel.add(new JLabel("Circle Radius:"));
        circlePanel.add(circleRadius);
        circlePanel.add(circleLabel);

        squarePanel.add(s);
        squarePanel.add(new JLabel("Square Side Length:"));
        squarePanel.add(squareSide);
        squarePanel.add(squareLabel);

        trianglePanel.add(t);
        trianglePanel.add(new JLabel("Triangle Side Length:"));
        trianglePanel.add(triangleSide);
        trianglePanel.add(triangleLabel);

        //buttons
        JButton calculate = new JButton("Calculate");
        calculate.addActionListener(e -> calculateInfo(e));
        JButton clear = new JButton("Clear");
        clear.addActionListener(e -> clearEverything(e));
        JButton exit = new JButton("Exit");
        exit.addActionListener(e -> System.exit(0));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(calculate);
        buttonPanel.add(clear);
        buttonPanel.add(exit);

        //organize panel locations
        add(circlePanel);
        add(squarePanel);
        add(trianglePanel);
        add(buttonPanel);
        
        pack();
        
    }
    private void calculateInfo(ActionEvent e){

        try {
            //check if data is a number and then if its positive + all formatted
            String squareStringPerm = String.format("%.2f", s.calcPerimeter(Double.parseDouble(squareSide.getText())));     
            String squareStringArea = String.format("%.2f", s.calcArea(Double.parseDouble(squareSide.getText())));          
            if(Double.parseDouble(squareSide.getText()) < 0){
                circleLabel.setText("Circle Perimeter: " + squareStringPerm + " Circle Area: " + squareStringArea);
            }else{
                circleLabel.setText("No numbers less than zero");
            }        
        } catch (NumberFormatException f) {
            squareLabel.setText("Numbers only - Try a decimal or integer");
        }

        try {
            String triangleStringPerm = String.format("%.2f", t.calcPerimeter(Double.parseDouble(triangleSide.getText()))); 
            String triangleStringArea = String.format("%.2f", t.calcArea(Double.parseDouble(triangleSide.getText())));      
            if(Double.parseDouble(triangleSide.getText()) < 0){
                circleLabel.setText("Circle Perimeter: " + triangleStringPerm + " Circle Area: " + triangleStringArea);
            }else{
                circleLabel.setText("No numbers less than zero");
            }        
        } catch (Exception g) {
            triangleLabel.setText("Numbers only - Try a decimal or integer");
        }

        try {
            String circleStringPerm = String.format("%.2f", c.calcPerimeter(Double.parseDouble(circleRadius.getText())));   
            String circleStringArea = String.format("%.2f", c.calcArea(Double.parseDouble(circleRadius.getText())));        
            if(Double.parseDouble(circleRadius.getText()) < 0){
                circleLabel.setText("Circle Perimeter: " + circleStringPerm + " Circle Area: " + circleStringArea);
            }else{
                circleLabel.setText("No numbers less than zero");
            }
        } catch (Exception h) {
            circleLabel.setText("Numbers only - Try a decimal or integer");
        }
        
        
        
        

        
        
        
    }
    //reset all data
    private void clearEverything(ActionEvent e) {
            //reset everything
            circleRadius.setText("");
            triangleSide.setText("");
            squareSide.setText("");
            squareLabel.setText("Square Side Length: -- Square Area: --");
            triangleLabel.setText("Triangle Side Length: -- Triangle Area: --");
            circleLabel.setText("Circle Radius: -- Circle Area: --");
    }
    
    //actually make the table visible
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShapesWithGUI().setVisible(true));
    }
}
