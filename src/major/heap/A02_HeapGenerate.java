package major.heap;

import java.util.Comparator;

public class A02_HeapGenerate {

    public static class MyMaxHeap {
        private int[] heap;
        private final int limit;
        private int heapSize;

        public MyMaxHeap(int limit) {
            this.limit = limit;
            this.heap = new int[limit];
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public int size() {
            return heapSize;
        }

        public void push(int value) {
            if (isFull()) {
                throw new RuntimeException("heap is full");
            }
            heap[heapSize] = value;
            heapInsert(heapSize++);
        }

        //用户此时，让你返回最大值，并且在大根堆中，把最大值删掉
        //剩下的数，依然保持大根堆组织
        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("heap is empty");
            }
            int ans = heap[0];
            swap(0, --heapSize);
            heapify(0);
            return ans;
        }

        //新加进来的数，现在停在了index位置，请依次往上移动
        //移动到0位置，或者干不掉自己的双亲了，停
        private void heapInsert(int index) {
            while (heap[index] > heap[(index - 1) / 2]) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        //从index位置，往下看，不断的下沉
        //较大的孩子都不再比index位置的数大了，停
        private void heapify(int index) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int largest = left + 1 < heapSize && heap[left + 1] > heap[left] ? left + 1 : left;
                largest = heap[largest] > heap[index] ? largest : index;
                if (largest == index) {
                    break;
                }
                swap(largest, index);
                index = largest;
                left = index * 2 + 1;
            }
        }

        private void swap(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }
    }

    public static class RightMaxHeap {
        private int[] arr;
        private final int limit;
        private int size;

        public RightMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (isFull()) {
                throw new RuntimeException("heap is full");
            }
            arr[size++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = arr[maxIndex];
            arr[maxIndex] = arr[--size];
            return ans;
        }
    }

    public static class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    public static void main(String[] args) {
        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyMaxHeap myMaxHeap = new MyMaxHeap(curLimit);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (myMaxHeap.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (myMaxHeap.isFull() != test.isFull()) {
                    System.out.println("Oops!");
                }
                if (myMaxHeap.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    myMaxHeap.push(curValue);
                    test.push(curValue);
                } else if (myMaxHeap.isFull()) {
                    if (myMaxHeap.pop() != test.pop()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        myMaxHeap.push(curValue);
                        test.push(curValue);
                    } else {
                        if (myMaxHeap.pop() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("测试结束");
    }
}
