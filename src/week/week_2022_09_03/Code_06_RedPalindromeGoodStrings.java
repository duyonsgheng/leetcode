package week.week_2022_09_03;

/**
 * @ClassName Code_06_RedPalindromeGoodStrings
 * @Author Duys
 * @Description
 * @Date 2022/9/22 13:08
 **/
// 来自阿里
// 小红定义一个仅有r、e、d三种字符的字符串中
// 如果仅有一个长度不小于2的回文子串，那么这个字符串定义为"好串"
// 给定一个正整数n，输出长度为n的好串有多少个
// 结果对10^9 + 7取模， 1 <= n <= 10^9
// 示例：
// n = 1, 输出0
// n = 2, 输出3
// n = 3, 输出18
// https://www.mashibing.com/question/detail/37485
public class Code_06_RedPalindromeGoodStrings {
    // 分析 ：如果回文串 只有 一个 且不小于2的，那么只能是 XX 或者XYX 两种，没有更多的 例如 rdeed 其中长度是4，那么必然会包含了一个长度为2的，不满足了
    // 当回文串是XX模式的时候
    // 1.XX 在字符串的首部 ee r第三个填r，那么后面的就确定了 第三个填d，后面的也确定了，所以6种
    // 2.xx 在字符串的中间，  reed 和 deer 也就确定了，所以也是6种
    // 3.xx 在字符串的末尾，也是6种
    // 当回文是XYX模式
    // 同样三种模式 首部 末尾 中间
    // 在首部和末尾同样是 6种，但是在 中间就不行了
    // 6+6+6+6+6 -> 6*(n-1)
    // 重要的是分析能力
    public int ways1(int n) {
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 3;
        }
        // rrd rre drr err
        // rdr rer ....
        if (n == 3) {
            return 18;
        }
        return 6 * (n - 1);
    }
}
