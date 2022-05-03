package leetcode.unionfind;

/**
 * 2、https://leetcode.com/problems/number-of-provinces/
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
 * Return the total number of provinces.
 *
 * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * Output: 2
 *
 * Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * Output: 3
 *
 * Constraints:
 *     1 <= n <= 200
 *     n == isConnected.length
 *     n == isConnected[i].length
 *     isConnected[i][j] is 1 or 0.
 *     isConnected[i][i] == 1
 *     isConnected[i][j] == isConnected[j][i]
 */
public class T02_NumberOfProvinces {

    public int findCircleNum(int[][] isConnected) {
        if (isConnected == null) {
            return 0;
        }
        int N = isConnected.length;
        UnionFind u = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isConnected[i][j] == 1) {
                    u.union(i, j);
                }
            }
        }
        return u.getSets();
    }

    static class UnionFind {
        private final int[] parents;
        private final int[] sizes;
        private final int[] path;
        private int sets;

        public UnionFind(int N) {
            parents = new int[N];
            sizes = new int[N];
            path = new int[N];
            sets = N;
            for (int i = 0; i < N; i++) {
                parents[i] = i;
                sizes[i] = 1;
            }
        }

        public int findParents(int i) {
            int index = 0;
            while (i != parents[i]) {
                path[index++] = i;
                i = parents[i];
            }
            for (index--; index >= 0 ; index--) {
                parents[path[index]] = i;
            }
            return i;
        }

        public void union(int i, int j) {
            int parents1 = findParents(i);
            int parents2 = findParents(j);
            if (parents1 != parents2) {
                int size1 = sizes[parents1];
                int size2 = sizes[parents2];
                if (size1 >= size2) {
                    parents[parents2] = parents1;
                    sizes[parents2] = size1 + size2;
                } else {
                    parents[parents1] = parents2;
                    sizes[parents1] = size1 + size2;
                }
                sets--;
            }
        }

        public int getSets() {
            return sets;
        }
    }
}
