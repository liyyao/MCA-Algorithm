package major.heap;

import java.util.*;

/**
 * 给定一个整型数组，int[] arr;和一个布尔类型数组，boolean[] op，两个数组一定等长，假设长度为N，arr[i]表示 客户编号，op[i]表示客户操作
 * arr = [3, 3, 1, 2, 1, 2, 5...
 *  op = [T, T, T, T, F, T, F...
 * 依次表示：3用户购买了一件商品，3用户购买了一件商品，1用户购买了一件商品，2用户购买了一件商品，1用户退货了一件商品，2用户购买了一件商品，5用户退货了一件商品...
 *
 * 一对arr[i]和op[i]就代表了一个事件：
 *  用户号为arr[i]，op[i] == T 就代表这个用户购买了一件商品，op[i] == F就代表这个用户退货了一件商品
 *  现在你作为电商平台负责人，你想在每一个事件到来的时候，都给购买次数最多的前K名用户颁奖，所以每个事件发生后，你都需要一个得奖名单（得奖区）
 *
 * 得奖系统的规则：
 *  1.如果某个用户购买商品数为0，但是又发生了退货事件，则认为该事件无效，得奖名单和上一个事件发生后一致，如例子中的5用户
 *  2.某用户发生了购买商品事件，购买商品数+1，发生退货事件，购买商品数-1
 *  3.每次都是最多K个用户得奖，K也为传入的参数，如果根据全部规则，得奖人数确实不够K个，那就以不够的情况输出结果
 *  4.得奖系统分为 得奖区和候选区，任何用户只要购买数>0,一定在这两个区域中的一个
 *  5.购买数最大的前K名用户进入得奖区，在最初时如果得奖区没有到达K个用户，那么新来的用户直接进入得奖区
 *  6.如果购买数不足以进入得奖区的用户，进入候选区
 *  7.如果候选区购买数最多的用户，已经足以进入得奖区，该用户应付替换得奖区中购买数最少的用户（大于才能替换）
 *    如果得奖区中购买数最少的用户有多个，就替换最早进入得奖区的用户
 *    如果候选区中购买数最多的用户有多个，机会会给最早进入候选区的用户
 *  8.候选区和得奖区是两套时间，因为用户只会在其中一个区域，所以只会有一个区域的时间，
 *    从得奖区出来进入候选区的用户，得奖区时间删除，进入候选区的时间就是当前事件的时间（可以理解为arr[i]和op[i]中的i），
 *    从候选区出来进入得奖区的用户，候选区时间删除，进入得奖区的时间就是当前事件的时间（可以理解为arr[i]和op[i]中的i）
 *  9.如果某用户购买数==0，不管在哪个区域都离开，区域时间删除，离开是指彻底离开，哪个区域也不会找到该用户，如果下载该用户又发生购买行为，产生>0的购买数
 *    会再次根据之前规则回到某个区域中，进入区域的时间重记
 *
 * 请遍历arr数组和op数组，遍历每一步输出一个得奖名单
 * public List<List<Integer>> topK(int[] arr, boolean[] op, int k)
 *
 */
public class A04_LuckDraw {

