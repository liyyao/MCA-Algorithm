package major.dp;

/**
 * 3、给定两个长度都为N的数组weights和values，weights[i]和values[i]分别代表i号物品的重量和价值。
 *    给定一个正数bag，表示一个载重bag的袋子，你装的物品不能超过这个重量。返回你能装下最多的价值是多少
 */
public class T03_01Bag {

    public static int way1(int[] weights, int[] values, int bag) {
        return bag1(weights, values, 0, bag);
    }

    private static int bag1(int[] weights, int[] values, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (index == weights.length) {
            return 0;
        }
        int ans1 = bag1(weights, values, index + 1, rest);
        int ans2 = 0;
        int next = bag1(weights, values, index + 1, rest - weights[index]);
        if (next != -1) {
            ans2 = values[index] + next;
        }
        return Math.max(ans1, ans2);
    }

    public static int way2(int[] weights, int[] values, int bag) {
        return bag2(weights, values, bag);
    }

    private static int bag2(int[] weights, int[] values, int bag) {
        if (weights == null || weights.length == 0
                || values == null || values.length == 0) {
            return 0;
        }
        int len = weights.length;
        int[][] dp = new int[len + 1][bag + 1];
        for (int i = len - 1; i >= 0; i--) {
            for (int rest = 0; rest <= bag; rest++) {
                int ans1 = dp[i + 1][rest];
                int ans2 = 0;
                int next = rest - weights[i] < 0 ? -1 : dp[i + 1][rest - weights[i]];
                if (next != -1) {
                    ans2 = values[i] + next;
                }
                dp[i][rest] = Math.max(ans1, ans2);
            }
        }
        return dp[0][bag];
    }


    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
        int[] values = { 5, 6, 3, 19, 12, 4, 2 };
        int bag = 15;
        System.out.println(way1(weights, values, bag));
        System.out.println(way2(weights, values, bag));
    }

}
