package statistics;

import java.util.*;
import java.lang.Math;

/*
 * Array sorter (does arraylist have its own sort method??) IT DOES
 * 
 * quarterly solver
 * 
 * median finder
 * 
 * min and max finder
 */


public class MultipleStatsSolver {
	protected ArrayList<Double> array = new ArrayList<>();
	
	
	@SuppressWarnings("unchecked")
	public MultipleStatsSolver(ArrayList<Double> array) {
		this.array = (ArrayList<Double>) array.clone();
	}
	
	
	double min() {
		return array.get(0);
	}
	
	double max() {
		return array.get(array.size()-1);
	}
	
	// .25(the size of the arraylist), round up and use that number as the index - 1
	double lowerQuartile() {
		double mean;
		int midpointIndex = (array.size() / 2);
		
		// if true find the lower half mean of the midpoint
		if (midpointIndex % 2 == 0)
		{
			double firstMidpoint = array.get((midpointIndex/2)-1);
			double secondMidpoint = array.get((midpointIndex/2));
			// mean = (array.get((midpointIndex/2)-1) + array.get((midpointIndex/2))) / 2;
			mean = (firstMidpoint + secondMidpoint) / 2;
			return mean;
		}
		else
		{
			double quartileIndex = .25*(array.size());
			int returnIndex;
			
			// if the index is a whole number find the mean of the index and index+1
			if (quartileIndex % 1 == 0)
			{
				double firstQuartileIndex = array.get((int) quartileIndex);
				double secondQuartileIndex = array.get((int) (quartileIndex+1));
				
				//double oddMean = (array.get((int) quartileIndex) + array.get((int) (quartileIndex+1))) / 2;
				double oddMean = (firstQuartileIndex + secondQuartileIndex) / 2;
				return oddMean;
			}
			// If not a whole number (decimal)
			else
			{
				returnIndex = ((int) (Math.ceil(quartileIndex)))-1;
				return array.get(returnIndex);
			}
		}
	}
	
	double upperQuartile() {
		double mean;
		int midpointIndex = (array.size() / 2);
		
		// if true get the upper half section past the midpoint
		if (midpointIndex % 2 == 0)
		{
			double firstMidpointIndex = array.get((midpointIndex+(midpointIndex/2))-1);
			double secondMidpointIndex = array.get((midpointIndex+(midpointIndex/2)));
			
			//mean = (array.get((midpointIndex+(midpointIndex/2))-1) + 
			//		array.get((midpointIndex+(midpointIndex/2)))) / 2;
			mean = (firstMidpointIndex + secondMidpointIndex) / 2;
			return mean;
		}
		
		else
		{
			double quartileIndex = .75*(array.size());
			int returnIndex;
			
			// if the index is a whole number find the mean of the index and index+1
			if (quartileIndex % 1 == 0)
			{
				
				double oddMean = (array.get((int) quartileIndex) + array.get((int) (quartileIndex+1))) / 2;
				return oddMean;
			}
			// If not a whole number (decimal) return the value ez pz
			else
			{
				returnIndex = ((int) (Math.ceil(quartileIndex)))-1;
				return array.get(returnIndex);
			}
		}
	}
	
	double median() {
		int midpoint, secondMidpoint; // MIDPOINT INDEXES
		
		double medianValue;
		
		// even number of indexes
		if (array.size() % 2 == 0)
		{
			midpoint = (array.size()-1) / 2; // middle index
			secondMidpoint = midpoint + 1; // next index after
			
			medianValue = (array.get(midpoint) + array.get(secondMidpoint)) / 2;
			return medianValue;
		}
		else
		{
			midpoint = array.size() / 2;
			medianValue = array.get(midpoint);
			return medianValue;
		}
	}
	
	double range() {
		return max() - min();
	}
	
	void printArray() {

		for (int i = 0; i < array.size(); i++)
		{
			if (i % 5 == 0)
				System.out.print("\n");
			
			System.out.print(array.get(i) + " \t");
		}
	}
	
	void printCommaArray() {
		for (int i = 0; i < array.size(); i++)
		{
			if (i < array.size())
				System.out.print(array.get(i) + ", ");
			
			else if (i == array.size() + 1)
				System.out.print(array.get(i));
		}
	}
	
	double interQuartileRange() {
		return upperQuartile() - lowerQuartile();
	}
	
	double mean() {
		double total = 0;
		
		for (int i = 0; i < array.size(); i++)
		{
			total += array.get(i);
		}
		return total / array.size();
	}
	
	double standardDeviation() {
		double mean = mean();
		
		double total = 0;
		
		for (int i = 0; i < array.size(); i++)
		{
			total += (array.get(i) - mean) * (array.get(i)-mean);
		}
		
		double meanOfSquaredDifferences = (1.0/(array.size()-1)) * total;
		
		return Math.sqrt(meanOfSquaredDifferences);
	}
	
	double mode() {
		
		
		return 0;
	}
}
