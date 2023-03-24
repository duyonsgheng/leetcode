package week.week_2023_03_04;

import java.util.HashSet;

/**
 * @ClassName Code_04_MaxLenOfIntegratedSubarray
 * @Author Duys
 * @Description
 * @Date 2023/3/24 9:42
 **/
// 最长可整合子数组
public class Code_04_MaxLenOfIntegratedSubarray {
    // 返回最长的可整合子数组长度
    // 时间复杂度O(N^2)，确实无法更好
    // 一个区间上，如果可整合，就是严格递增
    // 这个区间上如果可整合，那么就是最大值和最小值的差值就是数组的长度
    public static int maxLen(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int ans = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int start = 0; start < arr.length; start++) {
            set.clear();
            int min = arr[start];
            int max = arr[start];
            set.add(arr[start]);
            for (int end = start + 1; end < arr.length; end++) {
                if (set.contains(arr[end])) {
                    break;
                }
                set.add(arr[end]);
                min = Math.min(min, arr[end]);
                max = Math.max(max, arr[end]);
                if (max - min == end - start) {
                    ans = Math.max(ans, end - start + 1);
                }
            }
        }
        return ans;
    }
}
