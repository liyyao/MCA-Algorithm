package major.tree.questions;

import java.util.ArrayList;
import java.util.List;

/**
 * 16、派对的最大快乐值
 * 员工信息的定义如下：
 * class Employee {
 *     public int happy;   //这名员工可以带来的快乐值
 *     List<Employee> subordinates; //这名员工有哪些直接下级
 * }
 * 公司的每个员工都符合Employee类的描述。整个公司的人员结构可以看作是一棵标准的、没有环的多叉树。树的头节点是公司唯一的老板。
 * 除老板之外的每个员工都有唯一的直接上级。叶节点是没有任何下属的基层员工（subordinates列表为空），除基层员工外，每个员工
 * 都有一个或多个直接下级。这个公司现在要办party，你可以决定哪些员工来，哪些员工不来，规则：
 *     1、如果某个员工来了，那么这个员工的所有直接下级都不能来
 *     2、派对的整体快乐值是所有到场员工快乐值的累加
 *     3、你的目标是让派对的整体快乐值尽量大
 * 给定一棵多叉树的头节点boss，请返回派对的最大快乐值。
 */
public class T16_CalPartyMaxHappyVal {

    private static class Info {
        int come;
        int notCome;

        public Info(int come, int notCome) {
            this.come = come;
            this.notCome = notCome;
        }
    }

    private static class Employee {
        public int happy;
        List<Employee> subordinates;

        public Employee(int happy) {
            this.happy = happy;
            subordinates = new ArrayList<>();
        }
    }

    public static int calMaxHappyVal(Employee employee) {
        if (employee == null) {
            return 0;
        }
        Info info = process(employee);
        return Math.max(info.come, info.notCome);
    }

    private static Info process(Employee employee) {
        if (employee == null) {
            return new Info(0, 0);
        }
        int come = employee.happy;
        int notCome = 0;
        if (employee.subordinates != null && !employee.subordinates.isEmpty()) {
            for (Employee e : employee.subordinates) {
                Info info = process(e);
                come += info.notCome;
                notCome += Math.max(info.come, info.notCome);
            }
        }
        return new Info(come, notCome);
    }

    public static int test(Employee employee) {
        if (employee == null) {
            return 0;
        }
        return process(employee, false);
    }

    private static int process(Employee employee, boolean up) {
        if (up) {
            int ans = 0;
            for (Employee e : employee.subordinates) {
                ans += process(e, false);
            }
            return ans;
        } else {
            int p1 = employee.happy;
            int p2 = 0;
            for (Employee e : employee.subordinates) {
                p1 += process(e, true);
                p2 += process(e, false);
            }
            return Math.max(p1, p2);
        }
    }

    //for test
    private static Employee generateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        generateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    private static void generateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.subordinates.add(next);
            generateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }
    public static void main(String[] args) {
        int testTimes = 100000;
        int maxNexts = 7;
        int maxHappy = 100;
        int maxLevel = 4;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Employee boss = generateBoss(maxLevel, maxNexts, maxHappy);
            if (test(boss) != calMaxHappyVal(boss)) {
                System.out.println("出错了");
                return;
            }
        }
        System.out.println("测试结束");
    }
}
