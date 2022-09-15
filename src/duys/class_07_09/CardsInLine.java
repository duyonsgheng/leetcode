package duys.class_07_09;

/**
 * @ClassName CardsInLine
 * @Author Duys
 * @Description 先手-后手，求最大值
 * @Date 2021/7/13 10:52
 **/
public class CardsInLine {
    /**
     * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
     * 玩家A和玩家B依次拿走每张纸牌
     * 规定玩家A先拿，玩家B后拿
     * 但是每个玩家每次只能拿走最左或最右的纸牌
     * 玩家A和玩家B都绝顶聪明
     * 请返回最后获胜者的分数。
     */
    public static int win1(int arr[]) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 先手
        int first = first1(arr, 0, arr.length - 1);
        // 后手
        int scend = scend1(arr, 0, arr.length - 1);
        // 二者能获得的最大值
        return Math.max(first, scend);
    }

    // 先手从l ... r 位置上拿一个最大的
    public static int first1(int[] arr, int l, int r) {
        // base case 如果只剩下一张了，只能拿
        if (l == r) {
            return arr[l];
        }
        // 如果先手拿了l位置上的数，后手只能从l+1 到 r上拿
        int p1 = arr[l] + scend1(arr, l + 1, r);
        int p2 = arr[r] + scend1(arr, l, r - 1);
        return Math.max(p1, p2);
    }

    public static int scend1(int[] arr, int l, int r) {
        if (l == r) {
            // 如果你是后手来拿，只剩下一张牌了，你拿不到
            return 0;
        }
        // 作为后手，那么决定权不在自己，是对手给你留下的
        // 对手拿了l位置的
        int pa = first1(arr, l + 1, r);
        // 对手拿了r位置的
        int pb = first1(arr, l, r - 1);
        // 对手留给自己的肯定是比较小的
        return Math.min(pa, pb);
    }


    // 根据暴力递归方法，分析可变参数的变化范围，建立dp表
    public static int win2(int arr[]) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        // l 不会超过 N-1
        // r 也不会超过 N-1
        int[][] firstMap = new int[N][N];
        int[][] scendMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                firstMap[i][j] = -1;
                scendMap[i][j] = -1;
            }
        }
        // 先手
        int first = first2(arr, 0, arr.length - 1, firstMap, scendMap);
        // 后手
        int scend = scend2(arr, 0, arr.length - 1, firstMap, scendMap);
        // 二者能获得的最大值
        return Math.max(first, scend);
    }

    // 先手从l ... r 位置上拿一个最大的
    public static int first2(int[] arr, int l, int r, int[][] firstMap, int[][] scendMap) {
        if (firstMap[l][r] != -1) {
            return firstMap[l][r];
        }
        // base case 如果只剩下一张了，只能拿
        int ans = 0;
        if (l == r) {
            ans = arr[l];
        } else {
            // 如果先手拿了l位置上的数，后手只能从l+1 到 r上拿
            int p1 = arr[l] + scend2(arr, l + 1, r, firstMap, scendMap);
            int p2 = arr[r] + scend2(arr, l, r - 1, firstMap, scendMap);
            ans = Math.max(p1, p2);
        }
        firstMap[l][r] = ans;
        return ans;
    }

    public static int scend2(int[] arr, int l, int r, int[][] firstMap, int[][] scendMap) {

        if (scendMap[l][r] != -1) {
            return scendMap[l][r];
        }
        int ans = 0;
        if (l == r) {
            // 如果你是后手来拿，只剩下一张牌了，你拿不到
            ans = 0;
        } else {
            // 作为后手，那么决定权不在自己，是对手给你留下的
            // 对手拿了l位置的
            int pa = first2(arr, l + 1, r, firstMap, scendMap);
            // 对手拿了r位置的
            int pb = first2(arr, l, r - 1, firstMap, scendMap);
            // 对手留给自己的肯定是比较小的
            ans = Math.min(pa, pb);
        }
        scendMap[l][r] = ans;
        return ans;
    }


    // 例如 arr= [13,23,11,33,21]
    // 先手
    // 根据base 可知，当l==r的时候，就是arr[l]位置上的值
    // 先手的里面普通位置，是依赖后手的相对位置的 [l+1][r] 和[l][r-1]
    /** 左下角的是用不到的因为L>R，不会存在这样结构
     *    0   1   2   3   4    -- R
     * 0  13
     * 1  x  23
     * 2  x  x   11
     * 3  x  x   x   33
     * 4  x  x   x   x   21
     *
     * L
     */
    // 后手的
    // 根据base 可知，当l==r的时候，就等于0
    // 根据暴力递归过程，后手的位置，又依赖先手的相对位置的[l+1][r]和 [l][r-1]
    // 现在已经存在l==r位置的值了，那么后手的可以填出来了，后手的填出来了后，先手的也就可以填出来了
    /** 左下角的是用不到的因为L>R，不会存在这样结构
     *    0   1   2   3   4    -- R
     * 0  0
     * 1  x  0
     * 2  x  x   0
     * 3  x  x   x   0
     * 4  x  x   x   x   0
     *
     * L
     */


    /*****************严格表依赖结构-dp核心****************/
    public static int win3(int arr[]) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length; // 表的大小-这里是长度，不是下标
        // l 不会超过 N-1
        // r 也不会超过 N-1
        int[][] firstMap = new int[N][N];// 先手的表
        int[][] scendMap = new int[N][N];

        // 对角线都是自己本身，递归先手函数可得
        for (int i = 0; i < N; i++) {
            firstMap[i][i] = arr[i];
        }

        // 对角线的列，从1出发
        for (int startCol = 1; startCol < N; startCol++) {
            int l = 0; // 行
            int r = startCol;// 列
            // 对角线开始从0行，到最左列，列会先出现越界
            while (r < N) {
                // 如果先手拿了l位置上的数，后手只能从l+1 到 r上拿
                int p1 = arr[l] + scendMap[l + 1][r];
                int p2 = arr[r] + scendMap[l][r - 1];
                firstMap[l][r] = Math.max(p1, p2);

                int pa = firstMap[l + 1][r];
                // 对手拿了r位置的
                int pb = firstMap[l][r - 1];
                // 对手留给自己的肯定是比较小的
                scendMap[l][r] = Math.min(pa, pb);

                r++;
                l++;
            }
        }
        // 返回主函数怎么调用
        return Math.max(firstMap[0][N - 1], scendMap[0][N - 1]);
    }
}
