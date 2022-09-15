package custom.code_2022_08;

import java.util.Arrays;

/**
 * @ClassName LeetCode_462
 * @Author Duys
 * @Description
 * @Date 2022/8/12 16:18
 **/
// 462. 最少移动次数使数组元素相等 II
// 给你一个长度为 n 的整数数组 nums ，返回使所有数组元素相等需要的最少移动数。
// 在一步操作中，你可以使数组中的一个元素加 1 或者减 1 。
public class LeetCode_462 {
    public static int minMoves2(int[] nums) {
        // 找到一个中位数
        int n = nums.length;
        int p = findKth(nums, 0, n - 1, n / 2);
        int ans = 0;
        for (int num : nums) {
            ans += Math.abs(num - p);
        }
        return ans;
    }

    // 改写快排，找第k小的数
    public static int findKth(int[] arr, int l, int r, int index) {
        if (l == r) {
            return arr[l];
        }
        // 在l到r范围随机找一个
        int tar = arr[l + (int) (Math.random() * (r - l + 1))];
        int[] partition = partition(arr, l, r, tar);
        if (index >= partition[0] && index <= partition[1]) {
            return arr[index];
        } else if (index < partition[0]) {
            return findKth(arr, l, partition[0] - 1, index);
        } else {
            return findKth(arr, partition[1] + 1, r, index);
        }
    }

    public static int[] partition(int[] arr, int l, int r, int tar) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, l};
        }
        // 三个区域
        int less = l - 1;
        int more = r + 1;
        int cur = l;
        while (cur < more) {
            //
            if (arr[cur] < tar) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > tar) {
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
