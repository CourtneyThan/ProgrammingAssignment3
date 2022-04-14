import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Node<Integer extends Comparable<Integer>> {
    int present; // Tag number of each present
    Node<Integer> next;
    boolean removed; // Boolean to indicate logical removal
    Lock lock;

    Node(int present) {
        this.present = present;
        this.next = null;
        this.removed = false;
        this.lock = new ReentrantLock();
    }

    void lock() {
        lock.lock();
    }

    void unlock() {
        lock.unlock();
    }
}
