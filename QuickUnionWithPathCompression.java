import java.util.Scanner;

public class QuickUnionWithPathCompression {

    public static class QuickUnionWithPathCompressionFunc {
        private int[] parent;
        private int[] size; // to track the size of each tree

        public QuickUnionWithPathCompressionFunc(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1; // initialize size to 1
            }
        }

        private int findRoot(int i) {
            while (i != parent[i]) {
                parent[i] = parent[parent[i]]; // path compression
                i = parent[i];
            }
            return i;
        }

        public boolean connected(int p, int q) {
            return findRoot(p) == findRoot(q);
        }

        public void union(int p, int q) {
            int rootP = findRoot(p);
            int rootQ = findRoot(q);

            if (rootP == rootQ) {
                return; // already connected
            }

            // link the roots with path compression
            if (size[rootP] < size[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            } else {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
        }
    }

    public static class QuickFind {
        private int[] id;
        private int exchangeCount;
        private int findCount;

        public QuickFind(int n) {
            id = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
            }
            exchangeCount = 0;
            findCount = 0;
        }

        public int getExchangeCount() {
            return exchangeCount;
        }

        public int getFindCount() {
            return findCount;
        }

        public boolean connected(int p, int q) {
            findCount++;
            return id[p] == id[q];
        }

        public void union(int p, int q) {
            int pid = id[p];
            int qid = id[q];
            exchangeCount++;
            for (int i = 0; i < id.length; i++) {
                if (id[i] == pid) {
                    exchangeCount++;
                    id[i] = qid;
                }
            }
        }
    }

    public static class QuickUnion {
        private int[] parent;
        private int exchangeCount;
        private int findCount;

        public QuickUnion(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            exchangeCount = 0;
            findCount = 0;
        }

        public int getExchangeCount() {
            return exchangeCount;
        }

        public int getFindCount() {
            return findCount;
        }

        private int findRoot(int i) {
            findCount++;
            while (i != parent[i]) {
                findCount++;
                i = parent[i];
            }
            return i;
        }

        public boolean connected(int p, int q) {
            return findRoot(p) == findRoot(q);
        }

        public void union(int p, int q) {
            int rootP = findRoot(p);
            int rootQ = findRoot(q);
            exchangeCount++;
            parent[rootP] = rootQ;
        }
    }

    public static class ResizingArrayQueueAndStackOfStrings {
        private String[] items;
        private int size;

        public ResizingArrayQueueAndStackOfStrings() {
            items = new String[1];
            size = 0;
        }

        private void resize(int capacity) {
            String[] copy = new String[capacity];
            for (int i = 0; i < size; i++) {
                copy[i] = items[i];
            }
            items = copy;
        }

        public void push(String item) {
            if (size == items.length) {
                resize(2 * items.length);
            }
            items[size++] = item;
        }

        public String pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty");
            }

            String item = items[--size];
            items[size] = null;

