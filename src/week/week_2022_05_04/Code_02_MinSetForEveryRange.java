package week.week_2022_05_04;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Code_02_MinSetForEveryRange
 * @Author Duys
 * @Description
 * @Date 2022/5/27 13:16
 **/
// 给定区间的范围[xi,yi]，xi<=yi，且都是正整数
// 找出一个坐标集合set，set中有若干个数字
// set中的所有数字，一定要在每个给定的区间内，至少一个
// 求set的最少需要几个数
// 比如给定区间 : [5, 8] [1, 7] [2, 4] [1, 9]
// set最小可以是: {2, 6}或者{2, 5}
public class Code_02_MinSetForEveryRange {


    public static int minSet(int[][] range) {
        int n = range.length;
        // 生成一个二维的事件数组
        // 每个一维数组表示 0表示开头事件 1表示结束事件
        int[][] events = new int[n << 1][3];
        for (int i = 0; i < n; i++) {
            events[i][0] = 0; // 开始事件
            events[i][1] = range[i][1]; // 结束时刻
            events[i][2] = range[i][0]; // 开始时刻

            events[i + n][0] = 1;// 结束事件
            events[i + n][2] = range[i][1];
        }
        // 根据事件来排序，事件的开始时刻
        Arrays.sort(events, (a, b) -> a[2] - b[2]);
        Set<Integer> set = new HashSet<>();
        int ans = 0;
        for (int[] event : events) {
            if (event[0] == 0) { // 如果是开始事件，就把结束时间加到容器
                set.add(event[1]);
            } else { // 如果结束事件就结算一下答案
                if (set.contains(event[2])) {
                    ans++;
                    set.clear();
                }
            }
        }
        return ans;
    }
}
