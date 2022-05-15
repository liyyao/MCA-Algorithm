package major.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 2、打印一个字符串的全部子序列
 */
public class T02_StringAllSubStr {

    public static void main(String[] args) {
        String s = "abccd";
        List<String> ans = new ArrayList<>();
        process(s.toCharArray(), 0, ans);
        for (String str : ans) {
            System.out.println(str);
        }
        System.out.println("=======");

        List<String> ans1 = subs(s);
        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=================");

        List<String> ans2 = subsNoRepeat(s);
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=================");
    }

    private static void process(char[] str, int index, List<String> list) {
        if (index == str.length) {
            list.add("");
            return;
        }
        process(str, index + 1, list);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            list.add(str[index] + list.get(i));
        }
    }

    public static List<String> subs(String s) {
        char[] str = s.toCharArray();
        String path = "";
        List<String> ans = new ArrayList<>();
        process1(str, 0, ans, path);
        return ans;
    }

    /**
     *
     * @param str 固定参数
     * @param i 来到了str[i]字符，i是位置
     * @param ans   把所有生成的子序列放入到ans里去
     * @param path str[0...i-1]已经走过了，之前的决定，都在path上，之前的决定不能改变了
     */
    private static void process1(char[] str, int i, List<String> ans, String path) {
        if (i == str.length) {
            ans.add(path);
            return;
        }
        //没有要i位置的字符
        process1(str, i + 1, ans, path);
        //要了i位置的字符
        process1(str, i + 1, ans, path + str[i]);
    }

    /**
     * 3、打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
     * @param s
     * @return
     */
    public static List<String> subsNoRepeat(String s) {
        char[] str = s.toCharArray();
        String path = "";
        Set<String> set = new HashSet<>();
        process2(str, 0, set, path);
        List<String> ans = new ArrayList<>();
        for (String cur : set) {
            ans.add(cur);
        }
        return ans;
    }

    private static void process2(char[] str, int index, Set<String> set, String path) {
        if (index == str.length) {
            set.add(path);
            return;
        }
        String no = path;
        process2(str, index + 1, set, no);
        String yes = path + str[index];
        process2(str, index + 1, set, yes);
    }
}
