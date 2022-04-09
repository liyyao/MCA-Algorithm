package major.xor;

/**
 * 1.不用额外变量交换两个数
 *     a = a ^ b;
 *     b = a ^ b;
 *     a = a ^ b;
 */
public class ExchangeTwoData {

    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        System.out.println(a + "--" + b);

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println(a + "--" + b);

        System.out.println("===============");

        // 下面的交换就会出错
        int[] arr = new int[]{1, 2};
        int i = 0;
        int j = 0;
        System.out.println(arr[i] + "----" + arr[j]);
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];

        System.out.println(arr[i] + "----" + arr[j]);
    }
}
