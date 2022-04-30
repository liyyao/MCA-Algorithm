package major.greedy;

import tool.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * 1、给定一个由字符串组成的数组strs，必须把所有的字符串拼接起来，返回所有可能
 * 的拼接结果中，字典序最小的结果
 */
public class T01_SplitJoinStr {

    /**
     * 暴力求解
     * @param strs
     * @return
     */
    public static String lowestString(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        for (String str : strs) {
            System.out.print(str + ",");
        }
        System.out.println();
        TreeSet<String> ans = process(strs);
        for (String t : ans) {
            System.out.print(t + " ");
        }
        System.out.println();
        return ans.size() == 0 ? "" : ans.first();
    }


    public static TreeSet<String> process(String[] strs) {
        TreeSet<String> ans = new TreeSet<>();
        if (strs.length == 0) {
            ans.add("");
            return ans;
        }
        for (int i = 0; i < strs.length; i++) {
            String first = strs[i];
            String[] nexts = removeIndexString(strs, i);
            TreeSet<String> next = process(nexts);
            for (String str : next) {
                ans.add(first + str);
            }
        }
        return ans;
    }

    private static String[] removeIndexString(String[] arr, int index) {
        int N = arr.length;
        String[] ans = new String[N - 1];
        int ansIndex = 0;
        for (int i = 0; i < N; i++) {
            if (i != index) {
                ans[ansIndex++] = arr[i];
            }
        }
        return ans;
    }

    /**
     * 方法一
     * @param arr
     * @return
     */
    public static String joinStr(String[] arr) {
        if (arr == null || arr.length == 0) {
            return "";
        }
        Arrays.sort(arr, new StrComparator());
        StringBuilder sb = new StringBuilder();
        for (String str : arr) {
            sb.append(str);
        }
        return sb.toString();
    }

    static class StrComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    public static void main(String[] args) {
        int testTimes = 2;
        int arrLen = 6;
        int strLen = 5;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = ArrayUtils.generateRandomStringArray(arrLen, strLen);
            String[] arr2 = ArrayUtils.copyStringArr(arr1);
            if (!lowestString(arr1).equals(joinStr(arr2))) {
                for (String str : arr1) {
                    System.out.print(str + ",");
                }
                System.out.println();
                System.out.println("出错了");
            }
        }
        System.out.println("测试结束");
    }
}
