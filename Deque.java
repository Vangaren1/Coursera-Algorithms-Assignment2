/* *****************************************************************************
 *  Name: Mark Thompson
 *  Date: 7/9/25
 *  Description: Algorithms part 1 Deque assignment
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {

    private class DequeIterator implements Iterator<Item> {

        int pos = first;
        int count = 0;
        public boolean hasNext() {
            return count < queueSize;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            Item tmp = itemArray[pos];
            pos = (pos + capacity + 1) % capacity;
            count++;
            return tmp;
        }
    }

    private int queueSize;
    private int capacity;
    private int first, last;
    private Item[] itemArray;

    // construct an empty deque
    public Deque() {
        capacity = 1;
        itemArray = (Item[]) new Object[capacity];
        queueSize = 0;
        this.first = 0;
        this.last = 0;
    }

    private void resize(int capacity) {
        Item[] newArray = (Item[]) new Object[capacity];
        for ( int index = 0; index < queueSize; index++ ) {
            newArray[index] = itemArray[index];
        }
        itemArray = newArray;
        this.capacity = capacity;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return queueSize == 0;
    }

    // return the number of items on the deque
    public int size() {
        return queueSize;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if ( queueSize == capacity ) resize( capacity * 2);

        if(queueSize == 0){
            itemArray[0] = item;
        } else{
            first = ( first + capacity - 1) % capacity;
            itemArray[first] = item;
        }
        queueSize++;
    }


    // add the item to the back
    public void addLast(Item item) {
        if ( queueSize == capacity ) resize( capacity * 2);

        if(queueSize == 0){
            itemArray[0] = item;
        } else {
            last = ( last + capacity + 1) % capacity;
            itemArray[last] = item;
        }
        queueSize++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if ( queueSize == 0) throw new NoSuchElementException();

        Item tmp = itemArray[first];
        itemArray[first] = null;
        first = (first + capacity + 1) % capacity;
        queueSize--;
        return tmp;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if ( queueSize == 0) throw new NoSuchElementException();

        Item tmp = itemArray[last];
        itemArray[last] = null;
        last = (last + capacity - 1) % capacity;
        queueSize--;
        return tmp;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    public static void main(String[] args) {
        Deque<String> d = new Deque<>();
        System.out.println(d.isEmpty());
        d.addFirst("first");
        System.out.println(d.size());
        d.addLast("last");
        d.addFirst("tmp");
        d.removeFirst();
        d.removeLast();
        for (String s : d) {
            System.out.println(s);
        }
    }
}
