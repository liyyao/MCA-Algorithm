package major.tree.prefixtree;

import tool.ArrayUtils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 前缀树
 */
public class A01_TrieTree {

    public static class Node1 {
        public int pass;
        public int end;
        public Node1[] nexts;

        public Node1() {
            this.pass = 0;
            this.end = 0;
            nexts = new Node1[26];  //这里默认输入的都是小写字母
        }
    }

    public static class Trie1 {
        private Node1 root;

        public Trie1() {
            root = new Node1();
        }

        /**
         * 增加某个单词
         * @param word
         */
        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chars = word.toCharArray();
            Node1 node = root;
            node.pass++;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node1();
                }
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }

        /**
         * 删除某个单词
         * @param word
         */
        public void delete(String word) {
            if (search(word) != 0) {
                char[] chars = word.toCharArray();
                Node1 node = root;
                node.pass--;
                int index = 0;
                for (int i = 0; i < chars.length; i++) {
                    index = chars[i] - 'a';
                    if (--node.nexts[index].pass == 0) {
                        node.nexts[index] = null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }

        /**
         * word这个单词之前加入的次数
         * @param word
         * @return
         */
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            Node1 node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        /**
         * 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
         * @param pre
         * @return
         */
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chars = pre.toCharArray();
            Node1 node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }
    }

    public static class Node2 {
        public int pass;
        public int end;
        public Map<Integer, Node2> nexts;

        public Node2() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    public static class Trie2 {
        private Node2 root;

        public Trie2() {
            root = new Node2();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chars = word.toCharArray();
            Node2 node = root;
            node.pass++;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i];
                if (!node.nexts.containsKey(index)) {
                    node.nexts.put(index, new Node2());
                }
                node = node.nexts.get(index);
                node.pass++;
            }
            node.end++;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                char[] chars = word.toCharArray();
                Node2 node = root;
                node.pass--;
                int index = 0;
                for (int i = 0; i < chars.length; i++) {
                    index = chars[i];
                    if (--node.nexts.get(index).pass == 0) {
                        node.nexts.remove(index);
                        return;
                    }
                    node = node.nexts.get(index);
                }
                node.end--;
            }
        }

        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            Node2 node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i];
                if (!node.nexts.containsKey(index)) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.end;
        }

        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chars = pre.toCharArray();
            Node2 node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i];
                if (!node.nexts.containsKey(index)) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.pass;
        }
    }

    public static class Right {
        private Map<String, Integer> box;

        public Right() {
            box = new HashMap<>();
        }

        public void insert(String word) {
            if (!box.containsKey(word)) {
                box.put(word, 1);
            } else {
                box.put(word, box.get(word) + 1);
            }
        }

        public void delete(String word) {
            if (box.containsKey(word)) {
                if (box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            if (!box.containsKey(word)) {
                return 0;
            }
            return box.get(word);
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String cur : box.keySet()) {
                if (cur.startsWith(pre)) {
                    count += box.get(cur);
                }
            }
            return count;
        }
    }

    public static String generateRandomString(int strLen) {
        char[] arr = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < arr.length; i++) {
            int value = (int) (Math.random() * 6);
            arr[i] = (char) (97 + value);
        }
        return String.valueOf(arr);
    }

    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] arr = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = generateRandomString(strLen);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 1000000;
        int arrLen = 10;
        int strLen = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            //String[] arr = new String[] {"b", "aaba", "cbb", "b", "dda", "b", "fee"};
            Trie1 trie1 = new Trie1();
            Trie2 trie2 = new Trie2();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    trie2.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    trie2.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = trie2.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                        return;
                    }
                } else {
                    int ans1 = trie1.prefixNumber(arr[j]);
                    int ans2 = trie2.prefixNumber(arr[j]);
                    int ans3 = right.prefixNumber(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        ArrayUtils.print(arr);
                        System.out.println("prefix : " + arr[j]);
                        System.out.println("ans1 = " + ans1 + " ans2 = " + ans2 + " ans3 = " + ans3);
                        System.out.println("Oops!");
                        return;
                    }
                }
            }
        }
        System.out.println("测试结束");
    }
}
