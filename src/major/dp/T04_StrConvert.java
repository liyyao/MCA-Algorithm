package major.dp;

/**
 * 4、规定1和A对应、2和B对应、3和C对应...26和Z对应，那么一个数字字符串比如"111"就可以转化为："AAA"、"KA"和"AK"
 *    给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 */
public class T04_StrConvert {

    public static int way1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    private static int process(char[] c, int index) {
        if (index == c.length) {
            return 1;
        }
        if (c[index] == '0') {
            return 0;
        }
        int ans = process(c, index + 1);
        if (index + 1 < c.length && (c[index] - '0') * 10 + (c[index + 1] - '0') < 27) {
            ans += process(c, index + 2);
        }
        return ans;
    }

    /**
     * 从右往右的动态规则
     * @param str
     * @return
     */
    private static int dp(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int N = str.length();
        int[] dp = new int[N + 1];
        dp[N] = 1;
        char[] chars = str.toCharArray();
        for (int i = N - 1; i >= 0 ; i--) {
            if (chars[i] != '0') {
                int ans = dp[i + 1];
                if (i + 1 < N && (chars[i] - '0') * 10 + (chars[i + 1] - '0') < 27) {
                    ans += dp[i + 2];
                }
                dp[i] = ans;
            }
        }
        return dp[0];
    }

    /**
     * 从左往右的动态规则
     * @param s
     * @return
     */
    private static int dp1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        if (str[0] == '0') {
            return 0;
        }
        int N = str.length;
        int[] dp = new int[N];
        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            if (str[i] == '0') {
                if (str[i - 1] == '0' || str[i - 1] > '2' || (i - 2 >= 0 && dp[i - 2] == 0)) {
                    return 0;
                } else {
                    dp[i] = i - 2 >= 0 ? dp[i - 2] : 1;
                }
            } else {
                dp[i] = dp[i - 1];
                if (str[i - 1] != '0' && (str[i - 1] - '0') * 10 + str[i] - '0' < 27) {
                    dp[i] += i - 2 >= 0 ? dp[i - 2] : 1;
                }
            }
        }
        return dp[N - 1];
    }

    public static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        int testTimes = 1000000;
        int n = 30;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int len = (int) (Math.random() * n);
            String s = randomString(len);
            int ans1 = way1(s);
            int ans2 = dp(s);
            int ans3 = dp1(s);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println(s);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
