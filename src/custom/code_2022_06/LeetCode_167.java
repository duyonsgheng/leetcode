package custom.code_2022_06;

/**
 * @ClassName LeetCode_167
 * @Author Duys
 * @Description
 * @Date 2022/6/30 15:26
 **/
// 167. 两数之和 II - 输入有序数组
// 给你一个下标从 1 开始的整数数组numbers ，该数组已按 非递减顺序排列 ，请你从数组中找出满足相加之和等于目标数target 的两个数。
//如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
//以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
//你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
//你所设计的解决方案必须只使用常量级的额外空间
//链接：https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted
public class LeetCode_167 {

    public static int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            return new int[]{-1, -1};
        }
        // 固定一个
        for (int i = 0; i < numbers.length; i++) {
            // 使用二分，因为是有序的，可以使用二分
            int l = i + 1;
            int r = numbers.length - 1;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (numbers[i] + numbers[mid] == target) {
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[i] + numbers[mid] > target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {

    }
}
