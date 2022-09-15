package custom.code_2022_08;

/**
 * @ClassName LeetCode_486
 * @Author Duys
 * @Description
 * @Date 2022/8/17 11:12
 **/
// 486. 预测赢家
public class LeetCode_486 {

    public static boolean PredictTheWinner(int[] nums) {
        return process(nums, 0, 0, 0, 0, nums.length - 1);
    }

    public static boolean process(int[] nums, int p1, int p2, int i, int l, int r) {
        // 最后一个数字了
        if (l == r) {
            // 先手来拿
            if (i == 0) {
                return p1 + nums[l] >= p2;
            } else {
                // 后手来拿
                return p1 < p2 + nums[l];
            }
        }
        // 还有多余的数字
        else {
            // 该先手拿
            if (i == 0) {
                // 厚手输了，就是先手赢了
                return !process(nums, p1 + nums[l], p2, 1, l + 1, r) || !process(nums, p1 + nums[r], p2, 1, l, r - 1);
            } else {
                return !process(nums, p1, p2 + nums[l], 0, l + 1, r) || !process(nums, p1, p2 + nums[r], 0, l, r - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 1};
        System.out.println(PredictTheWinner(arr));
    }
}
