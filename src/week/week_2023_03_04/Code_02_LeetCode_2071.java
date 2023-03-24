package week.week_2023_03_04;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * @ClassName Code_02_LeetCode_2071
 * @Author Duys
 * @Description
 * @Date 2023/3/23 11:20
 **/
// 2071. 你可以安排的最多任务数目
public class Code_02_LeetCode_2071 {

    // 方法1
    //要想使得尽可能少的药丸来完成更多的任务
    // 任务排序，工人能力排序
    // 能完成的任务数量就是0到task长度之间
    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        int l = 0;
        int r = tasks.length;
        int m, ans = 0;
        Arrays.sort(tasks);
        Arrays.sort(workers);
        while (l <= r) {
            m = (l + r) / 2;
            // 贪点，我就让工人能力比较大的来完成任务难度较小的，来获取尽可能少的使用药丸
            if (process1(tasks, 0, m - 1, workers, workers.length - m, workers.length - 1, strength) <= pills) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    // 如果能完成这些任务至少需要几片药丸，
    // 尽可能的使得药丸更少
    public static int process1(int[] task, int tl, int tr, int[] workers, int wl, int wr, int strength) {
        // 工人不够了
        // 无论多少药丸都不行
        if (wl < 0) {
            return Integer.MAX_VALUE;
        }
        // 统计当前任务的词频
        TreeMap<Integer, Integer> cntMap = new TreeMap<>();
        for (int t : task) {
            cntMap.put(t, cntMap.getOrDefault(t, 0) + 1);
        }
        int ans = 0;
        // 当前工人要做任务
        // 那么我就使得当前工人来完成我能完成的最大难度任务
        for (int i = wl; i <= wr; i++) {
            // 小于等于我能力的最大难度任务
            Integer t = cntMap.floorKey(workers[i]);
            // 如果有任务，就做了，然后任务数量减少一个
            if (t != null) {
                // 一个任务
                if (cntMap.get(t).intValue() == 1) {
                    cntMap.remove(t);
                } else {
                    cntMap.put(t, cntMap.get(t) - 1);
                }
            }
            // 否则就嗑药，看看能不能搞定任务
            else {
                t = cntMap.floorKey(workers[i] + strength);
                if (t == null) {
                    return Integer.MAX_VALUE;
                } else {
                    ans++;
                    // 一个任务
                    if (cntMap.get(t).intValue() == 1) {
                        cntMap.remove(t);
                    } else {
                        cntMap.put(t, cntMap.get(t) - 1);
                    }
                }
            }
        }
        return ans;
    }
}
