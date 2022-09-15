package duys_code.day_25;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Code_03_149_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/max-points-on-a-line/
 * @Date 2021/11/17 11:30
 **/
public class Code_03_149_LeetCode {
    /**
     * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
     */
    // 四种情况
    // 1.共x轴
    // 2.共y轴
    // 3.共点
    // 4.其他的斜率
    // 我们把 共x，共y和其他斜率的求一个最大，最后和共点的加起来，斜率怎么算，可以使用最大公约数化简后得到
    public int maxPoints(int[][] points) {
        if (points == null) {
            return 0;
        }
        if (points.length <= 2) {
            return points.length;
        }
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < points.length; i++) {
            map.clear();// 每次计算都需要新得到的
            // 当前的点和后面所有的点都来一次
            // 四种情况
            int p1 = 1;// 共点 ，当前i点算一个了
            int px = 0;
            int py = 0;
            int pz = 0;
            for (int j = i + 1; j < points.length; j++) {
                int x = points[j][0] - points[i][0];
                int y = points[j][1] - points[i][1];
                if (x == 0 && y == 0) {
                    p1++;
                } else if (x == 0) {
                    px++;
                } else if (y == 0) {
                    py++;
                } else {
                    int gcd = gcd(x, y);
                    x /= gcd;
                    y /= gcd;
                    if (!map.containsKey(x)) {
                        map.put(x, new HashMap<>());
                    }
                    if (!map.get(x).containsKey(y)) {
                        map.get(x).put(y, 0);
                    }
                    map.get(x).put(y, map.get(x).get(y) + 1);
                    pz = Math.max(pz, map.get(x).get(y));
                }
            }
            ans = Math.max(ans, Math.max(Math.max(px, py), pz) + p1);
        }
        return ans;
    }

    // a合b不能等于0
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        System.out.println(gcd(6, 18));
    }
}
