package major.recursion;

import java.util.Stack;

/**
 * 6、给你一个栈，请你逆序这个栈，不能申请额外的数据结构，只能使用递归函数，如果实现？
 */
public class T04_ReverseStackUsingRecursive {

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = lastVal(stack);
        reverse(stack);
        stack.push(i);
    }

    private static int lastVal(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        }
        int last = lastVal(stack);
        stack.push(result);
        return last;
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }
    }
}
