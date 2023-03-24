package custom.code_2023_02;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1152
 * @Author Duys
 * @Description
 * @Date 2023/2/13 10:08
 **/
// 1552. 两球之间的磁力
public class LeetCode_1552 {
    // 二分答案
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int l = 1, r = position[position.length - 1] - position[0], ans = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            // 返回最大化的最小磁力
            if (check(mid, position, m)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    // 给定一个答案，看看这个答案是否满足，整个数组上最小的值是x，
    public boolean check(int x, int[] arr, int m) {
        int pre = arr[0], cnt = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - pre >= x) {
                pre = arr[i];
                cnt++;
            }
        }
        return cnt >= m;
    }
}
