package custom.code_2022_04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName LeetCode_18
 * @Author Duys
 * @Description
 * @Date 2022/4/26 15:27
 **/
// https://leetcode-cn.com/problems/4sum/
// 给你一个由 n 个整数组成的数组nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组[nums[a], nums[b], nums[c], nums[d]]
//（若两个四元组元素一一对应，则认为两个四元组重复）：
//0 <= a, b, c, d< n
//a、b、c 和 d 互不相同
//nums[a] + nums[b] + nums[c] + nums[d] == target
//你可以按 任意顺序 返回答案 。
//链接：https://leetcode-cn.com/problems/4sum
public class LeetCode_18 {


    public static List<List<Integer>> fourSum(int[] nums, int target) {

        Arrays.sort(nums);
        int n = nums.length;
        Set<String> set = new HashSet<>();
        // 卡住开头
        // 卡住结尾
        List<List<Integer>> ans = new ArrayList<>();
        if (n < 4) {
            return ans;
        }
        if (n == 4) {
            if (nums[0] + nums[1] + nums[2] + nums[3] == target) {
                ans.add(Arrays.asList(nums[0], nums[1], nums[2], nums[3]));
            }
            return ans;
        }
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                int left = i + 1;
                int right = j - 1;
                while (left < right) {
                    int cur = nums[i] + nums[j] + nums[left] + nums[right];
                    if (cur == target) {
                        List<Integer> list = Arrays.asList(nums[i], nums[left], nums[right], nums[j]);
                        String key = list.get(0) + "_" + list.get(1) + "_" + list.get(2) + "_" + list.get(3);
                        if (!set.contains(key)) {
                            ans.add(list);
                            set.add(key);
                        }
                        left++;
                        right--;
                    } else if (cur > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = {-3,-1,0,2,4,5};
        List<List<Integer>> lists = fourSum(a, 0);
        for (List<Integer> arr : lists) {
            for (Integer i : arr) {
                System.out.print(" " + i);
            }
            System.out.println();
        }
    }
}
