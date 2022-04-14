import java.util.*;
import java.lang.*;
import java.util.concurrent.atomic.AtomicBoolean;

class Presents {
    static int MAX = 500000;
    static int NUM_THREADS = 4;
    private static LinkedList<Integer> list = new LinkedList<>();

    public static class Threading extends Thread {
        int present;
        public Threading(int present) {
            this.present = present;
        }
        
        public void run() {
            if (Thread.currentThread().getName().equals("insert")) { // Insert
                list.insert(present);
                System.out.println("Added: " + present);
            } else { // Remove
                list.removeLast();
                System.out.println("Removed last item");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Create an array list with numbers 1 to MAX and shuffle them in random order
        ArrayList<Integer> insert_numbers = new ArrayList<>();
        boolean turn = true;
        
        for (int i = 1; i <= MAX; i++) {
            insert_numbers.add(i);
        }
        Collections.shuffle(insert_numbers);

        // Create an array of 4 threads
        Thread[] threads = new Thread[NUM_THREADS];
        // System.out.println(insert_numbers);

        // Keep adding and removing nodes until there are no numbers in the 
        // random list
        // NOTE: I could not get remove to work so only insertion is performed
        while (insert_numbers.size() > 0) {
            if (turn) {
                threads[0] = new Thread(new Threading(insert_numbers.get(0)), "insert"); // Insert
                if (insert_numbers.size() > 1) {
                    threads[1] = new Thread(new Threading(insert_numbers.get(1)), "insert"); // Insert
                }
                threads[2] = new Thread(new Threading(0), "remove"); // Remove
                threads[3] = new Thread(new Threading(0), "remove"); // Remove

                threads[0].start();
                threads[0].join();
                threads[1].start();
                threads[1].join();
                // threads[2].start();
                // threads[3].start();
            } else { // Remove
                threads[0] = new Thread(new Threading(0), "remove"); // Remove
                threads[1] = new Thread(new Threading(0), "remove"); // Remove
                threads[2] = new Thread(new Threading(insert_numbers.get(0)), "insert"); // Insert
                if (insert_numbers.size() > 1) {
                    threads[3] = new Thread(new Threading(insert_numbers.get(1)), "insert"); // Insert
                }

                // threads[0].start();
                // threads[1].start();
                threads[2].start();
                threads[2].join();
                threads[3].start();
                threads[3].join();
            }

            // Flip the boolean so workers alternate
            if (turn){
                turn = false;
            } else {
                turn = true;
            }

            // Remove inserted items from the unordered bag
            insert_numbers.remove(0);
            insert_numbers.remove(0);
        }

        list.removeLast();
        System.out.println("\nPresent Chain: " + list);
    }
}