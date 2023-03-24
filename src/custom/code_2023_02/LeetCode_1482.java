package custom.code_2023_02;

/**
 * @ClassName LeetCode_1482
 * @Author Duys
 * @Description
 * @Date 2023/2/1 16:16
 **/
// 1482. 制作 m 束花所需的最少天数
public class LeetCode_1482 {
    public int minDays(int[] bloomDay, int m, int k) {
        // m束花，每一束需要k朵，如果花园的花都不够，制不了
        if (m * k > bloomDay.length) {
            return -1;
        }
        int l = 1;
        int r = 1;
        for (int num : bloomDay) {
            r = Math.max(r, num);
        }
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (canMake(mid, m, k, bloomDay)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return canMake(r, m, k, bloomDay) ? r : -1;
    }

    public boolean canMake(int day, int m, int k, int[] arr) {
        int sum = 0, tmp = 0;
        for (int num : arr) {
            // 说明花开了，可以用来制花了
            if (num <= day) {
                // 减少一朵花，如果当前这一束花满了，需要把束减小，并且恢复花限制，下一束花继续了
                tmp++;
                if (tmp == k) {
                    sum++;
                    tmp = 0;
                }
                //m--; // 花束减小
                //tmp = k; // 花需要恢复
            } else { // 如果不能摘，就继续
                tmp = 0;
            }
        }
        // 如果花束没了，说明可以完成
        return sum >= m;
    }
}
