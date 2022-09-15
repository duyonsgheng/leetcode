package duys_code.day_14;

/**
 * @ClassName Code_05_41_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/first-missing-positive/
 * @Date 2021/10/25 13:32
 **/
public class Code_05_41_LeetCode {
    /**
     * 在一个未排序的数组中，找到未出现的最小的正整数
     * 比如：nums = [3,4,-1,1]    是2
     * 比如：nums = [7,8,9,11,12] 是1
     */

    /**
     * 意思就是 我们用一个辅助数组来记录。0位置出现的1，1位置上应该是2，2位置上应该是3.。。。i位置上应该是i+1
     * 我们原始数组总共10个数。假如都有效，那么从未出现的就是 11
     * 原数组可能是正数可能是负数。那么我们就要想到再一次遍历结束就要知道答案。双指针。前指针和后指针
     * 前指针卡住有效区域，后指针卡住无效区域
     * 前指针从0开始，。后指针从数组长度开始
     */
    public static int firstMissingPositive(int[] nums) {
        int l = 0;
        int r = nums.length;
        while (l != r) {
            // i位置上期望是 i+1
            if (nums[l] == l + 1) {
                l++;// 如果当前数有效。有效区域往后扩展
            }
            // 如果我们当前的数已经是小于= 有效区的
            // 如果当前位置的数已经都大于了我们期望的数
            // 如果当前数应该去的位置和当前数是相等的
            // 这三种情况都无效。因为每多一个无效数，我们位置都在减少，那么期望值都在减少
            else if (nums[l] <= l || nums[l] > r || nums[nums[l] - 1] == nums[l]) {
                // 垃圾区向前推进。把垃圾区的前一个数和l位置交换。交换后重复看l位置的数
                swap(nums, l, --r);
            } else {
                // 当前数去它该去的位置。然后l位置重复这个过程
                swap(nums, l, nums[l] - 1);
            }
        }
        return l + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {5, 6, 7, 8, 9};
        System.out.println(firstMissingPositive(arr));
    }
}
