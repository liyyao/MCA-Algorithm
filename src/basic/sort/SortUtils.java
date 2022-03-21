package basic.sort;

public class SortUtils {

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void print(int[] arr) {
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public static int[] defaultArr() {
        return new int[]{3, 4, 6, 1, 4, 6, 0, 8, 7, 8};
    }
}
