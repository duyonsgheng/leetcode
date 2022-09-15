package duys_code.day_32;

/**
 * @ClassName Code_06_189_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/rotate-array/
 * @Date 2021/12/6 10:30
 **/
public class Code_06_189_LeetCode {

    // 解法1：数组经过三次逆序
    // 0 到 N-k-1
    // N-k 到 N-1
    // 0 到 N-1
    public void rotate(int[] nums, int k) {
        int N = nums.length;
        // k可能会大于N
        k = k % N;
        reverse(nums, 0, N - k - 1);
        reverse(nums, N - k, N - 1);
        reverse(nums, 0, N - 1);
    }

    public static void reverse(int[] arr, int l, int r) {
        while (l < r) {
            int tmp = arr[l];
            arr[l++] = arr[r];
            arr[r--] = tmp;
        }
    }
}
