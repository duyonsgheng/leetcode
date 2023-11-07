package custom.code_2023_09;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2195
 * @date 2023年09月08日
 */
// 2195. 向数组中追加 K 个整数
// https://leetcode.cn/problems/append-k-integers-with-minimal-sum/
public class LeetCode_2195 {
    public static long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);

        long ans = 0, start = 1;
        for (int i = 0; i < nums.length && k > 0; i++) {
            // 存在未出现的数字
            if (start < nums[i]) {
                int cnt = (int) (nums[i] - start) > k ? k : (int) (nums[i] - start);
                // 不存在的数据累计
                ans += (2L * start + cnt - 1) * cnt / 2;
                k -= cnt;
            }
            start = nums[i] + 1;
        }
        // 不存在的数据累计
        if (k > 0) {
            ans += (2L * start + k - 1) * k / 2;
        }
        return ans;
    }

    public static void main(String[] args) {
        // 1 2 3 4 5 6 7
        //System.out.println(minimalKSum(new int[]{5, 4, 25, 10, 25}, 5));
        System.out.println(minimalKSum(new int[]{5, 6}, 6));
    }
}
