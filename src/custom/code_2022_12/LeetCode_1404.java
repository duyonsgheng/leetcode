package custom.code_2022_12;

/**
 * @ClassName LeetCode_1404
 * @Author Duys
 * @Description
 * @Date 2022/12/27 15:56
 **/
// 1404. 将二进制表示减到 1 的步骤数
public class LeetCode_1404 {
    // 如果当前数字为偶数，则将其除以 2
    // 如果当前数字为奇数，则将其加上 1
    public int numSteps(String s) {
        int n = s.length();
        int ans = 0;
        boolean is1 = false;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                // 如果还没有遇到过1，直接除以2，边0甩出去，一次操作了
                // 如果已经遇到了1了，说明下一次会+1 除以2，把当前的0处理掉，所以2次操作
                ans += is1 ? 2 : 1;
            } else {
                // 1.如果还没有遇到1，那么当前的1需要+1 和 除以2，搞掉，2次操作，如果当前这个1是最后的1了，就不需要操作了
                // 2.遇到过1了，当前的1一定会被右边的1+1变成0，知后只需要除以2，一次操作就没了
                if (!is1) {
                    if (i != 0) {
                        ans += 2;
                    }
                    is1 = true;
                } else {
                    ans += 1;
                }
            }
        }
        return ans;
    }
}
