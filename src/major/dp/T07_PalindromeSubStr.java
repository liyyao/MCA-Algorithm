package major.dp;

/**
 * 7、给定一个字符串str，返回这个字符串的最长回文子序列长度
 *    比如：str = "a12b3c43def2ghi1kpm"
 *    最长回文子序列是"1234321"或者"123c321"，返回长度7
 */
public class T07_PalindromeSubStr {


    public static int way1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] str1 = str.toCharArray();
        char[] str2 = new char[str1.length];
        int k = 0;
        for (int i = str1.length - 1; i >= 0 ; i--) {
            str2[k++] = str1[i];
        }
        return process1(str1, str2, str1.length - 1, str2.length - 1);
    }

    private static int process1(char[] str1, char[] str2, int i, int j) {
        if (i == 0 && j == 0) {
            return str1[i] == str2[j] ? 1 : 0;
        }
        if (i == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            }
            return process1(str1, str2, i, j - 1);
        }
        if (j == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            }
            return process1(str1, str2, i - 1, j);
        }
        int p1 = process1(str1, str2, i - 1, j);
        int p2 = process1(str1, str2, i, j - 1);
        int p3 = str1[i] == str2[j] ? 1 + process1(str1, str2, i - 1, j - 1) : 0;
        return Math.max(p1, Math.max(p2, p3));
    }

    private static int dp1(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
            return 0;
        }
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int N = s1.length;
        int M = s2.length;
        int[][] dp = new int[N][M];
        dp[0][0] = s1[0] == s2[0] ? 1 : 0;
        for (int j = 1; j < M; j++) {
            dp[0][j] = s1[0] == s2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < N; i++) {
            dp[i][0] = s1[i] == s2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = s1[i] == s2[j] ? (1 + dp[i - 1][j - 1]) : dp[i - 1][j - 1];
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[N - 1][M - 1];
    }

    //-----------------------------------------
    public static int way2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] s = str.toCharArray();
        return process2(s, 0, s.length - 1);
    }

    private static int process2(char[] str, int L, int R) {
        if (L == R) {
            return 1;
        }
        if (L == R - 1) {
            return str[L] == str[R] ? 2 : 1;
        }
        int p1 = process2(str, L + 1, R);
        int p2 = process2(str, L, R - 1);
        int p3 = str[L] == str[R] ? 2 + process2(str, L + 1, R - 1) : 0;
        int p4 = process2(str, L + 1, R - 1);       //根据下面动态规划最后得出的结果，这步可以省略
        return Math.max(p1, Math.max(p2, Math.max(p3, p4)));
    }

    //--------------------------------------
    public static int way3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        dp[N-1][N-1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int i = N - 3; i >= 0 ; i--) {
            for (int j = i + 2; j < N; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                if (str[i] == str[j]) {
                    dp[i][j] = Math.max(dp[i][j], 2 + dp[i + 1][j - 1]);
                }
            }
        }
        return dp[0][N - 1];
    }
}
