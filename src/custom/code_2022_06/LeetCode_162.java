package custom.code_2022_06;

/**
 * @ClassName LeetCode_162
 * @Author Duys
 * @Description
 * @Date 2022/6/30 14:15
 **/
// 162. 寻找峰值
// 峰值元素是指其值严格大于左右相邻值的元素。
public class LeetCode_162 {

    public static int findPeakElement(int[] nums) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        int max = 0;
        // 最大值一定满足
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[max]) {
                max = i;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 3, 5, 6, 4};
        System.out.println(findPeakElement(arr));
    }
}
