import java.util.Scanner;

public class InsertionSort {

    public static void sortArray(int[] arr, String order) {
        for (int i = 1; i < arr.length; i++) {
            int initIndex = arr[i];
            int refIndex = i - 1;
            // ascending sortion
            if (order.equals("Ascending")) {
                while (refIndex >= 0 && arr[refIndex] > initIndex) {
                    arr[refIndex + 1] = arr[refIndex];
                    refIndex--;
                }
            }
            // descending sortion
            else if (order.equals("Descending")) {
                while (refIndex >= 0 && arr[refIndex] < initIndex) {
                    arr[refIndex + 1] = arr[refIndex];
                    refIndex--;
                }
            }
            arr[refIndex + 1] = initIndex; // re-placement
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

            System.out.print("Enter the elements seperated by spaces: ");
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

    // printing the output sorted array by sortion preference
    public static void printSortedArray(int[] arr, String order) {
        sortArray(arr, order);
        System.out.print(order + " sorted array: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = getArray();
        if (arr != null) {
            printSortedArray(arr, "Ascending");
            printSortedArray(arr, "Descending");
        }
    }
}
