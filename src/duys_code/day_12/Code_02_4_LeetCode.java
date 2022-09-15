package duys_code.day_12;

/**
 * @ClassName Code_02_4_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 * @Date 2021/10/19 13:12
 **/
public class Code_02_4_LeetCode {
    /**
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数
     * 例如：
     * 输入：nums1 = [1,3], nums2 = [2]
     * 输出：2.00000
     * 解释：合并数组 = [1,2,3] ，中位数 2
     * <p>
     * 输入：nums1 = [1,2], nums2 = [3,4]
     * 输出：2.50000
     * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        boolean even = (size & 1) == 0;
        if (nums1.length != 0 && nums2.length != 0) {
            if (even) {
                return (double) (findKthNum(nums1, nums2, size / 2) + findKthNum(nums1, nums2, size / 2 + 1)) / 2D;
            } else {
                return findKthNum(nums1, nums2, size / 2 + 1);
            }
        } else if (nums1.length != 0) {
            if (even) {
                return (double) (nums1[(size - 1) / 2] + nums1[size / 2]) / 2;
            } else {
                return nums1[size / 2];
            }
        } else if (nums2.length != 0) {
            if (even) {
                return (double) (nums2[(size - 1) / 2] + nums2[size / 2]) / 2;
            } else {
                return nums2[size / 2];
            }
        } else {
            return 0;
        }
    }

    // 问题2：两个不等长的数组，返回第K小的问题
    public static int findKthNum(int[] arr1, int[] arr2, int kth) {
        // 分3中情况
        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
        int l = longs.length;
        int s = shorts.length;
        // 下面的 kth l s都是长度，换算成下标需要减去1
        // 情况1：0 < k <= s
        // 情况2：s < k <= l
        // 情况3：l < k <= l+s
        // 情况1：在长数组和短数组中各自拿出前k个数来进行问题1中的解答
        if (kth <= s) {
            return getUpMedian(shorts, 0, kth - 1, longs, 0, kth - 1);
        }
        // 情况3：
        /**
         *  比如当前 s = 7 ，l=10  k=12
         * arr1:  1 2 3 4 5 6 7 8 9 10 11 12
         * arr2:  a b c d e f g
         * 那么 长数组中 k-7之前的 不可能 长数组中第 k-s - 1位置的数单独校验
         * 那么 短数组中 k-l之前的 不可能 短数组中第 k-l - 1位置的数单独校验
         */
        if (kth > l) {
            if (longs[kth - s - 1] >= shorts[s - 1]) {
                return longs[kth - s - 1];
            }
            if (shorts[kth - l - 1] >= longs[l - 1]) {
                return shorts[kth - l - 1];
            }
            return getUpMedian(longs, kth - s, l - 1, shorts, kth - l, s - 1);
        }
        // 情况 2：
        /**
         *  比如当前 s = 7 ，l=10  k= 9
         * arr1:  1 2 3 4 5 6 7 8 9 10 11 12
         * arr2:  a b c d e f g
         * 那么 长数组中 k之后的 不可能 长数组中第 k-s-1 位置的数单独校验
         * 那么 短数组中 都有可能
         */
        // 校验长度
        if (longs[kth - s - 1] > shorts[s - 1]) {
            return longs[kth - s - 1];
        }
        return getUpMedian(shorts, 0, s - 1, longs, kth - s, kth - 1);
    }

    // 问题1：两个等长数组，长度为N，在两个数组中返回第N小的数
    // 给定两个长度为N的数组，求合并数组后第N小的数
    // A[s1....e1]
    // B[s2....e2]
    // 一定等长
    public static int getUpMedian(int[] A, int s1, int e1, int[] B, int s2, int e2) {
        // 1.分别找出A和B数组的中位数
        int mid1 = 0;
        int mid2 = 0;
        while (s1 < e1) {
            // 两个数组的中位数
            mid1 = s1 + ((e1 - s1) >> 1);
            mid2 = s2 + ((e2 - s2) >> 1);
            // 三种情况、
            // 1. 如果两个中位数是相等的，直接返回
            if (A[mid1] == B[mid2]) {
                return A[mid1];
            }
            // 两个中位数不等
            // 数组长度是奇数 , 二进制第0位置是1，一定是奇数
            if (((e1 - s1 + 1) & 1) == 1) {
                // 判断两个中位数哪一个大，小的那一方比当前数小的不可能了，大的哪一方，比当前数大的都不可能了
                // 小的哪一方多了一个当前数，单独判断
                if (A[mid1] > B[mid2]) { // A中的中位数大于B中的中位数。排除哪些位置不可能成为
                    if (B[mid2] >= A[mid1 - 1]) {// 如果B中位数是大于A中位数前一个数，那么B中位数就是第N小
                        return B[mid2];
                    }
                    // 否则剔除掉不可能的位置，继续跑
                    e1 = mid1 - 1;
                    s2 = mid2 + 1;
                }
                // A[mid1] < B[mid2]
                else {
                    // 手动判断小的哪一方的当前中位数是不是满足在第N小的位置
                    if (A[mid1] >= B[mid2 - 1]) {
                        return A[mid1];
                    }
                    s1 = mid1 + 1;
                    e2 = mid2 - 1;
                }
            }
            // 偶数长度
            else {
                // 依然排除不可能的位置
                // arr1 [1 2 3 4]
                // arr2 [a b c d]
                // 只代表位置。2位置数>b位置数，那么在arr1中 3 4位置不能成为第四小了，在arr2中a b位置不能成为第4小的数了
                if (A[mid1] > B[mid2]) {
                    e1 = mid1;
                    s2 = mid2 + 1;
                }
                // 只代表位置。2位置数 < b位置数，那么在arr1中 1 2位置不能成为第四小了，在arr2中c d位置不能成为第4小的数了
                else {
                    s1 = mid1 + 1;
                    e2 = mid2;
                }
            }
        }
        // 上面跑完了，可能还剩下最后一个数，就是我们的s1=e1。那么单独比较最后两个数组剩下的数
        return Math.min(A[s1], B[s2]);
    }

}
