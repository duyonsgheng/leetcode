package duys_code.day_30;

/**
 * @ClassName Code_02_88_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/merge-sorted-array/
 * @Date 2021/11/26 13:30
 **/
public class Code_02_88_LeetCode {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len = nums1.length - 1;
        while (m > 0 && n > 0) {
            // nums1要大一些，大于等于情况都拷贝nums1的，释放更多空间
            if (nums1[m - 1] >= nums2[n - 1]) {
                nums1[len--] = nums1[--m];
            } else {
                nums1[len--] = nums2[--n];
            }
        }
        while (m > 0) {
            nums1[len--] = nums1[--m];
        }
        while (n > 0) {
            nums1[len--] = nums2[--n];
        }
    }
}
