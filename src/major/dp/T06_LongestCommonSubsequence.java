package major.dp;

/**
 * 6、给定两个字符串str1和str2，返回这两个字符串的最长公共子序列长度
 *    比如：str1 = "a12b3c456d"，strt = "1ef23ghi4j56k"
 *    最长公共子序列是"123456"，所以返回长度6
 */
public class T06_LongestCommonSubsequence {

    public static int longestCommonSubsequence1(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
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
        int p3 = str1[i] == str2[j] ? (1 + process1(str1, str2, i - 1, j - 1)) : 0;
        return Math.max(p1, Math.max(p2, p3));
    }

    public static int longestCommonSubsequence2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 0; i < N; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        for (int j = 0; j < M; j++) {
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = str1[i] == str2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[N - 1][M - 1];
    }
}
