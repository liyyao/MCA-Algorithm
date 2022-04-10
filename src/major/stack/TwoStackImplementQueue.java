package major.stack;

import java.util.Stack;

/**
 * 只用栈实现队列的功能
 * 思路：用两个栈，一个push栈，一个pop栈，push栈管放数据，pop管拿数据
 * 遵循两个条件：1.push栈向pop栈倒数据的时候，pop栈一定要是空的
 *            2.push栈向pop栈倒数据的时候，要一把把push栈数据倒完
 */
public class TwoStackImplementQueue {

    public static class TwoStacksQueue {
        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;

        public TwoStacksQueue() {
            this.stackPush = new Stack<>();
            this.stackPop = new Stack<>();
        }

        /**
         * push栈向pop栈倒入数据
         */
        private void pushToPop() {
            if (stackPop.empty()) {
                while (!stackPush.empty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }

        public void add(int pushInt) {
            stackPush.push(pushInt);
            pushToPop();
        }

        public int poll() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty");
            }
            pushToPop();
            return stackPop.pop();
        }

        public int peek() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty");
            }
            pushToPop();
            return stackPop.peek();
        }
    }
}
