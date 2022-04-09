package major.xor;

import basic.util.ArrayUtil;
import tool.IntUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 5.一个数组中有一种数出现k次，其他数都出现了M次，M > 1, k < M，找到出现了k次的数，要求额外空间复杂度O(1)，时间复杂度O(N)
 */
public class KMData {

    public static void main(String[] args) {
        int testTime = 1000000;
        int kinds = 10;
        int range = 100;
        int max = 9;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int a = (int) ((Math.random() * max) + 1); // a 1 ~ 9
            int b = (int) ((Math.random() * max) + 1); // b 1 ~ 9
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            if (k == m) {
                m++;
            }
            int[] arr = randomArray(kinds, range, k, m);
            int ans1 = test(arr, k, m);
            int ans2 = findKTimesData(arr, k, m);
            if (ans1 != ans2) {
                System.out.println("出错了...");
                System.out.println("k = " + k + ", m = " + m);
                ArrayUtil.print(arr);
            }
        }
        System.out.println("测试结束");
    }

    /**
     * 生成数组
     * @param kinds 出现不同数的种数
     * @param range 出现数的范围
     * @param k
     * @param m
     * @return
     */
    private static int[] randomArray(int kinds, int range, int k, int m) {
        int kTimesNum = IntUtil.randomNumber(range);
        int numKinds = (int) (Math.random() * kinds) + 2;   //加2是因为出现数的种数要大于2
        int[] arr = new int[k + (numKinds - 1) * m];
        int index = 0;
        for (; index < k; index++) {
            arr[index] = kTimesNum;
        }
        numKinds--;
        Set<Integer> set = new HashSet<>();
        set.add(kTimesNum);
        while (numKinds != 0) {
            int curNum = 0;
            do {
                curNum = IntUtil.randomNumber(range);
            } while (set.contains(curNum));
            set.add(curNum);
            for (int i = 0; i < m; i++) {
                arr[index++] = curNum;
            }
            numKinds--;
        }
        //将数组中的数随机换位置
        for (int i = 0; i < arr.length; i++) {
            int j = (int) (Math.random() * arr.length);     //0 ~ n-1
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }



    /**
     *
     * @param arr 请保证arr中，只有一种数出现了k次，其他数都出现了m次
     * @param k 一种数出现了k次
     * @param m n个数出现了m次
     * @return 出现了k次的数
     */
    public static int findKTimesData(int[] arr, int k, int m) {
        int[] sumArr = new int[32];
        for (int i = 0; i < arr.length; i++) {
            addDataBit(sumArr, arr[i]);
        }
        int ans = 0;
        for (int i = 0; i < sumArr.length; i++) {
            if (sumArr[i] % m > 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }

    public static void addDataBit(int[] sumArr, int num) {
        for (int i = 31; i >= 0; i--) {
            sumArr[i] += (num & (1 << i)) == 0 ? 0 : 1;
        }
    }

    public static int test(int[] arr, int k, int m) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (int num : map.keySet()) {
            if (map.get(num) == k) {
                return num;
            }
        }
        return -1;
    }
}
