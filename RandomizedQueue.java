import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int queueSize;
    private int capacity;
    private int last;
    private Item[] itemArray;

    // construct an empty randomized queue
    public RandomizedQueue() {
        capacity = 1;
        itemArray = (Item[]) new Object[capacity];
        queueSize = 0;
        this.last = 0;
    }

    private void resize(int cap) {
        if (cap < 1) cap = 1;
        if (cap < queueSize) throw new IndexOutOfBoundsException();

        Item[] newArray = (Item[]) new Object[cap];
        for (int index = 0; index < queueSize; index++) {
            newArray[index] = itemArray[index];
        }
        itemArray = newArray;
        this.capacity = cap;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return queueSize == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return queueSize;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();

        if (queueSize == capacity) resize(capacity * 2);

        if (queueSize == 0) {
            itemArray[0] = item;
        } else {
            last++;
            itemArray[last] = item;
        }
        queueSize++;
    }

    private void swap(int a, int b) {
        Item tmp = itemArray[a];
        itemArray[a] = itemArray[b];
        itemArray[b] = tmp;
    }

    // remove and return a random item
    public Item dequeue() {
        if (queueSize == 0) throw new NoSuchElementException();

        // pick a random index of the array
        int index = StdRandom.uniformInt(queueSize);
        // swap it to the end
        swap(index, last);

        // remove the end
        Item tmp = itemArray[last];
        itemArray[last] = null;
        if (last > 0) last--;
        queueSize--;

        // resize if size < capacity / 4
        if (queueSize <= (capacity / 4)) resize(capacity/2);

        return tmp;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (queueSize == 0) throw new NoSuchElementException();
        // pick a random index of the array
        int index = StdRandom.uniformInt(queueSize);
        return itemArray[index];
    }

    private class RandomQueIterator implements Iterator<Item> {

        Item[] tmp;
        Item temp;
        int pos;
        public RandomQueIterator() {
            pos = 0;
            tmp = (Item[]) new Object[queueSize];
            for (int index = 0; index < queueSize; index++) {
                tmp[index] = itemArray[index];
            }
            StdRandom.shuffle(tmp);
        }

        public boolean hasNext() {
            return pos < queueSize;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (pos == queueSize) throw new NoSuchElementException();
            temp = tmp[pos];
            pos++;
            return temp;
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomQueIterator();
    }

    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");
        Iterator<String> test1 = queue.iterator();
        while (test1.hasNext()) {
            System.out.println(test1.next());
        }
        Iterator<String> test2 = queue.iterator();
        while (test2.hasNext()) {
            System.out.println(test2.next());
        }
    }
}
