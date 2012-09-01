import java.util.*;
import java.io.*;
public class WalkThroughDesert 
{
	public static void main(String[] args) 
	{
		//constants, used to determine the event on each line
		final String FUEL_CONSUMP = "Fuel consumption";				
		final String LEAK = "Leak";
		final String GAS_STATION = "Gas station";
		final String MECHANIC = "Mechanic";
		final String GOAL = "Goal";
		//access input first
		Scanner in = null;
		//if the input if from console
		if (args.length == 0)
		{
			in = new Scanner(System.in);
		}
		//if user has supply the input file
		else if (args.length == 1)
		{
			File inFile = new File(args[0]);
			if (!inFile.exists() || !inFile.canRead()) {
			    System.err.println("Problem with input file!");
			    System.exit(1);
			 }
			 try
			 {
				 in = new Scanner(inFile);	
			 } catch (FileNotFoundException ex)
			 {
				 System.err.println("Error when openning the file!");
				 System.exit(1);
			 }
		}
		else
		{
			System.err.println("You must provide a valid argument!");
			System.exit(1);
		}
		//start process the input, first get the first line of input to enter the loop
		String inputString = in.nextLine();
		String delims = "[ ]";
		
		//split the first line into words, making it easier to determine which kind of operation to perform on 
		//the current event
		String[] words = inputString.split(delims);
		
		//use this list to store the tank volumn of car before each gas station, and determine 
		//which tank volumn is the  largest to decide the final value of the tank
		ArrayList<Double> list = new ArrayList<Double>(); 
		
		//if no gas station, the total volunm is stored in this variable
		double tankVol = 0.0;
		
		//variable that store the tank volumn between each gas station
		double volForBeforeEachGasStation = 0.0;
		
		//consumption of gas per km, taking into consideration of all other conditions
		double realConsumption = 0.0;
		
		//store the distance value of previous event
		int prevDistance = 0;
		//store the number of leaks in each query
		int numOfLeaks = 0;
		while (!(inputString.contains("0 " + FUEL_CONSUMP) && 
				words[3].equals("0")))
		{
			int distance = Integer.parseInt(words[0].trim());
			int distanceBetween =  distance - prevDistance;
			if (distanceBetween != 0)
			{
				tankVol += realConsumption * distanceBetween;
				volForBeforeEachGasStation += realConsumption * distanceBetween; 
			}
			//for each of the situation 'Fuel consumption, Goal, Leak, Gas station, Mechanic
			if (words.length == 4 && (words[1] + " " + words[2]).equals(FUEL_CONSUMP))
			{
				double consumption = (double) Integer.parseInt(words[3].trim()) / 100;
				realConsumption = consumption + numOfLeaks;
			}
			else if (words[1].equals(LEAK))
			{
				realConsumption += 1;
				numOfLeaks++;
			}
			else if (words.length == 3 && (words[1] + " " + words[2]).equals(GAS_STATION))
			{
				list.add(volForBeforeEachGasStation);
				volForBeforeEachGasStation = 0;
			}
			else if (words[1].equals(MECHANIC))
			{
				realConsumption -= numOfLeaks;
				numOfLeaks = 0;
			}
			prevDistance = distance;
			if (words[1].equals(GOAL))
			{
				    if (list.isEmpty())
				    {
				    	System.out.printf("%.3f", tankVol);
				    	System.out.println();
				    }
				    else 
				    {
				    	list.add(volForBeforeEachGasStation);
				    	double largestVol = 0.0;
				    	for (Double d : list)
				    	{
				    		if (largestVol < d)
				    		{
				    			largestVol = d;
				    		}
				    	}
				    	System.out.printf("%.3f", largestVol);
				    	System.out.println();
				    }
				    //reset each variable for different event
				    tankVol = 0;
					prevDistance = 0;
					volForBeforeEachGasStation = 0;
					numOfLeaks = 0;
					list = new ArrayList<Double>();
			}
			inputString = in.nextLine();
			words = inputString.split(delims);
		}
	}
	//for testing purpose only
	public static void printArray(String[] word)
	{
		for (int i = 0; i < word.length; i++)
		{
			System.out.println(word[i]);
		}
	}
}
