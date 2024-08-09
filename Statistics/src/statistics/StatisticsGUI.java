package statistics;

import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.List;

public class StatisticsGUI {

    // Main application window
    private JFrame frame;

    // List to hold the numbers input by the user
    private ArrayList<Double> arrayOfNums = new ArrayList<>();
    
    private MultipleStatsSolver statObject;

    // Main method to launch the application
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                StatisticsGUI window = new StatisticsGUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Constructor that initializes the GUI
    public StatisticsGUI() {
        initialize();
    }

    // Initialize the contents of the frame
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 559, 321);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(createLayout());

        // List component to display numbers
        List numberDisplay = new List();

        // Label to display the calculated answer
        JLabel answerLabel = addLabel("", 5, 7, 4, GridBagConstraints.EAST);

        // Label and List for displaying input numbers
        addLabel("Numbers:", 2, 0);
        addComponent(numberDisplay, 2, 1, 3, 6, GridBagConstraints.VERTICAL);

        addLabel("Answer:", 6, 6, 2, GridBagConstraints.EAST);

        addButton("Add Nums", e -> addNumbers(numberDisplay, answerLabel), 10, 1);
        addButton("Reset Array", e -> resetArray(numberDisplay, answerLabel), 10, 3);

        addStatButtons(answerLabel);
    }

    // Method to create and return the grid layout configuration
    private GridBagLayout createLayout() {
        GridBagLayout layout = new GridBagLayout();
        layout.columnWidths = new int[13];
        layout.rowHeights = new int[10];
        layout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        layout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        return layout;
    }

    // Method to add a label to the frame
    private JLabel addLabel(String text, int x, int y) {
        return addLabel(text, x, y, 1, GridBagConstraints.CENTER);
    }

    // Method to add a label with custom parameters to the frame
    private JLabel addLabel(String text, int x, int y, int width, int anchor) {
        JLabel label = new JLabel(text);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 5, 5);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.anchor = anchor;
        frame.getContentPane().add(label, gbc);
        return label;
    }

    // Method to add a component with custom parameters to the frame
    private void addComponent(Component component, int x, int y, int width, int height, int fill) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 5, 5);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.fill = fill;
        frame.getContentPane().add(component, gbc);
    }

    // Method to add a button with an action listener to the frame
    private void addButton(String text, ActionListener action, int x, int y) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 5, 5);
        gbc.gridx = x;
        gbc.gridy = y;
        frame.getContentPane().add(button, gbc);
    }

    // Method to add buttons for calculating various statistical values
    private void addStatButtons(JLabel answerLabel) {
        String[] labels = {"Minimum", "Maximum", "Quartile 1", "Median", "Quartile 3", "Range", "Standard Deviation", "Mean", "Interquartile Range"};
        
        for (int i = 0; i < labels.length; i++) {
            final String label = labels[i];  // Declare label as final inside the loop
            addButton(label, e -> handleStatButton(label, answerLabel), 0, i);
        }
    }
    
    // Handle button clicks by using a switch case based on button label
    private void handleStatButton(String label, JLabel answerLabel) {
        if (arrayOfNums.isEmpty()) {
            answerLabel.setText("None");
            return;
        }

        switch (label) {
            case "Minimum":
                answerLabel.setText(formatDouble(statObject.min()));
                break;
            case "Maximum":
                answerLabel.setText(formatDouble(statObject.max()));
                break;
            case "Quartile 1":
                answerLabel.setText(formatDouble(statObject.lowerQuartile()));
                break;
            case "Median":
                answerLabel.setText(formatDouble(statObject.median()));
                break;
            case "Quartile 3":
            	answerLabel.setText(formatDouble(statObject.upperQuartile()));
                break;
            case "Range":
                answerLabel.setText(formatDouble(statObject.range()));
                break;
            case "Standard Deviation":
                answerLabel.setText(formatDouble(statObject.standardDeviation()));
                break;
            case "Mean":
                answerLabel.setText(formatDouble(statObject.mean()));
                break;
            case "Interquartile Range":
                answerLabel.setText(formatDouble(statObject.interQuartileRange()));
                break;
            default:
                answerLabel.setText("Invalid operation.");
                break;
        }
    }

    // Method to add numbers to the list and display them
    private void addNumbers(List numberDisplay, JLabel answerLabel) {
        String input = JOptionPane.showInputDialog("Enter numbers here: ");
        if (input != null) {
            try {
                String[] numberLine = input.replaceAll(", ", " ").split(" ");
                ArrayList<Double> currentInput = new ArrayList<>();
                
                for (String number : numberLine) {
                    double num = Double.parseDouble(number);
                    arrayOfNums.add(num);
                    currentInput.add(num);
                }
                Collections.sort(arrayOfNums); // object already sorted so just gotta sort arrayOfNums
                
                statObject = new MultipleStatsSolver(currentInput);

                // Update the number display list with sorted values
                numberDisplay.removeAll();
                currentInput.forEach(num -> numberDisplay.add(formatDouble(num)));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Numbers could not be added, please input valid numbers.");
                arrayOfNums.clear();
            }
        }
    }

    // Method to reset the list of numbers and clear the display
    private void resetArray(List numberDisplay, JLabel answerLabel) {
        if (!arrayOfNums.isEmpty()) {
            arrayOfNums.clear();
            answerLabel.setText("<Answer>");
            numberDisplay.removeAll();
            JOptionPane.showMessageDialog(frame, "Numbers reset!");
        } else {
            JOptionPane.showMessageDialog(frame, "No numbers to reset.");
        }
    }

    // Helper method to format a double value to 2 decimal places
    private String formatDouble(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(value);
    }
}
