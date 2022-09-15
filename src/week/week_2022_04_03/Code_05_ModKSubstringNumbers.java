package week.week_2022_04_03;

/**
 * @ClassName Code_05_ModKSubstringNumbers
 * @Author Duys
 * @Description
 * @Date 2022/4/21 10:42
 **/
// 来自微众
// 4.11笔试
// 给定n位长的数字字符串和正数k，求该子符串能被k整除的子串个数
// (n<=1000，k<=100)
public class Code_05_ModKSubstringNumbers {

    // 这个题需要一点想象力
    // 当然暴力解答很好做，双层for循环，每一个字串都去尝试，但是没分
    // 那么我们有哪些办法来降维呢？dp？二分？贪心？
    // 首先排除二分。二分首先需要确定一个范围，算整个字串的复杂度就是暴力过程了。没必要
    // 贪心呢？有可贪的点吗？好像不直观，先搁一边
    // dp呢？看着像范围上的尝试模型，有一个问题，当我来到i位置，我需要依次剥掉前面的字符，这是一个头疼的问题。


    // 我们剥掉前面的字符，其实是一个什么过程，
    // 比如 2345
    // 当前来到3，剥掉2，其实是剥掉了20，来到4剥掉2，其实是剥掉了200
    // 那么我们需要从前往后推，依次把前面剩下的余数，往后传下去
    public static int modK(String str, int k) {
        int[] cur = new int[k];
        int[] next = new int[k];
        int mod = 0; // 就当前来到的字符的整个数字 % k的余数
        int ans = 0;
        for (char cha : str.toCharArray()) {
            // 先把余数往下传递
            for (int i = 0; i < k; i++) {
                next[(i * 10) % k] += cur[i];
                cur[i] = 0;
            }
            int[] tmp = cur;
            cur = next;
            next = tmp;
            // 把mod往后推了
            mod = ((mod * 10) + (cha - '0')) % k;
            ans += (mod == 0 ? 1 : 0) + cur[mod];
            cur[mod]++;
        }
        return ans;
    }
}
