package major.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 重要
 * https://leetcode.com/problems/stickers-to-spell-word/
 * 5、给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文，arr每一个字符串，代表一张贴纸，
 *    你可以把单个字符剪开使用，目的是拼出str来，返回需要至少多少张贴纸可以完成这个任务。
 *    例子：str="babac"，arr={"ba","c","abcd"}
 *         ba + ba + c 3
 *         abcd + abcd 2
 *         abcd + ba 2
 *         所以返回2
 */
public class T05_StickersToSpellWord {

    public static int minStickers1(String[] stickers, String target) {
        int ans = process1(stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int process1(String[] stickers, String target) {
        if (target == null || target.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String sticker : stickers) {
            String str = cutStr(target, sticker);
            if (str.length() != target.length()) {
                min = Math.min(min, process1(stickers, str));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    private static String cutStr(String target, String str) {
        if (target == null || target.length() == 0 || str == null || str.length() == 0) {
            return target;
        }
        int[] arr = new int[26];
        char[] strArr = str.toCharArray();
        for (char c : strArr) {
            arr[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        char[] targetArr = target.toCharArray();
        for (char c : targetArr) {
            if (arr[c-'a'] > 0) {
                arr[c-'a']--;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    //--------------------
    public static int minStickers2(String[] stickers, String target) {
        int N = stickers.length;
        //关键优化（用词频表替代贴纸数组）
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] str = stickers[i].toCharArray();
            for (char c : str) {
                counts[i][c - 'a']++;
            }
        }
        int ans = process2(counts, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int process2(int[][] stickers, String t) {
        if (t.length() == 0) {
            return 0;
        }
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char c : target) {
            tcounts[c - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int[] sticker : stickers) {
            //尝试第一张贴纸是谁
            //最关键的优化（重要的剪枝！这一步也是贪心！）
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            sb.append((char) (j + 'a'));
                        }
                    }
                }
                min = Math.min(min, process2(stickers, sb.toString()));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    //-----------------------

    public static int minStickers3(String[] stickers, String target) {
        int N = stickers.length;
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] str = stickers[i].toCharArray();
            for (char c : str) {
                counts[i][c - 'a']++;
            }
        }
        Map<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        int ans = process3(counts, target, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int process3(int[][] stickers, String t, Map<String, Integer> dp) {
        if (dp.containsKey(t)) {
            return dp.get(t);
        }
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char c : target) {
            tcounts[c - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int[] sticker : stickers) {
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 26; i++) {
                    if (tcounts[i] > 0) {
                        int nums = tcounts[i] - sticker[i];
                        for (int j = 0; j < nums; j++) {
                            sb.append((char)(i + 'a'));
                        }
                    }
                }
                min = Math.min(min, process3(stickers, sb.toString(), dp));
            }
        }
        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(t, ans);
        return ans;
    }

    //for test
    private static String generateStr(int len) {
        int size = (int) (Math.random() * (len + 1));
        char[] ans = new char[size];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) (Math.random() * 26 + 'a');
        }
        return String.valueOf(ans);
    }

    private static String[] generateStrArr(int maxLen, int maxSize) {
        int size = (int) (Math.random() * maxSize + 1);
        String[] ans = new String[size];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateStr(maxLen);
        }
        return ans;
    }

    public static void main(String[] args) {
        int strLen = 10;
        int stickerLen = 10;
        int stickerSize = 5;
        int testTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            String target = generateStr(strLen);
            String[] stickers = generateStrArr(stickerLen, stickerSize);
            int ans1 = minStickers1(stickers, target);
            int ans2 = minStickers2(stickers, target);
            int ans3 = minStickers3(stickers, target);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
