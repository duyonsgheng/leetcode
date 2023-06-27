package custom.code_2023_05;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1818
 * @Author Duys
 * @Description
 * @Date 2023/5/23 10:36
 **/
// 1818. 绝对差值和
public class LeetCode_1818 {
    //
    int mod = 1_000_000_007;

    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] rec = new int[n];
        System.arraycopy(nums1, 0, rec, 0, n);
        Arrays.sort(rec);
        long sum = 0, maxn = 0;
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            sum += diff;
            int j = binarySearch(rec, nums2[i]);
            if (j < n) {
                maxn = Math.max(maxn, diff - (rec[j] - nums2[i]));
            }
            if (j > 0) {
                maxn = Math.max(maxn, diff - (nums2[i] - rec[j - 1]));
            }
        }
        return (int) (sum - maxn) % mod;
    }

    public int binarySearch(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        if (arr[r] < target) {
            return r + 1;
        }
        while (l < r) {
            int mid = (r - l) / 2 + l;
            if (arr[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