    public static List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        TopK topK = new TopK(k);
        for (int i = 0; i < arr.length; i++) {
            topK.operate(arr[i], op[i], i);
            ans.add(topK.getCustomers());
        }
        return ans;
    }

    private static class TopK {
        private A01_HeapGenerate<Customer> prizeHeap;
        private A01_HeapGenerate<Customer> candidateHeap;
        private Map<Integer, Customer> map;
        private final int limit;

        public TopK(int limit) {
            this.limit = limit;
            prizeHeap = new A01_HeapGenerate<>(new PrizeComparator());
            candidateHeap = new A01_HeapGenerate<>(new CandidateComparator());
            map = new HashMap<>();
        }

        public void operate(int id, boolean buy, int entryTime) {
            if (!map.containsKey(id) && !buy) {
                return;
            }
            if (!map.containsKey(id)) {
                map.put(id, new Customer(id, 0, 0));
            }
            Customer c = map.get(id);
            if (buy) {
                c.buy++;
            } else {
                c.buy--;
            }
            if (c.buyEmpty()) {
                map.remove(id);
            }
            if (!prizeHeap.contains(c) && !candidateHeap.contains(c)) {
                if (prizeHeap.size() < limit) {
                    c.entryTime = entryTime;
                    prizeHeap.push(c);
                } else {
                    c.entryTime = entryTime;
                    candidateHeap.push(c);
                }
            } else if (candidateHeap.contains(c)) {
                if (c.buyEmpty()) {
                    candidateHeap.remove(c);
                } else {
                    candidateHeap.resign(c);
                }
            } else {
                if (c.buyEmpty()) {
                    prizeHeap.remove(c);
                } else {
                    prizeHeap.resign(c);
                }
            }
            prizeMove(entryTime);
        }

        private void prizeMove(int entryTime) {
            if (candidateHeap.isEmpty()) {
                return;
            }
            if (prizeHeap.size() < limit) {
                Customer c = candidateHeap.pop();
                c.entryTime = entryTime;
                prizeHeap.push(c);
            } else {
                if (prizeHeap.peek().buy < candidateHeap.peek().buy) {
                    Customer oldCustomer = prizeHeap.pop();
                    Customer newCustomer = candidateHeap.pop();
                    oldCustomer.entryTime = entryTime;
                    newCustomer.entryTime = entryTime;
                    prizeHeap.push(newCustomer);
                    candidateHeap.push(oldCustomer);
                }
            }

        }

        public List<Integer> getCustomers() {
            List<Customer> customers = prizeHeap.getAllElements();
            List<Integer> ans = new ArrayList<>();
            for (Customer c : customers) {
                ans.add(c.id);
            }
            return ans;
        }

        public static class PrizeComparator implements Comparator<Customer> {
            @Override
            public int compare(Customer o1, Customer o2) {
                return o1.buy == o2.buy ? o1.entryTime - o2.entryTime : o1.buy - o2.buy;
            }
        }

        public static class CandidateComparator implements Comparator<Customer> {

            @Override
            public int compare(Customer o1, Customer o2) {
                return o1.buy == o2.buy ? o1.entryTime - o2.entryTime : o2.buy - o1.buy;
            }
        }
    }

    public static List<List<Integer>> compare(int[] arr, boolean[] op, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if (arr == null || k == 0) {
            return ans;
        }
        Map<Integer, Customer> prizeMap = new HashMap<>(k);
        Map<Integer, Customer> waitMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            Customer customer = new Customer(arr[i], op[i] ? 1 : -1, i);
            addOrUpdateCustomerFromPool(prizeMap, waitMap, customer, k);
            List<Integer> prizeList = getAllCustomerFromPool(prizeMap);
            ans.add(prizeList);
        }
        return ans;
    }

    private static void addOrUpdateCustomerFromPool(Map<Integer, Customer> prizeMap, Map<Integer, Customer> waitMap, Customer customer, int k) {
        int entryTime = customer.entryTime; //当前时间
        Customer customerFromPool = getCustomerFromPool(prizeMap, waitMap, customer.id);
        if (customerFromPool == null) {
            if (customer.buyEmpty()) {
                return;
            }
            addCustomerToPool(prizeMap, waitMap, customer, k);
            return;
        }
        customerFromPool.modify(customer.buy);
        if (customerFromPool.buyEmpty()) {
            if (checkAndRemoveCustomerFromPool(waitMap, customerFromPool)) {
                return;
            }
            if (checkAndRemoveCustomerFromPool(prizeMap, customerFromPool)) {
                Customer waitCustomer = getOneCustomerFromWait(waitMap);
                if (waitCustomer == null) {
                    return;
                }
                waitCustomer.entryTime = entryTime;
                waitMap.remove(waitCustomer.id);
                addCustomerToPool(prizeMap, waitCustomer);
            }
            return;
        }
        checkPrizePoolAndWaitPoolAndSwap(prizeMap, waitMap, entryTime);
    }

    private static List<Integer> getAllCustomerFromPool(Map<Integer, Customer> prizeMap) {
        List<Integer> ans = new ArrayList<>();
        Set<Integer> customerIds = prizeMap.keySet();
        ans.addAll(customerIds);
        ans.sort((o1, o2) -> o1 - o2);
        return ans;
    }

    public static Customer getCustomerFromPool(Map<Integer, Customer> prizeMap, Map<Integer, Customer> waitMap, int id) {
        if (prizeMap.containsKey(id)) {
            return prizeMap.get(id);
        }
        if (waitMap.containsKey(id)) {
            return waitMap.get(id);
        }
        return null;
    }

    public static void addCustomerToPool(Map<Integer, Customer> prizeMap, Map<Integer, Customer> waitMap, Customer customer, int k) {
        if (isPrizePoolFull(prizeMap, k)) {
            addCustomerToPool(waitMap, customer);
        } else {
            addCustomerToPool(prizeMap, customer);
        }
    }

    public static void checkPrizePoolAndWaitPoolAndSwap(Map<Integer, Customer> prizeMap, Map<Integer, Customer> waitMap, int entryTime) {
        if (waitMap.isEmpty()) {
            return;
        }
        Customer prizeCustomer = getOneCustomerFromPrizePool(prizeMap);
        Customer waitCustomer = getOneCustomerFromWait(waitMap);
        if (waitCustomer.buy > prizeCustomer.buy) {
            prizeMap.remove(prizeCustomer.id);
            waitMap.remove(waitCustomer.id);
            prizeCustomer.entryTime = entryTime;
            addCustomerToPool(waitMap, prizeCustomer);
            waitCustomer.entryTime = entryTime;
            addCustomerToPool(prizeMap, waitCustomer);
        }
    }

    public static boolean isPrizePoolFull(Map<Integer, Customer> prizeMap, int size) {
        return prizeMap.size() == size;
    }

    public static boolean checkAndRemoveCustomerFromPool(Map<Integer, Customer> map, Customer customer) {
        if (!map.containsKey(customer.id)) {
            return false;
        }
        map.remove(customer.id);
        return true;
    }

    public static void addCustomerToPool(Map<Integer, Customer> waitMap, Customer customer) {
        waitMap.put(customer.id, customer);
    }

    public static Customer getOneCustomerFromPrizePool(Map<Integer, Customer> prizeMap) {
        Customer ans = null;
        for (Map.Entry<Integer, Customer> entry : prizeMap.entrySet()) {
            Customer customer = entry.getValue();
            if (ans == null) {
                ans = customer;
                continue;
            }
            if (customer.buy < ans.buy || customer.buy == ans.buy && customer.entryTime < ans.entryTime) {
                ans = customer;
            }
        }
        return ans;
    }

    public static Customer getOneCustomerFromWait(Map<Integer, Customer> map) {
        Customer ans = null;
        for (Map.Entry<Integer, Customer> entry : map.entrySet()) {
            Customer customer = entry.getValue();
            if (ans == null) {
                ans = customer;
                continue;
            }
            if (ans.buy < customer.buy || ans.buy == customer.buy && ans.entryTime > customer.entryTime) {
                ans = customer;
            }
        }
        return ans;
    }

    private static class Customer {
        int id;
        int buy;
        int entryTime;

        public Customer(int id, int buy, int entryTime) {
            this.id = id;
            this.buy = buy;
            this.entryTime = entryTime;
        }

        public void modify(int buy) {
            this.buy += buy;
        }

        public boolean buyEmpty() {
            return buy < 1;
        }
    }

    //for test
    public static class Data {
        public int[] arr;
        public boolean[] op;

        public Data(int[] arr, boolean[] op) {
            this.arr = arr;
            this.op = op;
        }
    }

    public static Data randomData(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] arr = new int[len];
        boolean[] op = new boolean[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
            op[i] = Math.random() < 0.5 ? true : false;
        }
        return new Data(arr, op);
    }

    public static boolean sameAnswer(List<List<Integer>> ans1, List<List<Integer>> ans2) {
        if (ans1.size() != ans2.size()) {
            return false;
        }
        for (int i = 0; i < ans1.size(); i++) {
            List<Integer> cur1 = ans1.get(i);
            List<Integer> cur2 = ans2.get(i);
            if (cur1.size() != cur2.size()) {
                return false;
            }
            cur1.sort((a, b) -> a - b);
            cur2.sort((a, b) -> a - b);
            for (int j = 0; j < cur1.size(); j++) {
                if (!cur1.get(j).equals(cur2.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int len = 100;
        int value = 10;
        int maxK = 8;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int k = (int) (Math.random() * maxK) + 1;
            Data testData = randomData(len, value);
            int[] arr = testData.arr;
            boolean[] op = testData.op;
            List<List<Integer>> ans1 = topK(arr, op, k);
            List<List<Integer>> ans2 = compare(arr, op, k);
            if (!sameAnswer(ans1, ans2)) {
                for (int j = 0; j < arr.length; j++) {
                    System.out.println(arr[j] + ", " + op[j]);
                }
                System.out.println("k = " + k);
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
