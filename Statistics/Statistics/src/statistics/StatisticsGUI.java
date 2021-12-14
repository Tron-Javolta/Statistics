package statistics;

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
import java.awt.event.ActionEvent;
import java.awt.List;

public class StatisticsGUI {

	private JFrame frame;
	private ArrayList<Double> arrayOfNums = new ArrayList<Double>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatisticsGUI window = new StatisticsGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StatisticsGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 559, 321);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNumberLabel = new JLabel("Numbers:");
		GridBagConstraints gbc_lblNumberLabel = new GridBagConstraints();
		gbc_lblNumberLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberLabel.gridx = 2;
		gbc_lblNumberLabel.gridy = 0;
		frame.getContentPane().add(lblNumberLabel, gbc_lblNumberLabel);
		
		List numberDisplay = new List();
		GridBagConstraints gbc_numberDisplay = new GridBagConstraints();
		gbc_numberDisplay.gridwidth = 3;
		gbc_numberDisplay.fill = GridBagConstraints.VERTICAL;
		gbc_numberDisplay.gridheight = 6;
		gbc_numberDisplay.insets = new Insets(0, 0, 5, 5);
		gbc_numberDisplay.gridx = 2;
		gbc_numberDisplay.gridy = 1;
		frame.getContentPane().add(numberDisplay, gbc_numberDisplay);
		
		JLabel lblAnswer = new JLabel("Answer:");
		GridBagConstraints gbc_lblAnswer = new GridBagConstraints();
		gbc_lblAnswer.anchor = GridBagConstraints.EAST;
		gbc_lblAnswer.gridwidth = 2;
		gbc_lblAnswer.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnswer.gridx = 6;
		gbc_lblAnswer.gridy = 6;
		frame.getContentPane().add(lblAnswer, gbc_lblAnswer);
		
		JLabel answerLabel = new JLabel("");
		GridBagConstraints gbc_answerLabel = new GridBagConstraints();
		gbc_answerLabel.anchor = GridBagConstraints.EAST;
		gbc_answerLabel.gridwidth = 4;
		gbc_answerLabel.insets = new Insets(0, 0, 5, 5);
		gbc_answerLabel.gridx = 5;
		gbc_answerLabel.gridy = 7;
		frame.getContentPane().add(answerLabel, gbc_answerLabel);
		
		JButton btnAddNums = new JButton("Add Nums");
		btnAddNums.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Double> currentInput = new ArrayList<Double>();
				
				String test1 = JOptionPane.showInputDialog("Enter numbers here: ");
				
				if (test1 != null)
				{
					test1 = test1.replaceAll(", ", " ");
					String[] numberLine = test1.split(" ");
				
					for (int i = 0; i < numberLine.length; i++)
					{
						try {
							arrayOfNums.add(Double.parseDouble(numberLine[i]));
							currentInput.add(Double.parseDouble(numberLine[i]));
						}
						
						catch (Exception error) {
							JOptionPane.showMessageDialog(frame, "Numbers could not be added, please input valid numbers.");
							arrayOfNums.clear();
							return;
						}
					}
				
					Collections.sort(arrayOfNums);
					Collections.sort(currentInput);
				
					String numberDisplayText = "";
				
					for (int i = 0; i < currentInput.size(); i++)
					{	
						int doubleConvertedToInt;
						
						// If whole number
						if (currentInput.get(i)%1 == 0)
						{
							double doubleToConvert = currentInput.get(i);
							doubleConvertedToInt = (int)doubleToConvert;
							numberDisplayText = String.valueOf(doubleConvertedToInt) + "\n";
							numberDisplay.add(numberDisplayText);
						}
						else
						{
							numberDisplayText = String.valueOf(currentInput.get(i)) + "\n";
							numberDisplay.add(numberDisplayText);
						}
					}
				}
				else
					return;
			}
		});
		GridBagConstraints gbc_btnAddNums = new GridBagConstraints();
		gbc_btnAddNums.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddNums.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddNums.gridx = 10;
		gbc_btnAddNums.gridy = 1;
		frame.getContentPane().add(btnAddNums, gbc_btnAddNums);
		
		JButton btnResetArray = new JButton("Reset Array");
		btnResetArray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (arrayOfNums != null && arrayOfNums.size() >= 1)
				{
					arrayOfNums.clear();
					// RESET LABEL
					answerLabel.setText("<Answer>");
					numberDisplay.removeAll();
					
					JOptionPane.showMessageDialog(frame, "Numbers reset!");
				}
				else
					JOptionPane.showMessageDialog(frame, "No numbers to reset.");
			}
		});
		GridBagConstraints gbc_btnResetArray = new GridBagConstraints();
		gbc_btnResetArray.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnResetArray.insets = new Insets(0, 0, 5, 5);
		gbc_btnResetArray.gridx = 10;
		gbc_btnResetArray.gridy = 3;
		frame.getContentPane().add(btnResetArray, gbc_btnResetArray);
		
		JButton btnMinimum = new JButton("Minimum");
		btnMinimum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (arrayOfNums != null && arrayOfNums.size() >= 1)
				{
					if (arrayOfNums.get(0) % 1 == 0)
						answerLabel.setText(String.valueOf(arrayOfNums.get(0).intValue()));
					
					else
						answerLabel.setText(arrayOfNums.get(0).toString());
				}
				else
					return;
			}
		});
		GridBagConstraints gbc_btnMinimum = new GridBagConstraints();
		gbc_btnMinimum.anchor = GridBagConstraints.SOUTH;
		gbc_btnMinimum.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMinimum.insets = new Insets(0, 0, 5, 5);
		gbc_btnMinimum.gridx = 0;
		gbc_btnMinimum.gridy = 0;
		frame.getContentPane().add(btnMinimum, gbc_btnMinimum);
		
		JButton btnMaximum = new JButton("Maximum");
		btnMaximum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (arrayOfNums != null && arrayOfNums.size() >= 1)
				{
					if (arrayOfNums.get(arrayOfNums.size()-1) % 1 == 0)
						answerLabel.setText(String.valueOf(arrayOfNums.get(arrayOfNums.size()-1).intValue()));
					
					else
						answerLabel.setText(arrayOfNums.get(arrayOfNums.size()-1).toString());
				}
				else
					return;
			}
		});
		GridBagConstraints gbc_btnMaximum = new GridBagConstraints();
		gbc_btnMaximum.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMaximum.insets = new Insets(0, 0, 5, 5);
		gbc_btnMaximum.gridx = 0;
		gbc_btnMaximum.gridy = 1;
		frame.getContentPane().add(btnMaximum, gbc_btnMaximum);
		
		// QUARTILE 1 BUTTON
		JButton btnQuartile1 = new JButton("Quartile 1");
		btnQuartile1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (arrayOfNums != null && arrayOfNums.size() >= 2)
				{
					double mean;
					int midpointIndex = (arrayOfNums.size() / 2);
				
					// if true find the lower half mean of the midpoint
					if (midpointIndex % 2 == 0)
					{
						double firstMidpoint = arrayOfNums.get((midpointIndex/2)-1);
						double secondMidpoint = arrayOfNums.get((midpointIndex/2));

						mean = (firstMidpoint + secondMidpoint) / 2;

						String answer = String.valueOf(mean);
						DecimalFormat decimalFormat = new DecimalFormat("0.####");
						String result = decimalFormat.format(Double.valueOf(answer));
					
						answerLabel.setText(result);
					}
					else
					{
						double quartileIndex = .25*(arrayOfNums.size());
						int returnIndex;
					
						// if the index is a whole number find the mean of the index and index+1
						if (quartileIndex % 1 == 0)
						{
							double firstQuartileIndex = arrayOfNums.get((int) quartileIndex);
							double secondQuartileIndex = arrayOfNums.get((int) (quartileIndex+1));
						
							double oddMean = (firstQuartileIndex + secondQuartileIndex) / 2;

							String answer = String.valueOf(oddMean);
							DecimalFormat decimalFormat = new DecimalFormat("0.####");
							String result = decimalFormat.format(Double.valueOf(answer));
						
							answerLabel.setText(result);
						}
						// If not a whole number (decimal)
						else
						{
							returnIndex = ((int) (Math.ceil(quartileIndex)))-1;
							
							String answer = String.valueOf(returnIndex);
							DecimalFormat decimalFormat = new DecimalFormat("0.####");
							String result = decimalFormat.format(Double.valueOf(answer));
						
							answerLabel.setText(result);
						}	
					}
				}
				else
					return;
			}
		});
		//************************************************************************************
		
		GridBagConstraints gbc_btnQuartile1 = new GridBagConstraints();
		gbc_btnQuartile1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnQuartile1.insets = new Insets(0, 0, 5, 5);
		gbc_btnQuartile1.gridx = 0;
		gbc_btnQuartile1.gridy = 2;
		frame.getContentPane().add(btnQuartile1, gbc_btnQuartile1);
		
		JButton btnMedian = new JButton("Median");
		btnMedian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (arrayOfNums != null && arrayOfNums.size() >= 1)
				{
				
					int midpoint, secondMidpoint; // MIDPOINT INDEXES
				
					double medianValue;
				
					// even number of indexes
					if (arrayOfNums.size() % 2 == 0)
					{
						midpoint = (arrayOfNums.size()-1) / 2; // middle index
						secondMidpoint = midpoint + 1; // next index after
					
						medianValue = (arrayOfNums.get(midpoint) + arrayOfNums.get(secondMidpoint)) / 2;
						
						String answer = String.valueOf(medianValue);
						DecimalFormat decimalFormat = new DecimalFormat("0.####");
						String result = decimalFormat.format(Double.valueOf(answer));
					
						answerLabel.setText(result);
					}
					else
					{
						midpoint = arrayOfNums.size() / 2;
						medianValue = arrayOfNums.get(midpoint);

						String answer = String.valueOf(medianValue);
						DecimalFormat decimalFormat = new DecimalFormat("0.####");
						String result = decimalFormat.format(Double.valueOf(answer));
					
						answerLabel.setText(result);
					}
				}
				
				else
					return;
			}
		});
		GridBagConstraints gbc_btnMedian = new GridBagConstraints();
		gbc_btnMedian.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMedian.insets = new Insets(0, 0, 5, 5);
		gbc_btnMedian.gridx = 0;
		gbc_btnMedian.gridy = 3;
		frame.getContentPane().add(btnMedian, gbc_btnMedian);
		
		// 
		JButton btnQuartile3 = new JButton("Quartile 3");
		btnQuartile3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (arrayOfNums != null && arrayOfNums.size() >= 2)
				{
					double mean;
					int midpointIndex = (arrayOfNums.size() / 2);
					
					// if true get the upper half section past the midpoint
					if (midpointIndex % 2 == 0)
					{
						double firstMidpointIndex = arrayOfNums.get((midpointIndex+(midpointIndex/2))-1);
						double secondMidpointIndex = arrayOfNums.get((midpointIndex+(midpointIndex/2)));

						mean = (firstMidpointIndex + secondMidpointIndex) / 2;
						
						String answer = String.valueOf(mean);
						DecimalFormat decimalFormat = new DecimalFormat("0.####");
						String result = decimalFormat.format(Double.valueOf(answer));
					
						answerLabel.setText(result);
					}
					
					else
					{
						double quartileIndex = .75*(arrayOfNums.size());
						int returnIndex;
						
						// if the index is a whole number find the mean of the index and index+1
						if (quartileIndex % 1 == 0)
						{
							
							double oddMean = (arrayOfNums.get((int) quartileIndex) + arrayOfNums.get((int) (quartileIndex+1))) / 2;
							
							String answer = String.valueOf(oddMean);
							DecimalFormat decimalFormat = new DecimalFormat("0.####");
							String result = decimalFormat.format(Double.valueOf(answer));
						
							answerLabel.setText(result);
						}
						// If not a whole number (decimal) return the value ez pz
						else
						{
							returnIndex = ((int) (Math.ceil(quartileIndex)))-1;
							
							String answer = String.valueOf(returnIndex);
							DecimalFormat decimalFormat = new DecimalFormat("0.####");
							String result = decimalFormat.format(Double.valueOf(answer));
						
							answerLabel.setText(result);
						}
					}
				}
				
				else
					return;
			}
		});
		GridBagConstraints gbc_btnQuartile3 = new GridBagConstraints();
		gbc_btnQuartile3.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnQuartile3.insets = new Insets(0, 0, 5, 5);
		gbc_btnQuartile3.gridx = 0;
		gbc_btnQuartile3.gridy = 4;
		frame.getContentPane().add(btnQuartile3, gbc_btnQuartile3);
		
		// RANGE BUTTON
		JButton btnRange = new JButton("Range");
		btnRange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (arrayOfNums != null && arrayOfNums.size() >= 1)
				{
					double range = arrayOfNums.get(arrayOfNums.size()-1)-arrayOfNums.get(0);
					
					String answer = String.valueOf(range);
					DecimalFormat decimalFormat = new DecimalFormat("0.####");
					String result = decimalFormat.format(Double.valueOf(answer));
				
					answerLabel.setText(result);
				}
				else
					return;
			}
		});
		//**********************************************************************************
		
		GridBagConstraints gbc_btnRange = new GridBagConstraints();
		gbc_btnRange.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRange.insets = new Insets(0, 0, 5, 5);
		gbc_btnRange.gridx = 0;
		gbc_btnRange.gridy = 5;
		frame.getContentPane().add(btnRange, gbc_btnRange);
		
		// STANDARD DEVIATION BUTTON
		JButton btnStandardDeviation = new JButton("Standard Deviation");
		btnStandardDeviation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (arrayOfNums != null && arrayOfNums.size() >= 2)
				{
					double mean = mean();
					
					double total = 0;
					
					for (int i = 0; i < arrayOfNums.size(); i++)
					{
						total += (arrayOfNums.get(i) - mean) * (arrayOfNums.get(i)-mean);
					}
					
					double meanOfSquaredDifferences = (1.0/(arrayOfNums.size()-1)) * total;
					
					meanOfSquaredDifferences = Math.sqrt(meanOfSquaredDifferences);
					
					String answer = String.valueOf(meanOfSquaredDifferences);
					DecimalFormat decimalFormat = new DecimalFormat("0.####");
					
					String result = decimalFormat.format(Double.valueOf(answer));
					answerLabel.setText(result);
				}
				
				else if (arrayOfNums != null && arrayOfNums.size() >= 1)
					answerLabel.setText("Not applicable.");
				
				else
					return;
			}
		});
		//*******************************************************************************
		
		GridBagConstraints gbc_btnStandardDeviation = new GridBagConstraints();
		gbc_btnStandardDeviation.anchor = GridBagConstraints.WEST;
		gbc_btnStandardDeviation.insets = new Insets(0, 0, 5, 5);
		gbc_btnStandardDeviation.gridx = 0;
		gbc_btnStandardDeviation.gridy = 6;
		frame.getContentPane().add(btnStandardDeviation, gbc_btnStandardDeviation);
		
		// MEAN BUTTON
		JButton btnMean = new JButton("Mean");
		btnMean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arrayOfNums != null && arrayOfNums.size() >= 1)
				{
					double mean = mean();
					
					String answer = String.valueOf(mean);
					DecimalFormat decimalFormat = new DecimalFormat("0.####");
					String result = decimalFormat.format(Double.valueOf(answer));
				
					answerLabel.setText(result);
				}
				else
					return;
			}
		});
		//*************************************************
		
		GridBagConstraints gbc_btnMean = new GridBagConstraints();
		gbc_btnMean.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMean.insets = new Insets(0, 0, 5, 5);
		gbc_btnMean.gridx = 0;
		gbc_btnMean.gridy = 7;
		frame.getContentPane().add(btnMean, gbc_btnMean);
		
		// INTERQUARTILE RANGE BUTTON
		JButton btnInterquartileRange = new JButton("Interquartile Range");
		btnInterquartileRange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (arrayOfNums != null && arrayOfNums.size() >= 2)
				{
					String answer = String.valueOf(upperQuartile()-lowerQuartile());
					
					DecimalFormat decimalFormat = new DecimalFormat("0.####");
					String result = decimalFormat.format(Double.valueOf(answer));
					answerLabel.setText(result);
				}
				
				else if (arrayOfNums != null && arrayOfNums.size() >= 1)
					answerLabel.setText("Not applicable.");
				
				else
					return;
			}
		});
		//***************************************************************************
		
		GridBagConstraints gbc_btnInterquartileRange = new GridBagConstraints();
		gbc_btnInterquartileRange.insets = new Insets(0, 0, 0, 5);
		gbc_btnInterquartileRange.gridx = 0;
		gbc_btnInterquartileRange.gridy = 8;
		frame.getContentPane().add(btnInterquartileRange, gbc_btnInterquartileRange);
	}
	
	double lowerQuartile() {
		double mean;
		int midpointIndex = (arrayOfNums.size() / 2);
		
		// if true find the lower half mean of the midpoint
		if (midpointIndex % 2 == 0)
		{
			double firstMidpoint = arrayOfNums.get((midpointIndex/2)-1);
			double secondMidpoint = arrayOfNums.get((midpointIndex/2));

			mean = (firstMidpoint + secondMidpoint) / 2;
			return mean;
		}
		else
		{
			double quartileIndex = .25*(arrayOfNums.size());
			int returnIndex;
			
			// if the index is a whole number find the mean of the index and index+1
			if (quartileIndex % 1 == 0)
			{
				double firstQuartileIndex = arrayOfNums.get((int) quartileIndex);
				double secondQuartileIndex = arrayOfNums.get((int) (quartileIndex+1));
				
				double oddMean = (firstQuartileIndex + secondQuartileIndex) / 2;
				return oddMean;
			}
			// If not a whole number (decimal)
			else
			{
				returnIndex = ((int) (Math.ceil(quartileIndex)))-1;
				return arrayOfNums.get(returnIndex);
			}
		}
	}
	
	double upperQuartile() {
		double mean;
		int midpointIndex = (arrayOfNums.size() / 2);
		
		// if true get the upper half section past the midpoint
		if (midpointIndex % 2 == 0)
		{
			double firstMidpointIndex = arrayOfNums.get((midpointIndex+(midpointIndex/2))-1);
			double secondMidpointIndex = arrayOfNums.get((midpointIndex+(midpointIndex/2)));

			mean = (firstMidpointIndex + secondMidpointIndex) / 2;
			return mean;
		}
		
		else
		{
			double quartileIndex = .75*(arrayOfNums.size());
			int returnIndex;
			
			// if the index is a whole number find the mean of the index and index+1
			if (quartileIndex % 1 == 0)
			{
				
				double oddMean = (arrayOfNums.get((int) quartileIndex) + arrayOfNums.get((int) (quartileIndex+1))) / 2;
				return oddMean;
			}
			// If not a whole number (decimal) return the value ez pz
			else
			{
				returnIndex = ((int) (Math.ceil(quartileIndex)))-1;
				return arrayOfNums.get(returnIndex);
			}
		}
	}
	
	double mean() {
		double total = 0;
		
		for (int i = 0; i < arrayOfNums.size(); i++)
		{
			total += arrayOfNums.get(i);
		}
		return total / arrayOfNums.size();
	}
}
