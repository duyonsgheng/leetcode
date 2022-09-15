package duys.class_09_09;

/**
 * @ClassName DeleteChar
 * @Author Duys
 * @Description 消除相同字符，最优的消除策略
 * @Date 2021/9/13 10:20
 **/
public class DeleteChar {
    /**
     * 如果一个字符相邻的位置没有相同字符，那么这个位置的字符出现不能被消掉。比如:"ab"，其中a和b都不能被消掉
     * 如果一个字符相邻的位置有相同字符，就可以一起消掉。比如:“abbbc”，中间一串的b是可以被消掉的，
     * 消除之后剩下“ac”。某些字符如果消掉了，剩下的字符认为重新靠在一起
     * 给定一个字符串，你可以决定每一步消除的顺序，目标是请尽可能多的消掉字符，返回最少的剩余字符数量
     * 比如："aacca", 如果先消掉最左侧的"aa"，那么将剩下"cca"，然后把"cc"消掉，剩下的"a"将无法再消除，返回1
     * 但是如果先消掉中间的"cc"，那么将剩下"aaa"，最后都消掉就一个字符也不剩了，返回0，这才是最优解。
     * 再比如："baaccabb"，如果先消除最左侧的两个a，剩下"bccabb"，如果再消除最左侧的两个c，剩下"babb"，
     * 最后消除最右侧的两个b，剩下"ba"无法再消除，返回2
     * 而最优策略是：先消除中间的两个c，剩下"baaabb"，再消除中间的三个a，剩下"bbb"，最后消除三个b，
     * 不留下任何字符，返回0，这才是最优解
     */
    // 暴力解答
    public static int delete1(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() < 2) {
            return s.length();
        }
        int minL = s.length();
        // 枚举
        for (int L = 0; L < s.length(); L++) {
            for (int R = L + 1; R < s.length(); R++) {
                // 从前到后进行枚举
                if (canDelete(s.substring(L, R + 1))) {
                    // 因为L~R位置上已经消除了
                    // 那么就剩下 0~L-1 和 R+1 ~ s.length
                    minL = Math.min(minL, delete1(s.substring(0, L) + s.substring(R + 1, s.length())));
                }
            }
        }
        return minL;
    }

    public static boolean canDelete(String s) {
        char[] str = s.toCharArray();
        for (int i = 1; i < str.length; i++) {
            if (str[i - 1] != str[i]) {
                return false;
            }
        }
        return true;
    }


    // 暴力版本的改良
    public static int delete2(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() < 2) {
            return s.length();
        }
        char[] str = s.toCharArray();
        return process1(str, 0, str.length - 1, false);
    }

    // 参数含义：
    // L ~ R 这是范围
    // has 就是在L~R这个范围开始处理的时候，前面有没有跟着L位置的字符
    // 返回最少还能剩下多少个字符消不掉
    public static int process1(char[] str, int L, int R, boolean has) {
        if (L > R) { // 右边大于右边了，无意义
            return 0;
        }
        // 只剩下一个字符了，前端如果还跟着和L位置相同的字符，那么可以消掉
        if (L == R) {
            return has ? 0 : 1;
        }
        // index表示往后迭代的位置，如果有和L位置相同的，index表示了最后不同的位置，也就是不等于L位置字符的开头位置
        int index = L;
        // K表示，如果前面有和L位置相同的字符K>1,如果没有，那么K=1
        int K = has ? 1 : 0;
        // 无论后面的情况如何，K 只能>1 或者 = 1
        while (index <= R && str[index] == str[L]) {
            index++;
            K++;
        }
        // 如果K > 1 。表示前面的字符可以和当前index - 1 范围内的位置 一起消除
        int p1 = (K > 1 ? 0 : 1) + process1(str, index, R, false);
        int p2 = Integer.MAX_VALUE;
        // 枚举index ~R 之间的情况
        for (int i = index; i <= R; i++) {
            // 意思是i位置和 i-1位置的字符不相同
            if (str[i] == str[L] && str[i] != str[i - 1]) {
                // a  a  a   c   b  b  c  a  a x x
                //         index          i
                // 看看 index 到 i 这之间的字符能不能消掉，如果可以消掉，那么index前面和i后面的看看能不能连城一片消掉
                if (process1(str, index, i - 1, false) == 0) {
                    p2 = Math.min(p2, process1(str, i, R, K != 0));
                }
            }
        }
        return Math.min(p1, p2);
    }

    // 递归的动态规划
    public static int delete3(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() < 2) {
            return s.length();
        }
        char[] str = s.toCharArray();
        int N = str.length;
        // dp啥含义 L ~ R 范围内 跟着 L 位字符的消除后的最小剩余字符
        // L>R的区域是无效的
        int[][][] dp = new int[N][N][2];
        for (int L = 0; L <= N; L++) {
            for (int R = 0; R <= N; R++) {
                for (int K = 0; K <= 1; K++) {
                    dp[L][R][K] = -1;
                }
            }
        }
        return process2(str, 0, N - 1, false, dp);
    }

    public static int process2(char[] str, int L, int R, boolean has, int[][][] dp) {
        if (L > R) { // 右边大于右边了，无意义
            return 0;
        }
        int K = has ? 1 : 0;
        // 只剩下一个字符了，前端如果还跟着和L位置相同的字符，那么可以消掉
        if (dp[L][R][K] != -1) {
            return dp[L][R][K];
        }
        int ans = 0;
        if (L == R) {
            ans = has ? 0 : 1;
        } else {
            // index表示往后迭代的位置，如果有和L位置相同的，index表示了最后不同的位置，也就是不等于L位置字符的开头位置
            int index = L;
            // K表示，如果前面有和L位置相同的字符K>1,如果没有，那么K=1
            // 无论后面的情况如何，K 只能>1 或者 = 1
            while (index <= R && str[index] == str[L]) {
                index++;
                K++;
            }
            // 如果K > 1 。表示前面的字符可以和当前index - 1 范围内的位置 一起消除
            int p1 = (K > 1 ? 0 : 1) + process2(str, index, R, false, dp);
            int p2 = Integer.MAX_VALUE;
            // 枚举index ~R 之间的情况
            for (int i = index; i <= R; i++) {
                // 意思是i位置和 i-1位置的字符不相同
                if (str[i] == str[L] && str[i] != str[i - 1]) {
                    // a  a  a   c   b  b  c  a  a x x
                    //         index          i
                    // 看看 index 到 i 这之间的字符能不能消掉，如果可以消掉，那么index前面和i后面的看看能不能连城一片消掉
                    if (process2(str, index, i - 1, false, dp) == 0) {
                        p2 = Math.min(p2, process2(str, i, R, K > 0, dp));
                    }
                }
            }

            ans = Math.min(p1, p2);
        }
        dp[L][R][K] = ans;
        return ans;
    }

    public static void main(String[] args) {
        String s = "aabbcaabcbcc";
        System.out.println(delete1(s));
        System.out.println(delete2(s));
    }
}
