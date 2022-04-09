package major.xor;

import tool.BitUtil;

/**
 * 把一个int类型的数，提取出最右侧的1来
 */
public class PickDataRight1 {

    public static void main(String[] args) {

        int ans = pickDataRight1(8);
        BitUtil.printDataBitWise(ans);
    }

    public static int pickDataRight1(int data) {
        return data & ((~data) + 1);
    }
}
