package leetcode.unionfind;

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
                if ("1".equals(String.valueOf(grid[i][j]))) {
                    ans++;
                    infection(grid, i, j);
                }
            }
        }
        return ans;
    }

    public void infection(char[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
            return;
        }
        if ("1".equals(String.valueOf(grid[x][y]))) {
            grid[x][y] = '0';
            infection(grid, x - 1, y);
            infection(grid, x + 1, y);
            infection(grid, x, y - 1);
            infection(grid, x, y + 1);
        }
    }

    //for test
    private char[][] generateCharArr(int maxRow, int maxColumn) {
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

    private char generateChar() {
        return Math.random() < 0.5 ? '1' : '0';
    }

    private char[][] copyCharArr(char[][] arr) {
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

    public static void main(String[] args) {

    }
}