            if (size > 0 && size == items.length / 4) {
                resize(items.length / 2);
            }
            return item;
        }

        public void enqueue(String item) {
            push(item);
        }

        public String dequeue() {
            if (isEmpty()) {
                throw new IllegalStateException("Queue is empty");
            }

            String item = items[0];
            for (int i = 0; i < size - 1; i++) {
                items[i] = items[i + 1];
            }

            items[--size] = null;
            if (size > 0 && size == items.length / 4) {
                resize(items.length / 2);
            }
            return item;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < size; i++) {
                sb.append(items[i]);
                if (i < size - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }

    public static class Sortion {
        public static void sortArray(int[] arr, String order) {
            for (int i = 1; i < arr.length; i++) {
                int current = arr[i];
                int refIndex = i - 1;
                // ascending sortion
                if (order.equals("Ascending")) {
                    while (refIndex >= 0 && arr[refIndex] > current) {
                        arr[refIndex + 1] = arr[refIndex];
                        refIndex--;
                    }
                }
                // descending sortion
                else if (order.equals("Descending")) {
                    while (refIndex >= 0 && arr[refIndex] < current) {
                        arr[refIndex + 1] = arr[refIndex];
                        refIndex--;
                    }
                }
                arr[refIndex + 1] = current; // re-placement
            }
        }

        public static void selectionSortArray(int[] arr) {
            int length = arr.length;
            for (int i = 0; i < length - 1; i++) {
                int minIndex = i;
                int minElement = arr[i]; // assume i is the smallest
                for (int j = i + 1; j < length; j++) {
                    if (arr[j] < minElement) { // determine the accurate smallest
                        minIndex = j;
                        minElement = arr[j];
                    }
                }
                swapPos(arr, minIndex, i); // swap the positions
            }
        }

        // getting & checking inputs from the user
        public static int[] getArray() {
            Scanner scan = new Scanner(System.in);

            try {
                System.out.print("Enter the length of the array: ");
                int arrLength = scan.nextInt();

                if (arrLength <= 0) {
                    throw new IllegalArgumentException("Elements of the array must be a positive integer.");
                }

                int[] arr = new int[arrLength];

                System.out.print("Enter " + arrLength + " elements separated by spaces: ");
                for (int i = 0; i < arrLength; i++) {
                    String element = scan.next();
                    if (!isNumericElement(element)) {
                        throw new IllegalArgumentException("Elements of the array must be numerical values.");
                    }
                    arr[i] = Integer.parseInt(element);
                }
                return arr;

            } catch (Exception e) {
                System.out.println("Error!: " + e.getMessage());
                return null;
            }
        }

        // checking whether array elements consist of numbers
        public static boolean isNumericElement(String str) {
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        public static void swapPos(int[] arr, int index, int element) {
            int tempArr = arr[element];
            arr[element] = arr[index];
            arr[index] = tempArr;
        }

        // printing the output sorted array by sortion preference
        public static void printInsSortedArray(int[] arr, String order) {
            sortArray(arr, order);
            System.out.print(order + " sorted (insertion sort) array: ");
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }

        public static void printSelSortedArray(int[] arr) {
            selectionSortArray(arr);
            System.out.print("Sorted (selection sort) array: ");
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("---------- SORTION ALGORITHMS ----------");
        int[] arr = Sortion.getArray();
        if (arr != null) {
            Sortion.printInsSortedArray(arr, "Ascending");
            Sortion.printInsSortedArray(arr, "Descending");
            Sortion.printSelSortedArray(arr);
        }
        System.out.println("---------- STACK & QUEUES ----------");

        ResizingArrayQueueAndStackOfStrings stack = new ResizingArrayQueueAndStackOfStrings();
        System.out.println("Stack without any steps: " + stack);
        stack.push("A");
        stack.push("B");

        System.out.println("Stack with pushed elements: " + stack);

        System.out.println("Popped from the stack: " + stack.pop());
        System.out.println("Popped from the stack: " + stack.pop());

        System.out.println("Stack with popped elements: " + stack);
        System.out.println("----------------------------------");

        ResizingArrayQueueAndStackOfStrings queue = new ResizingArrayQueueAndStackOfStrings();
        System.out.println("Queue without any steps: " + queue);
        queue.enqueue("X");
        queue.enqueue("Y");

        System.out.println("Queue with enqueued elements: " + queue);

        System.out.println("Dequeued from the queue: " + queue.dequeue());
        System.out.println("Dequeued from the queue: " + queue.dequeue());

        System.out.println("Queue with dequeued elements: " + queue);

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nChoose an operation:");
        System.out.println("1. Push to Stack");
        System.out.println("2. Pop from Stack");
        System.out.println("3. Enqueue to Queue");
        System.out.println("4. Dequeue from Queue");
        System.out.println("5. Exit");

        int choice = scanner.nextInt();

        System.out.println("\nChoose an operation:");
        System.out.println("1. Push to Stack");
        System.out.println("2. Pop from Stack");
        System.out.println("3. Enqueue to Queue");
        System.out.println("4. Dequeue from Queue");
        System.out.println("5. Exit");

        switch (choice) {
            case 1:
                System.out.print("Enter element to push to the stack: ");
                String stackElement = scanner.next();
                stack.push(stackElement);
                System.out.println("Element '" + stackElement + "' pushed to the stack.");
                break;
            case 2:
                try {
                    String poppedElement = stack.pop();
                    System.out.println("Popped element from the stack: " + poppedElement);
                } catch (IllegalStateException e) {
                    System.out.println("Stack is empty. Cannot pop.");
                }
                break;
            case 3:
                System.out.print("Enter element to enqueue to the queue: ");
                String queueElement = scanner.next();
                queue.enqueue(queueElement);
                System.out.println("Element '" + queueElement + "' enqueued to the queue.");
                break;
            case 4:
                try {
                    String dequeuedElement = queue.dequeue();
                    System.out.println("Dequeued element from the queue: " + dequeuedElement);
                } catch (IllegalStateException e) {
                    System.out.println("Queue is empty. Cannot dequeue.");
                }
                break;
            case 5:
                System.out.println("Exiting program. Thank you!");

                System.out.println("Final state of the stack: " + stack);
                System.out.println("Final state of the queue: " + queue);

                scanner.close();
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
        }

        // Display the current state after each operation
        System.out.println("Current state of the stack: " + stack);
        System.out.println("Current state of the queue: " + queue);

        System.out.println("---------- QUICK ALGORITHMS ----------");
        System.out.println();
        System.out.println("AUTO QUICK EXAMPLE");

        int X = 20;
        QuickFind quickFind = new QuickFind(X);

        quickFind.union(0, 1);
        quickFind.union(2, 3);
        quickFind.union(4, 5);

        System.out.println("Quick-Find Exchange Count: " +
                quickFind.getExchangeCount());
        System.out.println("Quick-Find Find Count: " + quickFind.getFindCount());

        QuickUnion quickUnion = new QuickUnion(X);

        quickUnion.union(0, 1);
        quickUnion.union(2, 3);
        quickUnion.union(4, 5);

        System.out.println("Quick-Union Exchange Count: " + quickUnion.getExchangeCount());
        System.out.println("Quick-Union Find Count: " + quickUnion.getFindCount());
        System.out.println();

        System.out.println("USER INPUT EXAMPLE");

        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        QuickFind quickFind2 = new QuickFind(n);

        while (true) {
            System.out.println("Current elements and their groups: " + java.util.Arrays.toString(quickFind2.id));
            System.out.print("Enter the first element for union (or -1 to exit and observe the count results): ");

            int p;
            try {
                p = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
                continue;
            }

            if (p == -1) {
                break;
            }

            System.out.print("Enter the second element for union: ");

            int q;
            try {
                q = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
                continue;
            }

            if (p < 0 || p >= n || q < 0 || q >= n) {
                System.out.println("Invalid input. Please enter valid element indices.");
                continue;
            }

            quickFind.union(p, q);
        }

        // print results
        System.out.println("Final elements and their groups: " + java.util.Arrays.toString(quickFind2.id));
        System.out.println("Quick-Find Exchange Count: " + quickFind2.getExchangeCount());
        System.out.println("Quick-Find Find Count: " + quickFind2.getFindCount());

        scanner.nextLine();

        QuickUnion quickUnion2 = new QuickUnion(n);

        while (true) {
            System.out.println("Current elements and their groups: " + java.util.Arrays.toString(quickUnion2.parent));
            System.out.print("Enter the first element for union (or -1 to exit and observe the count results): ");

            int p;
            try {
                p = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
                continue;
            }

            if (p == -1) {
                break;
            }

            System.out.print("Enter the second element for union: ");

            int q;
            try {
                q = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
                continue;
            }

            if (p < 0 || p >= n || q < 0 || q >= n) {
                System.out.println("Invalid input. Please enter valid element indices.");
                continue;
            }

            quickUnion.union(p, q);
        }

        // print results
        System.out.println("Final elements and their groups: " + java.util.Arrays.toString(quickUnion2.parent));
        System.out.println("Quick-Union Exchange Count: " + quickUnion2.getExchangeCount());
        System.out.println("Quick-Union Find Count: " + quickUnion2.getFindCount());

        scanner.close();

        System.out.println("---------- QuickUnionWithPathCompression ----------");

        QuickUnionWithPathCompression.QuickUnionWithPathCompressionFunc quwpc = new QuickUnionWithPathCompressionFunc(
                10);

        quwpc.union(0, 1);
        quwpc.union(1, 2);
        quwpc.union(2, 3);
        quwpc.union(3, 4);

        System.out.println("Connected 0 and 4: " + quwpc.connected(0, 4)); // true

    }
}
