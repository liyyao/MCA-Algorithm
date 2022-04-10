package major.queue;

import major.stack.TwoStackImplementQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 两个队列实现栈
 */
public class TwoQueueImplementStack {

    public static class TwoQueueStack<T> {
        private Queue<T> queuePush;
        private Queue<T> queuePop;

        public TwoQueueStack() {
            this.queuePush = new LinkedList<>();
            this.queuePop = new LinkedList<>();
        }

        public void push(T num) {
            this.queuePush.offer(num);
        }

        public T pop() {
            if (queuePush.isEmpty()) {
                throw new RuntimeException("queue is empty");
            }
            pushToPop();
            return queuePop.poll();
        }

        public T peek() {
            T pop = pop();
            queuePush.add(pop);
            return pop;
        }

        private void pushToPop() {
            if (queuePush.isEmpty()) {
                return;
            }
            while (queuePush.size() > 1) {
                queuePop.offer(queuePush.poll());
            }
            Queue<T> temp = queuePush;
            queuePush = queuePop;
            queuePop = temp;
        }

        public boolean isEmpty() {
            return queuePush.isEmpty();
        }
    }

    public static void main(String[] args) {

        int testTime = 10000000;
        int max = 1000000;
        System.out.println("测试开始");
        TwoQueueStack<Integer> myStack = new TwoQueueStack<>();
        Stack<Integer> test = new Stack<>();
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!test.isEmpty()) {
                    System.out.println("Oops!");
                }
                int num = (int) (Math.random() * max);
                myStack.push(num);
                test.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (Math.random() * max);
                    myStack.push(num);
                    test.push(num);
                } else if (Math.random() < 0.5) {
                    if (!myStack.peek().equals(test.peek())) {
                        System.out.println("Oops!");
                    }
                } else if (Math.random() < 0.75) {
                    if (!myStack.pop().equals(test.pop())) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (myStack.isEmpty() != test.isEmpty()) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("测试结束");
    }
}
