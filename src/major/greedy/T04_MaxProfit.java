package major.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 4、输入：正数数组costs、正数数组profits、正数K、正数M
 *     costs[i]表示i号项目的花费；
 *     profits[i]表示i号项目在扣除花费之后还能挣到的钱（利润）；
 *     K表示你只能串行的最多做K个项目
 *     M表示你初始资金
 * 说明：每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
 * 输出：你最后获得的最大钱数。
 */
public class T04_MaxProfit {

    static class Program {
        int cost;
        int profit;

        public Program(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    public static int profit1(int[] costs, int[] profits, int k, int m) {
        PriorityQueue<Program> costPQ = new PriorityQueue<>();
        for (int i = 0; i < costs.length; i++) {
            costPQ.add(new Program(costs[i], profits[i]));
        }
        PriorityQueue<Program> profitPQ = new PriorityQueue<>((o1, o2) -> o2.profit - o1.profit);
        while (k-- > 0) {
            while (!costPQ.isEmpty() && costPQ.peek().cost <= m) {
                profitPQ.add(costPQ.poll());
            }
            if (profitPQ.isEmpty()) {
                return m;
            }
            m += profitPQ.poll().profit;
        }
        return m;
    }
}
