package week.week_2022_03_03;

/**
 * @ClassName Code_05_EatFish
 * @Author Duys
 * @Description
 * @Date 2022/3/17 17:29
 **/
public class Code_05_EatFish {

// 来自bilibili
// 现在有N条鱼，每条鱼的体积为Ai，从左到右排列，数组arr给出
// 每一轮，左边的大鱼一定会吃掉右边比自己小的第一条鱼，
// 并且每条鱼吃比自己小的鱼的事件是同时发生的。
// 返回多少轮之后，鱼的数量会稳定
// 注意：6 6 3 3
// 第一轮过后 :
// 对于两个6来说，右边比自己小的第一条鱼都是第1个3，所以只有这个3被吃掉，
// 数组变成 : 6 6 3（第2个3）
// 第二轮过后 : 6 6
// 返回2

    //单调栈的用法，一定要小压大，如果不比当前顶不小，结算
    // 注意可以同时进行。
    public static int minTurns(int[] arr) {
        int n = arr.length;
        // stack[i][j] = 第i号元素吃完比他小的元素，需要多少轮
        int[][] stack = new int[n][2];
        int stackSize = 0;
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            int curAns = 0;
            // 栈顶元素比当前小
            while (stackSize > 0 && stack[stackSize - 1][0] < arr[i]) {
                // 吃掉小的，并且轮数计算是 当前的+1 和栈顶的轮数比较，取最大的，因为吃这个动作是并行的
                curAns = Math.max(curAns + 1, stack[--stackSize][1]);
            }
            stack[stackSize][0] = arr[i];
            stack[stackSize++][1] = curAns;
            ans = Math.max(ans, curAns);
        }
        return ans;
    }
}
