package week.week_2022_02_02;

/**
 * @ClassName Code_04_LeetCode_780
 * @Author Duys
 * @Description
 * @Date 2022/3/30 13:15
 **/
public class Code_04_LeetCode_780 {
    // 给定四个整数sx,sy，tx和ty，如果通过一系列的转换可以从起点(sx, sy)到达终点(tx, ty)，则返回 true，否则返回false。
    //从点(x, y)可以转换到(x, x+y) 或者(x+y, y)。
    //链接：https://leetcode-cn.com/problems/reaching-points

    // 思路就是从 tx ty向 sx 和 sy移动
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) {
            return true;
        }
        while (tx != ty) {
            if (tx < ty) {
                ty -= tx;
            } else {
                tx -= ty;
            }
            if (sx == tx && sy == ty) {
                return true;
            }
        }
        return false;
    }

    public boolean reachingPoints2(int sx, int sy, int tx, int ty) {
        while (sx < tx && sy < ty) {
            if (tx < ty) {
                ty %= tx;
            } else {
                tx %= ty;
            }
        }
        return (sx == tx && sy <= ty && (ty - sy) % sx == 0) || (sy == ty && sx <= tx && (tx - sx) % sy == 0);
    }
}
