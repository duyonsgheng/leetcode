package week.week_2022_04_02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName Code_06_AllJobFinishTime
 * @Author Duys
 * @Description
 * @Date 2022/4/13 15:18
 **/

// 来自美团
// 3.36笔试
// 给定一个正数n, 表示有0~n-1号任务
// 给定一个长度为n的数组time，time[i]表示i号任务做完的时间
// 给定一个二维数组matrix
// matrix[j] = {a, b} 代表：a任务想要开始，依赖b任务的完成
// 只要能并行的任务都可以并行，但是任何任务只有依赖的任务完成，才能开始
// 返回一个长度为n的数组ans，表示每个任务完成的时间
// 输入可以保证没有循环依赖
public class Code_06_AllJobFinishTime {

    public static int[] finishTime(int n, int[] time, int[][] matrix) {
        // 搞一个图出来
        List<List<Integer>> nexts = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nexts.add(new ArrayList<>());
        }
        // 入度表
        int[] in = new int[n];

        for (int[] map : matrix) {
            int from = map[1];
            int to = map[0];
            nexts.get(from).add(to);
            in[to]++;
        }
        Queue<Integer> zeroIn = new LinkedList<>();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (in[i] == 0) {
                zeroIn.add(i);
            }
        }
        while (!zeroIn.isEmpty()) {
            int cur = zeroIn.poll();
            ans[cur] += time[cur];
            for (int next : nexts.get(cur)) {
                ans[next] = Math.max(ans[next], ans[cur]);
                if (--in[next] == 0) {
                    zeroIn.add(next);
                }
            }
        }
        return ans;
    }
}
