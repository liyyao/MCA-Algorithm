package major.dp;

/**
 * 2、给定一个整型数组arr，代表数值不同的纸牌排成一条线，玩家A和玩家B依次拿走每张纸牌
 *    规定玩家A先拿，玩家B后拿，但是每个玩家每次只能拿走最左或最右的纸牌
 *    玩家A和玩家B都绝顶聪明，请返回最后获胜的分数。
 */
public class T02_Card {

    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int first = first(arr, 0, arr.length - 1);
        int second = second(arr, 0, arr.length - 1);
        return Math.max(first, second);
    }

    //先手获得的最好分数返回
    private static int first(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int ans1 = arr[L] + second(arr, L + 1, R);
        int ans2 = arr[R] + second(arr, L, R - 1);
        return Math.max(ans1, ans2);
    }

    //后手获得的最好分数返回
    private static int second(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int ans1 = first(arr, L + 1, R);    //对手拿走了L位置的数
        int ans2 = first(arr, L, R - 1);    //对手拿走了R位置的数
        return Math.min(ans1, ans2);           //对手拿完牌后留下的最小的牌，这里需要好好理解
    }

    //-----------------------------------

    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] fmap = new int[arr.length][arr.length];
        int[][] smap = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                fmap[i][j] = -1;
                smap[i][j] = -1;
            }
        }
        int first = first2(arr, 0, arr.length - 1, fmap, smap);
        int second = second2(arr, 0, arr.length - 1, fmap, smap);
        return Math.max(first, second);
    }

    private static int first2(int[] arr, int L, int R, int[][] fmap, int[][] smap) {
        if (fmap[L][R] != -1) {
            return fmap[L][R];
        }
        int ans = 0;
        if (L == R) {
            ans = arr[L];
        } else {
            int ans1 = arr[L] + second2(arr, L + 1, R, fmap, smap);
            int ans2 = arr[R] + second2(arr, L, R - 1, fmap, smap);
            ans = Math.max(ans1, ans2);
            fmap[L][R] = ans;
        }
        return ans;
    }

    private static int second2(int[] arr, int L, int R, int[][] fmap, int[][] smap) {
        if (smap[L][R] != -1) {
            return smap[L][R];
        }
        int ans = 0;
        if (L != R) {
            int ans1 = first2(arr, L + 1, R, fmap, smap);
            int ans2 = first2(arr, L, R - 1, fmap, smap);
            ans = Math.min(ans1, ans2);
        }
        smap[L][R] = ans;
        return ans;
    }

    //-----------------------------------

    public static int win3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int len = arr.length;
        int[][] first = new int[len][len];
        int[][] second = new int[len][len];
        for (int i = 0; i < len; i++) {
            first[i][i] = arr[i];
        }
        for (int col = 1; col < len; col++) {
            int l = 0;
            int r = col;
            while (r < len) {
                first[l][r] = Math.max(arr[l] + second[l + 1][r], arr[r] + second[l][r - 1]);
                second[l][r] = Math.min(first[l + 1][r], first[l][r - 1]);
                l++;
                r++;
            }
        }
        return Math.max(first[0][len - 1], second[0][len - 1]);
    }

    public static void main(String[] args) {
        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));
    }
}
