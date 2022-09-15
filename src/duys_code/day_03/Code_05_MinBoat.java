package duys_code.day_03;

import java.util.Arrays;

/**
 * @ClassName Code_05_MinBoat
 * @Author Duys
 * @Description
 * @Date 2021/9/22 10:35
 **/
public class Code_05_MinBoat {
    /**
     * 给定一个正数数组arr，代表若干人的体重
     * 再给定一个正数limit，表示所有船共同拥有的载重量
     * 每艘船最多坐两人，且不能超过载重
     * 想让所有的人同时过河，并且用最好的分配方法让船尽量少
     * 返回最少的船数
     */

    /**
     * 解法：先给这个arr排序，然后从第一个大于 limit/2的位置开始往两边走，L指针向左，R指针向右
     * <p>
     * [5,4,7,3,2,8,9,1,6] limit = 10
     * [1,2,3,4,5,6,7,8,9] limit = 10
     */
    public static int minBoat(int[] arr, int limit) {
        if (arr == null || arr.length < 1 || limit < 0) {
            return 0;
        }
        int N = arr.length;
        Arrays.sort(arr);
        // 有搞不定的，无论怎么装都装不下，返回-1，无效的
        if (arr[N - 1] > limit) {
            return -1;
        }
        int mind = -1;
        for (int i = N - 1; i >= 0; i--) {
            if (arr[i] <= (limit >> 1)) {
                mind = i;
                break;
            }
        }
        // 每一个人都需要单独的额一艘船来装
        if (mind == -1) {
            return N;
        }
        int L = mind;
        int R = mind + 1;
        // [1,2,3,4,5, 5, 6,6,7,8,9] limit = 10
        //             L  R
        int noUsed = 0;
        while (L >= 0) {
            int solved = 0;
            while (R < N && arr[L] + arr[R] <= limit) {
                R++;
                solved++;
            }
            if (solved == 0) {
                // 这个就是不能和右侧进行配对的，就是课上讲的画X的位置
                noUsed++;
                L--;
            }
            // 比如当前 ... 3 5 5 5 6 6 7 7 7
            //            L       R       R'
            // 这里就是计算比如现在R可以走到R'这里，那么L也可以往左侧走R'-R+1这么多数都是可以配对的，这里就是计算L最多能到最左的什么位置
            else {
                L = Math.max(-1, L - solved);
            }
        }
        int all = mind + 1;
        // 算从mind位置开始向左，有多少个花了V的位置。也就是和右边配对的位置数目。
        int used = all - noUsed;
        // 右侧大的数还剩下的，单独配一个船
        int more = N - all - used;
        // 画X的位置需要向上取整
        return used + ((noUsed + 1) >> 1) + more;
    }
}
