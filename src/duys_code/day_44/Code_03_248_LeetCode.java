package duys_code.day_44;

/**
 * @ClassName Code_03_248_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/strobogrammatic-number-iii/
 * @Date 2022/1/10 15:40
 **/
public class Code_03_248_LeetCode {
    // 中心对称数
    // 数位DP

    // 比如现在 l = 234  和= 421312
    // 先 234 - 999
    // 1001 - 9999
    // 10001 - 99999
    // 100001 - 421312  4部分累加
    public static int strobogrammaticInRange(String l, String h) {
        char[] low = l.toCharArray();
        char[] high = h.toCharArray();
        if (!equalMore(low, high)) {
            return 0;
        }
        int lowLen = low.length;
        int highLen = high.length;
        // 如果长度相等
        // l= 123 r = 234 那么就算 123 到 999 的，然后算 234 到999的，最后用 123到999的减去234到999的就是答案
        if (lowLen == highLen) {
            int p1 = up(low, 0, false, 1);
            int p2 = up(high, 0, false, 1);
            return p2 - p1 + (valid(high) ? 1 : 0);
        }
        int ans = 0;
        // 开始算 位数差的那一部分
        for (int i = lowLen + 1; i < highLen; i++) {
            ans += all(i);
        }
        ans += up(low, 0, false, 1);
        ans += down(high, 0, false, 1);
        return ans;
    }

    public static int down(char[] high, int left, boolean ll, int rs) {
        int N = high.length;
        int right = N - 1 - left;
        if (left > right) {
            return ll || (!ll && rs != 2) ? 1 : 0;
        }
        if (ll) {
            return num(N - (left << 1));
        } else {
            int ways = 0;
            for (char cha = (N != 1 && left == 0) ? '1' : '0'; cha < high[left]; cha++) {
                if (convert(cha, left != right) != -1) {
                    ways += down(high, left + 1, true, rs);
                }
            }
            int convert = convert(high[left], left != right);
            if (convert != -1) {
                if (convert < high[right]) {
                    ways += down(high, left + 1, false, 0);
                } else if (convert == high[right]) {
                    ways += down(high, left + 1, false, rs);
                } else {
                    ways += down(high, left + 1, false, 2);
                }
            }
            return ways;
        }
    }

    public static int all(int len) {
        int ans = 0;
        // len 是0的时候，表示当前只有一种选择，就是什么也不做
        if (len == 0) {
            ans = 1;
        }
        // 有 1 8 0 三种
        if (len == 1) {
            ans = 3;
        }
        for (int i = (len & 1) == 0 ? 2 : 3; i < len; i += 2) {
            ans *= 5;
        }
        return ans << 2;
    }

    public static int all(int len, boolean init) {
        if (len == 0) { // init == true，不可能调用all(0)
            return 1;
        }
        if (len == 1) {
            return 3;
        }
        if (init) {
            return all(len - 2, false) << 2;
        } else {
            return all(len - 2, false) * 5;
        }
    }

    // left 就是左边已经做完决定了，那么 n-left+1 就是右边已经做完决定的部分
    // leftMore:因为左边只能 等于 或者大于不能小于，leftMore = ture 表示当前大于low[left]了，为false表示当前的等于了low[left]了
    // rightState: 表示右边做完决定的部分 与原始的 low[right]的关系， =0 表示小于，=1表示等于，=2表示大于
    public static int up(char[] low, int left, boolean leftMore, int rightState) {
        int n = low.length;
        int right = n - left - 1;
        // 两边做决定，往中间走，已经都到了left> right了，说明决定做完了
        if (left > right) {
            // 我当前左边做的决定比原始的大了，满足
            // 我当前左边做的决定和原始相同，但是我右边的也是等于或者大于，满足
            return leftMore || (!leftMore && rightState != 0) ? 1 : 0;
        }
        // 接着做决定，都已经大于了原始了的，直接结算
        if (leftMore) {
            // 做决定的时候，左右是对称的
            return num(n - (left << 1));
        }
        // 左边做完决定的部分还是等于原始的
        else {
            int ways = 0;
            // 当前的left做决定
            // 我决定大于之前的原始
            for (char cur = (char) (low[left] + 1); cur <= '9'; cur++) {
                if (convert(cur, left != right) != -1) {
                    // rightState 右边和左边是一样的
                    ways += up(low, left + 1, true, rightState);
                }
            }
            // 我当前要做一个 和原始相同的结果
            int convert = convert(low[left], left != right);
            if (convert != -1) {
                // 转完后，小于了我的右边
                if (convert < low[right]) {
                    ways += up(low, left + 1, false, 0);
                }
                // 转完后，等于了我的右边
                else if (convert == low[right]) {
                    ways += up(low, left + 1, false, 1);
                } else {   // 转完后，大于了我的右边
                    ways += up(low, left + 1, false, 2);
                }
            }
            return ways;
        }
    }

    // 当前的left位置的ch，diff是left和right是不是相等的，因为只有一个位置了，6和9转了会出问题，当是6 和 9的时候，如果是相等们就不能转转，如果不相等，就能转
    public static int convert(char ch, boolean diff) {
        switch (ch) {
            case '0':
                return '0';
            case '1':
                return '1';
            case '6':
                return diff ? '9' : -1;
            case '8':
                return '8';
            case '9':
                return diff ? '6' : -1;
            default:
                return -1;
        }
    }


    public static int num(int bits) {
        // 我还剩下1位
        if (bits == 1) {
            return 3;
        }
        if (bits == 2) {
            // 00 11 88 69 96 总共5种组合
            return 5;
        }
        int ans = 0;
        int p1 = 3;
        int p2 = 5;
        // x x x  最外面有 5种可能，里面1位有3种可能
        // x x x x 最外面有 5种，里面有 5种
        // x x x x x 最外面2个位置 5种里面3个位置 15种 5*15
        // 就往下递推
        for (int i = 3; i <= bits; i++) {
            ans = 5 * p1;
            p1 = p2;
            p2 = ans;
        }
        return ans;
    }

    // 如果左边小于右边，没戏
    public static boolean equalMore(char[] low, char[] high) {
        if (low.length != high.length) {
            return low.length < high.length;
        }
        for (int i = 0; i < low.length; i++) {
            if (low[i] != high[i]) {
                return low[i] < high[i];
            }
        }
        return true;
    }

    public static boolean valid(char[] str) {
        int L = 0;
        int R = str.length - 1;
        while (L <= R) {
            boolean t = L != R;
            if (convert(str[L++], t) != str[R--]) {
                return false;
            }
        }
        return true;
    }
}
