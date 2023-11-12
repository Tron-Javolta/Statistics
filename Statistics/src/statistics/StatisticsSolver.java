package statistics;

import java.util.*;

/**
 * 
 * @author Nicholas Souder
 * 
 * Application that helps in solving various statistics problems.
 *
 */
public class StatisticsSolver {

	private static final int SENTINEL_VALUE = -1;
	
	public static void main(String[] args)
	{
		try (Scanner keyboard = new Scanner(System.in)) {
			
			// User inputs numbers into this arraylist.
			List<Double> statsNumbers = new ArrayList<>();
		
			double value = 0;		// Initialize value input
	
			// Get values for the array of numbers while the input isn't -1
			while (true)
			{
				System.out.print("Insert number (input -1 to end): ");
				value = keyboard.nextDouble();
				
				// If value is -1, stop getting input from user.
				if (value == SENTINEL_VALUE)
					break;
				
				// Else, add user's input to the array.
				statsNumbers.add(value);
				System.out.println(value + " inserted.\n");
			}
			
			// Sort the array of numbers first for easier usage in certain methods.
			Collections.sort(statsNumbers);
			
			// Initialize stats solver object to start using methods to solve problems.
			MultipleStatsSolver object = new MultipleStatsSolver(statsNumbers);
	
			// Get user's option selection with the array given.
			userOptions(object);
		}
	}
	
	/**
	 * 
	 * @param array User's array of numbers
	 * 
	 * Method that presents the options the user can select for figuring out a given problem.
	 */
	static void userOptions(MultipleStatsSolver array) {
		
		try (Scanner keyboard = new Scanner(System.in)) {
			
			int selection = 0;
			
			// Present the selection of options for the user.
			do
			{
				System.out.println("\n-------------------------");
				System.out.println("Please select an option.\n");
				System.out.println("-1. Quit\n1. Find min\n2. Find max\n3. Find lower quartile\n4. Find upper quartile" +
								   "\n5. Find range\n6. Find median\n7. Print array\n8. Print comma array" +
								   "\n9. Standard Deviation\n10. Mean\n11. Interquartile Range");
				
				selection = keyboard.nextInt();
				
				// User selection
				switch (selection)
				{
					// Exit case
					case -1:
						System.out.println("Goodbye.\n");
						break;
					
					// Min case
					case 1:
						System.out.println("The min is: " + array.min() + "\n");
						break;
					
					// Max case
					case 2:
						System.out.println("The max is: " + array.max() + "\n");
						break;
						
					// Lower quartile
					case 3:
						System.out.println("The lower quartile is: " + array.lowerQuartile() + "\n");
						break;
					
					// Upper quartile
					case 4:
						System.out.println("The upper quartile is: " + array.upperQuartile()+ "\n");
						break;
					
					// Range
					case 5:
						System.out.println("The range is: " + array.range() + "\n");
						break;
					
					// Median
					case 6:
						System.out.println("The median is: " + array.median() + "\n");
						break;
					
					// Display the array
					case 7:
						array.printArray();
						break;
					
					// Display the array with commas
					case 8:
						array.printCommaArray();
						break;
						
					// Standard Deviation
					case 9:
						System.out.println("The standard deviation is: " + array.standardDeviation() + "\n");
						break;
						
					// Mean
					case 10:
						System.out.println("The mean is: " + array.mean() + "\n");
						break;
						
					// Inter Quartile Range
					case 11:
						System.out.println(array.interQuartileRange());
						break;
					
					// Bad selection
					default:
						System.out.println("Incorrect selection, please try again.\n");
						break;
				}
					
			} while (selection != -1);
			keyboard.close();
		}
	}
}

