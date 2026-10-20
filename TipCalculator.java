import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class TipCalculator extends JFrame {
    private static final double small = 3.50;
    private static final double medium = 3.5+0.75;
    private static final double large = 3.5+0.75+0.5;
    
    private final JTextField nameCustomer = new JTextField(12);
    private final JComboBox<String> drinks =
            new JComboBox<>(new String[]{"Mocha", "Latte", "Cappuccino", "Americano", "Venti", "Chai"});
    private final JComboBox<String> sizes =
            new JComboBox<>(new String[]{"Small", "Medium", "Large"});
    private final JLabel terminLabel = new JLabel("Total: ");
    private final JTextArea receiptArea = new JTextArea(6, 26);

    public TipCalculator() {
        super("Coffee Calculator");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 8, 8));

        inputPanel.add(new JLabel("Name :"));
        inputPanel.add(nameCustomer);
        inputPanel.add(new JLabel("Drink size:"));
        inputPanel.add(sizes);
        inputPanel.add(new JLabel("Drink type:"));
        inputPanel.add(drinks);

        JButton order = new JButton("Order");
        order.addActionListener(this::makeOrder);
        JButton clear = new JButton("Clear");
        clear.addActionListener(this::clearEverything);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(order);
        buttonPanel.add(clear);

        receiptArea.setEditable(false);
        JScrollPane receiptAreaTextBox = new JScrollPane(receiptArea);
        receiptAreaTextBox.setBorder(BorderFactory.createTitledBorder("Receipt"));

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(receiptAreaTextBox, BorderLayout.EAST);
        add(terminLabel, BorderLayout.NORTH);



        
        pack();
    }

    private void makeOrder(ActionEvent e) {
            String coffeeChoice = (String) drinks.getSelectedItem();
            String coffeeSize = (String) sizes.getSelectedItem();
            String name = nameCustomer.getText().trim();
            if (name.isEmpty()) {
                terminLabel.setText("Please enter your name");
                nameCustomer.requestFocusInWindow();
                return;
            }
            double price = 0;
            if (coffeeSize=="Small"){
                price = small;
            }else if(coffeeSize == "Medium"){
                price = medium;
            }            
            else if(coffeeSize == "Large"){
                price = large;
            }
            terminLabel.setText(String.format("Total: $%.2f", price));
            receiptArea.append(String.format("%s: %s %s  $%.2f%n", name, coffeeSize, coffeeChoice, price));
        }
    

        private void clearEverything(ActionEvent e) {
            nameCustomer.setText("");
            drinks.setSelectedIndex(0);
            sizes.setSelectedIndex(0);
            terminLabel.setText("Total:     ");
            nameCustomer.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TipCalculator().setVisible(true));
    }
}
