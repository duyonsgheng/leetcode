package week.week_2022_11_04;

import java.util.Arrays;

/**
 * @ClassName Code_04_LeetCode_805
 * @Author Duys
 * @Description
 * @Date 2022/11/24 16:39
 **/
// 805. 数组的均值分割
public class Code_04_LeetCode_805 {
    // 思路：分治
    // 1.根据题意给出的信息，数组大小很小，但是每个值很大，动态规划可能就会超时
    // 2.把数组分成两组，每一组的均值相等，那么就一位置 s1/t1 = s2/t2 = sum/t
    // 3.必然存在 在数组1中选出t1个数 数组2中选出 t2个数，他们的均值也是相等的
    // 分治的思想，就平均把数组分成两组，然后从两个数组中拿结果来进行匹配
    // (s1+s2)/(t1+t2) = sum/t
    public static int n;
    public static int s;
    public static int l;
    public static int r;
    public static int[] lsum = new int[1 << 15];
    public static int[] rsum = new int[1 << 15];

    public static boolean splitArraySameAverage(int[] nums) {
        n = nums.length;
        if (n == 1) {
            return false;
        }
        s = 0;
        for (int num : nums) {
            s += num;
        }
        l = 0;
        r = 0;
        int[] larr = new int[n / 2];
        int[] rarr = new int[n - larr.length];
        for (int i = 0; i < larr.length; i++) {
            larr[i] = nums[i];
        }
        for (int i = larr.length, j = 0; i < nums.length; i++, j++) {
            rarr[j] = nums[i];
        }
        collect(larr, true);
        collect(rarr, false);
        Arrays.sort(rsum, 0, r);
        for (int i = 0; i < l; i++) {
            if (contains(lsum[i])) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(int num) {
        for (int left = 0, right = r - 1, mid = 0; left <= right; ) {
            mid = ((right + left) >> 1);
            if (rsum[mid] == num) {
                return true;
            } else if (rsum[mid] < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    // 左边不能不选，右边不能选完
    public static void collect(int[] arr, boolean isLeft) {
        process(arr, 0, 0, 0, isLeft);
    }

    // 当前来到index位置做抉择
    // 之前算的sum
    // 之前选了num个数
    // 左边不能不选，右边不能全选
    public static void process(int[] arr, int index, int sum, int num, boolean isLeft) {
        if (index == arr.length) {
            if (isLeft && num > 0) {
                lsum[l++] = s * num - n * sum;
            }
            if (!isLeft && num != arr.length) {
                rsum[r++] = -s * num + n * sum;
            }
        } else {
            process(arr, index + 1, sum, num, isLeft);
            process(arr, index + 1, sum + arr[index], num + 1, isLeft);
        }
    }
}
