package tool;

public class IntUtil {

    /**
     * 生成一个[-range, range]的随机数
     * @param range 范围
     * @return 随机数
     */
    public static int randomNumber(int range) {
        return ((int) ((Math.random() * range) + 1)) - ((int) ((Math.random() * range) + 1));
    }

    public static int randomPositiveNumber(int range) {
        return ((int) (Math.random() * range) + 1);
    }
}
