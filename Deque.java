/* *****************************************************************************
 *  Name: Mark Thompson
 *  Date: 7/9/25
 *  Description: Algorithms part 1 Deque assignment
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    // construct an empty deque
    public Deque() {
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node tmp = new Node();
        tmp.val = item;
        if (size == 0) {
            first = tmp;
            last = tmp;
        } else {
            tmp.next = first;
            first.prev = tmp;
            first = tmp;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node tmp = new Node();
        tmp.val = item;
        if (size == 0) {
            first = tmp;
            last = tmp;
        } else {
            tmp.prev = last;
            last.next = tmp;
            last = tmp;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) throw new NoSuchElementException();

        Node tmp = first;
        first = first.next;
        if (first != null) first.prev = null;
        size--;

        if (size == 0) {
            first = null;
            last = null;
        }

        tmp.next = null;
        tmp.prev = null;

        return tmp.val;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) throw new NoSuchElementException();

        Node tmp = last;
        last = last.prev;
        if (last != null) last.next = null;
        size--;

        if (size == 0) {
            first = null;
            last = null;
        }

        tmp.prev = null;
        tmp.next = null;
        return tmp.val;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class Node {
        private Item val;
        private Node next;
        private Node prev;
    }

    private class DequeIterator implements Iterator<Item> {

        Node curr;
        public DequeIterator() {
            curr = first;
        }

        public boolean hasNext() {
            if (last == null) return false;
            return curr != last.next;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            Item tmp = curr.val;
            curr = curr.next;

            return tmp;
        }


    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        deque.addFirst("IBATXMGMSB");
        deque.addLast("JIXEHETMDN");
        deque.addFirst("EPVLOOBTGN");
        deque.addLast("XGEEXRYBOL");
        deque.removeLast();
        deque.removeLast();
        deque.removeFirst();
        deque.removeLast();
    }

}
