package duys_code.day_50;

/**
 * @ClassName Code_04_600_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/non-negative-integers-without-consecutive-ones/
 * @Date 2021/10/29 15:26
 **/
public class Code_04_600_LeetCode {
    /**
     * 根据题目描述的信息。这道题肯定是二进制位的关系
     * 给一个n我来到 n的二进制最高位上做抉择。选择0或者1，
     * 如果当前先择0，那么下一个二进制位可以是0或者1，
     * 如果当前选择的是1，那么下一个二进制位只能选择0，因为1不能相邻 ，但是前提是不能大于n
     * 一直到-1位结束
     */
    public static int findIntegers(int n) {
        int i = 31;
        for (; i >= 0; i--) {
            if ((n & (1 << i)) != 0) {
                break;
            }
        }
        return f(0, false, i, n);
    }

    // pre之前选择得是0还是1.
    // preStatus 之前位置选择会不会导致 比n大
    public static int f(int pre, boolean preStatus, int index, int n) {
        if (index == -1) {// 已经来到了越界的位置了，说明之前做的决定有效
            return 1;
        }
        // 前一位我做的决定是1，那么我当前位只能做0的决定
        if (pre == 1) {
            // 如果之前位置做的是1的决定，我再当前位置做0的决定，如果n原来的index位置是1，现在变成0，一定比之前小
            // 如果之前就应是小的了，那么后面的都会比n小，高位都已经小了，低位无论是1还是0都会小
            // (n & (1 << index)) != 0 n原来再index位置是不是1
            boolean curStatus = preStatus | ((n & (1 << index)) != 0);
            return f(0, curStatus, index - 1, n);
        } else {
            // 可能1：继续做0的决定
            // 如果之前就应是小的了，那么后面的都会比n小，高位都已经小了，低位无论是1还是0都会小
            boolean curStatus = preStatus | ((n & (1 << index)) != 0);
            int p1 = f(0, curStatus, index - 1, n);
            // 可能2：做1的决定
            int p2 = 0;
            // 之前做的决定是比n小的，并且n再index位置上是1，才能做1的决定，否则不能，因为再某一个位置做1决定的时候，都会导致整个数变大
            if (preStatus || ((n & (1 << index)) != 0)) {
                p2 = f(1, preStatus, index - 1, n);
            }
            return p1 + p2;
        }
    }


    public static int findIntegers2(int n) {
        int i = 31;
        for (; i >= 0; i--) {
            if ((n & (1 << i)) != 0) {
                break;
            }
        }
        // int status = 0 -> false
        // int status = 1 -> true
        int[][][] dp = new int[2][2][i + 1];
        return f2(0, 0, i, n, dp);
    }

    // 0 表示不比n小
    // 1 表示比n小
    public static int f2(int pre, int preStatus, int index, int n, int[][][] dp) {
        if (index == -1) {// 已经来到了越界的位置了，说明之前做的决定有效
            return 1;
        }
        if (dp[pre][preStatus][index] != 0) {
            return dp[pre][preStatus][index];
        }
        int ans = 0;
        // 前一位我做的决定是1，那么我当前位只能做0的决定
        // 如果之前位置做的是1的决定，我再当前位置做0的决定，如果n原来的index位置是1，现在变成0，一定比之前小
        // 如果之前就应是小的了，那么后面的都会比n小，高位都已经小了，低位无论是1还是0都会小
        // (n & (1 << index)) != 0 n原来再index位置是不是1
        if (pre == 1) {
            // 当前位置变0.之前如果已经大了。
            ans = f2(0, Math.max(preStatus, (n & (1 << index)) != 0 ? 1 : 0), index - 1, n, dp);
        } else { // 之前是0的决定
            // 之前做的决定是1.当前只能做0的决定，之前做的决定都已经导致比n大了，那么当前位置如果是0，并且之前已经导致大了，只能继续做0的决定，来减小
            if ((n & (1 << index)) == 0 && preStatus == 0) {
                ans = f2(0, preStatus, index - 1, n, dp);
            } else { // 之前就已经是小于n了，这里做0或者1都可以
                // 当前做1的决定，看我后续
                ans = f2(1, preStatus, index - 1, n, dp)
                        // 当前做0的决定，如果之前的就已经大于了，
                        + f2(0, Math.max(preStatus, (n & (1 << index)) != 0 ? 1 : 0), index - 1, n, dp);
            }
        }

        dp[pre][preStatus][index] = ans;
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(findIntegers2(4));
    }
}
