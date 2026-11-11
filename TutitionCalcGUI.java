import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class TutitionCalcGUI extends JFrame{

    //input boxes
    private final JTextField tuitionRate = new JTextField(12);
    private final JTextField addFees = new JTextField(12);
    private final JTextField finAid = new JTextField(12);
    private final JTextField credits = new JTextField(12);

    private final JLabel tuitionBalance = new JLabel("Total Tuition: --");
    private final JLabel aidApp = new JLabel("Aid Applied: --");
    private final JLabel balanceDue = new JLabel("Amount Due: --");



    public TutitionCalcGUI() {
        //name of tab
        super("Tutition Calc");

        //set "hit x button" behavior
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        //create different grid layouts to organize ui
        JPanel inputPanel = new JPanel(new GridLayout(2,4,8,8));
        JPanel titlePanel = new JPanel(new GridLayout(3,1,8,8));

        //section labels
        inputPanel.add(new JLabel("$ per credit"));
        inputPanel.add(new JLabel("Additional fees"));
        inputPanel.add(new JLabel("Financial Aid"));
        inputPanel.add(new JLabel("Credits"));

        inputPanel.add(tuitionRate);
        inputPanel.add(addFees);
        inputPanel.add(finAid);
        inputPanel.add(credits);

        titlePanel.add(tuitionBalance);
        titlePanel.add(aidApp);
        titlePanel.add(balanceDue);

        //add listeners so the program actually does things when the buttons are clicked
        JButton order = new JButton("Calculate");
        order.addActionListener(this::CalcTuition);
        JButton clear = new JButton("Clear");
        clear.addActionListener(this::clearEverything);
        JButton exit = new JButton("Exit");
        exit.addActionListener(e -> System.exit(0));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(order);
        buttonPanel.add(clear);
        buttonPanel.add(exit);

        //organize panel locations
        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        add(inputPanel, BorderLayout.EAST);
        
        pack();
        
    }

    public void CalcTuition(ActionEvent e){
        try {
            //make sure the booleans arent empty
            boolean notEmpty = (tuitionRate.getText() != null && credits.getText() != null && addFees.getText() != null && finAid.getText() != null);
            boolean overZero = ((Double.parseDouble(tuitionRate.getText()) > 0) && (Double.parseDouble(credits.getText()) > 0) && (Double.parseDouble(addFees.getText()) > 0) && (Double.parseDouble(finAid.getText()) > 0));
            //if both are true, calculate total, cost, and aid applied
            if(notEmpty && overZero){
                Double total = (Double.parseDouble(tuitionRate.getText()) * Double.parseDouble(credits.getText()) + Double.parseDouble(addFees.getText()) - Double.parseDouble(finAid.getText()));
                Double cost = (Double.parseDouble(tuitionRate.getText()) * Double.parseDouble(credits.getText()) + Double.parseDouble(addFees.getText()));
                if(total < 0){
                    //set total to zero for user because their aid is greater than the total cost
                    total = 0.0;
                    tuitionBalance.setText(String.format("Total Tuition: %.2f", cost));
                    aidApp.setText(String.format("Aid Applied: %.2f", cost));
                    balanceDue.setText(String.format("Amount Due: %.2f", total) + " - you owe us no money!");
                }else{
                    tuitionBalance.setText(String.format("Total Tuition: %.2f", cost));
                    aidApp.setText(String.format("Aid Applied: %.2f", Double.parseDouble(finAid.getText())));
                    balanceDue.setText(String.format("Amount Due: %.2f", total));
                }
            }else{
                tuitionBalance.setText("Error: Only postive numbers must be entered into all fields and every field must have a value!");
            }
        } catch (Exception f) {
            tuitionBalance.setText("Error: Only postive numbers must be entered into all fields and every field must have a value!");
        }
    }
    //reset all data
    private void clearEverything(ActionEvent e) {
            //reset everything
            tuitionRate.setText("");
            addFees.setText("");
            finAid.setText("");
            credits.setText("");
            tuitionBalance.setText("Total Tuition: --");
            aidApp.setText("Aid Applied: --");
            balanceDue.setText("Amount Due: --");
    }
    
    //actually make the table visible
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TutitionCalcGUI().setVisible(true));
    }
}
