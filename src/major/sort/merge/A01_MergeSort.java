package major.sort.merge;

import tool.ArrayUtils;

/**
 * 归并排序
 */
public class A01_MergeSort {

    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        int step = 1;
        while (step < N) {
            int L = 0;
            while (L < N) {
                int M = L + step - 1;
                if (M >= N) {
                    break;
                }
                int R = Math.min(M + step, N - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            //防止溢出
            if (step > N / 2) {
                break;
            }
            step <<= 1;
        }
    }

    /**
     * 递归方法
     */
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int M = L + ((R - L) >> 1);
        process(arr, L, M);
        process(arr, M + 1, R);
        merge(arr, L, M, R);
    }


    private static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int index = 0;
        int left = L;
        int right = M + 1;
        while (left <= M && right <= R) {
            help[index++] = arr[left] > arr[right] ? arr[right++] : arr[left++];
        }
        while (left <= M) {
            help[index++] = arr[left++];
        }
        while (right <= R) {
            help[index++] = arr[right++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int len = 100;
        int value = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = ArrayUtils.LenRandomValueRandom(len, value);
            int[] arr2 = ArrayUtils.copyArray(arr1);
            mergeSort1(arr1);
            mergeSort2(arr2);
            for (int j = 0; j < arr1.length; j++) {
                if (arr1[j] != arr2[j]) {
                    System.out.println("出错了");
                    return;
                }
            }
        }
        System.out.println("测试结束");
    }

}
