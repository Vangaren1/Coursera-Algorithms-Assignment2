/* *****************************************************************************
 *  Name: Mark Thompson
 *  Date: 7/9/25
 *  Description: Algorithms part 1 Deque assignment
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {

    private class QNode {
        Item val;
        QNode next;
        QNode prev;
    }

    private class DequeIterator implements Iterator<Item> {
        private QNode curr = first;

        public boolean hasNext() {
            return curr != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (curr == null) throw new NoSuchElementException();
            Item i = curr.val;
            curr = curr.next;
            return i;
        }
    }

    private int queueSize;
    private QNode first, last;

    // construct an empty deque
    public Deque() {
        queueSize = 0;
        first = null;
        last = null;
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
        if (item == null) throw new IllegalArgumentException();

        QNode newNode = new QNode();
        newNode.val = item;
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        }
        else {
            newNode.next = first;
            first = newNode;
        }
        queueSize++;
    }


    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();

        QNode newNode = new QNode();
        newNode.val = item;
        if (isEmpty()) {
            last = newNode;
            first = newNode;
        }
        else {
            newNode.next = last;
            last = newNode;
        }
        queueSize++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (queueSize == 0) throw new NoSuchElementException();
        Item tmp = first.val;
        first = first.next;
        queueSize--;
        return tmp;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (queueSize == 0) throw new NoSuchElementException();
        Item tmp = last.val;
        last = last.prev;
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
