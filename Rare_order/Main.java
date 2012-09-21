package ICPC_graph_rareorder;
import java.util.*;
/* 
 * this is implemented by Spencer and Allen
 * 
 * All right reserved
 * 
 */
public class Main 
{
	public static void main(String args[])
	{
		BasicGraph graph = constructGraph();
		List<String> resultList = new ArrayList<String>(0);
		Iterator<String> iter = graph.iterator();
		/*while(iter.hasNext())
		{
			String item = iter.next();
			Graphnode node = graph.findNode(item);
			//System.out.println("The node " + node.getData() + " has");
			for(Graphnode item1 : node.getSuccessors())
			{
				System.out.println(item1.getData());
			}
		}
		 */
		while (graph.size() != 0)
		{
			Graphnode root = graph.rootNode();
			resultList.add(graph.removeNode(root.getData()));
		}
		for(int i = 0; i < resultList.size(); i++)
		{
			System.out.print(resultList.get(i));
		}
	}
	public static ArrayList<String> convert(char[] a)
	{
		ArrayList<String> list = new ArrayList<String>(0);
		for (int i = 0; i < a.length; i++)
		{
			list.add(Character.toString(a[i]));
		}
		return list;
	}
	public static int getMaxSize(ArrayList<ArrayList<String>> superList)
	{
		int max = 0;
		for(ArrayList<String> list : superList)
		{
			if(list.size() > max)
			{
				max = list.size();
			}
		}
		return max;
	}
	public static BasicGraph constructGraph()
	{
		ArrayList<ArrayList<String>> superList = new ArrayList<ArrayList<String>>(0);
		ArrayList<ArrayList<String>> superList2 = new ArrayList<ArrayList<String>>(0);
		//get in the input first
		Scanner in = new Scanner(System.in);
		String line = in.nextLine().trim();
		while(!line.equals("#"))
		{
			char[] lines = line.toCharArray();
			superList.add(convert(lines));
			line = in.nextLine();
		}
		BasicGraph graph = new BasicGraph();
		if(superList.size() != 0)
		{

			ArrayList<String> list = new ArrayList<String>(0);
			for(int i = 0; i < getMaxSize(superList); i++)
			{
				for(ArrayList<String> eachTurn : superList)
				{
					if(i < eachTurn.size())
					{
						list.add(eachTurn.get(i));
					}
					else
					{
						list.add("0");
					}
				}
				superList2.add(list);
				list = new ArrayList<String>(0);
			}
			ArrayList<String> first = superList2.get(0);
			int index = 1, i = 0, j = 1;
			while(j < first.size())
			{
				//System.out.println("first: i j " + first.get(i) + " " + first.get(j));
				//System.out.println("Does graph has node : " + first.get(i) + " " + graph.hasNode(first.get(i)));
				//System.out.println("Does graph has node : " + first.get(j) + " " + graph.hasNode(first.get(j)));
				if(!first.get(i).equals(first.get(j)))
				{
					if(!graph.hasNode(first.get(i)) && !graph.hasNode(first.get(j)))
					{
						graph.addNode(first.get(i));
						graph.addNode(first.get(j));
						graph.addEdge(first.get(i), first.get(j));
					}
					else if (graph.hasNode(first.get(i)) && !graph.hasNode(first.get(j)))
					{
						graph.addNode(first.get(j));
						graph.addEdge(graph.findNode(first.get(i)).getData(), first.get(j));
					}
					else if(!graph.hasNode(first.get(i)) && graph.hasNode(first.get(j)))
					{
						graph.addNode(first.get(i));
						graph.addEdge(first.get(i), graph.findNode(first.get(j)).getData());
					}
					else {
						graph.addEdge(graph.findNode(first.get(i)).getData(), graph.findNode(first.get(j)).getData());
					}
				}
				else
				{
					boolean quit = false;
					while(index < superList2.size() && !quit)
					{
						ArrayList<String> nextList = superList2.get(index);
						if(!nextList.get(i).equals(nextList.get(j)))
						{
							//System.out.println("The next level: " + nextList.get(i) + " " + nextList.get(j));
							//System.out.println("Does graph has node : " + nextList.get(i) + " " + graph.hasNode(nextList.get(i)));
							//System.out.println("Does graph has node : " + nextList.get(j) + " " + graph.hasNode(nextList.get(j)));
							if (nextList.get(i).equals("0") || nextList.get(j).equals("0"))
							{	
								break;
							}
							else if(!graph.hasNode(nextList.get(i)) && !graph.hasNode(nextList.get(j)))
							{	
								graph.addNode(nextList.get(i));
								graph.addNode(nextList.get(j));
								graph.addEdge(nextList.get(i), nextList.get(j));
							}
							else if (graph.hasNode(nextList.get(i)) && !graph.hasNode(nextList.get(j)))
							{
								//System.out.println("Exist before: " + graph.findNode(nextList.get(i)).getData());
								graph.addNode(nextList.get(j));
								graph.addEdge(graph.findNode(nextList.get(i)).getData(), nextList.get(j));
							}
							else if (!graph.hasNode(nextList.get(i)) && graph.hasNode(nextList.get(j)))
							{
								//System.out.println("Exist before: " + graph.findNode(nextList.get(j)).getData());
								graph.addNode(nextList.get(i));
								graph.addEdge(nextList.get(i), graph.findNode(nextList.get(j)).getData());
							}
							else if (graph.hasNode(nextList.get(i)) && graph.hasNode(nextList.get(j)))
							{
								graph.addEdge(graph.findNode(nextList.get(i)).getData(), graph.findNode(nextList.get(j)).getData());
							}
							quit = true;
						}
						else
						{
							if(!graph.hasNode(nextList.get(i)) && !nextList.get(i).equals("0") && !nextList.get(j).equals("0"))
							{
								graph.addNode(nextList.get(i));
							}
						}
						index++;
					}
				}
				index = 1;
				i++;
				j++;
			}
		}
			return graph;
		
	}
}
