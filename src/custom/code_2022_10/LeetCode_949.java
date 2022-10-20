package custom.code_2022_10;

import java.util.Arrays;

/**
 * @ClassName LeetCode_949
 * @Author Duys
 * @Description
 * @Date 2022/10/19 15:13
 **/
//949. 给定数字能组成的最大时间
public class LeetCode_949 {
    public String largestTimeFromDigits(int[] arr) {
        int ans = -1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j) {
                    continue;
                }
                for (int k = 0; k < 4; k++) {
                    if (k == i || k == j) {
                        continue;
                    }
                    int d = 6 - i - j - k;
                    int h = 10 * arr[i] + arr[j];
                    int m = 10 * arr[k] + arr[d];
                    if (h < 23 && m < 60) {
                        ans = Math.max(60 * h + m, ans);
                    }
                }
            }
        }
        return ans >= 0 ? String.format("%02d:%02d", ans / 60, ans % 60) : "";
    }

}
