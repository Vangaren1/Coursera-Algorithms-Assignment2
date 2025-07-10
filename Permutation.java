import edu.princeton.cs.algs4.StdIn;



public class Permutation {
    public static void main(String[] args) {

        RandomizedQueue<String> q = new RandomizedQueue<>();

        int count = Integer.parseInt(args[0]);

        while (!StdIn.isEmpty()) {
            String tmp = StdIn.readString();
            q.enqueue(tmp);
        }


        for (int c = 0; c < count; c++) {
            System.out.println(q.dequeue());
        }

    }
}
