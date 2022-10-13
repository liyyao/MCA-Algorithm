package major.dp;

/**
 * 1、假设有排成一行的N个位置，记为1~N，N一定大于或等于2，开始时机器人在其中的M位置上（M一定是1~N中的一个）
 *     如果机器人来到1位置，那么下一步只能往右来到2位置；
 *     如果机器人来到N位置，那么下一步只能往左来到N-1位置；
 *     如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 *   规定机器人必须走K步，最终能来到P位置（P也是1~N中的一个）的方法有多少种
 *   给定四个参数N、M、K、P，返回方法数。
 */
public class T01_Step {

    private static int ways1(int N, int M, int K, int P) {
        return process1(N, M, K, P);
    }

    private static int process1(int size, int start, int rest, int aim) {
        if (rest == 0) {
            return start == aim ? 1 : 0;
        }
        if (start == 1) {
            return process1(size, 2, rest - 1, aim);
        }
        if (start == size) {
            return process1(size, start - 1, rest - 1, aim);
        }
        return process1(size, start - 1, rest - 1, aim) + process1(size, start + 1, rest - 1, aim);
    }

    private static int ways2(int N, int M, int K, int P) {
        int[][] arr = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                arr[i][j] = -1;
            }
        }
        return process2(N, M, K, P, arr);
    }

    private static int process2(int size, int start, int rest, int aim, int[][] arr) {
        if (arr[start][rest] != -1) {
            return arr[start][rest];
        }
        int ans = 0;
        if (rest == 0) {
            ans = start == aim ? 1 : 0;
        } else if (start == 1) {
            ans = process2(size, 2, rest - 1, aim, arr);
        } else if (start == size) {
            ans = process2(size, start - 1, rest - 1, aim, arr);
        } else {
            ans = process2(size, start - 1, rest - 1, aim, arr) + process2(size, start + 1, rest - 1, aim, arr);
        }
        arr[start][rest] = ans;
        return ans;
    }

    public static int ways3(int N, int M, int K, int P) {
        int[][] arr = new int[N + 1][K + 1];
        arr[P][0] = 1;
        for (int i = 1; i <= K; i++) {
            arr[1][i] = arr[2][i-1];
            for (int j = 2; j < N; j++) {
                arr[j][i] = arr[j-1][i-1] + arr[j+1][i-1];
            }
            arr[N][i] = arr[N-1][i-1];
        }
        return arr[M][K];
    }


    public static void main(String[] args) {
        System.out.println(ways1(5, 2, 6, 4));
        System.out.println(ways2(5, 2, 6, 4));
        System.out.println(ways3(5, 2, 6, 4));
    }
}
