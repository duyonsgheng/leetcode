package custom.code_2023_02;

/**
 * @ClassName LeetCode_1551
 * @Author Duys
 * @Description
 * @Date 2023/2/13 9:57
 **/
// 1551. 使数组中所有元素相等的最小操作数
public class LeetCode_1551 {

    public int minOperations(int n) {
        int ans = 0, avg = n;
        for (int i = 0; i < n; i++) {
            // 对于大于n的数，需要操作他们的差值 次，每一次对应一个数也要跟着操作
            if ((2 * i) + 1 > n) {
                ans += (2 * i) + 1 - n;
            }
        }
        return ans;
    }
}
