package pro;

/**
 * @ClassName ManacherCode
 * @Author Duys
 * @Description manacher专题
 * @Date 2022/2/10 9:47
 **/
public class Manacher {

    /**
     * 对一个字符串求最长的回文字串
     * 解法1：暴力
     * 思路：以每一个位置作为中心点然后往两边扩，然后左指针和右指针一次往下一个，比较是否相同。但是这个方式只能解决奇数长度的回文，
     * 也就是说，必须要是实轴的情况下才能找到回文，如果回文长度是偶数的情况下，是不能这么处理的，那么接下来的manacher就出现了。
     * 解法2：manacher算法
     * 概念：
     * 1.回文直径和回文半径
     * 2.回文半径数组
     * 3.最右回文边界 R，不管从哪里往两边扩的，记录最右的边界，如果能把这个边界推高则更新，否则不更新。
     * 4.取得最右回文有边界的时候，中心点（C）在哪里。这个变量和第3点的变量是伴生的。两个变量如果要更新则同时更新。
     * 流程：
     * 1.当来到i位置的时候，如果i是在r的外部，暴力扩展就行。
     * 2.当来到i位置的时候，如果i是在r的内部，这种情况下是由操作空间的，可以优化。那么C和R就派上用场了，C的对称点C'，R的对称点L
     * 2.1 i根据C点对称点 i'，i'点 在   L到R范围内。那么 i的回文半径就是i'的回文半径
     * 2.2 i根据C点对称点 i'，i'点 不在 L到R范围内。那么i的会问半径就是i到R的距离。
     * 2.3 i根据C点对称点 i'，i'点的左边界和L相同。i的回文半径最小就是i'的回文半径，接下来验证 i 回文半径长度左右的字符是否相同，看看能不能推高这个长度
     */

    // coding
    public static int manacher(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // 1.先生成manacher字符串
        char[] str = manacherString(s);
        // 2.计算回文半径数组
        int[] pArr = new int[str.length];
        // 3.回文半径的最右边界的下一个位置
        int R = -1;
        // 4.回文半径对应的中心点和R配套的
        int C = -1;
        int ans = Integer.MIN_VALUE;
        // 对manacher串每一个位置求回文半径
        for (int i = 0; i < str.length; i++) {
            // 当i在R的范围内，就是i被R罩住了，最少的回文半径就是i到R的距离,当i不在R的范围内，那么i的回文半径最少都是1
            // 当i在R的范围内，无论是i相对于C的对称点在不在L 到R 范围内，最少都是R到i的距离。
            // 这里 2 *C -i 就是再计算i的对称点
            // 无论是上面哪一种情况，i再R的范围内，回文半径最少都是 i对称位置的回文半径长度
            // Math.min(pArr[2 * C - i], R - i) 哪一个区域不用去验证，直接去设置
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            // 上面求了最小的
            // 这里来计算可能会再最少长度的基础上进行扩
            // 可以往两边扩
            // 从上面的到的最小，然后往左，往右去扩。看看能不能继续推高当前i位置的回文半径
            while (i + pArr[i] < str.length && i - pArr[i] > -1 && str[i + pArr[i]] == str[i - pArr[i]]) {
                pArr[i]++;
            }
            // 如果推高了R，那么C也要跟着更新
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            ans = Math.max(ans, pArr[i]);
        }
        // 这里的ans是根据manacher串计算出来的。所以再计算实际长度的时候，只需要半径-1就行了
        return ans - 1;
    }

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[charArr.length * 2 + 1];
        int index = 0;
        // 再所有的奇数位置添加 '#'
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }
}
