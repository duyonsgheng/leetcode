package duys_code.day_01;

/**
 * @ClassName TowChar_Code_04
 * @Author Duys
 * @Description TODO
 * @Date 2021/9/13 17:51
 **/
public class Code_04_TowChar {
    /**
     * 一个数组中只有两种字符'G'和’B’，
     * 可以让所有的G都放在左侧，所有的B都放在右侧
     * 或者可以让所有的G都放在右侧，所有的B都放在左侧
     * 但是只能在相邻字符之间进行交换操作，
     * 返回至少需要交换几次
     */
    // 从左往右，遍历遇到第一个G，放在0位置，第二个G放在1位置，、、。。。
    // 或者把一个B放在第0位置，第二个B放在第1位置
    public static int change1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] arr = s.toCharArray();
        int stepG = 0;
        int gIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'G') {
                // i 到 上一个G的位置之间的距离
                stepG += i - (gIndex++);
            }
        }
        int stepB = 0;
        int bIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'G') {
                // i 到 上一个G的位置之间的距离
                stepB += i - (bIndex++);
            }
        }
        return Math.min(stepB, stepG);
    }

    // 还有一次循环就可以搞定
    public static int change2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] arr = s.toCharArray();
        int stepG = 0;
        int stepB = 0;
        int GIndex = 0;
        int BIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'G') {
                stepG += i - (GIndex++);
            } else {
                stepB += i - (BIndex);
            }
        }
        return Math.min(stepB, stepG);
    }


}
