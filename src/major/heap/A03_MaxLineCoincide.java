package major.heap;

import tool.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最大线段重合问题
 * 给定很多线段，每个线段都有两个数[start, end]，表示线段开始位置和结束位置，左右都是闭区间
 * 规定：
 *  1.线段的开始和结束位置一定都是整数值
 *  2.线段重合区域的长度必须>=1
 *  返回线段最多重合区域中，包含了几条线段
 */
public class A03_MaxLineCoincide {

    public static void main(String[] args) {
        int testTimes = 100000;
        int len = 100;
        int value = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[][] arr = ArrayUtils.randomArrayPositiveCoordinate(len, value);
            int test = test(arr);
            int ans = maxLineCoincide(arr);
            if (ans != test) {
                System.out.println("出错了");
                ArrayUtils.print(arr);
                System.out.println("test = " + test + ", ans = " + ans);
                break;
            }
        }
        System.out.println("测试结束");
    }

    public static int maxLineCoincide(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Line[] lines = new Line[arr.length];
        for (int i = 0; i < arr.length; i++) {
            lines[i] = new Line(arr[i][0], arr[i][1]);
        }
        Arrays.sort(lines, new LineStartComparator());
        PriorityQueue<Line> heap = new PriorityQueue<>(new LineEndComparator());
        int ans = 0;
        for (Line line : lines) {
            while (!heap.isEmpty() && heap.peek().end <= line.start) {
                heap.poll();
            }
            heap.add(line);
            ans = Math.max(ans, heap.size());
        }
        return ans;
    }

    private static class LineStartComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    private static class LineEndComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.end - o2.end;
        }
    }

    private static class Line {
        int start;
        int end;
        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int test(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int min = arr[0][0];
        int max = arr[0][1];
        for (int i = 1; i < arr.length; i++) {
            min = Math.min(min, arr[i][0]);
            max = Math.max(max, arr[i][1]);
        }
        int ans = 0;
        for (int i = min; i < max; i++) {
            double v = i + 0.5;
            int count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j][0] < v && arr[j][1] > v) {
                    count += 1;
                }
            }
            ans = Math.max(ans, count);
        }
        return ans;
    }

}
