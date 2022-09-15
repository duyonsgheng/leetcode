package week.week_2022_08_05;

/**
 * @ClassName Code_01_Cakes
 * @Author Duys
 * @Description
 * @Date 2022/9/1 8:42
 **/
// nim博弈 和 巴什博弈
// 有a块草莓蛋糕，有b块芝士蛋糕，两人轮流拿蛋糕
// 每次不管是谁只能选择在草莓蛋糕和芝士蛋糕中拿一种
// 拿的数量在1~m之间随意
// 谁先拿完最后的蛋糕谁赢
// 返回先手赢还是后手赢
public class Code_01_Cakes {

    // nim博弈两个人同时从多个堆拿东西，谁先面对0的情况。谁输了
    // 那么我们可以思考一个问题，如果开始情况所有堆的东西亦或和为0，那么后手一定可以让状态变为亦或和为0，那么先手去拿的时候到最后一定就是遇到全为0
    // 所以异或和不为0，先手赢了，否则后手赢了

    // 巴什博弈 两个拿一堆的东西，每次能拿 1~m个，如果当前堆的东西数量是 m+1 的整数倍，先手一定赢了
    // 比如 30  m = 7 先手让30变成8的整数倍，拿走了6个，还剩下24 无论后手拿几个，先手始终让剩下的是8的整数倍，最后后手面对的情况就是 <=8 个，那么先手在最后一次可以拿完

    public static String whoWin(int a, int b, int m) {
        if (m >= Math.max(a, b)) {
            return a != b ? "先手" : "后手";
        }
        // 蛋糕一样多，异或和一定为0，先手一定输了
        if (a == b) {
            return "后手";
        }
        // a!=b
        // 关注差值，如果谁先面对差值为0的情况，谁就输了
        int rest = Math.max(a, b) - Math.min(a, b);

        return rest % (m + 1) != 0 ? "先手" : "后手";
    }
}
