package custom.code_2022_04;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @ClassName LeetCode_16
 * @Author Duys
 * @Description
 * @Date 2022/4/26 14:52
 **/
// https://leetcode-cn.com/problems/3sum-closest/
// 给你一个长度为 n 的整数数组nums和 一个目标值target。请你从 nums 中选出三个整数，使它们的和与target最接近。
//返回这三个数的和。
//假定每组输入只存在恰好一个解。
//链接：https://leetcode-cn.com/problems/3sum-closest
public class LeetCode_16 {

    public static int threeSumClosest(int[] nums, int target) {
        // 排序
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int cur = nums[i] + nums[l] + nums[r];
                if (cur > target) {
                    r--;
                } else if (cur < target) {
                    l++;
                } else {
                    return cur;
                }

                // 看看能不能把 ans 推得更小
                // tat = 2
                // ans = 3 cur =5
                if (Math.abs(cur - target) < Math.abs(ans - target)) {
                    ans = cur;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {-1, 2, 1, -4};
        System.out.println(threeSumClosest(arr, 1));
    }

}
