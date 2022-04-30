package major.greedy;

import tool.ArrayUtils;

import java.util.PriorityQueue;

/**
 *
 * 3、一块金条切成两半，是需要花费和长度数值一样的铜板的。比如长度为20的金条，不管怎么切，
 * 都要花费20个铜板。一群人想整分整块金条，怎么分最省铜板？
 * 例如：给定数组{10，20，30}，代表一共三个人，整块金条长度为60，金条要分成10，20，30三个部分。
 * 如果先把长度60的金条分成10和50，花费60；再把长度为50的金条分成20和30，花费50；一共花费110铜板。
 * 但如果先把长度60的金条分成30和30，花费60；再把长度30金条分成10和20，花费30；一共花费90铜板。
 * 输入一个数组，返回分割的最小代价。
 */
public class T03_SplitGod {

    public static int split2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0);
    }

    private static int process(int[] arr, int pre) {
        if (arr.length == 1) {
            return pre;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                ans = Math.min(ans, process(copyAndMergeTwo(arr, i, j), pre + arr[i] + arr[j]));
            }
        }
        return ans;
    }

    private static int[] copyAndMergeTwo(int[] arr, int i, int j) {
        int[] ans = new int[arr.length - 1];
        int ansi = 0;
        for (int k = 0; k < arr.length; k++) {
            if (k != i && k != j) {
                ans[ansi++] = arr[k];
            }
        }
        ans[ansi] = arr[i] + arr[j];
        return ans;
    }

    /**
     * 贪心
     * @param arr
     * @return
     */
    public static int split1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i : arr) {
            queue.add(i);
        }
        int count = 0;
        int cur = 0;
        while (queue.size() > 1) {
            cur = queue.poll() + queue.poll();
            count += cur;
            queue.add(cur);
        }
        return count;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 6;
        int maxValue = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = ArrayUtils.LenRandomValueRandom(maxSize, maxValue);
            if (split1(arr) != split2(arr)) {
                System.out.println("出错了");
            }
        }
        System.out.println("测试结束");
    }
}
