package leetcode.unionfind;

import java.util.*;

/**
 * 3、https://leetcode.com/problems/number-of-islands-ii/
 * (这是一道付费题)
 *  Number of Islands II
 *  A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the
 *  water at position (row, col) into a land, Given a list of positions to operate, count the number of islands after each
 *  addLand operation. An island is surrounded by water and is formed by connection adjacent lands horizontally or vertically.
 *  You may assume all four edges of the grid are all surrounded by water.
 *
 *  Example:
 *     Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
 *     output: [1, 1, 2, 3]
 *  Explanation:
 *     Initially, the 2d grid grid is filled with water.(Assume 0 represents water and 1 represents land).
 *         0 0 0
 *         0 0 0
 *         0 0 0
 *     Operation #1: addLand(0,0) turns the water at grid[0][0] into a land.
 *         1 0 0
 *         0 0 0       Number of islands = 1
 *         0 0 0
 *
 *     Operation #2: addLand(0,1) turns the water at grid[0][1] into a land.
 *         1 1 0
 *         0 0 0       Number of islands = 1
 *         0 0 0
 *
 *     Operation #3: addLand(1,2) turns the water at grid[1][2] into a land.
 *         1 1 0
 *         0 0 1       Number of islands = 2
 *         0 0 0
 *
 *     Operation #4: addLand(2,1) turns the water at grid[2][1] into a land.
 *         1 1 0
 *         0 0 1       Number of islands = 3
 *         0 1 0
 *
 * Follow up:
 * Can you do it in time Complexity O(k log mn), where k is the length of the positions ?
 */
public class T03_NumberOfIslands2 {

