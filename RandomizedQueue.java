import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item>  {
    private Deque<Item> theQ;
    // construct an empty randomized queue
    public RandomizedQueue(){
        theQ = new Deque<Item>();
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return theQ.isEmpty();
    }

    // return the number of items on the randomized queue
    public int size(){
        return theQ.size();
    }

    // add the item
    public void enqueue(Item item){
        theQ.addFirst(item);
    }

    // remove and return a random item
    public Item dequeue(){
        return null;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        return null;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return null;
    }

    public static void main(String[] args) {

    }
}
