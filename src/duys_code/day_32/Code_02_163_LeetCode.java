package duys_code.day_32;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Code_02_163_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/missing-ranges/
 * @Date 2021/12/3 15:32
 **/
public class Code_02_163_LeetCode {


    //比如 数组[3,6,9,12]
    // lower 是 0 upper 20
    // 那么就是 0 > 2 4->5 7->9 10 ->11 13->20
    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList<>();
        for (int num : nums) {
            if (num > lower) {
                ans.add(miss(lower, num - 1));
            }
            if (num == upper) {
                return ans;
            }
            lower = num + 1;
        }
        // 每次让区间缩进
        return ans;
    }

    public static String miss(int lower, int upper) {
        String left = String.valueOf(lower);
        String right = "";
        if (upper > lower) {
            right = "->" + String.valueOf(upper);
        }
        return left + right;
    }
}
