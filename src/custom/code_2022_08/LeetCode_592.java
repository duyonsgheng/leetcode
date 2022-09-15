package custom.code_2022_08;

/**
 * @ClassName LeetCode_592
 * @Author Duys
 * @Description
 * @Date 2022/8/29 13:22
 **/
// 592. 分数加减运算
public class LeetCode_592 {

    public String fractionAddition(String expression) {
        // 先转化
        int n = expression.length();
        long x = 0; // 分子
        long y = 1; // 分母
        int index = 0;
        while (index < n) {
            long x1 = 0;
            int pre = 1;
            if (expression.charAt(index) == '-' || expression.charAt(index) == '+') {
                pre = expression.charAt(index) == '-' ? -1 : 1;
                index++;
            }
            while (index < n && expression.charAt(index) >= '0' && expression.charAt(index) <= '9') {
                x1 = x1 * 10 + expression.charAt(index) - '0';
                index++;
            }
            x1 = x1 * pre;
            //  是 /
            index++;
            // 分母
            long y1 = 0;
            while (index < n && expression.charAt(index) >= '0' && expression.charAt(index) <= '9') {
                y1 = y1 * 10 + expression.charAt(index) - '0';
                index++;
            }
            x = x * y1 + x1 * y;
            y = y * y1;
        }
        if (x == 0) {
            return "0/1";
        }
        long gcd = gcd(Math.abs(x), y);
        return Long.toString(x / gcd) + "/" + Long.toString(y / gcd);
    }

    public long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
