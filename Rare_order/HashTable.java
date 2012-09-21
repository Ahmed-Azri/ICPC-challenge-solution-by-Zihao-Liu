package ICPC_graph_rareorder;

import java.io.*;
import java.util.LinkedList;

///////////////////////////////////////////////////////////////////////////////
//ALL STUDENTS COMPLETE THESE SECTIONS
//Main Class File:  TestHash.java
//File:             Hashtable.java
//Semester:         spring 2012
//
//Author:           Zihao Liu; zliu224@wisc.edu
//CS Login:         zihao
//Lecturer's Name:  Beck Hasti
//Lab Section:      NA
//
//PAIR PROGRAMMERS COMPLETE THIS SECTION
//Pair Partner:     Liqi Xu; 
//CS Login:         liqi
//Lecturer's Name:  Beck Hasti
//Lab Section:      NA
//
//STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
//Credits:          (list anyone who helped you write your program)
////////////////////////////80 columns wide //////////////////////////////////

/**
 * This class implements a hashtable that using chaining for collision handling.
 * Any non-<tt>null</tt> item may be added to a hashtable.  Chains are 
 * implemented using <tt>LinkedList</tt>s.  When a hashtable is created, its 
 * initial size, maximum load factor, and (optionally) maximum chain length are 
 * specified.  The hashtable can hold arbitrarily many items and resizes itself 
 * whenever it reaches its maximum load factor or whenever it reaches its 
 * maximum chain length (if a maximum chain length has been specified).
 * 
 * Note that the hashtable allows duplicate entries.
 */
public class HashTable<T> 
{
	private LinkedList<T>[] arr;
	private int tableSize;
	private double maxLoadFactor;
	private int maxChainLength;
	private int numItems;
    
