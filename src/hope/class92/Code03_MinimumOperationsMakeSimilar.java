package hope.class92;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code03_MinimumOperationsMakeSimilar
 * @date 2024年09月10日 下午 03:40
 */
// 使数组相似的最少操作次数
// 给你两个正整数数组 nums 和 target ，两个数组长度相等
// 在一次操作中，你可以选择两个 不同 的下标 i 和 j
// 其中 0 <= i, j < nums.length ，并且：
// 令 nums[i] = nums[i] + 2 且
// 令 nums[j] = nums[j] - 2
// 如果两个数组中每个元素出现的频率相等，我们称两个数组是 相似 的
// 请你返回将 nums 变得与 target 相似的最少操作次数
// 测试数据保证nums一定能变得与target相似
// 测试链接 : https://leetcode.cn/problems/minimum-number-of-operations-to-make-arrays-similar/
public class Code03_MinimumOperationsMakeSimilar {
    // 把数组分割成两部分，奇数在左边，偶数在右边
    public static long makeSimilar(int[] nums, int[] target) {
        int n = nums.length;
        int oddSize = spilt(nums, n);
        spilt(target, n);
        Arrays.sort(nums, 0, oddSize);
        Arrays.sort(nums, oddSize, n);
        Arrays.sort(target, 0, oddSize);
        Arrays.sort(target, oddSize, n);
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.abs((long) nums[i] - target[i]);
        }
        return ans / 4;
    }

    public static int spilt(int[] arr, int n) {
        int oddSize = 0;
        for (int i = 0; i < n; i++) {
            if ((arr[i] & 1) == 1) {
                swap(arr, i, oddSize++);
            }
        }
        return oddSize;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
