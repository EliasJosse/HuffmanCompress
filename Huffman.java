import java.util.PriorityQueue; 
import java.util.Scanner;
import java.util.TreeMap;
import java.io.Serializable;
import java.util.Comparator; 
  


class HuffmanNode implements Serializable { 
  
    int data; 
    char c; 
  
    HuffmanNode left; 
    HuffmanNode right; 
} 
  




class MyComparator implements Comparator<HuffmanNode> { 
    public int compare(HuffmanNode x, HuffmanNode y) 
    { 
        return x.data - y.data; 
    } 
} 




  
public class Huffman implements Serializable { 
	
	HuffmanNode root = null;
  

    public static void printCode(HuffmanNode rootE, String s) 
    { 
        if (rootE.left 
                == null
            && rootE.right 
                   == null
            && Character.isLetter(rootE.c)) { 
  
            // c is the character in the node 
            System.out.println(rootE.c + ":" + s); 
  
            return; 
        } 
  
        printCode(rootE.left, s + "0"); 
        printCode(rootE.right, s + "1"); 
    } 
    
    
    public void getNewEncoding(HuffmanNode rootE,TreeMap<Integer, String> map, String s)	{
        if (rootE.left 
                == null
            && rootE.right 
                   == null
            && Character.isLetter(rootE.c)) { 
  
        	map.put((int) rootE.c, s);
            return; 
        }
  
        getNewEncoding(rootE.left, map, s + "0");
        getNewEncoding(rootE.right, map, s + "1");
    }
    
    
    
    public Huffman(int size, char[] chars, int[] nums){
        PriorityQueue<HuffmanNode> q 
        = new PriorityQueue<HuffmanNode>(size, new MyComparator()); 

        for (int i = 0; i < size; i++) { 

	        // creating a Huffman node object 
	        // and add it to the priority queue. 
	        HuffmanNode hn = new HuffmanNode(); 
	
	        hn.c = chars[i]; 
	        hn.data = nums[i]; 
	
	        hn.left = null; 
	        hn.right = null; 
	
	        // add functions adds 
	        // the huffman node to the queue. 
	        q.add(hn); 
    	} 

	
	    // Here we will extract the two minimum value 
	    // from the heap each time until 
	    // its size reduces to 1, extract until 
	    // all the nodes are extracted. 
	    while (q.size() > 1) { 

	        // first min extract. 
	        HuffmanNode x = q.peek(); 
	        q.poll(); 
	
	        // second min extarct. 
	        HuffmanNode y = q.peek(); 
	        q.poll(); 
	
	        // new node f which is equal 
	        HuffmanNode f = new HuffmanNode(); 
	
	        // to the sum of the frequency of the two nodes 
	        // assigning values to the f node. 
	        f.data = x.data + y.data; 
	        f.c = '-'; 
	
	        // first extracted node as left child. 
	        f.left = x; 
	
	        // second extracted node as the right child. 
	        f.right = y; 
	
	        // marking the f node as the root node. 
	        root = f; 
	
	        // add this node to the priority-queue. 
	        q.add(f); 
	    } 
    	
    }
} 