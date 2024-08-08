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

        // Label for displaying calculated answer
        addLabel("Answer:", 6, 6, 2, GridBagConstraints.EAST);

        // Button to add numbers to the list
        addButton("Add Nums", e -> addNumbers(numberDisplay, answerLabel), 10, 1);

        // Button to reset the list of numbers
        addButton("Reset Array", e -> resetArray(numberDisplay, answerLabel), 10, 3);

        // Buttons to calculate various statistical values
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
        ActionListener[] actions = {
            e -> displayValue(0, answerLabel),
            e -> displayValue(arrayOfNums.size() - 1, answerLabel),
            e -> displayQuartile(0.25, answerLabel),
            e -> displayMedian(answerLabel),
            e -> displayQuartile(0.75, answerLabel),
            e -> displayRange(answerLabel),
            e -> displayStandardDeviation(answerLabel),
            e -> displayMean(answerLabel),
            e -> displayIQR(answerLabel)
        };

        for (int i = 0; i < labels.length; i++) {
            addButton(labels[i], actions[i], 0, i);
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
                Collections.sort(arrayOfNums);
                Collections.sort(currentInput);

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

    // Method to display the minimum or maximum value based on index
    private void displayValue(int index, JLabel answerLabel) {
        if (!arrayOfNums.isEmpty()) {
            answerLabel.setText(formatDouble(arrayOfNums.get(index)));
        }
    }

    // Method to calculate and display a quartile value
    private void displayQuartile(double quartile, JLabel answerLabel) {
        if (arrayOfNums.size() >= 2) {
            int n = arrayOfNums.size();
            double index = quartile * (n - 1);
            double result = (arrayOfNums.get((int) Math.floor(index)) + arrayOfNums.get((int) Math.ceil(index))) / 2;
            answerLabel.setText(formatDouble(result));
        }
    }

    // Method to calculate and display the median value
    private void displayMedian(JLabel answerLabel) {
        if (!arrayOfNums.isEmpty()) {
            int n = arrayOfNums.size();
            double median = n % 2 == 0 ? (arrayOfNums.get(n / 2 - 1) + arrayOfNums.get(n / 2)) / 2 : arrayOfNums.get(n / 2);
            answerLabel.setText(formatDouble(median));
        }
    }

    // Method to calculate and display the range (max - min)
    private void displayRange(JLabel answerLabel) {
        if (!arrayOfNums.isEmpty()) {
            double range = arrayOfNums.get(arrayOfNums.size() - 1) - arrayOfNums.get(0);
            answerLabel.setText(formatDouble(range));
        }
    }

    // Method to calculate and display the standard deviation
    private void displayStandardDeviation(JLabel answerLabel) {
        if (arrayOfNums.size() >= 2) {
            double mean = mean();
            double sum = arrayOfNums.stream().mapToDouble(num -> Math.pow(num - mean, 2)).sum();
            double stdDev = Math.sqrt(sum / (arrayOfNums.size() - 1));
            answerLabel.setText(formatDouble(stdDev));
        } else {
            answerLabel.setText("Not applicable.");
        }
    }

    // Method to calculate and display the mean value
    private void displayMean(JLabel answerLabel) {
        if (!arrayOfNums.isEmpty()) {
            answerLabel.setText(formatDouble(mean()));
        }
    }

    // Method to calculate and display the interquartile range (IQR)
    private void displayIQR(JLabel answerLabel) {
        if (arrayOfNums.size() >= 4) {
            double q1 = calculateQuartile(0.25);
            double q3 = calculateQuartile(0.75);
            double iqr = q3 - q1;
            answerLabel.setText(formatDouble(iqr));
        }
    }

    // Helper method to format a double value to 2 decimal places
    private String formatDouble(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(value);
    }

    // Method to calculate a specified quartile value
    private double calculateQuartile(double quartile) {
        int n = arrayOfNums.size();
        double index = quartile * (n - 1);
        return (arrayOfNums.get((int) Math.floor(index)) + arrayOfNums.get((int) Math.ceil(index))) / 2;
    }

    // Method to calculate the mean of the numbers
    private double mean() {
        return arrayOfNums.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }
}
