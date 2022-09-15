package duys_code.day_34;

/**
 * @ClassName Code_12_384_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/shuffle-an-array/
 * @Date 2021/12/7 17:29
 **/
public class Code_12_384_LeetCode {

    // 打乱数组
    // 0~n-1 随机弄一个出来 放到n-1位置
    // 0~n-2 随机弄一个出来 放到n-2位置
    // ..........
    class Solution {
        private int[] arr;
        private int n;
        private int[] bat;

        public Solution(int[] nums) {
            arr = nums;
            n = nums.length;
            bat = new int[n];
            for (int i = 0; i < n; i++) {
                bat[i] = nums[i];
            }
        }

        public int[] reset() {
            return bat;
        }

        // 0到n-1上随意搞一个放到n-1
        public int[] shuffle() {
            for (int i = n - 1; i >= 0; i--) {
                int r = (int) (Math.random() * (i + 1));
                int tmp = arr[i];
                arr[i] = arr[r];
                arr[r] = tmp;
            }
            return arr;
        }
    }
}
