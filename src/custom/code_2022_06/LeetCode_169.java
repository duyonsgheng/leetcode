package custom.code_2022_06;

/**
 * @ClassName LeetCode_169
 * @Author Duys
 * @Description
 * @Date 2022/6/9 16:57
 **/
// 给定一个大小为 n 的数组nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于⌊ n/2 ⌋的元素。
//你可以假设数组是非空的，并且给定的数组总是存在多数元素。
//链接：https://leetcode.cn/problems/majority-element
public class LeetCode_169 {

    // 1.二分
    // 2.map
    public static int majorityElement(int[] nums) {
        return process(nums, 0, nums.length - 1);
    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        int mid = ((r - l) >> 1) + l;
        int left = process(arr, l, mid);
        int right = process(arr, mid + 1, r);
        if (left == right) {
            return left;
        }
        int lc = count(arr, left, l, r);
        int rc = count(arr, right, l, r);
        return lc > rc ? left : right;
    }

    public static int count(int[] arr, int num, int l, int r) {
        int count = 0;
        for (int i = l; i <= r; i++) {
            if (arr[i] == num) {
                count++;
            }
        }
        return count;
    }
}
