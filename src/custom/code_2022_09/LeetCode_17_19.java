package custom.code_2022_09;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName LeetCode_17_19
 * @Author Duys
 * @Description
 * @Date 2022/9/26 9:35
 **/
// https://leetcode.cn/problems/missing-two-lcci/
public class LeetCode_17_19 {
    public static void main(String[] args) {
        int[] arr = {2, 3};
        int[] ans = missingTwo(arr);
        System.out.println();
    }

    public static int[] missingTwo(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        int n = nums.length + 2;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (set.contains(i)) {
                continue;
            }
            list.add(i);
        }
        int[] ans = new int[list.size()];
        int index = 0;
        for (Integer i : list)
            ans[index++] = i;
        return ans;
    }

    public static int[] missingTwo1(int[] nums) {
        int n = nums.length + 2;
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        // 两个缺失得元素之和
        int diffSum = (1 + n) * n / 2 - sum;
        int p = diffSum / 2;
        sum = 0;
        for (int i : nums) {
            if (i <= p) {
                sum += i;
            }
        }
        int res = (1 + p) * p / 2 - sum;
        return new int[]{res, diffSum - res};
    }
}
