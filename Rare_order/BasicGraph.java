package ICPC_graph_rareorder;

import java.util.*;
public class BasicGraph 
{
	private TreeMap<String, Graphnode> nodes;
	public BasicGraph()
	{
		this.nodes = new TreeMap<String, Graphnode>();
	}
	public boolean addNode(String label)
	{
		if (label == null)
		{
			throw new IllegalArgumentException();
		}
		if (hasNode(label))
		{
			return false;
		}
		Graphnode g = new Graphnode(label);
		nodes.put(label, g);
		return true;
	}
	public String removeNode(String label)
	{
		if (label == null)
		{
			throw new IllegalArgumentException();
		}
		if (!hasNode(label))
		{
			return null;
		}
		Graphnode node = findNode(label);
		TreeSet<Graphnode> successors = node.getSuccessors();
		for(Graphnode each : successors)
		{
			each.getParents().remove(node);
		}
		String returned = node.getData();
		nodes.remove(node.getData());
		return returned;
	}
	public boolean hasNode(String label)
	{
		return nodes.containsKey(label);
	}
	public boolean hasEdge(String sourceLabel, String targetLabel)
	{
		if (sourceLabel == null || targetLabel == null || !hasNode(sourceLabel) || !hasNode(targetLabel))
		{
			throw new IllegalArgumentException();
		}
		Graphnode temp = nodes.get(sourceLabel);
		Graphnode temp2 = nodes.get(targetLabel);
		return temp.hasSuccessors(temp2);
	}
	
	public boolean addEdge(String sourceLabel, String targetLabel)
	{
		if (sourceLabel == null || targetLabel == null || !hasNode(sourceLabel) || !hasNode(targetLabel))
		{
			throw new IllegalArgumentException();
		}
		Graphnode temp = nodes.get(sourceLabel);
		Graphnode temp2 = nodes.get(targetLabel);
		if (!hasEdge(sourceLabel, targetLabel))
		{
			temp.getSuccessors().add(temp2);
			temp2.addParents(sourceLabel);
			return true;
		}
		return false;
	}
	public List<String> dfs(String startLabel)
	{
		if (startLabel == null || !hasNode(startLabel))
		{
			throw new IllegalArgumentException();
		}
		List<String> visitedNodes = new ArrayList<String>();
		Graphnode temp = nodes.get(startLabel);
		dfs(temp, visitedNodes);
		this.clearMark();
		return visitedNodes;
	}
	private void dfs (Graphnode n, List<String> list) 
	{
		n.setVisited(true);
		list.add(n.getData());
		for (Graphnode m : n.getSuccessors()) 
		{
			if (! m.isVisited()) 
			{
				dfs(m, list);
			}
		}
	}
	public List<String> bfs(String startLabel)
	{
		if (startLabel == null || !hasNode(startLabel))
		{
			throw new IllegalArgumentException();
		}
		Graphnode temp = nodes.get(startLabel);
		Queue<Graphnode> queue = new LinkedList<Graphnode>();
		List<String> visitedList = new ArrayList<String>();
		temp.setVisited(true);
		visitedList.add(startLabel);
		queue.add(temp);
		while (!queue.isEmpty()) 
		{
			Graphnode current = queue.remove();
			for (Graphnode k : current.getSuccessors()) 
			{

				if (! k.isVisited())
				{
					k.setVisited(true);
					visitedList.add(k.getData());
					queue.add(k);
				} 
			} 
		} 
		this.clearMark();
		return visitedList;
	}
	public List<String> cyclicPath(String startLabel)
	{
		if (startLabel == null || !hasNode(startLabel))
		{
			throw new IllegalArgumentException();
		}
		List<String> cyclicPath = new ArrayList<String>();
		Graphnode temp = nodes.get(startLabel);
		cyclicPath.add(temp.getData());
		this.hasSelfCycle(temp, temp,cyclicPath);
		int fir = 1;
		int sec = cyclicPath.size() - 1;
		while (fir < sec)
		{
			String temp2 = cyclicPath.get(fir);
			cyclicPath.set(fir, cyclicPath.get(sec));
			cyclicPath.set(sec, temp2);
			fir++;
			sec--;
		}
		String first = cyclicPath.get(0);
		String last = cyclicPath.get(cyclicPath.size() - 1);
		this.clearMark();
		if (first.compareTo(last) == 0 && cyclicPath.size() != 1)
		{
			return cyclicPath;
		}
		else
		{
			return null;
		}

	}
	
	public boolean hasSelfCycle (Graphnode n, Graphnode originalNode, List<String> cyclicPath) 
	{
		n.setVisited(true);
		for (Graphnode m : n.getSuccessors()) 
		{
			if (m == originalNode)
			{
				cyclicPath.add(m.getData());
				return true;
			}
			if (! m.isVisited()) {
				if(hasSelfCycle(m, originalNode, cyclicPath))
				{
					cyclicPath.add(m.getData());
					return true;
				}
			}
		}
		return false;
	}
	public List<String> successors(String label)
	{
		if (label == null || !hasNode(label))
		{
			throw new IllegalArgumentException();
		}
		Graphnode temp = nodes.get(label);
		List<String> successors = new ArrayList<String>();
		for (Graphnode g : temp.getSuccessors())
		{
			successors.add(g.getData());
		}
		return successors;
	}
	public Iterator<String> iterator()
	{
		Set<String> keys = nodes.keySet();
		return keys.iterator();
	}
	public List<String> shortestPath(String startLabel, String finishLabel)
	{
		if (startLabel == null || finishLabel == null || !hasNode(startLabel) || !hasNode(finishLabel))
		{
			throw new IllegalArgumentException();
		}
		return findShortestPath(startLabel, finishLabel);
	}
	private List<String> findShortestPath(String startLabel, String finishLabel)
	{
		Graphnode temp = nodes.get(startLabel);
		TreeMap<String, String> mapForPredessors = new TreeMap<String, String>();
		Queue<Graphnode> queue = new LinkedList<Graphnode>();
		List<String> shortestPath = new ArrayList<String>();
		temp.setVisited(true);
		queue.add(temp);
		while (!queue.isEmpty()) 
		{
			Graphnode current = queue.remove();
			for (Graphnode k : current.getSuccessors()) 
			{
				if (! k.isVisited())
				{
					mapForPredessors.put(k.getData(), current.getData());			//store key(child), value(parent)
					k.setVisited(true);
					queue.add(k);
				} 
			} 
		}
		String child = new String(finishLabel);
		while(child.compareTo(startLabel) != 0) 
		{
			shortestPath.add(0, child);
			child = mapForPredessors.get(child);
		}
		shortestPath.add(0, child);
		this.clearMark();
		return shortestPath;
	}
	public int size()
	{
		return nodes.size();
	}
	public boolean isEmpty()
	{
		return (this.nodes.size() == 0);
	}
	private void clearMark()
	{
		for (String label : nodes.keySet())
		{
			Graphnode item = nodes.get(label);
			item.setVisited(false);
		}
	}
	public Graphnode findNode(String label)
	{
		return nodes.get(label);
	}
	public Graphnode rootNode()
	{
		//System.out.println(nodes.keySet());
		for(String label : nodes.keySet())
		{
			if(label != null)
			{
				Graphnode node = findNode(label);
				if(node.getParents().size() == 0)
				{
					//System.out.println(node);
					//	System.out.println(node.getData());
					return node;
				}
			}
		}
		return null;
	}
}
