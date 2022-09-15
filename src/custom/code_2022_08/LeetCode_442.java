package custom.code_2022_08;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_442
 * @Author Duys
 * @Description
 * @Date 2022/8/11 14:32
 **/
// 442. 数组中重复的数据
public class LeetCode_442 {
    // 试试下标循环
    // 当数该去i+1的位置
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            // 当前位置处理过了，并且当前位置是该位置的值，满足的
            if (cur < 0 || cur - 1 == i) {
                continue;
            }
            // 当前值要去的位置和当前值相同，重复了
            if (nums[cur - 1] == cur) {
                ans.add(cur);
                // 处理过了，并且对应位置是对应的值
                nums[i] *= -1;
            } else {
                int tar = nums[cur - 1];
                // 当前数换到它该去的位置
                nums[cur - 1] = cur;
                // 然后从交换后的前一个位置继续看
                nums[i--] = tar;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> duplicates = findDuplicates(arr);
        System.out.println();
    }
}
