package major.stack;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
 * 要求：1、pop push getMin操作的时间复杂度都是O(1)
 *      2、设计的栈类型可以使用现成的栈结构
 */
public class MinDataStack {

    public static class MyStack1 {
        private Stack<Integer> dataStack;
        private Stack<Integer> minDataStack;

        public MyStack1() {
            this.dataStack = new Stack<>();
            this.minDataStack = new Stack<>();
        }

        public void push(int num) {
            this.dataStack.push(num);
            if (minDataStack.isEmpty()) {
                minDataStack.push(num);
            } else {
                int min = this.getMin();
                this.minDataStack.push(Math.min(num, min));
            }
        }

        public int pop() {
            if (this.dataStack.isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }
            this.minDataStack.pop();
            return this.dataStack.pop();
        }

        public int getMin() {
            if (this.minDataStack.isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }
            return this.minDataStack.peek();
        }
    }

    public static class MyStack2 {
        private Stack<Integer> dataStack;
        private Stack<Integer> minDataStack;

        public MyStack2() {
            this.dataStack = new Stack<>();
            this.minDataStack = new Stack<>();
        }

        public void push(int num) {
            this.dataStack.push(num);
            if (minDataStack.isEmpty()) {
                minDataStack.push(num);
            } else if (num <= this.getMin()) {
                this.minDataStack.push(num);
            }
        }

        public int pop() {
            if (this.dataStack.isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }
            int value = this.dataStack.pop();
            if (value == this.getMin()) {
                this.minDataStack.pop();
            }
            return value;
        }

        public int getMin() {
            if (this.minDataStack.isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }
            return this.minDataStack.peek();
        }
    }

    public static void main(String[] args) {
        MyStack1 stack1 = new MyStack1();
        stack1.push(3);
        System.out.println(stack1.getMin());
        stack1.push(4);
        System.out.println(stack1.getMin());
        stack1.push(1);
        System.out.println(stack1.getMin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getMin());

        System.out.println("=============");

        MyStack1 stack2 = new MyStack1();
        stack2.push(3);
        System.out.println(stack2.getMin());
        stack2.push(4);
        System.out.println(stack2.getMin());
        stack2.push(1);
        System.out.println(stack2.getMin());
        System.out.println(stack2.pop());
        System.out.println(stack2.getMin());
    }
}
