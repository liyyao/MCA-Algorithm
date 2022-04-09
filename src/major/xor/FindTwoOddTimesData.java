package major.xor;

/**
 * 4.一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
 * 思路：1.假设a和b两个数出现了奇数次，数组中所有的数异或运算，得到的是xor = a^b
 *      2.再找到xor最右侧的1，xor' = xor & (-xor); 记最右侧位置为index
 *      3.xor != 0，说明a和b最右侧的位置index的数字一定不相同，即一个是0一个是1
 *      4.然后再将数组中所有数进行判断，如果index的数字是1的进行异或，不是1的不异或，这样就能得到一个数a(b)
 *      5.然后再用得到的a(b)与xor异或就得到另一个数了
 */
public class FindTwoOddTimesData {

    public static void main(String[] args) {
        int[] arr = new int[] {1,1,1,2,2,2,2,3};
        findTwoOddTimesData(arr);
    }

    public static void findTwoOddTimesData(int[] arr) {
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
        }
        // a和b是两种数
        // xor != 0
        //xor最右侧的1提取出来
        int rightOne = xor & (-xor);
        //找其中的一个数
        int theOne = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((rightOne & arr[i]) != 0) {
                theOne ^= arr[i];
            }
        }
        int otherOne = xor ^ theOne;
        System.out.println(theOne + "---" + otherOne);
    }
}
