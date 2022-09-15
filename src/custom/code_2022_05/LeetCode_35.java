package custom.code_2022_05;

/**
 * @ClassName LeetCode_35
 * @Author Duys
 * @Description
 * @Date 2022/5/5 9:46
 **/
// 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
//请必须使用时间复杂度为 O(log n) 的算法。
//链接：https://leetcode-cn.com/problems/search-insert-position
public class LeetCode_35 {

    // 直接改写快排的方式
    public static int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }
        int n = nums.length;
        int[] arr = netherlandsFlag(nums, 0, n, target);
        if (arr[0] == -1) {
            return -1;
        }
        return arr[0];
    }

    public static int[] netherlandsFlag(int[] arr, int l, int r, int target) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, l};
        }
        int less = l - 1;
        int more = r;
        int cur = l;
        while (cur < more) {
            // 当前数和小于区域最后一个交换，小于区域往后扩，当前数跳下一个
            if (arr[cur] < target) {
                swap(arr, cur++, ++less);
            }
            // 当前数和大于区域前一个交换，大于区域往前扩
            else if (arr[cur] > target) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6};
        int t = 7;
        System.out.println(searchInsert(arr, t));
    }
}
