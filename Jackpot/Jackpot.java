import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Jackpot {
	public static void main(String[] args)
	{
		//process the input first
		//if the input is from the console
		Scanner in = null;
		if (args.length == 0)
		{
			in = new Scanner(System.in);
		}
		else if (args.length == 1)
		{
			File file = new File(args[0]);
			if (!file.exists() || !file.canRead()) {
				System.err.println("Problem with input file!");
				System.exit(1);
			}
			try
			{
				in = new Scanner(file);
			}
			catch (FileNotFoundException ex)
			{
				System.out.println("Error happens when uploading the file!");
			}
		}
		else 
		{
			System.out.println("Error with the argument!");
			System.exit(1);
		}
		ArrayList<Integer> betList = new ArrayList<Integer>();
		int num = in.nextInt();
		while(num != 0)
		{
			int currInput = 0;
			for (int i = 0; i < num; i++)
			{
				currInput = in.nextInt();
				betList.add(currInput);
			}
			int result = processList(betList);
			if (result != 0)
			{
				System.out.println("The maximum winning streak is " + result + ".");
			}
			else
			{
				System.out.println("Losing streak.");
			}
			betList = new ArrayList<Integer>();
			num = in.nextInt();
		}	

	}
	public static int processList(ArrayList<Integer> list)
	{
		int currSum = 0;
		int maxStart = 0, maxEnd = 0, maxSum = -1;
		for(int currEnd = 0; currEnd < list.size(); currEnd++)
		{
			currSum += list.get(currEnd);
			if(currSum > 0 && currSum > maxSum)
			{
				maxSum = currSum;
				maxEnd = currEnd;
			}
			if (currSum < 0)
			{
				currSum = 0;
				maxStart = currEnd + 1;
			}
		 }
		return (maxSum < 0) ? 0 : maxSum;
 	}
}
