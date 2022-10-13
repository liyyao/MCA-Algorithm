package major.dp;

import tool.ArrayUtils;

/**
 * 10、给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角，沿途只可以向下或者向右走，
 *     沿途的数字都累加就是距离累加和，返回最小距离累加和
 */
public class T10_MinDistanceSum {


    public static int minDistanceSum(int[][] arr) {
        int x = arr.length - 1;
        int y = arr[0].length - 1;
        return process1(0, 0, x, y, arr);
    }

    private static int process1(int cur1, int cur2, int x, int y, int[][] arr) {
        if (cur1 > x || cur2 > y) {
            return Integer.MAX_VALUE;
        }
        if (cur1 == x && cur2 == y) {
            return arr[x][y];
        }
        return arr[cur1][cur2] + Math.min(process1(cur1 + 1, cur2, x, y, arr), process1(cur1, cur2 + 1, x, y, arr));
    }

    private static int minDistanceSum2(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = arr[0][0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = arr[0][i] + dp[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = arr[i][0] + dp[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = arr[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        //ArrayUtils.print(dp);
        return dp[m - 1][n - 1];
    }

    /**
     * 空间压缩
     * 上面2方法是原二维数组多大，同样创造出一样大的二维表，比如给定的matrix是100 * 100的，创造的dp表也是100 * 100的
     * 但本方法只需要用一个长度为100的一维表即可
     * @param arr
     * @return
     */
    private static int minDistanceSum3(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        int[] dp = new int[n];
        dp[0] = arr[0][0];
        for (int i = 1; i < n; i++) {
            dp[i] = arr[0][i] + dp[i - 1];
        }
        ArrayUtils.print(dp);
        for (int i = 1; i < m; i++) {
            dp[0] += arr[i][0];
            for (int j = 1; j < n; j++) {
                dp[j] = arr[i][j] + Math.min(dp[j - 1], dp[j]);
            }
            ArrayUtils.print(dp);
        }
        return dp[n - 1];
    }


    public static void main(String[] args) {
        int[][] arr1 = {{10, 3, 2, 5, 4},{8, 6, 3, 7, 9}, {12, 7, 3, 4, 2}};
        int[][] arr = {{1, 3, 5, 9},{8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
        int sum = minDistanceSum(arr);
        int sum2 = minDistanceSum2(arr);
        int sum3 = minDistanceSum3(arr);
        System.out.println("最小和：" + sum + "---" + sum3);
    }
}
