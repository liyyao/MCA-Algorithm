package leetcode.unionfind;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 1、https://leetcode.com/problems/number-of-islands/
 * 岛问题：给定一个二维数组matrix，里面的值不是1就是0，上、下、左、右相邻的1认为是一片岛，返回matrix中岛的数量
 */
public class T01_NumberOfIslands {

    /**
     * 感染法，如果当前位置是1，则将当前位置上下左右是1的都感染成0
     * @param grid
     * @return
     */
    public int numIslands1(char[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    ans++;
                    infection(grid, i, j);
                }
            }
        }
        return ans;
    }

    public void infection(char[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] != '1') {
            return;
        }
        grid[x][y] = '0';
        infection(grid, x - 1, y);
        infection(grid, x + 1, y);
        infection(grid, x, y - 1);
        infection(grid, x, y + 1);
    }

    //------------------------------------------------------------------------------
    /**
     * 使用并查集-hashmap
     * @param grid
     * @return
     */
    public int numIslands2(char[][] grid) {
        UnionFind u = new UnionFind(grid);
        for (int i = 1; i < grid[0].length; i++) {
            if (grid[0][i] == '1' && grid[0][i - 1] == '1') {
                u.union(0, i - 1,0, i);
            }
        }
        for (int i = 1; i < grid.length; i++) {
            if (grid[i - 1][0] == '1' && grid[i][0] == '1') {
                u.union(i - 1, 0, i, 0);
            }
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[i].length; j++) {
                if (grid[i][j - 1] == '1' && grid[i][j] == '1') {
                    u.union(i, j - 1, i, j);
                }
                if (grid[i - 1][j] == '1' && grid[i][j] == '1') {
                    u.union(i - 1, j, i, j);
                }
            }
        }
        return u.size();
    }

    static class UnionFind {
        static class Node {
            char c;

            public Node(char c) {
                this.c = c;
            }
        }

        Map<String, Node> nodes;
        Map<Node, Node> parents;
        Map<Node, Integer> sizes;

        public UnionFind(char[][] grid) {
            parents = new HashMap<>();
            sizes = new HashMap<>();
            nodes = new HashMap<>();
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == '1') {
                        Node node = new Node(grid[i][j]);
                        parents.put(node, node);
                        sizes.put(node, 1);
                        nodes.put(getKey(i, j), node);
                    }
                }
            }
        }

        private String getKey(int x, int y) {
            return x + "-" + y;
        }

        public Node getNode(int x, int y) {
            return nodes.get(getKey(x, y));
        }

        private Node findParents(Node a) {
            if (a == null) {
                return null;
            }
            Stack<Node> path = new Stack<>();
            while (a != parents.get(a)) {
                path.push(a);
                a = parents.get(a);
            }
            while (!path.isEmpty()) {
                parents.put(path.pop(), a);
            }
            return a;
        }

        public boolean isSameSet(Node a, Node b) {
            return findParents(a) == findParents(b);
        }

        private void union(int x, int y, int i, int j) {
            union(getNode(x, y), getNode(i, j));
        }

        public void union(Node a, Node b) {
            Node aParents = findParents(a);
            Node bParents = findParents(b);
            if (aParents == bParents || aParents == null || bParents == null) {
                return;
            }
            Integer aSize = sizes.get(aParents);
            Integer bSize = sizes.get(bParents);
            Node big = aSize >= bSize ? aParents : bParents;
            Node small = big == aParents ? bParents : aParents;
            parents.put(small, big);
            sizes.put(big, aSize + bSize);
            sizes.remove(small);
        }

        public int size() {
            return sizes.size();
        }
    }

    //--------------------------------------------------------------------------------------------


    public int numIslands3(char[][] grid) {
        UnionFind2 u = new UnionFind2(grid);
        for (int i = 1; i < grid[0].length; i++) {
            if (grid[0][i] == '1' && grid[0][i - 1] == '1') {
                u.union(0, i - 1,0, i);
            }
        }
        for (int i = 1; i < grid.length; i++) {
            if (grid[i - 1][0] == '1' && grid[i][0] == '1') {
                u.union(i - 1, 0, i, 0);
            }
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[i].length; j++) {
                if (grid[i][j - 1] == '1' && grid[i][j] == '1') {
                    u.union(i, j - 1, i, j);
                }
                if (grid[i - 1][j] == '1' && grid[i][j] == '1') {
                    u.union(i - 1, j, i, j);
                }
            }
        }
        return u.size();
    }

    /**
     * 使用数组实现
     */
    static class UnionFind2 {
        private final int[] parents;      //记录parents下标
        private final int[] path;
        private final int[] sizes;
        private final int col;
        private int sets;

        public UnionFind2(char[][] grid) {
            sets = 0;
            col = grid[0].length;
            int row = grid.length;
            int len = col * row;
            parents = new int[len];
            path = new int[len];
            sizes = new int[len];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == '1') {
                        int index = index(i, j);
                        parents[index] = index;
                        sizes[index] = 1;
                        sets++;
                    }
                }
            }
        }

        // (r, c) -> i;
        private int index(int r, int c) {
            return r * col + c;
        }

        private int findParents(int landIndex) {
            int i = 0;
            while (landIndex != parents[landIndex]) {
                path[i++] = landIndex;
                landIndex = parents[landIndex];
            }
            for (int j = 0; j < i; j++) {
                parents[path[j]] = landIndex;
            }
            return landIndex;
        }

        private void union(int x, int y, int i, int j) {
            int parents1 = findParents(index(x, y));
            int parents2 = findParents(index(i, j));
            if (parents1 == parents2) {
                return;
            }
            int size1 = sizes[parents1];
            int size2 = sizes[parents2];
            int big = size1 >= size2 ? parents1 : parents2;
            int small = big == parents1 ? parents2 : parents1;
            parents[small] = big;
            sizes[big] = size1 + size2;
            sets--;
        }

        private int size() {
            return sets;
        }
    }

    //for test
    private static char[][] generateCharArr(int maxRow, int maxColumn) {
        int row = (int) (Math.random() * maxRow + 1);
        int column = (int) (Math.random() * maxColumn + 1);
        char[][] ans = new char[row][column];
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[i].length; j++) {
                ans[i][j] = generateChar();
            }
        }
        return ans;
    }

    private static char generateChar() {
        return Math.random() < 0.5 ? '1' : '0';
    }

    private static char[][] copyCharArr(char[][] arr) {
        if (arr == null) {
            return null;
        }
        char[][] ans = new char[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                ans[i][j] = arr[i][j];
            }
        }
        return ans;
    }

    private static void printCharArr(char[][] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        /*int testTimes = 1000000;
        int maxRow = 10;
        int maxColumn = 10;
        System.out.println("测试开始");
        T01_NumberOfIslands t = new T01_NumberOfIslands();
        for (int i = 0; i < testTimes; i++) {
            char[][] char1 = generateCharArr(maxRow, maxColumn);
            char[][] char2 = copyCharArr(char1);
            if (t.numIslands1(char1) != t.numIslands3(char2)) {
                System.out.println("出错了...");
                return;
            }
        }
        System.out.println("测试结束");*/

        compareTest();
    }

    private static void compareTest() {
        int row = 0;
        int col = 0;
        char[][] board1 = null;
        char[][] board2 = null;
        char[][] board3 = null;
        long start = 0;
        long end = 0;

        row = 1000;
        col = 1000;
        board1 = generateCharArr(row, col);
        board2 = copyCharArr(board1);
        board3 = copyCharArr(board1);

        T01_NumberOfIslands t = new T01_NumberOfIslands();

        System.out.println("感染方法、并查集(map实现)、并查集(数组实现)的运行结果和运行时间");
        System.out.println("随机生成的二维矩阵规模 : " + row + " * " + col);

        start = System.currentTimeMillis();
        System.out.println("感染方法的运行结果: " + t.numIslands1(board1));
        end = System.currentTimeMillis();
        System.out.println("感染方法的运行时间: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        System.out.println("并查集(map实现)的运行结果: " + t.numIslands2(board2));
        end = System.currentTimeMillis();
        System.out.println("并查集(map实现)的运行时间: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        System.out.println("并查集(数组实现)的运行结果: " + t.numIslands3(board3));
        end = System.currentTimeMillis();
        System.out.println("并查集(数组实现)的运行时间: " + (end - start) + " ms");

        System.out.println();

        row = 10000;
        col = 10000;
        board1 = generateCharArr(row, col);
        board3 = copyCharArr(board1);
        System.out.println("感染方法、并查集(数组实现)的运行结果和运行时间");
        System.out.println("随机生成的二维矩阵规模 : " + row + " * " + col);

        start = System.currentTimeMillis();
        System.out.println("感染方法的运行结果: " + t.numIslands1(board1));
        end = System.currentTimeMillis();
        System.out.println("感染方法的运行时间: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        System.out.println("并查集(数组实现)的运行结果: " + t.numIslands3(board3));
        end = System.currentTimeMillis();
        System.out.println("并查集(数组实现)的运行时间: " + (end - start) + " ms");
    }

}
