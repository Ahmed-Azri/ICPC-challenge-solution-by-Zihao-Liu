#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include <stdlib.h>
using namespace std;

/**
 * get all of the combination of a sequence of numbers
 * @param combinations to store all of the combination
 * @param numbers      numbers considered currently
 * @param choose       choose how many numbers this turn?
 */
void get_all_combination(vector< vector<int> > & combinations, vector<int> numbers, int choose)
{
	if (choose == 1)
	{
		for(int i = 0; i < (int)numbers.size(); i++)
		{
			vector<int> temp;
			temp.push_back(numbers[i]);
			combinations.push_back(temp);
		}
	}
	else if (choose == (int)numbers.size())
	{
		vector<int> temp;
		for (vector<int>::iterator i = numbers.begin(); i != numbers.end(); ++i)
		{
			temp.push_back(*i);
		}
		combinations.push_back(temp);
	}
	else 
	{
		int first = numbers[0];
		numbers.erase(numbers.begin());
		get_all_combination(combinations, numbers, choose - 1);
		for (vector< vector<int> >::iterator i = combinations.begin(); i != combinations.end(); ++i)
		{
			if ((int)(*i).size() == choose - 1)
			{
				(*i).push_back(first);
			}
		}
		get_all_combination(combinations, numbers, choose);
	}
}

int main(int argc, char* argv[])
{
	string N, number;
	bool yes = false;
	vector<int> numbers;
	vector< vector<int> > combinations;
	while(getline(cin, N) && N != "0")
	{
		int n = atoi(N.c_str());
		while(getline(cin, number))
		{
			string buffer;
			stringstream ss(number);
			while(ss >> buffer)
			{
				numbers.push_back(atoi(buffer.c_str()));
			}
			break;	
		}
		get_all_combination(combinations, numbers, n);
		for(int i = 0; i < (int)combinations.size(); i++)
		{
			vector<int> temp = combinations[i];
			int sum = 0;
			for (int i = 0; i < (int) temp.size(); ++i)
			{
				sum += temp[i];
			}
			if (sum % n == 0)
			{
				yes = true;
				cout << "Yes" << endl;
				for (int i = 0; i < (int)temp.size(); ++i)
				{
					if (i != (int)temp.size() - 1)
					{
						cout << temp[i] << " ";
					}
					else
					{
						cout << temp[i];
					}
				}
				cout << endl;
				break;
			}
		}
		if (!yes)
		{
			cout << "No" << endl;
		}
		numbers.clear();
	}
	return 0;
}

