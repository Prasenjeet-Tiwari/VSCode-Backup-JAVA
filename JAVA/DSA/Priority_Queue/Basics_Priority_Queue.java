// ============================================================
// 📘 Priority Queue & Heap — Full Notes + Implementations
// ============================================================

import java.util.*;

// ============================================================
// 🧩 Task Class implementing Comparable (min-heap by priority)
// ============================================================
class Task implements Comparable<Task> {
    String name;
    int priority; // smaller value = higher priority

    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public int compareTo(Task other) {
        return this.priority - other.priority; // min-heap order
    }
}

// ============================================================
// 🧩 TaskComparator — reverses order (max-heap)
// ============================================================
class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task a, Task b) {
        return b.priority - a.priority; // reverse for max-heap
    }
}

// ============================================================
// 🧩 FlexibleHeap — Manual Heap using ArrayList
// ============================================================
// Can act as min-heap or max-heap based on constructor flag



// ============================================================
class FlexibleHeap {

    //Please refer Heaps.java

    private ArrayList<Integer> heap;
    private boolean isMaxHeap;

    public FlexibleHeap(boolean isMaxHeap) {
        this.isMaxHeap = isMaxHeap;
        heap = new ArrayList<>();
        heap.add(0); // dummy element for 1-based indexing
    }

    public boolean isEmpty() {
        return heap.size() == 1;
    }

    public void insert(int val) {
        heap.add(val);
        heapifyUp(heap.size() - 1);
    }

    public int peek() {
        if (isEmpty()) return -1;
        return heap.get(1);
    }

    public int remove() {
        if (isEmpty()) return -1;

        int root = heap.get(1);
        int last = heap.remove(heap.size() - 1);

        if (!isEmpty()) {
            heap.set(1, last);
            heapifyDown(1);
        }

        return root;
    }

    private void heapifyUp(int i) {
        while (i > 1 && compare(heap.get(i), heap.get(i / 2))) {
            swap(i, i / 2);
            i = i / 2;
        }
    }

    private void heapifyDown(int i) {
        while (true) {
            int left = 2 * i;
            int right = 2 * i + 1;
            int extreme = i;

            if (left < heap.size() && compare(heap.get(left), heap.get(extreme))) extreme = left;
            if (right < heap.size() && compare(heap.get(right), heap.get(extreme))) extreme = right;

            if (extreme != i) {
                swap(i, extreme);
                i = extreme;
            } else break;
        }
    }

    private boolean compare(int child, int parent) {
        return isMaxHeap ? child > parent : child < parent;
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}

// ============================================================
// 📗 Main Class — Driver Program
// ============================================================
public class Basics_Priority_Queue{
    public static void main(String[] args) {

        // --- 1. Built-in Min-Heap Priority Queue ---
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        minPQ.add(4);
        minPQ.add(1);
        minPQ.add(3);
        minPQ.add(2);

        System.out.println("🔹 Built-in PriorityQueue (Min-Heap)");
        System.out.println("Peek: " + minPQ.peek());

        System.out.print("Elements in order: ");
        while (!minPQ.isEmpty()) {
            System.out.print(minPQ.poll() + " ");
        }
        System.out.println("\n");

        // --- 2. Built-in Max-Heap using Comparator ---
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
        maxPQ.addAll(Arrays.asList(4, 1, 3, 2));

        System.out.println("🔹 Built-in PriorityQueue (Max-Heap using Comparator)");
        while (!maxPQ.isEmpty()) {
            System.out.print(maxPQ.poll() + " ");
        }
        System.out.println("\n");

        // --- 3. Custom Object PQ using Comparable ---
        System.out.println("🔹 Custom Task Queue using Comparable");
        PriorityQueue<Task> taskPQ = new PriorityQueue<>();
        taskPQ.add(new Task("Clean", 3));
        taskPQ.add(new Task("Study", 1));
        taskPQ.add(new Task("Cook", 2));

        while (!taskPQ.isEmpty()) {
            Task t = taskPQ.poll();
            System.out.println(t.name + " (priority: " + t.priority + ")");
        }
        System.out.println();

        // --- 4. Custom Object PQ using Comparator (Max-Priority First) ---
        System.out.println("🔹 Task Queue using Comparator (Max-Priority First)");
        PriorityQueue<Task> reversePQ = new PriorityQueue<>(new TaskComparator());
        reversePQ.add(new Task("Clean", 3));
        reversePQ.add(new Task("Study", 1));
        reversePQ.add(new Task("Cook", 2));

        while (!reversePQ.isEmpty()) {
            Task t = reversePQ.poll();
            System.out.println(t.name + " (priority: " + t.priority + ")");
        }
        System.out.println();

        // --- 5. Manual Heap PQ using ArrayList (Flexible) ---
        System.out.println("🔹 FlexibleHeap Example (Max-Heap)");
        FlexibleHeap maxHeap = new FlexibleHeap(true); // max-heap
        maxHeap.insert(5);
        maxHeap.insert(2);
        maxHeap.insert(8);
        maxHeap.insert(1);

        System.out.println("Peek: " + maxHeap.peek());
        System.out.print("Removing elements: ");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.remove() + " ");
        }
        System.out.println("\n");

        System.out.println("🔹 FlexibleHeap Example (Min-Heap)");
        FlexibleHeap minHeap = new FlexibleHeap(false); // min-heap
        minHeap.insert(5);
        minHeap.insert(2);
        minHeap.insert(8);
        minHeap.insert(1);

        System.out.println("Peek: " + minHeap.peek());
        System.out.print("Removing elements: ");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.remove() + " ");
        }
        System.out.println();
    }
}
