package duys_code.day_35;

import java.util.Arrays;

/**
 * @ClassName Code_08_MagicStone
 * @Author Duys
 * @Description
 * @Date 2021/12/10 13:16
 **/
public class Code_08_MagicStone {
    // 来自小红书
// [0,4,7] ： 0表示这里石头没有颜色，如果变红代价是4，如果变蓝代价是7
// [1,X,X] ： 1表示这里石头已经是红，而且不能改颜色，所以后两个数X无意义
// [2,X,X] ： 2表示这里石头已经是蓝，而且不能改颜色，所以后两个数X无意义
// 颜色只可能是0、1、2，代价一定>=0
// 给你一批这样的小数组，要求最后必须所有石头都有颜色，且红色和蓝色一样多，返回最小代价
// 如果怎么都无法做到所有石头都有颜色、且红色和蓝色一样多，返回-1

    public static int minCost(int[][] stones) {
        int n = stones.length;
        // 奇数不行
        if ((n & 1) == 1) {
            return -1;
        }
        // 如果没有颜色，就根据代价的差值排序
        // 有颜色就根据第一个代价排序
        Arrays.sort(stones, (a, b) -> a[0] == 0 && b[0] == 0 ? (b[1] - b[2] - a[1] + a[2]) : (a[0] - b[0]));
        int zero = 0;
        int red = 0;
        int blue = 0;
        int cost = 0;
        // 统计无色，有颜色的数量
        for (int i = 0; i < n; i++) {
            if (stones[i][0] == 0) {
                zero++;
                cost += stones[i][1];
            } else if (stones[i][0] == 1) {
                red++;
            } else {
                blue++;
            }
        }
        // 其中某一个都大于了一半了，说明不行
        if (red > (n >> 1) || blue > (n >> 1)) {
            return -1;
        }
        //
        blue = zero - ((n >> 1) - red);
        for (int i = 0; i < blue; i++) {
            cost += stones[i][2] - stones[i][1];
        }
        return cost;
    }
}
