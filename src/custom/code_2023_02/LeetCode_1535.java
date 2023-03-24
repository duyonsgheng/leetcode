package custom.code_2023_02;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @ClassName LeetCode_1535
 * @Author Duys
 * @Description
 * @Date 2023/2/6 15:56
 **/
// 1535. 找出数组游戏的赢家
public class LeetCode_1535 {
    public static int getWinner(int[] arr, int k) {
        if (k >= arr.length) {
            return Arrays.stream(arr).max().getAsInt();
        }
        int ans = arr[0], cnt = 0;
        for (int i = 1; i < arr.length && cnt < k; i++) {
            if (ans > arr[i]) {
                cnt++;
            } else {
                ans = arr[i];
                cnt = 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(getWinner(new int[]{2, 1, 3, 5, 4, 6, 7}, 2));
    }
}
