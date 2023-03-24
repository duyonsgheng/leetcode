package custom.code_2023_02;

/**
 * @ClassName LeetCode_1558
 * @Author Duys
 * @Description
 * @Date 2023/2/13 14:16
 **/
// 1558. 得到目标数组的最少函数调用次数
public class LeetCode_1558 {
    public int minOperations(int[] nums) {
        // 奇数，执行第一个操作，变成偶数
        // 偶数，执行第二个操作。
        // 那么奇数要变成0，只需要确定这个奇数二进制有多少个1，就确定了
        int ans = 0, max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
            while (num != 0) {
                if ((num & 1) == 1) {
                    ans++;
                }
                num >>= 1;
            }
        }
        // 偶数是整体操作，只需要记录一下最大的那个就可以确定了
        if (max != 0) {
            while (max != 0) {
                ans++;
                max >>= 1;
            }
        }
        return ans;
    }
}
