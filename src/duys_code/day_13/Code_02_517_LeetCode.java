package duys_code.day_13;

/**
 * @ClassName Code_02_517_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/super-washing-machines/
 * @Date 2021/10/21 10:16
 **/
public class Code_02_517_LeetCode {
    /**
     * 超级洗衣机问题：现在给一个数组表示 洗衣机 所在位置上有多少件衣服。
     * 每一个洗衣机的衣服在一轮平衡过程中可以往左扔一件衣服，也可能向右扔一件衣服。最后到所有的洗衣机衣服数量相同，问至少需要多少轮
     */

    /**
     * 1. 如果 sum%N != 0 不行
     * 2. 我们分析这个所有的位置，把每一个位置对应的最好答案求出来，然后在所有的答案中获取最大的，就是我们最后的答案
     * ---2.1 每一个位置的答案 可能性分析 求
     * -------1.比如来到 i i左边是 -15件衣服 i的右边是 5件衣服，那么 i位置平衡需要经历多少轮？右边需要5轮经历i，到达左边，右边平衡了，那么i需要再往右边扔10件，所以总共 15轮
     * -------2.比如来到 i i左边是 15件衣服 i的右边是 -5件衣服，那么 i位置平衡需要经历多少轮？左边需要15轮经历i，左边平衡，在经历左边给的过程我向右边给了5件，所以总共 15轮
     * -------3.比如来到 i i左边是 -15件衣服 i的右边是 -5件衣服，那么 i位置平衡需要经历多少轮？左边需要15轮经历i，左边平衡，在给右边5件，右边平衡，所以总共 20轮
     * todo ???? 如果我 左边也是正的  右边也是正的 为何会出现选择二者较大的
     * -------4.比如来到 i i左边是 15件衣服 i的右边是 5件衣服，那么 i位置平衡需要经历多少轮？左边需要15轮经历i，左边平衡，右边需要5件，经历5轮右边平衡。所以总共20轮 ???
     */
    public static int findMinMoves(int[] machines) {
        if (machines == null || machines.length < 1) {
            return 0;
        }
        int N = machines.length;
        int sum = 0;
        for (int m : machines) {
            sum += m;
        }
        if (sum % N != 0) {
            return -1;
        }
        int avg = sum / N;
        int ans = 0;
        int leftSum = 0;
        for (int i = 0; i < N; i++) {
            // 啥意思？意思就是 我左边在平衡的基础上多了还是少了
            int leftRest = leftSum - i * avg;
            int rightRest = (sum - leftSum - machines[i]) - (N - i - 1) * avg;
            if ((leftRest < 0 && rightRest < 0) /*|| (leftRest > 0 && rightRest > 0)*/) {
                ans = Math.max(ans, Math.abs(leftRest) + Math.abs(rightRest));
            } else {
                ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
            }
            leftSum += machines[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] add = {9, 1, 8, 8, 9};
        // 1: 8 2 8 8 9
        // 2: 7 3 8 8 9
        // 3: 7 6 7 7 8
        // 4: 7 7 7 7 7
        System.out.println(findMinMoves(add));
    }

}
