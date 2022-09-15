package custom.code_2022_05;

/**
 * @ClassName LeetCode_80
 * @Author Duys
 * @Description
 * @Date 2022/5/11 16:37
 **/
// 80. 删除有序数组中的重复项 II
// 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
//不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
//链接：https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii
public class LeetCode_80 {
    // 输入：nums = [1,1,1,2,2,3]
    // 输出：5, nums = [1,1,2,2,3]
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length < 3) {
            return nums == null ? 0 : nums.length;
        }
        // 1 1 1 2 2 2 3 3 3
        // 双指针
        int n = nums.length;
        int left = 0;
        int right = 0;
        int ans = 0;
        for (int i = 1; i < n; i++)
            if (nums[i] == nums[left]) {
                right = i;
            } else {
                // 需要修正这一短的数据
                if (right - left + 1 > 2) {
                    int tmp = right - left + 1 - 2;
                    while (tmp > 0) {
                        nums[left++] = Integer.MAX_VALUE;
                        tmp--;
                        ans++;
                    }
                }
                left = i;
            }
        // 最后校验
        if (right - left + 1 > 2 && nums[right] == nums[left]) {
            int tmp = right - left + 1 - 2;
            while (tmp > 0) {
                nums[left++] = Integer.MAX_VALUE;
                tmp--;
                ans++;
            }
        }
        for (int i = 0; i < n; i++) {
            int cur = i;
            while (cur > 0 && nums[cur] <= nums[cur - 1]) {
                swap(nums, cur, cur - 1);
                cur--;
            }
        }
        return n - ans;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        //   1, 1, 1, 2, 2, 2, 2, 3, 3, 4, 4, 4
        // left       r
        int[] arr = {1, 1, 1, 2, 2, 2, 2, 4, 4, 4, 5};
        System.out.println(removeDuplicates(arr));
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
