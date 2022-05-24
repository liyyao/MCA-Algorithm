package major.dp;

/**
 * 8、请同学们自行搜索或者想象一个象棋的棋盘，然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
 *    那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
 *    给你三个参数x,y,k，返回"马"从(0,0)位置出发，必须走k步
 *    最后落在(x,y)上的方法数有多少种？
 */
public class T08_Chess {

    public static int way1(int x, int y, int k) {
        return process1(0, 0, k, x, y);
    }

    private static int process1(int i, int j, int rest, int x, int y) {
        if (i > 9 || i < 0 || j > 8 || j < 0) {
            return 0;
        }
        if (rest == 0) {
            return i == x && j == y ? 1 : 0;
        }
        int ans = process1(i + 1, j + 2, rest - 1, x, y);
        ans += process1(i + 2, j + 1, rest - 1, x, y);
        ans += process1(i + 2, j - 1, rest - 1, x, y);
        ans += process1(i + 1, j - 2, rest - 1, x, y);
        ans += process1(i - 1, j - 2, rest - 1, x, y);
        ans += process1(i - 2, j - 1, rest - 1, x, y);
        ans += process1(i - 2, j + 1, rest - 1, x, y);
        ans += process1(i - 1, j + 2, rest - 1, x, y);
        return ans;
    }

    public static int dp(int x, int y, int k) {
        int[][][] dp = new int[10][9][k + 1];
        dp[x][y][0] = 1;
        for (int rest = 1; rest <= k; rest++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    int ans = pick(dp, i + 1, j + 2, rest - 1);
                    ans += pick(dp, i + 2, j + 1, rest - 1);
                    ans += pick(dp, i + 2, j - 1, rest - 1);
                    ans += pick(dp, i + 1, j - 2, rest - 1);
                    ans += pick(dp, i - 1, j - 2, rest - 1);
                    ans += pick(dp, i - 2, j - 1, rest - 1);
                    ans += pick(dp, i - 2, j + 1, rest - 1);
                    ans += pick(dp, i - 1, j + 2, rest - 1);
                    dp[i][j][rest] = ans;
                }
            }
        }
        return dp[0][0][k];
    }

    private static int pick(int[][][] dp, int x, int y, int rest) {
        if (x > 9 || x < 0 || y > 8 || y < 0) {
            return 0;
        }
        return dp[x][y][rest];
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(way1(x, y, step));
        System.out.println(dp(x, y, step));
    }
}
