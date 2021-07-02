package statistics;

import java.util.*;

public class StatisticsSolver {

	public static void main(String[] args)
	{
	
		Scanner keyboard = new Scanner(System.in);
	
		ArrayList<Double> statsNumbers = new ArrayList<>();
	
	
		int sentinelValue = -1;
		double value = 0;

		
		// Get values for the array of numbers
		while (true)
		{
			System.out.print("Insert number (input -1 to end): ");
			value = keyboard.nextDouble();
			
			if (value == sentinelValue)
				break;
			
			else
			{
				statsNumbers.add(value);
				System.out.println(value + " inserted.\n");
			}
		}
		
		Collections.sort(statsNumbers);
		
		
		MultipleStatsSolver object = new MultipleStatsSolver(statsNumbers);
		
		//System.out.println("\nLower quarterly: " + object.lowerQuartile());
		//System.out.println("Median: " + object.median());
		//System.out.println("Upper quarterly: " + object.upperQuartile());
		
		userOptions(object);
		
		keyboard.close();
	}
	
	static void userOptions(MultipleStatsSolver array) {
		Scanner keyboard = new Scanner(System.in);
		
		int selection = 0;
		
		do
		{
			System.out.println("\n-------------------------");
			System.out.println("Please select an option.\n");
			System.out.println("-1. Quit\n1. Find min\n2. Find max\n3. Find lower quartile\n4. Find upper quartile" +
							   "\n5. Find range\n6. Find median\n7. Print array\n8. Print comma array" +
							   "\n9. Standard Deviation\n10. Mean\n11. Interquartile Range");
			
			selection = keyboard.nextInt();
			
			switch (selection)
			{
				// Exit case
				case -1:
					System.out.println("Goodbye.\n");
					break;
				
				// Min 
				case 1:
					System.out.println("The min is: " + array.min() + "\n");
					break;
					
				case 2:
					System.out.println("The max is: " + array.max() + "\n");
					break;
					
				case 3:
					System.out.println("The lower quartile is: " + array.lowerQuartile() + "\n");
					break;
				
				case 4:
					System.out.println("The upper quartile is: " + array.upperQuartile()+ "\n");
					break;
					
				case 5:
					System.out.println("The range is: " + array.range() + "\n");
					break;
					
				case 6:
					System.out.println("The median is: " + array.median() + "\n");
					break;
					
				case 7:
					array.printArray();
					break;
					
				case 8:
					array.printCommaArray();
					break;
					
				case 9:
					System.out.println("The standard deviation is: " + array.standardDeviation() + "\n");
					break;
					
				case 10:
					System.out.println("The mean is: " + array.mean() + "\n");
					break;
					
				case 11:
					System.out.println(array.interQuartileRange());
					break;
				
				default:
					System.out.println("Incorrect selection, please try again.\n");
					break;
			}
				
		} while (selection != -1);
		
		keyboard.close();
	}
}

