package custom.code_2022_12;

/**
 * @ClassName LeetCode_1730
 * @Author Duys
 * @Description
 * @Date 2022/12/9 8:47
 **/
// 1780. 判断一个数字是否可以表示成三的幂的和
public class LeetCode_1780 {

    public boolean checkPowersOfThree(int n) {
        while (n != 0) {
            if (n % 3 == 2) {
                return false;
            }
            n /= 3;
        }
        return true;
    }
}
