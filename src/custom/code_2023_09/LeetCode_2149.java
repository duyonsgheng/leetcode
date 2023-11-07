package custom.code_2023_09;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2149
 * @date 2023年09月01日
 */
// 2149. 按符号重排数组
// https://leetcode.cn/problems/rearrange-array-elements-by-sign/?envType=daily-question&envId=2023-09-01
public class LeetCode_2149 {

    public static int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        int[] arr1 = new int[n / 2];
        int[] arr2 = new int[n / 2];
        int i = 0;
        int i1 = 0;
        for (int num : nums) {
            if (num < 0) {
                arr1[i++] = num;
            } else {
                arr2[i1++] = num;
            }
        }
        int[] ans = new int[n];
        int k = 0;
        i = 0;
        i1 = 0;
        while (k < n) {
            ans[k++] = arr2[i++];
            ans[k++] = arr1[i1++];
        }
        return ans;
    }

    public static int[] rearrangeArray1(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int a = 0;
        int b = 0;
        boolean pre = true;
        for (int i = 0; i < n; i++) {
            if (pre) { // 之前是负数
                while (nums[a] < 0) {
                    a++;
                }
                ans[i] = nums[a++];
            } else { // 之前是正数
                while (nums[b] > 0) {
                    b++;
                }
                ans[i] = nums[b++];
            }
            pre = !pre;
        }
        return ans;
    }

    public static void main(String[] args) {
        rearrangeArray1(new int[]{3, 1, -2, -5, 2, -4});
    }
}
