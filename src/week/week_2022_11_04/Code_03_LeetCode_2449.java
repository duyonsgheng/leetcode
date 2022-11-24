package week.week_2022_11_04;

import java.util.Arrays;

/**
 * @ClassName Code_03_LeetCode_2449
 * @Author Duys
 * @Description
 * @Date 2022/11/24 16:31
 **/
// 2449. 使数组相似的最少操作次数
public class Code_03_LeetCode_2449 {
    // 思路：
    // 1. 根据题目给出，一定可以变出一样的，那么对于一个数来说 +2 -2 是不影响数的奇偶性的。切入点
    // 2. 把奇数的元素和偶数的元素分离开
    // 3. 然后排序，然后分别算差值，最后统一来除以每一次需要变化的点数，4点
    public long makeSimilar(int[] nums, int[] target) {
        int n = nums.length;
        int oddSize = split(nums);
        split(target);
        Arrays.sort(nums, 0, oddSize);
        Arrays.sort(nums, oddSize, n);
        Arrays.sort(target, 0, oddSize);
        Arrays.sort(target, oddSize, n);
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.abs(nums[i] - target[i]);
        }
        return ans >> 2;
    }

    public static int split(int[] arr) {
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            // 奇数
            if ((arr[i] & 1) != 0) {
                swap(arr, i, index++);
            }
        }
        return index;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
