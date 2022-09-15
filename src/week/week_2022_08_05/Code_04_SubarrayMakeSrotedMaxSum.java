package week.week_2022_08_05;

/**
 * @ClassName Code_04_SubarrayMakeSrotedMaxSum
 * @Author Duys
 * @Description
 * @Date 2022/9/1 10:03
 **/

// 来自Amazon
// 定义一个概念叫"变序最大和"
// "变序最大和"是说一个数组中，每个值都可以减小或者不变，
// 在必须把整体变成严格升序的情况下，得到的最大累加和
// 比如，[1,100,7]变成[1,6,7]时，就有变序最大和为14
// 比如，[5,4,9]变成[3,4,9]时，就有变序最大和为16
// 比如，[1,4,2]变成[0,1,2]时，就有变序最大和为3
// 给定一个数组arr，其中所有的数字都是>=0的
// 求arr所有子数组的变序最大和中，最大的那个并返回
// 1 <= arr长度 <= 10^6
// 0 <= arr[i] <= 10^6
public class Code_04_SubarrayMakeSrotedMaxSum {

    // 单调栈 始终保持大压小，如果遇到栈顶比自己大的，分情况讨论
    public static long maxSum(int[] arr) {
        int n = arr.length;
        int[] stack = new int[n];
        int stackSize = 0;
        long[] dp = new long[n];
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int curVal = arr[i];
            int curIdx = i;
            // 因为curVal 可能会更改
            while (curVal > 0 && stackSize > 0) {
                int leftIdx = stack[stackSize - 1]; // 栈顶元素
                int leftVal = arr[leftIdx];

                if (leftVal >= curVal) {
                    // 如果栈顶比我当前大，就一直弹出，直到为空
                    stackSize--;
                } else {
                    // 如果是小于的可以，说明可以直接结算
                    int idxDiff = curIdx - leftIdx;
                    int valDiff = curVal - leftVal;
                    // 如果值的差距比较大
                    // 比如当前是 i =5 arr[i]=20 ，leftIdx = 2 ，arr[leftIdx] = 3
                    // 从当前往前贯穿，能贯穿到 2位置，且2位置是3，不用更换，直接取值，那么从 5位置到4位置 可以使用求和公式算出最大得分
                    if (valDiff >= idxDiff) {
                        // 当前的 index 到 栈顶中小于档期那value的位置求一个等差数列和，然后加上栈顶小于当前元素的答案
                        dp[i] += sum(curVal, idxDiff) + dp[leftIdx];
                        curVal = 0;
                        curIdx = 0;
                        break;
                    } else {
                        // 如果位置差比值差更大，说明等差数列的和更大
                        dp[i] += sum(curVal, idxDiff);
                        // 到了栈顶元素的时候，栈顶元素更改，然后继续往前贯穿
                        curIdx = leftIdx;
                        curVal -= idxDiff;
                        stackSize--;
                    }
                }
            }
            // 如果 栈空了，但是curVal还有值，那就算满
            if (curVal > 0) {
                dp[i] += sum(curIdx, curIdx + 1);
            }
            // 把新的值入栈
            stack[stackSize++] = i;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // 比如max = 10
    // n = 4 那么就是求 10 + 9+8+7
    public static long sum(int max, int n) {
        n = Math.min(max, n);
        // 等差数列求和公式
        // Sn=n*a1+n(n-1)d/2或Sn=n(a1+an)/2
        return ((long) (max * 2 - n + 1)) / 2;
    }
}
