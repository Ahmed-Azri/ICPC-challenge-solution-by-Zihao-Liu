import java.util.*;
public class TheTrip {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		ArrayList<Double> eachTurn = new ArrayList<Double>();
		int num_turn = in.nextInt();
		double posSum = 0;
		double negSum = 0;
		double result = 0.0;
		double avg = 0.0;
		double sum = 0.0;
		System.out.println(Math.round(0.5));
		while(num_turn != 0){
			sum = 0;
			posSum = 0;
			negSum = 0;
			result = 0;
			avg = 0;
			for (int i = 0; i < num_turn; i++){
				eachTurn.add((double)Math.round(in.nextDouble()*100.0) / 100.0) ;
			}
			for(Double item : eachTurn)
			{
				sum += item;
			}
			avg = Math.round(sum / eachTurn.size() * 100) / 100.0;
			for(Double item : eachTurn)
			{
				if(item > avg)
				{
					posSum += (item - avg);
				}
				else 
				{
					negSum += (avg - item);
				}
			}
			result = (posSum > negSum) ? negSum: posSum;
			System.out.printf("$%.2f\n",result);
			num_turn = in.nextInt();
			eachTurn = new ArrayList<Double>();
		}
	}
}
