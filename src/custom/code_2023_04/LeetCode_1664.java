package custom.code_2023_04;

/**
 * @ClassName LeetCode_1664
 * @Author Duys
 * @Description
 * @Date 2023/4/26 11:28
 **/
// 1664. 生成平衡数组的方案数
public class LeetCode_1664 {
    // 前缀和后缀相互变换使用
    public int waysToMakeFair(int[] nums) {
        int n = nums.length;
        int odd1 = 0, even1 = 0;
        int odd2 = 0, even2 = 0;
        for (int i = 0; i < n; i++) {
            if ((i & 1) != 0) { // 奇数
                odd1 += nums[i];
            } else {
                even1 += nums[i];
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            // 来当当前位置记录一下后缀和
            if ((i & 1) != 0) {
                odd1 -= nums[i];
            } else {
                even1 -= nums[i];
            }
            // 满足答案
            if (odd1 + even2 == odd2 + even1) {
                ans++;
            }
            // 记录前缀
            if ((i & 1) == 0) {
                even2 += nums[i];
            } else {
                odd2 += nums[i];
            }
        }
        return ans;
    }
}
