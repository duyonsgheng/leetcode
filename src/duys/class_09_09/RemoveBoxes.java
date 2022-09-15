package duys.class_09_09;

/**
 * @ClassName RemoveBoxes
 * @Author Duys
 * @Description ；力扣原题； https://leetcode-cn.com/problems/remove-boxes/
 * @Date 2021/9/10 15:48
 **/
public class RemoveBoxes {
    /**
     * 数组里面的数消除：得到的最大得分
     * 比如：[3,3,3,2,3,1,2] 连续相同的数可以一起消掉，
     * 比如3个3一起消掉，得分 3*3 = 9
     * 1 个1消掉得分 1*1 = 1
     * 一个3消掉 得分 1* 1 = 1
     * 2个2最后消掉 2*2 = 4
     */
    /**
     * 思路：需要设计一个函数来计算 L~R这个范围所有的数都消掉的最大得分。需要补齐信息是啥，外部需要满足的条件是啥，潜台词是啥？
     */
    public static int removeBoxes1(int[] boxes) {
        return func1(boxes, 0, boxes.length - 1, 0);
    }

    // arr[L ... R]范围上消除，
    // num的意思是前面有num个arr[L] 位置上的数，不算L位置上这个有num个
    public static int func1(int[] arr, int L, int R, int num) {
        // 只有一个位置了，如果
        if (L > R) {
            return 0;
        }
        // 可能性1 ：L位置上的数和前面的num个一起消掉,那么num+L位置上这个数，一共有num+1个
        int max = func1(arr, L + 1, R, 0) + (num + 1) * (num + 1);
        // 接下来是 L+1 到 R上的情况
        for (int i = L + 1; i <= R; i++) {
            // 可以合并，然后向下
            // 如果当前i位置的数是和L 位置的数一样的，那么有下面的可能性
            // 可能性1：当前位置单独消除
            // 可能性2：当前的位置和i合在一起向下传递
            // 比如 1 1 1 2 3 1 1 3 1 1 1其中的前面三个1可以一起消除，然后来到2 位置 也可以前面的1 不消除，先消除 2 和 3 ，然后 前面的三个1和后面的1合在一起消除
            // 意思就是 当前 L+1 ~ R范围上 i这个位置的数和L位置的数是一样的，那么 前面相同的是和i位置的数合在一起消，所以需要先消掉 L+1 到 i-1 位置上的不相同的数
            if (arr[L] == arr[i]) {
                max = Math.max(max, func1(arr, L + 1, i - 1, 0) + func1(arr, i, R, num + 1));
            }
        }
        return max;
    }

    public static int removeBoxes2(int[] boxes) {
        int N = boxes.length;
        int[][][] dp = new int[N][N][N];
        return func2(boxes, 0, boxes.length - 1, 0, dp);
    }

    // arr[L ... R]范围上消除，
    // num的意思是前面有num个arr[L] 位置上的数，不算L位置上这个有num个
    public static int func2(int[] arr, int L, int R, int num, int[][][] dp) {
        // 只有一个位置了，如果
        if (L > R) {
            return 0;
        }
        if (dp[L][R][num] > 0) {
            return dp[L][R][num];
        }
        // 可能性1 ：L位置上的数和前面的num个一起消掉,那么num+L位置上这个数，一共有num+1个
        int max = func2(arr, L + 1, R, 0, dp) + (num + 1) * (num + 1);
        // 接下来是 L+1 到 R上的情况
        for (int i = L + 1; i <= R; i++) {
            // 可以合并，然后向下
            // 如果当前i位置的数是和L 位置的数一样的，那么有下面的可能性
            // 可能性1：当前位置单独消除
            // 可能性2：当前的位置和i合在一起向下传递
            // 比如 1 1 1 2 3 1 1 3 1 1 1其中的前面三个1可以一起消除，然后来到2 位置 也可以前面的1 不消除，先消除 2 和 3 ，然后 前面的三个1和后面的1合在一起消除
            // 意思就是 当前 L+1 ~ R范围上 i这个位置的数和L位置的数是一样的，那么 前面相同的是和i位置的数合在一起消，所以需要先消掉 L+1 到 i-1 位置上的不相同的数
            if (arr[L] == arr[i]) {
                max = Math.max(max, func2(arr, L + 1, i - 1, 0, dp) + func2(arr, i, R, num + 1, dp));
            }
        }
        dp[L][R][num] = max;
        return max;
    }


    // 常数项进行消除
    public static int removeBoxes3(int[] boxes) {
        int N = boxes.length;
        int[][][] dp = new int[N][N][N];
        return func3(boxes, 0, boxes.length - 1, 0, dp);
    }

    // arr[L ... R]范围上消除，
    // num的意思是前面有num个arr[L] 位置上的数，不算L位置上这个有num个
    public static int func3(int[] arr, int L, int R, int num, int[][][] dp) {
        // 只有一个位置了，如果
        if (L > R) {
            return 0;
        }
        if (dp[L][R][num] > 0) {
            return dp[L][R][num];
        }
        // last的意思是：比如前面已经3个1了。找到连续的1最后的位置，优化常数项时间
        int last = L;
        while (last + 1 <= R && arr[last + 1] == arr[L]) {
            last++;
        }
        // 不算last这个位置，前面有几个相同的数和L位置的数相同
        int pre = num + last - L;
        // 可能性1 ：L位置上的数和前面的num个一起消掉,那么num+L位置上这个数，一共有num+1个
        // 前面和last位置相同的数加上last位置的数进行合并
        int max = func3(arr, last + 1, R, 0, dp) + (pre + 1) * (pre + 1);
        // 接下来是 last+2 到 R上的情况，前面last+1 已经和 last位置都已经计算过了
        for (int i = last + 2; i <= R; i++) {
            // 比如在后面的区域有连续的1出现，找到这一片连续的1 的开头位置，从这里开始进行去可能性迭代，选择消掉还是继续和后面有相同的数进行合并
            if (arr[L] == arr[i] && arr[i - 1] != arr[L]) {
                max = Math.max(max, func3(arr, last + 1, i - 1, 0, dp) + func3(arr, i, R, pre + 1, dp));
            }
        }
        dp[L][R][num] = max;
        return max;
    }
}
