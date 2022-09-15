package custom.code_2022_05;

/**
 * @ClassName LeetCode_81
 * @Author Duys
 * @Description
 * @Date 2022/5/16 13:03
 **/
// 81. 搜索旋转排序数组 II
// 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
//在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums[k+1], ...,
//nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
//给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
//你必须尽可能减少整个操作步骤。
//链接：https://leetcode.cn/problems/search-in-rotated-sorted-array-ii
public class LeetCode_81 {

    public static boolean search(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return false;
        }
        int index = -1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                index = i; // 出现第一次降序的位置
                break;
            }
        }
        if (index == -1) {
            int l = 0;
            int r = nums.length - 1;
            int mid = 0;
            while (l <= r) {
                mid = (l + r) / 2;
                if (nums[mid] == target) {
                    return true;
                } else if (nums[mid] > target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        } else {
            if (nums[index] == target) {
                return true;
            }
            // 0 到 index-1 递增
            // index 到 len -1 递增
            // 二分
            int l = 0;
            int r = index - 1;
            int mid = 0;
            while (l <= r) {
                mid = (l + r) / 2;
                if (nums[mid] == target) {
                    return true;
                } else if (nums[mid] > target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            l = index;
            r = nums.length - 1;
            while (l <= r) {
                mid = (l + r) / 2;
                if (nums[mid] == target) {
                    return true;
                } else if (nums[mid] > target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {2, 2, 3, 2, 2};
        System.out.println(search(arr, 3));
    }
}
