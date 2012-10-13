#include <iostream>
using namespace std;
void numberSystem(double, int, int, int, int);
int main(int argc, char *argv[])
{
	int first = 0;
	int second = 0;
	cin >> first >> second;
	while(first != 1 ||  second != 1)
	{
		double x = (double)first / second;	
		numberSystem(x, 0, 1, 1,0);
		cin >> first >> second;
	}
	return 0;
}
void numberSystem(double value, int first_denominator, int first_nominator, int second_denominator, int second_nominator)
{
	double currentComp = (double) (first_denominator + second_denominator) / (first_nominator + second_nominator);
	if(value == 1)
	{
		cout << "I";
	}
	if(value > currentComp)
	{
		cout << "R";
		numberSystem(value, first_denominator + second_denominator, first_nominator + second_nominator
		, second_denominator, second_nominator);
	}
	else if (value < currentComp)
	{
		cout << "L";
		numberSystem(value, first_denominator, first_nominator, first_denominator + second_denominator, 
		first_nominator + second_nominator);
	}
	else
	{
		cout << endl;
		return;
	}
}
