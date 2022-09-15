package week.week_2022_02_04;

/**
 * @ClassName Code_01_SplitSameNumberWays
 * @Author Duys
 * @Description
 * @Date 2022/3/23 15:51
 **/
public class Code_01_SplitSameNumberWays {
// 来自微软
// 比如，str = "ayxbx"
// 有以下4种切法 : a | yxbx、ay | xbx、ayx | bx、ayxb | x
// 其中第1、3、4种切法符合：x和y的个数，至少在左右两块中的一块里有相同的数量
// 所以返回3
// 给定一个字符串str，长度为N
// 你有N-1种划分方法，把str切成左右两半，返回有几种切法满足：
// x和y的个数，至少在左右两块中的一块里有相同的数量

    /**
     * 思路：词频统计
     */
    public static int splitSameNumberWays(char[] str) {
        if (str == null || str.length == 0) {
            return 0;
        }
        // 开始词频统计
        int xAll = 0;
        int yAll = 0;
        int n = str.length;
        for (int i = 0; i < n; i++) {
            if (str[i] == 'x') {
                xAll++;
            }
            if (str[i] == 'y') {
                yAll++;
            }
        }
        int leftX = str[0] == 'x' ? 1 : 0;
        int leftY = str[0] == 'y' ? 1 : 0;
        int ans = 0;
        for (int i = 1; i < n; i++) {
            // 尝试每一刀 砍在i位置，然后统计两边的x和y的数量
            if ((leftX == leftY) || (xAll - leftX) == (yAll - leftY)) {
                ans++;
            }
            leftX += str[i] == 'x' ? 1 : 0;
            leftY += str[i] == 'y' ? 1 : 0;
        }
        return ans;
    }

}
