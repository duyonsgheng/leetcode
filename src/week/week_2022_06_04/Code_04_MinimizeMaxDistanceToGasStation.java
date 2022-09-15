package week.week_2022_06_04;

/**
 * @ClassName Code_04_MinimizeMaxDistanceToGasStation
 * @Author Duys
 * @Description
 * @Date 2022/6/30 10:42
 **/
// 在加油站中间添加k个加油站，最小的精度
//  https://leetcode.cn/problems/minimize-max-distance-to-gas-station/
public class Code_04_MinimizeMaxDistanceToGasStation {
    public static double minmaxGasDist(int[] stations, int K) {
        double ac = 0.0000001;
        double l = 0;
        double r = 100000000;
        double m = 0;
        double ans = 0;
        // 二分。精度区间确定，加油站数量确定
        while (r - l > ac) {
            m = (r + l) / 2;
            if (isOk(stations, K, m)) {
                ans = m;
                r = m;
            } else {
                l = m;
            }
        }
        return ans;
    }

    public static boolean isOk(int[] stations, int k, double limit) {
        int used = 0;
        for (int i = 1; i < stations.length; i++) {
            // 看看每一个区间需要使用多少个加油站
            used += (int) ((stations[i] - stations[i - 1]) / limit);
            if (used > k) { // 超过了这么多加油站，不行了
                return false;
            }
        }
        return true;
    }
}
