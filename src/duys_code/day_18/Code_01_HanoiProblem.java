package duys_code.day_18;

/**
 * @ClassName Code_01_HanoiProblem
 * @Author Duys
 * @Description
 * @Date 2021/11/2 9:29
 **/
public class Code_01_HanoiProblem {
    /**
     * 给定一个数组arr，长度为N，arr中的值只有1，2，3三种
     * arr[i] == 1，代表汉诺塔问题中，从上往下第i个圆盘目前在左
     * arr[i] == 2，代表汉诺塔问题中，从上往下第i个圆盘目前在中
     * arr[i] == 3，代表汉诺塔问题中，从上往下第i个圆盘目前在右
     * 那么arr整体就代表汉诺塔游戏过程中的一个状况
     * 如果这个状况不是汉诺塔最优解运动过程中的状况，返回-1
     * 如果这个状况是汉诺塔最优解运动过程中的状况，返回它是第几个状况
     */

    public static int kth(int[] arr) {
        return process(arr, arr.length - 1, 1, 3, 2);
    }

    // 大的方向第一步 0 ~i-1  from 去other
    // 第二大步是i单独去  from到to的位置
    // 第三大步是把0~i-1 从other 到to
    // 0...index这些圆盘，arr[0..index] index+1层塔问题
    // 当前在from 去往to 另外一个是other
    // arr[0..index]这些圆盘对应的状态 是index+1层塔的最优解的第几步
    public static int process(int[] arr, int index, int from, int to, int other) {
        if (index == -1) { // 当index来到了越界的位置了，没有最优步骤
            return 0;
        }
        // 当前的 index 在 other，与实际相悖，因为index 是从from 到 to 去，没有可能去到other上
        if (arr[index] == other) {
            return -1;
        }

        if (arr[index] == from) {
            // 当前index在from 那么 0~index-1 从from 去往other，同成为了另外一个
            // 说明第一大步没走完。所以0~i-1走到哪一步就是当前整体的情况
            return process(arr, index - 1, from, other, to);
        }
        // arr[index] == to
        // 当前index在to上
        else {
            // i层汉诺塔的问题，最优解总共 2^(i)  - 1 步
            // 1.第一大步已经走完了。index 在to上，那么总共步数就是 2^index - 1
            // 2.index单独从from到to 只需要1步
            // 3.那么0~index-1 在other上。下一步 0~index-1 从other 去往 to
            int p1 = (1 << index) - 1;
            int p2 = 1;
            int p3 = process(arr, index - 1, other, to, from);
            if (p3 == -1) {
                return -1;
            }
            return p1 + p2 + p3;
        }
    }
}
