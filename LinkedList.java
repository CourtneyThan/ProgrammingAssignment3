public class LinkedList<Integer extends Comparable<Integer>> {
    
    private Node<Integer> head;

    public LinkedList() {
        this.head = new Node<>(0);
        this.head.next = new Node<>(500001);
    }


    // Insert a given present tag number into the linked list by numerical order
    // Lock the previous and current node while inserting
    // Unlock once the node has been added into the linked list
    public boolean insert(int present) {

        while (true) {
            Node<Integer> pred = this.head;
            Node<Integer> curr = head.next;

            while (curr.present < present) {
                pred = curr;
                curr = curr.next;
            }

            pred.lock();
            try {
                curr.lock();
                try {
                    if (!pred.removed && !curr.removed && pred.next == curr) {
                        if (curr.present == present) {
                            return false;
                        } else {
                            Node<Integer> node = new Node<>(present);
                            node.next = curr;
                            pred.next = node;
                            return true;
                        }
                    }
                } finally {
                    curr.unlock();
                }
            } finally {
                pred.unlock();
            }
        }
    }


    // Function to remove a node from the list given the present tag
    // Lock the previous and current node while deleting
    // Unlock once the node has been deleted into the linked list
    public boolean remove (int present) {
        while (true) {
            Node<Integer> pred = this.head;
            Node<Integer> curr = head.next;

            while (curr.present < present) {
                pred = curr;
                curr = curr.next;
            }

            pred.lock();
            try {
                curr.lock();
                try {
                    if (!pred.removed && !curr.removed && pred.next == curr) {
                        if (curr.present != present) {
                            return false;
                        }
                    } else {
                        curr.removed = true;
                        pred.next = curr.next;
                        return true;
                    }
                } finally {
                    curr.unlock();
                }
            } finally {
                pred.unlock();
            }
        }
    }


    // Remove the last node from the linked list
    public boolean removeLast () {
        while (true) {
            Node<Integer> pred = this.head;
            Node<Integer> curr = head.next;

            while (curr.next != null) {
                pred = curr;
                curr = curr.next;
            }
            
            pred.lock();
            try {
                curr.lock();
                try {
                    curr.removed = true;
                    pred.next = curr.next;
                    return true;
                } finally {
                    curr.unlock();
                }
            } finally {
                pred.unlock();
            }
        }
    }


    // Check if the linked list contains a given value
    public boolean contains(int present) {
		Node<Integer> curr = this.head;
		while (curr.present < present)
			curr = curr.next;
		return curr.present == present && !curr.removed;
	}

    @Override
	public synchronized String toString() {
		Node<Integer> tmp = head.next;
		StringBuilder sb = new StringBuilder("");
		while (tmp != null) {
			sb.append(tmp.present + " -> ");
			tmp = tmp.next;
		}
		return sb.toString();
	}
    
}
