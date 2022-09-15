package week.week_2022_08_01;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @ClassName Code_01_LeetCode_773
 * @Author Duys
 * @Description
 * @Date 2022/8/8 9:34
 **/
// 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用0来表示。一次 移动 定义为选择0与一个相邻的数字（上下左右）进行交换.
//最终当板board的结果是[[1,2,3],[4,5,0]]谜板被解开。
//给出一个谜板的初始状态board，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
//链接：https://leetcode.cn/problems/sliding-puzzle
public class Code_01_LeetCode_773 {

    // A * 算法
    // 本题使用Dijkstra 有一个前置条件，就是需要把不同情况下的数字能交换的情况先罗列，然后进行跑Dijkstra
    // 在Dijkstra的前提下，算一个曼哈顿
    // 算邻居
    // 比如
    //  1  3  5
    //  4  0  2
    //  0可以额和3交换，0可以和4交换，0可以和2交换。这些都是 135402的邻居，然后算一个曼哈顿距离，从135042 到 123450 这个曼哈顿距离
    //  1  3  5         1 2 3
    //  0  4  2   到    4 5 0  曼哈顿距离 1移动0，2移动2，3 移动1 4 移动1， 5移动2，所以至少是 0+2+1+1+2 的曼哈顿距离

    public static int b2 = 10;
    public static int b3 = 100;
    public static int b4 = 1000;
    public static int b5 = 10000;
    public static int b6 = 100000;
    // 邻居
    public static int[] nexts = new int[3];
    // 算曼哈顿距离使用
    public static int[][] end = {{1, 2}, {0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}};

    public static int slidingPuzzle(int[][] board) {
        Set<Integer> set = new HashSet<>();
        // 转成一个数字来进行 跑Dijkstra
        int from = board[0][0] * b6 + board[0][1] * b5 + board[0][2] * b4 + board[1][0] * b3 + board[1][1] * b2 + board[1][2];
        // int[]a a[0] 从from来到当前位置已经走了几步了，a[1] 从当前位置到最终状态的曼哈顿距离，a[2] 当前状态是啥
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));
        heap.add(new int[]{0, distance(from), from});
        int ans = -1;
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int dis = cur[0];
            int curNum = cur[2];
            // 已经来到过了，不需要算了
            if (set.contains(curNum)) {
                continue;
            }
            if (curNum == 123450) {
                ans = dis;
                break;
            }
            set.add(curNum);
            int nextSize = nexts(curNum);
            for (int i = 0; i < nextSize; i++) {
                int next = nexts[i];
                if (!set.contains(next)) {
                    heap.add(new int[]{dis + 1, distance(next), next});
                }
            }
        }
        return ans;
    }


    // 当前的数，num
    // 最终要去的数，123450
    // 返回num -> 123450 曼哈顿距离！
    public static int distance(int num) {
        int ans = end[num / b6][0] + end[num / b6][1];
        ans += end[(num / b5) % 10][0] + Math.abs(end[(num / b5) % 10][1] - 1);
        ans += end[(num / b4) % 10][0] + Math.abs(end[(num / b4) % 10][1] - 2);
        ans += Math.abs(end[(num / b3) % 10][0] - 1) + end[(num / b3) % 10][1];
        ans += Math.abs(end[(num / b2) % 10][0] - 1) + Math.abs(end[(num / b2) % 10][1] - 1);
        ans += Math.abs(end[num % 10][0] - 1) + Math.abs(end[num % 10][1] - 2);
        return ans;
    }

    public static int nexts(int from) {
        // 301245
        //
        int a = from / b6;// a = 3
        int b = (from / b5) % 10; // b = 0
        int c = (from / b4) % 10;// c = 1
        int d = (from / b3) % 10;
        int e = (from / b2) % 10;
        int f = from % 10;
        // 0在哪一个位置，计算
        if (a == 0) {// 第一个位置是0，那么就和右边或者下边的交换
            nexts[0] = from + (b - a) * b6 + (a - b) * b5;
            nexts[1] = from + (d - a) * b6 + (a - d) * b3;
            return 2;
        } else if (b == 0) {
            nexts[0] = from + (a - b) * b5 + (b - a) * b6;
            nexts[1] = from + (c - b) * b5 + (b - c) * b4;
            nexts[2] = from + (e - b) * b5 + (b - e) * b2;
            return 3;
        } else if (c == 0) {
            nexts[0] = from + (b - c) * b4 + (c - b) * b5;
            nexts[1] = from + (f - c) * b4 + (c - f);
            return 2;
        } else if (d == 0) {
            nexts[0] = from + (a - d) * b3 + (d - a) * b6;
            nexts[1] = from + (e - d) * b3 + (d - e) * b2;
            return 2;
        } else if (e == 0) {
            nexts[0] = from + (b - e) * b2 + (e - b) * b5;
            nexts[1] = from + (d - e) * b2 + (e - d) * b3;
            nexts[2] = from + (f - e) * b2 + (e - f);
            return 3;
        } else {
            nexts[0] = from + (e - f) + (f - e) * b2;
            nexts[1] = from + (c - f) + (f - c) * b4;
            return 2;
        }
    }
}
