package custom.code_2022_10;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @ClassName LeetCode_904
 * @Author Duys
 * @Description
 * @Date 2022/10/12 17:38
 **/
// 904. 水果成篮
public class LeetCode_904 {

    public static void main(String[] args) {
        int[] arr = {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};
        System.out.println(totalFruit(arr));
    }

    // 滑动窗口
    public static int totalFruit(int[] fruits) {
        int n = fruits.length;
        int ans = 0;
        // [3,3,3,1,2,1,1,2,3,3,4]
        int[] cnt = new int[n];
        int l = 0;
        int r = 0;
        int count = 0;
        while (r < n) {
            cnt[fruits[r]]++;
            // 窗口向右扩展。遇到为1的就算一种
            if (cnt[fruits[r]] == 1) {
                count++;
            }
            r++;
            while (count > 2) {
                cnt[fruits[l]]--;
                // 窗口收缩，遇到为0了，就--
                if (cnt[fruits[l]] == 0) {
                    count--;
                }
                l++;
            }
            ans = Math.max(ans, r - l);
        }
        return ans;
    }
}
