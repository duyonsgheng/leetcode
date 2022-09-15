package duys.class_08_26;

/**
 * @ClassName DP_OPT_02
 * @Author Duys
 * @Description 四边形不等式
 * @Date 2021/8/27 9:27
 **/
public class DP_OPT_02 {
    /**
     * 把题目一中提到的，
     * min{左部分累加和，右部分累加和}，定义为S(N-1)，也就是说：
     * S(N-1)：在arr[0…N-1]范围上，做最优划分所得到的min{左部分累加和，右部分累加和}的最大值
     * 现在要求返回一个长度为N的s数组，
     * s[i] =在arr[0…i]范围上，做最优划分所得到的min{左部分累加和，右部分累加和}的最大值
     * 得到整个s数组的过程，做到时间复杂度O(N)
     */
    // 解法1：是一个 O(N^2)的解
    public static int[] getArray1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int N = arr.length;
        int[] ans = new int[N];
        // 如果只有一个位置arr[0]上划分，最小就是0
        ans[0] = 0;
        int[] sum = new int[N + 1];
        // sum[0] =0 ;
        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        // 从[0] - [1] 范围上开始划分。[0] - [0]范围已经是0了
        for (int range = 1; range < N; range++) {
            // 每一次划分一个位置，求 [0] - [i] ,和 [i+1] - [range]这两个范围的最小值。
            // 然后和结果比较，如果比之前的更大，那么就使用当前的结果，这其实是一个枚举行为
            for (int i = 0; i < range; i++) {
                // 每次得到的一个新得数组，然后从0开始划分。0~0位置 1 ~ range位置 两个部分
                int sumLeft = sum(sum, 0, i);
                int sumRight = sum(sum, i + 1, range);
                ans[range] = Math.max(ans[range], Math.min(sumLeft, sumRight));
            }
        }
        return ans;
    }

    // 解法2：O(N)
    public static int[] getArray2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int N = arr.length;
        int[] ans = new int[N];
        // 如果只有一个位置arr[0]上划分，最小就是0
        ans[0] = 0;
        int[] sum = new int[N + 1];
        // sum[0] =0 ;
        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        // best的含义是这一刀切在哪里
        int best = 0;
        // 0~0 范围已经不能切了，之前的值已经设置了，从1开始
        for (int range = 1; range < N; range++) {
            // 这一刀开始往后走，因为是不回退的，比如 1 ~ 5 范围上 best = 3 现在 1 ~ 6范围了，那么无论什么情况，best不回回退，只会往前走
            while (best + 1 < range) {
                // best这划分的一刀还没往后移动的时候的左右两半的和，最小的是
                int beforeSumMin = Math.min(sum(sum, 0, best), sum(sum, best + 1, range));
                // 划分的这一刀慢慢往后走，走了一步，算出当前的左右两半的最小值
                int afterSumMin = Math.min(sum(sum, 0, best + 1), sum(sum, best + 2, range));
                // 如果移动知后的最小值比之前更大了，意思是出现了更优的划分，这里之所以要 >= 因为会存在 0 的情况，整个数组是非负的
                if (afterSumMin >= beforeSumMin) {
                    best++;
                } else
                    break;
            }
            // 最优的划分已经出现了，在best位置，那么直接算划分结果的两部分数组和的最小值
            ans[range] = Math.min(sum(sum, 0, best), sum(sum, best + 1, range));
        }
        return ans;
    }

    // sum 数组是每个位置的前缀和

    /**
     * 例如:  arr [ 1, 3, 5, 6 ]
     * 那么sum [ 0, 1, 4, 9, 15 ]
     * 要计算 [1-3]位置上的累加和：只需要sum[4]-sum[1]
     */
    public static int sum(int[] sum, int L, int R) {
        return sum[R + 1] - sum[L];
    }
}
