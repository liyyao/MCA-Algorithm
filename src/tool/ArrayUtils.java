package tool;

import java.util.Arrays;

public class ArrayUtils {

    /**
     * 判断两个数组是否相等
     * @param arr1
     * @param arr2
     * @return
     */
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1 == null || arr2 == null) {
            return false;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 打印数组
     * @param arr
     */
    public static void print(int[] arr) {
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public static void print(String[] arr) {
        for (String a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print("(");
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
                if (j != arr[i].length -1) {
                    System.out.print(",");
                }
            }
            System.out.println(")");
        }
    }

    /**
     * 判断数组是否是升序
     *
     * @param arr
     * @return
     */
    public static boolean isSorted(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max > arr[i]) {
                return false;
            }
            max = Math.max(max, arr[i]);
        }
        return true;
    }

    /**
     * copy一个数组
     *
     * @param arr
     * @return
     */
    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    /**
     * 生成一个随机数组
     *
     * @param maxLen   数组最大长度
     * @param maxValue 数组中最大值
     * @return int数组
     */
    public static int[] LenRandomValueRandom(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen);
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * maxValue);
        }
        return ans;
    }

    /**
     * 生成一个随机数组
     * 数组中相邻的两个数不相等
     * @param maxLen   数组最大长度
     * @param maxValue 数组中最大值
     * @return int数组
     */
    public static int[] randomArrAndLeftNotEqualsRight(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen);
        int[] arr = new int[len];
        if (len > 0) {
            arr[0] = (int) (Math.random() * maxValue);
        }
        for (int i = 1; i < len; i++) {
            do {
                arr[i] = (int)(Math.random() * maxValue);
            } while (arr[i] == arr[i - 1]);
        }
        return arr;
    }

    /**
     * 生成一个随机数组，排好序后数组中的每个数移动的距离不超过K
     * @param maxSize
     * @param maxValue
     * @param K
     * @return
     */
    public static int[] randomArrayNoMoveMoreK(int maxSize, int maxValue, int K) {
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (((maxValue + 1) * Math.random())) - (int) (((maxValue) * Math.random()));
        }
        Arrays.sort(arr);
        //开始随意交换，但是保证每个数的距离不超过k
        //swap[i] == true, 表示i位置已经参与过交换
        //swap[i] == false, 表示 i位置没有参与过交换
        boolean[] isSwap = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int j = Math.min(i + (int) (Math.random() * (K + 1)), arr.length - 1);
            if (!isSwap[i] && !isSwap[j]) {
                isSwap[i] = true;
                isSwap[j] = true;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        return arr;
    }

    /**
     * 随机生成一个坐标，其中坐标值都为正数且y坐标值要大于x坐标值
     * @param maxSize
     * @param maxValue
     * @return
     */
    public static int[][] randomArrayPositiveCoordinate(int maxSize, int maxValue) {
        int[][] arr = new int[(int) ((maxSize + 1) * Math.random())][2];
        for (int i = 0; i < arr.length; i++) {
            int x = (int) (maxValue * Math.random());
            int y = (int) ((maxValue - x) * Math.random() + 1 + x);
            arr[i][0] = x;
            arr[i][1] = y;
        }
        return arr;
    }
}
