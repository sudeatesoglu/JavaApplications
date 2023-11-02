import java.util.Scanner;

public class ResizingArrayQueueAndStackOfStrings {

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

        // getting & checking inputs from user
        public static int[] getArray() {
            Scanner scan = new Scanner(System.in);

            try {
                System.out.print("Enter the length of the array: ");
                int arrLength = scan.nextInt();

                if (arrLength <= 0) { // check if the array length has an appropriate value
                    throw new IllegalArgumentException("Elements of the array must be a positive integer.");
                }

                int[] arr = new int[arrLength];

                System.out.print("Enter " + arrLength + " elements seperated by spaces: ");
                for (int i = 0; i < arrLength; i++) {
                    String element = scan.next();
                    if (!isNumericElement(element)) { // check if the elements of the array are numbers
                        throw new IllegalArgumentException("Elements of the array must be numerical values.");
                    }
                    arr[i] = Integer.parseInt(element);
                }
                return arr;

            } catch (Exception e) {
                System.out.println("Error!: " + e.getMessage());
                return null;
            } finally {
                scan.close();
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

        public static void main(String[] args) {
            int[] arr = getArray();
            if (arr != null) {
                printInsSortedArray(arr, "Ascending");
                printInsSortedArray(arr, "Descending");
                printSelSortedArray(arr);
            }
            System.out.println("----------------------------------");

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

            System.out.println("Queue without any steps: " + stack);
            queue.enqueue("X");
            queue.enqueue("Y");

            System.out.println("Queue with enqueued elements: " + queue);

            System.out.println("Dequeued from the queue: " + queue.dequeue());
            System.out.println("Dequeued from the queue: " + queue.dequeue());

            System.out.println("Queue with dequeued elements: " + queue);
        }
    }
}
