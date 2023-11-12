package statistics;

import java.util.*;
import java.lang.Math;

/*
 * Main methods for the Statistics Solver.
 * 
 * includes:
 * 	- min
 *  - max
 *  - lower quartile
 *  - upper quartile
 *  - median
 *  - range
 *  - print array
 *  - mean
 *  - standard deviation
 *  - mode
 */


public class MultipleStatsSolver {
	protected ArrayList<Double> array = new ArrayList<>();
	
	
	public MultipleStatsSolver(List<Double> array) {
	    this.array = new ArrayList<>(array);
	}
	
	// Minimum value of the array.
	double min() {
		return array.get(0);
	}
	
	// Maximum value of the array.
	double max() {
		return array.get(array.size()-1);
	}
	
	private double calculateQuartile(double percentile) {
	    int index = (int) Math.ceil(percentile * array.size()) - 1;
	    return array.get(index);
	}

	public double lowerQuartile() {
	    return calculateQuartile(0.25);
	}

	public double upperQuartile() {
	    return calculateQuartile(0.75);
	}
	
	// Middle of the array.
	double median() {
		int midpoint; // MIDPOINT INDEXES
		
		int size = array.size();
		
		// even number of indexes
		if (size % 2 == 0)
		{
			midpoint = (size - 1) / 2; // middle index
			
			return (array.get(midpoint) + array.get(midpoint / 1)) / 2.0;
		}
		else
		{
			return array.get(size / 2);
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
	    StringJoiner joiner = new StringJoiner(", ");
	    for (Double value : array) {
	        joiner.add(value.toString());
	    }
	    System.out.println(joiner.toString());
	}
	
	double interQuartileRange() {
		return upperQuartile() - lowerQuartile();
	}
	
	double mean() {
	    double total = 0;
	    for (Double value : array) {
	        total += value;
	    }
	    return total / array.size();
	}
	
	double standardDeviation() {
	    double mean = mean();
	    double squaredDifferences = array.stream()
	                                    .mapToDouble(value -> Math.pow(value - mean, 2))
	                                    .sum();
	    return Math.sqrt(squaredDifferences / (array.size() - 1));
	}
	
	double mode() {
		return 0;
	}
}
