package major.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 4、打印一个字符串的全部排列
 * 5、打印一个字符串的全部排列，要求不要出现重复的排列
 */
public class T03_PrintAllPermutations {

    public static List<String> permutation1(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        List<Character> rest = new ArrayList<>();
        for (char c : str) {
            rest.add(c);
        }
        String path = "";
        f(rest, path, ans);
        return ans;
    }

    private static void f(List<Character> rest, String path, List<String> ans) {
        if (rest.isEmpty()) {
            ans.add(path);
            return;
        }
        int n = rest.size();
        for (int i = 0; i < n; i++) {
            char cur = rest.get(i);
            rest.remove(i);
            f(rest, path + cur, ans);
            rest.add(i, cur);
        }
    }

    public static List<String> permutation2(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        g1(str, 0, ans);
        return ans;
    }

    private static void g1(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
            return;
        }
        for (int i = index; i < str.length; i++) {
            swap(str, index, i);
            g1(str, index + 1, ans);
            swap(str, index, i);
        }
    }

    /**
     * 打印一个字符串的全部排列，要求不要出现重复的排列
     * 对已经访问过的不需要再访问了 剪枝
     * @param s
     * @return
     */
    public static List<String> permutation3(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        g2(str, 0, ans);
        return ans;
    }

    private static void g2(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
            return;
        }
        boolean[] visited = new boolean[256];
        for (int i = index; i < str.length; i++) {
            if (!visited[str[i]]) {
                visited[str[i]] = true;
                swap(str, index, i);
                g2(str, index + 1, ans);
                swap(str, index, i);
            }
        }
    }

    private static void swap(char[] str, int index1, int index2) {
        char tmp = str[index1];
        str[index1] = str[index2];
        str[index2] = tmp;
    }

    public static void main(String[] args) {
        String s = "acc";
        List<String> ans1 = permutation1(s);
        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("==================");

        List<String> ans2 = permutation2(s);
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("==================");

        List<String> ans3 = permutation3(s);
        for (String str : ans3) {
            System.out.println(str);
        }
        System.out.println("==================");
    }
}
