package duys_code.day_51;

import java.util.HashSet;

/**
 * @ClassName Code_01_LCP03_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/programmable-robot/
 * @Date 2021/11/5 14:30
 **/
public class Code_01_LCP03_LeetCode {

    public static boolean robot(String command, int[][] obstacles, int x, int y) {
        int X = 0;
        int Y = 0;
        // 记录一轮以内所有的情况，就是一轮以内，能不能达到目标位置
        HashSet<Integer> set = new HashSet<>();
        set.add(0);// 初始位置能到0
        char[] com = command.toCharArray();
        // 一轮以内总共往右贺往上走了多远
        for (char c : com) {
            X += c == 'R' ? 1 : 0;
            Y += c == 'U' ? 1 : 0;
            // 一轮以内所有的状态都记着
            set.add((X << 10) | Y);
        }
        // 如果都到不了目标位置，还说个JB
        if (!process(x, y, X, Y, set)) {
            return false;
        }
        // 如果碰到了任何障碍。惜败
        for (int[] ob : obstacles) {
            if (ob[0] <= x && ob[1] <= y && process(ob[0], ob[1], X, Y, set)) {
                return false;
            }
        }
        return true;
    }

    public static boolean process(int curX, int curY, int X, int Y, HashSet<Integer> set) {
        // 只能往上走
        if (X == 0) {
            return curX == 0;
        }
        if (Y == 0) {
            return curY == 0;
        }
        // 要想到达目标点，至少需要多少轮
        int last = Math.min(curX / X, curY / Y);

        // 经过这么多轮后还剩下多少
        int restX = curX - X * last;

        int restY = curY - Y * last;

        return set.contains((restX << 10) | restY);
    }
}