    /**
     * 自己写的方法
     * @param m
     * @param n
     * @param positions
     * @return
     */
    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> ansList = new ArrayList<>();
        UnionFind u = new UnionFind(m, n);
        for (int[] position : positions) {
            u.union(position[0], position[1]);
            ansList.add(u.getSize());
        }
        return ansList;
    }

    static class UnionFind {
        private final int[] parents;
        private final int[] sizes;
        private final int[] path;
        private final int row;
        private final int col;
        private int size;

        public UnionFind(int m, int n) {
            row = m;
            col = n;
            size = 0;
            int len = m * n;
            parents = new int[len];
            sizes = new int[len];
            path = new int[len];
        }

        public int index(int x, int y) {
            return x * col + y;
        }

        public int findParents(int x, int y) {
            int index = index(x, y);
            int hi = 0;
            while (index != parents[index]) {
                path[hi++] = index;
                index = parents[index];
            }
            for (hi--; hi >= 0; hi--) {
                parents[path[hi]] = index;
            }
            return index;
        }

        public void union(int x, int y) {
            int index = index(x, y);
            if (sizes[index] != 0) {
                return;
            }
            size++;
            sizes[index] = 1;
            parents[index] = index;
            if (x - 1 >= 0 && sizes[index(x - 1, y)] != 0) {     //上
                union(x, y, x - 1, y);
            }
            if (x + 1 < row && sizes[index(x + 1, y)] != 0) {      //下
                union(x, y, x + 1, y);
            }
            if (y - 1 >= 0 && sizes[index(x, y - 1)] != 0) {     //左
                union(x, y, x, y - 1);
            }
            if (y + 1 < col && sizes[index(x, y + 1)] != 0) {      //右
                union(x, y, x, y + 1);
            }
        }

        public void union(int x, int y, int i, int j) {
            int parent1 = findParents(x, y);
            int parent2 = findParents(i, j);
            if (parent1 == parent2) {
                return;
            }
            if (sizes[parent1] >= sizes[parent2]) {
                sizes[parent1] += sizes[parent2];
                parents[parent2] = parent1;
            } else {
                sizes[parent2] += parents[parent1];
                parents[parent1] = parent2;
            }
            size--;
        }

        public int getSize() {
            return size;
        }
    }

    /**
     * 参考左神的方法
     * @param m
     * @param n
     * @param positions
     * @return
     */
    public static List<Integer> numIslands21(int m, int n, int[][] positions) {
        UnionFind1 uf = new UnionFind1(m, n);
        List<Integer> ans = new ArrayList<>();
        for (int[] position : positions) {
            ans.add(uf.connect(position[0], position[1]));
        }
        return ans;
    }

    public static class UnionFind1 {
        private final int[] parent;
        private final int[] size;
        private final int[] help;
        private final int row;
        private final int col;
        private int sets;

        public UnionFind1(int m, int n) {
            row = m;
            col = n;
            sets = 0;
            int len = row * col;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
        }

        private int index(int r, int c) {
            return r * col + c;
        }

        private int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        private void union(int r1, int c1, int r2, int c2) {
            if (r1 < 0 || r1 == row || r2 < 0 || r2 == row || c1 < 0 || c1 == col || c2 < 0 || c2 == col) {
                return;
            }
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            if (size[i1] == 0 || size[i2] == 0) {
                return;
            }
            int f1 = find(i1);
            int f2 = find(i2);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }

        public int connect(int r, int c) {
            int index = index(r, c);
            if (size[index] == 0) {
                parent[index] = index;
                size[index] = 1;
                sets++;
                union(r - 1, c, r, c);
                union(r + 1, c, r, c);
                union(r, c - 1, r, c);
                union(r, c + 1, r, c);
            }
            return sets;
        }

    }

    //---------如果m*n比较大，会经历很重的初始化，而k比较小，怎么优化的方法-------------
    public static List<Integer> numIslands3(int m, int n, int[][] positions) {
        List<Integer> ansList = new ArrayList<>();
        UnionFind3 u = new UnionFind3(m, n);
        for (int[] position : positions) {
            u.connection(position[0], position[1]);
            ansList.add(u.getSize());
        }
        return ansList;
    }

    static class UnionFind3 {
        private final Map<String, String> parents;
        private final Map<String, Integer> sizes;
        private final int row;
        private final int col;
        private int size;

        public UnionFind3(int row, int col) {
            parents = new HashMap<>();
            sizes = new HashMap<>();
            this.row = row;
            this.col = col;
            size = 0;
        }

        private String key(int row, int col) {
            return row + "-" + col;
        }

        public String findParents(int x, int y) {
            String key = key(x, y);
            Stack<String> path = new Stack<>();
            while (!key.equals(parents.get(key))) {
                path.push(key);
                key = parents.get(key);
            }
            while (!path.isEmpty()) {
                parents.put(path.pop(), key);
            }
            return key;
        }

        public void connection(int x, int y) {
            String key = key(x, y);
            if (sizes.containsKey(key)) {
                return;
            }
            size++;
            sizes.put(key, 1);
            parents.put(key, key);
            if (x - 1 >= 0 && sizes.get(key(x - 1, y)) != null) {     //上
                union(x, y, x - 1, y);
            }
            if (x + 1 < row && sizes.get(key(x + 1, y)) != null) {      //下
                union(x, y, x + 1, y);
            }
            if (y - 1 >= 0 && sizes.get(key(x, y - 1)) != null) {     //左
                union(x, y, x, y - 1);
            }
            if (y + 1 < col && sizes.get(key(x, y + 1)) != null) {      //右
                union(x, y, x, y + 1);
            }
        }

        public void union(int x, int y, int i, int j) {
            String p1 = findParents(x, y);
            String p2 = findParents(i, j);
            if (p1.equals(p2)) {
                return;
            }
            Integer size1 = sizes.get(p1);
            Integer size2 = sizes.get(p2);
            if (size1 >= size2) {
                sizes.put(p1, size1 + size2);
                parents.put(p2, p1);
            } else {
                sizes.put(p2, size1 + size2);
                parents.put(p1, p2);
            }
            size--;
        }

        public int getSize() {
            return size;
        }
    }


    //for test
    private static int[][] generatePositions(int maxRow, int maxCol, int maxSize) {
        int size = (int) (Math.random() * maxSize + 1);
        int[][] ans = new int[size][2];
        for (int i = 0; i < ans.length; i++) {
            int row = (int) (Math.random() * maxRow);
            int col = (int) (Math.random() * maxCol);
            ans[i][0] = row;
            ans[i][1] = col;
        }
        return ans;
    }

    public static void main(String[] args) {
        int testTimes = 1000000;
        int maxRow = 10;
        int maxCol = 8;
        int maxSize = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[][] arrs = generatePositions(maxRow, maxCol, maxSize);
            List<Integer> ans1 = numIslands3(maxRow, maxCol, arrs);
            List<Integer> ans2 = numIslands21(maxRow, maxCol, arrs);
            if (ans1.size() != ans2.size()) {
                System.out.println("出错了...长度不相等");
                return;
            }
            for (int j = 0; j < ans1.size(); j++) {
                if (!Objects.equals(ans1.get(j), ans2.get(j))) {
                    System.out.println("出错了...");
                    return;
                }
            }
        }
        System.out.println("测试结束");
    }
}
