package custom.code_2022_08;

/**
 * @ClassName LeetCode_553
 * @Author Duys
 * @Description
 * @Date 2022/8/23 16:52
 **/
// 553. 最优除法
public class LeetCode_553 {

    // 我们可以直到除法的性质
    // 除数越大，被除数越小，结果就越大
    public String optimalDivision(int[] nums) {
        if (nums == null) {
            return "";
        }
        int n = nums.length;
        if (n == 1) {
            return nums[0] + "";
        }
        if (n == 2) {
            return nums[0] + "/" + nums[1];
        }
        StringBuilder builder = new StringBuilder();
        builder.append(nums[0] + "/(");
        builder.append(nums[1]);
        for (int i = 2; i < n; i++) {
            builder.append("/");
            builder.append(nums[i]);
        }
        builder.append(")");
        return builder.toString();
    }
}
