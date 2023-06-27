package custom.code_2023_06;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1870
 * @Author Duys
 * @Description
 * @Date 2023/6/12 9:27
 **/
// 1870. 准时到达的列车最小时速
public class LeetCode_1870 {
    // 二分
    public static int minSpeedOnTime(int[] dist, double hour) {
        int l = 1;
        int r = Integer.MAX_VALUE;//Arrays.stream(dist).sum();
        int n = dist.length;
        // 向上取整
        if (n > Math.ceil(hour)) {
            return -1;
        }
        while (l < r) {
            int m = l + (r - l) / 2;
            if (ok(dist, hour, m)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    public static boolean ok(int[] arr, double hour, int speed) {
        double cnt = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            // 向上取整
            cnt += (arr[i] + speed - 1) / speed;
        }
        cnt += (double) arr[arr.length - 1] / speed;
        return cnt <= hour;
    }

    private boolean check(int[] dist, double hour, int speed) {
        double cnt = 0.0;
        // 对除了最后一个站点以外的时间进行向上取整累加
        for (int i = 0; i < dist.length - 1; ++i) {
            // 除法的向上取整
            cnt += (dist[i] + speed - 1) / speed;
        }
        // 加上最后一个站点所需的时间
        cnt += (double) dist[dist.length - 1] / speed;
        return cnt <= hour;
    }

    public static void main(String[] args) {
        // [1,1,100000]
        //2.01
        int[] arr = {1, 1, 100000};
        System.out.println(minSpeedOnTime(arr, 2.01d));
        StringBuffer stringBuffer;
    }
}

