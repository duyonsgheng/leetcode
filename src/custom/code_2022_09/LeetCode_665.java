package custom.code_2022_09;

import java.util.Stack;

/**
 * @ClassName LeetCode_665
 * @Author Duys
 * @Description
 * @Date 2022/9/6 9:51
 **/
// 665. 非递减数列
public class LeetCode_665 {
    public static void main(String[] args) {
        int[] arr = {3, 4, 2, 3};
        System.out.println(checkPossibility(arr));
    }

    public static boolean checkPossibility(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return true;
        }
        int cnt = 0;
        for (int i = 0; i < n - 1; i++) {
            int x = nums[i];
            int y = nums[i + 1];
            if (x > y) {
                cnt++;// 存在一个了
                if (cnt > 1) { // 如果存在1个以上，不行
                    return false;
                }
                if (i > 0 && y < nums[i - 1]) {
                    nums[i + 1] = x;
                }
            }
        }
        return true;
    }
}
