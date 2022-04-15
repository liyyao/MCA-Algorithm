package major.sort.heap;

import basic.util.ArrayUtil;

import java.util.Arrays;

public class A01_HeapSort {

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //这里是将数组中的数一个一个的给出然后创建大根堆
        //这里是从上往下建堆，所以下层需要调整的次数是高度-1
        //时间复杂度是O(N * logN)
        /*for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }*/

        //这里是知道了整个数组的数，然后从下往上创建大根堆
        //从下往上建堆，下层的调整次数是1，而最上层的调整数次是高度-1
        //这里是把多的调整次数给到少的上层
        //时间复杂度是O(N)
        for (int i = arr.length - 1; i >= 0 ; i++) {
            heapify(arr, i, arr.length);
        }

        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        //O(N * logN)
        while (heapSize > 0) {      //O(N)
            heapify(arr, 0, heapSize);  //O(logN)
            swap(arr, 0, --heapSize);   // O(1)
        }
    }

    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int len = 100;
        int value = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = ArrayUtil.LenRandomValueRandom(len, value);
            int[] arr2 = ArrayUtil.copyArray(arr1);
            heapSort(arr1);
            Arrays.sort(arr2);
            if (!ArrayUtil.isEqual(arr1, arr2)) {
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
