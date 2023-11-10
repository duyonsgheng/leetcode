package hope.doubleEndedQueue;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code06_MaximumNumberOfTasksYouCanAssign
 * @date 2023年11月09日 10:36
 */
// 你可以安排的最多任务数目
// 给你 n 个任务和 m 个工人。每个任务需要一定的力量值才能完成
// 需要的力量值保存在下标从 0 开始的整数数组 tasks 中，
// 第i个任务需要 tasks[i] 的力量才能完成
// 每个工人的力量值保存在下标从 0 开始的整数数组workers中，
// 第j个工人的力量值为 workers[j]
// 每个工人只能完成一个任务，且力量值需要大于等于该任务的力量要求值，即workers[j]>=tasks[i]
// 除此以外，你还有 pills 个神奇药丸，可以给 一个工人的力量值 增加 strength
// 你可以决定给哪些工人使用药丸，但每个工人 最多 只能使用 一片 药丸
// 给你下标从 0 开始的整数数组tasks 和 workers 以及两个整数 pills 和 strength
// 请你返回 最多 有多少个任务可以被完成。
// 测试链接 : https://leetcode.cn/problems/maximum-number-of-tasks-you-can-assign/
public class Code06_MaximumNumberOfTasksYouCanAssign {
    public static int[] tasks;

    public static int[] workers;

    public static int MAXN = 50001;

    public static int[] deque = new int[MAXN];

    public static int h, t;
    // 二分 ： 做完任务从0到数组n-1
    // 贪心 ： 工人能力值从大到小的做最任务从小到大
    // 单调队列： 工人进来就先去解锁任务，任务从小到大在队列里，如果当前工人不能解锁任务，也不能做队列里最小的任务，则需要吃药，吃了药再去做任务较大的

    public static int maxTaskAssign(int[] ts, int[] ws, int pills, int strength) {
        tasks = ts;
        workers = ws;
        Arrays.sort(tasks);
        Arrays.sort(workers);
        int tsize = tasks.length, wsize = workers.length, ans = 0;
        // 二分
        for (int l = 0, r = Math.min(tsize, wsize), m; l <= r; ) {
            m = l + ((r - l) >> 1);
            // 这里是属于贪心，让能力较强的人做简单的任务
            if (ok(0, m - 1, wsize - m, wsize - 1, strength, pills)) {
                ans = m;
                l = m + 1;
            } else
                r = m - 1;
        }
        return ans;
    }

    // taskl[tl....tr]需要力量最小的几个任务
    // workers[wl....wr]力量最大的几个工人
    // 药效是s，数量是pills
    // 在药数量不超过的情况下，工人能不能每个人完成一个任务
    public static boolean ok(int tl, int tr, int wl, int wr, int s, int pills) {
        h = t = 0;
        int cnt = 0;
        // 来到第i号工人
        // j号任务
        for (int i = wl, j = tl; i <= wr; i++) {
            // 工人上来就去解锁任务，看看自己能做那些任务
            for (; j <= tr && tasks[j] <= workers[i]; j++) {
                deque[t++] = j; // 把解锁的任务都排在队列去，任务和工人都是排序了，所以都是从小到大的
            }
            //在没吃药的情况下。 如果有任务，且可以做，那么就做小的任务
            if (h < t && tasks[deque[h]] <= workers[i]) {
                h++;
            } else {
                // 没任务或者解锁不了新的任务
                // 吃药了，先去解锁
                for (; j <= tr && tasks[j] <= workers[i] + s; j++) {
                    deque[t++] = j; // 把解锁的任务都排在队列去，任务和工人都是排序了，所以都是从小到大的
                }
                // 解锁到了新的任务，并且吃了药就做当前解锁到的最大的任务，避免浪费药
                if (h < t) {
                    cnt++;
                    t--;
                } else { // 如果吃了药都不能解锁任务，或者做任务。那么就搞定不了了
                    return false;
                }
            }
        }
        return cnt <= pills;
    }
}
