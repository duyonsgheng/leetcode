package week.week_2022_03_03;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Code_07_Coop
 * @Author Duys
 * @Description
 * @Date 2022/3/18 15:14
 **/
public class Code_07_Coop {
// 来自银联编程比赛
// 为了不断提高用户使用的体验，开发团队正在对产品进行全方位的开发和优化。
// 已知开发团队共有若干名成员，skills[i] 表示第 i 名开发人员掌握技能列表。
// 如果两名成员各自拥有至少一门对方未拥有的技能，则这两名成员可以「合作开发」。
// 请返回当前有多少对开发成员满足「合作开发」的条件。
// 由于答案可能很大，请你返回答案对 10^9 + 7 取余的结果。
// 测试链接 : https://leetcode-cn.com/contest/cnunionpay-2022spring/problems/lCh58I/


    /**
     * 思路： 反向思考
     * 1.只要有一个与对方不相同的那么就算满足
     * 2.反着思考。当前遇到的只要是某一个的子集，或者等于某一个，那么就是不满足的
     * 3.根据题意每一个开发人员最多4个技能，并且每一个技能值最大1000，那么我们完全可以用一个long型数字来代表一个员工所会的技能
     * <p>
     * 我们用所有的，减去不满足的，剩下的就是满足的
     */
    //
    public static long mod = 1000000007L;

    public static int coopDevelop(int[][] arr) {
        int n = arr.length;
        Map<Long, Long> noFullStatusMap = new HashMap<>();
        for (int[] po : arr) {
            fillNoFullMap(po, 0, 0, true, noFullStatusMap);
        }

        // 来遍历，顺带记录全集的
        Map<Long, Long> curMap = new HashMap<>();
        long no = 0;
        for (int[] po : arr) {
            long status = 0;
            for (int p : po) {
                status = (status << 10) | p;
            }
            // 看看当前元素 是哪些元素的子集的，记录有几个元素
            no += noFullStatusMap.getOrDefault(status, 0L);
            // 看看当前元素，和哪些元素相等的，记录有几个
            no += curMap.getOrDefault(status, 0L);
            curMap.put(status, curMap.getOrDefault(status, 0L) + 1);
        }
        long all = (long) n * (long) (n - 1) / 2l;
        return (int) ((all - no) % mod);
    }

    // 计算当前员工 除了全集以外所有的子集
    public static void fillNoFullMap(int[] people, int i, long status, boolean full, Map<Long, Long> noFullStatusMap) {
        if (i == people.length) {
            // 不是全集，那么可以收集
            if (!full) {
                noFullStatusMap.put(status, noFullStatusMap.getOrDefault(status, 0L) + 1);
            }
        } else {
            // 不要当前位置的元素，那么肯定不是全集
            fillNoFullMap(people, i + 1, status, false, noFullStatusMap);
            // 要当前位置的元素，可能是全集，可能不是，
            fillNoFullMap(people, i + 1, (status << 10) | people[i], full, noFullStatusMap);
        }
    }
}
