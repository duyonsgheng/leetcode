package custom.code_2023_04;

/**
 * @ClassName LeetCode_2413
 * @Author Duys
 * @Description
 * @Date 2023/4/21 9:32
 **/
// 2413. 最小偶倍数
public class LeetCode_2413 {

    public int smallestEvenMultiple(int n) {
        if (n % 2 == 0) {
            return n;
        }
        return n * 2;
    }
}
