package custom.code_2022_10;

/**
 * @ClassName LeetCode_1822
 * @Author Duys
 * @Description
 * @Date 2022/10/27 15:58
 **/
// 1822. 数组元素积的符号
public class LeetCode_1822 {

    public static int arraySign(int[] nums) {
        int flag = 1;
        for (int num : nums) {
            if (num > 0) {
                flag *= 1;
            } else if (num == 0) {
                flag *= 0;
            } else {
                flag *= -1;
            }
        }
        return flag == 0 ? 0 : flag == 1 ? 1 : -1;
    }
}
