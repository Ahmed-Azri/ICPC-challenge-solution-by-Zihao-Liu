import java.util.*;
import java.io.*;
public class MainJillyWalker 
{
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
		int count = 1;
		int input1 = in.nextInt();
		int input2 = in.nextInt();
		while (input1 != 0 && input2 != 0)
		{
			//start processing the input
			int offset1 = input1 - 1;
			int offset2 = input2 - 1;
			int totalLengthOfPath = offset1 + offset2;
			int result = aChooseB(totalLengthOfPath, offset1);
			//prepare and output the result
			System.out.println("Case " + count + ": " + result);
			count++;
			input1 = in.nextInt();
			input2 = in.nextInt();
		}
		in.close();
	}
	//recursive method
	public static int aChooseB(int a, int b)
	{
		if (b == 0 || a == b)
		{
			return 1;
		}
		if (b == 1)
		{
			return a;
		}
		return aChooseB(a - 1, b - 1) + aChooseB(a-1, b);
	}
}
