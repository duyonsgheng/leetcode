package week.week_2022_02_03;

/**
 * @ClassName Code_03_LeetCode_1041
 * @Author Duys
 * @Description
 * @Date 2022/3/29 13:49
 **/
public class Code_03_LeetCode_1041 {
    // 在无限的平面上，机器人最初位于(0, 0)处，面朝北方。注意:
    //北方向 是y轴的正方向。
    //南方向 是y轴的负方向。
    //东方向 是x轴的正方向。
    //西方向 是x轴的负方向。
    //机器人可以接受下列三条指令之一：
    //"G"：直走 1 个单位
    //"L"：左转 90 度
    //"R"：右转 90 度
    //机器人按顺序执行指令instructions，并一直重复它们。
    //只有在平面中存在环使得机器人永远无法离开时，返回true。否则，返回 false。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/robot-bounded-in-circle

    public static boolean isRobotBounded(String instructions) {
        if (instructions == null || instructions.equals("")) {
            return true;
        }
        int row = 0;
        int col = 0;
        int direction = 0; // 0 1 2 3 就是四个方向
        char[] str = instructions.toCharArray();
        for (char cur : str) {
            if (cur == 'R') {
                direction = right(direction);
            } else if (cur == 'L') {
                direction = left(direction);
            } else {
                row = row(direction, row);
                col = col(direction, col);
            }
        }
        return row == 0 && col == 0 || direction != 0;
    }

    // 当前如果在正前面，
    public static int left(int direction) {
        return direction == 0 ? 3 : direction - 1;
    }

    public static int right(int direction) {
        return direction == 3 ? 0 : direction + 1;
    }

    public static int row(int direction, int r) {
        return (direction == 1 || direction == 3) ? r : (r + (direction == 0 ? 1 : -1));
    }

    public static int col(int direction, int c) {
        return (direction == 0 || direction == 2) ? c : (c + ((direction == 1) ? 1 : -1));
    }
}
