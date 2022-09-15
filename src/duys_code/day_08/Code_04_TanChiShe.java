package duys_code.day_08;

/**
 * @ClassName Code_04_TanChiShe
 * @Author Duys
 * @Description 贪吃蛇问题
 * @Date 2021/10/11 15:03
 **/
public class Code_04_TanChiShe {
    /**
     * 给定一个矩阵matrix，值有正、负、0
     * 蛇可以空降到最左列的任何一个位置，初始增长值是0
     * 蛇每一步可以选择右上、右、右下三个方向的任何一个前进
     * 沿途的数字累加起来，作为增长值；但是蛇一旦增长值为负数，就会死去
     * 蛇有一种能力，可以使用一次：把某个格子里的数变成相反数
     * 蛇可以走到任何格子的时候停止
     * 返回蛇能获得的最大增长值
     */

    /**
     * 思路：既然题意告诉了，最左列的任何位置都是可以空降的，那么从最左列开始，而且有三个位置可以往下走，右上，右边，右下，并且还具有一种能力
     * 那么我们想一个问题：
     * 1.如果在第0行，那么只能往右边 和右下走
     * 2.如果在第N-1行，那么只能往右上，和右边走
     * 3.除了这两行，可以走三个方向
     * 4.有一种能力，只能使用一次，那么我们需要知道，在过程中有没有用过这个能力
     */
    // 尝试暴力解答
    public static int maxHp(int[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return 0;
        }
        int ans = 0;
        // 意思是可能在任何一个i,j位置停下，所以每一个位置解答一次
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                Info cur = process(matrix, i, j);
                ans = Math.max(Math.max(cur.no, cur.yes), ans);
            }
        }
        return ans;
    }

    // 蛇从某一个最左列，且最优的空降地点开始，到 i , j 位置必须停
    // 返回一次能力也不用的最大分数
    // 返回用了一次能力的最大分数
    // 如果不用能力怎么都到不了 i,j位置，则返回 no=-1
    // 如果使用能力怎么都到不了 i,j位置，则返回 yes=-1
    public static Info process(int[][] matrix, int i, int j) {
        int no = -1;
        int yes = -1;
        // 最左列
        if (j == 0) {
            no = Math.max(matrix[i][0], -1);
            yes = Math.max(-matrix[i][0], -1);
            return new Info(no, yes);
        }

        // 要想来到 i,j 位置，可以从哪些地方到来
        // 如果不是第一行，也不是最后一行，有左上，左边，和左下
        // 如果是第一行，则只能从左下和左边
        // 如果是最后一行，则只能从左边和左上来
        int preNo = -1;
        int preYes = -1;
        // 无论如何都可以从左边来
        Info info = process(matrix, i, j - 1);
        preNo = Math.max(info.no, preNo);
        preYes = Math.max(info.yes, preYes);
        // 左上
        if (i > 0) {
            info = process(matrix, i - 1, j - 1);
            preNo = Math.max(info.no, preNo);
            preYes = Math.max(info.yes, preYes);
        }
        // 左下
        if (i < matrix.length - 1) {
            info = process(matrix, i + 1, j - 1);
            preNo = Math.max(info.no, preNo);
            preYes = Math.max(info.yes, preYes);
        }
        // 当前的
        // 当前依然不使用能力，一定要记得之前的不用能力的答案和现在的值相加和-1比较大小，因为可能当前就小于了-1了
        no = preNo == -1 ? -1 : Math.max(preNo + matrix[i][j], -1);
        // 之前已经使用了能力了，能力只有一次，之前用过了
        int preYesAndCurNo = preYes == -1 ? -1 : Math.max(preYes + matrix[i][j], -1);
        // 之前没有使用过能力，这一次用能力，因为之前没用过能力，那么这次使用，所以就需要用之前没有使用能力得到答案进行计算
        int preNoAndCueYes = preNo == -1 ? -1 : Math.max(preNo - matrix[i][j], -1);
        yes = Math.max(Math.max(preNoAndCueYes, preYesAndCurNo), -1);
        return new Info(no, yes);
    }

    public static class Info {
        public int no; // 不用能力
        public int yes;// 用了能力

        public Info(int n, int y) {
            no = n;
            yes = y;
        }
    }
}
