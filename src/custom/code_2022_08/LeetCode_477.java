package custom.code_2022_08;

/**
 * @ClassName LeetCode_477
 * @Author Duys
 * @Description
 * @Date 2022/8/15 11:21
 **/
// 477. 汉明距离总和
public class LeetCode_477 {

    public int totalHammingDistance(int[] nums) {
        int ans = 0;
        for (int i = 31; i >= 0; i--) {
            // 记录当前i位置有多少为1，多少为0的数，那么当前位置算距离，就是他们的乘积
            int res0 = 0;
            int res1 = 0;
            for (int num : nums) {
                if ((num & (1 << i)) == 1) {
                    res1++;
                } else {
                    res0++;
                }
            }
            ans += res0 * res1;
        }
        return ans;
    }
}
