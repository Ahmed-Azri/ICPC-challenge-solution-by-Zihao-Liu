package ICPC_graph_rareorder;

import java.util.*;
public class Graphnode implements Comparable<Object> 
{
	private String data;
	private TreeSet<Graphnode> parents;
	private TreeSet<Graphnode> successors;
	private boolean visited;
	public Graphnode(String data)
	{
		if (data == null)
		{
			throw new IllegalArgumentException();
		}
		this.data = data;
		this.successors = new TreeSet<Graphnode>();
		this.parents = new TreeSet<Graphnode>();
		this.visited = false;
	}
	public void setData(String data)
	{
		if (data == null)
		{
			throw new IllegalArgumentException();
		}
		this.data = data;
	}
	public String getData()
	{
		return this.data;
	}
	public void setVisited(boolean input)
	{
		this.visited = input;
	}
	public boolean isVisited()
	{
		return this.visited;
	}
	public void addSuccessors(String newMember)
	{
		if (newMember == null)
		{
			throw new IllegalArgumentException();
		}
		Graphnode newNode = new Graphnode(newMember);
		newNode.parents.add(this);
		this.successors.add(newNode);
	}
	public TreeSet<Graphnode> getSuccessors()
	{
		return this.successors;
	}
	public boolean hasSuccessors(Graphnode node)
	{
		if (node == null)
		{
			throw new IllegalArgumentException();
		}
		for (Graphnode item : this.successors)
		{
			if (item.getData().equals(node.getData()))
			{
				return true;
			}
		}
		return false;
	}
	public int compareTo(Object other)
	{
		if (!(other instanceof Graphnode))
		{
			throw new ClassCastException();		
		}
		else
		{
			Graphnode otherNode = (Graphnode) other;
			if (this.data.compareTo(otherNode.getData()) < 0)
			{
				return -1;
			}
			else if (this.data.compareTo(otherNode.getData()) > 0)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
	}
	public TreeSet<Graphnode> getParents()
	{
		return this.parents;
	}
	public void addParents(String label)
	{
		this.parents.add(new Graphnode(label));
	}
}

