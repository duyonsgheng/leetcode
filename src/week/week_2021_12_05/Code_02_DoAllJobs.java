package week.week_2021_12_05;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @ClassName Code_02_DoAllJobs
 * @Author Duys
 * @Description
 * @Date 2022/4/13 13:19
 **/

// 来自hulu
// 有n个人，m个任务，任务之间有依赖记录在int[][] depends里
// 比如: depends[i] = [a, b]，表示a任务依赖b任务的完成
// 其中 0 <= a < m，0 <= b < m
// 1个人1天可以完成1个任务，每个人都会选当前能做任务里，标号最小的任务
// 一个任务所依赖的任务都完成了，该任务才能开始做
// 返回n个人做完m个任务，需要几天
public class Code_02_DoAllJobs {

    // 图
    public static int days(int n, int m, int[][] arr) {
        if (n < 1) {
            return -1;
        }
        if (m <= 0) {
            return 0;
        }
        int[][] nexts = nexts(arr, m);
        int[] in = in(nexts, m);

        // 工人队列
        PriorityQueue<Integer> workers = new PriorityQueue<>();
        for (int i = 0; i < n; i++)
            workers.add(0); // 开始的时候，工人都是空闲的

        // 小根堆，来存放工作，序号小的
        PriorityQueue<Integer> zeorIn = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            if (in[i] == 0) {
                zeorIn.add(i);
            }
        }
        // start[i] i号任务 之前必须完成的任务占用了几天时间
        int[] start = new int[m];
        // 完成所有任务的天数
        int allDays = 0;
        int down = 0;
        while (!zeorIn.isEmpty()) {
            // 当前可以做的任务中，标号最小的
            int curJob = zeorIn.poll();
            // 当前空余的工人
            int worker = workers.poll();
            int finsh = Math.max(start[curJob], worker) + 1;
            allDays = Math.max(finsh, allDays);
            down++;
            // 当前工作做完了
            for (int next : nexts[curJob]) {
                // 下一个工作做的时候，用了几天
                start[next] = Math.max(start[next], finsh);
                if (--in[next] == 0) {
                    zeorIn.add(next);
                }
            }
            workers.add(finsh);
        }
        return down == m ? allDays : -1;
    }

    public static int[][] nexts(int[][] arr, int m) {
        // 根据选择标号最小的任务
        Arrays.sort(arr, (a, b) -> b[1] - a[1]);
        int n = arr.length;
        // 总共m个任务
        int[][] next = new int[m][];
        if (n == 0) {
            return next;
        }
        int size = 1;
        // next[i] 表示 i号任务结束，可以做的任务
        for (int i = 1; i < n; i++) {
            // 批次结算
            if (arr[i - 1][1] != arr[i][1]) {
                int from = arr[i - 1][1];
                next[from] = new int[size];
                for (int k = 0, j = i - size; k < size; j++, k++) {
                    next[from][k] = arr[j][0];
                }
                size = 1;
            } else {
                size++;
            }
        }
        int from = arr[n - 1][1];
        next[from] = new int[size];
        for (int k = 0, j = n - size; k < size; k++, j++) {
            next[from][k] = arr[j][0];
        }
        return next;
    }

    public static int[] in(int[][] next, int m) {
        int[] in = new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < next[i].length; j++) {
                in[next[i][j]]++;
            }
        }
        return in;
    }
}
