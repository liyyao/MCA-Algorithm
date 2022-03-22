package basic.util;

public class ArrayUtil {

    /**
     * 判断数组是否是升序
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
     * @param maxLen 数组最大长度
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
}
