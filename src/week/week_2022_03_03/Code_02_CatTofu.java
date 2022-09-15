package week.week_2022_03_03;

import java.util.Arrays;

/**
 * @ClassName Code_02_CatTofu
 * @Author Duys
 * @Description
 * @Date 2022/3/17 14:32
 **/
public class Code_02_CatTofu {
    // 来自美团
    // 有一块10000 * 10000 * 10000的立方体豆腐
    // 豆腐的前左下角放在(0,0,0)点，豆腐的后右上角放在(10000,10000,10000)点
    // 下面给出切法的数据结构
    // [a,b]
    // a = 1，表示x = b处，一把无穷大的刀平行于yz面贯穿豆腐切过去
    // a = 2，表示y = b处，一把无穷大的刀平行于xz面贯穿豆腐切过去
    // a = 3，表示z = b处，一把无穷大的刀平行于xy面贯穿豆腐切过去
    // a = 1 or 2 or 3，0<=b<=10000
    // 给定一个n*2的二维数组，表示切了n刀
    // 返回豆腐中最大的一块体积是多少

    /**
     * 思路：
     * 看到这个玩儿法，比较贴切实际
     * 从三个不同维度切，能切到最大的那一块体积
     * 首先 三个维度，都是在 0 和 10000之间，并且0和10000已经切了。那么在切的过程中，相邻差值最大的抓出来，然后就可以得出了
     */
    public static long maxCat(int[][] cats) {
        if (cats == null || cats.length == 0) {
            return 10000 * 10000 * 10000l;
        }
        // 然后根据不同维度的切排序
        Arrays.sort(cats, (a, b) -> a[0] != b[0] ? (a[0] - b[0]) : (a[1] - b[1]));
        int n = cats.length;
        int i = 0;
        int xDiffMax = 0;
        int pre = 0;
        //
        while (i < n && cats[i][0] == 1) {
            xDiffMax = Math.max(xDiffMax, cats[i][1] - pre);
            pre = cats[i][1];
            i++;
        }
        // 这儿别忘记了 10000的时候也有一刀
        xDiffMax = Math.max(xDiffMax, 10000 - pre);

        int yDiffMax = 0;
        pre = 0;
        while (i < n && cats[i][0] == 2) {
            yDiffMax = Math.max(yDiffMax, cats[i][1] - pre);
            pre = cats[i][1];
            i++;
        }
        yDiffMax = Math.max(yDiffMax, 10000 - pre);


        int zDiffMax = 0;
        pre = 0;
        while (i < n && cats[i][0] == 3) {
            zDiffMax = Math.max(zDiffMax, cats[i][1] - pre);
            pre = cats[i][1];
            i++;
        }
        zDiffMax = Math.max(zDiffMax, 10000 - pre);

        return (long) xDiffMax * yDiffMax * zDiffMax;
    }
}
