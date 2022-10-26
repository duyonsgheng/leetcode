package custom.code_2022_10;

/**
 * @ClassName LeetCode_974
 * @Author Duys
 * @Description
 * @Date 2022/10/26 10:10
 **/
// 974. 和可被 K 整除的子数组
public class LeetCode_974 {
    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        int[] map = new int[k];// 记录mod
        int sum = 0;
        map[0] = 1;
        for (int i = 1; i <= n; i++) {
            sum += nums[i - 1];
            int mod = (sum % k + k) % k;
            ans += map[mod];
            map[mod]++;
        }
        return ans;
    }

}