    /**
     * Constructs an empty hashtable with the given initial size, maximum load
     * factor, and no maximum chain length.  The load factor should be a real 
     * number greater than 0.0 (not a percentage).  For example, to create a 
     * hash table with an initial size of 10 and a load factor of 0.85, one 
     * would use:
     * 
     * <dir><tt>HashTable ht = new HashTable(10, 0.85);</tt></dir>
     *
     * @param initSize the initial size of the hashtable.
     * @param loadFactor the load factor expressed as a real number.
     * @throws IllegalArgumentException if <tt>initSize</tt> is less than or 
     *         equal to 0 or if <tt>loadFactor</tt> is less than or equal to 0.0
     **/
    public HashTable(int initSize, double loadFactor) 
    {
    	if (initSize <= 0 || loadFactor <= 0.0)
    	{
    		throw new IllegalArgumentException();
    	}
    	this.tableSize = initSize;
    	arr = (LinkedList<T>[])(new LinkedList[tableSize]);
    	for (int i = 0; i < tableSize; i++)
    	{
    		arr[i] = new LinkedList<T>();
    	}
    	this.maxLoadFactor = loadFactor;
    }
    
    
    /**
     * Constructs an empty hashtable with the given initial size, maximum load
     * factor, and maximum chain length.  The load factor should be a real 
     * number greater than 0.0 (and not a percentage).  For example, to create 
     * a hash table with an initial size of 10, a load factor of 0.85, and a 
     * maximum chain length of 20, one would use:
     * 
     * <dir><tt>HashTable ht = new HashTable(10, 0.85, 20);</tt></dir>
     *
     * @param initSize the initial size of the hashtable.
     * @param loadFactor the load factor expressed as a real number.
     * @param maxChainLength the maximum chain length.
     * @throws IllegalArgumentException if <tt>initSize</tt> is less than or 
     *         equal to 0 or if <tt>loadFactor</tt> is less than or equal to 0.0 
     *         or if <tt>maxChainLength</tt> is less than or equal to 0.
     **/
    public HashTable(int initSize, double loadFactor, int maxChainLength) 
    {
    	if (initSize <= 0 || loadFactor <= 0.0 || maxChainLength <= 0)
    	{
    		throw new IllegalArgumentException();
    	}
    	this.tableSize = initSize;
    	arr = (LinkedList<T>[])(new LinkedList[tableSize]);
    	for (int i = 0; i < tableSize; i++)
    	{
    		arr[i] = new LinkedList<T>();
    	}
    	this.maxLoadFactor = loadFactor;
    	this.maxChainLength = maxChainLength;
    }
    
    
    /**
     * Determines if the given item is in the hashtable and returns it if 
     * present.  If more than one copy of the item is in the hashtable, the 
     * first copy encountered is returned.
     *
     * @param item the item to search for in the hashtable.
     * @return the item if it is found and <tt>null</tt> if not found.
     **/
    public T lookup(T item) 
    {
    	int hashCode = item.hashCode();
    	int tableIndex = hashCode % this.tableSize;
    	if (tableIndex < 0)
    	{
    		tableIndex += this.tableSize;
    	}
    	LinkedList<T> list = arr[tableIndex];
    	for (T each : list)
    	{
    		if (each.equals(item))
    		{
    			return item;
    		}
    	}
    	return null;
    }
    
    
    /**
     * Inserts the given item into the hashtable.  The item cannot be 
     * <tt>null</tt>.  If there is a collision, the item is added to the end of
     * the chain.
     * <p>
     * If the load factor of the hashtable after the insert would exceed 
     * (not equal) the maximum load factor (given in the constructor), then the 
     * hashtable is resized.  
     * 
     * If the maximum chain length of the hashtable after insert would exceed
     * (not equal) the maximum chain length (given in the constructor), then the
     * hashtable is resized.
     * 
     * When resizing, to make sure the size of the table is reasonable, the new 
     * size is always 2 x <i>old size</i> + 1.  For example, size 101 would 
     * become 203.  (This guarantees that it will be an odd size.)
     * </p>
     * <p>Note that duplicates <b>are</b> allowed.</p>
     *
     * @param item the item to add to the hashtable.
     * @throws NullPointerException if <tt>item</tt> is <tt>null</tt>.
     **/
    public void insert(T item) 
    {
    	if (item == null)
    	{
    		throw new NullPointerException();
    	}
    	int hashCode = item.hashCode();
    	int tableIndex = hashCode % this.tableSize;
    	if (tableIndex < 0)
    	{
    		tableIndex += this.tableSize;
    	}
    	LinkedList<T> list = arr[tableIndex];
    	int numItemsAfterInsert = this.numItems + 1;
    	int listSizeAfterInsert = list.size() + 1;
    	if ((double)(numItemsAfterInsert) / this.tableSize > this.maxLoadFactor || this.maxChainLength != 0 && listSizeAfterInsert > this.maxChainLength)
    	{
    		LinkedList<T>[] temp = (LinkedList<T>[])(new LinkedList[2 * this.tableSize + 1]);
    		this.tableSize = 2 * this.tableSize + 1;
    		for (int i = 0; i < temp.length; i++)
    		{
    			temp[i] = new LinkedList<T>();
    		}
    		for (LinkedList<T> each : this.arr)
    		{
    			for (T individual : each)
    			{
    				int hashCodeForIndividual = individual.hashCode();
    				int tableIndexForIndividual = hashCodeForIndividual % this.tableSize;
    	    		if (tableIndexForIndividual < 0)
    	    		{
    	    			tableIndexForIndividual += this.tableSize;
    	    		}
    	        	LinkedList<T> listTemp = temp[tableIndexForIndividual];
    	    		listTemp.addLast(individual);
    			}
    		}
    		int hashCodeForItem = item.hashCode();
    		int tableIndexForItem = hashCodeForItem % this.tableSize;
    		if (tableIndexForItem < 0)
    		{
    			tableIndexForItem += this.tableSize;
    		}
    		LinkedList<T> listTemp = temp[tableIndexForItem];
    		listTemp.addLast(item);
        	this.arr = temp;
    	}
    	else 
    	{
    		list.addLast(item);
    	}
    	this.numItems++;
    }
    
    
    /**
     * Removes and returns the given item from the hashtable.  If the item is 
     * not in the hashtable, <tt>null</tt> is returned.  If more than one copy 
     * of the item is in the hashtable, only the first copy encountered is 
     * removed and returned.
     *
     * @param item the item to delete in the hashtable.
     * @return the removed item if it was found and <tt>null</tt> if not found.
     **/
    public T delete(T item) 
    {
    	if (item == null)
    	{
    		throw new NullPointerException();
    	}
    	int hashCode = item.hashCode();
    	int tableIndex = hashCode % this.tableSize;
    	if (tableIndex < 0)
    	{
    		tableIndex += this.tableSize;
    	}
    	LinkedList<T> list = arr[tableIndex];
    	boolean isReturn = list.remove(item);
    	if (isReturn)
    	{
    		this.numItems--;
    		return item;
    	}
    	return null;
    }
    
    
    /**
     * Prints all the items in the hashtable to the <tt>PrintStream</tt> 
     * supplied.  The items are printed in the order determined by the index of
     * the hashtable where they are stored (starting at 0 and going to 
     * (table size - 1)).  The values at each index are printed according 
     * to the order in the <tt>LinkedList</tt> starting from the beginning. 
     *
     * @param out the place to print all the output.
     **/
    public void dump(PrintStream out) 
    {
    	for (int i = 0; i < tableSize; i++)
    	{
    		if (!arr[i].isEmpty())
    		{
    			String temp = "";
    			LinkedList<T> list = arr[i];
    			temp = i + ": [";
    			for (T each : list)
    			{
    				temp += each + ", ";
    			}
    			temp = temp.substring(0, temp.length() - 2); 				//strip off comma and whitespace
    			temp += "]";
    			out.println(temp);
    		}
    	}
    }
    
  
    /**
     * Prints statistics about the hashtable to the <tt>PrintStream</tt> 
     * supplied.  The statistics displayed are: 
     * <ul>
     * <li>the current table size
     * <li>the number of items currently in the table 
     * <li>the current load factor
     * <li>the length of the largest chain
     * <li>the number of chains of length 0
     * <li>the average length of the chains of length > 0
     * </ul>
     *
     * @param out the place to print all the output.
     **/
    public void displayStats(PrintStream out) 
    {
    	int numOfZeroChain = 0;
        int longestChainLength = 0;
        int totalLengthForNonZeroChainLength = 0;
        int numOfNonZeroChain = 0;
        for (LinkedList<T> each : arr)
        {
        	if (each.size() > longestChainLength)
        	{
        		longestChainLength = each.size();
        	}
        	if (each.size() == 0)
        	{
        		numOfZeroChain++;
        	}
        	if (each.size() != 0)
        	{
        		numOfNonZeroChain++;
        		totalLengthForNonZeroChainLength += each.size();
        	}
        }
        out.println("Hashtable statistics:");
        out.println("  current table size:       " + this.tableSize);
        out.println("  # items in table:         " + this.numItems);
        out.println("  current load factor:      " +  (double)(this.numItems) / this.tableSize);
        out.println("  longestChainLength:       " + longestChainLength);
        out.println("  # 0-length chains:        " + numOfZeroChain);
        out.println("  avg (non-0) chain length: " + (double) (totalLengthForNonZeroChainLength)/numOfNonZeroChain);
    }
}

